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
public class MissionTableModel extends BaseTableModel<Mission> {

    private static final String[] COLUMN_NAMES = {"Id", "Name", "Description"};

    public MissionTableModel(List<Mission> items) {
        super(items);
    }

    @Override
    protected String[] getColumnNames() {
        return COLUMN_NAMES;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Mission m = items.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return m.getId();
            case 1:
                return m.getName();
            case 2:
                return m.getDescription();
            default:
                throw new RuntimeException("No such column");
        }
    }
}
