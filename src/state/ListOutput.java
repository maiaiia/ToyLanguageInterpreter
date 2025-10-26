package state;

import model.value.IValue;

import java.util.ArrayList;
import java.util.List;

public class ListOutput implements IOutput {
    private final List<IValue> values = new ArrayList<>();

    @Override
    public void append(IValue value) {
        values.addLast(value);
    }
}

