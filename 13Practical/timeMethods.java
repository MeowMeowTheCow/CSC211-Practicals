
import java.lang.Math.*;   
import java.io.*;   
import java.text.*;
import java.util.Random;

class Node {
   int key;
   String data; 
   Node(int key, String data) {
      this.key = key;
      this.data = data;
   }
}
public class timeMethods{
   static Node[] records;
   public static int N = 32655;
   public static void main(String args[])throws IOException {
      loadData("ulysses.numbered");
   }

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
double runTime = 0, runTime2 = 0, time;
double totalTime = 0.0;
int n = N;
int repetition, repetitions = 30;

   runTime = 0;
   for(repetition = 0; repetition < repetitions; repetition++) {
      start = System.currentTimeMillis();
		
      // call the procedures to time here:
      linearsearch (...);
      binarysearch (...);
      // Figure out how to alter this guideline here,
		
      finish = System.currentTimeMillis();
			
      time = (double)(finish - start);
      runTime += time;
      runTime2 += (time*time); }

   double aveRuntime = runTime/repetitions;
   double stdDeviation = 
      Math.sqrt(runTime2 - repetitions*aveRuntime*aveRuntime)/(repetitions-1);

   System.out.printf("\n\n\fStatistics\n");
   System.out.println("________________________________________________");
   System.out.println("Total time   =           " + runTime/1000 + "s.");
   System.out.println("Total time\u00b2  =           " + runTime2);
   System.out.println("Average time =           " + fiveD.format(aveRuntime/1000)
                     + "s. " + '\u00B1' + " " + fourD.format(stdDeviation) + "ms.");
   System.out.println("Standard deviation =     " + fourD.format(stdDeviation));
   System.out.println("n            =           " + n);
   System.out.println("Average time / run =     " + fiveD.format(aveRuntime/n*1000) 
                     + '\u00B5' + "s. "); 

   System.out.println("Repetitions  =             " + repetitions);
   System.out.println("________________________________________________");
   System.out.println();
   System.out.println(); }	} 

static void oneofyourMethods(int n, 
                       yourMethodParameter1,
                       yourMethodParameter2, . . . ) {