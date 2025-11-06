package view;

import controller.Controller;
import controller.IController;
import model.adt.DynamicArrayList;
import model.adt.HashMapDictionary;
import model.adt.Stack;
import repository.IRepository;
import repository.ListRepository;
import state.ProgramState;
import utils.HardCodedStatements;
import view.command.Command;
import view.command.ExitCommand;
import view.command.RunProgramCommand;

import java.io.*;
import java.util.*;

public class View  {
    static void main(String[] args) {
        TextMenu textMenu = new TextMenu();
        var statements = new HardCodedStatements().getStatements();

        for (int i = 0; i < statements.size(); i++) {
            ProgramState p = new ProgramState(new HashMapDictionary<>(), new Stack<>(), new DynamicArrayList<>(), new HashMapDictionary<>(), statements.get(i));
            IRepository repository = new ListRepository(new ArrayList<>(),"log" + Integer.toString(i + 1) + ".txt" );
            repository.addState(p);
            IController controller = new Controller(repository);
            textMenu.addCommand(new RunProgramCommand(Integer.toString(i + 1), statements.get(i).toString(), controller));

            try { //clear log files for hard coded programs
                PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("log" + Integer.toString(i + 1) + ".txt", false)));
                writer.println();
                writer.close();
            } catch (IOException _){

            }
        }

        textMenu.addCommand(new ExitCommand("0", "Exit"));

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

            }
            catch (Exception e) {
                IO.println(e.getMessage());
            }
        }
    }
}
