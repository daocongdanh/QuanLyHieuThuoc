/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.common;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Hoang
 */
public class TableActionCellRenderOnlyEdit extends DefaultTableCellRenderer {

    TableActionEventOnlyEdit event;
    private final Color hoverColor = new Color(0xF5F5F5); // Màu xám #F5F5F5

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        // Gọi super để lấy component mặc định

        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        PanelActionOnlyEdit action = new PanelActionOnlyEdit();
        action.initEvent(event, row); // Giữ nguyên sự kiện ban đầu

        // Xác định màu nền dựa trên trạng thái
        if (isSelected) {
            action.setBackground(hoverColor); // Màu nền khi chọn
        } else {
            // Kiểm tra xem hàng có phải là hàng hover hay không
            Point mousePosition = table.getMousePosition();
            if (mousePosition != null) {
                int rowIndex = table.rowAtPoint(mousePosition);
                // Kiểm tra nếu chuột ở trên hàng (và không ở header)
                if (rowIndex != -1 && mousePosition.y > 0) {
                    if (row == rowIndex) {
                        action.setBackground(new Color(0xF5F5F5)); // Màu xám cho hàng hover
                    } else {
                        action.setBackground(Color.WHITE); // Màu nền cho hàng không được chọn
                    }
                } else {
                    action.setBackground(Color.WHITE); // Màu nền cho hàng không được chọn
                }
            } else {
                action.setBackground(Color.WHITE); // Màu nền cho hàng không được chọn
            }
        }

        // Đặt màu chữ cho hàng
        action.setForeground(Color.BLACK);
        return action; // Trả về component tùy chỉnh
    }

}
