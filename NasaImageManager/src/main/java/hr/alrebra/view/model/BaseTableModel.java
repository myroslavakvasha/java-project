/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.alrebra.view.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author kvash
 */
public abstract class BaseTableModel<T> extends AbstractTableModel {

    // subclasses need direct access to it
    protected List<T> items;

    public BaseTableModel(List<T> items) {
        this.items = items;
    }

    public void setItems(List<T> items) {
        this.items = items;
        fireTableDataChanged();
    }
    
    // the base has no way to implement it - abstract
    protected abstract String[] getColumnNames();

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 0) {
            return Integer.class;
        }
        return super.getColumnClass(columnIndex);
    }

    @Override
    public String getColumnName(int column) {
        return getColumnNames()[column];
    }

    @Override
    public abstract Object getValueAt(int rowIndex, int columnIndex);

    @Override
    public int getColumnCount(){
        return getColumnNames().length;
    }

    @Override
    public int getRowCount(){
        return items.size();    
    }
}
