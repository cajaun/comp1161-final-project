package ui.panels.ViewStudents;

import models.Student;
import ui.components.StyledPanel;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

public class ViewStudentsMenu extends StyledPanel {

    public ViewStudentsMenu(List<Student> students) {
        super();
        setBackgroundColor(StyledPanel.MAIN_BACKGROUND);
        setLayout(new BorderLayout());

        String[] columnNames = {"ID", "First Name", "Last Name", "Faculty", "Program", "Enrollment Year", "GPA"};
        Object[][] data = new Object[students.size()][7];

        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            data[i][0] = s.getId();
            data[i][1] = s.getFirstName();
            data[i][2] = s.getLastName();
            data[i][3] = s.getFaculty();
            data[i][4] = s.getProgram();
            data[i][5] = s.getEnrollmentYear();
            data[i][6] = s.getGpa();
        }

        JTable studentTable = new JTable(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Dark theme styling
        studentTable.setBackground(StyledPanel.MAIN_BACKGROUND);
        studentTable.setForeground(Color.WHITE);
        studentTable.setGridColor(new Color(80, 80, 80));
        studentTable.setSelectionBackground(new Color(60, 60, 60));
        studentTable.setSelectionForeground(Color.WHITE);
        studentTable.setShowGrid(true);
        studentTable.setOpaque(false);

        JTableHeader header = studentTable.getTableHeader();
        header.setBackground(new Color(50, 50, 50));
        header.setForeground(Color.WHITE);
        header.setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(studentTable);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        add(scrollPane, BorderLayout.CENTER);
    }
}
