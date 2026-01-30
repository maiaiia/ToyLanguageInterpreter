package controller;

import repository.IRepository;
import state.ProgramState;

import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface IController {
    List<ProgramState> removeCompletedPrograms(List<ProgramState> programStates);
    void executeOneStepAllPrograms(List<ProgramState> programStates) throws InterruptedException, ExecutionException;
    void allStep() throws InterruptedException;

    PrintWriter getWriter();
}
