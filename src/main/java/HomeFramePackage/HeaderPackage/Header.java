// Định làm 1 thanh tìm kiếm to tổ chảng ở đỉnh nhma chưa biết có dùng ko nên chưa làm xong và chưa import vào clq j hết
package HomeFramePackage.HeaderPackage;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Header extends javax.swing.JPanel {

    
    public Header() {
        initComponents();
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics grphcs){
        Graphics2D grphcs2d = (Graphics2D) grphcs;
        grphcs2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        grphcs2d.setColor(getBackground());
        grphcs2d.fillRoundRect(0, 0, getWidth(), getHeight(), 0, 0);
        grphcs2d.fillRect(0,0,25,getHeight());
        grphcs2d.fillRect(getWidth()-25,getHeight()-25,getWidth(),getHeight());
        
        super.paintComponent(grphcs);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
