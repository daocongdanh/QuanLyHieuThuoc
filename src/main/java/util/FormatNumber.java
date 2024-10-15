/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.text.DecimalFormat;
import java.text.ParseException;

/**
 *
 * @author Hoang
 */
public class FormatNumber {

    private static DecimalFormat decimal = new DecimalFormat("#,### đ");

    public static String formatToVND(double amount) {
        return decimal.format(amount);
    }

    public static double parseFromVND(String vndAmount) throws ParseException {
        // Loại bỏ ký tự "đ" và khoảng trắng, rồi thay dấu phẩy
        String cleanedAmount = vndAmount.replace(" đ", "").replace(",", "");
        return Double.parseDouble(cleanedAmount);
    }

}
