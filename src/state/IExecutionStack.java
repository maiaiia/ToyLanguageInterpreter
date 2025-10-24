package state;

import model.adt.IStack;
import model.statement.IStatement;

public interface IExecutionStack {
    void push(IStatement statement);
    IStatement pop();
}
