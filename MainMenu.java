import javax.swing.*;
import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.*;
import java.util.List;

public class MainMenu {
    private JFrame frame;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private List<Student> students;
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

        // Sidebar Panel
        JPanel sidebar = new GlassPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(new Color(0, 0, 0, 0));
        sidebar.setPreferredSize(new Dimension(220, frame.getHeight()));

        // Sidebar Buttons
        JButton addStudentButton = createSidebarButton("Add Student");
        addStudentButton.addActionListener(e -> {
            showPanel("AddStudent");
            setActiveButton(addStudentButton);
        });
        sidebar.add(addStudentButton);
        sidebar.add(Box.createVerticalStrut(10));

        JButton viewStudentsButton = createSidebarButton("View All Students");
        viewStudentsButton.addActionListener(e -> {
            showPanel("ViewStudents");
            setActiveButton(viewStudentsButton);
        });
        sidebar.add(viewStudentsButton);
        sidebar.add(Box.createVerticalStrut(10));

        JButton editStudentsButton = createSidebarButton("Edit Students");
        editStudentsButton.addActionListener(e -> {
            showPanel("EditStudents");
            setActiveButton(editStudentsButton);
        });
        sidebar.add(editStudentsButton);
        sidebar.add(Box.createVerticalStrut(10));

        JButton logoutButton = createSidebarButton("Logout");
        logoutButton.addActionListener(e -> logout());
        sidebar.add(logoutButton);

        frame.add(sidebar, BorderLayout.WEST);

    
        cardLayout = new CardLayout();
        mainPanel = new GlassPanel();
        mainPanel.setLayout(cardLayout);
        mainPanel.setBackground(new Color(0, 0, 0, 0));
        frame.add(mainPanel, BorderLayout.CENTER);


        addPanel("AddStudent", createAddStudentPanel());
        addPanel("ViewStudents", new JLabel("View All Students Panel", SwingConstants.CENTER));
        addPanel("EditStudents", new JLabel("Edit Students Panel", SwingConstants.CENTER));

        frame.setVisible(true);

   
        setActiveButton(addStudentButton);
    }

    private JButton createSidebarButton(String text) {
        JButton button = new JButton(text);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setFont(new Font("Roboto", Font.PLAIN, 14));
        button.setFocusPainted(false);
        button.setForeground(Color.WHITE);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.setPreferredSize(new Dimension(180, 36));
        button.setMaximumSize(new Dimension(180, 36));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBackground(new Color(0, 0, 0, 0)); 
        button.setBorder(BorderFactory.createLineBorder(new Color(0x40, 0x4A, 0x54), 1));
        button.setUI(new RoundedButtonUI());

        return button;
    }

    private void setActiveButton(JButton button) {
        if (activeButton != null) {
            activeButton.setBackground(new Color(0, 0, 0, 0));
            activeButton.setBorder(BorderFactory.createLineBorder(new Color(0x40, 0x4A, 0x54), 1));
        }

        activeButton = button;
        activeButton.setBackground(new Color(0x2C, 0x6E, 0xF5, 80)); 
        activeButton.setBorder(BorderFactory.createLineBorder(new Color(0x2C, 0x6E, 0xF5)));
    }

    private void addPanel(String name, JComponent panel) {
        mainPanel.add(panel, name);
    }

    public void showPanel(String name) {
        cardLayout.show(mainPanel, name);
    }

    private JPanel createAddStudentPanel() {
        return new AddStudentMenu(this, students);
    }

    public void addStudent(Student student) {
        students.add(student);
        StudentUtils.saveStudents(students); 
    }

    public JFrame getFrame() {
        return frame;
    }

    private void logout() {
        JOptionPane.showMessageDialog(frame, "You have been logged out.");
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainMenu::new);
    }
}
