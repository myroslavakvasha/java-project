/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.parsers.rss;

import hr.algebra.model.Category;
import hr.algebra.model.EnrichedImage;
import hr.algebra.model.Mission;
import hr.algebra.model.NasaImage;
import hr.algebra.model.Photographer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author kvash
 */
public class ImageEnricher {

    private ImageEnricher() {
    }
    
    public static List<EnrichedImage> enrich
        (List<NasaImage> images, List<Mission> missions, List<Category> categories, List<Photographer> photographers) throws Exception{
        
            if(images.isEmpty() || missions.isEmpty() || categories.isEmpty() || photographers.isEmpty()){
                throw new Exception("Error happened while attempting to process data");
            }
            
            int roundRobinCounter = 0;
            var result = new ArrayList<EnrichedImage>();
            
            for (var image : images) {
                String searchText = (image.getTitle() + " " + image.getDescription()).toLowerCase();
                
                Optional<Mission> matchedMissionOptional =
                        missions.stream()
                            .filter(m -> searchText.contains(m.getName().toLowerCase()))
                            .findFirst();
                
                image.setIdMission(matchedMissionOptional.map(Mission::getId).orElse(10));
                // in db it's for sure index 10 (identity insert) - Unknown mission
               
                List<Category> matchedCategories =
                        categories.stream()
                            .filter(c -> searchText.contains(c.getName().toLowerCase()))
                            .collect(Collectors.toList());
              
                Photographer currentPhotographer = photographers.get(roundRobinCounter % photographers.size());
                image.setIdPhotographer(currentPhotographer.getId());

                roundRobinCounter++;
                
                var enrichedImage = new EnrichedImage(image, matchedCategories);
                result.add(enrichedImage);
            }
            
            return result;
    }
}
