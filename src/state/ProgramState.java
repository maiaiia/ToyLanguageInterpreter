package state;

import exception.ExecutionStackEmptyException;
import model.statement.IStatement;
import state.executionstack.IExecutionStack;
import state.filetable.IFileTable;
import state.heap.IHeap;
import state.output.IOutput;
import state.symboltable.ISymbolTable;

public class ProgramState {
    private final ISymbolTable symbolTable;
    private final IExecutionStack executionStack;
    private final IOutput output;
    private final IFileTable fileTable;
    private final IHeap heap;
    private final IStatement originalProgram;
    private final int id;
    static private int nextId = 0;


    public ProgramState(ISymbolTable symbolTable, IExecutionStack executionStack, IOutput output, IFileTable fileTable, IHeap heap, IStatement originalProgram) {
        this.symbolTable = symbolTable;
        this.executionStack = executionStack;
        this.output = output;
        this.fileTable = fileTable;
        this.heap = heap;
        this.originalProgram = originalProgram.deepCopy();
        this.executionStack.push(originalProgram);
        this.id = getNextId();
    }

    static synchronized int getNextId() {
        return nextId++;
    }

    public IExecutionStack getExecutionStack() {
        return executionStack;
    }

    public ISymbolTable getSymbolTable() {
        return symbolTable;
    }

    public IOutput getOutput() {
        return output;
    }

    public IHeap getHeap() {
        return heap;
    }

    public IFileTable getFileTable() {return fileTable;}


    public ProgramState executeOneStep() throws ExecutionStackEmptyException {
        var executionStack = this.executionStack;
        if (executionStack.isEmpty()) {
            throw new ExecutionStackEmptyException();
        }
        IStatement statement = executionStack.pop();
        return statement.execute(this);
    }

    public Boolean isNotCompleted() {
        return !executionStack.isEmpty();
    }

    public String toString() {
        StringBuilder result = new StringBuilder(
                "PROGRAM STATE " + this.id + ":\n" +
                executionStack.toString() + "\n" +
                symbolTable.toString() +
                "\nHEAP:\n" + heap.toString() + "\n" +
                output.toString() + "\n" +
                fileTable.toString());
        return result.toString();
    }
}
