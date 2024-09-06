import java.util.Scanner;

public class CashFlowMinimizer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("CASH FLOW MINIMIZER");
        System.out.print("Enter the number of people : ");
        try {
            int n = sc.nextInt();
            while (n < 0) {
                System.out.print("Number of people cannot be negative! Please enter again : ");
                n = sc.nextInt();
            }
            if (n == 0 || n == 1)
                System.out.println("No of transactions can't be minimized!");
            else {
                int[][] people = new int[n][n];
                System.out.println("Enter the transactions!");
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (i != j) {
                            System.out.print(
                                    "Enter the amount person " + (i + 1) + " has to give to person " + (j + 1) + " : ");
                            int m = sc.nextInt();
                            while (m < 0) {
                                System.out.print("Amount can't be negative! Please enter again : ");
                                m = sc.nextInt();
                            }
                            people[i][j] = m;
                        }
                    }
                }
                int amount[] = new int[n];
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        amount[i] += (people[i][j] - people[j][i]);
                    }
                }
                minCashFlow(amount, n);
            }
        } catch (Exception e) {
            System.out.println("Incorrect input !");
        }
        sc.close();
    }

    public static int min(int[] amount, int n) {
        int id = 0;
        for (int i = 1; i < n; i++) {
            if (amount[i] < amount[id])
                id = i;
        }
        return id;
    }

    public static int max(int[] amount, int n) {
        int id = 0;
        for (int i = 1; i < n; i++) {
            if (amount[i] > amount[id])
                id = i;
        }
        return id;
    }

    public static void minCashFlow(int[] amount, int n) {
        int min = min(amount, n);
        int max = max(amount, n);
        if (amount[min] == 0 && amount[max] == 0)
            return;

        int m = least(-amount[min], amount[max]);
        amount[max] -= m;
        amount[min] += m;
        System.out.println("Person " + (min + 1) + " pays " + m
                + " to " + "Person " + (max + 1));
        minCashFlow(amount, n);
    }

    public static int least(int a, int b) {
        return (a < b) ? a : b;
    }
}