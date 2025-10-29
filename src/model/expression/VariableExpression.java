package model.expression;

import exception.VariableNotDefinedException;
import model.adt.IDictionary;
import model.value.IValue;

public class VariableExpression implements IExpression {
    private final String variableName;
    public VariableExpression(String variableName) {
        this.variableName = variableName;
    }
    @Override
    public IValue evaluate(IDictionary<String, IValue> symbolTable) {
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
}
