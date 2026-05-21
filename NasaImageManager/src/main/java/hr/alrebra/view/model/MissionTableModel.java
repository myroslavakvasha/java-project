/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.alrebra.view.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import hr.algebra.model.Mission;

/**
 *
 * @author kvash
 */
public class MissionTableModel extends AbstractTableModel {

    private static final String[] COLUMN_NAMES = {"Id", "Name", "Description"};
    private List<Mission> missions;

    public MissionTableModel(List<Mission> missions) {
        this.missions = missions;
    }

    public void setMissions(List<Mission> missions) {
        this.missions = missions;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return missions.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return missions.get(rowIndex).getId();
            case 1:
                return missions.get(rowIndex).getName();
            case 2:
                return missions.get(rowIndex).getDescription();
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
