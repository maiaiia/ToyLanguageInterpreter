package model.expression;

import exception.VariableNotDefinedException;
import model.adt.IDictionary;
import model.value.IValue;
import state.heap.IHeap;
import state.symboltable.ISymbolTable;

public class VariableExpression implements IExpression {
    private final String variableName;
    public VariableExpression(String variableName) {
        this.variableName = variableName;
    }
    @Override
    public IValue evaluate(ISymbolTable symbolTable, IHeap heap) {
        if (!symbolTable.contains(variableName)) {
            throw new VariableNotDefinedException(variableName);
        }
        return symbolTable.get(variableName);
    }

    @Override
    public String toString()
    {
        return variableName;
    }

    @Override
    public IExpression deepCopy() {
        return new VariableExpression(variableName);
    }
}
