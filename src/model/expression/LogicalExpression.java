package model.expression;

import exception.InvalidOperandTypeException;
import exception.UnknownOperatorException;
import model.adt.IDictionary;
import model.type.BooleanType;
import model.value.BooleanValue;
import model.value.IValue;

public class LogicalExpression implements IExpression {
    private final IExpression left;
    private final IExpression right;
    private final String operator;

    public LogicalExpression(IExpression left, IExpression right, String operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    private boolean getBoolValue(IExpression expression, IDictionary<String, IValue> symbolTable) throws InvalidOperandTypeException {
        IValue value = expression.evaluate(symbolTable);
        if(! (value.getType().equals(new BooleanType()))) {
            throw new InvalidOperandTypeException();
        }
        return ((BooleanValue)value).getValue();
    }

    @Override
    public IValue evaluate(IDictionary<String, IValue> symbolTable) throws InvalidOperandTypeException {
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

    @Override
    public IExpression deepCopy() {
        return new LogicalExpression(left.deepCopy(), right.deepCopy(), operator);
    }
}
