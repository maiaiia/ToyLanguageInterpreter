package model.value;

import model.type.IType;
import model.type.IntegerType;

public class IntegerValue implements IValue {
    private final int value;
    public IntegerValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public IType getType() {
        return new IntegerType();
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
