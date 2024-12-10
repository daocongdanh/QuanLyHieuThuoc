/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.common;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Hoang
 */
public class TableActionCellRenderReturnManage extends DefaultTableCellRenderer {

    TableActionEventReturnManage event;


    public TableActionCellRenderReturnManage() {
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        // Gọi hàm cha để lấy component mặc định
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Nếu value là null, hiển thị PanelActionReturnManage
        if (value == null) {
            PanelActionReturnManage action = new PanelActionReturnManage();
            action.initEvent(event, row);
            return action; // Trả về panel tùy chỉnh
        } else {
            // Trả về renderer mặc định cho ô không null
            return c;
        }
    }

}
