package repository;

import model.adt.DynamicArrayList;
import model.adt.IList;
import state.ProgramState;

public class ListRepository implements IRepository {
    IList<ProgramState> programStates = new DynamicArrayList<>();
    int currentIndex = 0;

    @Override
    public void addState(ProgramState programState) {
        programStates.append(programState);
    }

    @Override
    public ProgramState getCurrentProgramState() {
        return programStates.get(currentIndex);
    }

}
