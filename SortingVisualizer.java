import java.util.*;

public class SortingVisualizer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Sorting Techniques : ");
        try {
            System.out.print("Enter the size of the array : ");
            int n = sc.nextInt();
            while (!(n > 0)) {
                System.out.print("Array length is not valid! Please enter again : ");
                n = sc.nextInt();
            }
            int[] arr = new int[n];
            System.out.println("Enter " + n + " elements of the array :");
            for (int i = 0; i < n; i++) {
                System.out.print("Enter element at index " + i + ": ");
                arr[i] = sc.nextInt();
            }
            while (true) {
                print(arr, n);
                System.out.println();
                System.out.println("1. Bubble Sort   2. Selection Sort   3. Insertion Sort    4. Merge Sort");
                System.out.println();
                System.out.print("Enter the sorting technique : ");
                int sorting = sc.nextInt();
                int[] copy = Arrays.copyOf(arr, n);
                if (sorting == 1) {
                    bubbleSort(copy, n);
                } else if (sorting == 2) {
                    selectionSort(copy, n);
                } else if (sorting == 3) {
                    insertionSort(copy, n);
                } else if (sorting == 4) {
                    System.out.println("MERGE SORT ");
                    System.out.println(
                            "It divides the entire array into two halfs until there are only 2 elements left and merges small partitions of array using a temporary array according to the sorting rule !");
                    System.out.println("Time Complexity : O(n^2)");
                    mergeSort(copy, 0, n - 1);
                } else {
                    System.out.println("Incorrect choice entered !");
                }
                System.out.print("Do you wish to continue ? 1.Yes 2.No : ");
                int choice = sc.nextInt();
                if (choice != 1) {
                    System.out.println("Thank You !");
                    sc.close();
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Incorrect input!");
        }
    }

    public static void print(int[] arr, int n) {
        System.out.print("The Array is : ");
        for (int i = 0; i < n; i++)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    public static void bubbleSort(int[] arr, int n) {
        System.out.println("BUBBLE SORT");
        System.out.println("It works by placing the greatest elements first , at their corresponding indeices!");
        System.out.println("Time Complexity: \nBest Case : O(n)\nWorst Case : O(n^2)");
        System.out.println();
        for (int i = 0; i < n - 1; i++) {
            int c = 0;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    c++;
                    System.out.println(arr[j] + " > " + arr[j + 1] + " , So they are swapped ");
                    int t = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = t;
                    print(arr, n);
                }
            }
            if (c == 0)
                break;
        }
        System.out.println("The array is completely sorted !");
        System.out.println();
    }

    public static void selectionSort(int[] arr, int n) {
        System.out.println("SELECTION SORT");
        System.out.println("It works by placing the smallest elements first , at their corresponding indeices!");
        System.out.println("Time Complexity: O(n^2)");
        System.out.println();
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[min] > arr[j])
                    min = j;
            }
            if (min != i) {
                System.out.println("The element at index " + i + " is not at the correct place, so they are swapped !");

                int t = arr[min];
                arr[min] = arr[i];
                arr[i] = t;
                print(arr, n);
            }
        }
        System.out.println("The array is completely sorted !");
        System.out.println();
    }

    public static void insertionSort(int[] arr, int n) {
        System.out.println("INSERTION SORT");
        System.out.println(
                "It works by placing the smallest elements at their corresponding indeices by swapping with the previous elements !");
        System.out.println("Time Complexity: \nBest Case : O(n)\nWorst Case : O(n^2)");
        System.out.println();
        for (int i = 1; i < n; i++) {
            int k = i;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[k] < arr[j]) {
                    System.out.println(arr[k] + " < " + arr[j] + " , So they are swapped !");
                    int t = arr[k];
                    arr[k] = arr[j];
                    arr[j] = t;
                    k--;
                    print(arr, n);
                }
            }
        }
        System.out.println("The array is completely sorted !");
        System.out.println();
    }

    public static void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int mid = (l + r) / 2;
            mergeSort(arr, l, mid);
            mergeSort(arr, mid + 1, r);
            merge(arr, l, mid, mid + 1, r);
        }
    }

    public static void merge(int[] arr, int l, int mid, int midplus, int r) {
        int[] t = new int[r - l + 1];
        int s = 0;
        int i = l, j = midplus;
        while (i <= mid && j <= r) {
            if (arr[i] < arr[j]) {
                System.out.println(arr[i] + " < " + arr[j] + " , So " + arr[i] + " is placed before " + arr[j]);
                t[s++] = arr[i++];
            } else {
                System.out.println(arr[j] + " < " + arr[i] + " , So " + arr[j] + " is placed before " + arr[i]);
                t[s++] = arr[j++];
            }
        }
        while (i <= mid) {
            // System.out.println(arr[i]+" is left out , so it is placed at the end");
            t[s++] = arr[i++];
        }
        while (j <= r) {
            // System.out.println(arr[j]+" is left out , so it is placed at the end");
            t[s++] = arr[j++];
        }
        s = 0;
        for (int x = l; x <= r; x++) {
            arr[x] = t[s++];
        }
        print(arr, arr.length);
    }
}