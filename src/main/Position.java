package main;

import lombok.Getter;

@Getter
public class Position {

    private final int posInBucket;
    private final int posInList;

    public Position(int posInBucket, int posInList){

        this.posInBucket = posInBucket;
        this.posInList = posInList;
    }
}
