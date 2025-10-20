package model.expression;


import model.value.IValue;
import state.ISymbolTable;

public interface IExpression {
    IValue evaluate(ISymbolTable symbolTable);
}
