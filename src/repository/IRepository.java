package repository;

import exception.OutOfBoundsIndexException;
import state.ProgramState;

import java.util.List;

public interface IRepository {
    void addState(ProgramState programState);
    void logProgramStateExecution(ProgramState programState);
    void addLogSeparator();

    List<ProgramState> getProgramList();
    void setProgramList(List<ProgramState> programList);
}
