package ui.panels.ViewStudents;

import models.Grade;
import models.Student;
import ui.components.StyledPanel;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.XChartPanel;


public class ViewStudentsMenu extends StyledPanel {

    private JPanel centerPanel;
    private CardLayout cardLayout;
    private JPanel buttonPanel;
    private JButton visualizeButton, backButton;

    public ViewStudentsMenu(List<Student> students) {
        super();
        setBackgroundColor(StyledPanel.WELCOME_BACKGROUND);
        setLayout(new BorderLayout());

  
        String[] columnNames = { "ID", "First Name", "Last Name", "Faculty", "Program", "Enrollment Year", "GPA" };
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
        
       
        TableRowSorter sorter = new TableRowSorter<>(studentTable.getModel());
        studentTable.setRowSorter(sorter);
        studentTable.setBackground(StyledPanel.WELCOME_BACKGROUND);
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

      
        cardLayout = new CardLayout();
        centerPanel = new JPanel(cardLayout);
        centerPanel.setOpaque(false);
        centerPanel.add(scrollPane, "TABLE");

        add(centerPanel, BorderLayout.CENTER);

 
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);

   
        visualizeButton = new JButton("Visualize Grades");
        visualizeButton.addActionListener(e -> showGradeChart(students));
        buttonPanel.add(visualizeButton);

      
        backButton = new JButton("Back to Table");
        backButton.setVisible(false);
        backButton.addActionListener(e -> {
            cardLayout.show(centerPanel, "TABLE");
            backButton.setVisible(false);
            visualizeButton.setVisible(true);
        });
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void showGradeChart(List<Student> students) {
        String id = JOptionPane.showInputDialog(this, "Enter Student ID:");
        if (id == null || id.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No Student ID entered.");
            return;
        }

        Student found = null;
        for (Student s : students) {
            if (s.getId().equals(id.trim())) {
                found = s;
                break;
            }
        }

        if (found == null) {
            JOptionPane.showMessageDialog(this, "Student not found.");
            return;
        }

        String[] gradeScale = {
                "A+", "A", "A-", "B+", "B", "B-", "C+", "C", "F1", "F2", "F3"
        };
        double[] gradeValues = {
                4.3, 4.0, 3.7, 3.3, 3.0, 2.7, 2.3, 2.0, 1.7, 1.3, 0.0
        };

        List<String> courses = new ArrayList<>();
        List<Double> values = new ArrayList<>();

        for (Grade g : found.getcourses()) {
            courses.add(g.getCourse());
            double val = 0.0;
            for (int i = 0; i < gradeScale.length; i++) {
                if (gradeScale[i].equals(g.getGrade())) {
                    val = gradeValues[i];
                    break;
                }
            }
            values.add(val);
        }

      
        CategoryChart chart = new CategoryChartBuilder()
                .width(800)
                .height(400)
                .title("Grades for Student ID: " + found.getId())
                .xAxisTitle("Courses")
                .yAxisTitle("GPA")
                .build();

        chart.getStyler().setChartBackgroundColor(Color.DARK_GRAY);
        chart.getStyler().setPlotBackgroundColor(Color.DARK_GRAY);
        chart.getStyler().setChartFontColor(Color.WHITE);
        chart.getStyler().setAxisTickLabelsColor(Color.WHITE);
        chart.getStyler().setPlotGridLinesColor(Color.GRAY);
        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setXAxisLabelRotation(45);
        chart.getStyler().setAvailableSpaceFill(0.8);
        chart.getStyler().setAxisTitleFont(new Font("Arial", Font.PLAIN, 14));

        chart.addSeries("GPA", courses, values);

       
        JPanel chartPanel = new XChartPanel<>(chart);
        chartPanel.setOpaque(false);
        centerPanel.add(chartPanel, "CHART");
        cardLayout.show(centerPanel, "CHART");

    
        visualizeButton.setVisible(false);
        backButton.setVisible(true);
    }
}
