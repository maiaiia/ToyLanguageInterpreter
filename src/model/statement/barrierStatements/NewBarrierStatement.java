package model.statement.barrierStatements;

import exception.InvalidOperandTypeException;
import exception.InvalidVariableTypeException;
import exception.VariableNotDefinedException;
import model.adt.IDictionary;
import model.expression.IExpression;
import model.statement.IStatement;
import model.type.IType;
import model.type.IntegerType;
import model.value.IValue;
import model.value.IntegerValue;
import state.ProgramState;

public record NewBarrierStatement(
        String barrierName,
        IExpression barrierSize
) implements IStatement {

    @Override
    public ProgramState execute(ProgramState programState) {
        if (!programState.getSymbolTable().contains(barrierName)) {
            throw new VariableNotDefinedException(barrierName);
        }
        if (!programState.getSymbolTable().get(barrierName).getType().equals(new IntegerType())) {
            throw new InvalidVariableTypeException(barrierName, new IntegerType());
        }
        IValue size = barrierSize.evaluate(programState.getSymbolTable(), programState.getHeap());
        if (!size.getType().equals(new IntegerType())) {
            throw new InvalidOperandTypeException("Barrier size should be of type Integer");
        }
        int barrierId = programState.getBarrierTable().addBarrier(((IntegerValue) size).getValue());
        programState.getSymbolTable().add(barrierName, new IntegerValue(barrierId));
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new NewBarrierStatement(barrierName, barrierSize.deepCopy());
    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnvironment) {
        IType barrierType = typeEnvironment.get(barrierName);
        if (!barrierType.equals(new IntegerType())) {
            throw new InvalidVariableTypeException(barrierName, new IntegerType());
        }
        IType sizeType = barrierSize.typecheck(typeEnvironment);
        if (!sizeType.equals(new IntegerType())) {
            throw new InvalidOperandTypeException("Barrier size should be of type Integer");
        }
        return typeEnvironment;
    }

    @Override
    public String toString() {
        return "newBarrier(" + barrierName + ", " +  barrierSize + ")";
    }
}
