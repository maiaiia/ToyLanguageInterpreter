package model.statement;

import state.ProgramState;

public class CompoundStatement implements IStatement {
    private final IStatement statement1;
    private final IStatement statement2;

    public CompoundStatement(IStatement statement1, IStatement statement2) {
        this.statement2 = statement2;
        this.statement1 = statement1;
    }

    @Override
    public ProgramState execute(ProgramState programState) {
        programState.getExecutionStack().push(statement2);
        programState.getExecutionStack().push(statement1);
        return programState;
    }

    @Override
    public String toString(){
        return "("+statement1.toString() + "; " +statement2.toString() + ")";
    }

    @Override
    public IStatement deepCopy() {
        return new CompoundStatement(statement1.deepCopy(), statement2.deepCopy());
    }
}
