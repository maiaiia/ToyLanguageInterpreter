package state.executionstack;

import exception.ExecutionStackEmptyException;
import model.adt.IStack;
import model.adt.Stack;
import model.statement.IStatement;

import java.util.EmptyStackException;

public class ExecutionStack implements IExecutionStack{
    private final IStack<IStatement> executionStack =  new Stack<IStatement>();

    @Override
    public void push(IStatement element) {
        executionStack.push(element);
    }

    @Override
    public IStatement pop() throws ExecutionStackEmptyException {
        try {
            return executionStack.pop();
        } catch (EmptyStackException _) {
            throw new ExecutionStackEmptyException();
        }
    }

    @Override
    public boolean isEmpty() {
        return executionStack.isEmpty();
    }

    @Override
    public String toString(){
        return "EXECUTION STACK\n" + executionStack;
    }

}
