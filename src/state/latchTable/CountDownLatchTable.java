package state.latchTable;

import exception.LatchNotFoundException;
import model.adt.HashMapDictionary;
import model.adt.IDictionary;

public class CountDownLatchTable implements ILatchTable {
    private final IDictionary<Integer, Integer> latchTable = new HashMapDictionary<>();
    private int firstFreePosition = 1;

    @Override
    public synchronized int newLatch(int latchSize) {
        latchTable.add(firstFreePosition, latchSize);
        return firstFreePosition++;
    }

    @Override
    public synchronized int getCount(int latchId) {
        if (!latchTable.contains(latchId)) {
            throw new LatchNotFoundException(latchId);
        }
        return latchTable.get(latchId);
    }

    @Override
    public synchronized void countDown(int latchId) {
        int count = getCount(latchId);
        if (count > 0){
            latchTable.add(latchId, count - 1);
        }
    }

    @Override
    public String toString(){
        return latchTable.toString();
    }
}
