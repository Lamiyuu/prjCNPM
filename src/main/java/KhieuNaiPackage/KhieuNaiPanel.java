/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package KhieuNaiPackage;

import Database.Service;
import Model.ButtonEditorKhieuNai;
import Model.ButtonRenderer;
import Model.CheckBoxTableHeader;
import Model.ModelKhieuNai;
import Model.ModelTaiKhoan;
import Model.TableHeaderAlignment;
import PhanTrangPackage.EventPagination;
import PhanTrangPackage.Style.PaginationItemRenderStyle1;
import com.formdev.flatlaf.FlatClientProperties;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JCheckBox;
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
    /**
     * Creates new form KhieuNaiPanel
     */
    public KhieuNaiPanel(ModelTaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
        initComponents();
        init();
        initPagination();
        loadData(1);
    }
    private void init(){
        panel.putClientProperty(FlatClientProperties.STYLE, ""
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
        table.getColumnModel().getColumn(8).setCellEditor(new ButtonEditorKhieuNai(new JCheckBox(),table, this));
        new TableHeaderAlignment(table);
        
    }
    
    private void initPagination() {
        // Sử dụng pagination1 thay vì pagination
        pagination1.setPaginationItemRender(new PaginationItemRenderStyle1());

        // Lắng nghe sự kiện thay đổi trang
        pagination1.addEventPagination(new EventPagination() {
            public void pageChanged(int page) {
                loadData(page); // Tải dữ liệu khi trang thay đổi
            }
        });

        // Tính toán số trang
        int totalCount = service.getTotalCount("tai_khoan");  // Lấy tổng số bản ghi từ cơ sở dữ liệu
        int recordsPerPage = 10;  // Số bản ghi trên mỗi trang
        int totalPages = (int) Math.ceil((double) totalCount / recordsPerPage);  // Tổng số trang

        pagination1.setPagegination(1, totalPages);  // Cập nhật trang hiện tại và tổng số trang

        // Thêm phân trang vào giao diện
        panel.add(pagination1);
        panel.revalidate();
        panel.repaint();
    }
    
    private void loadData(int page) {
        currentPage = page;
        try {
            // Lấy model của bảng
            DefaultTableModel model = (DefaultTableModel) table.getModel();

            // Nếu bảng đang chỉnh sửa, dừng chỉnh sửa ô
            if (table.isEditing()) {
                table.getCellEditor().stopCellEditing();
            }

            // Xóa hết các dòng hiện tại trong bảng
            model.setRowCount(0);

            // Tính số bản ghi mỗi trang
            int recordsPerPage = 20;
            int offset = (page - 1) * recordsPerPage;  // Tính offset dựa trên trang

            // Lấy dữ liệu của trang hiện tại
            List<ModelKhieuNai> list = service.getPage(ModelKhieuNai.class, offset, recordsPerPage);

            // Thêm các dòng vào bảng
            for (ModelKhieuNai d : list) {
                model.addRow(d.toTableRow(table.getRowCount() + 1));  // Thêm dòng vào bảng
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<ModelKhieuNai> getSelectedData() {
        List<ModelKhieuNai> list = new ArrayList<>();

        for (int i = 0; i < table.getRowCount(); i++) {
            if ((boolean) table.getValueAt(i, 0)) {  // Kiểm tra trạng thái checkbox ở cột đầu tiên
                Object data = table.getValueAt(i, 2);  // Lấy giá trị ở cột thứ 1

                // Kiểm tra kiểu dữ liệu và ép kiểu nếu đúng
                if (data instanceof ModelKhieuNai) {
                    ModelKhieuNai nhanKhau = (ModelKhieuNai) data;
                    list.add(nhanKhau);
                } else {
                    // Xử lý lỗi nếu không phải kiểu ModelTaiKhoan
                    System.out.println("Dữ liệu không phải kiểu ModelTaiKhoan tại dòng " + i);
                }
            }
        }

        return list;
    }
    
    public void reload(){
        loadData(currentPage); // Thêm để khi xóa toàn bộ bảng, tạo Khoản thu mới thì hiện trên giao diện
        initPagination();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        scroll = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        lbTitle = new javax.swing.JLabel();
        pagination1 = new PhanTrangPackage.Pagination();
        comboLoai = new javax.swing.JComboBox<>();
        khieuNaiCard2 = new KhieuNaiPackage.KhieuNaiCard();

        panel.setBackground(new java.awt.Color(255, 255, 255));

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
        scroll.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(2).setMinWidth(0);
            table.getColumnModel().getColumn(2).setPreferredWidth(0);
            table.getColumnModel().getColumn(2).setMaxWidth(0);
        }

        jButton1.setText("Tạo mới");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lbTitle.setText("Quản lý khiếu nại");

        comboLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chờ duyệt", "Đã duyệt", "Từ chối" }));

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addContainerGap(66, Short.MAX_VALUE)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(khieuNaiCard2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                            .addComponent(pagination1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(392, 392, 392))
                        .addGroup(panelLayout.createSequentialGroup()
                            .addComponent(lbTitle)
                            .addGap(1760, 1760, 1760)))
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(panelLayout.createSequentialGroup()
                            .addComponent(comboLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton1)
                            .addContainerGap())
                        .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 879, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(lbTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(khieuNaiCard2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(comboLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(pagination1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        CreateKhieuNai create = new CreateKhieuNai(taiKhoan);
        DefaultOption option = new DefaultOption() {
            @Override
            public boolean closeWhenClickOutside() {
                return true;
            }
        };
        String actions[] = new String[]{"Thoát", "Đồng ý"};
        GlassPanePopup.showPopup(new SimplePopupBorder(create, "Tạo đơn khiếu nại", actions, (pc, i) -> {
            if (i == 1) { // Nếu người dùng nhấn "Save"
                try {
                    // Kiểm tra dữ liệu trước khi lưu
                    ModelKhieuNai data = create.getData();
                    if (data == null) {
                        Notifications.getInstance().show(Notifications.Type.WARNING, "Hãy nhập đủ dữ liệu!");
                        return;
                    }

                    // Lưu dữ liệu
                    service.create(data);
                    pc.closePopup();
                    Notifications.getInstance().show(Notifications.Type.SUCCESS, "Thông tin mới đã được tạo");

                    // Tải lại dữ liệu sau khi tạo mới
                    loadData(currentPage); // Thêm để khi xóa toàn bộ bảng, tạo Khoản thu mới thì hiện trên giao diện
                    initPagination();
                } catch (Exception e) {
                    e.printStackTrace();
                    Notifications.getInstance().show(Notifications.Type.ERROR, "Đã có lỗi xảy ra trong quá trình tạo!");
                }
            } else {
                // Nếu người dùng nhấn "Cancel"
                pc.closePopup();
            }
        }), option);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboLoai;
    private javax.swing.JButton jButton1;
    private KhieuNaiPackage.KhieuNaiCard khieuNaiCard2;
    private javax.swing.JLabel lbTitle;
    private PhanTrangPackage.Pagination pagination1;
    private javax.swing.JPanel panel;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
