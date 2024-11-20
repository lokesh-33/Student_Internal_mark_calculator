package students;

import java.util.Arrays;

public class RegularStudent extends Student {

    public RegularStudent() {
        super();
    }

    public int calculateTotalMarks() {
        int totalMarks = calculateTotalTutorialMarks(tutorialMarks);
        totalMarks += calculateBestInternalTests(internalTestMarks);
        totalMarks += calculateAverageAssignmentMarks(assignmentMarks);
        totalMarks += calculateSapMarks(sapPoints);
        return totalMarks;
    }

    private int calculateTotalTutorialMarks(int[] marks) {
        Arrays.sort(marks);
        int total = 0;
        for (int i = 9; i < 15; i++) { // Best 6 out of 15
            total += marks[i];
        }
        return (int) Math.round(total / 6.0);
    }

    private int calculateBestInternalTests(int[] marks) {
        Arrays.sort(marks);
        return (marks[1] + marks[2]) * 15 / 100; // Get best two tests out of 50
    }

    private int calculateAverageAssignmentMarks(int[] marks) {
        double sum = 0;
        for (int mark : marks) {
            sum += mark;
        }
        return (int) Math.round(sum / 5);
    }

    private int calculateSapMarks(int sapPoints) {
        if (sapPoints >= 80) return 5;
        else if (sapPoints >= 70) return 4;
        else if (sapPoints >= 60) return 3;
        else if (sapPoints >= 50) return 2;
        else return 1;
    }

    public String getTutorialMarksAsJSON() {
        return Arrays.toString(tutorialMarks);
    }

    public String getInternalTestMarksAsJSON() {
        return Arrays.toString(internalTestMarks);
    }

    public String getAssignmentMarksAsJSON() {
        return Arrays.toString(assignmentMarks);
    }
}
