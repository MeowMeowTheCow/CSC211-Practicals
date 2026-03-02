public class openHash {

    private final String[] keys;
    private final String[] values;
    private final int m;
    private int size;

    private static final String DELETED = new String("<DELETED>");

    public openHash(int m) {
        this.m = m;
        this.keys = new String[m + 1];
        this.values = new String[m + 1];
        this.size = 0;
    }

    private int hash(String key) {
        int h = key.hashCode() & 0x7fffffff;
        return (h % m) + 1;
    }

    public void insert(String key, String value) {
        if (key == null) throw new IllegalArgumentException();
        if (size >= m) return;

        int i = hash(key);
        int firstDeleted = -1;

        for (int probes = 0; probes < m; probes++) {
            String k = keys[i];

            if (k == null) {
                if (firstDeleted != -1) i = firstDeleted;
                keys[i] = key;
                values[i] = value;
                size++;
                return;
            }

            if (k == DELETED) {
                if (firstDeleted == -1) firstDeleted = i;
            } else if (k.equals(key)) {
                values[i] = value;
                return;
            }

            i = (i % m) + 1;
        }

        if (firstDeleted != -1) {
            keys[firstDeleted] = key;
            values[firstDeleted] = value;
            size++;
        }
    }

    public String lookup(String key) {
        if (key == null) return null;

        int i = hash(key);

        for (int probes = 0; probes < m; probes++) {
            String k = keys[i];

            if (k == null) return null;
            if (k != DELETED && k.equals(key)) return values[i];

            i = (i % m) + 1;
        }
        return null;
    }

    public String remove(String key) {
        if (key == null) return null;

        int i = hash(key);

        for (int probes = 0; probes < m; probes++) {
            String k = keys[i];

            if (k == null) return null;

            if (k != DELETED && k.equals(key)) {
                String val = values[i];
                keys[i] = DELETED;
                values[i] = null;
                size--;
                return val;
            }

            i = (i % m) + 1;
        }
        return null;
    }
}