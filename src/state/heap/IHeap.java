package state.heap;

import exception.InvalidAddressException;
import model.value.IValue;

import java.util.Map;

public interface IHeap {
    int allocate(IValue value);
    void deallocate(int address) throws InvalidAddressException;
    IValue write(int address, IValue value) throws InvalidAddressException;
    IValue read(int address) throws InvalidAddressException;

    public Map<Integer, IValue> getHeap();
}
