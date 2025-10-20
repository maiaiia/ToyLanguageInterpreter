package state;

import model.value.IValue;
import model.value.Type;

public interface ISymbolTable {
    boolean symbolIsDefined(String symbolName);
    Type getSymbolType(String symbolName);
    void declareVariable(Type variableType, String variableName);
    void assignVariable(String variableName, IValue value);

}
