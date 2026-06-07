/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.alrebra.view.model;

import java.util.List;
import hr.algebra.model.Category;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author kvash
 */
public class CategoryTableModel extends BaseTableModel<Category> {

    private static final String[] COLUMN_NAMES = {"Id", "Name"};

    public CategoryTableModel(List<Category> categories) {
        super(categories);
    }
    
    @Override
    protected String[] getColumnNames() {
        return COLUMN_NAMES;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Category c = items.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return c.getId();
            case 1:
                return c.getName();
            default:
                throw new RuntimeException("No such column");
        }
    }
}
