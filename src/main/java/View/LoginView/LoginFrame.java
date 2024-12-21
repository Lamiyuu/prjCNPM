package View.LoginView;

import Controller.LoginController;
import View.RegisterView.RegisterFrame;
import View.HomeView.Home.Home;
import Model.ModelTaiKhoan;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class LoginFrame extends javax.swing.JFrame {
    
    
    private final Logger logger = Logger.getLogger(LoginFrame.class.getName());
    private String name;
    public LoginFrame() {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/icon.png")));
        setLocationRelativeTo(null);
        setBackground(new Color(255,255,255,255));
        LoginController controller = new LoginController(this);
    }
    
    public void addControllerListener(LoginController controller){
        dangNhap.addActionListener(controller::dangNhapActionPerformed);
        dangKi.addActionListener(controller::dangKiActionPerformed);
        quenMk.addActionListener(controller::quenMatKhauActionPerformed);
        quenMk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                controller.quenMatKhauMouseEntered(evt); // Gọi hàm thông qua controller
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                controller.quenMatKhauMouseExited(evt); // Giữ nguyên hàm này
            }
        });

    }

    public JPasswordField getjTextFieldMatKhau() {
        return jTextFieldMatKhau;
    }

    public JTextField getjTextFieldTenTaiKhoan() {
        return jTextFieldTenTaiKhoan;
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gradientPanelOuter1 = new View.LoginView.GradientPanelOuter();
        gradientPanelInner1 = new View.LoginView.GradientPanelInner();
        dangNhap = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldTenTaiKhoan = new javax.swing.JTextField();
        jTextFieldMatKhau = new javax.swing.JPasswordField();
        quenMk = new javax.swing.JButton();
        logoPanel1 = new View.LoginView.LogoPanel();
        jLabel1 = new javax.swing.JLabel();
        dangKi = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        dangNhap.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        dangNhap.setForeground(new java.awt.Color(0, 153, 153));
        dangNhap.setText("Đăng nhập");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tên tài khoản");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Mật khẩu");

        jTextFieldTenTaiKhoan.setForeground(new java.awt.Color(0, 102, 102));
        jTextFieldTenTaiKhoan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jTextFieldMatKhau.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        quenMk.setForeground(new java.awt.Color(255, 255, 255));
        quenMk.setText("Quên mật khẩu");
        quenMk.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        quenMk.setContentAreaFilled(false);

        javax.swing.GroupLayout gradientPanelInner1Layout = new javax.swing.GroupLayout(gradientPanelInner1);
        gradientPanelInner1.setLayout(gradientPanelInner1Layout);
        gradientPanelInner1Layout.setHorizontalGroup(
            gradientPanelInner1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gradientPanelInner1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(gradientPanelInner1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextFieldTenTaiKhoan, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, gradientPanelInner1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(quenMk, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldMatKhau, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
            .addGroup(gradientPanelInner1Layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(dangNhap)
                .addGap(98, 98, 98))
        );
        gradientPanelInner1Layout.setVerticalGroup(
            gradientPanelInner1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gradientPanelInner1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldTenTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(quenMk)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dangNhap)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout logoPanel1Layout = new javax.swing.GroupLayout(logoPanel1);
        logoPanel1.setLayout(logoPanel1Layout);
        logoPanel1Layout.setHorizontalGroup(
            logoPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );
        logoPanel1Layout.setVerticalGroup(
            logoPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 69, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Quản lý chung cư");

        dangKi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        dangKi.setForeground(new java.awt.Color(0, 153, 153));
        dangKi.setText("Đăng kí");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Chưa có tài khoản?");

        javax.swing.GroupLayout gradientPanelOuter1Layout = new javax.swing.GroupLayout(gradientPanelOuter1);
        gradientPanelOuter1.setLayout(gradientPanelOuter1Layout);
        gradientPanelOuter1Layout.setHorizontalGroup(
            gradientPanelOuter1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gradientPanelOuter1Layout.createSequentialGroup()
                .addGap(0, 72, Short.MAX_VALUE)
                .addGroup(gradientPanelOuter1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gradientPanelOuter1Layout.createSequentialGroup()
                        .addComponent(gradientPanelInner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gradientPanelOuter1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dangKi)
                        .addGap(133, 133, 133))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gradientPanelOuter1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(gradientPanelOuter1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gradientPanelOuter1Layout.createSequentialGroup()
                        .addComponent(logoPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(186, 186, 186))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gradientPanelOuter1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(118, 118, 118))))
        );
        gradientPanelOuter1Layout.setVerticalGroup(
            gradientPanelOuter1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gradientPanelOuter1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(logoPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(gradientPanelInner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(gradientPanelOuter1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dangKi)
                    .addComponent(jLabel4))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(gradientPanelOuter1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(gradientPanelOuter1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
                                           
    
    
    public static void main(String args[]) {
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("sample.themes");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        FlatMacLightLaf.setup();
        java.awt.EventQueue.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton dangKi;
    private javax.swing.JButton dangNhap;
    private View.LoginView.GradientPanelInner gradientPanelInner1;
    private View.LoginView.GradientPanelOuter gradientPanelOuter1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField jTextFieldMatKhau;
    private javax.swing.JTextField jTextFieldTenTaiKhoan;
    private View.LoginView.LogoPanel logoPanel1;
    private javax.swing.JButton quenMk;
    // End of variables declaration//GEN-END:variables
}
