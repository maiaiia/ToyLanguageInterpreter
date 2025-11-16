package model.expression;

import exception.InvalidOperandTypeException;
import exception.UnknownOperatorException;
import model.adt.IDictionary;
import model.type.IntegerType;
import model.value.BooleanValue;
import model.value.IValue;
import model.value.IntegerValue;
import state.heap.IHeap;

public class RelationalExpression implements IExpression {
    private final IExpression left;
    private final IExpression right;
    private final String operator;

    public RelationalExpression(IExpression left, IExpression right, String operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    private int getIntValue(IExpression expression, IDictionary<String, IValue> symbolTable, IHeap heap) throws InvalidOperandTypeException {
        IValue value = expression.evaluate(symbolTable, heap);
        if (! value.getType().equals(new IntegerType())) {
            throw new InvalidOperandTypeException();
        }
        return ((IntegerValue) value).getValue();
    }

    @Override
    public IValue evaluate(IDictionary<String, IValue> symbolTable, IHeap heap) {
        int leftIntValue = getIntValue(left, symbolTable, heap);
        int rightIntValue = getIntValue(right, symbolTable, heap);
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

    @Override
    public IExpression deepCopy() {
        return new  RelationalExpression(left.deepCopy(), right.deepCopy(), operator);
    }

    @Override
    public String toString(){
        return left.toString() + " " + operator + " " + right.toString();
    }
}
