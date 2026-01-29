package state.lockTable;

import exception.LockNotFoundException;
import exception.LockUnavailableException;
import model.adt.HashMapDictionary;
import model.adt.IDictionary;

public class LockTable implements ILockTable{
    public final IDictionary<Integer, Integer> lockTable;
    private int nextFreeLocation = 0;

    public LockTable() {
        lockTable  = new HashMapDictionary<>();
    }

    @Override
    public synchronized int addLock() {
        lockTable.put(++nextFreeLocation, -1);
        return nextFreeLocation - 1;
    }

    @Override
    public synchronized boolean contains(int lockId) {
        return lockTable.contains(lockId);
    }

    @Override
    public synchronized int getLockOwner(int lockId) {
        if (this.contains(lockId)) {
            return lockTable.get(lockId);
        }
        throw new LockNotFoundException(lockId);
    }

    @Override
    public void acquireLock(int lockId, int processId) {
        if (this.getLockOwner(lockId) == -1) {
            this.lockTable.put(lockId, processId);
        }
        else  {
            throw new LockUnavailableException();
        }
    }

    @Override
    public void releaseLock(int lockId) {
        if (!this.contains(lockId)) {
            throw new LockNotFoundException(lockId);
        }
        this.lockTable.put(lockId, -1);
    }
}
