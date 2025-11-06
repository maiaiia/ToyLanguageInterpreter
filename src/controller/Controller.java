package controller;

import exception.ExecutionStackEmptyException;
import exception.OutOfBoundsIndexException;
import model.statement.IStatement;
import repository.IRepository;
import state.ProgramState;

public class Controller implements IController {
    private final IRepository repository;
    private boolean displayFlag = false;
    public Controller(IRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProgramState executeOneStep(ProgramState programState) {
        var executionStack = programState.getExecutionStack();
        if (executionStack.isEmpty()) {
            throw new ExecutionStackEmptyException();
        }
        IStatement statement = executionStack.pop();
        var ret =  statement.execute(programState);
        repository.logCurrentState();
        // I know we're supposed to call this from executeProgramState, but it makes more sense to me to do it here,
        // since we want every single step to be logged, whenever this function is called
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
    public ProgramState moveToNextProgramState() throws OutOfBoundsIndexException { //TODO generic exception is bad :(
        return repository.getNextProgramState();
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
