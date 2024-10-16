//Lỗi, chưa dám import
package HomeFramePackage.TablePackage;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JLabel;


public class TableStatus extends JLabel {
    private StatusType type;
    
    public StatusType getType(){
        return type;
    }
    
    public TableStatus(){
        setForeground(Color.WHITE);
    }
    
    public void setType(StatusType type){
        this.type = type;
        setText(type.toString());
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics grphcs){
        if(type!=null){
            Graphics2D grphcs2d = (Graphics2D) grphcs;
        //Tạo màu gradient
            grphcs2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            GradientPaint grd;
            if(null == type){
                grd = new GradientPaint(0, 0 ,Color.decode("#ff9966"), 0 ,getHeight(), Color.decode("#ff5e62"));
            }else grd = switch (type) {
                case CHARITY -> new GradientPaint(0, 0 ,Color.decode("#654ea3"), 0 ,getHeight(), Color.decode("#eaafc8"));
                case VEHICLE -> new GradientPaint(0, 0 ,Color.decode("#4e54c8"), 0 ,getHeight(), Color.decode("#8f94fb"));
                default -> new GradientPaint(0, 0 ,Color.decode("#ff9966"), 0 ,getHeight(), Color.decode("#ff5e62"));
            };
            grphcs2d.setPaint(grd);
        //Làm mềm cạnh ô vuông
            grphcs2d.fillRoundRect(0, 0, getWidth(), getHeight(), 0, 0);

        }
        super.paintComponent(grphcs);
    }
    
}
