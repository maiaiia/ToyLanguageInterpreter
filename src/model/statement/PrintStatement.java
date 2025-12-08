package model.statement;

import model.adt.IDictionary;
import model.expression.IExpression;
import model.type.IType;
import model.value.IValue;
import state.ProgramState;

public class PrintStatement implements IStatement {
    private final IExpression expression;

    public PrintStatement(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState programState) {
        IValue result = expression.evaluate(programState.getSymbolTable(), programState.getHeap());
        //may throw an exception if the expression is not correct
        programState.getOutput().append(result.toString());
        return null;
    }

    @Override
    public String toString(){
        return "Print(" +  expression.toString() + ")";
    }

    @Override
    public IStatement deepCopy() {
        return new PrintStatement(expression.deepCopy());
    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnvironment) {
        expression.typecheck(typeEnvironment);
        return typeEnvironment;
    }
}
