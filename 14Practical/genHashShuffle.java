import java.util.Random;

public class genHashShuffle {
    private static final int N = 1 << 20;
    private static final int M = 1000003;
    private static final int maxUsed = 950000;
    private static final int searches = 1000000;
    private static final int reps = 30;

    private static final int[] counts = {750000, 800000, 850000, 900000, 950000};

    public static void main(String[] args) {
        int[] keys = new int[N];
        for (int i = 0; i < N; i++) keys[i] = i + 1; 

        
        shuffle(keys, new Random(42));

        String[] entryKeys = new String[maxUsed];
        String[] entryValues = new String[maxUsed];
        for (int i = 0; i < maxUsed; i++) {
            entryKeys[i] = Integer.toString(keys[i]);
            entryValues[i] = Integer.toString(i + 1);
        }

        System.out.println("Average time in seconds");
        System.out.printf("%-10s %-10s %-14s %-14s %-14s%n",
                "alpha", "N", "1/(1-a)", "OpenHash", "ChainedHash");

        for (int noInserts : counts) {
            double alpha = (double) noInserts / M;
            double openHashTime = 0.0, chainedHashTime = 0.0;

            Random r = new Random(12345);

            for (int i = 0; i < reps; i++) {
                openHash openHash = new openHash(M);
                chainedHash chainedHash = new chainedHash(M);

                for (int j = 0; j < noInserts; j++) {
                    openHash.insert(entryKeys[j], entryValues[j]);
                    chainedHash.insert(entryKeys[j], entryValues[j]);
                }

                long start = System.nanoTime();
                for (int j = 0; j < searches; j++) {
                    String k = entryKeys[r.nextInt(noInserts)];
                    openHash.lookup(k);
                }
                openHashTime += (System.nanoTime() - start) / 1e9;

                start = System.nanoTime();
                for (int j = 0; j < searches; j++) {
                    String k = entryKeys[r.nextInt(noInserts)];
                    chainedHash.lookup(k);
                }
                chainedHashTime += (System.nanoTime() - start) / 1e9;
            }

            System.out.printf("%-10.2f %-10d %-14.2f %-14.4f %-14.4f%n",
                    alpha, noInserts, 1.0 / (1.0 - alpha),
                    openHashTime / reps, chainedHashTime / reps);
        }
    }

    private static void shuffle(int[] a, Random rand) {
        for (int i = a.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }
}