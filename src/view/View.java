package view;

import controller.Controller;
import controller.IController;
import model.adt.HashMapDictionary;
import repository.IRepository;
import repository.ListRepository;
import state.ProgramState;
import state.executionstack.ExecutionStack;
import state.filetable.FileTable;
import state.heap.Heap;
import state.output.Output;
import state.symboltable.SymbolTable;
import utils.HardCodedStatements;
import view.command.Command;
import view.command.ExitCommand;
import view.command.RunProgramCommand;
import view.command.SetDisplayFlagCommand;

import java.io.*;
import java.util.*;

public class View  {
    static private TextMenu getMenu() {
        TextMenu textMenu = new TextMenu();
        var statements = new HardCodedStatements().getStatements();

        for (int i = 0; i < statements.size(); i++) {
            ProgramState p = new ProgramState(new SymbolTable(), new ExecutionStack(), new Output(), new FileTable(), new Heap(), statements.get(i));
            IRepository repository = new ListRepository(p,"log" + Integer.toString(i + 1) + ".txt" );
            IController controller = new Controller(repository);
            textMenu.addCommand(new RunProgramCommand(Integer.toString(i + 1), statements.get(i).toString(), controller));

            try { //clear log files for hard coded programs
                PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("log_files/log" + Integer.toString(i + 1) + ".txt", false)));
                writer.println();
                writer.close();
            } catch (IOException _){}

            if (i == 0){
                textMenu.addCommand(new SetDisplayFlagCommand("set", "Set the display flag for program 1", controller));
            }
        }

        textMenu.addCommand(new ExitCommand("0", "Exit"));
        return textMenu;
    }

    static void main(String[] args) {
        TextMenu textMenu = getMenu();

        while(true){
            textMenu.displayMenu();
            String userInput = IO.readln("Enter Command: ").strip();
            Command command = textMenu.getCommand(userInput);
            if (command == null) {
                IO.println("Invalid Command");
            }
            else {
                command.execute();
                //IO.println(command.getDescription() + " executed successfully");
            }
        }
    }
}
