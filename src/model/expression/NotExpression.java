package model.expression;

import exception.InvalidExpressionTypeException;
import model.adt.IDictionary;
import model.type.BooleanType;
import model.type.IType;
import model.value.BooleanValue;
import model.value.IValue;
import state.heap.IHeap;
import state.symboltable.ISymbolTable;

public record NotExpression(IExpression expression) implements IExpression {

    @Override
    public IValue evaluate(ISymbolTable symbolTable, IHeap heap) {
        IValue value = expression.evaluate(symbolTable, heap);
        var boolValue = ((BooleanValue) value).getValue();
        return new BooleanValue(!boolValue);
    }

    @Override
    public IType typecheck(IDictionary<String, IType> typeEnvironment) {
        if (!expression.typecheck(typeEnvironment).equals(new BooleanType())) {
            throw new InvalidExpressionTypeException("Expression is not a boolean type");
        }
        return new BooleanType();
    }

    @Override
    public IExpression deepCopy() {
        return new NotExpression(expression.deepCopy());
    }

    @Override
    public String toString() {
        return "!" + expression.toString();
    }
}
