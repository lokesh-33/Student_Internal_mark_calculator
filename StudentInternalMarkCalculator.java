package students;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class StudentInternalMarkCalculator {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/lokeshdb"; // Database URL
    private static final String USERNAME = "root";  // MySQL username
    private static final String PASSWORD = "Lokesh._.34";  // MySQL password

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RegularStudent student = new RegularStudent();

        try {
            // Input roll number
            System.out.print("Enter your Roll Number: ");
            String rollNumber = scanner.nextLine();  // Read roll number as a string
            student.setRollNumber(rollNumber);

            // Input tutorial marks
            int[] tutorialMarks = new int[15];
            System.out.println("Enter marks for 15 tutorials (each out of 15):");
            for (int i = 0; i < 15; i++) {
                System.out.print("Enter the mark for Tutorial " + (i + 1) + ": ");
                tutorialMarks[i] = getValidMark(scanner, 15);
            }
            student.setTutorialMarks(tutorialMarks);

            // Input internal test marks
            int[] internalTestMarks = new int[3];
            System.out.println("Enter marks for 3 internal tests (each out of 50):");
            for (int i = 0; i < 3; i++) {
                System.out.print("Enter the mark for Internal Test " + (i + 1) + ": ");
                internalTestMarks[i] = getValidMark(scanner, 50);
            }
            student.setInternalTestMarks(internalTestMarks);

            // Input assignment marks
            int[] assignmentMarks = new int[5];
            System.out.println("Enter marks for 5 assignments (each out of 20):");
            for (int i = 0; i < 5; i++) {
                System.out.print("Enter the mark for Assignment " + (i + 1) + ": ");
                assignmentMarks[i] = getValidMark(scanner, 20);
            }
            student.setAssignmentMarks(assignmentMarks);

            // Input SAP points
            System.out.print("Enter SAP points: ");
            student.setSapPoints(scanner.nextInt());

            // Calculate total marks
            int totalMarks = student.calculateTotalMarks();
            System.out.println("Total Marks: " + totalMarks);

            // Store marks in the database
            storeMarksInDatabase(student, totalMarks);

        } catch (InvalidMarkException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private static int getValidMark(Scanner scanner, int maxMark) throws InvalidMarkException {
        int mark = scanner.nextInt();
        if (mark < 0 || mark > maxMark) {
            throw new InvalidMarkException("Marks must be between 0 and " + maxMark);
        }
        return mark;
    }

    public static void storeMarksInDatabase(RegularStudent student, int totalMarks) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            // SQL query to insert student marks into the database
            String sql = "INSERT INTO student_marks (rollNumber, tutorialMarks, internalTestMarks, assignmentMarks, sapPoints, totalMarks) VALUES (?, ?, ?, ?, ?, ?)";

            // Create the PreparedStatement to execute the query
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, student.getRollNumber());  // Set the roll number
                pstmt.setString(2, student.getTutorialMarksAsJSON());  // Convert tutorial marks to JSON string
                pstmt.setString(3, student.getInternalTestMarksAsJSON());  // Convert internal test marks to JSON string
                pstmt.setString(4, student.getAssignmentMarksAsJSON());  // Convert assignment marks to JSON string
                pstmt.setInt(5, student.sapPoints);  // SAP points
                pstmt.setInt(6, totalMarks);  // Total marks

                // Execute the update (insert operation)
                pstmt.executeUpdate();
                System.out.println("Marks stored in the database successfully.");
            }
        } catch (Exception e) {
            System.err.println("Error while storing marks in database: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
