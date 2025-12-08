package model.expression;

import exception.InvalidOperandTypeException;
import exception.UnknownOperatorException;
import model.adt.IDictionary;
import model.type.BooleanType;
import model.type.IType;
import model.value.BooleanValue;
import model.value.IValue;
import state.heap.IHeap;
import state.symboltable.ISymbolTable;

public record LogicalExpression(IExpression left, IExpression right, String operator) implements IExpression {

    private boolean getBoolValue(IExpression expression, ISymbolTable symbolTable, IHeap heap) throws InvalidOperandTypeException {
        IValue value = expression.evaluate(symbolTable, heap);
        if (!(value.getType().equals(new BooleanType()))) {
            throw new InvalidOperandTypeException();
        }
        return ((BooleanValue) value).getValue();
    }

    @Override
    public IValue evaluate(ISymbolTable symbolTable, IHeap heap) throws InvalidOperandTypeException {
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
    public String toString() {
        return left.toString() + " " + operator + " " + right.toString();
    }

    @Override
    public IType typecheck(IDictionary<String, IType> typeEnvironment) {
        var leftType = left.typecheck(typeEnvironment);
        if (!leftType.equals(new BooleanType())) {
            throw new InvalidOperandTypeException("Left operand is not a Boolean");
        }
        var rightType = right.typecheck(typeEnvironment);
        if (!rightType.equals(new BooleanType())) {
            throw new InvalidOperandTypeException("Right operand is not a Boolean");
        }
        return new BooleanType();
    }

    @Override
    public IExpression deepCopy() {
        return new LogicalExpression(left.deepCopy(), right.deepCopy(), operator);
    }
}
