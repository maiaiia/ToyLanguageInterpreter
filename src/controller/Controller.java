package controller;

import exception.ExecutionStackEmptyException;
import model.statement.IStatement;
import state.IExecutionStack;
import state.ProgramState;

public class Controller implements IController {

    @Override
    public ProgramState executeOneStep(ProgramState programState) {
        IExecutionStack executionStack = programState.getExecutionStack();
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
    public ProgramState executeAll() {
        return null;
    }

    @Override
    public void displayCurrentProgramState() {

    }
}
