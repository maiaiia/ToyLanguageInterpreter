package model.expression;

import exception.InvalidOperandTypeException;
import exception.InvalidOperatorException;
import model.value.BooleanValue;
import model.value.IValue;
import model.value.Type;
import state.ISymbolTable;

public class LogicalExpression implements IExpression {
    IExpression left;
    IExpression right;
    String operator;

    private boolean getBoolValue(IExpression expression, ISymbolTable symbolTable) throws InvalidOperandTypeException{
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
            case "and" -> leftBoolValue && rightBoolValue;
            case "or" -> leftBoolValue || rightBoolValue;
            case "xor" -> leftBoolValue ^ rightBoolValue;
            default -> throw new InvalidOperatorException();
        };
        return new BooleanValue(result);
    }
}
