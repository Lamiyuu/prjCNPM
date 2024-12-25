/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Database.Service;
import Model.ModelHoaDon;
import Model.Pagination.PaginationItemRenderStyle1;
import View.ThuPhiView.ThongKeThuPhiPanel;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class ThongKeThuPhiController {
     private ThongKeThuPhiPanel view;
    private int currentPage = 1;
    private Service service = new Service();
    
    public ThongKeThuPhiController(ThongKeThuPhiPanel tuThienView) {
        this.view = tuThienView;
        view.addControllerListener(this);

        initComboBoxThang(); // Khởi tạo ComboBox chọn tháng
        initPagination(); // Khởi tạo phân trang
        loadData(getSelectedMonth(), 1); // Tải dữ liệu ban đầu
    }

    private void initComboBoxThang() {
        JComboBox<String> comboBoxThang = view.getComboBoxThang(); 
        // Gắn ActionListener để gọi hàm comboBoxThangActionPerformed
        comboBoxThang.addActionListener(this::comboBoxThangActionPerformed);
    }

    private int getSelectedMonth() {
        return Integer.parseInt(view.getComboBoxThang().getSelectedItem().toString());
    }

    public void comboBoxThangActionPerformed(java.awt.event.ActionEvent evt) {
        // Lấy tháng được chọn
        int thang = getSelectedMonth();
        // Gọi loadData để tải lại dữ liệu theo tháng mới
        loadData(thang, 1);
    }
    
    private void loadData(int thang, int page) {
        currentPage = page;
        try {
            DefaultTableModel model = (DefaultTableModel) view.getTable().getModel();

            if (view.getTable().isEditing()) {
                view.getTable().getCellEditor().stopCellEditing();
            }

            model.setRowCount(0);

            int recordsPerPage = 20;
            int offset = (page - 1) * recordsPerPage;

            List<ModelHoaDon> list = service.loadForThongKeThuPhi(thang, offset, recordsPerPage);
            for (ModelHoaDon tp : list) {
                model.addRow(tp.toTableRow1(view.getTable().getRowCount() + 1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi tải dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initPagination() {
        view.getPagination1().setPaginationItemRender(new PaginationItemRenderStyle1());

        view.getPagination1().addEventPagination(page -> {
            loadData(getSelectedMonth(), page);
        });

        int totalCount = service.getTotalCount("hoa_don");
        int recordsPerPage = 20;
        int totalPages = (int) Math.ceil((double) totalCount / recordsPerPage);

        view.getPagination1().setPagegination(1, totalPages);
        view.getPanel().add(view.getPagination1());
        view.getPanel().revalidate();
        view.getPanel().repaint();
    }
}
