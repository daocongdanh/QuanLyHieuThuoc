/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.common;

/**
 *
 * @author Hoang
 */
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class TableActionCellEditorOnlyDelete extends DefaultCellEditor {

    private TableActionEventOnlyDelete event;

    public TableActionCellEditorOnlyDelete(TableActionEventOnlyDelete event) {
        super(new JCheckBox());
        this.event = event;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object o, boolean bln, int row, int column) {
        PanelActionOnlyDelete action = new PanelActionOnlyDelete();
        action.initEvent(event, row);
        return action;
    }
}
