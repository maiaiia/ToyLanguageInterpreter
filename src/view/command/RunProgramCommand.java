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
            controller.allStep();
        } catch (OutOfBoundsIndexException _){
            controller.getWriter().println(new ExecutionStackEmptyException().getMessage());
            controller.getWriter().flush();
            return;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        IO.println(this.getDescription() + " executed successfully");
    }
}
