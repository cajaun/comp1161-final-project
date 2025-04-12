import java.util.ArrayList;
import java.util.List;

public class Student {
    private String firstName;
    private String lastName;
    private String id;
    private String faculty; 
    private String program; 
    private int enrollmentYear; 
    private String status; 
    private double gpa; 
    private List<Grade> grades; 
    private List<Course> courses; 


    public Student(String firstName, String lastName, String id, String faculty, String program, int enrollmentYear, String status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.faculty = faculty;
        this.program = program;
        this.enrollmentYear = enrollmentYear;
        this.status = status;
        this.grades = new ArrayList<>();
        this.courses = new ArrayList<>();
    }


    public String getId() {
        return id;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public int getEnrollmentYear() {
        return enrollmentYear;
    }

    public void setEnrollmentYear(int enrollmentYear) {
        this.enrollmentYear = enrollmentYear;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public Grade getGradeByCourse(String course) {
        for (Grade grade : grades) {
            if (grade.getCourse().equals(course)) {
                return grade;
            }
        }
        return null;
    }

    public void addGrade(Grade grade) {
        grades.add(grade);
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setId(String id) {
        this.id = id;
    }


}
