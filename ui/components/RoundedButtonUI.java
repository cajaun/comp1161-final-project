package ui.components;

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
        } else if (b.getModel().isRollover()) {
            g2.setColor(b.getBackground().brighter());
        } else {
            g2.setColor(b.getBackground());
        }

        int arc = 16;
        g2.fillRoundRect(0, 0, b.getWidth(), b.getHeight(), arc, arc);

        // Text
        g2.setColor(b.getForeground());
        FontMetrics fm = g2.getFontMetrics();
        String text = b.getText();

        int textWidth = fm.stringWidth(text);
        int textX = (b.getWidth() - textWidth) / 2;
        int textY = (b.getHeight() + fm.getAscent() - fm.getDescent()) / 2;

        g2.drawString(text, textX, textY);

        g2.dispose();
    }
}
