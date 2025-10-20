package model.statement;

import state.ProgramState;

public class NoOperationStatement implements IStatement {
    @Override
    public ProgramState execute(ProgramState programState) {
        return programState;
    }
}
