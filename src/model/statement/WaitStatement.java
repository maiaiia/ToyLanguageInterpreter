package model.statement;

import model.adt.IDictionary;
import model.expression.ValueExpression;
import model.type.IType;
import model.value.IntegerValue;
import state.ProgramState;

public class WaitStatement implements IStatement {
    private final int duration;
    public WaitStatement(int duration) {
        this.duration = duration;
    }

    @Override
    public ProgramState execute(ProgramState programState) {
        if (this.duration == 0)
            return null;
        programState.getExecutionStack().push(new CompoundStatement(
                new PrintStatement(new ValueExpression(new IntegerValue(this.duration))),
                new WaitStatement(this.duration - 1))
        );
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new WaitStatement(this.duration);
    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnvironment) {
        return typeEnvironment;
    }

    @Override
    public String toString() {
        return "wait(" +  this.duration + ")";
    }
}
