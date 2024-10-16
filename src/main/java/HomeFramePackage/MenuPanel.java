
package HomeFramePackage;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class MenuPanel extends javax.swing.JPanel {

    public MenuPanel() {
        initComponents();
        setOpaque(false);
        init();
    }
    
    private void init(){
        menuList1.addItem(new MenuModel("TrangChu","Trang chủ",MenuModel.MenuType.MENU));
        menuList1.addItem(new MenuModel("TaiKhoan","Tài khoản",MenuModel.MenuType.MENU));
        menuList1.addItem(new MenuModel("KhoanThu","Khoản thu",MenuModel.MenuType.MENU));
        menuList1.addItem(new MenuModel("ThuPhi","Thu phí",MenuModel.MenuType.MENU));
        menuList1.addItem(new MenuModel("NhanKhau","Nhân khẩu",MenuModel.MenuType.MENU));
        menuList1.addItem(new MenuModel("KhieuNai","Khiếu nại",MenuModel.MenuType.MENU));
        menuList1.addItem(new MenuModel("DangXuat","Đăng xuất",MenuModel.MenuType.MENU));
        
    }

    @Override
    public void paintComponent(Graphics grphcs){
        Graphics2D grphcs2d = (Graphics2D) grphcs;
        //Tạo màu gradient
        grphcs2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint grd = new GradientPaint(0, 0, Color.decode("#85D8CE"), 0 ,getWidth(), Color.decode("#085078"));
        grphcs2d.setPaint(grd);
        //Làm mềm cạnh ô vuông
        grphcs2d.fillRoundRect(0, 0, getWidth(), getHeight(), 0, 0);
        super.paintComponent(grphcs);
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        menuList1 = new HomeFramePackage.MenuList<>();

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon.png"))); // NOI18N
        jLabel3.setText(" Quản lý chung cư");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuList1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(menuList1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel3;
    private HomeFramePackage.MenuList<String> menuList1;
    // End of variables declaration//GEN-END:variables
}
