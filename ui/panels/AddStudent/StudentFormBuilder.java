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
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

    
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Student ID:"), gbc);
        gbc.gridx = 1;
        idField = new JTextField(12);
        formPanel.add(idField, gbc);

        gbc.gridx = 2;
        formPanel.add(new JLabel("Faculty:"), gbc);
        gbc.gridx = 3;
        facultyComboBox = new JComboBox<>(Constants.FACULTIES);
        formPanel.add(facultyComboBox, gbc);

      
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("First Name:"), gbc);
        gbc.gridx = 1;
        firstNameField = new JTextField(12);
        formPanel.add(firstNameField, gbc);

        gbc.gridx = 2;
        formPanel.add(new JLabel("Last Name:"), gbc);
        gbc.gridx = 3;
        lastNameField = new JTextField(12);
        formPanel.add(lastNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Program:"), gbc);
        gbc.gridx = 1;
        programField = new JTextField(12);
        formPanel.add(programField, gbc);

        gbc.gridx = 2;
        formPanel.add(new JLabel("Enrollment Year:"), gbc);
        gbc.gridx = 3;
        enrollmentYearField = new JTextField(12);
        formPanel.add(enrollmentYearField, gbc);

        
        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 4;
        formPanel.add(new JLabel("Courses & Letter Grades (min 3):"), gbc);
        gbc.gridwidth = 1;

        for (int i = 0; i < 6; i++) {
            gbc.gridy++;
            gbc.gridx = 0;
            formPanel.add(new JLabel("Course " + (i + 1) + ":"), gbc);

            gbc.gridx = 1;
            JComboBox<String> courseComboBox = new JComboBox<>(courseList.toArray(new String[0]));
            courseComboBox.setEditable(true);
            courseComboBox.setSelectedItem(null);
            ComboBoxUtils.makeSearchable(courseComboBox, courseList);
            courseComboBoxes.add(courseComboBox);
            formPanel.add(courseComboBox, gbc);

            gbc.gridx = 2;
            formPanel.add(new JLabel("Grade:"), gbc);

            gbc.gridx = 3;
            JComboBox<String> gradeComboBox = new JComboBox<>();
            gradeComboBox.addItem("");
            for (String grade : Constants.LETTER_GRADES) {
                gradeComboBox.addItem(grade);
            }
            gradeComboBox.setSelectedItem("");
            gradeComboBoxes.add(gradeComboBox);
            formPanel.add(gradeComboBox, gbc);
        }

        return formPanel;
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
