package state.countSemaphore;

public interface ISemaphoreTable {
    // semaphoreID -> (size, owner_list)
    int addSemaphore(int size);
    void acquireSemaphore(int semaphoreId, int threadId);
    void releaseSemaphore(int semaphoreId, int threadId);
}
