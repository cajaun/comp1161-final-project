package ui.panels.ViewStudents;

import models.Student;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ViewStudentsMenu extends JPanel {

    public ViewStudentsMenu(List<Student> students) {
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

        studentTable.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(studentTable);
        add(scrollPane, BorderLayout.CENTER);
    }
}
