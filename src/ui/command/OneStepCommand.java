package ui.command;

import controller.IController;
import exception.ExecutionCompletedException;
import repository.IRepository;
import state.ProgramState;

import java.util.List;

public class OneStepCommand extends Command{
    private final IController controller;
    private final IRepository repository;
    private List<ProgramState> programList;

    public OneStepCommand(IController controller, IRepository repository, String key, String description) {
        super(key, description);
        this.controller = controller;
        this.repository = repository;
        this.programList = controller.removeCompletedPrograms(repository.getProgramList());

        programList.forEach(repository::logProgramStateExecution);
    }

    @Override
    public void execute() {
        if (programList.isEmpty()) {
            throw new ExecutionCompletedException("");
        }
        try {
            controller.executeOneStepAllPrograms(programList);
        }
        catch (InterruptedException e) {
            //
        }
        programList = controller.removeCompletedPrograms(repository.getProgramList());
    }
}
