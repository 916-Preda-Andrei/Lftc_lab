package main;

import java.io.IOException;
import java.security.KeyException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner();
        try {
            scanner.scan();
        } catch (IOException | ScannerException | DuplicateElementException | KeyException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        List<String> lexicalErrors = scanner.getLexicalErrors();
        for (String lexicalError : lexicalErrors)
            System.out.println(lexicalError);

        if (lexicalErrors.isEmpty())
            System.out.println("lexically correct");
    }
}
