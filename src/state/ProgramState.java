package state;

import model.adt.IDictionary;
import model.statement.IStatement;
import model.value.IValue;
import state.executionstack.IExecutionStack;
import state.filetable.IFileTable;
import state.heap.IHeap;
import state.output.IOutput;

import java.io.*;

public class ProgramState {
    private final IExecutionStack executionStack;
    private final IDictionary<String, IValue> symbolTable;
    private final IOutput output;
    private final IFileTable fileTable;
    private final IHeap heap;
    private final IStatement originalProgram;

    public ProgramState(IDictionary<String, IValue> symbolTable, IExecutionStack executionStack, IOutput output, IFileTable fileTable, IHeap heap, IStatement originalProgram) {
        this.symbolTable = symbolTable;
        this.executionStack = executionStack;
        this.output = output;
        this.fileTable = fileTable;
        this.heap = heap;
        this.originalProgram = originalProgram.deepCopy();
        this.executionStack.push(originalProgram);
    }

    public IExecutionStack getExecutionStack() {
        return executionStack;
    }

    public IDictionary<String, IValue> getSymbolTable() {
        return symbolTable;
    }

    public IOutput getOutput() {
        return output;
    }

    public IHeap getHeap() {
        return heap;
    }

    public IFileTable getFileTable() {return fileTable;}

    public String toString() {
        StringBuilder result = new StringBuilder(executionStack.toString() +
                "\nSYMBOL TABLE:\n" + symbolTable.toString() +
                "\nHEAP:\n" + heap.toString() +
                "\n" + output.toString() +
                "\n" + fileTable.toString());
        return result.toString();
    }
}
