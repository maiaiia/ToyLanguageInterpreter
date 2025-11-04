package view;

import controller.IController;
import exception.StopExecutionException;
import view.command.Command;
import view.command.ExitCommand;
import view.command.RunProgramCommand;

import java.util.*;

public class View implements IView {
    // ----------- PRIVATE ATTRIBUTES -------------
    private final IController controller;
    private final TextMenu textMenu;

    // ------------ METHODS ---------------
    public View(IController controller) {
        this.controller = controller;
        this.textMenu = new TextMenu();
        textMenu.addCommand(new ExitCommand("0", "Exit"));
        textMenu.addCommand(new RunProgramCommand("1", "Run current program", controller));
    }

    private void addProgram() {
        IO.println("Add Program called");
    }
    private void executeProgram() { //will execute current program
        controller.executeCurrentProgram();
        IO.println("Program executed successfully");
    }

    @Override
    public void start(){
        while(true){
            try {
                textMenu.displayMenu();
                String userInput = IO.readln("Enter Command: ").strip();
                Command command = textMenu.getCommand(userInput);
                if (command == null) {
                    IO.println("Invalid Command");
                }
                else {
                    command.execute();
                    IO.println(command.getDescription() + " executed successfully");
                }

            } catch (StopExecutionException e) {
                break;
            }
            catch (Exception e) {
                IO.println(e.getMessage()); //TODO - either make exceptions more specific or print exception tree
            }
        }
    }
}
