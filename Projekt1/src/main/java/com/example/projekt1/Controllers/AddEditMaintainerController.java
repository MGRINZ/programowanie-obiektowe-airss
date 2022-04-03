package com.example.projekt1.Controllers;

import com.example.projekt1.Model.ComputerService;
import com.example.projekt1.Model.Maintainer;
import com.example.projekt1.Views.ManageMaintainersView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddEditMaintainerController implements Initializable {

    @FXML
    public TextField maintainerFirstName;

    @FXML
    public TextField maintainerLastName;

    private Stage stage;
    private ComputerService computerService;

    private Maintainer maintainer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        computerService = ComputerService.getInstance();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setMaintainer(Maintainer maintainer) {
        this.maintainer = maintainer;

        maintainerFirstName.setText(maintainer.getFirstName());
        maintainerLastName.setText(maintainer.getLastName());
    }

    private boolean validate() {
        boolean isValid = true;
        final String FIELD_INVALID_CLASS = "field-invalid";

        maintainerFirstName.getStyleClass().remove(FIELD_INVALID_CLASS);
        if(maintainerFirstName.getText().isBlank()) {
            maintainerFirstName.getStyleClass().add(FIELD_INVALID_CLASS);
            isValid = false;
        }

        maintainerLastName.getStyleClass().remove(FIELD_INVALID_CLASS);
        if(maintainerLastName.getText().isBlank()) {
            maintainerLastName.getStyleClass().add(FIELD_INVALID_CLASS);
            isValid = false;
        }

        return isValid;
    }

    @FXML
    public void onSaveClick(ActionEvent actionEvent) {

        if(!validate())
            return;

        String firstName = maintainerFirstName.getText().trim();
        String lastName = maintainerLastName.getText().trim();

        if(maintainer == null) {
            maintainer = new Maintainer(
                    firstName,
                    lastName);
            computerService.getMaintainers().add(maintainer);
        }
        else {
            maintainer.setFirstName(firstName);
            maintainer.setLastName(lastName);
        }

        ((ManageMaintainersView)stage.getOwner()).updateList();
        stage.close();
    }

    @FXML
    public void onCancelClick(ActionEvent actionEvent) {
        stage.close();
    }
}
