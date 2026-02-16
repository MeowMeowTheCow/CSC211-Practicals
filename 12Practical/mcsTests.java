import java.util.Random;
public class mcsTests {

     public static long mcsOn3(int[] X) {
        int n = X.length;
        long count = 0;
        int max = 0;
        
        for (int low = 0; low < n; low++) {

            for (int high = low; high < n; high++) {
                int sum = 0;

                for (int r = low; r < high; r++) {
                    sum += X[r];
                    count++;
                }

                if (sum > max) {
                    max = sum;
                }

            }

        }

        return count;

     }
     public static long mcsOn2A(int[] X) {
        int n = X.length;
        long count = 0;
        int max = 0;

        for (int low = 0; low < n; low++) {
            int sum = 0;

            for (int r = low; r < n; r++) {
                sum += X[r];
                count++;

                if (sum > max) {
                    max = sum;

                }

            }

        }
        return count;

    }
}
