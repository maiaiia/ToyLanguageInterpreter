package utils;

public class SemaphoreTableCell {
    private final String semaphoreId, size, threads;

    public SemaphoreTableCell(String semaphoreId, String size, String threads) {
        this.semaphoreId = semaphoreId;
        this.size = size;
        this.threads = threads;
    }

    public String getSemaphoreId() {
        return semaphoreId;
    }
    public String getSize() {
        return size;
    }
    public String getThreads() {
        return threads;
    }
}
