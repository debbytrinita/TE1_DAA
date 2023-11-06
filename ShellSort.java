// import java.util.*;
// source : https://www.baeldung.com/java-file-to-arraylist, https://www.vogella.com/tutorials/JavaPerformance/article.html#runtimeinfo
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class ShellSort{
    public static final int C=4; // number of region compare-exchange repetitions
    public static void exchange(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    public static void compareExchange(int[] a, int i, int j) {
        if (((i < j) && (a[i] > a[j])) || ((i > j) && (a[i] < a[j]))) 
        exchange(a, i, j);
    }
    public static void permuteRandom(int a[], MyRandom rand) {
        for (int i=0; i<a.length; i++)
         // Use the Knuth random perm. algorithm
        exchange(a, i, rand.nextInt(a.length-i)+i);
    }
    // compare-exchange two regions of length offset each
    public static void compareRegions(int[] a, int s, int t, int offset, MyRandom rand) {
        int mate[] = new int[offset]; // index offset array
        for (int count=0; count<C; count++) { // do C region compare-exchanges 20
            for (int i=0; i<offset; i++) mate[i] = i;
                permuteRandom(mate,rand); // comment this out to get a deterministic Shellsort
            for (int i=0; i<offset; i++)
                compareExchange(a, s+i, t+mate[i]);
        }
    }
    public static void randomizedShellSort(int[] a) {
        int n = a.length; // we assume that n is a power of 2
        MyRandom rand = new MyRandom(); // random number generator (not shown)
        for (int offset = n/2; offset > 0; offset /= 2) { 
            for (int i=0; i < n - offset; i += offset) // compare-exchange up
                compareRegions(a,i,i+offset,offset,rand);
            for (int i=n-offset; i >= offset; i -= offset) // compare-exchange down
                compareRegions(a,i-offset,i,offset,rand);
            for (int i=0; i < n-3*offset; i += offset) // compare 3 hops up
                compareRegions(a,i,i+3*offset,offset,rand);
            for (int i=0; i < n-2*offset; i += offset) // compare 2 hops up
                compareRegions(a,i,i+2*offset,offset,rand);
            for (int i=0; i < n; i += 2*offset) // compare odd-even regions
                compareRegions(a,i,i+offset,offset,rand); 
            for (int i=offset; i < n-offset; i += 2*offset) // compare even-odd regions
                compareRegions(a,i,i+offset,offset,rand);
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
                randomizedShellSort(arr);
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
                    System.out.println("file not found");
                }
        }
    }
}

