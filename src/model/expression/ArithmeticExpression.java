package model.expression;

import exception.DivisionByZeroException;
import exception.InvalidOperandTypeException;
import exception.UnknownOperatorException;
import model.adt.IDictionary;
import model.type.IType;
import model.type.IntegerType;
import model.value.IntegerValue;
import model.value.IValue;
import state.heap.IHeap;
import state.symboltable.ISymbolTable;

public record ArithmeticExpression(IExpression left, IExpression right, char operator) implements IExpression {

    private int getIntValue(IExpression expression, ISymbolTable symbolTable, IHeap heap) throws InvalidOperandTypeException {
        IValue value = expression.evaluate(symbolTable, heap);
        if (!value.getType().equals(new IntegerType())) {
            throw new InvalidOperandTypeException();
        }
        return ((IntegerValue) value).getValue();
    }

    @Override
    public IValue evaluate(ISymbolTable symbolTable, IHeap heap) throws InvalidOperandTypeException, DivisionByZeroException {
        int leftIntValue = getIntValue(left, symbolTable, heap);
        int rightIntValue = getIntValue(right, symbolTable, heap);
        int result = switch (operator) {
            case '+' -> leftIntValue + rightIntValue;
            case '-' -> leftIntValue - rightIntValue;
            case '*' -> leftIntValue * rightIntValue;
            case '/' -> getDivisionResult(leftIntValue, rightIntValue);
            default -> throw new UnknownOperatorException();
        };
        return new IntegerValue(result);
    }

    private static int getDivisionResult(int leftIntValue, int rightIntValue) throws DivisionByZeroException {
        try {
            return leftIntValue / rightIntValue;
        } catch (Exception e) {
            throw new DivisionByZeroException();
        }
    }

    @Override
    public String toString() {
        return left.toString() + operator + right.toString();
    }

    @Override
    public IType typecheck(IDictionary<String, IType> typeEnvironment) {
        IType leftType = left.typecheck(typeEnvironment);
        if (!leftType.equals(new IntegerType())) {
            throw new InvalidOperandTypeException("Left operand is not an Integer");
        }
        IType rightType = right.typecheck(typeEnvironment);
        if (!rightType.equals(new IntegerType())) {
            throw new InvalidOperandTypeException("Right operand is not an Integer");
        }
        return new IntegerType();

    }

    @Override
    public IExpression deepCopy() {
        return new ArithmeticExpression(left.deepCopy(), right.deepCopy(), operator);
    }
}
