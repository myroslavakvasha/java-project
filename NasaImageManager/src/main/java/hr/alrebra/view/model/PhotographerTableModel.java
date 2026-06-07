/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.alrebra.view.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import hr.algebra.model.Photographer;

/**
 *
 * @author kvash
 */
public class PhotographerTableModel extends BaseTableModel<Photographer> {

    private static final String[] COLUMN_NAMES = {"Id", "Name", "Surname"};

    public PhotographerTableModel(List<Photographer> items) {
        super(items);
    }

    @Override
    protected String[] getColumnNames() {
        return COLUMN_NAMES;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Photographer p = items.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return p.getId();
            case 1:
                return p.getName();
            case 2:
                return p.getSurname();
            default:
                throw new RuntimeException("No such column");
        }
    }
}
