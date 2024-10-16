// Màu sắc của mấy cái card đc design ở đây, và đây cũng là nơi Lâm phải thay mấy cái string kia bằng dữ liệu lấy từ csdl
package HomeFramePackage.HomeCardPackage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.ImageIcon;

public class CardsGroupPanel extends javax.swing.JPanel {

    
    public CardsGroupPanel() {
        
        initComponents();
        setOpaque(false);
        URL nhanKhauUrl = getClass().getResource("/images/NhanKhau.png");
        URL thuPhiUrl = getClass().getResource("/images/ThuPhi.png");
        URL congNoUrl = getClass().getResource("/images/CongNo.png");
        URL tuThienUrl = getClass().getResource("/images/TuThien.png");
        
        // Kết nối đến cơ sở dữ liệu
        try (Connection conn = DatabasePackage.DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            // Lấy tổng số hộ
            String sqlTongSoHo = "SELECT COUNT(*) AS tongSoHo FROM ho_gia_dinh";
            ResultSet rsTongSoHo = stmt.executeQuery(sqlTongSoHo);
            int tongSoHo = 0;
            if (rsTongSoHo.next()) {
                tongSoHo = rsTongSoHo.getInt("tongSoHo");
            }
            /*
            // Lấy tổng tiền thu phí
            String sqlTongTienThuPhi = "SELECT SUM(tien_thu_phi) AS tongTienThuPhi FROM thu_phi";
            ResultSet rsTongTienThuPhi = stmt.executeQuery(sqlTongTienThuPhi);
            double tongTienThuPhi = 0;
            if (rsTongTienThuPhi.next()) {
                tongTienThuPhi = rsTongTienThuPhi.getDouble("tongTienThuPhi");
            }
            
            // Lấy tổng công nợ
            String sqlTongCongNo = "SELECT SUM(cong_no) AS tongCongNo FROM cong_no";
            ResultSet rsTongCongNo = stmt.executeQuery(sqlTongCongNo);
            double tongCongNo = 0;
            if (rsTongCongNo.next()) {
                tongCongNo = rsTongCongNo.getDouble("tongCongNo");
            }

            // Lấy tổng đóng góp thiện nguyện
            String sqlTongDongGopThienNguyen = "SELECT SUM(tien_tu_thien) AS tongTienDongGopThienNguyen FROM tu_thien";
            ResultSet rsTongTienDongGop = stmt.executeQuery(sqlTongDongGopThienNguyen);
            double tongTienDongGopThienNguyen = 0;
            if (rsTongTienDongGop.next()) {
                tongTienDongGopThienNguyen = rsTongTienDongGop.getDouble("tongTienDongGopThienNguyen");
            }
            */
            // Cập nhật dữ liệu vào các CardPanel
            if (nhanKhauUrl != null && thuPhiUrl != null && congNoUrl != null && tuThienUrl != null) {
                cardPanel1.setData(new CardModel(new ImageIcon(nhanKhauUrl), "Tổng số hộ", String.valueOf(tongSoHo)));
                cardPanel2.setData(new CardModel(new ImageIcon(thuPhiUrl), "Tổng tiền thu phí", "1.000.000"));
                cardPanel3.setData(new CardModel(new ImageIcon(congNoUrl), "Tổng công nợ", "1.000.000"));
                cardPanel4.setData(new CardModel(new ImageIcon(tuThienUrl), "Tổng đóng góp thiện nguyện", "1.000.000"));
            } else {
                System.out.println("Không tìm thấy một hoặc nhiều hình ảnh.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cardPanel1 = new HomeFramePackage.HomeCardPackage.CardPanel();
        cardPanel2 = new HomeFramePackage.HomeCardPackage.CardPanel();
        cardPanel3 = new HomeFramePackage.HomeCardPackage.CardPanel();
        cardPanel4 = new HomeFramePackage.HomeCardPackage.CardPanel();

        setBackground(new java.awt.Color(255, 255, 255));

        cardPanel1.setColor1(new java.awt.Color(153, 51, 0));

        cardPanel2.setColor1(new java.awt.Color(0, 102, 153));

        cardPanel3.setColor1(new java.awt.Color(0, 153, 102));

        cardPanel4.setColor1(new java.awt.Color(51, 153, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(cardPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cardPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cardPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cardPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cardPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private HomeFramePackage.HomeCardPackage.CardPanel cardPanel1;
    private HomeFramePackage.HomeCardPackage.CardPanel cardPanel2;
    private HomeFramePackage.HomeCardPackage.CardPanel cardPanel3;
    private HomeFramePackage.HomeCardPackage.CardPanel cardPanel4;
    // End of variables declaration//GEN-END:variables
}
