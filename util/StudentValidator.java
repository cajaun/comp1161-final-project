package util;

import javax.swing.*;
import ui.MainMenu;

public class StudentValidator {
    public static boolean validateStudentData(String id, MainMenu mainMenu) {
        if (mainMenu.isStudentIdTaken(id)) {
            JOptionPane.showMessageDialog(mainMenu.getFrame(), "This Student ID is already taken.");
            return false;
        }
        return true;
    }
}
