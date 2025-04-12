public class calcGPA {
    private Student student;

    public calcGPA(Student student) {
        this.student = student;
    }

    // GPA points mapping for a single Grade object
    public double getGPAFromGrade(Grade grade) {
        String letterGrade = grade.getGrade(); // or getLetterGrade() if that's your method
        switch (letterGrade) {
            case "A+": return 4.3;
            case "A":  return 4.0;
            case "A-": return 3.7;
            case "B+": return 3.3;
            case "B":  return 3.0;
            case "B-": return 2.7;
            case "C+": return 2.3;
            case "C":  return 2.0;
            case "F1": return 1.0;
            case "F2": return 0.5;
            case "F3": return 0.0;
            default:   return 0.0;
        }
    }

    // GPA average over all courses
    public double calcOverallGPA() {
        double totalGPA = 0;
        int numOfcourses = 0;

        for (Grade currentGrade : student.getcourses()) {
            totalGPA += getGPAFromGrade(currentGrade);
            numOfcourses++;
        }

        if (numOfcourses == 0) {
            return 0;
        }

        return totalGPA / numOfcourses;
    }
}
