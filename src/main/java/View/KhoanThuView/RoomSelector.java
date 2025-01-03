/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View.KhoanThuView;

import Database.Service;
import Model.CheckBoxTableHeader;
import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Color;
import java.awt.Component;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author ADMIN
 */
public class RoomSelector extends javax.swing.JFrame {

    /**
     * Creates new form RoomSelector
     */
    // Set để lưu trữ các ô đã chọn
    private Set<String> selectedCells = new HashSet<>();

    public Set<String> getSelectedCells() {
        return selectedCells;
    }
    
    public RoomSelector(String maKhoanThu) {
        initComponents();
        panel.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:25;"
                + "background:$Table.background");
        table.getColumnModel().getColumn(0).setHeaderRenderer(new CheckBoxTableHeader(table, 0));
        setLocationRelativeTo(null);

        // Cài đặt Renderer cho JTable
        table.setDefaultRenderer(Object.class, new CustomCellRenderer());
        markRoomsBasedOnMaKhoanThu(maKhoanThu);
        // Add MouseListener cho bảng để bắt sự kiện chọn ô
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table.rowAtPoint(evt.getPoint());
                int column = table.columnAtPoint(evt.getPoint());

                if (row >= 0 && column >= 0) {
                    toggleSelection(row, column);
                    table.repaint();
                }
            }
        });
    }

    private void toggleSelection(int row, int column) {
        if (column == 0) { 
            // Xử lý khi click vào cột đầu tiên
            boolean isRowSelected = true;

            // Kiểm tra nếu tất cả các ô trong hàng đã được chọn
            for (int i = 1; i < table.getColumnCount(); i++) {
                Object rowValue = table.getValueAt(row, i);
                String rowContent = rowValue != null ? rowValue.toString() : "";

                if (!selectedCells.contains(rowContent)) {
                    isRowSelected = false;
                    break;
                }
            }

            // Nếu cả hàng đã được chọn, thì bỏ chọn tất cả
            if (isRowSelected) {
                for (int i = 1; i < table.getColumnCount(); i++) {
                    Object rowValue = table.getValueAt(row, i);
                    String rowContent = rowValue != null ? rowValue.toString() : "";
                    selectedCells.remove(rowContent);
                }
            } else {
                // Nếu chưa, thì chọn tất cả các ô trong hàng
                for (int i = 1; i < table.getColumnCount(); i++) {
                    Object rowValue = table.getValueAt(row, i);
                    String rowContent = rowValue != null ? rowValue.toString() : "";
                    selectedCells.add(rowContent);
                }
            }
        } else { 
            // Xử lý khi click vào các ô khác ngoài cột đầu tiên
            Object cellValue = table.getValueAt(row, column);
            String cellContent = cellValue != null ? cellValue.toString() : "";

            if (selectedCells.contains(cellContent)) {
                selectedCells.remove(cellContent); // Bỏ chọn
            } else {
                selectedCells.add(cellContent); // Chọn
            }
        }

        table.repaint(); // Vẽ lại bảng
    }
    
    public void markRoomsBasedOnMaKhoanThu(String maKhoanThu) {
        // Lấy danh sách phòng từ cơ sở dữ liệu
        Set<String> danhSachPhong = (new Service()).loadDanhSachPhong(maKhoanThu);

        // Duyệt qua tất cả các ô trong bảng để đánh dấu ô nào có số phòng trong danh sách
        for (int row = 0; row < table.getRowCount(); row++) {
            for (int column = 1; column < table.getColumnCount(); column++) { // Bắt đầu từ cột 1 vì cột 0 là cột chọn
                Object cellValue = table.getValueAt(row, column);
                String cellContent = cellValue != null ? cellValue.toString() : "";

                // Nếu số phòng trong ô nằm trong danh sách phòng đã tải, chọn ô đó
                if (danhSachPhong.contains(cellContent)) {
                    selectedCells.add(cellContent);
                }
            }
        }

        // Vẽ lại bảng để hiển thị sự thay đổi
        table.repaint();
    }


    //hello
    // Custom TableCellRenderer để thay đổi màu nền của ô khi chọn
    class CustomCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            // Lấy nội dung của ô
            String cellContent = value != null ? value.toString() : "";

            // Nếu nội dung của ô nằm trong selectedCells và không phải ô đầu hàng
            if (column != 0 && selectedCells.contains(cellContent)) {
                component.setBackground(new Color(173, 216, 230)); // Màu nền khác khi ô được chọn
            } else {
                component.setBackground(table.getBackground()); // Màu nền mặc định
            }

            // Đảm bảo không thay đổi màu chữ
            component.setForeground(table.getForeground());

            return component;
        }
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
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        panel.setBackground(new java.awt.Color(255, 255, 255));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, "101", "102", "103", "104", "105", "106", "107", "108", "109"},
                {null, "201", "202", "203", "204", "205", "206", "207", "208", "209"},
                {null, "301", "302", "303", "304", "305", "306", "307", "308", "309"},
                {null, "401", "402", "403", "404", "405", "406", "407", "408", "409"},
                {null, "501", "502", "503", "504", "505", "506", "507", "508", "509"},
                {null, "601", "602", "603", "604", "605", "606", "607", "608", "609"},
                {null, "701", "702", "703", "704", "705", "706", "707", "708", "709"},
                {null, "801", "802", "803", "804", "805", "806", "807", "808", "809"},
                {null, "901", "902", "903", "904", "905", "906", "907", "908", "909"}
            },
            new String [] {
                "Pick", "1", "2", "3", "4", "5", "6", "7", "8", "9"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setResizable(false);
            table.getColumnModel().getColumn(1).setResizable(false);
            table.getColumnModel().getColumn(2).setResizable(false);
            table.getColumnModel().getColumn(3).setResizable(false);
            table.getColumnModel().getColumn(4).setResizable(false);
            table.getColumnModel().getColumn(5).setResizable(false);
            table.getColumnModel().getColumn(6).setResizable(false);
            table.getColumnModel().getColumn(7).setResizable(false);
            table.getColumnModel().getColumn(8).setResizable(false);
            table.getColumnModel().getColumn(9).setResizable(false);
        }

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(RoomSelector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RoomSelector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RoomSelector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RoomSelector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RoomSelector("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panel;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
