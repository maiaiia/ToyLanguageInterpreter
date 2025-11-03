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

    @Override
    public boolean equals(Object o) {
        if (o instanceof BooleanValue) {
            return value == ((BooleanValue) o).value;
        }
        if (o instanceof boolean) {
            return o.equals(value);
        }
        return false;
    }
}
