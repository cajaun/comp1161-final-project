package ui.panels.EditStudent;

import models.Grade;
import models.Student;
import models.calcGPA;
import ui.MainMenu;
import util.StudentUtils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EditStudentMenu extends JPanel {
    private final MainMenu mainMenu;
    private Student student;
    private List<JComboBox<String>> newGradeFields = new ArrayList<>();
    private List<Grade> grades;

    public EditStudentMenu(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
        setLayout(new BorderLayout());
        setBackground(Color.decode("#1A1A1A"));
    }

    public void refresh() {
        promptForStudentId(); 
    }

    private void promptForStudentId() {
        String studentId = JOptionPane.showInputDialog(mainMenu.getFrame(), "Enter Student ID to Edit:");
        if (studentId == null || studentId.trim().isEmpty()) {
            JOptionPane.showMessageDialog(mainMenu.getFrame(), "No Student ID entered.");
            mainMenu.showPanel("MainMenu");
            return;
        }

        student = findStudentById(studentId.trim());

        if (student != null) {
            grades = student.getcourses();
            loadEditPanel();
        } else {
            JOptionPane.showMessageDialog(mainMenu.getFrame(), "Student not found.");
            mainMenu.showPanel("MainMenu");
        }
    }

    private Student findStudentById(String studentId) {
        for (Student s : mainMenu.getStudents()) {
            if (s.getId().equals(studentId)) {
                return s;
            }
        }
        return null;
    }

    private void loadEditPanel() {
        removeAll();
        setLayout(new BorderLayout());
        newGradeFields.clear();
    
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setOpaque(false);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
    
        JLabel idLabel = new JLabel("ID#: " + student.getId());
        idLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        idLabel.setForeground(Color.WHITE);
        idLabel.setAlignmentX(LEFT_ALIGNMENT);
        idLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        contentPanel.add(idLabel);
    
        contentPanel.add(createGradesPanel());
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(createButtonPanel());
    
        add(contentPanel, BorderLayout.NORTH);
        revalidate();
        repaint();
    }
    private JPanel createGradesPanel() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    
        for (Grade grade : grades) {
            JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 5));
            row.setOpaque(false);
    
            JLabel courseLabel = new JLabel(grade.getCourse());
            courseLabel.setPreferredSize(new Dimension(150, 25));
            courseLabel.setForeground(Color.WHITE);
            row.add(courseLabel);
    
            JComboBox<String> gradeDropdown = new JComboBox<>(new String[]{
                "A+", "A", "A-", "B+", "B", "B-", "C+", "C", "F1", "F2", "F3"
            });
            gradeDropdown.setSelectedItem(grade.getGrade());
            newGradeFields.add(gradeDropdown);
            row.add(gradeDropdown);
    
            panel.add(row);
        }
    
        return panel;
    }
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.setOpaque(false);

        JButton saveButton = new JButton("Save Changes");
        saveButton.addActionListener(e -> saveChanges());

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> mainMenu.showPanel("ViewStudents"));

        panel.add(saveButton);
        panel.add(backButton);

        return panel;
    }

    private void saveChanges() {
        if (student == null) return;

        for (int i = 0; i < grades.size(); i++) {
            String selectedGrade = (String) newGradeFields.get(i).getSelectedItem();
            if (selectedGrade != null) {
                grades.get(i).setGrade(selectedGrade);
            }
        }

        calcGPA gpaCalc = new calcGPA(student);
        double newGpa = Math.round(gpaCalc.calcOverallGPA() * 100.0) / 100.0;
        student.setGpa(newGpa);

        StudentUtils.saveStudents(mainMenu.getStudents());

        JOptionPane.showMessageDialog(mainMenu.getFrame(), "Grades and GPA updated.");
        mainMenu.reloadViewStudentsPanel();
        mainMenu.showPanel("ViewStudents");
    }
}
