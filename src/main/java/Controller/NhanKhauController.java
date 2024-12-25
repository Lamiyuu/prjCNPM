/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Database.Service;
import Model.ModelNhanKhau;
import Model.ModelTaiKhoan;
import View.NhanKhauView.EditNguoi;
import View.NhanKhauView.HoGiaDinhPanel;
import View.NhanKhauView.NhanKhauPanel;
import Model.Pagination.EventPagination;
import Model.Pagination.PaginationItemRenderStyle1;
import java.awt.BorderLayout;
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

/**
 *
 * @author lamto
 */
public class NhanKhauController {
    private NhanKhauPanel view;
    private ModelTaiKhoan taiKhoan;
    private Service service = new Service();
    private int currentPage = -1;
    public NhanKhauController(NhanKhauPanel nhanKhauView, ModelTaiKhoan taiKhoan) {
        this.view = nhanKhauView;
        this.taiKhoan = taiKhoan;
        view.addControllerListener(this);  // Gắn sự kiện cho các thành phần trong view
        initPagination();
        loadData(1);
    }
    
    public void taoMoiActionPerformed(java.awt.event.ActionEvent evt) {
        if(!taiKhoan.isAdmin()){
            Notifications.getInstance().show(Notifications.Type.WARNING, "Chỉ có ADMIN mới sử dụng được chức năng này!");
            return;
        }
        EditNguoi create = new EditNguoi(null);
        create.loadData(null);
        DefaultOption option = new DefaultOption() {
            @Override
            public boolean closeWhenClickOutside() {
                return true;
            }
        };
        String actions[] = new String[]{"Thoát", "Đồng ý"};
        GlassPanePopup.showPopup(new SimplePopupBorder(create, "Thêm cư dân", actions, (pc, i) -> {
            if (i == 1) { // Nếu người dùng nhấn "Save"
                try {
                    // Kiểm tra dữ liệu trước khi lưu
                    ModelNhanKhau data = create.getData();
                    if (data == null) {
                        Notifications.getInstance().show(Notifications.Type.WARNING, "Vui lòng nhập đủ thông tin người dân!");
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
    }
    
    public void chinhSuaActionPerformed(java.awt.event.ActionEvent evt) {
        if(!taiKhoan.isAdmin()){
            Notifications.getInstance().show(Notifications.Type.WARNING, "Chỉ có ADMIN mới sử dụng được chức năng này!");
            return;
        }
        List<ModelNhanKhau> list = getSelectedNhanKhau();
        
        if (list.isEmpty()){
            Notifications.getInstance().show(Notifications.Type.WARNING, "Hãy chọn mục để chỉnh sửa!");
        }else if(list.size() != 1){
            Notifications.getInstance().show(Notifications.Type.WARNING, "Hãy chọn duy nhất một mục!");
        }else{
        
        // Truyền đối tượng tài khoản hiện tại vào form để chỉnh sửa
        ModelNhanKhau currentNhanKhau = (ModelNhanKhau) getSelectedNhanKhau().get(0); // Lấy đối tượng tài khoản hiện tại, ví dụ từ cơ sở dữ liệu hoặc bảng
        // Tạo một form chỉnh sửa tài khoản và truyền đối tượng tài khoản hiện tại vào form
        EditNguoi editForm = new EditNguoi(currentNhanKhau);
        editForm.loadData(currentNhanKhau);
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
                    ModelNhanKhau updatedNhanKhau = editForm.getData();  // Truyền thong tin hiện tại vào form để lấy dữ liệu cập nhật
                    updatedNhanKhau.setID(currentNhanKhau.getID());
                    service.edit(updatedNhanKhau);  // Gọi phương thức edit trong ServiceTaiKhoan để cập nhật tài khoản

                    // Đóng Popup và thông báo thành công
                    pc.closePopup();
                    Notifications.getInstance().show(Notifications.Type.SUCCESS, "Thông tin đã được chỉnh sửa thành công");
                    System.out.println(updatedNhanKhau.getID());
                    loadData(currentPage);  
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
    
    public void xoaActionPerformed(java.awt.event.ActionEvent evt) {
        if(!taiKhoan.isAdmin()){
            Notifications.getInstance().show(Notifications.Type.WARNING, "Chỉ có ADMIN mới sử dụng được chức năng này!");
            return;
        }
        List<ModelNhanKhau> list = getSelectedNhanKhau();
        if (!list.isEmpty()){
              DefaultOption option = new DefaultOption(){ // hien thi bang de minh edit
                @Override
                public boolean closeWhenClickOutside(){
                    return true;
                }
            };
            String actions[] = new String[]{"Thoát", "Đồng ý"};
            JLabel label = new JLabel("Bạn có muốn xóa " + list.size()+" mục ?");
            label.setBorder(new EmptyBorder(0, 25, 0, 25));
             
            GlassPanePopup.showPopup(new SimplePopupBorder(label, "Xác nhận xóa", actions, (pc, i) ->{
                if(i == 1){
                    try{
                        for(ModelNhanKhau d: list){
                            service.delete(d);
                        }
                        pc.closePopup();
                        Notifications.getInstance().show(Notifications.Type.SUCCESS, "Các mục chọn đã được xoá");
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    loadData(1);
                }else{
                    pc.closePopup();
                }
            }),option);
        }else{
            Notifications.getInstance().show(Notifications.Type.WARNING, "Hãy chọn mục để xóa!");
        }
    }
    public void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {                                        
        searchData(view.getjTextField1().getText().trim());
    }
    public void comboBoxPhongActionPerformed(java.awt.event.ActionEvent evt) {                                              
        if(view.getComboBoxPhong().getSelectedIndex() == 0){
            if(view.getComboBoxTang().getSelectedIndex() != 0){
                searchData(view.getComboBoxTang().getSelectedItem().toString());
            }else{
                loadData(1);
            } 
        }else if(view.getComboBoxPhong().getSelectedItem() != null){
            searchData(view.getComboBoxPhong().getSelectedItem().toString());
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
            List<ModelNhanKhau> list = new ArrayList<>();

            list = service.getAll(ModelNhanKhau.class,search);
  
            for(ModelNhanKhau d:list){
                model.addRow(d.toTableRow(view.getTable().getRowCount() + 1));
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
        int totalCount = service.getTotalCount("tai_khoan");  // Lấy tổng số bản ghi từ cơ sở dữ liệu
        int recordsPerPage = 10;  // Số bản ghi trên mỗi trang
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
            List<ModelNhanKhau> list = service.getPage(ModelNhanKhau.class, offset, recordsPerPage);

            // Thêm các dòng vào bảng
            for (ModelNhanKhau d : list) {
                model.addRow(d.toTableRow(view.getTable().getRowCount() + 1));  // Thêm dòng vào bảng
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<ModelNhanKhau> getSelectedNhanKhau() {
        List<ModelNhanKhau> list = new ArrayList<>();

        for (int i = 0; i < view.getTable().getRowCount(); i++) {
            if ((boolean) view.getTable().getValueAt(i, 0)) {  // Kiểm tra trạng thái checkbox ở cột đầu tiên
                Object data = view.getTable().getValueAt(i, 2);  // Lấy giá trị ở cột thứ 1

                // Kiểm tra kiểu dữ liệu và ép kiểu nếu đúng
                if (data instanceof ModelNhanKhau) {
                    ModelNhanKhau nhanKhau = (ModelNhanKhau) data;
                    list.add(nhanKhau);
                } else {
                    // Xử lý lỗi nếu không phải kiểu ModelTaiKhoan
                    System.out.println("Dữ liệu không phải kiểu ModelTaiKhoan tại dòng " + i);
                }
            }
        }

        return list;
    }
    
    public void chuyenTrangActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // Tạo một instance mới của HoGiaDinhPanel
        HoGiaDinhPanel hoGiaDinhPanel = new HoGiaDinhPanel(taiKhoan);

        // Xóa nội dung hiện tại và thay thế bằng giao diện mới
        view.removeAll();
        view.setLayout(new BorderLayout()); // Sử dụng BorderLayout cho chính xác
        view.add(hoGiaDinhPanel, BorderLayout.CENTER);

        // Cập nhật lại giao diện
        view.revalidate();
        view.repaint();
    } 
}
