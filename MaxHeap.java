import java.util.ArrayList;
import java.util.Scanner;

public class MaxHeap {
    
    public static void insert(ArrayList<Integer> a, int item) {
        a.add(item);
        int i = a.size() - 1;
        while (i > 0 && a.get(i) > a.get(i / 2)) {
            int temp = a.get(i);
            a.set(i, a.get(i / 2));
            a.set(i / 2, temp);
            i = i / 2;
        }
    }

    public static int DeleteMax(ArrayList<Integer> a) {
        if (a.isEmpty()) {
            System.out.println("Heap is empty!");
            return -1;
        }
        int max = a.get(0);
        a.set(0, a.get(a.size() - 1));
        a.remove(a.size() - 1);
        if(!a.isEmpty())
            Adjust(a, 0 , a.size() - 1);
        return max;
    }

    public static void Adjust(ArrayList<Integer> a, int i , int n) {
        int j = 2 * i + 1;
        int item = a.get(i);

        while (j < n) {
            if (j + 1 < n && a.get(j) < a.get(j + 1)) {
                j = j + 1;
            }
            if (item >= a.get(j)) break;
            a.set(i, a.get(j));
            i = j;
            j = 2 * i + 1;
        }
        a.set(i, item);
    }

    public static void Heapify(ArrayList<Integer> a) {
        int n = a.size();
        for (int i = n - 1; i >= 0; i--) {
            Adjust(a, i , n);
        }
    }

    public static void HeapSort(ArrayList<Integer> a) {
        Heapify(a);
        int n = a.size();
        for (int i = n - 1; i > 0; i--) {
            int temp = a.get(i);
            a.set(i, a.get(0));
            a.set(0, temp);
            Adjust(a, 0 , i-1);
        }
    }

    public static int SearchMax(ArrayList<Integer> a) {
        return a.size() > 0 ? a.get(0) : -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> a = new ArrayList<>();

        System.out.println("Enter the number of elements in the array: ");
        int n = sc.nextInt();
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < n; i++) {
            a.add(sc.nextInt());
        }

        System.out.println("Given array: " + a);
        System.out.println("Heapifying the array");
        Heapify(a);
        System.out.println("Heap array: " + a);

        while (true) {
            System.out.println("1.Insert \n2.DeleteMax \n3.SearchMax \n4.HeapSort \n5.Exit \n6.Heapify");
            System.out.println("Enter your choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.print("Enter the number: ");
                    int item = sc.nextInt();
                    insert(a, item);
                    System.out.println("After Insertion: " + a);
                }

                case 2 -> {
                    int max = DeleteMax(a);
                    if (max != -1) {
                        System.out.println("After Deletion: " + a);
                    }
                }

                case 3 -> {
                    System.out.println("The largest element in the a is: " + SearchMax(a));
                }

                case 4 -> {
                    System.out.println("Heap array before sorting: " + a);
                    HeapSort(a);
                    System.out.println("Heap array after sorting: " + a);
                }

                case 5 -> {
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                }

                case 6 -> {
                    Heapify(a);
                    System.out.println("After Heapify: " + a);
                }

                default -> System.out.println("Invalid choice!!!");
            }
        }
    }
}
