package controller;

import exception.OutOfBoundsIndexException;
import state.ProgramState;

import java.io.PrintWriter;

public interface IController {
    ProgramState executeProgramState(ProgramState programState);
    ProgramState executeCurrentProgram();
    ProgramState getCurrentProgramState();
    ProgramState moveToNextProgramState() throws OutOfBoundsIndexException;


    PrintWriter getWriter();
    void setDisplayFlag();
    void resetDisplayFlag();
    boolean getDisplayFlag();
}
