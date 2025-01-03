/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View.NhanKhauView;

import View.HomeView.Card.CardModel;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.ImageIcon;

/**
 *
 * @author ADMIN
 */
public class NhanKhauCard extends javax.swing.JPanel {

    /**
     * Creates new form NhanKhauCard
     */
    public NhanKhauCard() {
        initComponents();
        setOpaque(false);
        URL approveUrl = getClass().getResource("/images/NhanKhau.png");
        URL dismissUrl = getClass().getResource("/images/DangXuat.png");
        URL pendingUrl = getClass().getResource("/images/pending.png");
        try (Connection conn = Database.DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement()) {

           // Truy vấn dữ liệu
           String thuongTru = "SELECT COUNT(*) AS a FROM nhan_khau WHERE TTTV = 'Thường trú'";
           String tamTru = "SELECT COUNT(*) AS b FROM nhan_khau WHERE TTTV = 'Tạm trú'";
           String tamVang = "SELECT COUNT(*) AS c FROM nhan_khau WHERE TTTV = 'Tạm vắng'";

           int thuongTru1 = 0, tamTru1 = 0, tamVang1 = 0;

           // Lấy dữ liệu
           ResultSet rsTongDaDuyet = stmt.executeQuery(thuongTru);
           if (rsTongDaDuyet.next()) thuongTru1 = rsTongDaDuyet.getInt("a");

           ResultSet rsTongChuaDuyet = stmt.executeQuery(tamTru);
           if (rsTongChuaDuyet.next()) tamTru1 = rsTongChuaDuyet.getInt("b");

           ResultSet rsTongCho = stmt.executeQuery(tamVang);
           if (rsTongCho.next()) tamVang1 = rsTongCho.getInt("c");


           // Cập nhật dữ liệu lên các CardPanel
           if (approveUrl != null && pendingUrl != null && dismissUrl != null) {
               cardPanel1.setData(new CardModel(new ImageIcon(approveUrl), "Thường trú", thuongTru1+""));
               cardPanel2.setData(new CardModel(new ImageIcon(pendingUrl), "Tạm trú", tamTru1+""));
               cardPanel3.setData(new CardModel(new ImageIcon(dismissUrl), "Tạm vắng", tamVang1+""));
           } else {
               System.out.println("Không tìm thấy một hoặc nhiều hình ảnh.");
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
    
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cardPanel1 = new View.HomeView.Card.CardPanel();
        cardPanel2 = new View.HomeView.Card.CardPanel();
        cardPanel3 = new View.HomeView.Card.CardPanel();

        setBackground(new java.awt.Color(255, 255, 255));

        cardPanel1.setColor1(new java.awt.Color(0, 153, 153));
        cardPanel1.setColor2(new java.awt.Color(0, 255, 204));

        cardPanel2.setColor1(new java.awt.Color(255, 0, 153));
        cardPanel2.setColor2(new java.awt.Color(255, 102, 102));

        cardPanel3.setColor1(new java.awt.Color(255, 204, 102));
        cardPanel3.setColor2(new java.awt.Color(255, 153, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(cardPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cardPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cardPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cardPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private View.HomeView.Card.CardPanel cardPanel1;
    private View.HomeView.Card.CardPanel cardPanel2;
    private View.HomeView.Card.CardPanel cardPanel3;
    // End of variables declaration//GEN-END:variables
}
