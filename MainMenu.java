import javax.swing.*;

import com.formdev.flatlaf.FlatDarkLaf;

import java.awt.*;
import java.awt.event.ActionEvent;

public class MainMenu {
    private JFrame frame;
    private Student[] students = new Student[0]; // Array to store students

    public MainMenu() {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        frame = new JFrame("Student Grade System");
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel headingPanel = new JPanel();
        headingPanel.setBackground(new Color(30, 30, 30));
        JLabel headingLabel = new JLabel("Student Grade System", SwingConstants.CENTER);
        headingLabel.setForeground(Color.WHITE);
        headingLabel.setFont(new Font("Roboto", Font.BOLD, 24));
        headingPanel.add(headingLabel);

        frame.add(headingPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1, 10, 10));
        buttonPanel.setBackground(new Color(30, 30, 30));

        JButton addStudentBtn = new JButton("Add Student");
        addStudentBtn.setBackground(new Color(200, 50, 50));
        addStudentBtn.setForeground(Color.WHITE);
        addStudentBtn.setPreferredSize(new Dimension(200, 40));
        addStudentBtn.addActionListener(e -> showAddStudentMenu());
        buttonPanel.add(addStudentBtn);

        JButton editStudentBtn = new JButton("Edit Student");
        editStudentBtn.setBackground(new Color(200, 50, 50));
        editStudentBtn.setForeground(Color.WHITE);
        editStudentBtn.setPreferredSize(new Dimension(200, 40));
        editStudentBtn.addActionListener(e -> showEditStudentMenu());
        buttonPanel.add(editStudentBtn);

        JButton visualizeDataBtn = new JButton("Visualize Student Data");
        visualizeDataBtn.setBackground(new Color(200, 50, 50));
        visualizeDataBtn.setForeground(Color.WHITE);
        visualizeDataBtn.setPreferredSize(new Dimension(200, 40));
        visualizeDataBtn.addActionListener(e -> visualizeData());
        buttonPanel.add(visualizeDataBtn);

        JButton exitBtn = new JButton("Exit");
        exitBtn.setBackground(new Color(200, 50, 50));
        exitBtn.setForeground(Color.WHITE);
        exitBtn.setPreferredSize(new Dimension(200, 40));
        exitBtn.addActionListener(e -> System.exit(0));
        buttonPanel.add(exitBtn);

        frame.add(buttonPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private void showAddStudentMenu() {
        new AddStudentMenu(this, students);
        frame.setVisible(false);
    }

    private void showEditStudentMenu() {
        new EditStudentMenu(this, students);
        frame.setVisible(false);
    }

    private void visualizeData() {
        JOptionPane.showMessageDialog(frame, "Visualizing Student Data!");
    }

    public void showMainMenu() {
        frame.setVisible(true);
    }

    public void addStudent(Student student) {
        Student[] newStudents = new Student[students.length + 1];
        System.arraycopy(students, 0, newStudents, 0, students.length);
        newStudents[students.length] = student;
        students = newStudents;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainMenu::new);
    }
}
