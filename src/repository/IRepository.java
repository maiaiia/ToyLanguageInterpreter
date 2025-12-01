package repository;

import exception.OutOfBoundsIndexException;
import state.ProgramState;

import java.util.List;

public interface IRepository {
    void addState(ProgramState programState);
    ProgramState getCurrentProgramState();
    ProgramState getNextProgramState() throws OutOfBoundsIndexException;
    void logCurrentState();
    void logCurrentState(boolean displaySeparator);
    void logAllPrograms();

    List<ProgramState> getProgramList();
    void setProgramList(List<ProgramState> programList);
}
