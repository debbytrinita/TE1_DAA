// source : https://www.geeksforgeeks.org/heap-sort/
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class MaxHeapSort {

    public static void heapSort(int[] a){
        buildMaxHeap(a);
        int n = a.length;
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            int temp = a[0];
            a[0] = a[i];
            a[i] = temp;
 
            // call max heapify on the reduced heap
            maxHeapify(a, i, 0);
        }
    }
    public static void buildMaxHeap(int[] a){
        int n = a.length;
        for (int i = n/2-1; i >= 0; i--)
            maxHeapify(a, n, i);
    }

    public static void maxHeapify(int[] a, int n, int i){
        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2

        if (l < n && a[l] > a[largest])
            largest = l;

        // If right child is larger than largest so far
        if (r < n && a[r] > a[largest])
            largest = r;

        // If largest is not root
        if (largest != i) {
            int swap = a[i];
            a[i] = a[largest];
            a[largest] = swap;

        // Recursively heapify the affected sub-tree
            maxHeapify(a, n, largest);
        }
    }
    public static void main(String[] args) {
        for(int i = 1;i<=9;i++){
            ArrayList<Integer> dataset = new ArrayList<Integer>();
                // read file
                try (Scanner s = new Scanner(new FileReader(String.format("dataset%d.txt",i)))) {
                while (s.hasNext()) {
                    dataset.add(s.nextInt());
                }
                // convert into array primitive
                int[] arr = dataset.stream().mapToInt(j -> j).toArray();
    
                long startTime = System.nanoTime();
                heapSort(arr);
                long endTime   = System.nanoTime();
                double totalTime = (endTime - startTime)/1000000.0;
                Runtime runtime = Runtime.getRuntime();
    
                System.out.println(String.format("Waktu eksekusi dataset %d: %.2f",i,totalTime ));
                
                // Run the garbage collector
                runtime.gc();
                // Calculate the used memory
                long memory = runtime.totalMemory() - runtime.freeMemory();
                System.out.println(String.format("Penggunaan memori untuk dataset %d: %d ",i , memory));
                System.out.println();
    
                }
            catch (Exception e) {
                System.out.println(e);
            }
        }
    }

}
    

