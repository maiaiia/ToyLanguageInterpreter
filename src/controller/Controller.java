package controller;

import controller.garbagecollector.GarbageCollector;
import exception.ExecutionStackEmptyException;
import exception.OutOfBoundsIndexException;
import exception.ThreadExecutionException;
import repository.IRepository;
import state.ProgramState;

import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class Controller implements IController {
    private final IRepository repository;
    private boolean displayFlag = false;
    private final GarbageCollector garbageCollector;
    private ExecutorService executor;
    public Controller(IRepository repository) {
        this.repository = repository;
        this.garbageCollector = new GarbageCollector();
    }
    private final PrintWriter writer = new PrintWriter(System.out); //I did this in order to be able to change output dest

    @Override
    public List<ProgramState> removeCompletedPrograms(List<ProgramState> programStates) {
        return programStates.stream()
                .filter(ProgramState::isNotCompleted)
                .collect(Collectors.toList());
    }

    @Override
    public void executeOneStepAllPrograms(List<ProgramState> programStates) throws InterruptedException, ThreadExecutionException {
        //before the execution, print the program state list into the log file
        //programStates.forEach(repository::logProgramStateExecution);

        //prepare the list of callables
        List<Callable<ProgramState>> callList = programStates.stream()
                .map((ProgramState program) -> (Callable<ProgramState>)(program::executeOneStep))
                .toList();
        // start the execution of the callables
        // it returns the list of newly created ProgramStates (namely threads)\
        List<ProgramState> newProgramsList = executor.invokeAll(callList).stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (InterruptedException | ExecutionException e){
                        throw new ThreadExecutionException(); //TODO Figure out what i'm supposed to do here?
                    }
                })
                .filter(Objects::nonNull)
                .toList();
        programStates.addAll(newProgramsList);
        programStates.forEach(repository::logProgramStateExecution);
        repository.addLogSeparator();
        repository.setProgramList(programStates);
    }

    @Override
    public void allStep() throws InterruptedException {
        executor = Executors.newFixedThreadPool(2);
        List<ProgramState> programList = removeCompletedPrograms(repository.getProgramList());
        //moved the first print here to remove redundant log entries (uncomment the first line in executeOneStepAllPrograms for a more detailed output)
        programList.forEach(repository::logProgramStateExecution);
        while (!programList.isEmpty()) {
            garbageCollector.runGarbageCollector(programList);
            executeOneStepAllPrograms(programList);
            programList = removeCompletedPrograms(repository.getProgramList());
        }
        executor.shutdownNow();
        repository.setProgramList(programList);
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
