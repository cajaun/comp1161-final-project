import javax.swing.*;

import com.formdev.flatlaf.FlatDarkLaf;

import java.awt.*;
import java.awt.event.ActionEvent;

public class AddStudentMenu {
    private JFrame frame;
    private JTextField firstNameField, lastNameField, idField, gradeField;
    private JComboBox<String> subjectComboBox;
    private JButton saveButton, closeButton;
    private MainMenu mainMenu;

    public AddStudentMenu(MainMenu mainMenu, Student[] students) {
        this.mainMenu = mainMenu;

        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        frame = new JFrame("Add Student");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(6, 2));

        frame.add(new JLabel("First Name:"));
        firstNameField = new JTextField();
        frame.add(firstNameField);

        frame.add(new JLabel("Last Name:"));
        lastNameField = new JTextField();
        frame.add(lastNameField);

        frame.add(new JLabel("Student ID:"));
        idField = new JTextField();
        frame.add(idField);

        frame.add(new JLabel("Subject:"));
        subjectComboBox = new JComboBox<>(new String[]{"Math", "Science", "History", "English"});
        frame.add(subjectComboBox);

        frame.add(new JLabel("Grade:"));
        gradeField = new JTextField();
        frame.add(gradeField);

        saveButton = new JButton("Save");
        saveButton.addActionListener(new SaveButtonListener());
        frame.add(saveButton);

        closeButton = new JButton("Close");
        closeButton.addActionListener(e -> {
            frame.setVisible(false);
            mainMenu.showMainMenu();
        });
        frame.add(closeButton);

        frame.setVisible(true);
    }

    private class SaveButtonListener implements java.awt.event.ActionListener {
        public void actionPerformed(ActionEvent e) {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String id = idField.getText();
            String subject = (String) subjectComboBox.getSelectedItem();
            double grade = Double.parseDouble(gradeField.getText());

            Grade newGrade = new Grade(subject, grade);
            Student newStudent = new Student(firstName, lastName, id);
            newStudent.addGrade(newGrade);

            mainMenu.addStudent(newStudent);
            JOptionPane.showMessageDialog(frame, "Student Saved!");
            frame.setVisible(false);
            mainMenu.showMainMenu();
        }
    }
}
