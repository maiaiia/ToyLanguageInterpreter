package model.statement;

import state.ProgramState;

public interface IStatement {
    ProgramState execute(ProgramState programState);
}
