//Cái panel này tú kéo cái table và cái Header vào để có sai thì mình cx ko cần sửa cả Home, rén quá đ dám kéo thẳng vào home đou
//Thay string ở addTable bằng code csdl của Lâm, dùng vòng lặp để lấy 1 số hữu hạn phần tử ở đầu bảng hóa đơn gần đây chẳng hạn
package HomeFramePackage.TablePackage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

public class TablePanel extends javax.swing.JPanel {

    public TablePanel() {
        initComponents();
        ((DefaultTableModel) table1.getModel()).setRowCount(0);
        try (Connection conn = DatabasePackage.DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            // Truy vấn dữ liệu từ bảng apartment_fees
            String sql = "SELECT fee_name, amount, ho, payment_date FROM apartment_fees";
            ResultSet rs = stmt.executeQuery(sql);

            // Lặp qua các dòng kết quả và thêm vào table1
            while (rs.next()) {
                String feeName = rs.getString("fee_name");
                String amount = rs.getString("amount");
                String ho = rs.getString("ho");
                String paymentDate = rs.getString("payment_date");

                // Thêm dữ liệu vào table1
                table1.addRow(new Object[]{feeName, amount, ho, paymentDate});
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new HomeFramePackage.TablePackage.Table();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setText("Khoản thu mới nhất");

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Loại khoản thu", "Tiền thu", "Hộ đóng", "Ngày thu"
            }
        ));
        jScrollPane1.setViewportView(table1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 999, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 950, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private HomeFramePackage.TablePackage.Table table1;
    // End of variables declaration//GEN-END:variables
}
