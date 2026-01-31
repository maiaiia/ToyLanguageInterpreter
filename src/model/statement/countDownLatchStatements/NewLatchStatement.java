package model.statement.countDownLatchStatements;

import exception.InvalidExpressionTypeException;
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

public record NewLatchStatement(
        String latchName,
        IExpression counterExpression
) implements IStatement {

    @Override
    public ProgramState execute(ProgramState programState) {
        if (!programState.getSymbolTable().contains(latchName)) {
            throw new VariableNotDefinedException(latchName);
        }
        if (!programState.getSymbolTable().get(latchName).getType().equals(new IntegerType())) {
            throw new InvalidVariableTypeException(latchName, new IntegerType());
        }
        IValue latchCount = counterExpression.evaluate(programState.getSymbolTable(), programState.getHeap());
        if (!latchCount.getType().equals(new IntegerType())) {
            throw new InvalidExpressionTypeException(new IntegerType());
        }
        int latchId = programState.getLatchTable().newLatch(((IntegerValue) latchCount).getValue());
        programState.getSymbolTable().add(latchName, new IntegerValue(latchId));
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new NewLatchStatement(latchName, counterExpression.deepCopy());
    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnvironment) {
        IType latchType = typeEnvironment.get(latchName);
        if (!latchType.equals(new IntegerType())) {
            throw new InvalidVariableTypeException(latchName, new IntegerType());
        }
        IType counterType = counterExpression.typecheck(typeEnvironment);
        if (!counterType.equals(new IntegerType())) {
            throw new InvalidVariableTypeException(latchName, new IntegerType());
        }
        return typeEnvironment;
    }

    @Override
    public String toString(){
        return "NewLatch(" + latchName + ", " + counterExpression + ")";
    }
}
