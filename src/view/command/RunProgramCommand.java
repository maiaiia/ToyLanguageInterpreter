package view.command;

import controller.IController;
import exception.ExecutionStackEmptyException;

public class RunProgramCommand extends Command {
    private final IController controller;
    public RunProgramCommand(String key, String description, IController controller) {
        super(key, description);
        this.controller = controller;
    }
    @Override
    public void execute() throws Exception {
        try {
            var program = controller.executeCurrentProgram();
            controller.getWriter().println(program);
            controller.getWriter().flush();
        } catch (IndexOutOfBoundsException _){
            throw new ExecutionStackEmptyException();
        }
    }
}
