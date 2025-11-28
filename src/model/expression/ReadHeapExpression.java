package model.expression;

import exception.InvalidAddressException;
import exception.InvalidExpressionTypeException;
import model.adt.IDictionary;
import model.value.IValue;
import model.value.RefValue;
import state.heap.IHeap;
import state.symboltable.ISymbolTable;

public class ReadHeapExpression implements IExpression {
    private final IExpression expression;

    public ReadHeapExpression(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public IValue evaluate(ISymbolTable symbolTable, IHeap heap) throws InvalidExpressionTypeException, InvalidAddressException {
        var expressionResult = expression.evaluate( symbolTable, heap);
        if (! (expressionResult instanceof RefValue)){
            throw new InvalidExpressionTypeException();
        }
        int address = ((RefValue)expressionResult).getAddress();
        return heap.read(address);
    }

    @Override
    public IExpression deepCopy() {
        return new ReadHeapExpression(expression.deepCopy());
    }

    @Override
    public String toString() {
        return "readHeap(" +  expression.toString() + ")";
    }

}
