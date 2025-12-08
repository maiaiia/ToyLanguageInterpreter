package model.statement.heapStatements;

import com.sun.jdi.InvalidTypeException;
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

public class AllocateHeapStatement implements IStatement {
    private final String variableName;
    private final IExpression expression;

    public AllocateHeapStatement(String variableName, IExpression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws InvalidVariableTypeException, VariableNotDefinedException, InvalidAddressException {
        if (!programState.getSymbolTable().contains(variableName)) {
            throw new VariableNotDefinedException(variableName);
        }
        IValue variableValue = programState.getSymbolTable().get(variableName);
        IValue expressionValue = expression.evaluate(programState.getSymbolTable(), programState.getHeap());

        if (!variableValue.getType().equals(new RefType(expressionValue.getType()))) {
            throw new InvalidVariableTypeException(variableName, new RefType(expressionValue.getType()));
        }

        int address = programState.getHeap().allocate(expressionValue);
        var newRef = new RefValue(address, expressionValue.getType());
        programState.getSymbolTable().add(variableName, newRef);
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new AllocateHeapStatement(variableName, expression.deepCopy());
    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnvironment) {
        IType variableType = typeEnvironment.search(variableName);
        IType expressionType = expression.typecheck(typeEnvironment);
        if (variableType.equals(new RefType(expressionType)))
            return typeEnvironment;
        throw new InvalidExpressionTypeException("Heap Allocation: Reference inner type and Expression type mismatch");
    }

    @Override
    public String toString() {
        return "new(" + variableName + ", " + expression.toString() + ")";
    }
}
