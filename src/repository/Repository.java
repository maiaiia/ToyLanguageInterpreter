package repository;

import state.ProgramState;

//TODO - this will (probably) be changed to ListRepository, with multiple threadStates
public class Repository implements IRepository {
    ProgramState mainThreadState;
    public Repository(ProgramState mainThreadState) {
        this.mainThreadState = mainThreadState;
    }

    @Override
    public ProgramState getCurrentProgramState() {
        return mainThreadState;
    }

}
