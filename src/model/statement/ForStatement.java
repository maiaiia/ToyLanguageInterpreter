package model.statement;

import exception.InvalidExpressionTypeException;
import model.adt.IDictionary;
import model.expression.IExpression;
import model.expression.RelationalExpression;
import model.expression.VariableExpression;
import model.type.IType;
import model.type.IntegerType;
import state.ProgramState;

public class ForStatement implements IStatement {
    private final IExpression startExpression, endExpression, incrementExpression;
    private final IStatement body;

    public ForStatement(IExpression startExpression, IExpression endExpression, IExpression incrementExpression, IStatement body) {
        this.startExpression = startExpression;
        this.endExpression = endExpression;
        this.incrementExpression = incrementExpression;
        this.body = body;
    }

    @Override
    public ProgramState execute(ProgramState programState) {
        IStatement equivalentStatement = new CompoundStatement(
                new CompoundStatement(
                        new VariableDeclarationStatement("v", new IntegerType()),
                        new AssignmentStatement("v", startExpression)
                ),
                new WhileStatement(
                        new RelationalExpression(
                                new VariableExpression("v"),
                                endExpression,
                                "<"
                        ),
                        new CompoundStatement(
                                body.deepCopy(),
                                new AssignmentStatement("v", incrementExpression)
                        )
                )
        );
        programState.getExecutionStack().push(equivalentStatement);
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new ForStatement(startExpression.deepCopy(), endExpression.deepCopy(), incrementExpression.deepCopy(), body.deepCopy());
    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnvironment) {
        if (!startExpression.typecheck(typeEnvironment).equals(new IntegerType())) {
            throw new InvalidExpressionTypeException("Start expression should be of IntegerType");
        }
        IDictionary<String, IType> newTypeEnvironment = typeEnvironment.copy();
        newTypeEnvironment.add("v", new IntegerType());
        if (!endExpression.typecheck(newTypeEnvironment).equals(new IntegerType())) {
            throw new InvalidExpressionTypeException("End expression should be of IntegerType");
        }
        if  (!incrementExpression.typecheck(newTypeEnvironment).equals(new IntegerType())) {
            throw new InvalidExpressionTypeException("Increment expression should be of IntegerType");
        }
        body.typecheck(newTypeEnvironment);
        return typeEnvironment;
    }

    @Override
    public String toString() {
        return "for(v = " + startExpression.toString() + "; v < " + endExpression.toString() + "; v = " + incrementExpression.toString() + ")" + body.toString();
    }
}
