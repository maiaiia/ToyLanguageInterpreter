package model.statement.barrierStatements;

import exception.BarrierDoesNotExistException;
import exception.BarrierFullException;
import exception.InvalidVariableTypeException;
import model.adt.IDictionary;
import model.statement.IStatement;
import model.type.IType;
import model.type.IntegerType;
import model.value.IValue;
import model.value.IntegerValue;
import state.ProgramState;

public record AwaitBarrierStatement(
        String barrierName
) implements IStatement {
    @Override
    public ProgramState execute(ProgramState programState) {
        if (!programState.getSymbolTable().contains(barrierName)) {
            throw new BarrierDoesNotExistException(barrierName);
        }
        IValue barrierId = programState.getSymbolTable().get(barrierName);
        if (!barrierId.getType().equals(new IntegerType())) {
            throw new InvalidVariableTypeException(barrierName, new IntegerType());
        }
        try{
            programState.getBarrierTable().awaitBarrier(((IntegerValue) barrierId).getValue(), programState.getId());
            programState.getExecutionStack().push(this.deepCopy());
        }
        catch (BarrierFullException _){}
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new AwaitBarrierStatement(barrierName);
    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnvironment) {
        IType barrierType = typeEnvironment.get(barrierName);
        if (!barrierType.equals(new IntegerType()))
            throw new InvalidVariableTypeException(barrierName, new IntegerType());
        return typeEnvironment;
    }

    @Override
    public String toString() {
        return "AwaitBarrier(" + barrierName + ")";
    }
}

