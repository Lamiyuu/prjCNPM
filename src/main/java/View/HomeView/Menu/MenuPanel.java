
package View.HomeView.Menu;

import View.HomeView.Menu.EventMenuSelected;
import View.HomeView.Menu.MenuModel;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Path2D;
import java.awt.geom.RoundRectangle2D;

public class MenuPanel extends javax.swing.JPanel {

    private EventMenuSelected event;
    
    public void setName(String name){
        labelName.setText(name);
    }
    public void addEventMenuSelected(EventMenuSelected event){
        this.event = event;
        menuList1.addEventMenuSelected(event);
    }
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
        menuList1.addItem(new MenuModel("ThongKe","Thống kê phí",MenuModel.MenuType.MENU));
        menuList1.addItem(new MenuModel("TuThien","Từ thiện",MenuModel.MenuType.MENU));
        menuList1.addItem(new MenuModel("NhanKhau","Nhân khẩu",MenuModel.MenuType.MENU));
        menuList1.addItem(new MenuModel("KhieuNai","Khiếu nại",MenuModel.MenuType.MENU));
        menuList1.addItem(new MenuModel("DangXuat","Đăng xuất",MenuModel.MenuType.MENU));
        menuList1.setFixedCellHeight(50);
        
    }

    @Override
    public void paintComponent(Graphics grphcs){
        super.paintComponent(grphcs);
        
        // Chuyển đổi Graphics sang Graphics2D
        Graphics2D grphcs2d = (Graphics2D) grphcs;
        
        // Tạo màu gradient
        grphcs2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        grphcs2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        GradientPaint grd = new GradientPaint(0, 0, Color.decode("#007991"), 0, 1024, Color.decode("#78ffd6"));
        grphcs2d.setPaint(grd);

        // Tạo hình chữ nhật bo tròn chỉ góc trên bên phải
        int width = getWidth();
        int height = getHeight();
        int cornerRadius = 40; // Bán kính bo tròn góc

        // Vẽ đường viền bằng Path2D
        Path2D path = new Path2D.Double();
        
        // Vẽ phần còn lại của hình chữ nhật, chỉ bo tròn góc trên bên phải
        path.moveTo(0, 0); // Bắt đầu từ góc trên bên trái
        path.lineTo(width - cornerRadius, 0); // Vẽ đường ngang đến góc trên bên phải
        path.quadTo(width, 0, width, cornerRadius); // Bo tròn góc trên bên phải
        path.lineTo(width, height); // Vẽ đường dọc xuống góc dưới bên phải
        path.lineTo(0, height); // Vẽ đường ngang xuống góc dưới bên trái
        path.closePath(); // Đóng lại đường viền

        // Vẽ hình theo path đã tạo
        grphcs2d.fill(path); // Điền màu cho hình
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuList1 = new View.HomeView.Menu.MenuList<>();
        labelName = new javax.swing.JLabel();
        userImage = new javax.swing.JLabel();

        labelName.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        labelName.setForeground(new java.awt.Color(255, 255, 255));
        labelName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelName.setText("Nguyễn Hoàng Xuân Sơn");
        labelName.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        userImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        userImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/user.png"))); // NOI18N
        userImage.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuList1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(labelName, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(userImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(userImage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelName, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(menuList1, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(132, 132, 132))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel labelName;
    private View.HomeView.Menu.MenuList<String> menuList1;
    private javax.swing.JLabel userImage;
    // End of variables declaration//GEN-END:variables
}
