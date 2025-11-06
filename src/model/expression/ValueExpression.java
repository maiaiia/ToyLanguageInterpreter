package model.expression;


import model.adt.IDictionary;
import model.value.IValue;

public class ValueExpression implements IExpression {
    private final IValue value;

    public ValueExpression(IValue value) {
        this.value = value;
    }

    @Override
    public IValue evaluate(IDictionary<String, IValue> symbolTable) {
        return value;
    }

    @Override
    public String toString()
    {
        return value.toString();
    }

    @Override
    public IExpression deepCopy() {
        return new ValueExpression(value.deepCopy());
    }
}
