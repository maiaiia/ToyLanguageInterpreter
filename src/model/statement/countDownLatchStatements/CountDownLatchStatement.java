package model.statement.countDownLatchStatements;

import exception.InvalidVariableTypeException;
import exception.VariableNotDefinedException;
import model.adt.IDictionary;
import model.statement.IStatement;
import model.type.IType;
import model.type.IntegerType;
import model.value.IValue;
import model.value.IntegerValue;
import state.ProgramState;

public record CountDownLatchStatement(
        String latchName
) implements IStatement {
    @Override
    public ProgramState execute(ProgramState programState) {
        if (!programState.getSymbolTable().contains(latchName)) {
            throw new VariableNotDefinedException(latchName);
        }
        IValue latchId = programState.getSymbolTable().get(latchName);
        if (!latchId.getType().equals(new IntegerType())) {
            throw new InvalidVariableTypeException(latchName, new IntegerType());
        }
        programState.getLatchTable().countDown(((IntegerValue)latchId).getValue());
        programState.getOutput().append(String.valueOf(programState.getId()));
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new CountDownLatchStatement(latchName);
    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnvironment) {
        if (!typeEnvironment.get(latchName).equals(new IntegerType())) {
            throw new InvalidVariableTypeException(latchName, new IntegerType());
        }
        return typeEnvironment;
    }

    @Override
    public String toString(){
        return "CountDownLatch(" +  latchName + ")";
    }
}
