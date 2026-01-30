package ui.gui.controller;

import controller.IController;
import exception.ExecutionCompletedException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import repository.IRepository;
import ui.command.Command;
import ui.command.OneStepCommand;
import utils.HeapCell;
import utils.LockTableCell;
import utils.SymbolTableCell;

import java.util.Arrays;

public class MainWindowController {
    @FXML
    public TextField programCountField;
    @FXML
    public ListView<String> threadsList;
    @FXML
    public ListView<String> executionStackList;
    @FXML
    public TableView<HeapCell> heapTable;
    @FXML
    public TableView<LockTableCell> lockTableView;
    @FXML
    public TableView<SymbolTableCell> symbolTable;
    @FXML
    public ListView<String> outputList;
    @FXML
    public ListView<String> fileTableList;
    @FXML
    public Button runOneStepButton;
    public TableColumn<HeapCell, Integer> addressColumn;
    public TableColumn<HeapCell, String > heapValueColumn;
    public TableColumn<SymbolTableCell, String> variableColumn;
    public TableColumn<SymbolTableCell, String> stValueColumn;
    public TableColumn<LockTableCell, String> lockIdColumn;
    public TableColumn<LockTableCell, String> threadIdColumn;

    private IController controller;
    private IRepository repository;
    private Command oneStepCommand;

    public void setController(IController controller) {
        this.controller = controller;
    }
    public void setRepository(IRepository repository) {
        this.repository = repository;
    }

    @FXML
    public void initialize() {
        if (this.controller == null || this.repository == null) {return;}
        programCountField.setEditable(false);

        addressColumn.setEditable(false);
        heapValueColumn.setEditable(false);
        variableColumn.setEditable(false);
        stValueColumn.setEditable(false);
        lockIdColumn.setEditable(false);
        threadIdColumn.setEditable(false);

        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        heapValueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        variableColumn.setCellValueFactory(new PropertyValueFactory<>("variableName"));
        stValueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        lockIdColumn.setCellValueFactory(new PropertyValueFactory<>("lockId"));
        threadIdColumn.setCellValueFactory(new PropertyValueFactory<>("threadId"));

        oneStepCommand = new OneStepCommand(controller, repository, "", "");
        threadsList.getSelectionModel().selectedItemProperty().addListener((_, _, _) -> changeThread());
        updateAll();
    }

    private void updateProgramCount(){
        programCountField.setText(repository.getProgramList().size() + "\n");
    }

    private void updateThreadsList() {
        threadsList.setItems(FXCollections.observableArrayList(repository.getProgramList().stream()
                .map(programState -> "Thread " + programState.getId())
                .toList()));
        threadsList.getSelectionModel().select(0);
    }

    private void updateHeapTable() {
        var heap = repository.getProgramList().getFirst().getHeap();
        heapTable.getItems().clear();

        ObservableList<HeapCell> heapCells = FXCollections.observableArrayList(
                Arrays.stream(heap.toString().split("\n"))
                        .filter(s -> !s.isEmpty())
                        .map(pair -> new HeapCell(pair.split(" --> ")[0], pair.split(" --> ")[1]))
                        .toList()
        );

        heapTable.setItems(heapCells);
    }

    private void updateOutputList() {
        var output = repository.getProgramList().getFirst().getOutput();
        outputList.getItems().clear();
        outputList.setItems(FXCollections.observableArrayList(output.toString().split("\n")));
    }

    private void updateExecutionStackList(int currentProgram) {
        var stack = repository.getProgramList().get(currentProgram).getExecutionStack();
        executionStackList.getItems().clear();
        executionStackList.setItems(FXCollections.observableArrayList(stack.toString().split("\n")));
    }

    private void updateSymbolTable(int currentProgram) {
        var data = repository.getProgramList().get(currentProgram).getSymbolTable();
        symbolTable.getItems().clear();

        ObservableList<SymbolTableCell> symbolTableCells = FXCollections.observableArrayList(
                Arrays.stream(data.toString().split("\n"))
                        .filter(line -> !line.isEmpty())
                        .map(pair -> new SymbolTableCell(pair.split(" --> ")[0], (pair.split(" --> ").length > 1 ? pair.split(" --> ")[1] : "")))
                        .toList()
        );

        symbolTable.setItems(symbolTableCells);

    }

    private void updateFileList(int currentProgram) {
        var data = repository.getProgramList().get(currentProgram).getFileTable();
        fileTableList.getItems().clear();
        fileTableList.setItems(FXCollections.observableArrayList(Arrays.stream(data.toString().split("\n")).toList()));
    }

    private void updateLockTable(){
        var data = repository.getProgramList().getFirst().getLockTable();
        lockTableView.getItems().clear();

        ObservableList<LockTableCell> lockTableCells = FXCollections.observableArrayList(
                Arrays.stream(data.toString().split("\n"))
                        .filter(line->!line.isEmpty())
                        .map(pair -> new LockTableCell(pair.split(" --> ")[0], pair.split(" --> ")[1]))
                        .toList()
        );
        lockTableView.setItems(lockTableCells);
    }

    private void updateAll(){
        updateProgramCount();
        updateThreadsList();
        updateHeapTable();
        updateOutputList();
        //updateLockTable();
    }

    private void changeThread(){
        int currentProgram = threadsList.getSelectionModel().getSelectedIndex();
        if (currentProgram == -1) {return;}
        updateExecutionStackList(currentProgram);
        updateSymbolTable(currentProgram);
        updateFileList(currentProgram);
    }

    @FXML
    public void handleOneStepButtonAction(ActionEvent actionEvent) {
        try {
            oneStepCommand.execute();
        }
        catch (ExecutionCompletedException e) {
            runOneStepButton.setDisable(true);
        }
        updateAll();
    }
}
