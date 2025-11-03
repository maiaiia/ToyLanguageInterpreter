package model.adt;

import exception.OutOfBoundsIndexException;
import exception.ValueNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class DynamicArrayList<T> implements  IList<T> {
    private final List<T> list =  new ArrayList<T>();

    void checkIndex(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        try {
            return list.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new OutOfBoundsIndexException();
        }
    }

    @Override
    public T set(int index, T value) {
        try {
            return list.set(index, value);
        } catch (IndexOutOfBoundsException e) {
            throw new OutOfBoundsIndexException();
        }
    }

    @Override
    public void append(T value) {
        this.list.addLast(value);
    }

    @Override
    public void insert(int index, T value) throws IndexOutOfBoundsException {
        checkIndex(index);
        list.add(index, value);
    }

    @Override
    public T remove(int index) {
        try {
            return list.remove(index);
        }
        catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public int find(T value) throws ValueNotFoundException {
        int indexOf = list.indexOf(value);
        if (indexOf == -1) {
            throw new ValueNotFoundException();
        }
        return indexOf;
    }

    @Override
    public String toString(){
        String result = "";
        for (T item : list){
            result += item.toString() + "\n";
        }
        if (result.length() != 0) {result = result.substring(0, result.length()-1);}
        return result;
    }
}
