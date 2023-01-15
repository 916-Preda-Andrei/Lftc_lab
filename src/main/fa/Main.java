package main.fa;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FiniteAutomata finiteAutomata;
        try {
            finiteAutomata = new FiniteAutomata("C:\\Users\\Preda Andrei\\OneDrive\\Documents\\Facultate\\Lftc_lab\\Input\\fa.in");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }

        Scanner scanner = new Scanner(System.in);
        while(true){
            printMenu();
            int option = scanner.nextInt();
            switch (option) {
                case 1 -> System.out.println(finiteAutomata.getStates());
                case 2 -> System.out.println(finiteAutomata.getInitialState());
                case 3 -> System.out.println(finiteAutomata.getFinalStates());
                case 4 -> System.out.println(finiteAutomata.getAlphabet());
                case 5 -> System.out.println(finiteAutomata.getTransitions());
                case 6 -> {
                    List<String> dfa = readDFA(scanner);
                    if (!finiteAutomata.accepts(dfa))
                        System.out.println("This DFA is not valid");
                    else
                        System.out.println("This DFA is valid");
                }
                case 0 -> {
                    System.out.println("Goodbye!");
                    return;
                }
            }
        }
    }


    private static List<String> readDFA(Scanner scanner) {
        scanner.nextLine();
        System.out.println("DFA: ");
        String rawDFA = scanner.nextLine();
        return List.of(rawDFA.split(","));
    }

    private static void printMenu() {
        System.out.println("1. Print the set of states");
        System.out.println("2. Print the initial state");
        System.out.println("3. Print the set of final states");
        System.out.println("4. Print the alphabet");
        System.out.println("5. Print the set of transitions");
        System.out.println("6. Insert a DFA and verify if a sequence is accepted by the FA");
        System.out.println("0. Exit");
    }
}
