package model.statement.heapStatements;

import exception.InvalidAddressException;
import exception.InvalidExpressionTypeException;
import exception.InvalidVariableTypeException;
import exception.VariableNotDefinedException;
import model.adt.IDictionary;
import model.expression.IExpression;
import model.statement.IStatement;
import model.type.IType;
import model.type.RefType;
import model.value.IValue;
import model.value.RefValue;
import state.ProgramState;

public class WriteHeapStatement implements IStatement {
    private final String variableName;
    private final IExpression expression;

    public WriteHeapStatement(String variableName, IExpression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws VariableNotDefinedException, InvalidVariableTypeException, InvalidAddressException, InvalidExpressionTypeException {
        if (! programState.getSymbolTable().contains(variableName)) {
            throw new VariableNotDefinedException(variableName);
        }
        IValue variableValue = programState.getSymbolTable().get(variableName);
        if (!(variableValue instanceof RefValue)) {
            throw new InvalidVariableTypeException();
        }

        //check if the type of what's written coincides with the inner type of the ref value
        var refValue = (RefValue) variableValue;
        var refType = (RefType)refValue.getType();
        int address = refValue.getAddress();

        IValue expressionResult = expression.evaluate(programState.getSymbolTable(), programState.getHeap());
        if (!expressionResult.getType().equals(refType.getInnerType())) {
            throw new InvalidExpressionTypeException();
        }

        programState.getHeap().write(address, expressionResult); //write checks if the address is defined in the heap table
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new WriteHeapStatement(variableName, expression.deepCopy());
    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnvironment) {
        IType variableType =  typeEnvironment.search(variableName);
        IType expressionType = expression.typecheck(typeEnvironment);
        if (variableType.equals(new RefType(expressionType))) {
            return typeEnvironment;
        }
        throw new InvalidExpressionTypeException("Write Heap: Reference inner type and Expression type mismatch");
    }

    @Override
    public String toString() {
        return "writeHeap(" +  variableName + ", " + expression.toString() + ")";
    }
}
