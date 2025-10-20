package state;

import model.statement.IStatement;

public interface IExecutionStack {
    void push(IStatement statement);
}
