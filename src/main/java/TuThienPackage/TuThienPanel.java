/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package TuThienPackage;

import Database.Service;
import Model.CheckBoxTableHeader;
import Model.ModelKhoanThu;
import Model.ModelThuPhi;
import Model.TableHeaderAlignment;
import com.formdev.flatlaf.FlatClientProperties;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import raven.popup.DefaultOption;
import raven.popup.GlassPanePopup;
import raven.popup.component.SimplePopupBorder;
import raven.toast.Notifications;

public class TuThienPanel extends javax.swing.JPanel {
    
    private final Service service = new Service();
    
    public TuThienPanel() {
        initComponents();
        init();
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
        new TableHeaderAlignment(table);
        comboBoxThang.setSelectedIndex(java.time.LocalDate.now().getMonthValue() - 1);
        int thang = Integer.parseInt(comboBoxThang.getSelectedItem().toString()); 
        try {
            comboBox.removeAllItems();
            List<ModelKhoanThu> listKhoanThu = service.getAll(ModelKhoanThu.class, "Tự nguyện"); 
            if (listKhoanThu != null) {
                for (ModelKhoanThu pos : listKhoanThu) {
                    int thangBatDau = pos.getNgayBatDauThu().getMonth() + 1;
                    int thangKetThuc = pos.getNgayKetThuc().getMonth() + 1;
                    if (thangKetThuc >= thang && thangBatDau <= thang) {
                        comboBox.addItem(pos); 
                    }
                }
            } else {
                System.out.println("Danh sách loại khoản thu trả về rỗng.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi tải danh sách loại khoản thu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    private void loadData(int thang, String IDKhoanThu){
        try {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);  // Xóa dữ liệu cũ (nếu có)
            // Lấy và thêm dữ liệu mới từ cơ sở dữ liệu
            List<ModelThuPhi> list = service.loadForTuThien(IDKhoanThu, thang);
            for (ModelThuPhi tp : list) {
                model.addRow(tp.toTableRowCharity(table.getRowCount() + 1));  // Thêm dòng dữ liệu mới vào bảng
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<ModelThuPhi> getSelectedData(){
        List<ModelThuPhi> list = new ArrayList<>();
        for(int i = 0; i<table.getRowCount(); i++){
            if((boolean) table.getValueAt(i,0)){
                ModelThuPhi data = (ModelThuPhi)table.getValueAt(i, 5);
                list.add(data);
            }
        }
        return list;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        scroll = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        lbTitle = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        comboBoxThang = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        comboBox = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "STT", "Người ủng hộ", "Phòng", "Số tiền", "this", "Ngày đóng góp", "Ghi chú"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false
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
            table.getColumnModel().getColumn(0).setMinWidth(35);
            table.getColumnModel().getColumn(0).setPreferredWidth(35);
            table.getColumnModel().getColumn(0).setMaxWidth(35);
            table.getColumnModel().getColumn(1).setMinWidth(40);
            table.getColumnModel().getColumn(1).setPreferredWidth(40);
            table.getColumnModel().getColumn(1).setMaxWidth(40);
            table.getColumnModel().getColumn(2).setMinWidth(260);
            table.getColumnModel().getColumn(2).setPreferredWidth(260);
            table.getColumnModel().getColumn(2).setMaxWidth(260);
            table.getColumnModel().getColumn(3).setMinWidth(90);
            table.getColumnModel().getColumn(3).setPreferredWidth(90);
            table.getColumnModel().getColumn(3).setMaxWidth(90);
            table.getColumnModel().getColumn(4).setMinWidth(120);
            table.getColumnModel().getColumn(4).setPreferredWidth(120);
            table.getColumnModel().getColumn(4).setMaxWidth(120);
            table.getColumnModel().getColumn(5).setMinWidth(0);
            table.getColumnModel().getColumn(5).setPreferredWidth(0);
            table.getColumnModel().getColumn(5).setMaxWidth(0);
            table.getColumnModel().getColumn(6).setMinWidth(120);
            table.getColumnModel().getColumn(6).setPreferredWidth(120);
            table.getColumnModel().getColumn(6).setMaxWidth(120);
        }

        lbTitle.setText("Thống kê đóng góp tự nguyện");

        jLabel2.setText("Tháng");

        comboBoxThang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        comboBoxThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxThangActionPerformed(evt);
            }
        });

        jLabel3.setText("Tên khoản đóng góp");

        jButton1.setText("Tra cứu");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Tạo mới");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Xóa");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2024" }));

        jLabel1.setText("Năm");

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lbTitle))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 990, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboBoxThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelLayout.createSequentialGroup()
                                        .addComponent(comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(33, 33, 33)
                                        .addComponent(jButton1)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton3))
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(94, 94, 94))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lbTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1))
                .addGap(6, 6, 6)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 613, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(308, 308, 308))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (comboBox.getSelectedItem() == null || comboBoxThang.getSelectedItem() == null){
            Notifications.getInstance().show(Notifications.Type.ERROR, "Hãy chọn đủ tháng và loại khoản từ thiện để tạo!");
            return;
        }
        int thang = Integer.parseInt(comboBoxThang.getSelectedItem().toString());
        ModelKhoanThu khoanThu = (ModelKhoanThu) comboBox.getSelectedItem();
        String IDKhoanThu = khoanThu.getID();
        loadData(thang, IDKhoanThu);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void comboBoxThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxThangActionPerformed
        
        int thang = Integer.parseInt(comboBoxThang.getSelectedItem().toString()); 
        try {
            comboBox.removeAllItems();
            List<ModelKhoanThu> listKhoanThu = service.getAll(ModelKhoanThu.class, "Tự nguyện"); 
            if (listKhoanThu != null) {
                for (ModelKhoanThu pos : listKhoanThu) {
                    int thangBatDau = pos.getNgayBatDauThu().getMonth() + 1;
                    int thangKetThuc = pos.getNgayKetThuc().getMonth() + 1;
                    if (thangKetThuc >= thang && thangBatDau <= thang) {
                        comboBox.addItem(pos); 
                    }
                }
            } else {
                System.out.println("Danh sách loại khoản thu trả về rỗng.");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi tải danh sách loại khoản thu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }//GEN-LAST:event_comboBoxThangActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (comboBox.getSelectedItem() == null || comboBoxThang.getSelectedItem() == null){
            Notifications.getInstance().show(Notifications.Type.ERROR, "Hãy chọn đủ tháng và loại khoản từ thiện để tạo!");
            return;
        }
        CreateTuThien create = new CreateTuThien((ModelKhoanThu) comboBox.getSelectedItem(), Integer.parseInt(comboBoxThang.getSelectedItem().toString()));
        DefaultOption option = new DefaultOption() {
            @Override
            public boolean closeWhenClickOutside() {
                return true;
            }
        };
        String actions[] = new String[]{"Thoát", "Đồng ý"};
        GlassPanePopup.showPopup(new SimplePopupBorder(create, "Thêm khoản đóng góp", actions, (pc, i) -> {
            if (i == 1) { // Nếu người dùng nhấn "Save"
                try {
                    // Kiểm tra dữ liệu trước khi lưu
                    ModelThuPhi data = create.getData();
                    if (data == null || data.getModelKhoanThu().getLoaiKhoanThu() == null) {
                        Notifications.getInstance().show(Notifications.Type.WARNING, "Vui lòng nhập đủ thông tin");
                        return;
                    }

                    // Lưu dữ liệu
                    service.createForTuThien(data);
                    loadData(Integer.parseInt(comboBoxThang.getSelectedItem().toString()), ((ModelKhoanThu) comboBox.getSelectedItem()).getID());
                    pc.closePopup();
                    Notifications.getInstance().show(Notifications.Type.SUCCESS, "Dữ liệu đóng góp mới đã được tạo");
                } catch (Exception e) {
                    e.printStackTrace();
                    Notifications.getInstance().show(Notifications.Type.ERROR, "Đã có lỗi xảy ra trong quá trình tạo khoản thu!");
                }
            } else {
                // Nếu người dùng nhấn "Cancel"
                pc.closePopup();
            }
        }), option);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        List<ModelThuPhi> list = getSelectedData();
        if (!list.isEmpty()){
              DefaultOption option = new DefaultOption(){ // hien thi bang de minh xoa
                @Override
                public boolean closeWhenClickOutside(){
                    return true;
                }
            };
            String actions[] = new String[]{"Thoát", "Đồng ý"};
            JLabel label = new JLabel("Bạn có muốn xóa " + list.size()+" khoản thu ?");
            label.setBorder(new EmptyBorder(0, 25, 0, 25));
             
            GlassPanePopup.showPopup(new SimplePopupBorder(label, "Xác nhận xóa", actions, (pc, i) ->{
                if(i == 1){
                    try{
                        for(ModelThuPhi d: list){
                            service.deleteForTuThien(d);
                        }
                        pc.closePopup();
                        Notifications.getInstance().show(Notifications.Type.SUCCESS, "Khoản thu đã được xoá");
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    loadData(Integer.parseInt(comboBoxThang.getSelectedItem().toString()), ((ModelKhoanThu) comboBox.getSelectedItem()).getID());
                }else{
                    pc.closePopup();
                }
            }),option);
        }else{
            Notifications.getInstance().show(Notifications.Type.WARNING, "Hãy chọn khoản thu để xóa!");
        }
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Object> comboBox;
    private javax.swing.JComboBox<String> comboBoxThang;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JPanel panel;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
