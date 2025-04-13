package ui.panels.AddStudent;

import javax.swing.*;
import com.formdev.flatlaf.FlatDarkLaf;
import models.Student;
import ui.MainMenu;
import util.CourseLoader;

import java.awt.*;
import java.util.List;

public class AddStudentMenu extends JPanel {
    private JButton saveButton, closeButton;
    private MainMenu mainMenu;
    private List<String> courseList;
    private StudentFormBuilder formBuilder;

    public AddStudentMenu(MainMenu mainMenu, List<Student> students) {
        this.mainMenu = mainMenu;

        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        courseList = CourseLoader.loadCourses("comp1161-final-project\\data\\courseCode.json");
        formBuilder = new StudentFormBuilder();

        setBackground(Color.decode("#1A1A1A"));
        setLayout(new BorderLayout());

        JPanel formPanel = formBuilder.buildForm(courseList);
        JPanel wrapperPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        wrapperPanel.add(formPanel);
        add(wrapperPanel, BorderLayout.CENTER);

        JPanel buttonPanel = buildButtonPanel();
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JPanel buildButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        saveButton = new JButton("Save");
        saveButton.addActionListener(new SaveStudentListener(
                formBuilder.getFirstNameField(),
                formBuilder.getLastNameField(),
                formBuilder.getIdField(),
                formBuilder.getProgramField(),
                formBuilder.getEnrollmentYearField(),
                formBuilder.getFacultyComboBox(),
                formBuilder.getCourseComboBoxes(),
                formBuilder.getGradeComboBoxes(),
                mainMenu
        ));
        panel.add(saveButton);

        closeButton = new JButton("Back");
        closeButton.addActionListener(e -> mainMenu.showPanel("MainMenu"));
        panel.add(closeButton);

        return panel;
    }
}
