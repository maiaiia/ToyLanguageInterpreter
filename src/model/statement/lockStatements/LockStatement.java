package model.statement.lockStatements;

import exception.InvalidVariableTypeException;
import exception.LockUnavailableException;
import exception.VariableNotDefinedException;
import model.adt.IDictionary;
import model.statement.IStatement;
import model.type.IType;
import model.type.IntegerType;
import model.value.IValue;
import model.value.IntegerValue;
import state.ProgramState;

public class LockStatement implements IStatement {
    private final String lockName;

    public LockStatement(String variableName) {
        this.lockName = variableName;
    }

    @Override
    public ProgramState execute(ProgramState programState) {
        if (!programState.getSymbolTable().contains(lockName)) {
            throw new VariableNotDefinedException(lockName);
        }
        IValue variableValue = programState.getSymbolTable().get(lockName);
        if (!variableValue.getType().equals(new IntegerType())) {
            throw new InvalidVariableTypeException(lockName, new IntegerType());
        }
        int intVariableValue = ((IntegerValue) variableValue).getValue();
        // lockTable handles checking if the lock is in the table
        try{
            programState.getLockTable().acquireLock(intVariableValue, programState.getId());
        } catch (LockUnavailableException e) {
            programState.getExecutionStack().push(this.deepCopy());
        }
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new LockStatement(lockName);
    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnvironment) {
        IType variableType = typeEnvironment.get(lockName);
        if (! variableType.equals(new IntegerType())) {
            throw new InvalidVariableTypeException(lockName, new IntegerType());
        }
        return typeEnvironment;
    }

    @Override
    public String toString(){
        return "Lock(" + lockName + ")";
    }
}
