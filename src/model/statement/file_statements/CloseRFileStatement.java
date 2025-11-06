package model.statement.file_statements;

import exception.FileOperationException;
import exception.InvalidOperandTypeException;
import model.expression.IExpression;
import model.statement.IStatement;
import model.type.IType;
import model.type.StringType;
import programState.ProgramState;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFileStatement implements IStatement {
    private final IExpression expression;

    public CloseRFileStatement(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState programState) {
        var fileNameIValue = expression.evaluate(programState.getSymbolTable());
        if (!fileNameIValue.getType().equals(new StringType()))
            throw new InvalidOperandTypeException();
        String fileName = fileNameIValue.toString();

        try {
            BufferedReader file = programState.getFileTable().getFile(fileName);
            file.close();
            programState.getFileTable().remove(fileName);
        } catch (IOException e) {
            throw new FileOperationException(e);
        }
        return programState;
    }

    @Override
    public String toString() {
        return "CloseRFile(" + expression.toString() + ")";
    }
}
