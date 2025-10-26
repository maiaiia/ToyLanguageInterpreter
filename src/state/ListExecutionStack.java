package state;

import model.statement.IStatement;

import java.util.LinkedList;

public class ListExecutionStack implements IExecutionStack {
    private final LinkedList<IStatement> statements = new LinkedList<>();

    @Override
    public void push(IStatement statement) {
        statements.addFirst(statement);
    }

    @Override
    public IStatement pop() {
        return statements.removeFirst();
    }
}
