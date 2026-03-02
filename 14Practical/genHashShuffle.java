import java.util.*;

public class genHashShuffle {
    private static final int N = 1<<20, M = 1000003, maxUsed = 950000, searches = 1000000, reps = 30;
    private static int[] counts = {750000, 800000, 850000, 900000, 950000};

    public static void main(String[] args) {
        int [] keys = new int[N];
        for (int i = 0; i < N; i++) {
            keys[i] = i;
        }

        Collections.shuffle(Arrays.asList(keys));

        String[] entryKeys = new String[N];
        String[] entryValues = new String[N];

        for (int i = 0; i < maxUsed; i++) {
            entryKeys[i] = Integer.toString(keys[i]);
            entryValues[i] =Integer.toString(i+1);
        }

         System.out.println("Average time in seconds");
        System.out.println(String.format("%-10s %-10s %-14s %-14s %-14s",
                "alpha", "N", "1/(1-a)", "OpenHash", "ChainedHash"));

    }
}