import java.util.Random;
public class mcsTests {

    public static int[] makeArray(int n) {
        Random r = new Random();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            int val = r.nextInt(n) + 1;
            if (r.nextInt(3) == 0) {
                val = -val;
            }
            arr[i] = val;
        }
        return arr;
    }

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

    public static long mcsOn2B(int[] X) {
        int n = X.length;
        long count = 0;
        int[] sumTo = new int[n+1];

        for (int i = 1; i <= n; i++) {
            sumTo[i] = sumTo[i-1] + X[i-1];
            count++;

        }

        int max = 0;

        for (int low = 0; low < n; low++) {
            for (int high = low + 1; high <= n; high++) {
                int sum = sumTo[high] - sumTo[low];
                count++;
                if (sum > max) {
                    max = sum;
                }
            }
        }
        return count;
    }

    public static long mcsOn(int[] X) {
        int n = X.length;
        long count = 0;
        int maxSoFar = 0;
        int maxToHere = 0;

       for (int i = 0; i < n; i++) {
            maxToHere = Math.max(maxToHere + X[i], 0);
            maxSoFar = Math.max(maxSoFar, maxToHere);
            count++;
        }
        return count;
    }
    

}
