package model.statement.lockStatements;

import exception.InvalidVariableTypeException;
import model.adt.IDictionary;
import model.statement.IStatement;
import model.type.IType;
import model.type.IntegerType;
import model.value.IntegerValue;
import state.ProgramState;

public class newLockStatement implements IStatement {
    private final String variableName;

    public newLockStatement(String variableName) {
        this.variableName = variableName;
    }

    @Override
    public ProgramState execute(ProgramState programState) {
        if (programState.getSymbolTable().contains(variableName) &&
                programState.getSymbolTable().get(variableName).getType().equals(new IntegerType())) {
            int location = programState.getLockTable().addLock();
            programState.getSymbolTable().add(variableName, new IntegerValue(location));
            return null;
        }
        throw new InvalidVariableTypeException(variableName, new IntegerType());
    }

    @Override
    public IStatement deepCopy() {
        return new newLockStatement(this.variableName);
    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnvironment) {
        IType varType = typeEnvironment.get(variableName);
        if (!varType.equals(new IntegerType())) {
            throw new InvalidVariableTypeException(variableName, new IntegerType());
        }
        return typeEnvironment;
    }

    @Override
    public String toString() {
        return "newLock(" +  variableName + ")";
    }
}
