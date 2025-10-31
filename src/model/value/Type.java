package model.value;

public enum Type {
    INTEGER,
    BOOLEAN,
    STRING;

    public IValue getDefaultValue(){
        if (this.equals(INTEGER)){
            return new IntegerValue(0);
        }
        else if (this.equals(BOOLEAN)){
            return new BooleanValue(false);
        }
        else if (this.equals(STRING)){
            return new StringValue("");
        }
        return null;
    }
}
