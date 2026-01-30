package state.lockTable;

import exception.LockUnavailableException;

public interface ILockTable {
    int addLock();
    boolean contains(int lockId);
    int getLockOwner(int lockId);
    void acquireLock(int lockId, int processId) throws LockUnavailableException;
    void releaseLock(int lockId);
}
