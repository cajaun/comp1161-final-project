package ui;

import javax.swing.*;
import com.formdev.flatlaf.FlatDarkLaf;

import models.Student;
import ui.components.RoundedButtonUI;
import ui.components.StyledPanel;
import ui.panels.AddStudent.AddStudentMenu;
import ui.panels.ViewStudents.ViewStudentsMenu;
import util.StudentUtils;

import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainMenu {
    private JFrame frame;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private List<Student> students;
    private Set<String> studentIDs = new HashSet<>();
    private JButton activeButton;

    public MainMenu() {
        students = StudentUtils.loadStudents();
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        frame = new JFrame("Student Grade System");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        frame.add(createSidebar(), BorderLayout.WEST);
        initializeMainPanel();

        frame.setVisible(true);
        setActiveButton((JButton)((JPanel) frame.getContentPane().getComponent(0)).getComponent(0)); // default: first button
    }

    // Sidebar Creation with Improved Design
    private JPanel createSidebar() {
        JPanel sidebar = StyledPanel.createSidebarPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(Color.decode("#FF0000"));
        sidebar.setPreferredSize(new Dimension(200, frame.getHeight()));
        sidebar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create Sidebar Buttons with icons (if any)
        JButton addStudentButton = createSidebarButton("Add Student", "add_icon.png");
        addStudentButton.addActionListener(e -> {
            showPanel("AddStudent");
            setActiveButton(addStudentButton);
        });

        JButton viewStudentsButton = createSidebarButton("View All Students", "view_icon.png");
        viewStudentsButton.addActionListener(e -> {
            reloadViewStudentsPanel();
            showPanel("ViewStudents");
            setActiveButton(viewStudentsButton);
        });

        JButton editStudentsButton = createSidebarButton("Edit Students", "edit_icon.png");
        editStudentsButton.addActionListener(e -> {
            showPanel("EditStudents");
            setActiveButton(editStudentsButton);
        });

        JButton logoutButton = createSidebarButton("Logout", "logout_icon.png");
        logoutButton.addActionListener(e -> logout());

        // Add the buttons to the sidebar with proper spacing
        sidebar.add(addStudentButton);
        sidebar.add(Box.createVerticalStrut(3));
        sidebar.add(viewStudentsButton);
        sidebar.add(Box.createVerticalStrut(3));
        sidebar.add(editStudentsButton);
        sidebar.add(Box.createVerticalStrut(3));
        sidebar.add(logoutButton);

        return sidebar;
    }

    private void initializeMainPanel() {
        cardLayout = new CardLayout();
        mainPanel = StyledPanel.createMainPanel();
        mainPanel.setLayout(cardLayout);
        mainPanel.setBackground(new Color(0, 0, 0, 0));
        frame.add(mainPanel, BorderLayout.CENTER);

        addPanel("AddStudent", createAddStudentPanel());
        addPanel("ViewStudents", new ViewStudentsMenu(students));
        addPanel("EditStudents", new JLabel("Edit Students Panel", SwingConstants.CENTER));
    }

    private JPanel createAddStudentPanel() {
        return new AddStudentMenu(this, students);
    }

    private void reloadViewStudentsPanel() {
        students = StudentUtils.loadStudents();
        addPanel("ViewStudents", new ViewStudentsMenu(students));
    }

    private void addPanel(String name, JComponent panel) {
        mainPanel.add(panel, name);
    }


    private JButton createSidebarButton(String text, String iconPath) {
        JButton button = new JButton(text);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setFont(new Font("Roboto", Font.PLAIN, 12)); 
        button.setFocusPainted(false);
        button.setForeground(Color.WHITE);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));



        button.setPreferredSize(new Dimension(200, 27));
        button.setMaximumSize(new Dimension(200, 27));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBackground(new Color(0, 0, 0, 0));
        button.setUI(new RoundedButtonUI());



        return button;
    }

    private void setActiveButton(JButton button) {
        if (activeButton != null) {
            activeButton.setBackground(new Color(0, 0, 0, 0));
        }
        activeButton = button;
        activeButton.setBackground(Color.decode("#7F7F7F")); 
    }
    

    public void addStudent(Student student) {
        if (studentIDs.contains(student.getId())) {
            JOptionPane.showMessageDialog(getFrame(), "Error: Student ID already exists!");
            return;
        }
        studentIDs.add(student.getId());
        students.add(student);
        StudentUtils.saveStudents(students);
    }

    public boolean isStudentIdTaken(String id) {
        return studentIDs.contains(id);
    }

    public void showPanel(String name) {
        cardLayout.show(mainPanel, name);
    }

    private void logout() {
        JOptionPane.showMessageDialog(frame, "You have been logged out.");
        System.exit(0);
    }

    public JFrame getFrame() {
        return frame;
    }

    public Set<String> getStudentIDs() {
        return studentIDs;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainMenu::new);
    }
}
