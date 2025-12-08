package model.statement.fileStatements;

import exception.*;
import model.adt.IDictionary;
import model.expression.IExpression;
import model.statement.IStatement;
import model.type.IType;
import model.type.IntegerType;
import model.type.StringType;
import model.value.IntegerValue;
import state.ProgramState;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFileStatement implements IStatement {
    private final IExpression fileNameExpression;
    private final String variableName;

    public ReadFileStatement(IExpression expression, String variableName) {
        this.fileNameExpression = expression;
        this.variableName = variableName;
    }

    @Override
    public ProgramState execute(ProgramState programState)
        throws InvalidExpressionTypeException, VariableNotDefinedException, InvalidVariableTypeException
    {
        var symbolTable = programState.getSymbolTable();
        if (!symbolTable.contains(variableName)) {
            throw new VariableNotDefinedException(variableName);
        }
        if (! symbolTable.get(variableName).getType().equals(new IntegerType())) {
            throw new InvalidVariableTypeException(variableName, new IntegerType());
        }
        var expressionResult = fileNameExpression.evaluate(symbolTable, programState.getHeap());
        if (!expressionResult.getType().equals(new StringType())) {
            throw new InvalidExpressionTypeException(new StringType());
        }
        String fileName = expressionResult.toString();

        if (!programState.getFileTable().contains(fileName)) {
            throw new FileNotOpenException(fileName);
        }

        BufferedReader file = programState.getFileTable().get(fileName);
        try {
            String line = file.readLine();
            int val;
            if (line == null)
                val = 0;
            else
                val = Integer.parseInt(line);
            IntegerValue value = new IntegerValue(val);
            programState.getSymbolTable().add(variableName, value);

        } catch (IOException e) {
            throw new FileOperationException(e);
        } catch (NumberFormatException e) {
            throw new NotANumberException();
        }
        return null;
    }

    @Override
    public String toString() {
        return "ReadFile(" + fileNameExpression.toString() + ", " + variableName + ")";
    }

    @Override
    public IStatement deepCopy() {
        return new ReadFileStatement(fileNameExpression.deepCopy(), variableName);
    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnvironment) {
        IType fileNameType = fileNameExpression.typecheck(typeEnvironment);
        if (!fileNameType.equals(new StringType())) {
            throw new InvalidExpressionTypeException(new StringType());
        }
        IType variableType = typeEnvironment.search(variableName);
        if (!variableType.equals(new IntegerType())) {
            throw new InvalidVariableTypeException(variableName, new IntegerType());
        }
        return typeEnvironment;
    }
}

