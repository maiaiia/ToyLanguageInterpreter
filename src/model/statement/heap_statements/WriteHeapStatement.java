package model.statement.heap_statements;

import model.statement.IStatement;
import state.ProgramState;

public class WriteHeapStatement implements IStatement {
    @Override
    public ProgramState execute(ProgramState programState) {
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return null;
    }
}
