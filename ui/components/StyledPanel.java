package ui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class StyledPanel extends JPanel {
    // Define default and themed background colors
    public static final Color SIDEBAR_BACKGROUND = Color.decode("#636B75");
    public static final Color MAIN_BACKGROUND = Color.decode("#2B2B2B");
    public static final Color WELCOME_BACKGROUND = Color.decode("#505D6A");


    private Color backgroundColor = WELCOME_BACKGROUND;
    private Color borderColor = new Color(60, 60, 60);
    private int cornerRadius = 12;

    public StyledPanel() {
        setOpaque(false);
        setPreferredSize(new Dimension(600, 400));
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    
  
        Rectangle2D.Float shape = new Rectangle2D.Float(
                0, 0, getWidth() - 1, getHeight() - 1);
    
        g2d.setColor(backgroundColor);
        g2d.fill(shape);
    
        g2d.setColor(borderColor);
        g2d.setStroke(new BasicStroke(1f));
        g2d.draw(shape);
    
        g2d.dispose();
    }
    


    public static StyledPanel createSidebarPanel() {
        StyledPanel panel = new StyledPanel();
        panel.setBackgroundColor(SIDEBAR_BACKGROUND);
        panel.setBorderColor(new Color(50, 50, 50));
        // panel.setCornerRadius(12);
        panel.setPreferredSize(new Dimension(200, 600));
        return panel;
    }


    public static StyledPanel createMainPanel() {
        StyledPanel panel = new StyledPanel();
        panel.setBackgroundColor(WELCOME_BACKGROUND);
        panel.setBorderColor(new Color(40, 40, 40));

        return panel;
    }

    public static StyledPanel createWelcomePanel() {
        StyledPanel panel = new StyledPanel();
        panel.setBackgroundColor(WELCOME_BACKGROUND);
        panel.setBorderColor(new Color(40, 40, 40));

        return panel;
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
