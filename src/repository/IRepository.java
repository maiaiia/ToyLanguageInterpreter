package repository;

import exception.OutOfBoundsIndexException;
import programState.ProgramState;

public interface IRepository {
    void addState(ProgramState programState);
    ProgramState getCurrentProgramState();
    public ProgramState getNextProgramState() throws OutOfBoundsIndexException; //TODO needs better exception
    void logProgramStateExecution();

}
