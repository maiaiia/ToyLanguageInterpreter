package controller;

import exception.OutOfBoundsIndexException;
import state.ProgramState;

public interface IController {
    ProgramState executeOneStep(ProgramState programState);
    ProgramState executeProgramState(ProgramState programState);
    ProgramState executeCurrentProgram();
    ProgramState getCurrentProgramState();
    ProgramState moveToNextProgramState() throws OutOfBoundsIndexException;

    void setDisplayFlag();
    void resetDisplayFlag();
    int getDisplayFlag();
}
