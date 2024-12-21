/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Database.Service;
import Model.ModelTaiKhoan;
import Model.Pagination.EventPagination;
import Model.Pagination.PaginationItemRenderStyle1;
import View.TaiKhoanView.EditTaiKhoan;
import View.TaiKhoanView.TaiKhoanPanel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import raven.popup.DefaultOption;
import raven.popup.GlassPanePopup;
import raven.popup.component.SimplePopupBorder;
import raven.toast.Notifications;

/**
 *
 * @author ADMIN
 */
public class TaiKhoanController {
    private TaiKhoanPanel view;
    private ModelTaiKhoan taiKhoan;
    private Service service = new Service();
    int currentPage = -1;
    public TaiKhoanController(TaiKhoanPanel view, ModelTaiKhoan taiKhoan) {
        this.view = view;
        this.taiKhoan = taiKhoan;
        view.addControllerListener(this);  // Gắn sự kiện cho các thành phần trong view
        initPagination();
        loadData(1);
    }
    public void initPagination() {
        // Sử dụng pagination1 thay vì pagination
        view.getPagination1().setPaginationItemRender(new PaginationItemRenderStyle1());

        // Lắng nghe sự kiện thay đổi trang
        view.getPagination1().addEventPagination(new EventPagination() {
            public void pageChanged(int page) {
                loadData(page); // Tải dữ liệu khi trang thay đổi
            }
        });

        // Tính toán số trang
        int totalCount = service.getTotalCount("tai_khoan");  // Lấy tổng số bản ghi từ cơ sở dữ liệu
        int recordsPerPage = 10;  // Số bản ghi trên mỗi trang
        int totalPages = (int) Math.ceil((double) totalCount / recordsPerPage);  // Tổng số trang

        view.getPagination1().setPagegination(1, totalPages);  // Cập nhật trang hiện tại và tổng số trang

        // Thêm phân trang vào giao diện
        view.getPanel().add(view.getPagination1());
        view.getPanel().revalidate();
        view.getPanel().repaint();
    }
    
    private void loadData(int page) {
        currentPage = page;
        try {
            // Lấy model của bảng
            DefaultTableModel model = (DefaultTableModel) view.getTable().getModel();

            // Nếu bảng đang chỉnh sửa, dừng chỉnh sửa ô
            if (view.getTable().isEditing()) {
                view.getTable().getCellEditor().stopCellEditing();
            }

            // Xóa hết các dòng hiện tại trong bảng
            model.setRowCount(0);

            // Tính số bản ghi mỗi trang
            int recordsPerPage = 10;
            int offset = (page - 1) * recordsPerPage;  // Tính offset dựa trên trang

            // Khởi tạo danh sách
            List<ModelTaiKhoan> list = new ArrayList<>();

            // Lấy dữ liệu phù hợp
            if (taiKhoan.isAdmin()) {
                list = service.getPage(ModelTaiKhoan.class, offset, recordsPerPage);
            } else {
                list.add(taiKhoan);
            }
            // Thêm các dòng vào bảng
            for (ModelTaiKhoan d : list) {
                model.addRow(d.toTableRow(view.getTable().getRowCount() + 1));  // Thêm dòng vào bảng
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Phuong thuc de Search trong bang
    private void searchData(String search){
        try {
            DefaultTableModel model =(DefaultTableModel)view.getTable().getModel();
            if(view.getTable().isEditing()){
                view.getTable().getCellEditor().stopCellEditing();
            }
            model.setRowCount(0);
            // Khởi tạo danh sách
            List<ModelTaiKhoan> list = new ArrayList<>();

            // Lấy dữ liệu phù hợp
            if (taiKhoan.isAdmin()) {
                list = service.getAll(ModelTaiKhoan.class,search);
            } else {
                list.add(taiKhoan);
            }
            
            for(ModelTaiKhoan d:list){
                model.addRow(d.toTableRow(view.getTable().getRowCount() + 1));
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public List<ModelTaiKhoan> getSelectedTaiKhoan() {
        List<ModelTaiKhoan> list = new ArrayList<>();

        for (int i = 0; i < view.getTable().getRowCount(); i++) {
            if ((boolean) view.getTable().getValueAt(i, 0)) {  // Kiểm tra trạng thái checkbox ở cột đầu tiên
                Object data = view.getTable().getValueAt(i, 10);  // Lấy giá trị ở cột thứ 1

                // Kiểm tra kiểu dữ liệu và ép kiểu nếu đúng
                if (data instanceof ModelTaiKhoan) {
                    ModelTaiKhoan taiKhoan = (ModelTaiKhoan) data;
                    list.add(taiKhoan);
                } else {
                    // Xử lý lỗi nếu không phải kiểu ModelTaiKhoan
                    System.out.println("Dữ liệu không phải kiểu ModelTaiKhoan tại dòng " + i);
                }
            }
        }

        return list;
    }
    
    public void buttonEditActionPerformed(java.awt.event.ActionEvent evt) {                                           
        
        List<ModelTaiKhoan> list = getSelectedTaiKhoan();
        if (list.isEmpty()){
            Notifications.getInstance().show(Notifications.Type.WARNING, "Hãy chọn mục để chỉnh sửa!");
        }else if(list.size() != 1){
            Notifications.getInstance().show(Notifications.Type.WARNING, "Hãy chọn duy nhất một mục!");
        }else{
        
        // Truyền đối tượng tài khoản hiện tại vào form để chỉnh sửa
        ModelTaiKhoan currentTaiKhoan = (ModelTaiKhoan) getSelectedTaiKhoan().get(0); // Lấy đối tượng tài khoản hiện tại, ví dụ từ cơ sở dữ liệu hoặc bảng
        // Tạo một form chỉnh sửa tài khoản và truyền đối tượng tài khoản hiện tại vào form
        EditTaiKhoan editForm = new EditTaiKhoan(currentTaiKhoan);
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
                    
                    ModelTaiKhoan updatedTaiKhoan = editForm.getData(currentTaiKhoan);  // Truyền tài khoản hiện tại vào form để lấy dữ liệu cập nhật
                    if(updatedTaiKhoan != null){
                        service.edit(updatedTaiKhoan);  // Gọi phương thức edit trong ServiceTaiKhoan để cập nhật tài khoản
                        // Đóng Popup và thông báo thành công
                        pc.closePopup();
                        Notifications.getInstance().show(Notifications.Type.SUCCESS, "Tài khoản đã được chỉnh sửa thành công");
                    } 

                    // Tải lại dữ liệu từ database để cập nhật bảng
                    loadData(currentPage);  // Hàm load lại dữ liệu sau khi chỉnh sửa
                    initPagination();
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(view, "Đã xảy ra lỗi khi chỉnh sửa tài khoản!");
                }
            } else {  // Nếu nhấn Cancel
                pc.closePopup();
            }
        }), option);
        }
    }    
    public void buttonApproveActionPerformed(java.awt.event.ActionEvent evt) {                                              
        
        List<ModelTaiKhoan> list = getSelectedTaiKhoan();
        if (list.isEmpty()){
            Notifications.getInstance().show(Notifications.Type.WARNING, "Hãy chọn mục để chỉnh sửa!");
        } else {
            // Kiểm tra xem có tài khoản nào đã duyệt chưa
            boolean hasApproved = false;
            for (ModelTaiKhoan tk : list) {
                if ("Đã duyệt".equals(tk.getGhiChu())) {
                    hasApproved = true;
                    break; // Nếu có tài khoản đã duyệt thì không cần kiểm tra tiếp
                }
            }

            if (hasApproved) {
                // Nếu có tài khoản đã duyệt, thông báo cho người dùng
                JOptionPane.showMessageDialog(view, "Trong các mục đã chọn có tài khoản đã duyệt rồi!");
            } else {
                // Đếm số tài khoản đã chọn
                int count = list.size();

                // Duyệt qua từng tài khoản và cập nhật "Đã duyệt"
                for (ModelTaiKhoan tk : list) {
                    tk.setGhiChu("Đã duyệt");
                    try {
                        service.edit(tk);
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(view, "Đã xảy ra lỗi khi duyệt tài khoản!");
                        return; // Nếu có lỗi, dừng và không tiếp tục
                    }
                }
                loadData(currentPage);  // Hàm load lại dữ liệu sau khi chỉnh sửa
                initPagination();
                // Hiển thị thông báo với số lượng tài khoản đã duyệt
                JOptionPane.showMessageDialog(view, "Duyệt thành công " + count + " tài khoản!");
            }
        }
    }
    public void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {                                      
        searchData(view.getTxtSearch().getText().trim());
    }  
    public void buttonDeleteActionPerformed(java.awt.event.ActionEvent evt) {                                             
        if(!taiKhoan.isAdmin()){
            Notifications.getInstance().show(Notifications.Type.WARNING, "Chỉ có ADMIN mới sử dụng được chức năng này!");
            return;
        }
        List<ModelTaiKhoan> list = getSelectedTaiKhoan();
        if (list.isEmpty()){
            Notifications.getInstance().show(Notifications.Type.WARNING, "Hãy chọn mục để xóa!");
        } else {
            // Đếm số tài khoản đã chọn để xóa
            int count = list.size();

            // Xác nhận hành động xóa với người dùng
            int confirm = JOptionPane.showConfirmDialog(view, 
                "Bạn có chắc chắn muốn xóa " + count + " tài khoản này?", 
                "Xác nhận xóa", JOptionPane.YES_NO_OPTION);

            // Nếu người dùng xác nhận xóa
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    for (ModelTaiKhoan tk : list) {
                        service.delete(tk); // Gọi phương thức xóa trong service
                    }
                    loadData(1); // Tải lại dữ liệu
                    JOptionPane.showMessageDialog(view, "Đã xóa thành công " + count + " tài khoản!");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(view, "Đã xảy ra lỗi khi xóa tài khoản!");
                }
            }
        }
    }           
    
}
