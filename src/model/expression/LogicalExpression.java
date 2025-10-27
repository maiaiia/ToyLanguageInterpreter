package model.expression;

import exception.InvalidOperandTypeException;
import exception.UnknownOperatorException;
import model.value.BooleanValue;
import model.value.IValue;
import model.value.Type;
import state.ISymbolTable;

public class LogicalExpression implements IExpression {
    IExpression left;
    IExpression right;
    String operator;

    private boolean getBoolValue(IExpression expression, ISymbolTable symbolTable) throws InvalidOperandTypeException {
        IValue value = expression.evaluate(symbolTable);
        if(! (value.getType() == Type.BOOLEAN)){
            throw new InvalidOperandTypeException();
        }
        return ((BooleanValue)value).getValue();
    }

    @Override
    public IValue evaluate(ISymbolTable symbolTable) throws InvalidOperandTypeException {
        boolean leftBoolValue = getBoolValue(left, symbolTable);
        boolean rightBoolValue = getBoolValue(right, symbolTable);

        boolean result = switch (operator) {
            case "&&" -> leftBoolValue && rightBoolValue;
            case "||" -> leftBoolValue || rightBoolValue;
            default -> throw new UnknownOperatorException();
        };
        return new BooleanValue(result);
    }

    @Override
    public String toString()
    {
        return left.toString() + " " + operator + " " + right.toString();
    }
}
