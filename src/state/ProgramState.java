package state;

import exception.ExecutionStackEmptyException;
import model.statement.IStatement;
import state.barrierTable.IBarrierTable;
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
    private final IBarrierTable barrierTable;
    private final IStatement originalProgram;
    private final int id;
    static private int nextId = 0;


    public ProgramState(ISymbolTable symbolTable,
                        IExecutionStack executionStack,
                        IOutput output,
                        IFileTable fileTable,
                        IHeap heap,
                        IBarrierTable barrierTable,
                        IStatement originalProgram) {
        //originalProgram.typecheck(new HashMapDictionary<>()); WARNING this will break on fork
        this.symbolTable = symbolTable;
        this.executionStack = executionStack;
        this.output = output;
        this.fileTable = fileTable;
        this.heap = heap;
        this.barrierTable = barrierTable;
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

    public IBarrierTable getBarrierTable() {return barrierTable;}

    public int getId() {return id;}

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
                "Thread " + this.id + ":\n" +
                "EXECUTION STACK:\n" + executionStack.toString() + "\n" +
                "SYMBOL TABLE:\n" + symbolTable.toString() + "\n" +
                "HEAP:\n" + heap.toString() + "\n" +
                "OUTPUT:\n" + output.toString() + "\n" +
                "FILE TABLE:\n" + fileTable.toString() + "\n" +
                "BARRIER TABLE:\n" +  barrierTable.toString());
        return result.toString();
    }

    public String getOriginalProgram() {
        return originalProgram.toString();
    }
}
