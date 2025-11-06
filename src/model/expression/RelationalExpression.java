package model.expression;

import exception.InvalidOperandTypeException;
import exception.UnknownOperatorException;
import model.adt.IDictionary;
import model.type.IntegerType;
import model.value.BooleanValue;
import model.value.IValue;
import model.value.IntegerValue;

public class RelationalExpression implements IExpression {
    private final IExpression left;
    private final IExpression right;
    private final String operator;

    public RelationalExpression(IExpression left, IExpression right, String operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    private int getIntValue(IExpression expression, IDictionary<String, IValue> symbolTable) throws InvalidOperandTypeException {
        IValue value = expression.evaluate(symbolTable);
        if (! value.getType().equals(new IntegerType())) {
            throw new InvalidOperandTypeException();
        }
        return ((IntegerValue) value).getValue();
    }

    @Override
    public IValue evaluate(IDictionary<String, IValue> symbolTable) {
        int leftIntValue = getIntValue(left, symbolTable);
        int rightIntValue = getIntValue(right, symbolTable);
        boolean result = switch (operator){
            case "==" -> leftIntValue == rightIntValue;
            case "!=" -> leftIntValue != rightIntValue;
            case "<" -> leftIntValue < rightIntValue;
            case "<=" -> leftIntValue <= rightIntValue;
            case ">" -> leftIntValue > rightIntValue;
            case ">=" -> leftIntValue >= rightIntValue;
            default -> throw new UnknownOperatorException();
        };
        return new BooleanValue(result);
    }
}
