package state;

import exception.FileOperationException;
import model.adt.IDictionary;
import model.adt.IFileTable;
import model.adt.IList;
import model.adt.IStack;
import model.statement.IStatement;
import model.value.IValue;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ProgramState {
    private final IStack<IStatement> executionStack;
    private final IDictionary<String, IValue> symbolTable;
    private final IList<String> output;
    private final IFileTable fileTable;
    private final IStatement originalProgram;

    public ProgramState(IDictionary<String, IValue> symbolTable, IStack<IStatement> executionStack, IList<String> output, IFileTable fileTable, IStatement originalProgram) {
        this.symbolTable = symbolTable;
        this.executionStack = executionStack;
        this.output = output;
        this.fileTable = fileTable;
        this.originalProgram = originalProgram.deepCopy();
        this.executionStack.push(originalProgram);
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

    public IFileTable getFileTable() {return fileTable;}

    public String toString() {
        return "Execution Stack:\n" + executionStack.toString() +
                "\nSymbol Table:\n" + symbolTable.toString() +
                "\nOutput:\n" + output.toString() +
                "\nFile Table:\n" + fileTable.toString();
    }
}
