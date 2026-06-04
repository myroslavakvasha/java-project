/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model.dto;

import hr.algebra.utilities.jaxb.LocalDateTimeAdapter;
import java.time.LocalDateTime;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author kvash
 */
@XmlRootElement(name = "nasaImage")
@XmlAccessorType(XmlAccessType.FIELD)
public class NasaImageExportDto {

    private int id;
    private String title;
    private String link;
    private String description;
    private String picturePath;

    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime publishedDate;

    private String missionName;
    private String photographerName;

    @XmlElementWrapper(name = "categories")
    @XmlElement(name = "category")
    private List<String> categoryNames;

    public NasaImageExportDto() {
    }

    public NasaImageExportDto(int id, String title, String link, String description, String picturePath, LocalDateTime publishedDate, String missionName, String photographerName) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.description = description;
        this.picturePath = picturePath;
        this.publishedDate = publishedDate;
        this.missionName = missionName;
        this.photographerName = photographerName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public LocalDateTime getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDateTime publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getMissionName() {
        return missionName;
    }

    public void setMissionName(String missionName) {
        this.missionName = missionName;
    }

    public String getPhotographerName() {
        return photographerName;
    }

    public void setPhotographerName(String photographerName) {
        this.photographerName = photographerName;
    }

    public List<String> getCategoryNames() {
        return categoryNames;
    }

    public void setCategoryNames(List<String> categoryNames) {
        this.categoryNames = categoryNames;
    }
}
