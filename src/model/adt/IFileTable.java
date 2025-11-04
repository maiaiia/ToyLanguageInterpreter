package model.adt;

import exception.FileNotFoundException;

import java.io.BufferedReader;

public interface IFileTable {
    boolean isOpened(String fileName);
    void add(String fileName, BufferedReader bufferedReader);
    BufferedReader getFile(String fileName) throws FileNotFoundException;
    void remove(String fileName);
}
