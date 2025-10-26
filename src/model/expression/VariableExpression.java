package model.expression;

import exception.VariableNotDefinedException;
import model.value.IValue;
import state.ISymbolTable;

public class VariableExpression implements IExpression {
    private final String variableName;
    VariableExpression(String variableName) {
        this.variableName = variableName;
    }
    @Override
    public IValue evaluate(ISymbolTable symbolTable) {
        if (!symbolTable.symbolIsDefined(variableName)) {
            throw new VariableNotDefinedException(variableName);
        }
        return symbolTable.getVariableValue(variableName);
    }
}
