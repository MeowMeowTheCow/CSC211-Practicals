import java.util.*;

public class openHash {
    private enum State {EMPTY, OCCUPIED, DELETED};

    private static class Entry {
        String key;
        String value;
        State state;

        Entry(){
            state = state.EMPTY;
        }
    }

    private final int m;
    private final Entry[] table;
    private int size;

    public openHash(int m){
        if (m<3) throw new IllegalArgumentException("M must be at least 3");
        this.m = m;
        this.table = new Entry[m+1];
        
        for (int i = 0; i <= m; i++) {
            this.size = 0;
        }
    }
}