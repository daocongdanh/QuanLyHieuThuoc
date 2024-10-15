/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.common;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import java.util.Hashtable;
import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author Hoang
 */

public class EachRowEditor implements TableCellEditor {

    protected Hashtable<Integer, TableCellEditor> editors;
    protected TableCellEditor editor, defaultEditor;
    JTable table;

    public EachRowEditor(JTable table) {
        this.table = table;
        editors = new Hashtable<>();
        // Default editor là một JTextField
        defaultEditor = new DefaultCellEditor(new JTextField());
    }

    // Đặt editor (ví dụ như JComboBox) tại một hàng cụ thể
    public void setEditorAt(int row, TableCellEditor editor) {
        editors.put(row, editor);
    }

    // Trả về editor cho một ô cụ thể, dựa trên hàng
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        // Lấy editor của hàng, nếu không có thì dùng editor mặc định
        editor = editors.get(row);
        if (editor == null) {
            editor = defaultEditor;
        }
        return editor.getTableCellEditorComponent(table, value, isSelected, row, column);
    }

    public Object getCellEditorValue() {
        return editor.getCellEditorValue();
    }

    public boolean stopCellEditing() {
        return editor.stopCellEditing();
    }

    public void cancelCellEditing() {
        editor.cancelCellEditing();
    }

    public boolean isCellEditable(EventObject anEvent) {
        if (anEvent instanceof MouseEvent) {
            selectEditor((MouseEvent) anEvent);
        }
        return editor.isCellEditable(anEvent);
    }

    public void addCellEditorListener(CellEditorListener l) {
        editor.addCellEditorListener(l);
    }

    public void removeCellEditorListener(CellEditorListener l) {
        editor.removeCellEditorListener(l);
    }

    public boolean shouldSelectCell(EventObject anEvent) {
        if (anEvent instanceof MouseEvent) {
            selectEditor((MouseEvent) anEvent);
        }
        return editor.shouldSelectCell(anEvent);
    }

    // Chọn editor cho hàng dựa vào sự kiện chuột
    protected void selectEditor(MouseEvent e) {
        int row;
        if (e == null) {
            row = table.getSelectionModel().getAnchorSelectionIndex();
        } else {
            row = table.rowAtPoint(e.getPoint());
        }
        editor = editors.get(row);
        if (editor == null) {
            editor = defaultEditor;
        }
    }
}
