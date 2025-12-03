package repository;

import exception.FileOperationException;
import exception.OutOfBoundsIndexException;
import state.ProgramState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ListRepository implements IRepository {
    private List<ProgramState> programStates;
    private final String logFileName;
    private final String LOG_FILES_PATH = "log_files/";
    int currentIndex = 0;

    public ListRepository(){
        this.programStates = new ArrayList<>();
        this.logFileName = "program_states.txt";
    }
    public ListRepository(String logFileName) {
        this.programStates = new ArrayList<>();
        this.logFileName = logFileName;
    }
    public ListRepository(List<ProgramState> programStates, String logFileName) {
        this.programStates = programStates;
        this.logFileName = logFileName;
    }

    @Override
    public void addState(ProgramState programState) {
        programStates.addLast(programState);
    }

    @Override
    public ProgramState getCurrentProgramState() throws OutOfBoundsIndexException {
        try {
            return programStates.get(currentIndex);
        } catch(IndexOutOfBoundsException e) {
            throw new OutOfBoundsIndexException("Index out of bounds");
        }
    }

    @Override
    public ProgramState getNextProgramState() throws OutOfBoundsIndexException {
        try {
            return programStates.get(currentIndex++);
        } catch (IndexOutOfBoundsException e) {
            throw new OutOfBoundsIndexException("Index out of bounds!");
        }
    }

    @Override
    public void logProgramStateExecution(ProgramState programState) {
        logProgramStateExecution(programState, false);
    }

    @Override
    public void logProgramStateExecution(ProgramState programState, boolean displaySeparator) {
        PrintWriter logFile;
        try {
            logFile = new PrintWriter(new BufferedWriter(new FileWriter(LOG_FILES_PATH + logFileName, true)));
        } catch (IOException e) {
            throw new FileOperationException(e);
        }
        logFile.println(programState.toString());

        if (displaySeparator) {logFile.println("--------------");}

        logFile.close();

    }

    @Override
    public void logAllPrograms() {
        PrintWriter logFile;
        try {
            logFile = new PrintWriter(new BufferedWriter(new FileWriter(LOG_FILES_PATH + logFileName, true)));
        } catch (IOException e) {
            throw new FileOperationException(e);
        }

        for (int i = 0; i < programStates.size(); i++) {
            logFile.println("Program " + i);
            logFile.println(programStates.get(i).toString());
        }
        logFile.println("***************************");
        logFile.close();
    }

    @Override
    public List<ProgramState> getProgramList() {
        return this.programStates;
    }

    @Override
    public void setProgramList(List<ProgramState> programList) {
        //TODO - check if I'm supposed to copy the content of the programList or just do this
        this.programStates = programList;
    }


}


