package state;

import model.adt.IDictionary;
import model.adt.IList;
import model.adt.IStack;
import model.statement.IStatement;
import model.value.IValue;

public class ProgramState {
    private final IStack<IStatement> executionStack;
    private final IDictionary<String, IValue> symbolTable;
    private final IList<String> output;
    private final IStatement originalProgram;
    private int displayFlag = 0;

    public ProgramState(IDictionary<String, IValue> symbolTable, IStack<IStatement> executionStack, IList<String> output, IStatement originalProgram) {
        this.symbolTable = symbolTable;
        this.executionStack = executionStack;
        this.output = output;
        this.originalProgram = originalProgram.deepCopy();
        this.executionStack.push(this.originalProgram);
    }

    public void setDisplayFlag() {displayFlag = 1;}
    public void resetDisplayFlag() {displayFlag = 0;}
    public void setDisplayFlag(int displayFlag) {this.displayFlag = displayFlag;}
    public int getDisplayFlag() {return displayFlag;}

    public IStack<IStatement> getExecutionStack() {
        return executionStack;
    }

    public IDictionary<String, IValue> getSymbolTable() {
        return symbolTable;
    }

    public IList<String> getOutput() {
        return output;
    }

    public String toString() {
        return "Execution Stack:\n" + executionStack.toString() +
                "\nSymbol Table:\n" + symbolTable.toString() +
                "\nOutput:\n" + output.toString();
    }
}
