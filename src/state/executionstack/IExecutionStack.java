package state.executionstack;

import exception.ExecutionStackEmptyException;
import model.statement.IStatement;

public interface IExecutionStack {
    void push(IStatement element);
    IStatement pop() throws ExecutionStackEmptyException;
    boolean isEmpty();
    String toString();
}
