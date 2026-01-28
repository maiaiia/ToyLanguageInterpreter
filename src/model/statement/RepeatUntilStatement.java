package model.statement;

import exception.InvalidExpressionTypeException;
import model.adt.IDictionary;
import model.expression.IExpression;
import model.expression.NotExpression;
import model.type.BooleanType;
import model.type.IType;
import state.ProgramState;

public class RepeatUntilStatement implements IStatement{
    private final IStatement statement;
    private final IExpression expression;
    public RepeatUntilStatement(IStatement statement, IExpression expression) {
        this.statement = statement;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState programState) {
        IStatement equivalentStatement = new CompoundStatement(
                statement.deepCopy(),
                new WhileStatement(
                        new NotExpression(expression.deepCopy()),
                        statement.deepCopy()
                )
        );
        programState.getExecutionStack().push(equivalentStatement);
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new RepeatUntilStatement(statement.deepCopy(), expression.deepCopy());
    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnvironment) {
        if (!expression.typecheck(typeEnvironment.copy()).equals(new BooleanType())) {
            throw new InvalidExpressionTypeException("Expression should evaluate to a boolean");
        }
        statement.typecheck(typeEnvironment.copy());
        return typeEnvironment;
    }

    @Override
    public String toString() {
        return "(repeat " + statement.toString() + " until " + expression.toString() + ")";
    }
}
