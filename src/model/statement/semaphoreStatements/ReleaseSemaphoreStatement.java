package model.statement.semaphoreStatements;

import exception.InvalidVariableTypeException;
import exception.SemaphoreNotFoundException;
import model.adt.IDictionary;
import model.statement.IStatement;
import model.type.IType;
import model.type.IntegerType;
import model.value.IValue;
import model.value.IntegerValue;
import state.ProgramState;

public class ReleaseSemaphoreStatement implements IStatement {
    private final String semaphoreName;

    public ReleaseSemaphoreStatement(String semaphoreName) {
        this.semaphoreName = semaphoreName;
    }

    @Override
    public ProgramState execute(ProgramState programState) {
        if (!programState.getSymbolTable().contains(semaphoreName)) {
            throw new SemaphoreNotFoundException(semaphoreName);
        }
        IValue semaphoreId = programState.getSymbolTable().get(semaphoreName);
        if (!semaphoreId.getType().equals(new IntegerType())) {
            throw new SemaphoreNotFoundException(semaphoreName);
        }
        programState.getSemaphoreTable().releaseSemaphore(((IntegerValue)semaphoreId).getValue(), programState.getId());
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new ReleaseSemaphoreStatement(semaphoreName);
    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnvironment) {
        IType semaphoreType = typeEnvironment.get(semaphoreName);
        if (!semaphoreType.equals(new IntegerType())){
            throw new InvalidVariableTypeException(semaphoreName, new IntegerType());
        }
        return typeEnvironment;
    }

    @Override
    public String toString() {
        return "Release(" +  semaphoreName + ")";
    }
}
