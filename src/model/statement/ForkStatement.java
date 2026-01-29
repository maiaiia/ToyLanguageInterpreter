package model.statement;

import model.adt.IDictionary;
import model.type.IType;
import state.ProgramState;
import state.executionstack.ExecutionStack;

public record ForkStatement(IStatement program) implements IStatement {

    @Override
    public ProgramState execute(ProgramState programState) {
        var executionStack = new ExecutionStack();
        var symbolTable = programState.getSymbolTable().deepCopy();
        var heap = programState.getHeap();
        var fileTable = programState.getFileTable();
        var output = programState.getOutput();
        var lockTable = programState.getLockTable();
        var forkedProgram = program.deepCopy();
        return new ProgramState(symbolTable, executionStack, output, fileTable, heap, lockTable, forkedProgram);
    }

    @Override
    public IStatement deepCopy() {
        return new ForkStatement(program.deepCopy());
    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnvironment) {
        program.typecheck(typeEnvironment.copy());
        return typeEnvironment;
    }

    @Override
    public String toString() {
        return "fork(" + program + ")";
    }
}
