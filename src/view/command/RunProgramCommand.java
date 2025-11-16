package view.command;

import controller.IController;
import exception.ExecutionStackEmptyException;
import exception.OutOfBoundsIndexException;

public class RunProgramCommand extends Command {
    private final IController controller;
    public RunProgramCommand(String key, String description, IController controller) {
        super(key, description);
        this.controller = controller;
    }
    @Override
    public void execute(){
        try {
            var program = controller.executeCurrentProgram();
            controller.getWriter().println(program);
            controller.getWriter().flush();
        } catch (OutOfBoundsIndexException _){
            controller.getWriter().println(new ExecutionStackEmptyException().getMessage());
            controller.getWriter().flush();
        } catch (Exception e) {
            controller.getWriter().println(e.getMessage());
            controller.getWriter().flush();
        }
    }
}
