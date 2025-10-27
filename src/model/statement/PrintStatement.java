package model.statement;

import model.expression.IExpression;
import model.value.IValue;
import state.ProgramState;

//TODO - exception is thrown for wrong expression? or would that be caught before the print statement is created
public class PrintStatement implements IStatement {
    private final IExpression expression;

    public PrintStatement(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState programState) {
        IValue result = expression.evaluate(programState.getSymbolTable());
        //may throw an exception if the expression is not correct
        programState.getOutput().append(result.toString());
        return programState;
    }

    @Override
    public String toString(){
        return "Print(" +  expression.toString() + ")";
    }
}
