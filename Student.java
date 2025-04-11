public class Student {
    private String firstName;
    private String lastName;
    private String id;
    private Grade[] grades;

    public Student(String firstName, String lastName, String id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.grades = new Grade[0];  // Initialize with an empty array
    }

    public String getId() {
        return id;
    }

    public Grade[] getGrades() {
        return grades;
    }

    public Grade getGradeBySubject(String subject) {
        for (Grade grade : grades) {
            if (grade.getSubject().equals(subject)) {
                return grade;
            }
        }
        return null;
    }

    public void addGrade(Grade grade) {
        // Create a new array with one extra space
        Grade[] newGrades = new Grade[grades.length + 1];
        // Copy the existing grades into the new array
        System.arraycopy(grades, 0, newGrades, 0, grades.length);
        // Add the new grade
        newGrades[grades.length] = grade;
        // Update the grades array
        grades = newGrades;
    }

    //Setters for Student
    public void setFirstName (String firstName) {
        this.firstName = firstName;
    }

    public void setLastName (String lastName) {
        this.lastName = lastName;
    }

    public void setID (String id) {
        this.id = id;
    }

}
