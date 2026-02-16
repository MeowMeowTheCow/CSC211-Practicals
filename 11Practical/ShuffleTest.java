import java.util.HashMap;
import java.util.Map;

public class ShuffleTest {

    private static String toKey(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int x : arr) {
            sb.append(x);
        }
        return sb.toString();
    }

    private static void runBiasedTest(int trials) {
        Map<String, Integer> counts = new HashMap<>();

        for (int i = 0; i < trials; i++) {
            int[] result = BiasedShuffle.biasedshuffle(3);
            String key = toKey(result);

            if (!counts.containsKey(key)) {
                counts.put(key, 1);
            } else {
                counts.put(key, counts.get(key) + 1);
            }
        }

        System.out.println("Biased shuffle results:");
        for (String key : counts.keySet()) {
            System.out.println(key + " " + counts.get(key));
        }
        System.out.println();
    }

    private static void runUnbiasedTest(int trials) {
        Map<String, Integer> counts = new HashMap<>();

        for (int i = 0; i < trials; i++) {
            int[] result = Shuffle.shuffle(3);
            String key = toKey(result);

            if (!counts.containsKey(key)) {
                counts.put(key, 1);
            } else {
                counts.put(key, counts.get(key) + 1);
            }
        }

        System.out.println("Unbiased shuffle results:");
        for (String key : counts.keySet()) {
            System.out.println(key + " " + counts.get(key));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int runs = 60000;

        runBiasedTest(runs);
        runUnbiasedTest(runs);
    }
}
