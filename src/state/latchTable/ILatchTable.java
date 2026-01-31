package state.latchTable;

public interface ILatchTable {
    int newLatch(int latchSize);
    int getCount(int latchId);
    void countDown(int latchId);
}
