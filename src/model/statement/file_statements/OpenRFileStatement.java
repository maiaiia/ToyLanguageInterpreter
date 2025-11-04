package model.statement.file_statements;

import exception.*;
import model.expression.IExpression;
import model.statement.IStatement;
import model.value.Type;
import programState.ProgramState;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class OpenRFileStatement implements IStatement {
    private final IExpression expression;

    public OpenRFileStatement(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState programState)
            throws InvalidOperandTypeException, FileAlreadyOpenedException, FileOperationException
    {
        var fileName = expression.evaluate(programState.getSymbolTable());
        if (fileName.getType() != Type.STRING)
            throw new InvalidOperandTypeException();
        String fileNameString = fileName.toString();

        var fileTable = programState.getFileTable();
        if (fileTable.isOpened(fileNameString)){
            throw new FileAlreadyOpenedException();
        }

        try {
            var bufferedReader = new BufferedReader(new FileReader(fileNameString));
            programState.getFileTable().add(fileNameString, bufferedReader);
        } catch (IOException ioException) {
            throw new FileOperationException(ioException);
        }

        return programState;
    }

    @Override
    public String toString() {
        return "OpenRFile(" + expression.toString() + ")";
    }
}
