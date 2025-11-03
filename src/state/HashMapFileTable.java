package state;

import exception.FileNotFoundException;

import java.io.BufferedReader;
import java.util.HashMap;

public class HashMapFileTable implements IFileTable {
    HashMap<String, BufferedReader> fileTable = new HashMap<>();

    @Override
    public boolean isOpened(String fileName){
        return fileTable.get(fileName) != null;
    }

    @Override
    public void add(String fileName, BufferedReader bufferedReader) {
        fileTable.put(fileName, new BufferedReader(bufferedReader));
    }

    @Override
    public BufferedReader getFile(String fileName) throws FileNotFoundException {
        var bufferedReader = fileTable.get(fileName);
        if (bufferedReader == null)
            throw new FileNotFoundException();
        return bufferedReader;
    }
}
