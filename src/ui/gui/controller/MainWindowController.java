package ui.gui.controller;

import controller.IController;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MainWindowController {
    public TextField programCountField;
    public ListView<String> threadsList;
    public ListView<String> executionStackList;
    public TableView<String> heapTable;
    public TableView<String> symbolTable;
    public ListView<String> outputList;
    public ListView<String> fileTableList;

    private IController controller;
    public void setController(IController controller) {
        this.controller = controller;
    }
}
