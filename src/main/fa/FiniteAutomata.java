package main.fa;

import lombok.Getter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Getter
public class FiniteAutomata {

    private Set<String> states;
    private String initialState;
    private Set<String> finalStates;
    private Set<String> alphabet;
    private Map<Edge, String> transitions;
    private String file;

    public FiniteAutomata(String file) throws IOException {
        this.file = file;
        transitions = new HashMap<>();
        getParameters();
    }

    private void getParameters() throws IOException {
        List<String> lines = readFileLines();
        for (String line : lines) {
            List<String> tokens = List.of(line.split("="));
            if (tokens.size() != 2)
                throw new IOException("Wrong format of fa.in!");
            switch (tokens.get(0)) {
                case "states" -> states = new HashSet<>(List.of(tokens.get(1).split(",")));
                case "initialState" -> initialState = tokens.get(1);
                case "finalStates" -> finalStates = new HashSet<>(List.of(tokens.get(1).split(",")));
                case "alphabet" -> alphabet = new HashSet<>(List.of(tokens.get(1).split(",")));
                case "transitions" -> getTransitions(List.of(tokens.get(1).split(";")));
                default -> throw new IOException("Unexpected format for fa.in!");
            }
        }
    }

    private void getTransitions(List<String> transitionsList) throws IOException {
        for (String transition : transitionsList) {
            transition = transition.substring(1, transition.length() - 1);
            List<String> transitionElements = List.of(transition.split(","));
            if (transitionElements.size()!=3)
                throw new IOException("Invalid format for transition item");
            String from = transitionElements.get(0);
            String with = transitionElements.get(1);
            String to = transitionElements.get(2);
            if(!checkInStates(from) || !checkInStates(to) || !checkInAlphabet(with))
                throw new IOException("Invalid elements for transition item");
            transitions.put(new Edge(from, with), to);
        }
    }

    private boolean checkInStates(String s) {
        return states.contains(s);
    }

    private boolean checkInAlphabet(String s) {
        return alphabet.contains(s);
    }

    private List<String> readFileLines() throws IOException {
        File fp = new File(file);
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

    public boolean accepts(List<String> dfa) {
        String node = initialState;
        for(String with: dfa){
            Edge edge = new Edge(node, with);
            if(transitions.containsKey(edge))
                node = transitions.get(edge);
            else
                return false;
        }
        return finalStates.contains(node);
    }
}
