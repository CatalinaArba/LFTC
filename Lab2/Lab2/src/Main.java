public class Main {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable(10);

        // Insert strings
        hashTable.put("one");
        hashTable.put("two");
        hashTable.put("three");
        hashTable.put("one");

        // Retrieve values by strings
        System.out.println("Value for 'one': " + hashTable.get("one")); // Output: 2
        System.out.println("Value for 'two': " + hashTable.get("two")); // Output: 6
        System.out.println("Value for 'four': " + hashTable.get("four")); // Output: -1 (not found)
    }
}