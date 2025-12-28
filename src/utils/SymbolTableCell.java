package utils;

public class SymbolTableCell {
    private final String variableName;
    private final String value;

    public SymbolTableCell(String variableName, String value) {
        this.variableName = variableName;
        this.value = value;
    }

    public String getVariableName() {
        return variableName;
    }
    public String getValue() {
        return value;
    }
}
