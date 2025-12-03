package controller;

import controller.garbagecollector.GarbageCollector;
import exception.ExecutionStackEmptyException;
import exception.OutOfBoundsIndexException;
import repository.IRepository;
import state.ProgramState;

import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

public class Controller implements IController {
    private final IRepository repository;
    private boolean displayFlag = false;
    private final GarbageCollector garbageCollector =  new GarbageCollector();
    public Controller(IRepository repository) {
        this.repository = repository;
    }
    private final PrintWriter writer = new PrintWriter(System.out); //I did this in order to be able to change output dest

    @Override
    public ProgramState executeProgramState(ProgramState programState) {
        while (true){
            try {
                programState.executeOneStep();

                repository.logProgramStateExecution(programState);
                garbageCollector.runGarbageCollector(programState);
                repository.logProgramStateExecution(programState, true);


                if (displayFlag) {
                    writer.println(programState);
                    writer.flush();
                }

            } catch (ExecutionStackEmptyException e) {
                break;
            }
        }
        return programState;
    }

    @Override
    public ProgramState executeCurrentProgram() {
        ProgramState programState = getCurrentProgramState();
        repository.logProgramStateExecution(programState);
        executeProgramState(programState);
        moveToNextProgramState();
        return programState;
    }

    public ProgramState getCurrentProgramState() {
        return repository.getCurrentProgramState();
    }

    @Override
    public ProgramState moveToNextProgramState() throws OutOfBoundsIndexException {
        return repository.getNextProgramState();
    }

    @Override
    public List<ProgramState> removeCompletedPrograms(List<ProgramState> programStates) {
        return programStates.stream()
                .filter(ProgramState::isNotCompleted)
                .collect(Collectors.toList());
    }

    @Override
    public PrintWriter getWriter() {
        return writer;
    }

    @Override
    public void setDisplayFlag() {
        displayFlag = true;
    }

    @Override
    public void resetDisplayFlag() {
        displayFlag = false;
    }

    @Override
    public boolean getDisplayFlag() {
        return displayFlag;
    }
}
