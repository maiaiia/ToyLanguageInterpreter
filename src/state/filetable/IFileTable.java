package state.filetable;

import exception.FileNotFoundException;
import exception.KeyNotInDictionaryException;

import java.io.BufferedReader;
import java.util.Collection;
import java.util.Set;

public interface IFileTable {
    void add( String key, BufferedReader value);
    void remove( String key) throws FileNotFoundException;
    BufferedReader search( String key) throws FileNotFoundException;
    BufferedReader get( String key); //no checking
    boolean contains( String key);

    int size();
    boolean isEmpty();
    String toString();
    Set<String> keySet();
    Collection<BufferedReader> values();
}
