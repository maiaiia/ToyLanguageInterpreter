package model.value;


public class StringValue implements IValue {
    private final String value;
    public StringValue(String value) {
        this.value = value;
    }

    @Override
    public Type getType() {
        return Type.STRING;
    }

    @Override
    public String toString() {
        return value;
    }

}
