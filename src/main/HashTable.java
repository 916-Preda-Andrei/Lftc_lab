package main;

import java.security.KeyException;
import java.util.ArrayList;
import java.util.List;

public class HashTable {

    private final int size;
    private List<List<Object>> bucket;

    public HashTable(int size){
        this.size = size;
        bucket = new ArrayList<>(size);
    }

    public int hash(int elementHashCode){
        return elementHashCode % size;
    }

    public Position get(Object element, int posInBucket) throws KeyException {
        for(int posInList=0;posInList<bucket.get(posInBucket).size();posInList++){
            Object elem = bucket.get(posInBucket).get(posInList);
            if (elem == element)
                return new Position(posInBucket,posInList);
        }
        throw new KeyException("The element was not found");
    }

    public Position add(Object element, int posInBucket) throws DuplicateElementException {
        try{
            get(element, posInBucket);
            throw new DuplicateElementException("This element is already in the HashTable");
        } catch (KeyException ignored) {
        }
        bucket.get(posInBucket).add(element);
        return new Position(posInBucket, bucket.get(posInBucket).size()-1);
    }
}
