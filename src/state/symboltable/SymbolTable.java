package state.symboltable;

import exception.KeyNotInDictionaryException;
import model.adt.HashMapDictionary;
import model.adt.IDictionary;
import model.value.IValue;

import java.util.Collection;
import java.util.Set;

public class SymbolTable implements  ISymbolTable {
    private final IDictionary<String, IValue> symbolTable = new HashMapDictionary<>();

    @Override
    public void add(String key, IValue value) {
        symbolTable.add(key, value);
    }

    @Override
    public void remove(String key) throws KeyNotInDictionaryException {
        symbolTable.remove(key);
    }

    @Override
    public IValue search(String key) throws KeyNotInDictionaryException {
        return symbolTable.search(key);
    }

    @Override
    public IValue get(String key) {
        return symbolTable.get(key);
    }

    @Override
    public boolean contains(String key) {
        return symbolTable.contains(key);
    }

    @Override
    public int size() {
        return symbolTable.size();
    }

    @Override
    public boolean isEmpty() {
        return symbolTable.isEmpty();
    }

    @Override
    public Set<String> keySet() {
        return symbolTable.keySet();
    }

    @Override
    public Collection<IValue> values() {
        return symbolTable.values();
    }

    @Override
    public String toString(){
        return "SYMBOL TABLE:\n" + symbolTable;
    }
}
