package ui.gui.service;

import controller.Controller;
import controller.IController;
import model.adt.HashMapDictionary;
import repository.IRepository;
import repository.ListRepository;
import state.ProgramState;
import state.countSemaphore.CountSemaphoreTable;
import state.executionstack.ExecutionStack;
import state.filetable.FileTable;
import state.heap.Heap;
import state.output.Output;
import state.symboltable.SymbolTable;
import utils.HardCodedStatements;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ProgramService {
    private final List<IController> controllers = new ArrayList<>();
    private final List<IRepository> repositories = new ArrayList<>();
    private final List<String> originalPrograms = new ArrayList<>();

    public ProgramService() {
        var statements = new HardCodedStatements().getStatements();
        for (int i = 0; i < statements.size(); i++) {
            statements.get(i).typecheck(new HashMapDictionary<>());
            ProgramState p = new ProgramState(new SymbolTable(), new ExecutionStack(), new Output(), new FileTable(), new Heap(), new CountSemaphoreTable(), statements.get(i));
            IRepository repository = new ListRepository(p,"log" + (i + 1) + ".txt" );
            IController controller = new Controller(repository);

            controllers.add(controller);
            repositories.add(repository);
            originalPrograms.add(p.getOriginalProgram());
        }
    }

    private void clearLogFile(int i){ // call this before executing a program
        try { //clear log files for hard coded programs
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("log_files/log" + (i + 1) + ".txt", false)));
            writer.println();
            writer.close();
        } catch (IOException _){}
    }

    public List<String> getAllPrograms() {
        return originalPrograms;
    }

    public IController getController(int i) {
        return controllers.get(i);
    }
    public IRepository getRepository(int i) {
        return repositories.get(i);
    }
}
