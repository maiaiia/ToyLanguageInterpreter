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

    public ListRepository(ProgramState programState, String logFileName) {
        this.programStates = new ArrayList<>();
        this.programStates.add(programState);
        this.logFileName = logFileName;
        clearLogFile();
    }

    void clearLogFile() {
        PrintWriter logFile;
        try {
            logFile = new PrintWriter(new BufferedWriter(new FileWriter(LOG_FILES_PATH + logFileName, false)));
        } catch (IOException e) {
            throw new FileOperationException(e);
        }
        logFile.println();

        logFile.close();
    }

    @Override
    public void logProgramStateExecution(ProgramState programState) {
        PrintWriter logFile;
        try {
            logFile = new PrintWriter(new BufferedWriter(new FileWriter(LOG_FILES_PATH + logFileName, true)));
        } catch (IOException e) {
            throw new FileOperationException(e);
        }
        logFile.println(programState.toString());

        logFile.close();
    }

    @Override
    public void addLogSeparator() {
        PrintWriter logFile;
        try {
            logFile = new PrintWriter(new BufferedWriter(new FileWriter(LOG_FILES_PATH + logFileName, true)));
        } catch (IOException e) {
            throw new FileOperationException(e);
        }

        logFile.println("-".repeat(100));

        logFile.close();
    }


    @Override
    public List<ProgramState> getProgramList() {
        return this.programStates;
    }

    @Override
    public void setProgramList(List<ProgramState> programList) {
        this.programStates = programList;
    }


}


