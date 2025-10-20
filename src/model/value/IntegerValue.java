package model.value;

public class IntegerValue implements IValue {
    private final int value;
    public IntegerValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public Type getType() {
        return Type.INTEGER;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
