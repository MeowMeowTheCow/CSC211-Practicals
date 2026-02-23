import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to the Student Mark Lookup System.");

        Scanner scanner = new Scanner(System.in);

        int Students[] = new int[6];
        Students[0]= 54;
        Students[1]= 72;
        Students[2]= 38;
        Students[3]= 85;
        Students[4]= 46;
        Students[5]= 59;

        System.out.println("Please enter a mark to search.");
        int search = scanner.nextInt();

        Arrays.sort(Students);
        System.out.println(Arrays.toString(Students));

        long start = System.nanoTime();

        binarySearch(Students, search);

        long end = System.nanoTime();
        System.out.println("Time taken: " + (end - start) + " nanoseconds");

        scanner.close();
    }






    public static int binarySearch(int[] Students, int search){
        int left=0;
        int right= Students.length-1;

        while(left<=right){
            int mid = (left+right)/2;

            if(Students[mid]==search){
                System.out.println("Mark found at index: " + mid);
                return mid;
            }
            else if(Students[mid]<search){
                left=mid+1;
            }
            else{
                right=mid-1;
            }
        }
        System.out.println("Mark not found in the array.");
        return -1;






    }
}