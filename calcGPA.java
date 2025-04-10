public class calcGPA {
    private Student student;
    double GPA;
    String letterGrade;

    public calcGPA(Student student) {
        this.student = student;
    }

    public double calcSubjectGPA(String subjectName) {
        //Gets the grade for a given subject
        Grade subGrade = student.getGradeBySubject(subjectName);
        //Returns 0.0 if the subject wasn't foound
        if (subGrade == null){return 0.0;}
        double grade = subGrade.getGrade();

        //Assisgns a numeric grade to a letter grade
        if (grade >= 85){
            letterGrade = "A";
        } else if (grade >= 70){
            letterGrade = "B";
        } else if (grade >= 55){
            letterGrade = "C";
        } else if (grade >= 50){
            letterGrade = "D";
        } else letterGrade = "F";

        //Returns a grade point for a letter grade
        switch (letterGrade){
            case "A":
                GPA = 4.0;
                break;
            case "B":
                GPA = 3.0;
                break;
            case "C":
                GPA = 2.0;
                break;
            case "D":
                GPA = 1.0;
                break;
            case "F":
                GPA = 0.0;
                break;
        }

        return GPA;
    }

    public double calcOverallGPA(){
        double totalGPA = 0;
        int numOfGrades = 0;
        //Iterates through list of grades and gets the GPA for each
        for(Grade currentGrade : student.getGrades()){
            totalGPA += calcSubjectGPA(currentGrade.getSubject());
            numOfGrades++;
        }
        if (numOfGrades == 0){
            return 0;
        }
        return totalGPA/numOfGrades;
    }
}