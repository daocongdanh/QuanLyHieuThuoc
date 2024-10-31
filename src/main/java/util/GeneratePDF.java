/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author Hoang
 */
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.draw.LineSeparator;
import entity.Customer;
import entity.Employee;
import entity.Order;
import entity.OrderDetail;
import entity.Product;
import entity.Promotion;
import java.text.DecimalFormat;
import java.util.List;
import java.time.format.DateTimeFormatter;
import view.common.CustomDashedLineSeparator;

public class GeneratePDF {

    private DecimalFormat decimal = new DecimalFormat("#,### đ");
    private DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");

    public void GeneratePDF(Order order) throws IOException, DocumentException {
        String billName = order.getOrderId();
        String path = "bill/hoaDon" + billName + ".pdf";
        File fontFile = new File("data/font/vuArial.ttf");
        BaseFont bf = BaseFont.createFont(fontFile.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Document document = new Document();
        Customer cus = order.getCustomer();
        Employee emp = order.getEmployee();
        List<OrderDetail> listOrders = order.getOrderDetails();
        Promotion promotion = order.getPromotion();
//        float newHeight = 900; // Chiều cao mới của tài liệu (đơn vị là pixel)
//        document.setPageSize(new Rectangle(document.getPageSize().getWidth(), newHeight));
        try {
            PdfWriter.getInstance(document, new FileOutputStream(path));
            document.open();
            CustomDashedLineSeparator dotLine = new CustomDashedLineSeparator();
            dotLine.setDash(10);
            dotLine.setGap(7);
            dotLine.setLineWidth(1);

            // Font
            Font titleFont = new Font(bf, 18, Font.BOLD);
            Font titleFontMini = new Font(bf, 11, Font.BOLD);
            Font normalFont = new Font(bf, 12, Font.NORMAL);
            Font italicBoldFont = new Font(bf, 11, Font.BOLDITALIC);

            // Title
            Paragraph title = new Paragraph("HÓA ĐƠN BÁN THUỐC", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(20f);
            document.add(title);

            // Line separator
            LineSeparator line = new LineSeparator();
            document.add(line);

            //Infor
            Paragraph creatorInfo = new Paragraph();
            creatorInfo.add(new Chunk("Nhân viên: ", normalFont));
            creatorInfo.add(new Chunk(emp.getName(), titleFontMini));
            Paragraph creatorInfo2 = new Paragraph();
            creatorInfo2.add(new Chunk("Ngày lập: ", normalFont));
            creatorInfo2.add(new Chunk(formatTime.format(order.getOrderDate()), normalFont));
            creatorInfo.setAlignment(Element.ALIGN_LEFT);
            creatorInfo2.setAlignment(Element.ALIGN_LEFT);

            // Buyer details
            Paragraph buyerInfo = new Paragraph();
            buyerInfo.add(new Chunk("Khách hàng: ", normalFont));
            String cusName = "Khách vãng lai";
            if ( cus != null ) cusName = cus.getName();
            buyerInfo.add(new Chunk( cusName, titleFontMini)); 
            Paragraph buyerInfo2 = new Paragraph();
            String cusPhone = "";
            if ( cus != null ){
                cusPhone = cus.getPhone();
            }
            else cusPhone = "Trống";
            buyerInfo2.add(new Chunk("Điện thoại: ", normalFont));
            buyerInfo2.add(new Chunk( cusPhone, normalFont)); 
            buyerInfo.setAlignment(Element.ALIGN_LEFT);
            buyerInfo2.setAlignment(Element.ALIGN_LEFT);

            PdfPTable inforTable = new PdfPTable(2);
            inforTable.setSpacingBefore(10f);
            inforTable.setSpacingAfter(15f);
            inforTable.setWidthPercentage(100);
            PdfPCell inforNV = new PdfPCell(creatorInfo);
            inforNV.setBorder(Rectangle.NO_BORDER);
            PdfPCell inforKH = new PdfPCell(buyerInfo);
            inforKH.setBorder(Rectangle.NO_BORDER);
            inforKH.setHorizontalAlignment(Element.ALIGN_RIGHT);
            PdfPCell inforNV2 = new PdfPCell(creatorInfo2);
            inforNV2.setBorder(Rectangle.NO_BORDER);
            PdfPCell inforKH2 = new PdfPCell(buyerInfo2);
            inforKH2.setBorder(Rectangle.NO_BORDER);
            inforKH2.setHorizontalAlignment(Element.ALIGN_RIGHT);
            inforTable.addCell(inforNV);
            inforTable.addCell(inforKH);
            inforTable.addCell(inforNV2);
            inforTable.addCell(inforKH2);
            document.add(inforTable);
            document.add(dotLine);

            float[] columnWidths = {2f, 1.5f, 1.5f, 1.5f, 1.5f};
            // Product details
            Paragraph productDetails = new Paragraph(" Chi tiết sản phẩm", titleFontMini);
            productDetails.setSpacingBefore(10f);
            productDetails.setSpacingAfter(5f);

            document.add(productDetails);

            PdfPTable productTable = new PdfPTable(5);
            productTable.setWidthPercentage(100);
            productTable.setSpacingBefore(10f);
            productTable.setSpacingAfter(10f);
            // Set column widths
            productTable.setWidths(columnWidths);

            PdfPCell productNameHeader = new PdfPCell(new Phrase("Tên sản phẩm", normalFont));
            productNameHeader.setBorder(Rectangle.NO_BORDER);
            PdfPCell unitHeader = new PdfPCell(new Phrase("Đơn vị tính", normalFont));
            unitHeader.setBorder(Rectangle.NO_BORDER);
            PdfPCell quantityHeader = new PdfPCell(new Phrase("Số lượng", normalFont));
            quantityHeader.setBorder(Rectangle.NO_BORDER);
            PdfPCell unitPricePHeader = new PdfPCell(new Phrase("Đơn giá", normalFont));
            unitPricePHeader.setBorder(Rectangle.NO_BORDER);
            PdfPCell totalPriceHeader2 = new PdfPCell(new Phrase("Thành tiền", normalFont));
            totalPriceHeader2.setBorder(Rectangle.NO_BORDER);

            productTable.addCell(productNameHeader);
            productTable.addCell(unitHeader);
            productTable.addCell(quantityHeader);
            productTable.addCell(unitPricePHeader);
            productTable.addCell(totalPriceHeader2);

            // Sample product data
            for (OrderDetail item : listOrders) {
                Product product = item.getBatch().getProduct();
                PdfPCell productNameCell = new PdfPCell(new Phrase(product.getName()));
                productNameCell.setBorder(Rectangle.NO_BORDER);
                productTable.addCell(productNameCell);
                
                PdfPCell unitCell = new PdfPCell(new Phrase( item.getBatch().getProduct().getUnit().getName()));
                unitCell.setBorder(Rectangle.NO_BORDER);
                productTable.addCell(unitCell);

                PdfPCell quantityCell = new PdfPCell(new Phrase(item.getQuantity() + "", normalFont));
                quantityCell.setBorder(Rectangle.NO_BORDER);
                productTable.addCell(quantityCell);

                PdfPCell unitPriceCell = new PdfPCell(new Phrase(decimal.format(product.getSellingPrice()) + "", normalFont));
                unitPriceCell.setBorder(Rectangle.NO_BORDER);
                productTable.addCell(unitPriceCell);

                PdfPCell totalPriceCell = new PdfPCell(new Phrase(decimal.format(item.getLineTotal()) + "", normalFont));
                totalPriceCell.setBorder(Rectangle.NO_BORDER);
                productTable.addCell(totalPriceCell);
            }
            document.add(productTable);
            document.add(Chunk.NEWLINE);
            document.add(dotLine);
            // Total
            
            double tongHoaDon = listOrders.stream()
                    .mapToDouble(od -> od.getQuantity() * od.getPrice() * (1 + od.getBatch().getProduct().getVAT()))
                    .sum();
            
            double tongGiamGiaSanPham = listOrders.stream()
                    .mapToDouble(od -> {
                        double subTotal = od.getQuantity() * od.getPrice() * (1 + od.getBatch().getProduct().getVAT());
                        return subTotal * od.getDiscount();
                    })
                    .sum();
            
            double tongGiamGiaHoaDon = 0;
            if(order.getPromotion() != null){
                tongGiamGiaHoaDon = (tongHoaDon - tongGiamGiaSanPham) * order.getPromotion().getDiscount();
            }
            
            Paragraph total = new Paragraph();
            total.add(new Chunk("Tổng hóa đơn: ", normalFont));
            total.add(new Chunk(decimal.format(tongHoaDon), FontFactory.getFont(FontFactory.TIMES_BOLD, 12)));
            total.add(Chunk.NEWLINE);
            
            total.add(new Chunk("Tổng giảm giá sản phẩm: ", normalFont));
            total.add(new Chunk(decimal.format(tongGiamGiaSanPham), FontFactory.getFont(FontFactory.TIMES_BOLD, 12)));
            total.add(Chunk.NEWLINE);
            
            total.add(new Chunk("Tổng giảm giá hóa đơn: ", normalFont));
            total.add(new Chunk(decimal.format(tongGiamGiaHoaDon), FontFactory.getFont(FontFactory.TIMES_BOLD, 12)));
            total.add(Chunk.NEWLINE);
            
            total.add(new Chunk("Tổng tiền: ", normalFont));
            total.add(new Chunk(decimal.format(order.getTotalPrice()), FontFactory.getFont(FontFactory.TIMES_BOLD, 12)));
            total.setAlignment(Element.ALIGN_LEFT);
            total.setSpacingBefore(20f);
            total.setSpacingAfter(10f);
            document.add(total);

            document.close();
        } catch (DocumentException | IOException e) {
            MessageDialog.error(null, "Lỗi không thể xuất file PDF");
        }
        PDFImageViewer pdfView = new PDFImageViewer("bill/hoaDon" + billName + ".pdf");
    }

}
