package controller;

import exception.ExecutionStackEmptyException;
import exception.OutOfBoundsIndexException;
import model.statement.IStatement;
import repository.IRepository;
import state.ProgramState;

public class Controller implements IController {
    private final IRepository repository;
    private int displayFlag = 0;

    public Controller(IRepository repository) {
        this.repository = repository;

    }
    @Override
    public void setDisplayFlag() {displayFlag = 1;}
    @Override
    public void resetDisplayFlag() {displayFlag = 0;}
    @Override
    public void setDisplayFlag(int displayFlag) {this.displayFlag = displayFlag;}
    @Override
    public int getDisplayFlag() {return displayFlag;}


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
    public ProgramState moveToNextProgramState() throws OutOfBoundsIndexException {
        return repository.getNextProgramState();
    }
}
