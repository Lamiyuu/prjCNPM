/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Database.Service;
import View.KhieuNaiView.CheckKhieuNai;
import View.KhieuNaiView.KhieuNaiPanel;
import View.NhanKhauView.NguoiTrongPhong;
import java.awt.Component;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import raven.popup.DefaultOption;
import raven.popup.GlassPanePopup;
import raven.popup.component.SimplePopupBorder;
import raven.toast.Notifications;

/**
 *
 * @author ADMIN
 */
public class ButtonEditorKhieuNai extends DefaultCellEditor {
    private final JButton button;
    private Service service = new Service();
    private KhieuNaiPanel panel;
    private ModelTaiKhoan taiKhoan;
    public ButtonEditorKhieuNai(JCheckBox checkBox, JTable table, KhieuNaiPanel panel, ModelTaiKhoan taiKhoan) {
        super(checkBox);
        this.panel = panel;
        button = new JButton();
        button.setOpaque(true);
        // Đặt ActionListener cho nút
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();  // Dừng chế độ chỉnh sửa

                System.out.println("Button clicked!"); // Kiểm tra xem sự kiện có được kích hoạt không

                // Lấy giá trị số phòng từ dòng của JTable
                ModelKhieuNai khieuNai = (ModelKhieuNai) table.getValueAt(table.getSelectedRow(), 2);

                // Mở frame NguoiTrongPhong
                CheckKhieuNai checkKhieuNai = new CheckKhieuNai(khieuNai);
                //checkKhieuNai.loadData(currentNhanKhau);
                // Tạo một cửa sổ Popup để chỉnh sửa
                DefaultOption option = new DefaultOption() {
                    @Override
                    public boolean closeWhenClickOutside() {
                        return true;
                    }
                };
                String actions[];
                if (taiKhoan.isAdmin()) {
                    actions = new String[]{"Thoát", "Duyệt", "Từ chối"};
                } else {
                    actions = new String[]{"Thoát"};
                }
                // Hiển thị Popup và xử lý sự kiện khi nhấn nút Save hoặc Cancel

                GlassPanePopup.showPopup(new SimplePopupBorder(checkKhieuNai, "Phản hồi khiếu nại", actions, (pc, i) -> {
                    if (i == 2){  // Nếu nhấn Save
                        try {
                            
                            ModelKhieuNai updatedKhieuNai = checkKhieuNai.getData();  // Truyền thong tin hiện tại vào form để lấy dữ liệu cập nhật
                            updatedKhieuNai.setID(khieuNai.getID());
                            updatedKhieuNai.setXetDuyet("Từ chối");
                            service.edit(updatedKhieuNai);  // Gọi phương thức edit trong ServiceTaiKhoan để cập nhật tài khoản
                            panel.getController().reload();
                            // Đóng Popup và thông báo thành công
                            pc.closePopup();
                            Notifications.getInstance().show(Notifications.Type.SUCCESS, "Gửi thành công");
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(new JPanel(), "Đã xảy ra lỗi khi chỉnh sửa tài khoản!");
                        }
                    } else if (i == 1){
                        try {
                            
                            ModelKhieuNai updatedKhieuNai = checkKhieuNai.getData();  // Truyền thong tin hiện tại vào form để lấy dữ liệu cập nhật
                            updatedKhieuNai.setID(khieuNai.getID());
                            updatedKhieuNai.setXetDuyet("Đã duyệt");
                            service.edit(updatedKhieuNai);  // Gọi phương thức edit trong ServiceTaiKhoan để cập nhật tài khoản
                            panel.getController().reload();
                            // Đóng Popup và thông báo thành công
                            pc.closePopup();
                            Notifications.getInstance().show(Notifications.Type.SUCCESS, "Gửi thành công");
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(new JPanel(), "Đã xảy ra lỗi khi chỉnh sửa tài khoản!");
                        }
                    }
                    else {  // Nếu nhấn Cancel
                        pc.closePopup();
                    }
                }), option);
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return button.getText();
    }

    @Override
    public boolean stopCellEditing() {
        return super.stopCellEditing();
    }

    @Override
    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}
