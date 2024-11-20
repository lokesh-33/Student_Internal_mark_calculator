package students;

public class Student {
    protected int[] tutorialMarks;
    protected int[] internalTestMarks;
    protected int[] assignmentMarks;
    protected int sapPoints;
    protected String rollNumber;  // New field for roll number

    public Student() {
        tutorialMarks = new int[15];
        internalTestMarks = new int[3];
        assignmentMarks = new int[5];
    }

    // Getter and setter for roll number
    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public void setTutorialMarks(int[] marks) {
        this.tutorialMarks = marks;
    }

    public void setInternalTestMarks(int[] marks) {
        this.internalTestMarks = marks;
    }

    public void setAssignmentMarks(int[] marks) {
        this.assignmentMarks = marks;
    }

    public void setSapPoints(int sapPoints) {
        this.sapPoints = sapPoints;
    }
}
