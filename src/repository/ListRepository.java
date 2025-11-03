package repository;

import exception.OutOfBoundsIndexException;
import state.ProgramState;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ListRepository implements IRepository {
    List<ProgramState> programStates = new ArrayList<>();
    int currentIndex = 0;

    public ListRepository() {}
    public ListRepository(List<ProgramState> programStates) {
        this.programStates = programStates;
    }

    @Override
    public void addState(ProgramState programState) {
        programStates.addLast(programState);
    }

    @Override
    public ProgramState getCurrentProgramState() throws OutOfBoundsIndexException {
        try {
            return programStates.get(currentIndex);
        } catch(IndexOutOfBoundsException e) {
            throw new OutOfBoundsIndexException("Index out of bounds");
        }
    }

    @Override
    public ProgramState getNextProgramState() throws OutOfBoundsIndexException {
        try {
            return programStates.get(currentIndex++);
        } catch (IndexOutOfBoundsException e) {
            throw new OutOfBoundsIndexException("Index out of bounds!");
        }
    }

    @Override
    public void logProgramStateExecution() {
        return;
    }

}
