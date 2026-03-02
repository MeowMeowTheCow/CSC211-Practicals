import java.util.Objects;
public class openHash {

    private enum State { EMPTY, OCCUPIED, DELETED }

    private static class Entry {
        String key;
        String value;
        State state = State.EMPTY;
    }

    private final int m;
    private final Entry[] table; 
    private int size;

    public openHash(int m) {
        if (m < 3) throw new IllegalArgumentException("m must be >= 3");
        this.m = m;
        this.table = new Entry[m + 1];
        for (int i = 0; i <= m; i++) table[i] = new Entry();
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int hash(String key) {
        Objects.requireNonNull(key, "key");
        return Math.floorMod(key.hashCode(), m) + 1;
    }

    public boolean insert(String key, String value) {
        Objects.requireNonNull(key, "key");
        Objects.requireNonNull(value, "value");

        if (size == m) return false;

        int start = hash(key);
        int firstDeleted = -1;

        for (int probe = 0; probe < m; probe++) {
            int idx = probeIndex(start, probe);
            Entry e = table[idx];

            if (e.state == State.EMPTY) {
                int target = (firstDeleted != -1) ? firstDeleted : idx;
                table[target].key = key;
                table[target].value = value;
                table[target].state = State.OCCUPIED;
                size++;
                return true;
            }

            if (e.state == State.DELETED) {
                if (firstDeleted == -1) firstDeleted = idx;
                continue;
            }

            if (key.equals(e.key)) {
                e.value = value;
                return false;
            }
        }

        if (firstDeleted != -1) {
            table[firstDeleted].key = key;
            table[firstDeleted].value = value;
            table[firstDeleted].state = State.OCCUPIED;
            size++;
            return true;
        }

        return false;
    }

    public String lookup(String key) {
        Objects.requireNonNull(key, "key");

        int start = hash(key);

        for (int probe = 0; probe < m; probe++) {
            int idx = probeIndex(start, probe);
            Entry e = table[idx];

            if (e.state == State.EMPTY) return null;
            if (e.state == State.OCCUPIED && key.equals(e.key)) return e.value;
        }
        return null;
    }

    public String remove(String key) {
        Objects.requireNonNull(key, "key");

        int start = hash(key);

        for (int probe = 0; probe < m; probe++) {
            int idx = probeIndex(start, probe);
            Entry e = table[idx];

            if (e.state == State.EMPTY) return null;

            if (e.state == State.OCCUPIED && key.equals(e.key)) {
                String old = e.value;
                e.key = null;
                e.value = null;
                e.state = State.DELETED;
                size--;
                return old;
            }
        }
        return null;
    }

    private int probeIndex(int start, int probe) {
        int raw = start + probe;
        return (raw <= m) ? raw : ((raw - 1) % m) + 1;
    }
}