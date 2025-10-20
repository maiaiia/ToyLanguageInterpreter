package model.value;


public class BooleanValue implements IValue {
    private final boolean value;
    public BooleanValue(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public Type getType() {
        return Type.BOOLEAN;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
