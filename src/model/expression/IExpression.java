package model.expression;

import model.adt.IDictionary;
import model.value.IValue;
import state.heap.IHeap;

public interface IExpression {
    IValue evaluate(IDictionary<String, IValue> symbolTable, IHeap heap);
    String toString();
    IExpression deepCopy();
}
