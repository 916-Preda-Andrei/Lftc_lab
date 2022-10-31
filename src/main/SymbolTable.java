package main;

import java.security.KeyException;

public class SymbolTable {

    private final HashTable hashTable = new HashTable(100);

    public Position addIdentifier(String name) throws DuplicateElementException {
        return hashTable.add(name, hashTable.hash(name.hashCode()));
    }
    public Position addIntConstant(Integer constant) throws DuplicateElementException {
        return hashTable.add(constant, hashTable.hash(constant.hashCode()));
    }
    public Position addStringConstant(String constant) throws DuplicateElementException {
        return hashTable.add(constant, hashTable.hash(constant.hashCode()));
    }
    public boolean containsIdentifier(String name) throws KeyException {
        try{
            hashTable.get(name, hashTable.hash(name.hashCode()));
            return true;
        }
        catch (KeyException ignored){
            return false;
        }
    }
    public boolean containsIntConstant(Integer constant) throws KeyException {
        try{
            hashTable.get(constant, hashTable.hash(constant.hashCode()));
            return true;
        }
        catch (KeyException ignored){
            return false;
        }
    }
    public boolean containsStringConstant(String constant) throws KeyException {
        try{
            hashTable.get(constant, hashTable.hash(constant.hashCode()));
            return true;
        }
        catch (KeyException ignored){
            return false;
        }

    }
    public Position getIdentifier(String name) throws KeyException {
        return hashTable.get(name, hashTable.hash(name.hashCode()));
    }
    public Position getIntConstant(Integer constant) throws KeyException {
        return hashTable.get(constant, hashTable.hash(constant.hashCode()));
    }
    public Position getStringConstant(String constant) throws KeyException {
        return hashTable.get(constant, hashTable.hash(constant.hashCode()));
    }
}
