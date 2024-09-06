import java.util.*;

public class CpuScheduling {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("CPU SCHEDULING ALGORITHMS ");
        try {
            System.out.print("Enter the no of processes : ");
            int n = sc.nextInt();
            while (!(n >= 0)) {
                System.out.print("No of processes cannot be negative! Please enter again : ");
                n = sc.nextInt();
            }
            int[][] a = new int[n][2];
            System.out.println("Enter the arrival time and burst times of " + n + " processes");
            for (int i = 0; i < n; i++) {
                System.out.print("Enter the proper(non-negative) arrival time of Process " + i + " : ");
                a[i][0] = sc.nextInt();
                System.out.print("Enter the proper(non-negative) burst time of Process " + i + " : ");
                a[i][1] = sc.nextInt();
            }
            System.out.println("The processes are : ");
            System.out.println("     Arrival time:\tBurst Time: ");
            for (int i = 0; i < n; i++) {
                System.out.println("P" + i + "\t\t" + a[i][0] + "\t\t" + a[i][1]);
            }
            System.out.println();
            while (true) {
                System.out.println("1.FCFS    2.SJF     3.ROUND ROBIN    4.PRIORITY SCHEDULING\n");
                System.out.print("Enter the algorithm : ");
                int choice = sc.nextInt();
                if (choice == 1)
                    fcfs(a, n);
                else if (choice == 2) {
                    System.out.print("Pre-Emptive ?  \t1-Yes\t2-No : ");
                    int preemption = sc.nextInt();
                    shortest_job_first(a, n, preemption);
                } else if (choice == 3)
                    roundrobin(a, n);
                else if (choice == 4) {
                    System.out.println("Enter priorities of the processes : ");
                    int[][] copy = new int[n][3];
                    for (int i = 0; i < n; i++) {
                        copy[i][0] = a[i][0];
                        copy[i][1] = a[i][1];
                        System.out.print("Priority of P" + i + " : ");
                        copy[i][2] = sc.nextInt();
                    }
                    System.out.println();
                    System.out.print("Pre-Emptive ?  \t1-Yes\t2-No : ");
                    int preemption = sc.nextInt();
                    priority(copy, n, preemption);
                } else
                    System.out.println("Incorrect choice!");
                System.out.println();
                System.out.print("Do you wish to continue ? 1-Yes\t 2-No : ");
                int ask = sc.nextInt();
                if (ask != 1) {
                    System.out.println("Thank You !");
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Incorrect input!");
        }
        sc.close();
    }

    public static void fcfs(int[][] a, int n) {
        System.out.println("First Come First Serve");
        Arrays.sort(a, Comparator.comparingInt(arr -> arr[0]));
        int[][] b = new int[n][3];
        int ct = 0;
        for (int i = 0; i < n; i++) {
            if (ct < a[i][0])
                ct = a[i][0];
            b[i][0] = ct + a[i][1];
            b[i][1] = b[i][0] - a[i][0];
            b[i][2] = ct - a[i][0];
            ct = b[i][0];
        }
        System.out.println("The processes are : ");
        System.out.println("      AT\tBT\tCT\tTAT\tWT");
        for (int i = 0; i < n; i++) {
            System.out
                    .println("P" + i + "\t" + a[i][0] + "\t" + a[i][1] + "\t" + b[i][0] + "\t" + b[i][1]
                            + "\t" + b[i][2]);
        }
        double avg_tat = 0, ava_wt = 0;
        for (int i = 0; i < n; i++) {
            avg_tat += b[i][1];
            ava_wt += b[i][2];
        }
        ava_wt /= n;
        avg_tat /= n;
        System.out.println();
        System.out.printf("Average Turn Around Time : %.2f", avg_tat);
        System.out.println();
        System.out.printf("Average Waiting Time : %.2f", ava_wt);
    }

    public static void shortest_job_first(int[][] a, int n, int choice) {
        System.out.println("Shortest Job First");
        int[][] b = new int[n][3];
        int[] burst = new int[n];
        for (int i = 0; i < n; i++)
            burst[i] = a[i][1];
        Arrays.sort(a, Comparator.comparingInt(arr -> arr[0]));
        int ct = 0;
        int completed = 0;
        boolean[] visited = new boolean[n];
        if (choice == 1) {
            while (completed < n) {
                int shortest = -1, min = Integer.MAX_VALUE;
                for (int i = 0; i < n; i++) {
                    if (!visited[i] && a[i][0] <= ct && min > a[i][1]) {
                        shortest = i;
                        min = a[i][1];
                    }
                }
                if (shortest == -1) {
                    ct++;
                    continue;
                } else {
                    a[shortest][1] -= 1;
                    if (a[shortest][1] == 0) {
                        b[shortest][0] = ct + 1;
                        b[shortest][1] = b[shortest][0] - a[shortest][0];
                        b[shortest][2] = b[shortest][1] - burst[shortest];
                        visited[shortest] = true;
                        completed++;
                    }
                    ct++;
                }
            }
            System.out.println("The processes are : ");
            System.out.println("      AT\tBT\tCT\tTAT\tWT");
            for (int i = 0; i < n; i++) {
                System.out.println("P" + i + "\t" + a[i][0] + "\t" + burst[i] + "\t" + b[i][0] + "\t"
                        + b[i][1] + "\t" + b[i][2]);
            }
        } else {
            while (completed < n) {
                int shortest = -1, min = Integer.MAX_VALUE;
                for (int i = 0; i < n; i++) {
                    if (!visited[i] && a[i][0] <= ct && min > a[i][1]) {
                        shortest = i;
                        min = a[i][1];
                    }
                }
                if (shortest == -1) {
                    ct++;
                    continue;
                } else {
                    b[shortest][0] = ct + a[shortest][1];
                    b[shortest][1] = b[shortest][0] - a[shortest][0];
                    b[shortest][2] = b[shortest][1] - a[shortest][1];
                    visited[shortest] = true;
                    completed++;
                    ct += a[shortest][1];
                }
            }
            System.out.println("The processes are : ");
            System.out.println("      AT\tBT\tCT\tTAT\tWT");
            for (int i = 0; i < n; i++) {
                System.out.println("P" + i + "\t" + a[i][0] + "\t" + burst[i] + "\t" + b[i][0] + "\t"
                        + b[i][1] + "\t" + b[i][2]);
            }
        }
        double avg_tat = 0, ava_wt = 0;
        for (int i = 0; i < n; i++) {
            avg_tat += b[i][1];
            ava_wt += b[i][2];
        }
        ava_wt /= n;
        avg_tat /= n;
        System.out.println();
        System.out.printf("Average Turn Around Time : %.2f", avg_tat);
        System.out.println();
        System.out.printf("Average Waiting Time : %.2f", ava_wt);
    }

    public static void roundrobin(int[][] a, int n) {
        Arrays.sort(a, Comparator.comparingInt(arr -> arr[0]));
        int ct = 0, qt = 2, count = 0;
        int[][] b = new int[n][3];
        int[] burst = new int[n];
        for (int i = 0; i < n; i++) {
            burst[i] = a[i][1];
        }
        boolean[] visited = new boolean[n];
        while (count < n) {
            int check = 0;
            for (int i = 0; i < n; i++) {
                if (!visited[i] && a[i][0] <= ct) {
                    check++;
                    if (a[i][1] <= qt) {
                        visited[i] = true;
                        ct += a[i][1];
                        count++;
                        a[i][1] = 0;
                        b[i][0] = ct;
                        b[i][1] = ct - a[i][0];
                        b[i][2] = b[i][1] - a[i][1];
                    } else {
                        a[i][1] -= qt;
                        ct += qt;
                    }
                }
            }
            if (check == 0) {
                ct++;
            }
        }
        System.out.println("The processes are : ");
        System.out.println("      AT\tBT\tCT\tTAT\tWT");
        for (int i = 0; i < n; i++) {
            System.out.println("P" + i + "\t" + a[i][0] + "\t" + burst[i] + "\t" + b[i][0] + "\t"
                    + b[i][1] + "\t" + b[i][2]);
        }
        double avg_tat = 0, ava_wt = 0;
        for (int i = 0; i < n; i++) {
            avg_tat += b[i][1];
            ava_wt += b[i][2];
        }
        ava_wt /= n;
        avg_tat /= n;
        System.out.println();
        System.out.printf("Average Turn Around Time : ", avg_tat);
        System.out.println();
        System.out.printf("Average Waiting Time : %.2f", ava_wt);
    }

    public static void priority(int[][] a, int n, int preemption) {
        System.out.println("Priority Scheduling");
        int[][] b = new int[n][3];
        int[] burst = new int[n];
        for (int i = 0; i < n; i++)
            burst[i] = a[i][1];
        Arrays.sort(a, Comparator.comparingInt(arr -> arr[0]));
        int ct = 0;
        int completed = 0;
        boolean[] visited = new boolean[n];
        if (preemption == 1) {
            while (completed < n) {
                int highest = -1, priority = Integer.MAX_VALUE;
                for (int i = 0; i < n; i++) {
                    if (!visited[i] && a[i][0] <= ct && priority > a[i][2]) {
                        highest = i;
                        priority = a[i][2];
                    }
                }
                if (highest == -1) {
                    ct++;
                    continue;
                } else {
                    a[highest][1] -= 1;
                    if (a[highest][1] == 0) {
                        b[highest][0] = ct + 1;
                        b[highest][1] = b[highest][0] - a[highest][0];
                        b[highest][2] = b[highest][1] - burst[highest];
                        visited[highest] = true;
                        completed++;
                    }
                    ct++;
                }
            }
            System.out.println("The processes are : ");
            System.out.println("      AT\tBT\tCT\tTAT\tWT\tPrty");
            for (int i = 0; i < n; i++) {
                System.out.println("P" + i + "\t" + a[i][0] + "\t" + burst[i] + "\t" + b[i][0] + "\t"
                        + b[i][1] + "\t" + b[i][2] + "\t" + a[i][2]);
            }
        } else {
            while (completed < n) {
                int highest = -1, priority = Integer.MAX_VALUE;
                for (int i = 0; i < n; i++) {
                    if (!visited[i] && a[i][0] <= ct && priority > a[i][2]) {
                        highest = i;
                        priority = a[i][2];
                    }
                }
                if (highest == -1) {
                    ct++;
                    continue;
                } else {
                    b[highest][0] = ct + a[highest][1];
                    b[highest][1] = b[highest][0] - a[highest][0];
                    b[highest][2] = b[highest][1] - a[highest][1];
                    visited[highest] = true;
                    ct += a[highest][1];
                    completed++;
                }
            }
            System.out.println("The processes are : ");
            System.out.println("      AT\tBT\tCT\tTAT\tWT\tPrty");
            for (int i = 0; i < n; i++) {
                System.out.println("P" + i + "\t" + a[i][0] + "\t" + burst[i] + "\t" + b[i][0] + "\t"
                        + b[i][1] + "\t" + b[i][2] + "\t" + a[i][2]);
            }
        }
        double avg_tat = 0, ava_wt = 0;
        for (int i = 0; i < n; i++) {
            avg_tat += b[i][1];
            ava_wt += b[i][2];
        }
        ava_wt /= n;
        avg_tat /= n;
        System.out.println();
        System.out.printf("Average Turn Around Time : %.2f", avg_tat);
        System.out.println();
        System.out.printf("Average Waiting Time : %.2f", ava_wt);
    }
}