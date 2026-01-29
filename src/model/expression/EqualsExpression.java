package model.expression;

import exception.InvalidOperandTypeException;
import model.adt.IDictionary;
import model.type.BooleanType;
import model.type.IType;
import model.value.BooleanValue;
import model.value.IValue;
import state.heap.IHeap;
import state.symboltable.ISymbolTable;

public record EqualsExpression(
        IExpression left, IExpression right
) implements IExpression{
    @Override
    public IValue evaluate(ISymbolTable symbolTable, IHeap heap) {
        return new BooleanValue(left.evaluate(symbolTable, heap).equals(right.evaluate(symbolTable, heap)));
    }

    @Override
    public String toString() {
        return left + " == " + right;
    }

    @Override
    public IType typecheck(IDictionary<String, IType> typeEnvironment) {
        IType leftType = left.typecheck(typeEnvironment);
        IType rightType = right.typecheck(typeEnvironment);
        if (!leftType.equals(rightType)) {
            throw new InvalidOperandTypeException("Left and right types don't match");
        }
        return new BooleanType();
    }

    @Override
    public IExpression deepCopy() {
        return new EqualsExpression(left.deepCopy(), right.deepCopy());
    }
}
