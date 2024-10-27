/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.common;

import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import util.FormatNumber;

/**
 *
 * @author Hoang
 */
public class SuggestPriceButton {

    public JToggleButton[] btnSuggest = new JToggleButton[6]; // Mảng chứa các nút gợi ý giá
    private double finalPrice; // Giá trị để tính toán
    private JPanel panel; // JPanel để chứa các nút gợi ý
    private ButtonGroup buttonGroup; // Nhóm các nút toggle

    public SuggestPriceButton(JPanel panel) {
        this.panel = panel;
        this.buttonGroup = new ButtonGroup();

        // Khởi tạo các nút toggle
        for (int i = 0; i < btnSuggest.length; i++) {
            btnSuggest[i] = new JToggleButton();
            btnSuggest[i].setFont(new Font("Arial", Font.PLAIN, 14));
            btnSuggest[i].setVisible(false);
            buttonGroup.add(btnSuggest[i]); // Thêm mỗi nút vào nhóm buttonGroup
        }
    }

    public List<JToggleButton> getListBtnSuggestPrice(){
        return Arrays.asList(btnSuggest);
    }
    
    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public void setButtonsSuggest() {
        int[] suggestions = getSuggestDecimal(finalPrice);
        int length = suggestions.length;

        // Loại bỏ tất cả các nút hiện có trong panel
        panel.removeAll();

        // Đảm bảo không vượt quá số nút có sẵn và chỉ thêm các nút cần thiết vào panel
        for (int i = 0; i < Math.min(length, btnSuggest.length); i++) {
            btnSuggest[i].setText(FormatNumber.formatToVND(suggestions[i]));
            btnSuggest[i].setVisible(true);
            panel.add(btnSuggest[i]); // Thêm nút vào panel
        }

        // Ẩn các nút không cần thiết
        for (int i = length; i < btnSuggest.length; i++) {
            btnSuggest[i].setVisible(false);
        }

        // Cập nhật giao diện của panel
        panel.revalidate();
        panel.repaint();
    }

    private int[] getSuggestDecimal(double finalPrice) {
        int billAmount = (int) finalPrice;
        List<Integer> denominations = getSuggestedDenominations(billAmount);

        // Convert List to array
        return denominations.stream().mapToInt(i -> i).toArray();
    }

    private List<Integer> getSuggestedDenominations(int billAmount) {
        List<Integer> suggestedDenominations = new ArrayList<>();

        // Thêm số tiền hóa đơn gốc
        suggestedDenominations.add(billAmount);

        // Tạo các mệnh giá gần với số tiền đầu vào
        int[] nearbyAmounts = {
            ((billAmount / 1000) + 1) * 1000, // Rounding up to nearest 1000
            ((billAmount / 10000) + 1) * 10000, // Rounding up to nearest 10,000
            ((billAmount / 50000) + 1) * 50000, // Rounding up to nearest 50,000
            ((billAmount / 100000) + 1) * 100000, // Rounding up to nearest 100,000
            ((billAmount / 200000) + 1) * 200000, // Rounding up to nearest 200,000
            ((billAmount / 500000) + 1) * 500000 // Rounding up to nearest 500,000
        };

        for (int amount : nearbyAmounts) {
            if (amount > billAmount && !suggestedDenominations.contains(amount)) {
                suggestedDenominations.add(amount);
            }
        }

        // Sắp mảng suggestedDenominations xếp tăng dần
        Collections.sort(suggestedDenominations);

        return suggestedDenominations;
    }
}
