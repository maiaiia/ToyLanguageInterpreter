package state;

import model.value.IValue;
import model.value.Type;

import java.util.HashMap;
import java.util.Map;

public class MapSymbolTable implements ISymbolTable {
    private final Map<String, IValue> symbolTable = new HashMap<>();

    @Override
    public boolean symbolIsDefined(String symbolName) {
        return symbolTable.containsKey(symbolName);
    }

    @Override
    public Type getSymbolType(String symbolName) {
        return symbolTable.get(symbolName).getType();
    }

    @Override
    public void declareVariable(Type variableType, String variableName) {
        symbolTable.put(variableName, variableType.getDefaultValue());
    }

    @Override
    public void assignVariable(String variableName, IValue value) {
        symbolTable.put(variableName, value);
    }

    @Override
    public IValue getVariableValue(String variableName) {
        return symbolTable.get(variableName);
    }
}
