package state.output;

import exception.OutOfBoundsIndexException;
import model.adt.DynamicArrayList;
import model.adt.IList;

public class Output implements IOutput{
    private final IList<String> output = new DynamicArrayList<>();

    @Override
    public int size() {
        return output.size();
    }

    @Override
    public boolean isEmpty() {
        return output.isEmpty();
    }

    @Override
    public String get(int index) throws OutOfBoundsIndexException {
        return output.get(index);
    }

    @Override
    public String set(int index, String value) throws OutOfBoundsIndexException {
        return output.set(index, value);
    }

    @Override
    public void append(String value) {
        output.append(value);
    }

    @Override
    public String toString() {
        return output.toString();
    }
}
