package utils;

public class LockTableCell {
    private final String lockId;
    private final String threadId;
    public LockTableCell(String lockId, String processId) {
        this.lockId = lockId;
        this.threadId = processId;
    }
    public String getLockId() {
        return lockId;
    }
    public String getThreadId() {
        return threadId;
    }
}
