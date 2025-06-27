import java.util.*;

class Student {
    String rollNo;
    String name;
    Map<String, Integer> marks;
    
    Student(String rollNo, String name, Map<String,Integer> marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.marks = marks;
    }
    
    int getTotal() {
        int total = 0;
        for (int mark : marks.values()) total += mark;
        return total;
    }
    
    double getPercentage() {
        return getTotal() / (double)marks.size();
    }
    
    String getGrade() {
        double percent = getPercentage();
        if (percent >= 80) return "A";
        if (percent >= 60) return "B";
        if (percent >= 40) return "C";
        return "D";
    }
    
    boolean isPass() {
        for (int mark : marks.values()) {
            if (mark < 35) return false;
        }
        return true;
    }
}

public class StudentResultManagement {
    static Map<String, Student> students = new HashMap<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n==== Student Result Management ====");
            System.out.println("1. Add Student");
            System.out.println("2. View Student Result");
            System.out.println("3. View All Results");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();
            sc.nextLine(); // consume newline
            switch (ch) {
                case 1: addStudent(); break;
                case 2: viewStudentResult(); break;
                case 3: viewAllResults(); break;
                case 4: System.exit(0);
                default: System.out.println("Invalid option!");
            }
        }
    }
    
    static void addStudent() {
        System.out.print("Enter Roll No: ");
        String roll = sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        Map<String, Integer> marks = new LinkedHashMap<>();
        System.out.print("Enter number of subjects: ");
        int n = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            System.out.print("Enter subject name: ");
            String sub = sc.nextLine();
            System.out.print("Enter marks in " + sub + ": ");
            int mark = sc.nextInt();
            sc.nextLine();
            marks.put(sub, mark);
        }
        students.put(roll, new Student(roll, name, marks));
        System.out.println("Student added successfully!");
    }
    
    static void viewStudentResult() {
        System.out.print("Enter Roll No: ");
        String roll = sc.nextLine();
        Student s = students.get(roll);
        if (s == null) {
            System.out.println("No such student!");
            return;
        }
        printResult(s);
    }
    
    static void viewAllResults() {
        if (students.isEmpty()) {
            System.out.println("No student records!");
            return;
        }
        for (Student s : students.values()) {
            printResult(s);
            System.out.println("-------------");
        }
    }
    
    static void printResult(Student s) {
        System.out.println("Roll No: " + s.rollNo);
        System.out.println("Name: " + s.name);
        System.out.println("Marks:");
        for (Map.Entry<String, Integer> entry : s.marks.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("Total: " + s.getTotal());
        System.out.printf("Percentage: %.2f\n", s.getPercentage());
        System.out.println("Grade: " + s.getGrade());
        System.out.println("Result: " + (s.isPass() ? "Pass" : "Fail"));
    }
}