package model.statement;

import exception.InvalidExpressionTypeException;
import model.adt.IDictionary;
import model.expression.IExpression;
import model.expression.RelationalExpression;
import model.type.IType;
import model.type.IntegerType;
import state.ProgramState;

public class SwitchStatement implements IStatement {
    private final IExpression switchExpression, case1Expression, case2Expression;
    private final IStatement statement1, statement2, statement3;

    public SwitchStatement(IExpression switchExpression, IExpression case1Expression, IStatement statement1, IExpression case2Expression, IStatement statement2, IStatement statement3) {
        this.switchExpression = switchExpression;
        this.case1Expression = case1Expression;
        this.case2Expression = case2Expression;
        this.statement1 = statement1;
        this.statement2 = statement2;
        this.statement3 = statement3;
    }

    @Override
    public ProgramState execute(ProgramState programState) {
        IfStatement equivalentStatement = new IfStatement(
                new RelationalExpression(switchExpression.deepCopy(), case1Expression.deepCopy(), "=="),
                statement1.deepCopy(),
                new IfStatement(
                        new RelationalExpression(switchExpression.deepCopy(), case2Expression.deepCopy(), "=="),
                        statement2.deepCopy(),
                        statement1.deepCopy()
                )
        );
        programState.getExecutionStack().push(equivalentStatement);
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new SwitchStatement(switchExpression.deepCopy(), case1Expression.deepCopy(), statement1.deepCopy(), case2Expression.deepCopy(), statement2.deepCopy(), statement3.deepCopy());
    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnvironment) {
        IType switchType = switchExpression.typecheck(typeEnvironment);
        if (!switchType.equals(new IntegerType())) {
            throw new InvalidExpressionTypeException("Switch expression type is not integer");
        }
        if (!case1Expression.typecheck(typeEnvironment).equals(new IntegerType())) {
            throw new InvalidExpressionTypeException("Case one expression type is not integer");
        }
        if (!case2Expression.typecheck(typeEnvironment).equals(new IntegerType())) {
            throw new InvalidExpressionTypeException("Case two expression type is not integer");
        }
        statement1.typecheck(typeEnvironment.copy());
        statement2.typecheck(typeEnvironment.copy());
        statement3.typecheck(typeEnvironment.copy());
        return typeEnvironment;
    }

    @Override
    public String toString() {
        return "(switch " + switchExpression +
                "(case " + case1Expression.toString() + " : " + statement1.toString() + ") " +
                "(case " + case2Expression.toString() + " : " + statement2.toString() + ") " +
                "(default : " + statement3.toString() + "))";
    }
}
