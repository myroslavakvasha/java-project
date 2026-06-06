/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model;

import java.util.List;

/**
 *
 * @author kvash
 */
public final class EnrichedImage {

    NasaImage image;
    List<Category> matchedCategories;

    public EnrichedImage() {
    }

    public EnrichedImage(NasaImage image, List<Category> matchedCategories) {
        this.image = image;
        this.matchedCategories = matchedCategories;
    }

    public NasaImage getImage() {
        return image;
    }

    public List<Category> getMatchedCategories() {
        return matchedCategories;
    }

}
