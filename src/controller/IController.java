package controller;

import repository.IRepository;
import state.ProgramState;

import java.io.PrintWriter;
import java.util.List;

public interface IController {
    List<ProgramState> removeCompletedPrograms(List<ProgramState> programStates);
    void executeOneStepAllPrograms(List<ProgramState> programStates) throws InterruptedException;
    void allStep() throws InterruptedException;

    PrintWriter getWriter();
}
