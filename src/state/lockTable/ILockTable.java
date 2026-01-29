package state.lockTable;

public interface ILockTable {
    int addLock();
    boolean contains(int lockId);
    int getLockOwner(int lockId);
    void acquireLock(int lockId, int processId);
    void releaseLock(int lockId);
}
