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
public class HoKhauCard extends javax.swing.JPanel {

    /**
     * Creates new form HoKhauCard
     */
    public HoKhauCard() {
        initComponents();
        setOpaque(false);
        URL homeUrl = getClass().getResource("/images/TrangChu.png");
        URL rentUrl = getClass().getResource("/images/CongNo.png");
        URL blankUrl = getClass().getResource("/images/Blank.png");
        try (Connection conn = Database.DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement()) {

           // Truy vấn dữ liệu
           String daBanQuery = "SELECT COUNT(*) AS a FROM ho_gia_dinh WHERE trangThai = 'Đã bán'";
           String choThueQuery = "SELECT COUNT(*) AS b FROM ho_gia_dinh WHERE trangThai = 'Cho thuê'";
           String trongQuery = "SELECT COUNT(*) AS c FROM ho_gia_dinh WHERE trangThai = 'Trống'";

           int daBan =0, choThue = 0, trong = 0 ;

           // Lấy dữ liệu
           ResultSet rsTongDaDuyet = stmt.executeQuery(daBanQuery);
           if (rsTongDaDuyet.next()) daBan = rsTongDaDuyet.getInt("a");

           ResultSet rsTongChuaDuyet = stmt.executeQuery(choThueQuery);
           if (rsTongChuaDuyet.next()) choThue = rsTongChuaDuyet.getInt("b");

           ResultSet rsTongCho = stmt.executeQuery(trongQuery);
           if (rsTongCho.next()) trong = rsTongCho.getInt("c");


           // Cập nhật dữ liệu lên các CardPanel
           if (homeUrl != null && rentUrl != null && blankUrl != null) {
               cardPanel1.setData(new CardModel(new ImageIcon(homeUrl), "Đã bán", daBan+""));
               cardPanel2.setData(new CardModel(new ImageIcon(rentUrl), "Đang cho thuê", choThue+""));
               cardPanel3.setData(new CardModel(new ImageIcon(blankUrl), "Trống", trong+""));
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

        cardPanel1.setColor1(new java.awt.Color(0, 153, 204));
        cardPanel1.setColor2(new java.awt.Color(0, 255, 255));

        cardPanel2.setColor1(new java.awt.Color(255, 102, 102));
        cardPanel2.setColor2(new java.awt.Color(255, 0, 204));

        cardPanel3.setColor1(new java.awt.Color(204, 204, 255));
        cardPanel3.setColor2(new java.awt.Color(204, 102, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(cardPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cardPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cardPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cardPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private View.HomeView.Card.CardPanel cardPanel1;
    private View.HomeView.Card.CardPanel cardPanel2;
    private View.HomeView.Card.CardPanel cardPanel3;
    // End of variables declaration//GEN-END:variables
}