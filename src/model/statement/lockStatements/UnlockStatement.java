package model.statement.lockStatements;

import exception.InvalidVariableTypeException;
import exception.VariableNotDefinedException;
import model.adt.IDictionary;
import model.statement.IStatement;
import model.type.IType;
import model.type.IntegerType;
import model.value.IntegerValue;
import state.ProgramState;

public class UnlockStatement implements IStatement {
    private final String lockName;

    public UnlockStatement(String lockName) {
        this.lockName = lockName;
    }

    @Override
    public ProgramState execute(ProgramState programState) {
        if (!programState.getSymbolTable().contains(lockName)) {
            throw new VariableNotDefinedException(lockName);
        }
        if (programState.getSymbolTable().get(lockName).getType().equals(new IntegerType())) {
            throw new InvalidVariableTypeException(lockName, new IntegerType());
        }
        int lockIndex = ((IntegerValue) programState.getSymbolTable().get(lockName)).getValue();
        programState.getLockTable().releaseLock(lockIndex, programState.getId());
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new UnlockStatement(lockName);
    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnvironment) {
        IType lockType = typeEnvironment.get(lockName);
        if (! lockType.equals(new IntegerType())) {
            throw new InvalidVariableTypeException(lockName, new IntegerType());
        }
        return typeEnvironment;
    }

    @Override
    public String toString() {
        return "Release(" + lockName +")";
    }
}
