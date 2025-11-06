package model.value;


import model.type.IType;
import model.type.StringType;

public class StringValue implements IValue {
    private final String value;
    public StringValue(String value) {
        this.value = value;
    }

    @Override
    public IType getType() {
        return new StringType();
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public IValue deepCopy() {
        return new StringValue(value);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof StringValue) {
            return value.equals(((StringValue) o).value);
        }
        if (o instanceof String) {
            return value.equals(o);
        }
        return false;
    }
}
