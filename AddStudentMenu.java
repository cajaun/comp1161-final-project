import javax.swing.*;
import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.*;
import java.awt.event.*;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;
import javax.swing.text.JTextComponent;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class AddStudentMenu extends JPanel {
    private JTextField firstNameField, lastNameField, idField, programField, enrollmentYearField;
    private JComboBox<String>  facultyComboBox;
    private List<JComboBox<String>> courseComboBoxes = new ArrayList<>();
    private List<JComboBox<String>> gradeComboBoxes = new ArrayList<>();
    private JButton saveButton, closeButton;
    private MainMenu mainMenu;
    private List<String> courseList;

    private final String[] LETTER_GRADES = {
        "A+", "A", "A-", "B+", "B", "B-", "C+", "C", "F1", "F2", "F3"
    };

    public AddStudentMenu(MainMenu mainMenu, List<Student> students) {
        this.mainMenu = mainMenu;

        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        loadCoursesFromJSON();

        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Student ID and Faculty
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Student ID:"), gbc);
        gbc.gridx = 1;
        idField = new JTextField(12);
        formPanel.add(idField, gbc);

        gbc.gridx = 2;
        formPanel.add(new JLabel("Faculty:"), gbc);
        gbc.gridx = 3;
        facultyComboBox = new JComboBox<>(new String[] {
                "Social Sciences", "Humanities and Education", "Law",
                "Engineering", "Science and Technology", "Sport", "MedSci"
        });
        formPanel.add(facultyComboBox, gbc);

        // First Name and Last Name
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

        // Program and Enrollment Year
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


        // Courses & Letter Grades
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
            makeSearchable(courseComboBox);
            courseComboBoxes.add(courseComboBox);
            formPanel.add(courseComboBox, gbc);

            gbc.gridx = 2;
            formPanel.add(new JLabel("Grade:"), gbc);

            gbc.gridx = 3;
            JComboBox<String> gradeComboBox = new JComboBox<>(LETTER_GRADES);
            gradeComboBoxes.add(gradeComboBox);
            formPanel.add(gradeComboBox, gbc);
        }

        JPanel wrapperPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        wrapperPanel.add(formPanel);
        add(wrapperPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        saveButton = new JButton("Save");
        saveButton.addActionListener(new SaveButtonListener());
        buttonPanel.add(saveButton);

        closeButton = new JButton("Back");
        closeButton.addActionListener(e -> mainMenu.showPanel("MainMenu"));
        buttonPanel.add(closeButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadCoursesFromJSON() {
        courseList = new ArrayList<>();
        try {
            JSONTokener tokener = new JSONTokener(new FileReader("courseCode.json"));
            JSONObject jsonObject = new JSONObject(tokener);

            if (jsonObject.has("courseCodes")) {
                JSONArray array = jsonObject.getJSONArray("courseCodes");
                for (int i = 0; i < array.length(); i++) {
                    String course = array.optString(i, null);
                    if (course != null) {
                        courseList.add(course);
                    }
                }
            } else {
                throw new JSONException("'courseCodes' field is missing in the JSON.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(mainMenu.getFrame(), "Failed to load course list: " + e.getMessage());
        }
    }

    private void makeSearchable(JComboBox<String> comboBox) {
        JTextComponent editor = (JTextComponent) comboBox.getEditor().getEditorComponent();
        editor.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String input = editor.getText();
                comboBox.removeAllItems();
                for (String course : courseList) {
                    if (course.toLowerCase().contains(input.toLowerCase())) {
                        comboBox.addItem(course);
                    }
                }
                editor.setText(input);
                comboBox.showPopup();
            }
        });
    }

    private class SaveButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String id = idField.getText();
                String faculty = (String) facultyComboBox.getSelectedItem();
                String program = programField.getText();
                int enrollmentYear = Integer.parseInt(enrollmentYearField.getText());
    
                Student newStudent = new Student(firstName, lastName, id, faculty, program, enrollmentYear);
    
                int filledCount = 0;
                for (int i = 0; i < 6; i++) {
                    String course = ((String) courseComboBoxes.get(i).getEditor().getItem()).trim();
                    String grade = (String) gradeComboBoxes.get(i).getSelectedItem();
                    if (!course.isEmpty() && grade != null && !grade.isEmpty()) {
                        newStudent.addGrade(new Grade(course, grade));
                        filledCount++;
                    }
                }
    
                if (filledCount < 3) {
                    JOptionPane.showMessageDialog(mainMenu.getFrame(), "Please fill at least 3 course/grade pairs.");
                    return;
                }
    
                calcGPA gpaCalculator = new calcGPA(newStudent);
                double overallGPA = gpaCalculator.calcOverallGPA();
                double roundedGPA = Math.round(overallGPA * 100.0) / 100.0;
                newStudent.setGpa(roundedGPA);
    
             
                mainMenu.addStudent(newStudent);
                mainMenu.showPanel("MainMenu");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(mainMenu.getFrame(), "Error: " + ex.getMessage());
            }
        }
    }
    
}
