import java.util.Arrays;

public class IntList implements Comparable<IntList>{
    private int[] arr = new int[1];
    private int ptr = 0;

    private void updateSize() {
        if (ptr == arr.length) {
            arr = Arrays.copyOf(arr, arr.length * 2);
        }
    }

    public void add(int val) {
        arr[ptr++] = val;
        updateSize();
    }

    public int get(int pos) {
        return arr[pos];
    }

    public int size() {
        return ptr;
    }

    public void set(int pos, int val) {
        arr[pos] = val;
    }

    @Override
    public int compareTo(IntList o) {
        if (o == null) {
            throw new NullPointerException("Comaparator: comparing with Null object");
        }
        if (this.ptr < 2 || o.ptr < 2) {
            throw new IndexOutOfBoundsException("Comparator: some of this IntLists can't be compared");
        }
        if (this.get(0) == o.get(0)) {
            return this.get(1) - o.get(1);
        }
        return this.get(0) - o.get(0);
    }
}
