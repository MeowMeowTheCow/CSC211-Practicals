import java.util.LinkedList;
import java.util.ListIterator;

public class chainedHash {

    private final LinkedList<Node>[] table;
    private final int m;
    private int size;

    private static class Node {
        String key;
        String value;

        Node(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    @SuppressWarnings("unchecked")
    public chainedHash(int m) {
        this.m = m;
        this.size = 0;

        table = (LinkedList<Node>[]) new LinkedList[m + 1];
        for (int i = 1; i <= m; i++) {
            table[i] = new LinkedList<>();
        }
    }

    private int hash(String key) {
        int h = key.hashCode() & 0x7fffffff;
        return (h % m) + 1;
    }

    public void insert(String key, String value) {
        if (key == null) throw new IllegalArgumentException();

        int i = hash(key);

        for (Node node : table[i]) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
        }

        table[i].addLast(new Node(key, value));
        size++;
    }

    public String lookup(String key) {
        if (key == null) return null;

        int i = hash(key);

        for (Node node : table[i]) {
            if (node.key.equals(key)) return node.value;
        }
        return null;
    }

    public String remove(String key) {
        if (key == null) return null;

        int i = hash(key);

        ListIterator<Node> it = table[i].listIterator();
        while (it.hasNext()) {
            Node node = it.next();
            if (node.key.equals(key)) {
                String val = node.value;
                it.remove();
                size--;
                return val;
            }
        }
        return null;
    }
}