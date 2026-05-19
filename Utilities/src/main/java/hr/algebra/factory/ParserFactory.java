/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.factory;

import java.io.InputStream;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;

/**
 *
 * @author daniel.bele
 */
public class ParserFactory {

    private ParserFactory() {
    }

    public static XMLEventReader createStaxParser(InputStream stream) throws XMLStreamException {
        
        // data is pulled from a stream, XMLEventReader reads an xml file as a stream of events
        // XMLInputFactory creates XMLEventReader:
        
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader eventReader = factory.createXMLEventReader(stream);
        
        return eventReader;
    }
}
