package model.adt;

import exception.ValueNotFoundException;

//TODO question - should i implement these using IValue or a generic class T
public class DynamicArrayList<T> implements  IList<T> {
    static final int DEFAULT_CAPACITY = 10;

    private T[] data;
    private int capacity;
    private int size;

    public DynamicArrayList(int initialCapacity) {
        this.data = (T[]) new Object[initialCapacity]; //downcasting, but is it ok?
        this.capacity = initialCapacity;
        this.size = 0;
    }
    public DynamicArrayList() {
        this(DEFAULT_CAPACITY);
    }
    private void checkIndex(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        checkIndex(index);
        return data[index];
    }

    @Override
    public void append(T value) {
        if (this.size() == this.capacity)
            this.grow();
        this.data[size++] = value;
    }

    @Override
    public void insert(int index, T value) throws IndexOutOfBoundsException {
        checkIndex(index);
        if (size == this.capacity) {
            this.grow();
        }
        for (int i = size; i > index; i--) {
            this.data[i] = data[i - 1];
        }
        this.data[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removed = this.data[index];
        for (int i = index; i < size - 1; i++) {
            this.data[i] = this.data[i + 1];
        }
        this.size--;
        if (size >= DEFAULT_CAPACITY * 2 && size < this.capacity / 2) {
            shrink();
        }
        return removed;
    }

    @Override
    public int find(T value) throws ValueNotFoundException {
        for (int i = 0; i < size; i++) {
            if (data[i] == value) {
                return i;
            }
        }
        throw new ValueNotFoundException();
    }

    // -----------
    void grow() {
        T[] newData = (T[]) new Object[this.capacity * 2];
        for (int i = 0; i < this.capacity; i++) {
            newData[i] = this.data[i];
        }
        this.data = newData;
        this.capacity = this.capacity * 2;
    }
    void shrink() {
        T[] newData = (T[]) new Object[this.capacity / 2];
        for (int i = 0; i < this.size; i++) {
            newData[i] = this.data[i];
        }
        this.data = newData;
        this.capacity = this.capacity / 2;
    }

}
