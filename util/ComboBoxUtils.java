package util;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class ComboBoxUtils {
    public static void makeSearchable(JComboBox<String> comboBox, List<String> fullList) {
        JTextComponent editor = (JTextComponent) comboBox.getEditor().getEditorComponent();
        editor.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String input = editor.getText();
                comboBox.removeAllItems();
                for (String item : fullList) {
                    if (item.toLowerCase().contains(input.toLowerCase())) {
                        comboBox.addItem(item);
                    }
                }
                editor.setText(input);
                comboBox.showPopup();
            }
        });
    }
}
