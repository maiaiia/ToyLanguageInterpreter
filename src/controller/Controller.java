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
        var ret =  statement.execute(programState);
        programState.logCurrentState();
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
    public ProgramState executeAll() {
        ProgramState programState = null;
        try{
            programState = getCurrentProgramState();
            while(true){
                programState = this.executeCurrentProgram();
            }
        } catch (OutOfBoundsIndexException _) {}
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
}
