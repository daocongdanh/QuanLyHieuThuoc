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

public class TableActionCellEditorOneAction extends DefaultCellEditor {

    private TableActionEventOneAction event;
    private int check = 1;

    public TableActionCellEditorOneAction(TableActionEventOneAction event, int check) {
        super(new JCheckBox());
        this.event = event;
        this.check = check;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object o, boolean bln, int row, int column) {
        PanelActionOneAction action = new PanelActionOneAction();
        action.initEvent(event, row);
        if ( check == 1 ) action.editVer();
        else if ( check == 2 ) action.deleteVer();
        return action;
    }
}
