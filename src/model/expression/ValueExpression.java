package model.expression;


import model.value.IValue;
import state.heap.IHeap;
import state.symboltable.ISymbolTable;

public record ValueExpression(IValue value) implements IExpression {

    @Override
    public IValue evaluate(ISymbolTable symbolTable, IHeap heap) {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public IExpression deepCopy() {
        return new ValueExpression(value.deepCopy());
    }
}
