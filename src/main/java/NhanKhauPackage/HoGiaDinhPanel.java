/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package NhanKhauPackage;

import Database.Service;
import Model.ButtonEditor;
import Model.ButtonRenderer;
import Model.CheckBoxTableHeader;
import Model.TableHeaderAlignment;
import Model.ModelHoGiaDinh;
import Model.ModelTaiKhoan;
import PhanTrangPackage.EventPagination;
import PhanTrangPackage.Style.PaginationItemRenderStyle1;
import com.formdev.flatlaf.FlatClientProperties;
import java.awt.BorderLayout;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import raven.popup.DefaultOption;
import raven.popup.GlassPanePopup;
import raven.popup.component.SimplePopupBorder;
import raven.toast.Notifications;



/**
 *
 * @author lamto
 */
public class HoGiaDinhPanel extends javax.swing.JPanel {

    /**
     * Creates new form HoGiaDinhPanel
     */
    private Service service = new Service();
    private ModelTaiKhoan taiKhoan;
    private int currentPage = -1;
    /**
     * Creates new form HoGiaDinhPanel
     */
    public HoGiaDinhPanel(ModelTaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
        initComponents();
        init();
        initPagination();
        loadData(1); 
    }
    
    
    
    // Phuong thuc de Search trong bang
    private void searchData(String search){
        try {
            DefaultTableModel model =(DefaultTableModel)table.getModel();
            if(table.isEditing()){
                table.getCellEditor().stopCellEditing();
            }
            model.setRowCount(0);
            // Khởi tạo danh sách
            List<ModelHoGiaDinh> list = new ArrayList<>();

            list = service.getAll(ModelHoGiaDinh.class,search);
  
            for(ModelHoGiaDinh d:list){
                model.addRow(d.toTableRow());
                
            }
            init();
        } catch(Exception e){
            e.printStackTrace();
        }
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
        table.getColumnModel().getColumn(8).setCellEditor(new ButtonEditor(new JCheckBox(),table));
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
        int totalCount = service.getTotalCount("ho_gia_dinh");  // Lấy tổng số bản ghi từ cơ sở dữ liệu
        int recordsPerPage = 20;  // Số bản ghi trên mỗi trang
        int totalPages = (int) Math.ceil((double) totalCount / recordsPerPage);  // Tổng số trang

        pagination1.setPagegination(1, totalPages);  // Cập nhật trang hiện tại và tổng số trang

        // Thêm phân trang vào giao diện
        panel.add(pagination1);
        panel.revalidate();
        panel.repaint();
    }

    
    // Phương thức tải dữ liệu cho bảng
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
            List<ModelHoGiaDinh> list = service.getPage(ModelHoGiaDinh.class, offset, recordsPerPage);

            // Thêm các dòng vào bảng
            for (ModelHoGiaDinh d : list) {
                model.addRow(d.toTableRow());  // Thêm dòng vào bảng
                
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<ModelHoGiaDinh> getSelectedItem() {
        List<ModelHoGiaDinh> list = new ArrayList<>();

        for (int i = 0; i < table.getRowCount(); i++) {
            if ((boolean) table.getValueAt(i, 0)) {  // Kiểm tra trạng thái checkbox ở cột đầu tiên
                Object data = table.getValueAt(i, 7);  // Lấy giá trị ở cột thứ 1

                // Kiểm tra kiểu dữ liệu và ép kiểu nếu đúng
                if (data instanceof ModelHoGiaDinh) {
                    ModelHoGiaDinh nhanKhau = (ModelHoGiaDinh) data;
                    list.add(nhanKhau);
                } else {
                    // Xử lý lỗi nếu không phải kiểu ModelTaiKhoan
                    System.out.println("Dữ liệu không phải kiểu ModelTaiKhoan tại dòng " + i);
                }
            }
        }

        return list;
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
        jTextField1 = new javax.swing.JTextField();
        lbTitle = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        pagination1 = new PhanTrangPackage.Pagination();

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Chọn", "Số phòng", "Diện tích", "Số người ", "Số xe máy", "Số ô tô", "Trạng thái", "this", "Chi tiết "
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
            table.getColumnModel().getColumn(7).setMinWidth(0);
            table.getColumnModel().getColumn(7).setMaxWidth(0);
        }

        lbTitle.setText("Thống kê theo phòng");

        jButton1.setText("Chỉnh sửa");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "1", "2", "3", "4", "5", "6", "7", "8", "9", " " }));

        jButton3.setText("Thông tin cư dân");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 953, Short.MAX_VALUE)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(lbTitle)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(pagination1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(254, 254, 254)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lbTitle)
                .addGap(18, 18, 18)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pagination1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        // Tạo một instance mới của HoGiaDinhPanel
        NhanKhauPanel nhanKhauPanel = new NhanKhauPanel(taiKhoan);

        // Xóa nội dung hiện tại và thay thế bằng giao diện mới
        this.removeAll();
        this.setLayout(new BorderLayout()); // Sử dụng BorderLayout cho chính xác
        Component hoGiaDinhPanel;
        this.add(nhanKhauPanel, BorderLayout.CENTER);

        // Cập nhật lại giao diện
        this.revalidate();
        this.repaint();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        List<ModelHoGiaDinh> list = getSelectedItem();
        if (list.isEmpty()){
            Notifications.getInstance().show(Notifications.Type.WARNING, "Hãy chọn mục để chỉnh sửa!");
        }else if(list.size() != 1){
            Notifications.getInstance().show(Notifications.Type.WARNING, "Hãy chọn duy nhất một mục!");
        }else{
        
        // Truyền đối tượng tài khoản hiện tại vào form để chỉnh sửa
        ModelHoGiaDinh currentHoGiaDinh = (ModelHoGiaDinh) getSelectedItem().get(0); // Lấy đối tượng tài khoản hiện tại, ví dụ từ cơ sở dữ liệu hoặc bảng
        // Tạo một form chỉnh sửa tài khoản và truyền đối tượng tài khoản hiện tại vào form
        EditPhong editForm = new EditPhong(currentHoGiaDinh);
        editForm.loadData(currentHoGiaDinh);
        // Tạo một cửa sổ Popup để chỉnh sửa
        DefaultOption option = new DefaultOption() {
            @Override
            public boolean closeWhenClickOutside() {
                return true;
            }
        };
        String actions[] = new String[]{"Thoát", "Đồng ý"};
        // Hiển thị Popup và xử lý sự kiện khi nhấn nút Save hoặc Cancel
        GlassPanePopup.showPopup(new SimplePopupBorder(editForm, "Chỉnh sửa tài khoản", actions, (pc, i) -> {
            if (i == 1) {  // Nếu nhấn Save
                try {
                    // Lấy dữ liệu từ form chỉnh sửa và truyền tài khoản hiện tại vào phương thức getData
                    ModelHoGiaDinh updatedHoGiaDinh = editForm.getData();  // Truyền thong tin hiện tại vào form để lấy dữ liệu cập nhật
                    updatedHoGiaDinh.setSoPhong(currentHoGiaDinh.getSoPhong());
                    service.edit(updatedHoGiaDinh);  // Gọi phương thức edit trong ServiceTaiKhoan để cập nhật tài khoản

                    // Đóng Popup và thông báo thành công
                    pc.closePopup();
                    Notifications.getInstance().show(Notifications.Type.SUCCESS, "Thông tin đã được chỉnh sửa thành công");
                    loadData(currentPage);  
                    initPagination();
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi chỉnh sửa!");
                }
            } else {  // Nếu nhấn Cancel
                pc.closePopup();
            }
        }), option);
        } // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lbTitle;
    private PhanTrangPackage.Pagination pagination1;
    private javax.swing.JPanel panel;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
