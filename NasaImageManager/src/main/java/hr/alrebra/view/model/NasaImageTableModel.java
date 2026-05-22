/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.alrebra.view.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import hr.algebra.model.NasaImage;

/**
 *
 * @author kvash
 */
public class NasaImageTableModel extends AbstractTableModel {

    private static final String[] COLUMN_NAMES
            = {"Id", "Title", "Link", "Description", "Picture path", "Published date", "IdMission", "IdPhotographer"};

    private List<NasaImage> nasaImages;

    public NasaImageTableModel(List<NasaImage> nasaImages) {
        this.nasaImages = nasaImages;
    }

    public void setNasaImages(List<NasaImage> nasaImages) {
        this.nasaImages = nasaImages;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return nasaImages.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return nasaImages.get(rowIndex).getId();
            case 1:
                return nasaImages.get(rowIndex).getTitle();
            case 2:
                return nasaImages.get(rowIndex).getLink();
            case 3:
                return nasaImages.get(rowIndex).getDescription();
            case 4:
                return nasaImages.get(rowIndex).getPicturePath();
            case 5:
                return nasaImages.get(rowIndex).getPublishedDate().format(NasaImage.DATE_FORMATTER);
            case 6:
                return nasaImages.get(rowIndex).getIdMission();
            case 7:
                return nasaImages.get(rowIndex).getIdPhotographer();
            default:
                throw new RuntimeException("No such column");
        }
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
        }
        return super.getColumnClass(columnIndex);
    }

}
