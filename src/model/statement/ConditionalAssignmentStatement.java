package model.statement;

import exception.ConditionNotBooleanException;
import exception.InvalidExpressionTypeException;
import model.adt.IDictionary;
import model.expression.IExpression;
import model.type.BooleanType;
import model.type.IType;
import state.ProgramState;

public class ConditionalAssignmentStatement implements IStatement {
    private final String variableName;
    private final IExpression conditionalExpression, thenExpression, elseExpression;

    public ConditionalAssignmentStatement(String variableName, IExpression conditionalExpression, IExpression resultExpression1, IExpression resultExpression2) {
        this.conditionalExpression = conditionalExpression;
        this.thenExpression = resultExpression1;
        this.elseExpression = resultExpression2;
        this.variableName = variableName;
    }

    @Override
    public ProgramState execute(ProgramState programState) {
        IfStatement equivalentStatement = new IfStatement(conditionalExpression.deepCopy(),
                new AssignmentStatement(variableName, thenExpression.deepCopy()),
                new AssignmentStatement(variableName, elseExpression.deepCopy()));
        programState.getExecutionStack().push(equivalentStatement);
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new ConditionalAssignmentStatement(variableName, conditionalExpression.deepCopy(), thenExpression.deepCopy(), elseExpression.deepCopy());
    }

    @Override
    public IDictionary<String, IType> typecheck(IDictionary<String, IType> typeEnvironment) {
        IType conditionalExpressionType = conditionalExpression.typecheck(typeEnvironment);
        if (!conditionalExpressionType.equals(new BooleanType())){
            throw new ConditionNotBooleanException();
        }
        IType thenExpressionType = thenExpression.typecheck(typeEnvironment);
        IType elseExpressionType = elseExpression.typecheck(typeEnvironment);
        IType variableType = typeEnvironment.search(variableName);
        if (!variableType.equals(thenExpressionType)) {
            throw new InvalidExpressionTypeException("Variable and left expression type mismatch");
        }
        if (!variableType.equals(elseExpressionType)) {
            throw new InvalidExpressionTypeException("Variable and right expression type mismatch");
        }
        return typeEnvironment;
    }

    @Override
    public String toString() {
        return '(' + variableName + '=' + conditionalExpression + '?' + thenExpression + ':' + elseExpression + ')';
    }
}
