package model.type;

import model.value.IValue;

public interface IType {
    IValue getDefaultValue();
    boolean equals(IType other);
}
