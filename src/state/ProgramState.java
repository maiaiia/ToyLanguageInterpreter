package state;

import model.adt.IDictionary;
import model.adt.IList;
import model.adt.IStack;
import model.statement.IStatement;
import model.value.IValue;

public class ProgramState {
    IStack<IStatement> executionStack;
    IDictionary<String, IValue> symbolTable;
    IList<String> output;

    public ProgramState(IDictionary<String, IValue> symbolTable, IStack<IStatement> executionStack, IList<String> output) {
        this.symbolTable = symbolTable;
        this.executionStack = executionStack;
        this.output = output;
    }

    public IStack<IStatement> getExecutionStack() {
        return executionStack;
    }

    public IDictionary<String, IValue> getSymbolTable() {
        return symbolTable;
    }

    public IList<String> getOutput() {
        return output;
    }
}
