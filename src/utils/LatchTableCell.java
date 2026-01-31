package utils;

public class LatchTableCell {
    private final String latchId, latchCount;

    public LatchTableCell(String latchId, String latchCount) {
        this.latchId = latchId;
        this.latchCount = latchCount;
    }

    public String getLatchId() {
        return latchId;
    }
    public String getLatchCount() {
        return latchCount;
    }
}
