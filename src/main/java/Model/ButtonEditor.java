package Model;

import View.NhanKhauView.NguoiTrongPhong;
import java.awt.Component;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 *
 * @author lamto
 */
public class ButtonEditor extends DefaultCellEditor {
    private final JButton button;
    
    public ButtonEditor(JCheckBox checkBox, JTable table) {
        super(checkBox);
        button = new JButton();
        button.setOpaque(true);
        // Đặt ActionListener cho nút
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();  // Dừng chế độ chỉnh sửa

                System.out.println("Button clicked!"); // Kiểm tra xem sự kiện có được kích hoạt không

                // Lấy giá trị số phòng từ dòng của JTable
                String soPhong = table.getValueAt(table.getSelectedRow(), 1).toString();

                // Mở frame NguoiTrongPhong
                NguoiTrongPhong nguoiTrongPhongFrame = new NguoiTrongPhong(soPhong);

                // Lấy kích thước của JFrame
                int frameWidth = nguoiTrongPhongFrame.getWidth();
                int frameHeight = nguoiTrongPhongFrame.getHeight();

                // Đặt vị trí của frame sao cho góc phải của frame trùng với vị trí con trỏ chuột
                Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
                int xPos = mouseLocation.x - frameWidth; // Vị trí x của góc phải của JFrame
                int yPos = mouseLocation.y; // Vị trí y của con trỏ chuột

                // Đặt vị trí cho JFrame và hiển thị nó
                nguoiTrongPhongFrame.setLocation(xPos, yPos);
                nguoiTrongPhongFrame.setVisible(true);
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