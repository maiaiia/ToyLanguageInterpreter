package model.expression;

import model.value.IValue;
import state.heap.IHeap;
import state.symboltable.ISymbolTable;

public interface IExpression {
    IValue evaluate(ISymbolTable symbolTable, IHeap heap);
    String toString();
    IExpression deepCopy();
}
