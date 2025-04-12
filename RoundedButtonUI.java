import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class RoundedButtonUI extends BasicButtonUI {
    @Override
    public void installDefaults(AbstractButton b) {
        super.installDefaults(b);
        b.setOpaque(false);
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        AbstractButton b = (AbstractButton) c;
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Background
        if (b.getModel().isPressed()) {
            g2.setColor(b.getBackground().darker());
        } else {
            g2.setColor(b.getBackground());
        }
        g2.fillRoundRect(0, 0, b.getWidth(), b.getHeight(), 20, 20); 

        // Text
        g2.setColor(b.getForeground());
        FontMetrics fm = g2.getFontMetrics();
        int textX = 14;
        int textY = (b.getHeight() + fm.getAscent() - fm.getDescent()) / 2;
        g2.drawString(b.getText(), textX, textY);

        g2.dispose();
    }
}
