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
import java.util.EventObject;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class TableActionCellEditorReturnManage extends DefaultCellEditor {

    private TableActionEventReturnManage event;

    public TableActionCellEditorReturnManage(TableActionEventReturnManage event) {
        super(new JCheckBox());
        this.event = event;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        // Kiểm tra xem hàng và cột có hợp lệ không
        if (row >= 0 && row < table.getRowCount() && column >= 0 && column < table.getColumnCount()) {
            // Chỉ cho phép chỉnh sửa nếu giá trị là null
            if (value == null) {
                PanelActionReturnManage action = new PanelActionReturnManage();
                action.initEvent(event, row);
                return action; // Trả về panel cho việc chỉnh sửa
            }
        }
        // Nếu không cho phép chỉnh sửa hoặc hàng/cột không hợp lệ, trả về null
        return null;
    }

}
