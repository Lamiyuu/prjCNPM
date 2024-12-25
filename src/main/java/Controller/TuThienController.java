/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Database.Service;
import Model.ModelKhoanThu;
import Model.ModelTaiKhoan;
import Model.ModelThuPhi;
import View.TuThienView.CreateTuThien;
import View.TuThienView.TuThienPanel;
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

/**
 *
 * @author ADMIN
 */
public class TuThienController {
    private TuThienPanel view;
    private ModelTaiKhoan taiKhoan;
    private Service service = new Service();

    public TuThienController(TuThienPanel tuThienView, ModelTaiKhoan taiKhoan) {
        this.view = tuThienView;
        this.taiKhoan = taiKhoan;
        view.addControllerListener(this);  // Gắn sự kiện cho các thành phần trong view
        if(view.getComboBoxThang().getSelectedItem()!=null && view.getComboBox().getSelectedItem()!=null) 
            loadData(Integer.parseInt(view.getComboBoxThang().getSelectedItem().toString()), ((ModelKhoanThu) view.getComboBox().getSelectedItem()).getID());
    }
    
    public List<ModelThuPhi> getSelectedData(){
        List<ModelThuPhi> list = new ArrayList<>();
        for(int i = 0; i<view.getTable().getRowCount(); i++){
            if((boolean) view.getTable().getValueAt(i,0)){
                ModelThuPhi data = (ModelThuPhi)view.getTable().getValueAt(i, 5);
                list.add(data);
            }
        }
        return list;
    }
    
    public void taoMoiActionPerformed(java.awt.event.ActionEvent evt) {
        if (view.getComboBox().getSelectedItem() == null || view.getComboBoxThang().getSelectedItem() == null){
            Notifications.getInstance().show(Notifications.Type.ERROR, "Hãy chọn đủ tháng và loại khoản từ thiện để tạo!");
            return;
        }
        CreateTuThien create = new CreateTuThien((ModelKhoanThu) view.getComboBox().getSelectedItem(), Integer.parseInt(view.getComboBoxThang().getSelectedItem().toString()));
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
                    loadData(Integer.parseInt(view.getComboBoxThang().getSelectedItem().toString()), ((ModelKhoanThu) view.getComboBox().getSelectedItem()).getID());
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
    }
    
    public void traCuuActionPerformed(java.awt.event.ActionEvent evt) {                                             
        if (view.getComboBox().getSelectedItem() == null || view.getComboBoxThang().getSelectedItem() == null){
            Notifications.getInstance().show(Notifications.Type.ERROR, "Hãy chọn đủ tháng và loại khoản từ thiện để tạo!");
            return;
        }
        int thang = Integer.parseInt(view.getComboBoxThang().getSelectedItem().toString());
        ModelKhoanThu khoanThu = (ModelKhoanThu) view.getComboBox().getSelectedItem();
        String IDKhoanThu = khoanThu.getID();
        loadData(thang, IDKhoanThu);
    }        

    public void xoaActionPerformed(java.awt.event.ActionEvent evt) {                                          
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
                    loadData(Integer.parseInt(view.getComboBoxThang().getSelectedItem().toString()), ((ModelKhoanThu) view.getComboBox().getSelectedItem()).getID());
                }else{
                    pc.closePopup();
                }
            }),option);
        }else{
            Notifications.getInstance().show(Notifications.Type.WARNING, "Hãy chọn khoản thu để xóa!");
        }
    }      
    
    public void comboBoxThangActionPerformed(java.awt.event.ActionEvent evt) {                                              
        
        int thang = Integer.parseInt(view.getComboBoxThang().getSelectedItem().toString()); 
        try {
            view.getComboBox().removeAllItems();
            List<ModelKhoanThu> listKhoanThu = service.getAll(ModelKhoanThu.class, "Tự nguyện"); 
            if (listKhoanThu != null) {
                for (ModelKhoanThu pos : listKhoanThu) {
                    int thangBatDau = pos.getNgayBatDauThu().getMonth() + 1;
                    int thangKetThuc = pos.getNgayKetThuc().getMonth() + 1;
                    if (thangKetThuc >= thang && thangBatDau <= thang) {
                        view.getComboBox().addItem(pos); 
                    }
                }
            } else {
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi tải danh sách loại khoản thu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }      

    private void loadData(int thang, String IDKhoanThu){
        try {
            DefaultTableModel model = (DefaultTableModel) view.getTable().getModel();
            model.setRowCount(0);  // Xóa dữ liệu cũ (nếu có)
            // Lấy và thêm dữ liệu mới từ cơ sở dữ liệu
            List<ModelThuPhi> list = service.loadForTuThien(IDKhoanThu, thang);
            for (ModelThuPhi tp : list) {
                model.addRow(tp.toTableRowCharity(view.getTable().getRowCount() + 1));  // Thêm dòng dữ liệu mới vào bảng
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
