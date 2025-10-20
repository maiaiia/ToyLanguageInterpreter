package model.expression;

import exception.InvalidOperandTypeException;
import exception.InvalidOperatorException;
import model.value.IValue;
import model.value.IntegerValue;
import model.value.Type;
import state.ISymbolTable;

public class ArithmeticExpression implements IExpression {
    private final IExpression left;
    private final IExpression right;
    private final char operator;

    public ArithmeticExpression(IExpression left, IExpression right, char operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public IValue evaluate(ISymbolTable symbolTable) {
        IValue leftValue = left.evaluate(symbolTable);
        IValue rightValue = right.evaluate(symbolTable);
        if (! (leftValue.getType() == Type.INTEGER)) {
            throw new InvalidOperandTypeException();
        }
        int leftIntValue = ((IntegerValue) leftValue).getValue();

        if (! (rightValue.getType() == Type.INTEGER)) {
            throw new InvalidOperandTypeException();
        }
        int rightIntValue = ((IntegerValue) rightValue).getValue();
        int result = switch (operator){
            case '+' -> leftIntValue + rightIntValue;
            case '-' -> leftIntValue - rightIntValue;
            case '*' -> leftIntValue * rightIntValue;
            case '/' -> leftIntValue / rightIntValue;
            default -> throw new InvalidOperatorException();
        };
        return new IntegerValue(result);
    }
}
