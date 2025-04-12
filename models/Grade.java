package models;
public class Grade {
    private String courseCode;
    private String grade;

    public Grade(String courseCode, String grade) {
        this.courseCode = courseCode;
        this.grade = grade;
    }
    //test change
    public String getCourse() {
        return courseCode;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setCourse (String course) {
        this.courseCode = course;
    }
}
