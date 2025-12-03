package model.statement;

import state.ProgramState;
import state.executionstack.ExecutionStack;
import state.symboltable.SymbolTable;

public record ForkStatement(IStatement program) implements IStatement {

    @Override
    public ProgramState execute(ProgramState programState) {
        var executionStack = new ExecutionStack();
        var symbolTable = programState.getSymbolTable().deepCopy();
        var heap = programState.getHeap();
        var fileTable = programState.getFileTable();
        var output = programState.getOutput();
        var forkedProgram = program.deepCopy();
        return new ProgramState(symbolTable, executionStack, output, fileTable, heap, forkedProgram);
    }

    @Override
    public IStatement deepCopy() {
        return new ForkStatement(program.deepCopy());
    }

    @Override
    public String toString() {
        return "fork(" + program + ")";
    }
}
