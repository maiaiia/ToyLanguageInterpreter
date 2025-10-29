package controller;

import exception.ExecutionStackEmptyException;
import exception.OutOfBoundsIndexException;
import model.statement.IStatement;
import repository.IRepository;
import state.ProgramState;

public class Controller implements IController {
    private final IRepository repository;
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
        return statement.execute(programState);
    }

    @Override
    public ProgramState executeProgramState(ProgramState programState) {
        return null;
    }

    @Override
    public ProgramState executeCurrentProgram() {
        ProgramState programState = getCurrentProgramState();
        while (true){
            try {
                programState = executeOneStep(programState);
            } catch (ExecutionStackEmptyException e) {
                break;
            }
        }
        moveToNextProgramState();
        return programState;
    }

    @Override
    public ProgramState executeAll() {
        return null;
    }

    @Override
    public ProgramState getCurrentProgramState() {
        return repository.getCurrentProgramState();
    }

    @Override
    public ProgramState moveToNextProgramState() throws RuntimeException { //TODO generic exception is bad :(
        return repository.getNextProgramState();
    }
}
