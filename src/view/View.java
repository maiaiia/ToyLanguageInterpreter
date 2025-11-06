package view;

import controller.IController;
import exception.ExecutionStackEmptyException;
import exception.OutOfBoundsIndexException;
import exception.StopExecutionException;

import java.util.*;

public class View implements IView {
    // -------------- CONSTANTS ----------------
    private final String DISPLAY_MENU = "1", ADD_PROGRAM = "2", EXECUTE_FULL_PROGRAM = "3", EXECUTE_STEP_BY_STEP = "4", EXIT = "0";
    private final List<String> menuOptions = new ArrayList<>(){{
        add(DISPLAY_MENU);
        add(ADD_PROGRAM);
        add(EXECUTE_FULL_PROGRAM);
        add(EXECUTE_STEP_BY_STEP);
        add(EXIT);
    }};
    private final Dictionary<String, String> menuDisplay = new Hashtable<>(){{
        put(DISPLAY_MENU, "Display Menu");
        put(ADD_PROGRAM, "Add Program");
        put(EXECUTE_FULL_PROGRAM, "Execute Full Program");
        put(EXECUTE_STEP_BY_STEP, "Execute Program Step By Step");
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
    private void executeFullProgram() { //will execute current program
        var currentState = controller.getCurrentProgramState();
        IO.println("########## INITIAL PROGRAM STATE ###########");
        IO.println(currentState);
        var finalState = controller.executeCurrentProgram();
        IO.println("########## FINAL PROGRAM STATE ###########");
        IO.println(finalState);
    }
    private void executeStepByStep() {
        var currentState = controller.getCurrentProgramState();
        IO.println("########## INITIAL PROGRAM STATE ###########");
        IO.println(currentState);
        IO.println("################ EXECUTION #################");
        while (true) {
            try {
                currentState = controller.executeOneStep(currentState);
                IO.println(currentState);
                IO.println("*************************************");
            } catch (ExecutionStackEmptyException e) {
                break;
            }
        }
        IO.println("########## FINAL PROGRAM STATE ###########");
        IO.println(currentState);
        try {
            controller.moveToNextProgramState();
        }
        catch (OutOfBoundsIndexException _) {}
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
                    case EXECUTE_FULL_PROGRAM -> executeFullProgram();
                    case EXECUTE_STEP_BY_STEP -> executeStepByStep();
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