package model.expression;

import model.value.IValue;
import state.IExecutionStack;
import state.ISymbolTable;

public class LogicalExpression implements IExpression {
    IExpression lhs;
    IExpression rhs;

    @Override
    public IValue evaluate(ISymbolTable symbolTable) {
        return null;
    }
}
