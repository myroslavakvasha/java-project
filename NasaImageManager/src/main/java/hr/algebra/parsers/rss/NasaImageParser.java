/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.parsers.rss;

import hr.algebra.factory.ParserFactory;
import hr.algebra.factory.UrlConnectionFactory;
import hr.algebra.model.NasaImage;
import hr.algebra.utilities.FileUtils;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author kvash
 */
public class NasaImageParser {
    
    private static final String RSS_URL = "https://www.nasa.gov/feeds/iotd-feed/";
    private static final String ATTRIBUTE_URL = "url";
    private static final String EXT = ".jpg";
    private static final String DIR = "assets";

    public static List<NasaImage> parse() throws IOException, XMLStreamException {
        
        List<NasaImage> images = new ArrayList<>();
        HttpURLConnection con = UrlConnectionFactory.getHttpUrlConnection(RSS_URL);
        
        try (InputStream is = con.getInputStream()) { // stream will close the connection
            
            XMLEventReader reader = ParserFactory.createStaxParser(is);

            Optional<TagType> tagType = Optional.empty(); // remembers what tag was just opened
            NasaImage image = null; // the current object being built
            StartElement startElement = null; // holds the last opening tag
            
            // move forward through the stream with nextEvent()
            while (reader.hasNext()) {
                
                XMLEvent event = reader.nextEvent();
                
                switch (event.getEventType()) {
                    
                    // hit an opening tag
                    
                    // Every time we hit an opening tag, we save it as startElement and try to match it to our TagType enum.
                    // If it's <item>, that means a new image is starting — so we create a fresh NasaImage and add it
                    // to the list immediately. All subsequent field assignments will go into this object.
                    case XMLStreamConstants.START_ELEMENT -> {
                        
                        startElement = event.asStartElement();
                        String qName = startElement.getName().getLocalPart();
                        tagType = TagType.from(qName);
                        
                        // if it's item, create a new NasaImage
                        if (tagType.isPresent() && tagType.get().equals(TagType.ITEM)) {
                            image = new NasaImage(); // idMission = null, idPhotographer = null by default
                            images.add(image);
                        }
                    }
                    
                    // read the text inside the tag
                    
                    // When we hit text content, we check what tag we're currently inside (tagType) and set the appropriate field.
                    // The image != null guard means we ignore any text that appears before the first <item> (like the channel title).
                    case XMLStreamConstants.CHARACTERS -> {
                        if (tagType.isPresent() && image != null) {
                            
                            // get the data
                            Characters characters = event.asCharacters();
                            String data = characters.getData().trim();
                            
                            // switch by the tag type to add to the correct place
                            switch (tagType.get()) {
                                case TITLE -> {
                                    if (!data.isEmpty()) {
                                        image.setTitle(data);
                                    }
                                }
                                case LINK -> {
                                    if (!data.isEmpty()) {
                                        image.setLink(data);
                                    }
                                }
                                case DESCRIPTION -> {
                                    if (!data.isEmpty()) {
                                        image.setDescription(data);
                                    }
                                }
                                case ENCLOSURE -> {
                                    if (startElement != null && image.getPicturePath() == null) {
                                        Attribute urlAttribute = startElement.getAttributeByName(new QName(ATTRIBUTE_URL));
                                        if (urlAttribute != null) {
                                            handlePicture(image, urlAttribute.getValue());
                                        }
                                    }
                                }
                                case PUB_DATE -> {
                                    if (!data.isEmpty()) {
                                        LocalDateTime publishedDate = LocalDateTime.parse(data, DateTimeFormatter.RFC_1123_DATE_TIME);
                                        image.setPublishedDate(publishedDate);
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }
        return images;

    }

    private static void handlePicture(NasaImage image, String pictureUrl) {
        // if picture is not ok, we must continue!!!
        try {
            String ext = pictureUrl.substring(pictureUrl.lastIndexOf("."));
            if (ext.length() > 4) {
                ext = EXT;
            }
            String pictureName = UUID.randomUUID() + ext; // random unique filename
            String localPicturePath = DIR + File.separator + pictureName;

            FileUtils.copyFromUrl(pictureUrl, localPicturePath); //download
            
            image.setPicturePath(localPicturePath);
        } catch (IOException ex) {
            Logger.getLogger(NasaImageParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private enum TagType {

        ITEM("item"),
        TITLE("title"),
        LINK("link"),
        DESCRIPTION("description"),
        ENCLOSURE("enclosure"),
        PUB_DATE("pubDate");

        private final String name;

        private TagType(String name) {
            this.name = name;
        }

        private static Optional<TagType> from(String name) {
            for (TagType value : values()) {
                if (value.name.equals(name)) {
                    return Optional.of(value);
                }
            }
            return Optional.empty();
        }
    }
    
//    public static void main(String[] args) throws Exception {
//        List<NasaImage> images = parse();
//        for (NasaImage image : images) {
//            System.out.println("Title: " + image.getTitle());
//            System.out.println("Link: " + image.getLink());
//            System.out.println("Description: " + image.getDescription());
//            System.out.println("Picture path: " + image.getPicturePath());
//            System.out.println("Published date: " + image.getPublishedDate());
//            System.out.println("---");
//        }
//        System.out.println("Total parsed: " + images.size());
//    }
}