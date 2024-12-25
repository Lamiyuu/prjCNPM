/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Database.Service;
import Model.ModelHoGiaDinh;
import Model.ModelTaiKhoan;
import View.NhanKhauView.EditPhong;
import View.NhanKhauView.HoGiaDinhPanel;
import View.NhanKhauView.NhanKhauPanel;
import Model.Pagination.EventPagination;
import Model.Pagination.PaginationItemRenderStyle1;
import java.awt.BorderLayout;
import java.awt.Component;
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
 * @author lamto
 */
public class HoGiaDinhController {
    private final HoGiaDinhPanel view;
    private final ModelTaiKhoan taiKhoan;
    private final Service service = new Service();
    private int currentPage = -1;
    public HoGiaDinhController(HoGiaDinhPanel hoGiaDinhView, ModelTaiKhoan taiKhoan) {
        this.view = hoGiaDinhView;
        this.taiKhoan = taiKhoan;
        view.addControllerListener(this);  // Gắn sự kiện cho các thành phần trong view
        initPagination();
        loadData(1);
        
        
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
            List<ModelHoGiaDinh> list = new ArrayList<>();

            list = service.getAll(ModelHoGiaDinh.class,search);
  
            for(ModelHoGiaDinh d:list){
                model.addRow(d.toTableRow());
                
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void initPagination() {
        // Sử dụng pagination1 thay vì pagination
        view.getPagination1().setPaginationItemRender(new PaginationItemRenderStyle1());

        // Lắng nghe sự kiện thay đổi trang
        view.getPagination1().addEventPagination(new EventPagination() {
            public void pageChanged(int page) {
                loadData(page); // Tải dữ liệu khi trang thay đổi
            }
        });

        // Tính toán số trang
        int totalCount = service.getTotalCount("ho_gia_dinh");  // Lấy tổng số bản ghi từ cơ sở dữ liệu
        int recordsPerPage = 20;  // Số bản ghi trên mỗi trang
        int totalPages = (int) Math.ceil((double) totalCount / recordsPerPage);  // Tổng số trang

        view.getPagination1().setPagegination(1, totalPages);  // Cập nhật trang hiện tại và tổng số trang

        // Thêm phân trang vào giao diện
        view.getPanel().add(view.getPagination1());
        view.getPanel().revalidate();
        view.getPanel().repaint();
    }

    
    // Phương thức tải dữ liệu cho bảng
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

        for (int i = 0; i < view.getTable().getRowCount(); i++) {
            if ((boolean) view.getTable().getValueAt(i, 0)) {  // Kiểm tra trạng thái checkbox ở cột đầu tiên
                Object data = view.getTable().getValueAt(i, 7);  // Lấy giá trị ở cột thứ 1

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
    public void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {  
        if(!taiKhoan.isAdmin()){
            Notifications.getInstance().show(Notifications.Type.WARNING, "Chỉ có ADMIN mới sử dụng được chức năng này!");
            return;
        }
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
                    JOptionPane.showMessageDialog(view, "Đã xảy ra lỗi khi chỉnh sửa!");
                }
            } else {  // Nếu nhấn Cancel
                pc.closePopup();
            }
        }), option);
        } // TODO add your handling code here:
    }
    
    public void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        if(!taiKhoan.isAdmin()){
            Notifications.getInstance().show(Notifications.Type.WARNING, "Chỉ có ADMIN mới sử dụng được chức năng này!");
            return;
        }
        NhanKhauPanel nhanKhauPanel = new NhanKhauPanel(taiKhoan);

        // Xóa nội dung hiện tại và thay thế bằng giao diện mới
        view.removeAll();
        view.setLayout(new BorderLayout()); // Sử dụng BorderLayout cho chính xác
        Component hoGiaDinhPanel;
        view.add(nhanKhauPanel, BorderLayout.CENTER);

        // Cập nhật lại giao diện
        view.revalidate();
        view.repaint();
    }
    
    public void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {                                        
        searchData(view.getjTextField1().getText().trim());
    }

}
