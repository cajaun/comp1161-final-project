package ui.panels.AddStudent;

import javax.swing.*;
import config.Constants;
import util.ComboBoxUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StudentFormBuilder {
    private JTextField firstNameField, lastNameField, idField, programField, enrollmentYearField;
    private JComboBox<String> facultyComboBox;
    private List<JComboBox<String>> courseComboBoxes = new ArrayList<>();
    private List<JComboBox<String>> gradeComboBoxes = new ArrayList<>();

    public JPanel buildForm(List<String> courseList) {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setOpaque(false);


        mainPanel.add(createSectionLabel("Student Information"));
        mainPanel.add(wrapWithRoundedBorder(createInfoPanel()));
        mainPanel.add(Box.createVerticalStrut(20));

    
        mainPanel.add(createSectionLabel("Courses & Grades"));
        mainPanel.add(wrapWithRoundedBorder(createCoursePanel(courseList)));

        return mainPanel;
    }

    private JLabel createSectionLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(label.getFont().deriveFont(Font.BOLD, 14f));
        label.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 5));
        return label;
    }

    private JPanel wrapWithRoundedBorder(JPanel inner) {
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180), 1, true),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        wrapper.setOpaque(false);
        wrapper.add(inner, BorderLayout.CENTER);
        return wrapper;
    }

    private JPanel createInfoPanel() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setOpaque(false);

        panel.add(new JLabel("Student ID:"));
        idField = new JTextField(15);
        panel.add(idField);

        panel.add(new JLabel("Faculty:"));
        facultyComboBox = new JComboBox<>(Constants.FACULTIES);
        panel.add(facultyComboBox);

        panel.add(new JLabel("First Name:"));
        firstNameField = new JTextField(15);
        panel.add(firstNameField);

        panel.add(new JLabel("Last Name:"));
        lastNameField = new JTextField(15);
        panel.add(lastNameField);

        panel.add(new JLabel("Program:"));
        programField = new JTextField(15);
        panel.add(programField);

        panel.add(new JLabel("Enrollment Year:"));
        enrollmentYearField = new JTextField(15);
        panel.add(enrollmentYearField);

        return panel;
    }

    private JPanel createCoursePanel(List<String> courseList) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        panel.add(new JLabel("Courses & Letter Grades (min 3):"), gbc);
        gbc.gridwidth = 1;

        for (int i = 0; i < 6; i++) {
            gbc.gridy++;
            gbc.gridx = 0;
            panel.add(new JLabel("Course " + (i + 1) + ":"), gbc);

            gbc.gridx = 1;
            JComboBox<String> courseComboBox = new JComboBox<>(courseList.toArray(new String[0]));
            courseComboBox.setEditable(true);
            courseComboBox.setSelectedItem(null);
            ComboBoxUtils.makeSearchable(courseComboBox, courseList);
            courseComboBoxes.add(courseComboBox);
            panel.add(courseComboBox, gbc);

            gbc.gridx = 2;
            panel.add(new JLabel("Grade:"), gbc);

            gbc.gridx = 3;
            JComboBox<String> gradeComboBox = new JComboBox<>();
            gradeComboBox.addItem("");
            for (String grade : Constants.LETTER_GRADES) {
                gradeComboBox.addItem(grade);
            }
            gradeComboBox.setSelectedItem("");
            gradeComboBoxes.add(gradeComboBox);
            panel.add(gradeComboBox, gbc);
        }

        return panel;
    }

    public JTextField getFirstNameField() { return firstNameField; }
    public JTextField getLastNameField() { return lastNameField; }
    public JTextField getIdField() { return idField; }
    public JTextField getProgramField() { return programField; }
    public JTextField getEnrollmentYearField() { return enrollmentYearField; }
    public JComboBox<String> getFacultyComboBox() { return facultyComboBox; }
    public List<JComboBox<String>> getCourseComboBoxes() { return courseComboBoxes; }
    public List<JComboBox<String>> getGradeComboBoxes() { return gradeComboBoxes; }
}
