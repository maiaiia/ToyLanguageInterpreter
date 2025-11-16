package view.command;

import controller.IController;

public class SetDisplayFlagCommand extends Command{
    private final IController controller;

    public SetDisplayFlagCommand(String key, String description, IController controller) {
        super(key, description);
        this.controller = controller;
    }

    @Override
    public void execute(){
        controller.setDisplayFlag();
    }
}
