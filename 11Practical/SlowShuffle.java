import java.util.Random;

public class SlowShuffle {

    public static int[] slowshuffle(int N) {
        int[] shuffled = new int[N];
        boolean[] isNotPresent = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            isNotPresent[i] = true;
        }

        Random rand = new Random();
        int i = 0;

        while (i < N - 1) {
            int r = rand.nextInt(N) + 1;

            if (isNotPresent[r]) {
                shuffled[i] = r;
                isNotPresent[r] = false;
                i++;
            }
        }

        for (int x = 1; x <= N; x++) {
            if (isNotPresent[x]) {
                shuffled[N - 1] = x;
                break;
            }
        }

        return shuffled;
    }

    public static void main(String[] args) {
        int[] result = slowshuffle(10);
        for (int x : result) {
            System.out.print(x + " ");
        }
        System.out.println();
    }
}
