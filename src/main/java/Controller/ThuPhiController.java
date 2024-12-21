/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Database.Service;
import Model.ModelHoaDon;
import Model.ModelTaiKhoan;
import Model.ModelThuPhi;
import View.ThuPhiView.CreateThuPhi;
import Model.CreatePdf;
import View.ThuPhiView.ThuPhiPanel;
import com.itextpdf.text.DocumentException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import raven.popup.DefaultOption;
import raven.popup.GlassPanePopup;
import raven.popup.component.SimplePopupBorder;
import raven.toast.Notifications;

/**
 *
 * @author ADMIN
 */
public class ThuPhiController {
    private ThuPhiPanel view;
    private ModelTaiKhoan taiKhoan;
    private Service service = new Service();

    public ThuPhiController(ThuPhiPanel view, ModelTaiKhoan taiKhoan) {
        this.view = view;
        this.taiKhoan = taiKhoan;
        view.addControllerListener(this);
    }
    public void loadDataHoaDon(int thang, String tang){
        try {
            DefaultTableModel model = (DefaultTableModel) view.getTableHoaDon().getModel();
            model.setRowCount(0);  // Xóa dữ liệu cũ (nếu có)
            
            // Lấy và thêm dữ liệu mới từ cơ sở dữ liệu
            List<ModelHoaDon> list = service.loadForHoaDon(tang, thang);
            for (ModelHoaDon tp : list) {
                model.addRow(tp.toTableRow(view.getTableHoaDon().getRowCount() + 1));  // Thêm dòng dữ liệu mới vào bảng
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void loadDataThuPhi(String phong, int thang) {
        try {
            DefaultTableModel model = (DefaultTableModel) view.getTable().getModel();
            model.setRowCount(0);  // Xóa dữ liệu cũ (nếu có)
            
            // Lấy và thêm dữ liệu mới từ cơ sở dữ liệu
            List<ModelThuPhi> list = service.loadForThuPhi(phong, thang);
            for (ModelThuPhi tp : list) {
                model.addRow(tp.toTableRow(view.getTable().getRowCount() + 1));  // Thêm dòng dữ liệu mới vào bảng
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void traCuuCuTheActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // Lấy số phòng từ TextField và loại bỏ khoảng trắng
        String soPhong = view.getTxtSoPhong().getText().trim();

        // Kiểm tra nếu TextField trống
        if (soPhong.isEmpty()) {
            // Hiển thị thông báo lỗi cho người dùng
            javax.swing.JOptionPane.showMessageDialog(view, "Vui lòng nhập số phòng!", "Lỗi", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;  // Dừng thực hiện hành động nếu số phòng chưa được nhập
        }

        // Kiểm tra nếu tháng chưa được chọn trong ComboBox
        if (view.getjComboBoxThang().getSelectedItem() == null) {
            javax.swing.JOptionPane.showMessageDialog(view, "Vui lòng chọn tháng!", "Lỗi", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Lấy tháng từ ComboBox và chuyển sang số nguyên
            int thang = Integer.parseInt(view.getjComboBoxThang().getSelectedItem().toString());

            // Gọi loadDataThuPhi với số phòng và tháng đã chọn
            loadDataThuPhi(soPhong, thang);
        } catch (Exception e) {
            // Xử lý nếu có lỗi khi chuyển đổi tháng thành số
            javax.swing.JOptionPane.showMessageDialog(view, "Vui lòng nhập dữ liệu hợp lệ!", "Lỗi", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
    }   
    public void traCuuActionPerformed(java.awt.event.ActionEvent evt) {                                       
        try {
            String tang = (String) view.getFloor().getSelectedItem();
            int thang = Integer.parseInt(view.getMonth().getSelectedItem().toString());
            if (tang.equals("Tất cả")) {
                loadDataHoaDon(thang, null);
                return;
            }
            loadDataHoaDon(thang, tang);
        } catch (Exception e) {
            Notifications.getInstance().show(Notifications.Type.WARNING, "Có lỗi xảy ra");
            return;
        }
    }   
    public void xacNhanActionPerformed(java.awt.event.ActionEvent evt) {                                        
        if(taiKhoan.isAdmin() == false){
            Notifications.getInstance().show(Notifications.Type.WARNING, "Chỉ ADMIN mới có quyền thu phí!");
            return;
        }
        ModelHoaDon startData;
        try {
            startData = service.loadForHoaDon(view.getTxtSoPhong().getText(), Integer.parseInt(view.getjComboBoxThang().getSelectedItem().toString())).getFirst();
        } catch (SQLException ex) {
            Logger.getLogger(ThuPhiPanel.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        CreateThuPhi create = new CreateThuPhi(startData);
        DefaultOption option = new DefaultOption() {
            @Override
            public boolean closeWhenClickOutside() {
                return true;
            }
        };
        String actions[] = new String[]{"Xác nhận"};
        GlassPanePopup.showPopup(new SimplePopupBorder(create, "Xác nhận đã đóng", actions, (pc, i) -> {
            if (i == 0) { // Nếu người dùng nhấn "Save"
                try {
                    if (startData == null) {
                        Notifications.getInstance().show(Notifications.Type.WARNING, "Vui lòng nhập đủ thông tin khoản thu!");
                        return;
                    }
                    
                    if (startData.isDaDong()){
                        Notifications.getInstance().show(Notifications.Type.WARNING, "Khoản này đã được thu rồi!");
                        return;
                    }
                    
                    ModelHoaDon newHoaDon = create.getData();
                    service.edit(newHoaDon);
                    pc.closePopup();
                    Notifications.getInstance().show(Notifications.Type.SUCCESS, "Xác nhận đã thu phí tháng " + newHoaDon.getThang() + " thành công cho phòng " + newHoaDon.getSoPhong());
                } catch (Exception e) {
                    e.printStackTrace();
                    Notifications.getInstance().show(Notifications.Type.ERROR, "Đã có lỗi xảy ra trong quá trình tạo khoản thu!");
                }
            } else {
                // Nếu người dùng nhấn "Cancel"
                pc.closePopup();
            }
        }), option);
    }   
    public void xuatBienLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // Lấy giá trị từ jTextField1 và chuyển thành String
        String soPhong = view.getTxtSoPhong().getText();
        try {
            CreatePdf.create(soPhong, view.getTable());
        } catch (IOException ex) {
            Logger.getLogger(ThuPhiPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(ThuPhiPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
