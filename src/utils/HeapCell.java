package utils;

public class HeapCell {
    private final String address;
    private final String value;

    public HeapCell(String address, String value) {
        this.address = address;
        this.value = value;
    }

    public String getAddress() {
        return address;
    }
    public String getValue() {
        return value;
    }
}
