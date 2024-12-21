package Model;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ButtonRenderer extends JButton implements TableCellRenderer {

    private ImageIcon icon;

    public ButtonRenderer() {
        setOpaque(true);
        String imagePath = "/images/eye.png";
        ImageIcon tempIcon = new ImageIcon(getClass().getResource(imagePath));
        Image image = tempIcon.getImage();
        icon = new ImageIcon(image);
        setIcon(icon);
        setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
        setContentAreaFilled(false);
        setBorder(BorderFactory.createEmptyBorder());
        setBackground(Color.WHITE);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(Color.LIGHT_GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(Color.WHITE);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                setBackground(Color.GRAY);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setBackground(Color.LIGHT_GRAY);
            }
        });
    }

    @Override
    public void paintComponent(Graphics grphcs){
        Graphics2D grphcs2d = (Graphics2D) grphcs;
        //Tạo màu gradient
        grphcs2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        grphcs2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        GradientPaint grd = new GradientPaint(0, 0, Color.WHITE, 0 ,getWidth(), Color.WHITE);
        grphcs2d.setPaint(grd);
        //Làm mềm cạnh ô vuông
        grphcs2d.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
        super.paintComponent(grphcs);
    }


    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        if (isSelected) {
            setBackground(Color.GREEN);
            setForeground(Color.BLACK);
        } else {
            setBackground(Color.WHITE);
            setForeground(Color.BLACK);
        }

        return this;
    }
}
