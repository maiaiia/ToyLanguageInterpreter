package model.statement;

import exception.ConditionNotBooleanException;
import model.expression.IExpression;
import model.value.BooleanValue;
import model.value.IValue;
import state.ProgramState;

public class IfStatement implements IStatement {
    private final IExpression condition;
    private final IStatement thenStatement;
    private final IStatement elseStatement;

    public IfStatement(IExpression condition, IStatement thenStatement, IStatement elseStatement) {
        this.condition = condition;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    @Override
    public ProgramState execute(ProgramState programState) {
        IValue conditionValue = condition.evaluate(programState.getSymbolTable(), programState.getHeap());
        if (!(conditionValue instanceof BooleanValue)) {
            throw new ConditionNotBooleanException();
        }
        boolean conditionBoolean = ((BooleanValue) conditionValue).getValue();
        IStatement chosenStatement = conditionBoolean ? thenStatement : elseStatement;
        programState.getExecutionStack().push(chosenStatement);
        return null;
    }

    @Override
    public String toString(){
        return "(If " +  condition.toString() + " Then " + thenStatement.toString() + " Else " + elseStatement.toString() + ")";
    }

    @Override
    public IStatement deepCopy() {
        return new IfStatement(condition.deepCopy(), thenStatement.deepCopy(), elseStatement.deepCopy());
    }
}
