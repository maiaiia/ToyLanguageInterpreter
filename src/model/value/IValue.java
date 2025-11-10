package model.value;

import model.type.IType;

public interface IValue {
    IType getType();
    String toString();
    IValue deepCopy();
    boolean equals(Object other);
}
