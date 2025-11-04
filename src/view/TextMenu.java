package view;

import view.command.Command;
import view.command.ExitCommand;
import view.command.RunProgramCommand;

import java.util.HashMap;
import java.util.Map;

public class TextMenu {
    private final Map<String, Command> commands;
    public TextMenu() {
        this.commands = new HashMap<>();
        addCommand(new ExitCommand("0", "Exit"));
    }
    public void addCommand(Command command) {
        commands.put(command.getKey(), command);
    }
    public void displayMenu() {
        for (Command command : this.commands.values()) {
            System.out.println(String.format("%4s. %s", command.getKey(), command.getDescription()));
        }
    }
    public Command getCommand(String key) {
        return commands.get(key);
    }
}
