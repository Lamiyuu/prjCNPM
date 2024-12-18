 package Model;

import java.awt.Component;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;
import javax.swing.JLabel;
import javax.swing.JTable;

public class TableHeaderAlignment implements TableCellRenderer {
    private final TableCellRenderer oldHeaderRenderer;
    private final TableCellRenderer oldCellRenderer;

    public TableHeaderAlignment(JTable table) {
        this.oldHeaderRenderer = table.getTableHeader().getDefaultRenderer();
        this.oldCellRenderer = table.getDefaultRenderer(Object.class);
        table.setDefaultRenderer(Object.class, new TableCellRenderer(){
        @Override
        public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int row, int column){
            JLabel label = (JLabel) oldCellRenderer.getTableCellRendererComponent(jtable, o, bln, bln1, row, column);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            return label;
        }
    });
    }

    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int row, int column) {
        JLabel label = (JLabel) oldHeaderRenderer.getTableCellRendererComponent(jtable, o, bln, bln1, row, column);
        label.setHorizontalAlignment(getAlignment(column));
        return label;
    }

    public int getAlignment(int column) {
        
        return SwingConstants.CENTER; // Default alignment for other columns
    }
}
