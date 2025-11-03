package model.statement;

import exception.*;
import model.adt.IDictionary;
import model.expression.IExpression;
import model.value.IValue;
import model.value.IntegerValue;
import model.value.Type;
import state.ProgramState;

import java.io.BufferedReader;
import java.io.IOException;

public class readFileStatement implements IStatement {
    private final IExpression fileNameExpression;
    private final String variableName;

    public readFileStatement(IExpression expression, String variableName) {
        this.fileNameExpression = expression;
        this.variableName = variableName;
    }

    @Override
    public ProgramState execute(ProgramState programState)
        throws InvalidExpressionTypeException, VariableNotDefinedException, InvalidVariableTypeException
    {
        IDictionary<String, IValue> symbolTable = programState.getSymbolTable();
        if (!symbolTable.contains(variableName)) {
            throw new VariableNotDefinedException(variableName);
        }
        if (symbolTable.get(variableName).getType() != Type.INTEGER) {
            throw new InvalidVariableTypeException(variableName, Type.INTEGER);
        }
        var expressionResult = fileNameExpression.evaluate(symbolTable);
        if (expressionResult.getType() != Type.STRING) {
            throw new InvalidExpressionTypeException(Type.STRING);
        }
        String fileName = expressionResult.toString();

        if (!programState.getFileTable().isOpened(fileName)) {
            throw new FileNotOpenException(fileName);
        }

        BufferedReader file = programState.getFileTable().getFile(fileName);
        try {
            String line = file.readLine();
            int val = Integer.parseInt(line);
            IntegerValue value = new IntegerValue(val);
            programState.getSymbolTable().add(variableName, value);

        } catch (IOException e) {
            throw new FileOperationException(e);
        } catch (NumberFormatException e) {
            throw new NotANumberException();
        }
        return programState;
    }
}
