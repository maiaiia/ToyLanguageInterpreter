package model.adt;

import exception.FileNotFoundException;
import exception.FileOperationException;

import java.io.BufferedReader;
import java.util.HashMap;

//TODO might have to change file table to just implement IDictionary<String, BufferedReader> instead (but I don't like that)

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

    @Override
    public void remove(String fileName) {
        if (!fileTable.containsKey(fileName)){
            throw new FileOperationException("File does not exist");
        }
        /*if (isOpened(fileName)) {
            throw new FileOperationException("Close file before removing it: " + fileName);
        }*/
        fileTable.remove(fileName);
    }

    @Override
    public String toString() {
        String result = "";
        for (String fileName : fileTable.keySet()) {
            result += fileName + "\n";
        }
        return result;
    }

}
