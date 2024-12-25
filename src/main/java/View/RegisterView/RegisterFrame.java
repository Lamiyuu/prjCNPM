/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View.RegisterView;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.UUID;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author ADMIN
 */
public class RegisterFrame extends javax.swing.JFrame {

    /**
     * Creates new form RegisterFrame
     */
    public RegisterFrame() {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/icon.png")));
        datePicker1.setCloseAfterSelected(true);
        datePicker1.setEditor(jTextFieldNgaySinh);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        for (int i = 1; i < 10; i++) {
                    jComboBox2.addItem("1"+String.format("%02d", i));  
        }
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String selectedValue = (String) jComboBox1.getSelectedItem();  // Lấy giá trị từ jComboBox1

                // Xóa các mục cũ trong jComboBox2
                jComboBox2.removeAllItems();

                // Thêm các mục mới vào jComboBox2 tùy theo giá trị đã chọn từ jComboBox1
                for (int i = 1; i <= 10; i++) {  // Thay đổi từ 0 thành 1 để tạo các phòng từ 301 đến 310
                    jComboBox2.addItem(selectedValue + String.format("%02d", i));  // Tạo các phòng từ 301 đến 310
                }
            }
        });
    }
    public boolean isValidPassword(String password) {
    // Kiểm tra mật khẩu có độ dài tối thiểu 8 ký tự
    if (password.length() < 8) {
        return false;
    }
 
    // Biểu thức chính quy để kiểm tra mật khẩu có ít nhất một ký tự thường, một ký tự in hoa, một chữ số và một ký tự đặc biệt
    String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])[^\\s]{8,}$";
        // Kiểm tra mật khẩu với biểu thức chính quy
        return password.matches(regex);
    }
    
    public void validateNgaySinh() {
        try {
            // Lấy giá trị từ jTextFieldNgaySinh
            String ngaySinhText = jTextFieldNgaySinh.getText();

            // Định dạng ngày (chỉnh lại nếu cần khớp với định dạng datePicker1 sử dụng)
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            // Chuyển chuỗi thành LocalDate
            LocalDate ngaySinh = LocalDate.parse(ngaySinhText, formatter);

            // Lấy ngày hiện tại
            LocalDate ngayHienTai = LocalDate.now();

            // Kiểm tra nếu ngày sinh >= ngày hiện tại
            if (!ngaySinh.isBefore(ngayHienTai)) {
                // Hiển thị lỗi
                JOptionPane.showMessageDialog(this, "Ngày sinh phải nhỏ hơn ngày hiện tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else {
                // Ngày sinh hợp lệ
                System.out.println("Ngày sinh hợp lệ: " + ngaySinh);
            }
        } catch (DateTimeParseException e) {

            JOptionPane.showMessageDialog(this, "Ngày sinh không hợp lệ. Vui lòng nhập đúng định dạng dd/MM/yyyy!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonHide = new javax.swing.JButton();
        jButtonHide1 = new javax.swing.JButton();
        gradientText1 = new View.RegisterView.GradientText();
        datePicker1 = new raven.datetime.component.date.DatePicker();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        gradientText2 = new View.RegisterView.GradientText();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldHoTen = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldEmail = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldTenDangNhap = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButtonDangKi = new javax.swing.JButton();
        jButtonVeDangNhap = new javax.swing.JButton();
        jTextFieldNgaySinh = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jTextFieldCCCD = new javax.swing.JTextField();
        jTextFieldMatKhauMoi = new javax.swing.JPasswordField();
        jTextFieldXacNhanMatKhau = new javax.swing.JPasswordField();

        jButtonHide.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jButtonHide.setForeground(new java.awt.Color(255, 255, 255));
        jButtonHide.setText("–");
        jButtonHide.setContentAreaFilled(false);
        jButtonHide.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonHideMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonHideMouseExited(evt);
            }
        });
        jButtonHide.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHideActionPerformed(evt);
            }
        });

        jButtonHide1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jButtonHide1.setForeground(new java.awt.Color(255, 255, 255));
        jButtonHide1.setText("–");
        jButtonHide1.setContentAreaFilled(false);
        jButtonHide1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonHide1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonHide1MouseExited(evt);
            }
        });
        jButtonHide1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHide1ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Đăng kí");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(0, 153, 153));
        jPanel1.setPreferredSize(new java.awt.Dimension(440, 409));

        gradientText2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout gradientText2Layout = new javax.swing.GroupLayout(gradientText2);
        gradientText2.setLayout(gradientText2Layout);
        gradientText2Layout.setHorizontalGroup(
            gradientText2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 165, Short.MAX_VALUE)
        );
        gradientText2Layout.setVerticalGroup(
            gradientText2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 153));
        jLabel1.setText("Số CCCD");

        jTextFieldHoTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldHoTenActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 153));
        jLabel2.setText("Họ tên");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 153, 153));
        jLabel3.setText("Ngày sinh");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 153, 153));
        jLabel5.setText("Số điện thoại");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 153, 153));
        jLabel6.setText("Số phòng");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 153, 153));
        jLabel7.setText("Tên đăng nhập");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 153, 153));
        jLabel8.setText("Mật khẩu mới");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 153, 153));
        jLabel9.setText("Xác nhận mật khẩu");

        jButtonDangKi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonDangKi.setForeground(new java.awt.Color(0, 153, 153));
        jButtonDangKi.setText("Gửi đăng kí ");
        jButtonDangKi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDangKiActionPerformed(evt);
            }
        });

        jButtonVeDangNhap.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonVeDangNhap.setForeground(new java.awt.Color(0, 153, 153));
        jButtonVeDangNhap.setText("Về đăng nhập");
        jButtonVeDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVeDangNhapActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 153, 153));
        jLabel10.setText("Giới tính");

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("Nam");

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Nữ");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 153, 153));
        jLabel11.setText("Số tầng");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9" }));

        jTextFieldMatKhauMoi.setPreferredSize(new java.awt.Dimension(64, 30));

        jTextFieldXacNhanMatKhau.setPreferredSize(new java.awt.Dimension(64, 30));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(jButtonVeDangNhap)
                            .addComponent(jLabel8)
                            .addComponent(jTextFieldTenDangNhap, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                            .addComponent(jTextFieldHoTen)
                            .addComponent(jTextFieldCCCD)
                            .addComponent(jTextFieldEmail)
                            .addComponent(jTextFieldMatKhauMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel9)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jRadioButton1)
                                    .addGap(18, 18, 18)
                                    .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextFieldXacNhanMatKhau, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(1, 1, 1)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jComboBox2, 0, 167, Short.MAX_VALUE)
                                            .addComponent(jTextFieldNgaySinh, javax.swing.GroupLayout.Alignment.TRAILING)))))
                            .addComponent(jButtonDangKi)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(gradientText2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(106, 106, 106)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(gradientText2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldTenDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldXacNhanMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldMatKhauMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonDangKi)
                    .addComponent(jButtonVeDangNhap))
                .addContainerGap(74, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonHideMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonHideMouseEntered
        jButtonHide.setOpaque(true);
        jButtonHide.setBackground(Color.GRAY);
    }//GEN-LAST:event_jButtonHideMouseEntered

    private void jButtonHideMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonHideMouseExited
        jButtonHide.setOpaque(false);
    }//GEN-LAST:event_jButtonHideMouseExited

    private void jButtonHideActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHideActionPerformed
        this.setExtendedState(JFrame.ICONIFIED);
    }//GEN-LAST:event_jButtonHideActionPerformed

    private void jButtonHide1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonHide1MouseEntered
        jButtonHide.setOpaque(true);
        jButtonHide.setBackground(Color.GRAY);
    }//GEN-LAST:event_jButtonHide1MouseEntered

    private void jButtonHide1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonHide1MouseExited
        jButtonHide.setOpaque(false);
    }//GEN-LAST:event_jButtonHide1MouseExited

    private void jButtonHide1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHide1ActionPerformed
        this.setExtendedState(JFrame.ICONIFIED);
    }//GEN-LAST:event_jButtonHide1ActionPerformed

    private void jButtonVeDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVeDangNhapActionPerformed

        this.dispose();
    }//GEN-LAST:event_jButtonVeDangNhapActionPerformed

    private void jButtonDangKiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDangKiActionPerformed
        String ID, fullName, loginName, password, confirmPassword, cccd, phone, roomPosition, query;
        Date dob;
        boolean gender;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = Database.DatabaseConnection.getConnection();

            // Lấy dữ liệu từ người dùng
            fullName = jTextFieldHoTen.getText();
            loginName = jTextFieldTenDangNhap.getText();
            password = new String(jTextFieldMatKhauMoi.getPassword());
            confirmPassword = new String(jTextFieldXacNhanMatKhau.getPassword());
            cccd = jTextFieldCCCD.getText();
            phone = jTextFieldEmail.getText();
            roomPosition = (String) jComboBox2.getSelectedItem();
            gender = true;
            if(jRadioButton2.isSelected()){
                gender = false;
            }

            // Lấy hoặc xử lý ngày sinh
            dob = datePicker1.isDateSelected() 
                ? Date.valueOf(datePicker1.getSelectedDate().toString()) 
                : Date.valueOf("1000-01-01");


            // Kiểm tra lỗi
            if (!datePicker1.isDateSelected()){
                JOptionPane.showMessageDialog(this, "Hãy chọn ngày sinh", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // So sánh ngày sinh với ngày hiện tại
            LocalDate ngaySinh = dob.toLocalDate();
            LocalDate ngayHienTai = LocalDate.now();
            if (!ngaySinh.isBefore(ngayHienTai)) {
                JOptionPane.showMessageDialog(this, "Ngày sinh phải nhỏ hơn ngày hiện tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (fullName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Họ và tên là bắt buộc", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (loginName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tên đăng nhập là bắt buộc", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Mật khẩu là bắt buộc", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(this, "Mật khẩu xác nhận không khớp", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!isValidPassword(password))
            {
                JOptionPane.showMessageDialog(this, "Mật khẩu cần chứa ít nhất 8 kí tự, bao gồm ít nhất 1 ký tự thường, 1 ký tự in hoa, 1 ký tự số, 1 ký tự đặc biệt", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (cccd.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Số CCCD là bắt buộc", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (cccd.length() != 12 || !cccd.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "Số CCCD chưa hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (phone.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Số điện thoại là bắt buộc", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (phone.length() != 10 || !phone.matches("\\d+")) { 
              JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
               return;
            }
            if (dob == null) {
                JOptionPane.showMessageDialog(this, "Ngày sinh là bắt buộc", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (roomPosition.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vị trí phòng là bắt buộc", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Kiểm tra xem tên đăng nhập đã tồn tại hay chưa
            String checkQuery = "SELECT COUNT(*) FROM tai_khoan WHERE tenDangNhap = ?";
            PreparedStatement checkStmt = con.prepareStatement(checkQuery);
            checkStmt.setString(1, loginName);
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            if (rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(this, "Tên đăng nhập đã tồn tại, vui lòng chọn tên khác.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Câu lệnh SQL để thêm người dùng mới
            query = "INSERT INTO tai_khoan(hoTen, ngaySinh, SĐT, CCCD, soPhong, tenDangNhap, matKhau, ghiChu, gioiTinh, ID) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, fullName);
            pstmt.setDate(2, dob); // Sử dụng kiểu Date cho trường ngày sinh
            pstmt.setString(3, phone);
            pstmt.setString(4, cccd);
            pstmt.setString(5, roomPosition);
            pstmt.setString(6, loginName);
            pstmt.setString(7, password);
            pstmt.setString(8, "Chưa duyệt");
            pstmt.setBoolean(9, gender);
            pstmt.setString(10, UUID.randomUUID().toString());
            pstmt.executeUpdate();
            

            // Thông báo thành công
            JOptionPane.showMessageDialog(this, "Tài khoản đã được gửi đăng kí tạo thành công");
            this.dispose();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi cơ sở dữ liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy driver JDBC", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonDangKiActionPerformed

    private void jTextFieldHoTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldHoTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldHoTenActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RegisterFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegisterFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegisterFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegisterFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("sample.themes");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        FlatMacLightLaf.setup();
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegisterFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private raven.datetime.component.date.DatePicker datePicker1;
    private View.RegisterView.GradientText gradientText1;
    private View.RegisterView.GradientText gradientText2;
    private javax.swing.JButton jButtonDangKi;
    private javax.swing.JButton jButtonHide;
    private javax.swing.JButton jButtonHide1;
    private javax.swing.JButton jButtonVeDangNhap;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JTextField jTextFieldCCCD;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldHoTen;
    private javax.swing.JPasswordField jTextFieldMatKhauMoi;
    private javax.swing.JFormattedTextField jTextFieldNgaySinh;
    private javax.swing.JTextField jTextFieldTenDangNhap;
    private javax.swing.JPasswordField jTextFieldXacNhanMatKhau;
    // End of variables declaration//GEN-END:variables
}
