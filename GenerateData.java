// source: https://www.geeksforgeeks.org/collections-shuffle-method-in-java-with-examples/
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GenerateData {

        public static int[] random(int n){
            List<Integer> list = new ArrayList<Integer>(n);
            for(int i = 1; i <= n; i++) {
                list.add(i);
            }
            Collections.shuffle(list);
            int[] arr = list.stream().mapToInt(i -> i).toArray();
            return arr;
        }
        public static int[] sorted(int n){
            int[] arr = new int[n];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = i+1;
            }
            return arr;
        }

        public static int[] reversed(int n){
            int[] arr = new int[n];
            for (int i = 0; i < arr.length; i++) {
                    arr[i] = arr.length-i;
                }
            return arr;
        }
        public static void main(String[] args) throws FileNotFoundException {
            PrintWriter outputfile = new PrintWriter("dataset1.txt");
            int[] numbers = sorted((int)Math.pow(2, 9));
            for(int i = 0; i < numbers.length; i++) {
                outputfile.println((numbers[i]));
            }
            outputfile.close();
        }
    }

