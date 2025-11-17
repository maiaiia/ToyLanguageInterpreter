package repository;

import exception.OutOfBoundsIndexException;
import state.ProgramState;

public interface IRepository {
    void addState(ProgramState programState);
    ProgramState getCurrentProgramState();
    ProgramState getNextProgramState() throws OutOfBoundsIndexException;
    void logCurrentState();
    void logCurrentState(boolean displaySeparator);
    void logAllPrograms();
}
