/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model.export;

import hr.algebra.model.dto.NasaImageExportDto;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kvash
 */
@XmlRootElement(name = "nasaImages")
@XmlAccessorType(XmlAccessType.FIELD)
public class NasaImageExportList {

    @XmlElement(name = "nasaImage")
    private List<NasaImageExportDto> items = new ArrayList<>();

    public NasaImageExportList() {}

    public NasaImageExportList(List<NasaImageExportDto> items) {
        this.items = items;
    }

    public List<NasaImageExportDto> getItems() {
        return items;
    }
}