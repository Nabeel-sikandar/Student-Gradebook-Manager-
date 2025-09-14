import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("==== Student Gradebook Manager ====");
        // Main menu using while loop
        while (true) {
            System.out.println("\n1) Add Student");
            System.out.println("2) Display All");
            System.out.println("3) Search Student (by Roll / by Name)");
            System.out.println("4) Class Average & Topper");
            System.out.println("5) Exit");
            System.out.print("Choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    handleAddStudent(scanner);
                    break;
                case 2:
                    StudentGradebook.displayAll();
                    break;
                case 3:
                    handleSearch(scanner);
                    break;
                case 4:
                    StudentGradebook.classAverageAndTopper();
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    scanner.close();
                    return; // Exit the program
                default:
                    System.out.println("Invalid choice! Please enter 1-5.");
                    continue; // Continue to next iteration
            }
        }
    }
    private static void handleAddStudent(Scanner scanner) {
        System.out.print("Enter Roll: ");
        int rollNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Name: ");
        String studentName = scanner.nextLine();
        int studentMarks;
        while (true) {
            System.out.print("Enter Marks (0-100): ");
            studentMarks = scanner.nextInt();
            if (StudentGradebook.isValidMarks(studentMarks)) {
                break; // Valid marks, exit loop
            } else {
                System.out.println("Invalid marks! Please enter a value between 0 and 100.");
                continue; // Continue to ask for valid input
            }
        }
        if (StudentGradebook.addStudent(rollNumber, studentName, studentMarks)) {
            System.out.println("Student added successfully.");
        } else {
            System.out.println("Failed to add student. (Duplicate roll number or maximum capacity reached)");
        }
    }
    private static void handleSearch(Scanner scanner) {
        System.out.println("Search by:");
        System.out.println("1) Roll");
        System.out.println("2) Name");
        System.out.print("Enter choice: ");
        int searchChoice = scanner.nextInt();
        boolean found = false;
        switch (searchChoice) {
            case 1:
                System.out.print("Enter Roll to search: ");
                int rollToSearch = scanner.nextInt();
                found = StudentGradebook.search(rollToSearch); // Overloaded method
                break;
            case 2:
                scanner.nextLine(); // Consume newline
                System.out.print("Enter Name to search: ");
                String nameToSearch = scanner.nextLine();
                found = StudentGradebook.search(nameToSearch); // Overloaded method
                break;
            default:
                System.out.println("Invalid search option!");
                return;
        }
        if (!found) {
            System.out.println("No record found.");
            System.out.println("(Press any key to return to menu)");
            try {
                System.in.read();
            } catch (Exception e) {
                // Ignore exception
            }
        }
    }
}