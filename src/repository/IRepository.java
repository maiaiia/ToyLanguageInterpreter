package repository;

import exception.OutOfBoundsIndexException;
import state.ProgramState;

import java.util.List;

public interface IRepository {
    List<ProgramState> getProgramList();
    void setProgramList(List<ProgramState> programList);
    void logProgramStateExecution(ProgramState programState);
    void addLogSeparator();

}
