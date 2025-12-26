package ui.gui.controller;

import controller.IController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import ui.gui.service.ProgramService;

import java.io.IOException;

public class ProgramMenuController {
    @FXML
    private Button runButton;

    @FXML
    private ListView<String> programList;

    private ProgramService programService;

    @FXML
    public void initialize() {
        programService = new ProgramService();
        programList.setItems(FXCollections.observableArrayList(programService.getAllPrograms()));

        runButton.setDisable(true);
        programList.getSelectionModel().selectedItemProperty().addListener((_, _, newValue) -> runButton.setDisable(newValue==null));
    }

    @FXML
    public void handleRunProgram() {
        int selectedIndex = programList.getSelectionModel().getSelectedIndex();
        IController controller = programService.getController(selectedIndex);
        String programName = programService.getAllPrograms().get(selectedIndex);
        if (controller!=null) {
            openExecutionWindow(controller, programName);
        }
    }

    private void openExecutionWindow(IController controller, String programName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/gui/views/main-window.fxml"));
            Parent root = loader.load();

            MainWindowController mainWindowController = loader.getController();
            mainWindowController.setController(controller);

            Stage stage = new Stage();
            stage.setTitle("Executing Program " + programName);
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace(); //TODO change
        }
    }
}
