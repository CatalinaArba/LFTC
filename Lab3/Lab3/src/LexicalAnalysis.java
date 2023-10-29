

import java.io.*;
import java.util.*;
class LexicalAnalysis {
    private Set<String> operators;
    private Set<String> separators;
    private Set<String> reservedWords;
    private ArrayList<PifPair> PIF;
    private HashTable symbolTable;

    public LexicalAnalysis() {
        operators = new HashSet<>();
        separators = new HashSet<>();
        reservedWords = new HashSet();
        PIF = new ArrayList<>();
        symbolTable = new HashTable(20);

        readDataFromFile("src/operators.txt", operators);
        readDataFromFile("src/separators.txt", separators);
        readDataFromFile("src/reservedwords.txt", reservedWords);
    }

    public void processFile(String filename) throws FileNotFoundException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                List<String> tokens = tokenize(line);
                for (String token : tokens) {
                    analyze(token);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            // You can rethrow the exception if you want to terminate the program
            throw new RuntimeException("An IOException occurred while processing the file.");
        }
    }


    public ArrayList<PifPair> getPIF() {
        return PIF;
    }

    public HashTable getSymbolTable() {
        return symbolTable;
    }

    public void analyze(String token) {

        token = token.trim();
        if (!token.isEmpty()) {
            if (isReservedWord(token) || isOperator(token) || isSeparator(token)) {
                PIF.add(new PifPair(token, -1));
            } else {
                if (isIdentifier(token) || isConstant(token.toLowerCase())) {
                    int index = symbolTable.get(token);
                    if (index == -1)
                        index = symbolTable.put(token);
                    PIF.add(new PifPair(token, index));
                } else {
                    System.out.println("Lexical error: " + token);
                }
            }
        }
    }

   public List<String> tokenize(String line) {
        List<String> tokens = new ArrayList<>();
        String currentToken = "";
        int position = 0;
        int len = line.length();

        while (position < len) {
            char c = line.charAt(position);
            //it is a char or string constant
            if(isQuote(c)){
                tokens.add(currentToken.trim());
                currentToken = "";
                currentToken+=String.valueOf(c);
                position += 1;
                c = line.charAt(position);
                while (!(isQuote(c)) ) {
                    currentToken += c;
                    position += 1;
                    if(position < len){
                    c = line.charAt(position);}
                    else break;
                }
                currentToken += c;
                tokens.add(currentToken);
                if(position<len){
                position+=1;
                c = line.charAt(position);}
                currentToken="";
            }
            else{
            if (c==' ' ){
                tokens.add(currentToken.trim());
                currentToken="";
                position++;

            }else{
                if (isOperator(String.valueOf(c)) || isSeparator(String.valueOf(c))) {
                    if (!currentToken.isEmpty()) {
                        tokens.add(currentToken.trim());
                        currentToken="";
                    }

                    if (isOperator(String.valueOf(c))) {
                        char nextChar = line.charAt(position + 1);
                        if (isOperator(String.valueOf(nextChar))) {
                            tokens.add(String.valueOf(c) + String.valueOf(nextChar));
                            position += 2;
                        } else {
                            tokens.add(String.valueOf(c));
                            position += 1;
                        }
                    } else {
                        tokens.add(String.valueOf(c));
                        position += 1;
                    }
                } else {
                    currentToken += String.valueOf(c);
                    position += 1;
                }
            }}}

        if (!currentToken.isEmpty()) {
            tokens.add(currentToken);
        }

        return tokens;
    }

    private boolean isQuote(Character c){
        return c=='\'' || c=='"';
    }

    private boolean isSingleSpace(String token) {
        return token.equals(" ");
    }

    private void readDataFromFile(String filename, Set<String> set) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                set.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isOperator(String s) {
        return operators.contains(s);
    }

    public boolean isSeparator(String s) {
        return separators.contains(s);
    }

    public boolean isReservedWord(String s) {
        return reservedWords.contains(s.toLowerCase());
    }

    public boolean isIdentifier(String s) {
        if (s.isEmpty()) {
            return false;
        }

        char firstChar = s.charAt(0);
        if (!isLetter(firstChar)) {
            return false;
        }

        for (int i = 1; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            if (!isLetter(currentChar) && !isDigit(currentChar) && currentChar != '_') {
                return false;
            }
        }

        return true;
    }

    private boolean isLetter(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
    }

    private boolean isDigit(char c) {
        return (c >= '0' && c <= '9');
    }

    public boolean isIntConstant(String s) {
        return s.matches("[-+]?[1-9]\\d*|0");
    }

    public boolean isCharacterConstant(String s) {
        return s.matches("'[a-zA-Z0-9]'");
    }

    public boolean isStringConstant(String s) {
        return s.matches("\"[a-zA-Z0-9_ ]*\"");
    }

    public boolean isConstant(String s) {
        return isCharacterConstant(s) || isStringConstant(s) || isIntConstant(s);
    }
}