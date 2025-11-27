package controller;

import controller.garbagecollector.GarbageCollector;
import exception.ExecutionStackEmptyException;
import exception.OutOfBoundsIndexException;
import model.statement.IStatement;
import repository.IRepository;
import state.ProgramState;

import java.io.PrintWriter;

public class Controller implements IController {
    private final IRepository repository;
    private boolean displayFlag = false;
    private final GarbageCollector garbageCollector =  new GarbageCollector();
    public Controller(IRepository repository) {
        this.repository = repository;
    }
    private final PrintWriter writer = new PrintWriter(System.out); //I did this in order to be able to change output dest

    @Override
    public ProgramState executeOneStep(ProgramState programState) {
        var executionStack = programState.getExecutionStack();
        if (executionStack.isEmpty()) {
            throw new ExecutionStackEmptyException();
        }
        IStatement statement = executionStack.pop();

        // I know we're supposed to call this from executeProgramState, but it makes more sense to me to do it here,
        // since we want every single step to be logged, whenever this function is called
        var ret = statement.execute(programState);

        repository.logCurrentState();
        garbageCollector.runGarbageCollector(programState);
        repository.logCurrentState(true);


        if (displayFlag) {
            writer.println(ret);
            writer.flush();
        }

        return ret;
    }

    @Override
    public ProgramState executeProgramState(ProgramState programState) {
        while (true){
            try {
                programState = executeOneStep(programState);
            } catch (ExecutionStackEmptyException e) {
                break;
            }
        }
        return programState;
    }

    @Override
    public ProgramState executeCurrentProgram() {
        ProgramState programState = getCurrentProgramState();
        programState = executeProgramState(programState);
        moveToNextProgramState();
        return programState;
    }

    @Override
    public ProgramState getCurrentProgramState() {
        return repository.getCurrentProgramState();
    }

    @Override
    public ProgramState moveToNextProgramState() throws OutOfBoundsIndexException {
        return repository.getNextProgramState();
    }

    @Override
    public PrintWriter getWriter() {
        return writer;
    }

    @Override
    public void setDisplayFlag() {
        displayFlag = true;
    }

    @Override
    public void resetDisplayFlag() {
        displayFlag = false;
    }

    @Override
    public boolean getDisplayFlag() {
        return displayFlag;
    }
}
