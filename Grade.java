public class Grade {
    private String subject;
    private double grade;

    public Grade(String subject, double grade) {
        this.subject = subject;
        this.grade = grade;
    }
    //test change
    public String getSubject() {
        return subject;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public void setSubject (String subject) {
        this.subject = subject;
    }
}
