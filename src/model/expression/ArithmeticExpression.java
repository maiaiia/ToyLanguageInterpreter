package model.expression;

import exception.DivisionByZeroException;
import exception.InvalidOperandTypeException;
import exception.UnknownOperatorException;
import model.type.IntegerType;
import model.value.IntegerValue;
import model.adt.IDictionary;
import model.value.IValue;
import state.heap.IHeap;

public class ArithmeticExpression implements IExpression {
    private final IExpression left;
    private final IExpression right;
    private final char operator;

    public ArithmeticExpression(IExpression left, IExpression right, char operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    private int getIntValue(IExpression expression, IDictionary<String, IValue> symbolTable, IHeap heap) throws InvalidOperandTypeException {
        IValue value = expression.evaluate(symbolTable, heap);
        if (!value.getType().equals(new IntegerType())) {
            throw new InvalidOperandTypeException();
        }
        return ((IntegerValue) value).getValue();
    }

    @Override
    public IValue evaluate(IDictionary<String, IValue> symbolTable, IHeap heap) throws InvalidOperandTypeException, DivisionByZeroException {
        int leftIntValue = getIntValue(left, symbolTable, heap);
        int rightIntValue = getIntValue(right, symbolTable, heap);
        int result = switch (operator){
            case '+' -> leftIntValue + rightIntValue;
            case '-' -> leftIntValue - rightIntValue;
            case '*' -> leftIntValue * rightIntValue;
            case '/' -> getDivisionResult(leftIntValue, rightIntValue);
            default -> throw new UnknownOperatorException();
        };
        return new IntegerValue(result);
    }

    private static int getDivisionResult(int leftIntValue, int rightIntValue) throws DivisionByZeroException {
        try{
            return leftIntValue / rightIntValue;
        }
        catch (Exception e){
            throw new DivisionByZeroException();
        }
    }
    @Override
    public String toString() {
        return left.toString() + operator + right.toString();
    }

    @Override
    public IExpression deepCopy() {
        return new  ArithmeticExpression(left.deepCopy(), right.deepCopy(), operator);
    }
}
