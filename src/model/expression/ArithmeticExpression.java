package model.expression;

import exception.DivisionByZeroException;
import exception.InvalidOperandTypeException;
import exception.UnknownOperatorException;
import model.value.IValue;
import model.value.IntegerValue;
import model.value.Type;
import state.ISymbolTable;

public class ArithmeticExpression implements IExpression {
    private final IExpression left;
    private final IExpression right;
    private final char operator;

    public ArithmeticExpression(IExpression left, IExpression right, char operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    private int getIntValue(IExpression expression, ISymbolTable symbolTable) throws InvalidOperandTypeException {
        IValue value = expression.evaluate(symbolTable);
        if (value.getType() != Type.INTEGER) {
            throw new InvalidOperandTypeException();
        }
        return ((IntegerValue) value).getValue();
    }

    @Override
    public IValue evaluate(ISymbolTable symbolTable) throws InvalidOperandTypeException, DivisionByZeroException {
        int leftIntValue = getIntValue(left, symbolTable);
        int rightIntValue = getIntValue(right, symbolTable);
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
}
