import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            LexicalAnalysis lexer = new LexicalAnalysis();
            lexer.processFile("src/input_program2.txt");

            System.out.println("PIF: ");
            printPIF(lexer.getPIF());
            System.out.println("ST: ");
            printSymbolTable(lexer.getSymbolTable());

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printPIF(ArrayList<PifPair> PIF) {
        System.out.println("Program Internal Form (PIF):");
        for (PifPair entry : PIF) {
            System.out.println("Token: " + entry.getToken() + ", Index: " + entry.getPositionInST());
        }
    }
    public static void printSymbolTable(HashTable symbolTable) {
        for (int i = 0; i < symbolTable.getCapacity(); i++) {
            LinkedList<String> bucket = symbolTable.getTable()[i];
            if (bucket != null) {
                for (String symbol : bucket) {
                    System.out.println("Key: " + symbol + ", Value: " + i);
                }
            }
        }
    }


}


//    public static void main(String[] args) {
//        HashTable hashTable = new HashTable(10);
//
//        // Insert strings
//        hashTable.put("one");
//        hashTable.put("two");
//        hashTable.put("three");
//        hashTable.put("one");
//
//        // Retrieve values by strings
//        System.out.println("Value for 'one': " + hashTable.get("one")); // Output: 1
//        System.out.println("Value for 'two': " + hashTable.get("two")); // Output: 1
//        System.out.println("Value for 'four': " + hashTable.get("four")); // Output: -1 (not found)
//    }

