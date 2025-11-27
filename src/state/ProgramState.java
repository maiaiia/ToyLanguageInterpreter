package state;

import model.adt.IDictionary;
import model.adt.IList;
import model.statement.IStatement;
import model.value.IValue;
import state.executionstack.IExecutionStack;
import state.heap.IHeap;

import java.io.*;

public class ProgramState {
    private final IExecutionStack executionStack;
    private final IDictionary<String, IValue> symbolTable;
    private final IList<String> output;
    private final IDictionary<String, BufferedReader> fileTable;
    private final IHeap heap;
    private final IStatement originalProgram;

    public ProgramState(IDictionary<String, IValue> symbolTable, IExecutionStack executionStack, IList<String> output, IDictionary<String, BufferedReader> fileTable, IHeap heap, IStatement originalProgram) {
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

    public IList<String> getOutput() {
        return output;
    }

    public IHeap getHeap() {
        return heap;
    }

    public IDictionary<String, BufferedReader> getFileTable() {return fileTable;}

    public String toString() {
        StringBuilder result = new StringBuilder(executionStack.toString() +
                "\nSYMBOL TABLE:\n" + symbolTable.toString() +
                "\nHEAP:\n" + heap.toString() +
                "\nOUTPUT:\n" + output.toString() +
                "\nFILE TABLE:\n");

        for (var key: fileTable.keySet()) {
            result.append(key).append("\n");
        }
        return result.toString();
    }
}
