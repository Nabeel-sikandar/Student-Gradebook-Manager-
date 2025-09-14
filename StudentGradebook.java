public class StudentGradebook {
    private static final int MAX_STUDENTS = 50;
    private static int[] roll = new int[MAX_STUDENTS];
    private static String[] name = new String[MAX_STUDENTS];
    private static int[] marks = new int[MAX_STUDENTS];
    private static int studentCount = 0;
    // Method to add a student
    public static boolean addStudent(int rollNumber, String studentName, int studentMarks) {
        if (studentCount >= MAX_STUDENTS) {
            return false; // Cannot add more students
        }
        // Check if roll number already exists
        for (int i = 0; i < studentCount; i++) {
            if (roll[i] == rollNumber) {
                return false; // Duplicate roll number
            }
        }
        roll[studentCount] = rollNumber;
        name[studentCount] = studentName;
        marks[studentCount] = studentMarks;
        studentCount++;
        return true;
    }
    // Method to display all students
    public static void displayAll() {
        if (studentCount == 0) {
            System.out.println("No students in the gradebook.");
            return;
        }
        System.out.println("Roll\tName\t\tMarks\tGrade");
        System.out.println("----\t----\t\t-----\t-----");
        for (int i = 0; i < studentCount; i++) {
            String studentGrade = grade(marks[i]);
            System.out.printf("%d\t%-12s\t%d\t%s%n", roll[i], name[i], marks[i], studentGrade);
        }
        System.out.println("Total Students: " + studentCount);
    }
    // Method to search by roll number (overloaded)
    public static boolean search(int rollNumber) {
        for (int i = 0; i < studentCount; i++) {
            if (roll[i] == rollNumber) {
                String studentGrade = grade(marks[i]);
                System.out.println("Found:");
                System.out.println("Roll: " + roll[i]);
                System.out.println("Name: " + name[i]);
                System.out.println("Marks: " + marks[i]);
                System.out.println("Grade: " + studentGrade);
                return true; // Early exit with break equivalent
            }
        }
        return false; // Not found
    }
    // Method to search by name (overloaded)
    public static boolean search(String studentName) {
        for (int i = 0; i < studentCount; i++) {
            if (name[i].equalsIgnoreCase(studentName)) {
                String studentGrade = grade(marks[i]);
                System.out.println("Found:");
                System.out.println("Roll: " + roll[i]);
                System.out.println("Name: " + name[i]);
                System.out.println("Marks: " + marks[i]);
                System.out.println("Grade: " + studentGrade);
                return true; // Early exit with break equivalent
            }
        }
        return false; // Not found
    }
    // Method to compute class average
    public static double computeAverage(int[] marksArray, int count) {
        if (count == 0) {
            return 0.0;
        }
        int sum = 0;
        for (int i = 0; i < count; i++) {
            sum += marksArray[i];
        }
        return (double) sum / count;
    }
    // Method to determine grade based on marks
    public static String grade(int studentMarks) {
        if (studentMarks >= 85) {
            return "A";
        } else if (studentMarks >= 70) {
            return "B";
        } else if (studentMarks >= 50) {
            return "C";
        } else {
            return "F";
        }
    }
    // Method to find class average and topper
    public static void classAverageAndTopper() {
        if (studentCount == 0) {
            System.out.println("No students in the gradebook.");
            return;
        }
        // Calculate average
        double average = computeAverage(marks, studentCount);
        // Find topper
        int topperIndex = 0;
        int maxMarks = marks[0];

        for (int i = 1; i < studentCount; i++) {
            if (marks[i] > maxMarks) {
                maxMarks = marks[i];
                topperIndex = i;
            }
        }
        System.out.println("==== Class Average & Topper ====");
        System.out.printf("Class Average: %.2f%n", average);
        System.out.println("Topper:");
        System.out.println("Roll: " + roll[topperIndex]);
        System.out.println("Name: " + name[topperIndex]);
        System.out.println("Marks: " + marks[topperIndex]);
        System.out.println("Grade: " + grade(marks[topperIndex]));
    }
    // Method to get current student count
    public static int getStudentCount() {
        return studentCount;
    }
    // Method to validate marks
    public static boolean isValidMarks(int studentMarks) {
        return studentMarks >= 0 && studentMarks <= 100;
    }
}