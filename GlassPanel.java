import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class GlassPanel extends JPanel {
    private Color backgroundColor = new Color(30, 30, 30, 220);
    private Color borderColor = new Color(60, 60, 60);
    private int cornerRadius = 12;
    
    public GlassPanel() {
      
        setOpaque(false);
   
        setPreferredSize(new Dimension(600, 400));
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
   
        RoundRectangle2D.Float shape = new RoundRectangle2D.Float(
                0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
        
     
        g2d.setColor(backgroundColor);
        g2d.fill(shape);
        
  
        g2d.setColor(borderColor);
        g2d.setStroke(new BasicStroke(1f));
        g2d.draw(shape);
        
        g2d.dispose();
    }
    

    public Color getBackgroundColor() {
        return backgroundColor;
    }
    
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        repaint();
    }
    
    public Color getBorderColor() {
        return borderColor;
    }
    
    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        repaint();
    }
    
    public int getCornerRadius() {
        return cornerRadius;
    }
    
    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
        repaint();
    }
}