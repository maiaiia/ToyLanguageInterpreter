package model.type;

import model.value.IValue;
import model.value.IntegerValue;

public class IntegerType implements IType {
    @Override
    public IValue getDefaultValue() {
        return new IntegerValue(0);
    }

    @Override
    public boolean equals(IType other) {
        return other instanceof IntegerType;
    }

    @Override
    public IType deepCopy() {
        return new  IntegerType();
    }

    @Override
    public String toString() {
        return "int";
    }
}
