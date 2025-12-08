package model.expression;

import model.adt.IDictionary;
import model.type.IType;
import model.value.IValue;
import state.heap.IHeap;
import state.symboltable.ISymbolTable;

public interface IExpression {
    IValue evaluate(ISymbolTable symbolTable, IHeap heap);
    String toString();
    IType typecheck(IDictionary<String, IType> typeEnvironment);
    IExpression deepCopy();
}
