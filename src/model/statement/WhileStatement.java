package model.statement;

import exception.InvalidExpressionTypeException;
import model.expression.IExpression;
import model.type.BooleanType;
import model.value.BooleanValue;
import state.ProgramState;

public class WhileStatement implements IStatement {
    IExpression condition;
    IStatement body;
    public WhileStatement(IExpression condition, IStatement body) {
        this.condition = condition;
        this.body = body;
    }

    @Override
    public ProgramState execute(ProgramState programState) {
        var expressionResult = condition.evaluate(programState.getSymbolTable(), programState.getHeap());
        if (! expressionResult.getType().equals(new BooleanType())) {
            throw new InvalidExpressionTypeException(new BooleanType());
        }
        BooleanValue booleanValue = (BooleanValue) expressionResult;
        if (booleanValue.getValue()) {
            programState.getExecutionStack().push(this.deepCopy());
            programState.getExecutionStack().push(this.body);
        }
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new WhileStatement(condition.deepCopy(), body.deepCopy());
    }

    @Override
    public String toString() {
        return "(while (" + condition.toString() + ") " + body.toString() + ")";
    }
}
