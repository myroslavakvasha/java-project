/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author kvash
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"id", "title", "link", "description", "picturePath", "publishedDate"})
public final class NasaImage implements Comparable<NasaImage> {

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    private int id;
    private String title;
    private String link;
    private String description;
    private String picturePath;
    private LocalDateTime publishedDate;
    // nullable
    private Integer idMission;
    private Integer idPhotographer;

    public NasaImage() {
    }

    public NasaImage(String title, String link, String description, String picturePath, LocalDateTime publishedDate, Integer idMission, Integer idPhotographer) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.picturePath = picturePath;
        this.publishedDate = publishedDate;
        this.idMission = idMission;
        this.idPhotographer = idPhotographer;
    }

    public NasaImage(int id, String title, String link, String description, String picturePath, LocalDateTime publishedDate, Integer idMission, Integer idPhotographer) {
        this(title, link, description, picturePath, publishedDate, idMission, idPhotographer);
        this.id = id;
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

    public Integer getIdMission() {
        return idMission;
    }

    public void setIdMission(Integer idMission) {
        this.idMission = idMission;
    }

    public Integer getIdPhotographer() {
        return idPhotographer;
    }

    public void setIdPhotographer(Integer idPhotographer) {
        this.idPhotographer = idPhotographer;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NasaImage other = (NasaImage) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "ID " + id + " \"" + title + "\" (" + publishedDate + ")";
    }

    @Override
    public int compareTo(NasaImage o) {
        return Integer.valueOf(this.id).compareTo(o.id);
    }
}
