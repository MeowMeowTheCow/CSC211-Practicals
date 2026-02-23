import java.io.*;   
import java.text.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

class Node {
   int key;
   String lines; 
   Node(int key, String lines) {
      this.key = key;
      this.lines = lines;
   }
}
public class timeMethods{
   static Node[] records;
   public static int N = 32655;
public static void main(String args[])throws IOException {
   loadData("ulysses.numbered");

   DecimalFormat twoD = new DecimalFormat("0.00");
   DecimalFormat fourD = new DecimalFormat("0.0000");
   DecimalFormat fiveD = new DecimalFormat("0.00000");

   Arrays.sort(records, (a, b) -> Integer.compare(a.key, b.key));

   int reps = 30;
   Random rand = new Random();
   int[] searchKeys = new int[reps];
   for (int i = 0; i < reps; i++) {
      searchKeys[i] = rand.nextInt(32654) + 1;
   }

   long start, finish;
   double linearTime = 0, linearTime2 = 0, time;
   int repetitions = reps;
        
      for (int repetition = 0; repetition < repetitions; repetition++) {
         start = System.nanoTime();
         linearSearch(searchKeys[repetition]);
         finish = System.nanoTime();
            
         time = (double)(finish - start) / 1_000_000; // convert to milliseconds
         linearTime += time;
         linearTime2 += (time * time);
      }
        
      double aveLinearTime = linearTime / repetitions;
      double linearStdDev = Math.sqrt((linearTime2 - repetitions * aveLinearTime * aveLinearTime) / (repetitions - 1));
      double binaryTime = 0, binaryTime2 = 0;
        
      for (int repetition = 0; repetition < repetitions; repetition++) {
         start = System.nanoTime();
         binarySearch(searchKeys[repetition]);
         finish = System.nanoTime();
            
         time = (double)(finish - start) / 1_000_000; // convert to milliseconds
         binaryTime += time;
         binaryTime2 += (time * time);
      }
        
      double aveBinaryTime = binaryTime / repetitions;
      double binaryStdDev = Math.sqrt((binaryTime2 - repetitions * aveBinaryTime * aveBinaryTime) / (repetitions - 1));
        
     
      System.out.printf("\n\n\fLinear Search Statistics\n");
      System.out.println("________________________________________________");
      System.out.println("Total time   =           " + linearTime/1000 + "s.");
      System.out.println("Total time\u00b2  =           " + linearTime2);
      System.out.println("Average time =           " + fiveD.format(aveLinearTime/1000)
                         + "s. " + '\u00B1' + " " + fourD.format(linearStdDev) + "ms.");
      System.out.println("Standard deviation =     " + fourD.format(linearStdDev));
      System.out.println("n            =           " + N);
      System.out.println("Average time / run =     " + fiveD.format(aveLinearTime/repetitions*1000) 
                         + '\u00B5' + "s. ");
      System.out.println("Repetitions  =           " + repetitions);
      System.out.println("________________________________________________");
      System.out.println();
      System.out.println();

      System.out.printf("\n\n\fBinary Search Statistics\n");
      System.out.println("________________________________________________");
      System.out.println("Total time   =           " + binaryTime/1000 + "s.");
      System.out.println("Total time\u00b2  =           " + binaryTime2);
      System.out.println("Average time =           " + fiveD.format(aveBinaryTime/1000)
                         + "s. " + '\u00B1' + " " + fourD.format(binaryStdDev) + "ms.");
      System.out.println("Standard deviation =     " + fourD.format(binaryStdDev));
      System.out.println("n            =           " + N);
      System.out.println("Average time / run =     " + fiveD.format(aveBinaryTime/repetitions*1000) 
                         + '\u00B5' + "s. ");
      System.out.println("Repetitions  =           " + repetitions);
      System.out.println("________________________________________________");
      System.out.println();
      System.out.println();
   }
    
   static void loadData(String filename) throws IOException {
      BufferedReader br = new BufferedReader(new FileReader(filename));
      ArrayList<Node> list = new ArrayList<>();
      String line;
        
      while ((line = br.readLine()) != null) {
         if (line.length() >= 5) {
            int key = Integer.parseInt(line.substring(0, 5));
            String data = line.substring(5).trim();
            list.add(new Node(key, data));
         }
      }
      br.close();
        
      records = list.toArray(new Node[0]);
      N = records.length;
   }

   static Node linearSearch(int key) {
      for (int i = 0; i < records.length; i++) {
         if (records[i].key == key) {
            return records[i];
            }
         }
      return null;
   }

   static Node binarySearch(int key) {
      int left = 0, right = records.length - 1;
         while (left <= right) {
            int mid = left + (right - left) / 2;
            int midKey = records[mid].key;
            
            if (midKey == key) {
               return records[mid];
            } else if (midKey < key) {
               left = mid + 1;
            } else {
               right = mid - 1;
            }
        }
      return null;
   }
}