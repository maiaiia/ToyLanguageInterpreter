package model.expression;

import exception.InvalidAddressException;
import exception.InvalidExpressionTypeException;
import model.adt.IDictionary;
import model.type.IType;
import model.type.RefType;
import model.value.IValue;
import model.value.RefValue;
import state.heap.IHeap;
import state.symboltable.ISymbolTable;

public record ReadHeapExpression(IExpression expression) implements IExpression {

    @Override
    public IValue evaluate(ISymbolTable symbolTable, IHeap heap) throws InvalidExpressionTypeException, InvalidAddressException {
        var expressionResult = expression.evaluate(symbolTable, heap);
        if (!(expressionResult instanceof RefValue)) {
            throw new InvalidExpressionTypeException();
        }
        int address = ((RefValue) expressionResult).getAddress();
        return heap.read(address);
    }

    @Override
    public IExpression deepCopy() {
        return new ReadHeapExpression(expression.deepCopy());
    }

    @Override
    public String toString() {
        return "readHeap(" + expression.toString() + ")";
    }

    @Override
    public IType typecheck(IDictionary<String, IType> typeEnvironment) {
        var type = expression.typecheck(typeEnvironment);
        if (! (type instanceof RefType)) {
            throw new InvalidExpressionTypeException("Expression is not a Ref Type");
        }
        RefType refType = (RefType) type;
        return refType.getInnerType();
    }

}
