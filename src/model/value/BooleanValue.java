package model.value;


import model.type.*;

public class BooleanValue implements IValue {
    private final boolean value;
    public BooleanValue(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public IType getType() {
        return new BooleanType();
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
