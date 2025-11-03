package controller;

import exception.OutOfBoundsIndexException;
import programState.ProgramState;

public interface IController {
    ProgramState executeOneStep(ProgramState programState);
    ProgramState executeProgramState(ProgramState programState);
    ProgramState executeCurrentProgram();
    ProgramState executeAll();
    ProgramState getCurrentProgramState();
    ProgramState moveToNextProgramState() throws OutOfBoundsIndexException;
}
