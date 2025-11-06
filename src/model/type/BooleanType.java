package model.type;

import model.value.BooleanValue;
import model.value.IValue;

public class BooleanType implements IType {

    @Override
    public IValue getDefaultValue() {
        return new BooleanValue(false);
    }

    @Override
    public boolean equals(IType other) {
        return other instanceof BooleanType;
    }

    @Override
    public IType deepCopy() {
        return new  BooleanType();
    }

    @Override
    public String toString() {
        return "bool";
    }
}
