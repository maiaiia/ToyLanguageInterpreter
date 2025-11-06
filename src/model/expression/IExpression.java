package model.expression;

import model.adt.IDictionary;
import model.value.IValue;

public interface IExpression {
    IValue evaluate(IDictionary<String, IValue> symbolTable);
    String toString();
    IExpression deepCopy();
}
