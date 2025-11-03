package state;

import exception.FileNotFoundException;

import java.io.BufferedReader;

public interface IFileTable {
    boolean isOpened(String fileName) throws FileNotFoundException;

    void add(String fileName, BufferedReader bufferedReader);

    BufferedReader getFile(String fileName) throws FileNotFoundException;
}
