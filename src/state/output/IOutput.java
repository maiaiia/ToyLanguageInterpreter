package state.output;

import exception.OutOfBoundsIndexException;

public interface IOutput {
    int size();
    boolean isEmpty();
    String get(int index) throws OutOfBoundsIndexException;
    String set(int index, String value) throws OutOfBoundsIndexException;
    void append(String value);
    String toString();
}