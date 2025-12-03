package controller;

import exception.OutOfBoundsIndexException;
import state.ProgramState;

import java.io.PrintWriter;
import java.util.List;

public interface IController {
    ProgramState executeProgramState(ProgramState programState);
    ProgramState executeCurrentProgram();
    ProgramState moveToNextProgramState() throws OutOfBoundsIndexException;
    List<ProgramState> removeCompletedPrograms(List<ProgramState> programStates);

    PrintWriter getWriter();
    void setDisplayFlag();
    void resetDisplayFlag();
    boolean getDisplayFlag();
}
