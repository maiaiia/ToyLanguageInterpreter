package utils;

public class BarrierTableCell {
    private final String barrierId, barrierSize, threads;

    public BarrierTableCell(String barrierId, String barrierSize, String threads) {
        this.barrierId = barrierId;
        this.barrierSize = barrierSize;
        this.threads = threads;
    }

    public String getBarrierId() {
        return barrierId;
    }
    public String getBarrierSize() {
        return barrierSize;
    }
    public String getThreads() {
        return threads;
    }
}
