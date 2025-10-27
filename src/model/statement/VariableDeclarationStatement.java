package model.statement;

import exception.VariableAlreadyDeclaredException;
import model.value.BooleanValue;
import model.value.IValue;
import model.value.IntegerValue;
import model.value.Type;
import state.ProgramState;

public class VariableDeclarationStatement implements IStatement {
    private final String variableName;
    private final Type variableType;

    public VariableDeclarationStatement(String variableName,  Type variableType) {
        this.variableName = variableName;
        this.variableType = variableType;
    }

    @Override
    public ProgramState execute(ProgramState programState) {
        var symbolTable =  programState.getSymbolTable();
        if (symbolTable.contains(variableName)) {
            throw new VariableAlreadyDeclaredException();
        }
        symbolTable.add(variableName, variableType.getDefaultValue());
        return programState;
    }

    @Override
    public String toString(){
        return variableType.toString() + " " + variableName;
    }
}
