/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Database.Service;
import View.KhoanThuView.CreateKhoanThu;
import View.KhoanThuView.KhoanThuPanel;
import Model.ModelKhoanThu;
import Model.ModelTaiKhoan;
import Model.Pagination.EventPagination;
import Model.Pagination.PaginationItemRenderStyle1;
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
 * @author ADMIN
 */
public class KhoanThuController {
    private KhoanThuPanel view;
    private ModelTaiKhoan taiKhoan;
    private int currentPage = 1;
    private Service service = new Service();
    
    public KhoanThuController(KhoanThuPanel khoanThuView, ModelTaiKhoan taiKhoan){
        this.view = khoanThuView;
        this.taiKhoan = taiKhoan;
        view.addControllerListener(this);
        initPagination();
        loadData(currentPage);
    }
    
    public void taoMoiActionPerformed(java.awt.event.ActionEvent evt){
        if(!taiKhoan.isAdmin()){
            Notifications.getInstance().show(Notifications.Type.WARNING, "Chỉ có ADMIN mới sử dụng được chức năng này!");
            return;
        }
        CreateKhoanThu create = new CreateKhoanThu();
        create.getController().loadData(service, null);
        DefaultOption option = new DefaultOption() {
            @Override
            public boolean closeWhenClickOutside() {
                return true;
            }
        };
        String actions[] = new String[]{"Thoát", "Đồng ý"};
        GlassPanePopup.showPopup(new SimplePopupBorder(create, "Thêm khoản thu", actions, (pc, i) -> {
            if (i == 1) { // Nếu người dùng nhấn "Save"
                try {
                    // Kiểm tra dữ liệu trước khi lưu
                    ModelKhoanThu data = create.getController().getData();
                    if (data == null || data.getLoaiKhoanThu() == null) {
                        Notifications.getInstance().show(Notifications.Type.WARNING, "Vui lòng nhập đủ thông tin khoản thu!");
                        return;
                    }

                    // Lưu dữ liệu
                    service.create(data);
                    if(create.getController().getRoomSelector() != null && create.getController().getRoomSelector().getSelectedCells() != null)
                        service.insertIntoChiuPhi(create.getController().getRoomSelector().getSelectedCells() , data.getID());
                    pc.closePopup();
                    Notifications.getInstance().show(Notifications.Type.SUCCESS, "Khoản thu mới đã được tạo");

                    // Tải lại dữ liệu sau khi tạo mới
                    loadData(currentPage); // Thêm để khi xóa toàn bộ bảng, tạo Khoản thu mới thì hiện trên giao diện
                    initPagination();
                } catch (Exception e) {
                    e.printStackTrace();
                    Notifications.getInstance().show(Notifications.Type.ERROR, "Đã có lỗi xảy ra trong quá trình tạo khoản thu!");
                }
            }else {
                // Nếu người dùng nhấn "Cancel"
                pc.closePopup();
            }
        }), option);
    }
    public void chinhSuaActionPerformed(java.awt.event.ActionEvent evt){
        if(!taiKhoan.isAdmin()){
            Notifications.getInstance().show(Notifications.Type.WARNING, "Chỉ có ADMIN mới sử dụng được chức năng này!");
            return;
        }
        List<ModelKhoanThu> list = getSelectedData();
        if (!list.isEmpty()) {
            if (list.size() == 1) {
                ModelKhoanThu data = list.get(0);
                CreateKhoanThu create = new CreateKhoanThu();
                create.getController().loadData(service, data);

                DefaultOption option = new DefaultOption() {
                    @Override
                    public boolean closeWhenClickOutside() {
                        return true;
                    }
                };
                String[] actions = new String[] {"Thoát", "Đồng ý"};

                GlassPanePopup.showPopup(new SimplePopupBorder(create, "Chỉnh sửa khoản thu [" + data.getLoaiKhoanThu() + "]", actions, (pc, i) -> {
                if (i == 1) { // Nếu người dùng chọn "Update"
                    try {
                        ModelKhoanThu dataEdit = create.getController().getData();

                        // Kiểm tra dữ liệu đầu vào, tránh null hoặc thiếu thông tin quan trọng
                        if (dataEdit == null || dataEdit.getLoaiKhoanThu() == null) {
                            Notifications.getInstance().show(Notifications.Type.WARNING, "Tên khoản thu không được để trống!");
                            return; // Dừng việc cập nhật nếu thông tin không hợp lệ
                        }

                        // Đặt lại khóa chính cho dữ liệu để cập nhật đúng bản ghi
                        dataEdit.setID(data.getID());

                        // Dừng chỉnh sửa ô nếu có
                        if (view.getTable().isEditing()) {
                            view.getTable().getCellEditor().stopCellEditing();
                        }

                        
                        service.edit(dataEdit);

                        pc.closePopup();
                        Notifications.getInstance().show(Notifications.Type.SUCCESS, "Khoản thu đã được cập nhật thành công!");

                        loadData(currentPage);
                        initPagination();

                    } catch (Exception e) {
                        e.printStackTrace();
                        Notifications.getInstance().show(Notifications.Type.ERROR, "Có lỗi xảy ra khi cập nhật khoản thu!");
                    }
                } else {
                pc.closePopup();
                }
            }), option);

            } else {
                Notifications.getInstance().show(Notifications.Type.WARNING, "Hãy chọn duy nhất một khoản thu để chỉnh sửa!");
            }
        } else {
            Notifications.getInstance().show(Notifications.Type.WARNING, "Hãy chọn khoản thu để chỉnh sửa!");
        }
    }    
    public void xoaActionPerformed(java.awt.event.ActionEvent evt){
        if(!taiKhoan.isAdmin()){
            Notifications.getInstance().show(Notifications.Type.WARNING, "Chỉ có ADMIN mới sử dụng được chức năng này!");
            return;
        }
        List<ModelKhoanThu> list = getSelectedData();
        if (!list.isEmpty()){
              DefaultOption option = new DefaultOption(){ // hien thi bang de minh edit
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
                        for(ModelKhoanThu d: list){
                            service.delete(d);
                        }
                        pc.closePopup();
                        Notifications.getInstance().show(Notifications.Type.SUCCESS, "Khoản thu đã được xoá");
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    loadData(1);
                }else{
                    pc.closePopup();
                }
            }),option);
        }else{
            Notifications.getInstance().show(Notifications.Type.WARNING, "Hãy chọn khoản thu để xóa!");
        }
    }
    
    public void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {                                      
        searchData(view.getTxtSearch().getText().trim());
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
            List<ModelKhoanThu> list = service.getPage(ModelKhoanThu.class, offset, recordsPerPage);

            // Thêm các dòng vào bảng
            for (ModelKhoanThu d : list) {
                model.addRow(d.toTableRow(view.getTable().getRowCount() + 1));  // Thêm dòng vào bảng
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void searchData(String search) {
        try {
            DefaultTableModel model = (DefaultTableModel) view.getTable().getModel();

            // Dừng chỉnh sửa nếu bảng đang ở trạng thái chỉnh sửa
            if (view.getTable().isEditing() && view.getTable().getCellEditor() != null) {
                view.getTable().getCellEditor().stopCellEditing();
            }

            // Xóa dữ liệu cũ trong bảng
            model.setRowCount(0);

            // Kiểm tra từ khóa tìm kiếm, nếu null hoặc rỗng, tải toàn bộ dữ liệu
            if (search == null || search.trim().isEmpty()) {
                search = null; // Tải toàn bộ dữ liệu
            }

            // Lấy danh sách dữ liệu
            List<ModelKhoanThu> list = service.getAll(ModelKhoanThu.class, search);

            // Kiểm tra và hiển thị dữ liệu
            if (list != null) {
                for (ModelKhoanThu d : list) {
                    model.addRow(d.toTableRow(view.getTable().getRowCount() + 1));
                }
            } else {
                JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu phù hợp!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi tìm kiếm dữ liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //Tạo đối tượng dựa vào hàng được tick
    public List<ModelKhoanThu> getSelectedData(){
        List<ModelKhoanThu> list = new ArrayList<>();
        for(int i = 0; i<view.getTable().getRowCount(); i++){
            if((boolean) view.getTable().getValueAt(i,0)){
                ModelKhoanThu data = (ModelKhoanThu)view.getTable().getValueAt(i, 2);
                list.add(data);
            }
        }
        return list;
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
        int totalCount = service.getTotalCount("khoan_thu");  // Lấy tổng số bản ghi từ cơ sở dữ liệu
                int recordsPerPage = 20;  // Số bản ghi trên mỗi trang
                int totalPages = (int) Math.ceil((double) totalCount / recordsPerPage);  // Tổng số trang

                view.getPagination1().setPagegination(1, totalPages);  // Cập nhật trang hiện tại và tổng số trang

                // Thêm phân trang vào giao diện
                view.getPanel().add(view.getPagination1());
                view.getPanel().revalidate();
                view.getPanel().repaint();
            }

    }
