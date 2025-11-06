package repository;

import exception.OutOfBoundsIndexException;
import state.ProgramState;

public interface IRepository {
    void addState(ProgramState programState);
    ProgramState getCurrentProgramState();
    ProgramState getNextProgramState() throws OutOfBoundsIndexException; //TODO needs better exception
    void logCurrentState();
    void logAllPrograms();
}
