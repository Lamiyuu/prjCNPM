/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text. Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import raven.toast.Notifications;
/**
 *
 * @author lamto
 */
public class CreatePdf {
    public static void create(String soPhong,JTable table) throws IOException, DocumentException {
        
        // Đảm bảo đường dẫn chính xác từ thư mục resources
        String fontPath = CreatePdf.class.getClassLoader().getResource("fonts/arial-unicode-ms.ttf").getPath();
        BaseFont unicodeFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font font = new Font(unicodeFont, 14);
       
        
        // Lấy mô hình dữ liệu của JTable
        TableModel model = table.getModel();
        
        // Xác định thư mục lưu trữ
        String outputDirPath = System.getProperty("user.dir") + "/src/bienlai/";
        File outputDir = new File(outputDirPath);
        
        // Kiểm tra và tạo thư mục nếu chưa tồn tại
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }
        
        // Lấy thời gian hiện tại để tạo tên file (theo định dạng yyyy-MM-dd_HH-mm-ss)
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String formattedDate = now.format(formatter);
        
        // Tạo tên file theo định dạng: soPhong + thời gian
        String fileName = soPhong + "_" + formattedDate + ".pdf";
        
        // Xây dựng đường dẫn đầy đủ cho file PDF
        String filePath = outputDirPath + fileName;

        // Lấy các dữ liệu liên quan đến document
        Document doc = new Document(PageSize.A4);
        
        // Tạo PdfWriter với đường dẫn file đầy đủ
        PdfWriter.getInstance(doc, new FileOutputStream(filePath));
        
        // Mở document để có thể thêm nội dung
        doc.open();

        // Thêm tiêu đề vào tài liệu PDF
        Paragraph p = new Paragraph();
        p.setFont(font);

        String no = "1523";  // Mã số hóa đơn hoặc thông tin gì đó
        p.add("BILL NI: " + no);  // Thêm số hóa đơn vào tiêu đề
        p.setAlignment(Element.ALIGN_CENTER);  // Căn giữa tiêu đề
        doc.add(p);  // Thêm vào tài liệu
        
        doc.add(new Paragraph(""));
        // Thêm dòng "Phòng đóng" với font đã chỉ định
        Paragraph p1 = new Paragraph("Phòng đóng: " + soPhong, font);  
        doc.add(p1);
        Date today = new Date(System.currentTimeMillis());
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        // Thêm dòng "Ngày giờ" với font đã chỉ định
        Paragraph p2 = new Paragraph("DATE AND TIME: " + df.format(today) , font);
        doc.add(p2);

        doc.add(new Paragraph("\n"));
        
        // Tạo PdfPTable với số cột bằng số cột của JTable
        PdfPTable pdfTable = new PdfPTable(model.getColumnCount() - 1);
        
        // Thêm tiêu đề cột vào PdfPTable
        for (int i = 0; i < model.getColumnCount(); i++) {
            if (i == 4) {
                    continue;
                }
            PdfPCell cell = new PdfPCell(new Phrase(model.getColumnName(i), font));  // Thêm font vào Phrase
            pdfTable.addCell(cell);
        }

        // Thêm dữ liệu từ JTable vào PdfPTable
        for (int row = 0; row < model.getRowCount(); row++) {
            for (int col = 0; col < model.getColumnCount(); col++) {
                if (col == 4) {
                    continue;
                }
                Object cellValue = model.getValueAt(row, col);  // Lấy giá trị từ từng ô trong JTable
                String cellText = (cellValue != null) ? cellValue.toString() : "";  // Lấy chuỗi từ giá trị ô
                // Thêm ô vào bảng PDF với font đã chỉ định
                PdfPCell cell = new PdfPCell(new Phrase(cellText, font));  // Áp dụng font cho ô
                pdfTable.addCell(cell);  // Thêm ô vào bảng
            }
        }
        
        // Thêm PdfPTable vào tài liệu PDF
        doc.add(pdfTable);
        
        // Đóng tài liệu PDF sau khi hoàn thành
        doc.close();
        
        System.out.println("PDF đã được tạo và lưu tại: " + filePath);
        JOptionPane.showMessageDialog(null, 
            "PDF đã được tạo và lưu tại:\n" + filePath, 
            "Thông báo", 
            JOptionPane.INFORMATION_MESSAGE);
    }
}
