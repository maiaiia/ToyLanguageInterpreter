package model.expression;

import exception.InvalidOperandTypeException;
import exception.UnknownOperatorException;
import model.adt.IDictionary;
import model.type.IType;
import model.type.IntegerType;
import model.value.BooleanValue;
import model.value.IValue;
import model.value.IntegerValue;
import state.heap.IHeap;
import state.symboltable.ISymbolTable;

public record RelationalExpression(IExpression left, IExpression right, String operator) implements IExpression {

    private int getIntValue(IExpression expression, ISymbolTable symbolTable, IHeap heap) throws InvalidOperandTypeException {
        IValue value = expression.evaluate(symbolTable, heap);
        if (!value.getType().equals(new IntegerType())) {
            throw new InvalidOperandTypeException();
        }
        return ((IntegerValue) value).getValue();
    }

    @Override
    public IValue evaluate(ISymbolTable symbolTable, IHeap heap) {
        int leftIntValue = getIntValue(left, symbolTable, heap);
        int rightIntValue = getIntValue(right, symbolTable, heap);
        boolean result = switch (operator) {
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
        return new RelationalExpression(left.deepCopy(), right.deepCopy(), operator);
    }

    @Override
    public String toString() {
        return left.toString() + " " + operator + " " + right.toString();
    }

    @Override
    public IType typecheck(IDictionary<String, IType> typeEnvironment) {
        var leftType = left.typecheck(typeEnvironment);
        if (!leftType.equals(new IntegerType())) {
            throw new InvalidOperandTypeException("Left type is not Integer");
        }
        var rightType = right.typecheck(typeEnvironment);
        if (!rightType.equals(new IntegerType())) {
            throw new InvalidOperandTypeException("Right type is not Integer");
        }
        return new IntegerType();
    }
}
