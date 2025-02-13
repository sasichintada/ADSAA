import java.util.ArrayList;
import java.util.Scanner;

public class MinHeap {
    
    public static void insert(ArrayList<Integer> a , int item){
        a.add(item);
        int i = a.size() - 1;
        while (i > 0 && a.get(i) < a.get(i/2)){
            int temp = a.get(i);
            a.set(i, a.get(i/2));
            a.set(i/2 , temp);
            i =i/2;
        }
    }

    public static int DeleteMin(ArrayList<Integer> a){
        Heapify(a);
        if(a.isEmpty()){
            System.out.println("Heap is Empty! ");
            return -1;
        }
        int min = a.get(0);
        a.set(0, a.get(a.size() -1));
        a.remove(a.size() - 1);
        if(!a.isEmpty())
            Adjust(a,0, a.size()-1);
        return min;
    }

    public static void Adjust(ArrayList<Integer> a,int i , int n){
        int j = 2 * i + 1 ;
        int item = a.get(i);

        while (j<n) {
            if (j +1 < n && a.get(j + 1) < a.get(j)) 
                j = j +1;
            if (item <= a.get(j)) break;
            a.set(i, a.get(j));
            i =j;
            j = 2 * i + 1;
        }
        a.set(i, item);

    }

    public static void Heapify(ArrayList<Integer> a){
            int n = a.size();
            for (int i = n -1; i >=0 ; i--){
                Adjust(a, i ,n);
        }
    }

    public static void HeapSort(ArrayList<Integer> a , int n) {
        Heapify(a);
        for (int i = n - 1; i > 0; i--) {
            int temp = a.get(i);
            a.set(i, a.get(0));
            a.set(0, temp);
            Adjust(a, 0, i-1);
        }
    }

    public static int SearchMin(ArrayList<Integer> a) {
        return !a.isEmpty() ? a.get(0) : -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of elements :");
        int n = sc.nextInt();

        ArrayList<Integer> a = new ArrayList<>();

        System.out.println("Enter the " + n + "elements into the array :");
        for (int i = 0; i < n; i++) {
            int temp = sc.nextInt();
            a.add(temp);
        }

        System.out.println("Given array: " + a);
        System.out.println("Heapifying the given array:");
        Heapify(a);
        System.out.println("Heap array :" + a);

        while(true){
            System.out.println("1.Insert \n2.DeleteMin \n3.SearchMin \n4.HeapSort \n5.Exit \n6.Heapify");
            System.out.print("Enter your choice:");
            int choice = sc.nextInt();
            switch(choice){
                case 1 ->{
                    System.out.print("Enter the number:");
                    int item = sc.nextInt();
                    insert(a, item);
                    System.out.println("After insertion : "+ a);
                }
                case 2 ->{
                    int x = DeleteMin(a);
                    if (x == -1){
                        System.out.println("Heap is empty !");
                    }
                    else
                        System.out.println("Element "+ x+ " is deleted ");
                    System.out.println("After Deletion: " + a);
                }
                case 3 ->{
                    System.out.println("Minimum element of the Heap:" + SearchMin(a));
                }
                case 4 ->{
                    System.out.println("Before HeapSort: " + a);
                    HeapSort(a , a.size());
                    System.out.println("After HeapSort: " + a);
                }
                case 5 ->{
                    System.out.println("Exiting...");
                    System.exit(0);
                }
                case 6 -> {
                    Heapify(a);
                    System.out.println("After Heapify: " + a);
                }

                default -> System.out.println("Invalid input !...");
            }
        }


    }

}
