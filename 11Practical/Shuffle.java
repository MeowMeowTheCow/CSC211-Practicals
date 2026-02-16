import java.util.Random;

public class Shuffle {

    public static int[] shuffle(int N) {

        int[] B = new int[N];

        for (int i = 0; i < N; i++) {
            B[i] = i + 1;
        }

        Random rand = new Random();
        int b = 0;

        while (b < N) {
            int r = rand.nextInt(N - b) + b; // r in [b..N-1]
            int temp = B[b];
            B[b] = B[r];
            B[r] = temp;
            b++;
        }

        return B;
    }

    public static void main(String[] args) {
        int[] result = shuffle(10);
        for (int x : result) {
            System.out.print(x + " ");
        }
        System.out.println();
    }
}
