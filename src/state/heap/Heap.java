package state.heap;

import exception.InvalidAddressException;
import model.adt.HashMapDictionary;
import model.adt.IDictionary;
import model.value.IValue;

import java.util.Set;

public class Heap implements IHeap {
    public final IDictionary<Integer, IValue> heap;
    public int nextFree;

    public Heap() {
        this.heap = new HashMapDictionary<>();
        nextFree = 1;
    }

    @Override
    public int allocate(IValue value) {
        heap.add(nextFree, value);
        nextFree++;
        return nextFree - 1;
    }

    @Override
    public void deallocate(int address) throws InvalidAddressException {
        if (! heap.contains(address)) {
            throw new InvalidAddressException(address);
        }
        heap.remove(address);
        if (address == nextFree - 1) {
            nextFree--;
        }
    }

    @Override
    public IValue write(int address, IValue value) throws InvalidAddressException {
        if (! heap.contains(address)) {
            throw new InvalidAddressException(address);
        }
        IValue oldValue = heap.get(address);
        heap.add(address, value);
        return oldValue;
    }

    @Override
    public IValue read(int address) throws InvalidAddressException {
        if (! heap.contains(address)) {
            throw new InvalidAddressException(address);
        }
        return heap.get(address);
    }

    @Override
    public Set<Integer> getAddresses() {
        return heap.keySet();
    }

    @Override
    public String toString() {
        return heap.toString();
    }
}
