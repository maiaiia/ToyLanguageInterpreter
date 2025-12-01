package model.statement;

import state.ProgramState;

public class NoOperationStatement implements IStatement {
    @Override
    public ProgramState execute(ProgramState programState) {
        return null;
    }

    @Override
    public String toString(){
        return "";
    }

    @Override
    public IStatement deepCopy() {
        return new NoOperationStatement();
    }
}
