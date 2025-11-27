package model.statement.filestatements;

import exception.FileNotFoundException;
import exception.FileOperationException;
import exception.InvalidOperandTypeException;
import exception.KeyNotInDictionaryException;
import model.expression.IExpression;
import model.statement.IStatement;
import model.type.StringType;
import state.ProgramState;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFileStatement implements IStatement {
    private final IExpression expression;

    public CloseRFileStatement(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState programState) {
        var fileNameIValue = expression.evaluate(programState.getSymbolTable(), programState.getHeap());
        if (!fileNameIValue.getType().equals(new StringType()))
            throw new InvalidOperandTypeException();
        String fileName = fileNameIValue.toString();

        try {
            BufferedReader file = programState.getFileTable().search(fileName);
            file.close();
            programState.getFileTable().remove(fileName);
        } catch (IOException e) {
            throw new FileOperationException(e);
        } catch (KeyNotInDictionaryException _){
            throw new FileNotFoundException(fileName);
        }
        return programState;
    }

    @Override
    public String toString() {
        return "CloseRFile(" + expression.toString() + ")";
    }

    @Override
    public IStatement deepCopy() {
        return new CloseRFileStatement(expression.deepCopy());
    }
}
