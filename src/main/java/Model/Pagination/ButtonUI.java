/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Pagination;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
/**
 *
 * @author lamto
 */
public class ButtonUI extends BasicButtonUI {
    private boolean hover;
    private JButton button;

    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
        button = (JButton) c;
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hover = true;
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hover = false;
            }
        });
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setOpaque(false);
        button.setForeground(Color.WHITE);
        button.setBackground(Color.decode("#007991"));
        button.setBorder(new EmptyBorder(5, 10, 5, 10));
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = c.getWidth();
        int height = c.getHeight();

        // Xác định màu nền
        if (hover) {
            g2.setColor(new Color(0, 121, 145)); // Màu khi di chuột
        } else if (button.isSelected()) {
            g2.setColor(new Color(0, 105, 135)); // Màu khi nút được chọn
        } else {
            g2.setColor(new Color(220, 220, 220)); // Màu nổi bật hơn cho các nút không chọn
        }

        // Vẽ hình nền với bo góc
        Shape shape = new RoundRectangle2D.Double(0, 0, width, height, 10, 10);
        g2.fill(shape);

        g2.dispose();
        super.paint(g, c); // Vẽ chữ và các thành phần khác
    }
}
