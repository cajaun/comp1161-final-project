package ui.panels.EditStudent;
import javax.swing.*;

import models.Grade;
import models.Student;
import ui.MainMenu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class EditStudentMenu {
    private JFrame frame;
    private JTextField idField, gradeField;
    private JComboBox<String> courseComboBox;
    private JButton saveButton, closeButton;
    private List<Student> students;

    public EditStudentMenu(MainMenu mainMenu, List<Student> students) {
        this.students = students;

        try {
            UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatDarkLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        frame = new JFrame("Edit Student");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(6, 2));

        frame.add(new JLabel("Student ID:"));
        idField = new JTextField();
        frame.add(idField);

        frame.add(new JLabel("course:"));
        courseComboBox = new JComboBox<>(new String[] { "Math", "Science", "History", "English" });
        frame.add(courseComboBox);

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
            String id = idField.getText();
            String course = (String) courseComboBox.getSelectedItem();
            String gradeText = gradeField.getText();

            if (id.isEmpty() || gradeText.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill all fields.");
                return;
            }

            try {
                double grade = Double.parseDouble(gradeText);

                Student student = findStudentById(id);

                if (student != null) {
                    Grade gradeToUpdate = student.getGradeByCourse(course);
                    if (gradeToUpdate != null) {
                        gradeToUpdate.setGrade(grade);
                        JOptionPane.showMessageDialog(frame, "Grade Updated!");
                    } else {
                        JOptionPane.showMessageDialog(frame, "course not found for the student.");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Student not found.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid grade.");
            }
        }
    }

    private Student findStudentById(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }
}
