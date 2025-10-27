package state;

import model.expression.IExpression;
import model.value.IValue;

import java.util.ArrayList;
import java.util.List;

public class ListOutput implements IOutput {
    private final List<String> outputs = new ArrayList<>();

    @Override
    public void append(String string) {
        outputs.addLast(string);
    }
}

