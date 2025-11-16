package model.expression;

import exception.InvalidOperandTypeException;
import exception.UnknownOperatorException;
import model.adt.IDictionary;
import model.type.BooleanType;
import model.value.BooleanValue;
import model.value.IValue;
import state.heap.IHeap;

public class LogicalExpression implements IExpression {
    private final IExpression left;
    private final IExpression right;
    private final String operator;

    public LogicalExpression(IExpression left, IExpression right, String operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    private boolean getBoolValue(IExpression expression, IDictionary<String, IValue> symbolTable, IHeap heap) throws InvalidOperandTypeException {
        IValue value = expression.evaluate(symbolTable, heap);
        if(! (value.getType().equals(new BooleanType()))) {
            throw new InvalidOperandTypeException();
        }
        return ((BooleanValue)value).getValue();
    }

    @Override
    public IValue evaluate(IDictionary<String, IValue> symbolTable, IHeap heap) throws InvalidOperandTypeException {
        boolean leftBoolValue = getBoolValue(left, symbolTable, heap);
        boolean rightBoolValue = getBoolValue(right, symbolTable, heap);

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
