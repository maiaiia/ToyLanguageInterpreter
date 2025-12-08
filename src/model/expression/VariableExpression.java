package model.expression;

import exception.VariableNotDefinedException;
import model.adt.IDictionary;
import model.type.IType;
import model.value.IValue;
import state.heap.IHeap;
import state.symboltable.ISymbolTable;

public record VariableExpression(String variableName) implements IExpression {
    @Override
    public IValue evaluate(ISymbolTable symbolTable, IHeap heap) {
        if (!symbolTable.contains(variableName)) {
            throw new VariableNotDefinedException(variableName);
        }
        return symbolTable.get(variableName);
    }

    @Override
    public String toString() {
        return variableName;
    }

    @Override
    public IType typecheck(IDictionary<String, IType> typeEnvironment) {
        return typeEnvironment.search(variableName);
    }

    @Override
    public IExpression deepCopy() {
        return new VariableExpression(variableName);
    }
}
