/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Database.DatabaseConnection;
import Database.Service;
import Model.ModelKhoanThu;
import Model.ModelLoaiKhoanThu;
import Model.ModelTaiKhoan;
import View.KhoanThuView.CreateKhoanThu;
import View.KhoanThuView.RoomSelector;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class CreateKhoanThuController {
    private final CreateKhoanThu view;
    private final Service service = new Service();
    private int currentPage = -1;
    private RoomSelector roomSelector;
    private boolean danhRieng = false;

    public RoomSelector getRoomSelector() {
        return roomSelector;
    }

    public CreateKhoanThuController(CreateKhoanThu view) {
        this.view = view;
        view.addControllerListener(this);
    }
    
    //load các loại khoản thu vào combobox
    public void loadData(Service service, ModelKhoanThu data) {
        try {
            // Lấy danh sách loại khoản thu
            List<ModelLoaiKhoanThu> listLoaiKhoanThu = service.getAll(ModelLoaiKhoanThu.class, null);

            // Kiểm tra danh sách không null
            if (listLoaiKhoanThu != null) {
                for (ModelLoaiKhoanThu pos : listLoaiKhoanThu) {
                    view.getComboBox().addItem(pos);

                    // Chọn mục phù hợp nếu có dữ liệu
                    if (data != null && data.getLoaiKhoanThu() != null && data.getLoaiKhoanThu().getID() == pos.getID()) {
                        view.getComboBox().setSelectedItem(pos);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi tải danh sách loại khoản thu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        // Nếu có dữ liệu, thiết lập các trường
        if (data != null) {
            view.getTxtSoTienThu().setValue(data.getSoTienThu());
            view.getTxtName().setText(data.getTenKhoanThu());
            // Định dạng ngày
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            if (data.getNgayBatDauThu() != null) {
                view.getTxtNgayBatDauThu().setText(dateFormat.format(data.getNgayBatDauThu()));
            } else {
                view.getTxtNgayBatDauThu().setText("");
            }
            if (data.getNgayKetThuc() != null) {
                view.getTxtNgayKetThuc().setText(dateFormat.format(data.getNgayKetThuc()));
            } else {
                view.getTxtNgayKetThuc().setText("");
            }
            view.getTxtMoTa().setText(data.getMoTa());
        }
    }
    
    //Chuyển dữ liệu thành đối tượng ModelKhoanThu
    public ModelKhoanThu getData() {
        // Kiểm tra tên khoản thu không thể trống
        if (view.getTxtName().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Tên khoản thu không thể trống!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        // Kiểm tra số tiền thu không thể trống và lớn hơn 0
        if (view.getTxtName().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Số tiền thu không thể trống!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        int soTienThu;
        try {
            // Kiểm tra nếu không phải là số hợp lệ
            soTienThu = Integer.parseInt(view.getTxtSoTienThu().getText().trim().replace(",", ""));
            if (soTienThu <= 0) {
                JOptionPane.showMessageDialog(view, "Số tiền thu phải lớn hơn 0!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return null;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Số tiền thu không hợp lệ!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        // Kiểm tra loại khoản thu
        Object selectedItem = view.getComboBox().getSelectedItem();
        ModelLoaiKhoanThu loaiKhoanThu = null;
        if (selectedItem instanceof ModelLoaiKhoanThu) {
            loaiKhoanThu = (ModelLoaiKhoanThu) selectedItem;
        } else if (selectedItem instanceof String) {
            loaiKhoanThu = searchLoaiKhoanThuFromDatabase((String) selectedItem);
            if (loaiKhoanThu == null) {
                JOptionPane.showMessageDialog(view, "Không tìm thấy loại khoản thu!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return null;
            }
        }

        // Lấy dữ liệu ngày bắt đầu và ngày kết thúc
        Date sqlDate = Date.valueOf("1990-01-01");
        Date ngayBatDauThu = view.getDatePicker().isDateSelected() ? Date.valueOf(view.getDatePicker().getSelectedDate()) : sqlDate;
        Date ngayKetThuc = view.getDatePicker1().isDateSelected() ? Date.valueOf(view.getDatePicker1().getSelectedDate()) : sqlDate;

        // Kiểm tra ngày kết thúc phải sau ngày bắt đầu
        if (ngayKetThuc.before(ngayBatDauThu)) {
            JOptionPane.showMessageDialog(view, "Ngày kết thúc phải sau ngày bắt đầu!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        // Lấy dữ liệu mô tả và đơn vị
        String tenKhoanThu = view.getTxtName().getText().trim();
        String moTa = view.getTxtMoTa().getText().trim();
        String donVi = view.getTxtDonVi().getSelectedItem().toString();

        // Tạo và trả về đối tượng ModelKhoanThu
        return new ModelKhoanThu(UUID.randomUUID().toString(), tenKhoanThu, loaiKhoanThu, ngayBatDauThu, soTienThu, ngayKetThuc, moTa, donVi, danhRieng);
    }
    
    private ModelLoaiKhoanThu searchLoaiKhoanThuFromDatabase(String tenKhoanThu) {
        Connection con = null;
        PreparedStatement p = null;
        ResultSet rs = null;
        try {
            // Sử dụng phương thức getConnection() để lấy kết nối
            con = DatabaseConnection.getConnection();

            // Câu truy vấn SQL
            String query = "SELECT * FROM `loai_khoan_thu` WHERE `tenKhoanThu_Name` = ?";

            // Chuẩn bị câu truy vấn
            p = con.prepareStatement(query);
            p.setString(1, tenKhoanThu);  // Thiết lập giá trị tham số trong câu truy vấn

            // Thực thi truy vấn
            rs = p.executeQuery();

            // Nếu có kết quả trả về
            if (rs.next()) {
                // Giả sử ModelLoaiKhoanThu có constructor nhận id và name
                return new ModelLoaiKhoanThu(rs.getString("tenKhoanThu_ID"), rs.getString("tenKhoanThu_Name"));
            }
        } catch (SQLException ex) {
            // Nếu có lỗi xảy ra trong quá trình truy vấn
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi truy vấn cơ sở dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Đảm bảo đóng ResultSet, PreparedStatement, và Connection trong khối finally
            try {
                if (rs != null) rs.close();
                if (p != null) p.close();
                DatabaseConnection.closeConnection(con);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return null; // Không tìm thấy loại khoản thu
    } 
    
    public void buttonPhamViActionPerformed(ActionEvent evt) {
        danhRieng = true;
        // tạo một instance của RoomSelector
        if(roomSelector == null){
            roomSelector = new RoomSelector();
        }
        System.out.println("Hello");
        // hiển thị JFrame
        roomSelector.setVisible(true);
    }

    
}
