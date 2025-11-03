package model.statement;

import programState.ProgramState;

public interface IStatement {
    ProgramState execute(ProgramState programState);
    String toString();
}
