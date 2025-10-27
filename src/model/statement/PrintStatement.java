package model.statement;

import model.value.IValue;
import state.ProgramState;

// TODO this needs an EXPRESSION as an argument!!!
public class PrintStatement implements IStatement {
    private final IValue value;

    public PrintStatement(IValue value) {
        this.value = value;
    }

    @Override
    public ProgramState execute(ProgramState programState) {
        programState.getOutput().append(value);
        return programState;
    }

    @Override
    public String toString(){
        return "Print(" +  value.toString() + ");";
    }
}
