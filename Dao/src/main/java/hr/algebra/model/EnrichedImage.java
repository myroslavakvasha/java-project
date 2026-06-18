/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model;

import java.util.Set;

/**
 *
 * @author kvash
 */
public final class EnrichedImage {

    private NasaImage image;
    private Set<Category> matchedCategories;

    public EnrichedImage() {
    }

    public EnrichedImage(NasaImage image, Set<Category> matchedCategories) {
        this.image = image;
        this.matchedCategories = matchedCategories;
    }

    public NasaImage getImage() {
        return image;
    }

    public Set<Category> getMatchedCategories() {
        return matchedCategories;
    }

}
