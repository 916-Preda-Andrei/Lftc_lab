package main;

import lombok.Getter;

@Getter
public class PifElement {
    private String key;
    private Position value;

    public PifElement(String key, Position value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString(){
        return key + " " + value.toString() + "\n";
    }
}
