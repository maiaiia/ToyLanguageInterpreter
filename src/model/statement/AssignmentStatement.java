package model.statement;

import exception.InvalidVariableTypeException;
import exception.VariableNotDefinedException;
import model.expression.IExpression;
import model.value.IValue;
import programState.ProgramState;

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
        IValue expressionResult = expression.evaluate(programState.getSymbolTable());
        if (symbolTable.get(symbolName).getType() != expressionResult.getType()) {
            throw new InvalidVariableTypeException();
        }
        programState.getSymbolTable().add(symbolName, expressionResult);
        return programState;
    }

    @Override
    public String toString() {
        return symbolName + " = " + expression;
    }
}
