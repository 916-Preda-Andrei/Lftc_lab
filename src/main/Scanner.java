package main;

import lombok.Getter;

import java.io.*;
import java.security.KeyException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Scanner {
    private final SymbolTable symbolTable = new SymbolTable();
    private final List<PifElement> pifList = new ArrayList<>();
    private List<String> tokens;
    private List<String> program;
    @Getter
    private final List<String> lexicalErrors = new ArrayList<>();


    public void scan() throws IOException, ScannerException, DuplicateElementException, KeyException {
        tokens = readFileLines("C:\\Users\\Preda Andrei\\OneDrive\\Documents\\Facultate\\Lftc_lab\\Input\\Token.in");
        program = readFileLines("C:\\Users\\Preda Andrei\\OneDrive\\Documents\\Facultate\\Lftc_lab\\Input\\p1.txt");

        for (int i = 0; i < program.size(); i++) {
            String line = program.get(i);
            int spaces = countSpaces(line);
            line = line.substring(spaces);
            insertSpacesInPIF(spaces);
            List<String> elements = Arrays.asList(line.split(" "));
            for (String element : elements) {
                if (isToken(element)) {
                    pifList.add(new PifElement(element, new Position(-1, -1)));
                    continue;
                }
                if (isIdentifier(element)) {
                    if (!symbolTable.containsIdentifier(element))
                        symbolTable.addIdentifier(element);
                    pifList.add(new PifElement("id", symbolTable.getIdentifier(element)));
                    continue;
                }
                if (isIntConstant(element)) {
                    int constant = Integer.parseInt(element);
                    if (!symbolTable.containsIntConstant(constant))
                        symbolTable.addIntConstant(constant);
                    pifList.add(new PifElement("const", symbolTable.getIntConstant(constant)));
                    continue;
                }
                if (isStringConstant(element)) {
                    if (!symbolTable.containsStringConstant(element))
                        symbolTable.addStringConstant(element);

                    pifList.add(new PifElement("const", symbolTable.getStringConstant(element)));
                    continue;
                }
                lexicalErrors.add("lexical error at line " + i + ": unrecognized element " + element);
            }
        }
        printOutput();
    }

    private void insertSpacesInPIF(int spaces) {
        while (spaces > 0){
            pifList.add(new PifElement(" ", new Position(-1, -1)));
            spaces--;
        }
    }

    private void printOutput() throws IOException {
        FileWriter myPIFWriter = new FileWriter("C:\\Users\\Preda Andrei\\OneDrive\\Documents\\Facultate\\Lftc_lab\\Output\\PIF.out");
        FileWriter mySTWriter = new FileWriter("C:\\Users\\Preda Andrei\\OneDrive\\Documents\\Facultate\\Lftc_lab\\Output\\ST.out");

        for(PifElement pifElement: pifList)
            myPIFWriter.write(pifElement.toString());
        myPIFWriter.close();

        List<List<Element> > bucket = symbolTable.getHashTable().getBucket();
        for (int i=0;i< bucket.size();i++){
            List<Element> bucketList = bucket.get(i);
            for (int j=0;j<bucketList.size(); j++){
                Element element = bucketList.get(j);
                mySTWriter.write(i + ", "+ j + ": " + element.toString() + "\n");
            }
        }
        mySTWriter.close();
    }

    private boolean isStringConstant(String s) {
        return Pattern.matches("^\"[a-zA-Z0-9]*\"$", s);
    }

    private boolean isIntConstant(String s) {
        return Pattern.matches("^([1-9][0-9]*|0)$", s);
    }

    private boolean isIdentifier(String s) {
        return Pattern.matches("^[a-zA-Z][a-zA-Z0-9]*$", s);
    }

    private boolean isToken(String s) {
        for (String token : tokens) {
            if (token.equals(s))
                return true;
        }
        return false;
    }

    private int countSpaces(String line) {
        int spaces = 0;
        while (spaces < line.length()) {
            if (line.charAt(spaces) == ' ')
                spaces++;
            else
                break;
        }
        return spaces;
    }

    private List<String> readFileLines(String filepath) throws IOException {
        File fp = new File(filepath);
        FileReader fr = new FileReader(fp);
        BufferedReader br = new BufferedReader(fr);

        List<String> lines = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }

        fr.close();
        return lines;
    }
}
