package ui.panels.AddStudent;

import javax.swing.*;
import com.formdev.flatlaf.FlatDarkLaf;
import models.Student;
import ui.MainMenu;
import ui.components.StyledPanel;
import util.CourseLoader;
import util.Fonts;

import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class AddStudentMenu extends StyledPanel {
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

        Path path = Paths.get("data", "courseCode.json");
        courseList = CourseLoader.loadCourses(path.toString());
        formBuilder = new StudentFormBuilder();

        setLayout(new BorderLayout());


        JLabel titleLabel = new JLabel("Add Student", SwingConstants.LEFT);
        titleLabel.setFont(Fonts.OPEN_RUNDE.deriveFont(16f));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 0, 1, 1, StyledPanel.TEXT_GRAY), 
            BorderFactory.createEmptyBorder(10, 10, 10, 10)                 
        ));
        
        
        add(titleLabel, BorderLayout.NORTH);


        StyledPanel formPanel = formBuilder.buildForm(courseList); 
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS)); 


        JScrollPane scrollPane = new JScrollPane(formPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); 

    
        formPanel.setPreferredSize(new Dimension(600, formPanel.getPreferredSize().height));
        add(scrollPane, BorderLayout.CENTER);

        StyledPanel buttonPanel = buildButtonPanel(); 
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private StyledPanel buildButtonPanel() {
        StyledPanel panel = new StyledPanel();
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panel.setPreferredSize(new Dimension(600, 50));

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
                mainMenu));
        panel.add(saveButton);

        closeButton = new JButton("Back");
        closeButton.addActionListener(e -> mainMenu.showPanel("MainMenu"));
        panel.add(closeButton);

        return panel;
    }
}
