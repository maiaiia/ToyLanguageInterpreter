package view;

import controller.IController;
import exception.StopExecutionException;

import java.util.*;

public class View implements IView {
    // -------------- CONSTANTS ----------------
    private final String DISPLAY_MENU = "1", ADD_PROGRAM = "2", EXECUTE_PROGRAM = "3", EXIT = "0";
    private final List<String> menuOptions = new ArrayList<>(){{
        add(DISPLAY_MENU);
        add(ADD_PROGRAM);
        add(EXECUTE_PROGRAM);
        add(EXIT);
    }};
    private final Dictionary<String, String> menuDisplay = new Hashtable<>(){{
        put(DISPLAY_MENU, "Display Menu");
        put(ADD_PROGRAM, "Add Program");
        put(EXECUTE_PROGRAM, "Execute Program");
        put(EXIT, "Exit");
    }};

    // ----------- PRIVATE ATTRIBUTES -------------
    private final IController controller;

    // ------------ METHODS ---------------
    public View(IController controller) {
        this.controller = controller;
    }
    @Override
    public void displayMenu() {
        for (String option : menuOptions) {
            IO.println(option + ". " + menuDisplay.get(option));
        }
    }


    private void addProgram() {
        IO.println("Add Program called");
    }
    private void executeProgram() { //will execute current program
        var currentState = controller.getCurrentProgramState();
        var finalState = controller.executeCurrentProgram();
        IO.println("Program executed successfully");
    }

    @Override
    public void start(){
        while(true){
            try {
                displayMenu();
                String userInput = IO.readln("Enter Command: ").strip();
                switch(userInput){
                    case DISPLAY_MENU -> displayMenu();
                    case ADD_PROGRAM -> addProgram();
                    case EXECUTE_PROGRAM -> executeProgram();
                    case EXIT -> throw new StopExecutionException();
                    default -> IO.println("Invalid Command. Try again.");
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
