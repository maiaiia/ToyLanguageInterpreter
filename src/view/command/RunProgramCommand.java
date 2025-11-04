package view.command;

import controller.IController;

public class RunProgramCommand extends Command {
    private final IController controller;
    public RunProgramCommand(String key, String description, IController controller) {
        super(key, description);
        this.controller = controller;
    }
    @Override
    public void execute() throws Exception {
        controller.executeCurrentProgram();
    }
}
