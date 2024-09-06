import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MathRiddle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        System.out.println("WELCOME TO ONLINE MATH RIDDLE !");
        System.out.println("This test consists of 10 questions. 10 * 5 = 50 Marks");
        try {
            System.out.print("Do you want write the test ? 1. Yes 2. No : ");
            int choice = sc.nextInt();
            sc.nextLine();
            while (choice == 1) {
                ArrayList<String> questions = new ArrayList<>();
                ArrayList<String> answers = new ArrayList<>();
                double a, b;
                a = (double) (int) random.nextDouble(1, 251);
                b = (double) (int) random.nextDouble(a / 2, 1000);
                questions.add((int) a + " * " + (int) b + " = ");
                answers.add(String.valueOf((int) (a * b)));
                a = (double) (int) random.nextDouble(7, 101);
                questions.add("Area of the square with " + (int) a + " as its side is : ");
                answers.add(String.valueOf((int) (a * a)));
                a = (double) (int) random.nextDouble(1, 21);
                b = (double) (int) random.nextDouble(1, 21);
                questions.add("LCM of " + (int) a + " and " + (int) b + " is : ");
                answers.add(String.valueOf((int) (lcm(a, b))));
                a = (double) (int) random.nextDouble(1, 21);
                b = (double) (int) random.nextDouble(a + 1, 151);
                questions.add("Prime Numbers between " + (int) a + " and " + (int) b + " : ");
                answers.add(String.valueOf(String.valueOf((int) (count(a, b)))));
                a = (double) (int) random.nextDouble(30, 201);
                questions.add("Sum of first " + (int) a + " natural numbers is : ");
                answers.add(String.valueOf((int) ((a * (a + 1)) / 2)));
                a = (double) (int) random.nextDouble(1, 21);
                questions.add("Total surface area of cube with its side as " + (int) a + " : ");
                answers.add(String.valueOf((int) (6 * a * a)));
                a = (double) (int) random.nextDouble(1, 81);
                b = (double) (int) random.nextDouble(1, 1001);
                questions.add("What is " + (int) a + "% (percent) of " + (int) b + " : ");
                answers.add(String.valueOf((int) ((a * b) / 100)));
                a = (double) (int) random.nextDouble(1000, 10001);
                b = (double) (int) random.nextDouble(1, 41);
                questions.add("Simple interest on " + (int) a + " at " + (int) b + " % for 2 years is : ");
                answers.add(String.valueOf((int) ((a * b * 2) / 100)));
                b = (double) (int) random.nextDouble(25);
                a = (double) (int) random.nextDouble(b + 1, 101);
                questions.add((int) a + " % " + (int) b + " = ");
                answers.add(String.valueOf((int) (a % b)));
                a = (double) (int) random.nextDouble(1, 101);
                questions.add("Square root of " + (int) (a * a) + " is : ");
                answers.add(String.valueOf((int) a));
                ArrayList<ArrayList<String>> pair = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    ArrayList<String> temp = new ArrayList<>();
                    temp.add(questions.get(i));
                    temp.add(answers.get(i));
                    pair.add(temp);
                }
                Collections.shuffle(pair);
                for (int i = 0; i < 10; i++) {
                    questions.set(i, pair.get(i).get(0));
                    answers.set(i, pair.get(i).get(1));
                }
                int marks = 0;
                String res;
                for (int i = 0; i < 10; i++) {
                    System.out.println(questions.get(i) + " ");
                    res = sc.nextLine();
                    if (res.equals(answers.get(i)))
                        marks += 5;
                }
                System.out.println();
                System.out.println("Total marks obtained : " + marks);
                System.out.print("Want to retake the test? 1.Yes 2.No : ");
                choice = sc.nextInt();
                sc.nextLine();
                if (choice != 1) {
                    sc.close();
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Incorrect input!!!");
        }
        System.out.println("Thank You !");
    }

    public static double lcm(double a, double b) {
        if (a == b)
            return a;
        else if (a % b == 0)
            return a;
        else if (b % a == 0)
            return b;
        double max = Math.max(a, b);
        while (!(max % a == 0 && max % b == 0))
            max++;
        return max;
    }

    public static double count(double a, double b) {
        double c = 0;
        for (double i = a + 1; i < b; i++) {
            int f = 0;
            for (int j = 2; j < i; j++) {
                if (i % j == 0)
                    f++;
            }
            if (f == 0)
                c++;
        }
        return c;
    }
}