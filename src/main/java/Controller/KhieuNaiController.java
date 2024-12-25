/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Database.Service;
import View.KhieuNaiView.CreateKhieuNai;
import View.KhieuNaiView.KhieuNaiPanel;
import Model.ModelKhieuNai;
import Model.ModelTaiKhoan;
import Model.Pagination.EventPagination;
import Model.Pagination.PaginationItemRenderStyle1;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import raven.popup.DefaultOption;
import raven.popup.GlassPanePopup;
import raven.popup.component.SimplePopupBorder;
import raven.toast.Notifications;

/**
 *
 * @author ADMIN
 */
public class KhieuNaiController {
    private KhieuNaiPanel view;
    private ModelTaiKhoan taiKhoan;
    private Service service = new Service();
    int currentPage = -1;

    public KhieuNaiController(KhieuNaiPanel view, ModelTaiKhoan taiKhoan) {
        this.view = view;
        this.taiKhoan = taiKhoan;
        view.addControllerListener(this);  // Gắn sự kiện cho các thành phần trong view
        initPagination();
        loadData(1, view.getComboLoai().getSelectedItem().toString());
    }
    public void initPagination() {
        // Sử dụng pagination1 thay vì pagination
        view.getPagination1().setPaginationItemRender(new PaginationItemRenderStyle1());

        // Lắng nghe sự kiện thay đổi trang
        view.getPagination1().addEventPagination(new EventPagination() {
            public void pageChanged(int page) {
                loadData(page, view.getComboLoai().getSelectedItem().toString()); // Tải dữ liệu khi trang thay đổi
            }
        });

        // Tính toán số trang
        int totalCount = service.getTotalCount("tai_khoan");  // Lấy tổng số bản ghi từ cơ sở dữ liệu
        int recordsPerPage = 10;  // Số bản ghi trên mỗi trang
        int totalPages = (int) Math.ceil((double) totalCount / recordsPerPage);  // Tổng số trang

        view.getPagination1().setPagegination(1, totalPages);  // Cập nhật trang hiện tại và tổng số trang

        // Thêm phân trang vào giao diện
        view.add(view.getPagination1());
        view.revalidate();
        view.repaint();
    }
    private void loadData(int page, String xetDuyet) {
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
            List<ModelKhieuNai> list = service.getPage(ModelKhieuNai.class, offset, recordsPerPage);

            // Thêm các dòng vào bảng
            for (ModelKhieuNai d : list) {
                if(d.getXetDuyet().equals(xetDuyet))
                model.addRow(d.toTableRow(view.getTable().getRowCount() + 1));  // Thêm dòng vào bảng
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<ModelKhieuNai> getSelectedData() {
        List<ModelKhieuNai> list = new ArrayList<>();

        for (int i = 0; i < view.getTable().getRowCount(); i++) {
            if ((boolean) view.getTable().getValueAt(i, 0)) {  // Kiểm tra trạng thái checkbox ở cột đầu tiên
                Object data = view.getTable().getValueAt(i, 2);  // Lấy giá trị ở cột thứ 1

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
        loadData(currentPage, view.getComboLoai().getSelectedItem().toString()); // Thêm để khi xóa toàn bộ bảng, tạo Khoản thu mới thì hiện trên giao diện
        initPagination();
    }
    
    public void taoMoiActionPerformed(java.awt.event.ActionEvent evt) {                                       
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
                    loadData(currentPage, view.getComboLoai().getSelectedItem().toString()); // Thêm để khi xóa toàn bộ bảng, tạo Khoản thu mới thì hiện trên giao diện
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
    
    public void comboActionPerformed(java.awt.event.ActionEvent evt){
        reload();
    }
}
