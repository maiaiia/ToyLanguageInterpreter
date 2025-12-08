package model.statement;

import exception.InvalidExpressionTypeException;
import exception.InvalidVariableTypeException;
import exception.VariableNotDefinedException;
import model.adt.IDictionary;
import model.expression.IExpression;
import model.type.IType;
import model.value.IValue;
import state.ProgramState;

public class AssignmentStatement implements IStatement {
    private final String symbolName;
    private final IExpression expression;

    public AssignmentStatement(String symbolName, IExpression expression) {
        this.symbolName = symbolName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState programState) {
        var symbolTable = programState.getSymbolTable();
        if (! symbolTable.contains(symbolName)) {
            throw new VariableNotDefinedException();
        }
        IValue expressionResult = expression.evaluate(programState.getSymbolTable(), programState.getHeap());
        if (!symbolTable.get(symbolName).getType().equals(expressionResult.getType())) {
            throw new InvalidVariableTypeException();
        }
        programState.getSymbolTable().add(symbolName, expressionResult);
        return null;
    }

    @Override
    public String toString() {
        return symbolName + " = " + expression;
    }

    @Override
    public IStatement deepCopy() {
        return new  AssignmentStatement(symbolName, expression.deepCopy());
    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnvironment) {
        IType variableType = typeEnvironment.search(symbolName);
        IType expressionType = expression.typecheck(typeEnvironment);
        if (variableType.equals(expressionType)) {
            return typeEnvironment;
        }
        throw new InvalidExpressionTypeException("Assignment: left and right hand side type mismatch");
    }
}
