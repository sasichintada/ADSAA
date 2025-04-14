import java.util.ArrayList;
import java.util.Scanner;

public class MaxHeap {

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
        for (int i = n / 2 - 1; i >= 0; i--) { 
            Adjust(a, i, n);
        }
    }

    public static void DeleteElement(ArrayList<Integer> a, int element) {
        int index = a.indexOf(element);
        if (index == -1) {
            System.out.println("Element not found in the heap!");
            return;
        }
        a.set(index, a.get(a.size() - 1));
        a.remove(a.size() - 1);

        Heapify(a);
        System.out.println("Heap after deletion: " + a);
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
        Heapify(a);
        System.out.println("Max Heap: " + a);

        System.out.print("Enter the element to delete: ");
        int element = sc.nextInt();
        DeleteElement(a, element);

        sc.close();
    }
}
