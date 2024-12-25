/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View.KhieuNaiView;

import Controller.KhieuNaiController;
import Database.Service;
import Model.ButtonEditorKhieuNai;
import Model.ButtonRenderer;
import Model.CheckBoxTableHeader;
import Model.ModelKhieuNai;
import Model.ModelTaiKhoan;
import Model.TableHeaderAlignment;
import Model.Pagination.EventPagination;
import Model.Pagination.Pagination;
import Model.Pagination.PaginationItemRenderStyle1;
import static com.bw.jtools.svg.Type.g;
import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import raven.popup.DefaultOption;
import raven.popup.GlassPanePopup;
import raven.popup.component.SimplePopupBorder;
import raven.toast.Notifications;

/**
 *
 * @author ADMIN
 */
public class KhieuNaiPanel extends javax.swing.JPanel {
    private Service service = new Service();
    private ModelTaiKhoan taiKhoan;
    private int currentPage = -1;
    private KhieuNaiController controller;
    /**
     * Creates new form KhieuNaiPanel
     */
    public KhieuNaiPanel(ModelTaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
        initComponents();
        init();
        controller = new KhieuNaiController(this, taiKhoan);
    }
    
    
    private void init(){
        this.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:25;"
                + "background:$Table.background");
        
        table.getTableHeader().putClientProperty(FlatClientProperties.STYLE, ""
                + "height: 30;"
                + "hoverBackground:null;"
                + "pressedBackground:null;"
                + "separatorColor:$TableHeader.background;"
                + "font:bold;");
                
        table.putClientProperty(FlatClientProperties.STYLE,"" 
                + "rowHeight:30;"
                + "showHorizontalLines:true;"
                + "intercellSpacing: 0, 1;"
                + "cellFocusColor: $TableHeader.hoverBackground;"
                + "selectionBackground: $TableHeader.hoverBackground;" // Sửa lại chính tả
                + "selectionForeground: $Table.foreground;");
         scroll.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE,""
                + "trackArc:999;"
                + "trackInsets: 3,3,3,3;"
                + "thumbInsets: 3,3,3,3;"
                + "background: $Table.background;");
          
        lbTitle.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:bold +5;");
        table.getColumnModel().getColumn(0).setHeaderRenderer(new CheckBoxTableHeader(table, 0));
        table.getTableHeader().setDefaultRenderer(new TableHeaderAlignment(table));
        table.getTableHeader().setReorderingAllowed(false);
        table.getColumnModel().getColumn(8).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(8).setCellEditor(new ButtonEditorKhieuNai(new JCheckBox(),table, this, taiKhoan));
        new TableHeaderAlignment(table);
        taoMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tao_moi.png"))); // NOI18N
        taoMoi.setFocusPainted(false);  // Loại bỏ hiệu ứng khi nút được chọn
        taoMoi.setContentAreaFilled(false);  // Loại bỏ nền của nút
        taoMoi.setBorderPainted(false);  // Loại bỏ viền của nút

        // Thêm hiệu ứng mượt mà cho nút
        taoMoi.setRolloverEnabled(false);  // Kích hoạt hiệu ứng khi di chuột qua nút
        taoMoi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

    }
    
    @Override
    public void paintComponent(Graphics grphcs){
    Graphics2D grphcs2d = (Graphics2D) grphcs;
    // Tăng cường chất lượng rendering
    grphcs2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    grphcs2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    grphcs2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

    // Tạo màu gradient
    GradientPaint grd = new GradientPaint(0, 0, Color.WHITE, 0, getHeight(), Color.WHITE);
    grphcs2d.setPaint(grd);

    // Làm mềm cạnh ô vuông
    grphcs2d.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);

    super.paintComponent(grphcs);
}

    
    
    public void addControllerListener(KhieuNaiController controller) {
        taoMoi.addActionListener(controller::taoMoiActionPerformed);
        comboLoai.addActionListener(controller::comboActionPerformed);
    }
    public Pagination getPagination1() {
        return pagination1;
    }

    public JTable getTable() {
        return table;
    }

    public KhieuNaiController getController() {
        return controller;
    }

    public JComboBox<String> getComboLoai() {
        return comboLoai;
    }
    
    
    
        
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scroll = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        taoMoi = new javax.swing.JButton();
        pagination1 = new Model.Pagination.Pagination();
        lbTitle = new javax.swing.JLabel();
        comboLoai = new javax.swing.JComboBox<>();
        khieuNaiCard2 = new View.KhieuNaiView.KhieuNaiCard();

        setBackground(new java.awt.Color(255, 255, 255));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Check", "STT", "this", "Phân loại", "Tiêu đề", "Người gửi", "Ngày gửi", "Ngày duyệt", "Xem"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.getTableHeader().setReorderingAllowed(false);
        scroll.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setMinWidth(35);
            table.getColumnModel().getColumn(0).setPreferredWidth(35);
            table.getColumnModel().getColumn(0).setMaxWidth(35);
            table.getColumnModel().getColumn(1).setMinWidth(45);
            table.getColumnModel().getColumn(1).setPreferredWidth(45);
            table.getColumnModel().getColumn(1).setMaxWidth(45);
            table.getColumnModel().getColumn(2).setMinWidth(0);
            table.getColumnModel().getColumn(2).setPreferredWidth(0);
            table.getColumnModel().getColumn(2).setMaxWidth(0);
            table.getColumnModel().getColumn(3).setMinWidth(150);
            table.getColumnModel().getColumn(3).setPreferredWidth(150);
            table.getColumnModel().getColumn(3).setMaxWidth(150);
            table.getColumnModel().getColumn(4).setMinWidth(350);
            table.getColumnModel().getColumn(4).setPreferredWidth(350);
            table.getColumnModel().getColumn(4).setMaxWidth(350);
            table.getColumnModel().getColumn(5).setMinWidth(200);
            table.getColumnModel().getColumn(5).setPreferredWidth(200);
            table.getColumnModel().getColumn(5).setMaxWidth(200);
            table.getColumnModel().getColumn(6).setMinWidth(100);
            table.getColumnModel().getColumn(6).setPreferredWidth(100);
            table.getColumnModel().getColumn(6).setMaxWidth(100);
            table.getColumnModel().getColumn(7).setMinWidth(100);
            table.getColumnModel().getColumn(7).setPreferredWidth(100);
            table.getColumnModel().getColumn(7).setMaxWidth(100);
        }

        taoMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tao_moi.png"))); // NOI18N

        lbTitle.setText("Quản lý khiếu nại");

        comboLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chờ duyệt", "Đã duyệt", "Từ chối" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbTitle)
                            .addComponent(comboLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 1066, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(taoMoi)
                                .addGap(18, 18, 18)
                                .addComponent(khieuNaiCard2, javax.swing.GroupLayout.PREFERRED_SIZE, 994, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(459, 459, 459)
                        .addComponent(pagination1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(866, 866, 866))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lbTitle)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(taoMoi)
                    .addComponent(khieuNaiCard2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(comboLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pagination1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(424, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboLoai;
    private View.KhieuNaiView.KhieuNaiCard khieuNaiCard2;
    private javax.swing.JLabel lbTitle;
    private Model.Pagination.Pagination pagination1;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTable table;
    private javax.swing.JButton taoMoi;
    // End of variables declaration//GEN-END:variables
}
