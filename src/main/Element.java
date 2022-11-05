package main;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Element {
    private String type;
    private Object object;

    @Override
    public String toString(){
        return switch (type) {
            case "identifier", "string_constant" -> (String) object;
            case "int_constant" -> String.valueOf(object);
            default -> "invalid";
        };
    }
}
