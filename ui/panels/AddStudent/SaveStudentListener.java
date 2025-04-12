package ui.panels.AddStudent;

import models.Grade;
import models.Student;
import models.calcGPA;
import ui.MainMenu;
import util.StudentValidator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SaveStudentListener implements ActionListener {
  private final JTextField firstNameField, lastNameField, idField, programField, enrollmentYearField;
  private final JComboBox<String> facultyComboBox;
  private final List<JComboBox<String>> courseComboBoxes;
  private final List<JComboBox<String>> gradeComboBoxes;
  private final MainMenu mainMenu;

  public SaveStudentListener(JTextField firstNameField, JTextField lastNameField, JTextField idField,
      JTextField programField, JTextField enrollmentYearField,
      JComboBox<String> facultyComboBox,
      List<JComboBox<String>> courseComboBoxes,
      List<JComboBox<String>> gradeComboBoxes,
      MainMenu mainMenu) {
    this.firstNameField = firstNameField;
    this.lastNameField = lastNameField;
    this.idField = idField;
    this.programField = programField;
    this.enrollmentYearField = enrollmentYearField;
    this.facultyComboBox = facultyComboBox;
    this.courseComboBoxes = courseComboBoxes;
    this.gradeComboBoxes = gradeComboBoxes;
    this.mainMenu = mainMenu;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    try {
      String firstName = firstNameField.getText();
      String lastName = lastNameField.getText();
      String id = idField.getText();
      String faculty = (String) facultyComboBox.getSelectedItem();
      String program = programField.getText();
      int enrollmentYear = Integer.parseInt(enrollmentYearField.getText());

      if (!StudentValidator.validateStudentData(id, mainMenu))
        return;

      Student newStudent = new Student(firstName, lastName, id, faculty, program, enrollmentYear);

      int filledCount = 0;
      for (int i = 0; i < courseComboBoxes.size(); i++) {
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
      newStudent.setGpa(Math.round(overallGPA * 100.0) / 100.0);

      mainMenu.addStudent(newStudent);

    } catch (Exception ex) {
      ex.printStackTrace();
      JOptionPane.showMessageDialog(mainMenu.getFrame(), "Error: " + ex.getMessage());
    }
  }
}
