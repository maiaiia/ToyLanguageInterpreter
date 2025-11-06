package controller;

import exception.OutOfBoundsIndexException;
import state.ProgramState;

public interface IController {
    ProgramState executeOneStep(ProgramState programState);
    ProgramState executeProgramState(ProgramState programState);
    ProgramState executeCurrentProgram();
    ProgramState executeAll();
    ProgramState getCurrentProgramState();
    ProgramState moveToNextProgramState() throws OutOfBoundsIndexException;

    void setDisplayFlag();
    void resetDisplayFlag();
    void setDisplayFlag(int displayFlag);
    int getDisplayFlag();
}
