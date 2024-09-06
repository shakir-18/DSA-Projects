import java.util.*;

public class GpaCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("GPA CALCULATOR");
        System.out.println();
        System.out.println("Enter the student name and roll_no : ");
        try {
            System.out.print("NAME : ");
            String name = sc.nextLine();
            System.out.print("ROLL_NO : ");
            String roll_no = sc.nextLine();
            System.out.print("Enter the no of semesters completed : ");
            int n = sc.nextInt();
            while (!(n >= 1 && n <= 8)) {
                System.out.print("No of semesters is not in the valid range! Please enter again : ");
                n = sc.nextInt();
            }
            int[][] a = new int[n][];
            double gpa = 0;
            int fail = 0;
            outer: for (int i = 0; i < n; i++) {
                System.out.print("Enter the number of subjects in semester " + (i + 1) + " : ");
                int s = sc.nextInt();
                while (!(s >= 1 && s <= 6)) {
                    System.out.print("Number of subjects is not in the valid range! Please enter again : ");
                    s = sc.nextInt();
                }
                a[i] = new int[s];
                double sgpa = 0;
                for (int j = 0; j < s; j++) {
                    System.out.print("Enter the marks in subject " + (j + 1) + "  : ");
                    int marks = sc.nextInt();
                    while (!(marks >= 0 && marks <= 100)) {
                        System.out.print("Marks are not in the valid range! Please enter again : ");
                        marks = sc.nextInt();
                    }
                    if (marks >= 91 && marks <= 100)
                        sgpa += 10;
                    else if (marks >= 81 && marks <= 90)
                        sgpa += 9;
                    else if (marks >= 71 && marks <= 80)
                        sgpa += 8;
                    else if (marks >= 61 && marks <= 70)
                        sgpa += 7;
                    else if (marks >= 51 && marks <= 60)
                        sgpa += 6;
                    else if (marks >= 40 && marks <= 50)
                        sgpa += 5;
                    else {
                        System.out.println("The student has failed. Can't display the CGPA!");
                        fail++;
                        break outer;
                    }
                }
                sgpa /= s;
                gpa += sgpa;
                if (fail == 0) {
                    System.out.print("SGPA of sem " + (i + 1) + " : ");
                    System.out.println(sgpa);
                    System.out.print("CGPA till sem " + (i + 1) + " : ");
                    System.out.println(gpa / (i + 1));
                }
            }
            if (fail == 0) {
                System.out.println(name + " bearing the roll number " + roll_no + " has secured an overall CGPA of "
                        + (gpa / n) + " in " + n + " semesters !");
                System.out.println("Thank You !");
            }
        } catch (Exception e) {
            System.out.println("Incorrect input!");
        }
        sc.close();
    }
}