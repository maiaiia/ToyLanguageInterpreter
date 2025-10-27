package repository;

import state.ProgramState;

public interface IRepository {
    void addState(ProgramState programState);
    ProgramState getCurrentProgramState();
}
