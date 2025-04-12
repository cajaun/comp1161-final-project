import javax.swing.*;
import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class AddStudentMenu {
    private JFrame frame;
    private JTextField firstNameField, lastNameField, idField, gradeField, programField,
            enrollmentYearField;
    private JComboBox<String> courseComboBox, statusComboBox, facultyComboBox;
    private JList<String> coursesList;
    private DefaultListModel<String> coursesListModel;
    private JButton saveButton, closeButton;
    private MainMenu mainMenu;

    public AddStudentMenu(MainMenu mainMenu, List<Student> students) {
        this.mainMenu = mainMenu;

        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        frame = new JFrame("Add Student");
        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Student ID:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        idField = new JTextField();
        formPanel.add(idField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Faculty:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        facultyComboBox = new JComboBox<>(new String[] {
                "Social Sciences",
                "Humanities and Education",
                "Law",
                "Engineering",
                "Science and Technology",
                "Sport",
                "MedSci"
        });
        formPanel.add(facultyComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("First Name:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        firstNameField = new JTextField();
        formPanel.add(firstNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Last Name:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        lastNameField = new JTextField();
        formPanel.add(lastNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(new JLabel("Program:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        programField = new JTextField();
        formPanel.add(programField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(new JLabel("Enrollment Year:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        enrollmentYearField = new JTextField();
        formPanel.add(enrollmentYearField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        formPanel.add(new JLabel("Status:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 6;
        statusComboBox = new JComboBox<>(new String[] { "Active", "Graduated", "On Leave" });
        formPanel.add(statusComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        formPanel.add(new JLabel("Courses:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 8;
        coursesListModel = new DefaultListModel<>();
        coursesList = new JList<>(coursesListModel);
        coursesList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        formPanel.add(new JScrollPane(coursesList), gbc);

        gbc.gridx = 0;
        gbc.gridy = 10;
        formPanel.add(new JLabel("Grade:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 10;
        gradeField = new JTextField();
        formPanel.add(gradeField, gbc);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        saveButton = new JButton("Save");
        saveButton.addActionListener(new SaveButtonListener());
        buttonPanel.add(saveButton);

        closeButton = new JButton("Close");
        closeButton.addActionListener(e -> {
            frame.setVisible(false);
            mainMenu.showMainMenu();
        });
        buttonPanel.add(closeButton);

        frame.add(formPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private class SaveButtonListener implements java.awt.event.ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String id = idField.getText();
                String faculty = (String) facultyComboBox.getSelectedItem();
                String program = programField.getText();
                int enrollmentYear = Integer.parseInt(enrollmentYearField.getText());
                String status = (String) statusComboBox.getSelectedItem();

                String extractedCourse = (String) courseComboBox.getSelectedItem();
                double grade = Double.parseDouble(gradeField.getText());
                Grade newGrade = new Grade(extractedCourse, grade);

                Student newStudent = new Student(firstName, lastName, id, faculty, program, enrollmentYear, status);
                newStudent.addGrade(newGrade);

                for (String course : coursesList.getSelectedValuesList()) {
                    newStudent.addCourse(new Course(course, course, 3));
                }

                mainMenu.addStudent(newStudent);

                JOptionPane.showMessageDialog(frame, "Student Saved!");
                frame.setVisible(false);
                mainMenu.showMainMenu();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error saving student: " + ex.getMessage());
            }
        }
    }
}
