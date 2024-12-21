
package View.HomeView.Home;

import View.HomeView.Home.BlankPanel;
import View.HomeView.Menu.EventMenuSelected;
import View.KhieuNaiView.KhieuNaiPanel;
import View.NhanKhauView.NhanKhauPanel;
import View.KhoanThuView.KhoanThuPanel;
import View.LoginView.LoginFrame;
import Model.ModelTaiKhoan;
import View.NhanKhauView.HoGiaDinhPanel;
import View.TaiKhoanView.TaiKhoanPanel;
import View.ThuPhiView.ThongKeThuPhiPanel;
import View.ThuPhiView.ThuPhiPanel;
import View.TuThienView.TuThienPanel;
import java.awt.BorderLayout;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.UIManager;
import raven.popup.GlassPanePopup;
public class Home extends javax.swing.JFrame {
    
    private static ModelTaiKhoan taiKhoan;

    public static ModelTaiKhoan getTaiKhoan() {
        return taiKhoan;
    }

    public static void setTaiKhoan(ModelTaiKhoan taikhoan) {
        Home.taiKhoan = taikhoan;
        
    }
    
    public Home() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/icon.png")));
        GlassPanePopup.install(this);
        initComponents();
        
        menuPanel2.addEventMenuSelected(new EventMenuSelected(){
           
            @Override
            public void selected(int index) {
                
                switch (index) {
                    case 0 -> setForm(new HomePanel());
                    case 1 -> setForm(new TaiKhoanPanel(taiKhoan));
                    case 2 -> setForm(new KhoanThuPanel(taiKhoan));
                    case 3 -> setForm(new ThuPhiPanel(taiKhoan));
                    case 4 -> setForm(new ThongKeThuPhiPanel());
                    case 5 -> setForm(new TuThienPanel(taiKhoan));
                    case 6 -> setForm(new HoGiaDinhPanel(taiKhoan));
                    case 7 -> setForm(new KhieuNaiPanel(taiKhoan));
                    case 8 -> {
                        int confirm = javax.swing.JOptionPane.showConfirmDialog(
                            Home.this,
                            "Bạn có chắc chắn muốn đăng xuất không?",
                            "Xác nhận đăng xuất",
                            javax.swing.JOptionPane.YES_NO_OPTION,
                            javax.swing.JOptionPane.QUESTION_MESSAGE
                        );
                        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                            LoginFrame reg = new LoginFrame();
                            reg.setVisible(true);
                            reg.pack();
                            Home.this.dispose();
                        }
                    }
                    default -> setForm(new BlankPanel());
                }
            }
            
        });
        setForm(new HomePanel());
        menuPanel2.setName(taiKhoan.getHoTen());
    }
    
    
    public void setForm(JComponent component){
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("sample.themes");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 15));
        FlatMacLightLaf.setup();
        mainPanel.removeAll();
        mainPanel.add(component, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(mainPanel);
        SwingUtilities.invokeLater(() -> {
            mainPanel.revalidate(); // Xác thực lại layout
            mainPanel.repaint(); // Vẽ lại mainPanel
        });
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuPanel2 = new View.HomeView.Menu.MenuPanel();
        mainPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(menuPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 578, Short.MAX_VALUE)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new Home().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel mainPanel;
    private View.HomeView.Menu.MenuPanel menuPanel2;
    // End of variables declaration//GEN-END:variables
}
