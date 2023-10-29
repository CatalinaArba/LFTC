import java.util.LinkedList;
public class HashTable {
    private int capacity;
    private LinkedList<String>[] table;

    public HashTable(int capacity) {
        this.capacity = capacity;
        this.table = new LinkedList[capacity];
    }

    private int hash(String value) {
        return Math.abs(value.hashCode() % capacity);
    }


    public void put(String value) {
        if (this.get(value) == -1) {
            int index = hash(value);

            if (table[index] == null) {
                table[index] = new LinkedList<>();
            }
            // String doesn't exist, add a new node to the bucket
            LinkedList<String> bucket = table[index];

            bucket.add(value);
        }
    }

    public Integer get(String key) {
        int index = hash(key);
        LinkedList<String> bucket = table[index];

        if (bucket != null) {
            for (String node : bucket) {
                if (node.equals(key)) {
                    return index;
                }
            }
        }
        return -1;
    }
}
