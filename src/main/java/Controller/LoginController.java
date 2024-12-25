/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.HomeView.Home.Home;
import View.LoginView.ForgotPasswordFrame;
import View.LoginView.LoginFrame;
import View.RegisterView.RegisterFrame;
import Model.ModelTaiKhoan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class LoginController {
    private final Logger logger = Logger.getLogger(LoginFrame.class.getName());
    private String name;
    private LoginFrame view;

    public LoginController(LoginFrame view) {
        this.view = view;
        view.addControllerListener(this);
    }
    
    public void dangKiActionPerformed(java.awt.event.ActionEvent evt) {                                              
        RegisterFrame SignUpFrame = new RegisterFrame();
        SignUpFrame.setVisible(true);
        SignUpFrame.pack();
    }  
    
    public void dangNhapActionPerformed(java.awt.event.ActionEvent evt) {                                                
        String loginName = view.getjTextFieldTenTaiKhoan().getText().trim();
        String password = view.getjTextFieldMatKhau().getText().trim();

        if (loginName.isEmpty()) {
            JOptionPane.showMessageDialog(new JFrame(), "Bạn chưa nhập tên đăng nhập", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(new JFrame(), "Bạn chưa nhập mật khẩu", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection con = Database.DatabaseConnection.getConnection()) {
            if (con == null) {
                throw new Exception("Không thể kết nối cơ sở dữ liệu");
            }

            String query = "SELECT * FROM tai_khoan WHERE tenDangNhap = ? AND ghiChu = 'Đã duyệt'";
            try (PreparedStatement stmt = con.prepareStatement(query)) {
                stmt.setString(1, loginName);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        String passDb = rs.getString("matKhau");

                        if (password.equals(passDb)) {
                            ModelTaiKhoan taiKhoan = new ModelTaiKhoan(
                                rs.getString("hoTen"),
                                rs.getDate("ngaySinh"),
                                rs.getString("SĐT"),
                                rs.getString("CCCD"),
                                rs.getString("soPhong"),
                                rs.getString("tenDangNhap"),
                                rs.getString("ghiChu"),
                                rs.getBoolean("gioiTinh"),
                                rs.getString("ID"),
                                rs.getBoolean("admin")
                            );
                            Home.setTaiKhoan(taiKhoan);
                            Home homeFrame = new Home();
                            homeFrame.setVisible(true);
                            homeFrame.pack();
                            homeFrame.setLocationRelativeTo(null);
                            view.dispose();
                            return;
                        }
                    }

                    JOptionPane.showMessageDialog(new JFrame(), "Tên đăng nhập hoặc mật khẩu không chính xác", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Đã xảy ra lỗi trong quá trình truy vấn dữ liệu", e);
            JOptionPane.showMessageDialog(new JFrame(), "Đã xảy ra lỗi trong quá trình đăng nhập", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Đã xảy ra lỗi trong quá trình đăng nhập", e);
            JOptionPane.showMessageDialog(new JFrame(), "Lỗi khi đăng nhập", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } finally {
            view.getjTextFieldMatKhau().setText("");
        }

    }    
    public void quenMatKhauActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        ForgotPasswordFrame fpf = new ForgotPasswordFrame();
        fpf.setVisible(true);
        fpf.pack();
    } 
    public void quenMatKhauMouseEntered(java.awt.event.MouseEvent evt) {                                                
        view.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR)); 
    } 
    public void quenMatKhauMouseExited(java.awt.event.MouseEvent evt) {                                               
        view.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR)); 
    }
}
