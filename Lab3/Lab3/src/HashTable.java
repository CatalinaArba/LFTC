import java.util.LinkedList;

public class HashTable {
    private int capacity;
    private LinkedList<String>[] table;

    public HashTable(int capacity) {
        this.capacity = capacity;
        this.table = new LinkedList[capacity];
    }

    private int hash(String value) {
        int hash = 0;
        for (char c : value.toCharArray()) {
            hash = (hash * 31 + c) % capacity;
        }
        return Math.abs(hash);
    }



    public Integer put(String value) {
            int index = hash(value);

            if (table[index] == null) {
                table[index] = new LinkedList<>();
            }
            // String doesn't exist, add a new node to the bucket
            LinkedList<String> bucket = table[index];

            bucket.add(value);
            return index;

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

    public int getCapacity() {
        return capacity;
    }

    public LinkedList<String>[] getTable() {
        return table;
    }
}
