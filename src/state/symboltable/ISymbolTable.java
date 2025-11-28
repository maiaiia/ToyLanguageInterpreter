package state.symboltable;

import exception.KeyNotInDictionaryException;
import model.value.IValue;

import java.util.Collection;
import java.util.Set;

public interface ISymbolTable {
    void add(String key, IValue value);
    void remove(String key) throws KeyNotInDictionaryException;
    IValue search(String key) throws KeyNotInDictionaryException;
    IValue get(String key); //no checking
    boolean contains(String key);
    int size();
    boolean isEmpty();
    Set<String> keySet();
    Collection<IValue> values();
    String toString();

}
