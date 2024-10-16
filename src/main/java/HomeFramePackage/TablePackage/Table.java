//Cái table này là 1 đối tượng bảng đc tạo thủ công, Tú đã kéo nó vào Home r edit chi tiết (vd tên cột,...) ở phần design của Home.java. 
//Thực ra có thể kéo 3-4 lần file này vào desgin của home để đc 3-4 bảng khác nhau, nhma lười ý :)))


package HomeFramePackage.TablePackage;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Table extends JTable {
    
    public Table(){
        setShowHorizontalLines(true);
        setRowHeight(40);
        setGridColor(new Color(230,230,230,230));
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer(){ 
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1){
                TableHeader header = new TableHeader(o+"");
                if(i1 == 4){
                    header.setHorizontalAlignment(JLabel.CENTER);
                }
                return header;
            }
        });
        
        setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean selected, boolean bln1, int i, int i1){ //i là chỉ số hàng của component (ô) đó, i1 là số cột, từ 0-...
                
                    Component com = super.getTableCellRendererComponent(jtable, o, selected, bln1, i, i1);
                    com.setBackground(Color.WHITE);
                    setBorder(noFocusBorder);
                    if(selected){
                        com.setForeground(new Color(13,113,82));//đổi màu hàng khi đc chọn
                    }else{
                        com.setForeground(new Color(102,102,102));
                    }
                    return com;
                
                
            }
        
        });
    }
    
    public void addRow(Object[] row){
        DefaultTableModel model = (DefaultTableModel) getModel();
        model.addRow(row);
    }
        
    
}
