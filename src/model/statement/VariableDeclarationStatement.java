package model.statement;

import exception.VariableAlreadyDeclaredException;
import model.type.IType;
import state.ProgramState;

public class VariableDeclarationStatement implements IStatement {
    private final String variableName;
    private final IType variableType;

    public VariableDeclarationStatement(String variableName,  IType variableType) {
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

    @Override
    public IStatement deepCopy() {
        return new VariableDeclarationStatement(variableName, variableType.deepCopy());
    }
}
