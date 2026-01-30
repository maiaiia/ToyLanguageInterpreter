package model.statement.semaphoreStatements;

import exception.InvalidOperandTypeException;
import exception.InvalidVariableTypeException;
import exception.SemaphoreNotFoundException;
import model.adt.IDictionary;
import model.expression.IExpression;
import model.statement.IStatement;
import model.type.IType;
import model.type.IntegerType;
import model.value.IValue;
import model.value.IntegerValue;
import state.ProgramState;

public class createSemaphoreStatement implements IStatement {
    private final String semaphoreName;
    private final IExpression semaphoreSize;

    public createSemaphoreStatement(String semaphoreName, IExpression semaphoreSize) {
        this.semaphoreName = semaphoreName;
        this.semaphoreSize = semaphoreSize;
    }

    @Override
    public ProgramState execute(ProgramState programState) {
        IValue sizeAsInt = semaphoreSize.evaluate(programState.getSymbolTable(), programState.getHeap());
        if (!sizeAsInt.getType().equals(new IntegerType())){
            throw new InvalidOperandTypeException("Semaphore size must be integer");
        }
        if (!programState.getSymbolTable().contains(semaphoreName)){
            throw new SemaphoreNotFoundException(semaphoreName);
        }
        if (!programState.getSymbolTable().get(semaphoreName).getType().equals(new IntegerType())){
            throw new InvalidVariableTypeException(semaphoreName, new IntegerType());
        }
        int semaphoreId = programState.getSemaphoreTable().addSemaphore(((IntegerValue)sizeAsInt).getValue());
        programState.getSymbolTable().add(semaphoreName, new IntegerValue(semaphoreId));
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new createSemaphoreStatement(semaphoreName, semaphoreSize.deepCopy());
    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnvironment) {
        IType semaphoreType = typeEnvironment.get(semaphoreName);
        if (!semaphoreType.equals(new IntegerType()))
            throw new InvalidVariableTypeException(semaphoreName, new IntegerType());
        IType expressionType = semaphoreSize.typecheck(typeEnvironment);
        if (!expressionType.equals(new IntegerType()))
            throw new InvalidVariableTypeException(semaphoreName, new IntegerType());
        return typeEnvironment;
    }

    @Override
    public String toString() {
        return "createSemaphore(" + semaphoreSize +")";
    }
}
