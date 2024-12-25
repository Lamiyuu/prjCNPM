/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View.KhieuNaiView;

import Database.Service;
import Model.ModelKhieuNai;
import Model.ModelTaiKhoan;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.UUID;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class CreateKhieuNai extends javax.swing.JPanel {
    private Service service = new Service();
    private ModelTaiKhoan taiKhoan;
    /**
     * Creates new form CreateKhieuNai
     */
    public CreateKhieuNai(ModelTaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
        initComponents();
        txtHoTen.setText(taiKhoan.getHoTen());
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        txtNgayGui.setText(df.format(new Date(System.currentTimeMillis())));
    }
    public ModelKhieuNai getData() {
        // Continue gathering other data
        String hoTen = txtHoTen.getText().trim();
        Date ngayGui = new Date(System.currentTimeMillis());
        String phanLoai = comboPhanLoai.getSelectedItem().toString();
        String tieuDe = txtTieuDe.getText();
        String noiDung = txtNoiDung.getText();
        return new ModelKhieuNai(UUID.randomUUID().toString(), hoTen, phanLoai, tieuDe, noiDung, null, "Chờ duyệt", ngayGui, null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtTieuDe = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtNoiDung = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        comboPhanLoai = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        txtNgayGui = new javax.swing.JLabel();

        jLabel1.setText("Họ tên:");

        txtHoTen.setText("Tự động nhá");

        jLabel3.setText("Tiêu đề:");

        jLabel4.setText("Nội dung");

        txtNoiDung.setColumns(20);
        txtNoiDung.setLineWrap(true);
        txtNoiDung.setTabSize(16);
        txtNoiDung.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txtNoiDung);

        jLabel5.setText("Phân loại:");

        comboPhanLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hạ tầng", "Vệ sinh", "An ninh", "Dịch vụ", "Góp ý", "Khác" }));

        jLabel8.setText("Ngày gửi:");

        txtNgayGui.setText("jLabel9");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtNgayGui, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboPhanLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTieuDe, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHoTen)
                    .addComponent(jLabel1))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(comboPhanLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTieuDe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtNgayGui))
                .addGap(12, 12, 12)
                .addComponent(jLabel4)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboPhanLoai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel txtHoTen;
    private javax.swing.JLabel txtNgayGui;
    private javax.swing.JTextArea txtNoiDung;
    private javax.swing.JTextField txtTieuDe;
    // End of variables declaration//GEN-END:variables
}
