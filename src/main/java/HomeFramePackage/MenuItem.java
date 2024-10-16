
package HomeFramePackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;


public class MenuItem extends javax.swing.JPanel {
 
    private boolean selected;
    private MenuModel data;
    
    public MenuItem(MenuModel data) {
        initComponents();
        this.data=data;
        setOpaque(false);
        if(null==data.getType()){
            menuItem.setText(" ");
        }else switch (data.getType()) {
            case MENU -> {
                Icon.setIcon(data.toIcon());
                menuItem.setText(data.getName());
            }
            case TITLE -> {
                Icon.setText(data.getName());
                Icon.setFont(new Font("Verdana",1,12));
                menuItem.setVisible(false);
            }
            default -> menuItem.setText(" ");
        }
    }
    
    public void setSelected(boolean s){
        this.selected = s;
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics grphcs){
        if(selected){
        Graphics2D grphcs2d = (Graphics2D) grphcs;
        grphcs2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        grphcs2d.setColor(new Color(255,255,255,80));
        grphcs2d.fillRoundRect(0, 0, getWidth()-20, getHeight(), 0, 0);
        }
        super.paintComponent(grphcs);
        
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuItem = new javax.swing.JLabel();
        Icon = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(201, 35));

        menuItem.setBackground(new java.awt.Color(255, 255, 255));
        menuItem.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        menuItem.setForeground(new java.awt.Color(255, 255, 255));
        menuItem.setText("MenuItem");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(Icon, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(menuItem)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Icon, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
            .addComponent(menuItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Icon;
    private javax.swing.JLabel menuItem;
    // End of variables declaration//GEN-END:variables
}
