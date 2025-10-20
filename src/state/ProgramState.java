package state;

public class ProgramState {
    IExecutionStack executionStack;
    ISymbolTable symbolTable;
    IOutput output;

    public ProgramState(ISymbolTable symbolTable, IExecutionStack executionStack, IOutput output) {
        this.symbolTable = symbolTable;
        this.executionStack = executionStack;
        this.output = output;
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
}
