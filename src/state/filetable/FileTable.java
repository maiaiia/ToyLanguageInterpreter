package state.filetable;

import exception.FileNotFoundException;
import exception.KeyNotInDictionaryException;
import model.adt.HashMapDictionary;
import model.adt.IDictionary;

import java.io.BufferedReader;
import java.util.Collection;
import java.util.Set;

public class FileTable implements IFileTable {
    private final IDictionary<String, BufferedReader> fileTable = new HashMapDictionary<>();


    @Override
    public void add(String key, BufferedReader value) {
        fileTable.add(key, value);
    }

    @Override
    public void remove(String fileName) throws FileNotFoundException {
        try {
            fileTable.remove(fileName);
        } catch (KeyNotInDictionaryException _) {
            throw new FileNotFoundException(fileName);
        }
    }

    @Override
    public BufferedReader search(String fileName) throws FileNotFoundException {
        try {
            return fileTable.search(fileName);
        } catch (KeyNotInDictionaryException _) {
            throw new FileNotFoundException(fileName);
        }
    }

    @Override
    public BufferedReader get(String key) {
        return fileTable.get(key);
    }

    @Override
    public boolean contains(String key) {
        return fileTable.contains(key);
    }

    @Override
    public int size() {
        return fileTable.size();
    }

    @Override
    public boolean isEmpty() {
        return fileTable.isEmpty();
    }

    @Override
    public Set<String> keySet() {
        return fileTable.keySet();
    }

    @Override
    public Collection<BufferedReader> values() {
        return fileTable.values();
    }

    @Override
    public String toString() {
        String result = "";
        for (var key: fileTable.keySet()) {
            result += key + "\n";
        }
        return result;
    }
}
