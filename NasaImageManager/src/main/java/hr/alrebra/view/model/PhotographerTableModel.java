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
public class PhotographerTableModel extends AbstractTableModel{
    
    private static final String[] COLUMN_NAMES = {"Id", "Name", "Surname"};
    private List<Photographer> photographers;

    public PhotographerTableModel(List<Photographer> photographers) {
        this.photographers = photographers;
    }

    public void setPhotographers(List<Photographer> photographers) {
        this.photographers = photographers;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return photographers.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return photographers.get(rowIndex).getId();
            case 1:
                return photographers.get(rowIndex).getName();
            case 2:
                return photographers.get(rowIndex).getSurname();
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
