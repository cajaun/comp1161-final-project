package util;


import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import java.awt.*;
import javax.swing.plaf.basic.ComboPopup;

public class CustomHeightComboBoxUI extends BasicComboBoxUI {
    private final int popupHeight;

    public CustomHeightComboBoxUI(int popupHeight) {
        this.popupHeight = popupHeight;
    }

    @Override
    protected ComboPopup createPopup() {
        BasicComboPopup popup = new BasicComboPopup(comboBox) {
            @Override
            public Dimension getPreferredSize() {
                Dimension d = super.getPreferredSize();
                d.height = popupHeight;
                return d;
            }
        };
        popup.getAccessibleContext().setAccessibleParent(comboBox);
        return popup;
    }
}
