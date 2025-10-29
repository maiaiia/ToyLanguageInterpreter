package controller;

import state.ProgramState;

public interface IController {
    ProgramState executeOneStep(ProgramState programState);
    ProgramState executeProgramState(ProgramState programState);
    ProgramState executeCurrentProgram();
    ProgramState executeAll();
    ProgramState getCurrentProgramState();
    ProgramState moveToNextProgramState();
}
