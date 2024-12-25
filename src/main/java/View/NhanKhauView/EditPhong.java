/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View.NhanKhauView;

import Database.Service;
import Model.ModelHoGiaDinh;
import javax.swing.JOptionPane;

/**
 *
 * @author lamto
 */
public class EditPhong extends javax.swing.JPanel {

    /**
     * Creates new form EditPhong
     */
    private Service service = new Service();
    private ModelHoGiaDinh hoGiaDinh;
    /**
     * Creates new form EditPhong
     */
    public EditPhong(ModelHoGiaDinh hoGiaDinh) {
        this.hoGiaDinh = hoGiaDinh;
        initComponents();
    }
    
    public void loadData(ModelHoGiaDinh data){
        if (data != null) {
            txtSoPhong.setText(data.getSoPhong());
            txtDienTich.setText(data.getDienTich() + "");
            txtSoNguoi.setText(data.getSoNguoi() + "");
            txtSoXeMay.setText(data.getSoXeMay() + "");
            txtSoOto.setText(data.getSoOto() + "");
            comboTrangThai.setSelectedItem(data.getTrangThai());
            comboTrangThai.addActionListener(evt -> {
                String selectedItem = comboTrangThai.getSelectedItem().toString();
                int soNguoi = Integer.parseInt(txtSoNguoi.getText().trim());

                if ("Còn trống".equals(selectedItem) && soNguoi > 0) {
                    JOptionPane.showMessageDialog(this,
                            "Không thể chọn 'Còn trống' khi phòng này vẫn còn người!",
                            "Lỗi",
                            JOptionPane.ERROR_MESSAGE);
                    // Đặt lại trạng thái `ComboBox` về giá trị trước đó
                    comboTrangThai.setSelectedItem(hoGiaDinh.getTrangThai());
                }
            });

        }
    }
    
    public ModelHoGiaDinh getData() {
        String soPhong = txtSoPhong.getText().trim();
        if (txtSoNguoi.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số người không thể trống", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        if (txtSoXeMay.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số xe máy không thể trống!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        
        if (txtSoOto.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số ô tô không thể trống!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        
        try {
            // Ép kiểu tất cả các giá trị
            int soNguoi = Integer.parseInt(txtSoNguoi.getText().trim());
            int soXeMay = Integer.parseInt(txtSoXeMay.getText().trim());
            int soOto = Integer.parseInt(txtSoOto.getText().trim());
            double dienTich = Double.parseDouble(txtDienTich.getText().trim());
            String trangThai = comboTrangThai.getSelectedItem().toString();
            // Kiểm tra các điều kiện hợp lệ
            if (soNguoi <= 0 || soXeMay < 0 || soOto < 0) {
                throw new IllegalArgumentException("Tất cả các trường phải là số nguyên dương!");
            }
            if (dienTich <= 0) {
                throw new IllegalArgumentException("Diện tích phải là số thực dương!");
            }
               
            return new ModelHoGiaDinh(soPhong, dienTich, soNguoi, soXeMay, soOto, trangThai);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                "Vui lòng nhập đúng định dạng số:\n- Số người, Số xe máy và Số ô tô: số nguyên dương\n- Diện tích: số thực dương!", 
                "Cảnh báo",
                JOptionPane.WARNING_MESSAGE);
            return null;
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, 
                e.getMessage(), 
                "Cảnh báo", 
                JOptionPane.WARNING_MESSAGE);
            return null;
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtSoPhong = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtDienTich = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtSoXeMay = new javax.swing.JTextField();
        txtSoOto = new javax.swing.JTextField();
        txtSoNguoi = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        comboTrangThai = new javax.swing.JComboBox<>();

        jLabel1.setText("Số Phòng");

        txtSoPhong.setText("...............................");

        jLabel3.setText("Diện tích");

        txtDienTich.setText("...............................");

        jLabel2.setText("Số người");

        jLabel4.setText("Số xe máy");

        jLabel5.setText("Số Ô tô");

        txtSoNguoi.setText("///////////////////////");

        jLabel6.setText("Trạng thái");

        comboTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Còn trống", "Cho thuê", "Đã bán" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(62, 62, 62))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDienTich, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSoPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(comboTrangThai, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtSoNguoi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtSoOto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                                .addComponent(txtSoXeMay, javax.swing.GroupLayout.Alignment.LEADING)))))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtSoPhong))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtDienTich))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtSoNguoi))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtSoXeMay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSoOto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(comboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> comboTrangThai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel txtDienTich;
    private javax.swing.JLabel txtSoNguoi;
    private javax.swing.JTextField txtSoOto;
    private javax.swing.JLabel txtSoPhong;
    private javax.swing.JTextField txtSoXeMay;
    // End of variables declaration//GEN-END:variables
}
