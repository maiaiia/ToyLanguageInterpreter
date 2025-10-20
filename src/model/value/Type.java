package model.value;

public enum Type {
    INTEGER,
    BOOLEAN;

    IValue getDefaultValue(){
        if (this.equals(INTEGER)){
            return new IntegerValue(0);
        }
        else if (this.equals(BOOLEAN)){
            return new BooleanValue(false);
        }
        return null;
    }
}
