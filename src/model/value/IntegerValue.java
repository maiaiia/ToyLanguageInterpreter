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

    @Override
    public boolean equals(Object o) {
        if (o instanceof IntegerValue) {
            return value == ((IntegerValue) o).value;
        }
        if (o instanceof Integer) {
            return value == (Integer) o;
        }
        return false;
    }
}
