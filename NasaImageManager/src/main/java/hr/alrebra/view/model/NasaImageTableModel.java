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
public class NasaImageTableModel extends BaseTableModel<NasaImage> {

    private static final String[] COLUMN_NAMES
            = {"Id", "Title", "Link", "Description", "Picture path", "Published date", "IdMission", "IdPhotographer"};

    public NasaImageTableModel(List<NasaImage> items) {
        super(items);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        NasaImage i = items.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return i.getId();
            case 1:
                return i.getTitle();
            case 2:
                return i.getLink();
            case 3:
                return i.getDescription();
            case 4:
                return i.getPicturePath();
            case 5:
                return i.getPublishedDate().format(NasaImage.DATE_FORMATTER);
            case 6:
                return i.getIdMission();
            case 7:
                return i.getIdPhotographer();
            default:
                throw new RuntimeException("No such column");
        }
    }

    @Override
    protected String[] getColumnNames() {
        return COLUMN_NAMES;
    }
}
