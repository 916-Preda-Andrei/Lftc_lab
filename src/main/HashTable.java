package main;


import lombok.Getter;

import java.security.KeyException;
import java.util.ArrayList;
import java.util.List;

public class HashTable {

    private final int size;
    @Getter
    private List<List<Element> > bucket;

    public HashTable(int size){
        this.size = size;
        bucket = new ArrayList<>();
        for (int i=0;i<size;i++) {
            bucket.add(new ArrayList<>());
        }
    }

    public int hash(int elementHashCode){
        while (elementHashCode < 0 )
            elementHashCode += size;
        return elementHashCode % size;
    }

    public Position get(Object element, int posInBucket) throws KeyException {
        for(int posInList=0;posInList<bucket.get(posInBucket).size();posInList++){
            Object elem = bucket.get(posInBucket).get(posInList).getObject();
            if (elem.equals(element))
                return new Position(posInBucket,posInList);
        }
        throw new KeyException("The element was not found");
    }

    public Position add(Element element, int posInBucket) throws DuplicateElementException {
        try{
            get(element, posInBucket);
            throw new DuplicateElementException("This element is already in the HashTable");
        } catch (KeyException ignored) {
        }
        bucket.get(posInBucket).add(element);
        return new Position(posInBucket, bucket.get(posInBucket).size()-1);
    }
}
