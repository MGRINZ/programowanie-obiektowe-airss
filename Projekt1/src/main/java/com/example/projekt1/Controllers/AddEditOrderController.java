package com.example.projekt1.Controllers;

import com.example.projekt1.Model.ComputerService;
import com.example.projekt1.Model.Maintainer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddEditOrderController implements Initializable {

    @FXML
    protected GridPane orderGrid;

    @FXML
    protected TextField clientFirstName;

    @FXML
    protected TextField clientLastName;

    @FXML
    protected TextField phoneNumber;

    @FXML
    protected TextField manufacturer;

    @FXML
    protected TextField model;

    @FXML
    protected TextField os;

    @FXML
    protected CheckBox formatAllowed;

    @FXML
    protected TextField type;

    @FXML
    protected TextField problem;

    @FXML
    protected ComboBox<Maintainer> maintainerComboBox;

    protected String deviceType;

    protected Stage stage;

    protected ComputerService computerService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        maintainerComboBox.setConverter(new StringConverter<Maintainer>() {
            @Override
            public String toString(Maintainer maintainer) {
                return maintainer.getName();
            }

            @Override
            public Maintainer fromString(String s) {
                return null;
            }
        });

        computerService = ComputerService.getInstance();
        ArrayList<Maintainer> maintainers = new ArrayList<>();
        maintainers.add(Maintainer.NULL_MAINTAINER);
        maintainers.addAll(computerService.getMaintainers());
        maintainerComboBox.getItems().addAll(maintainers);
        maintainerComboBox.setValue(maintainers.get(0));
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void onCancelClick(ActionEvent actionEvent) {
        stage.close();
    }

    protected boolean validate() {
        boolean isValid = true;
        final String FIELD_INVALID_CLASS = "field-invalid";

        clientFirstName.getStyleClass().remove(FIELD_INVALID_CLASS);
        if(clientFirstName.getText().isBlank()) {
            clientFirstName.getStyleClass().add(FIELD_INVALID_CLASS);
            isValid = false;
        }

        clientLastName.getStyleClass().remove(FIELD_INVALID_CLASS);
        if(clientLastName.getText().isBlank()) {
            clientLastName.getStyleClass().add(FIELD_INVALID_CLASS);
            isValid = false;
        }

        phoneNumber.getStyleClass().remove(FIELD_INVALID_CLASS);
        if(!phoneNumber.getText().matches("^(\\+\\d\\d)?\\d{9}$")) {
            phoneNumber.getStyleClass().add(FIELD_INVALID_CLASS);
            isValid = false;
        }

        manufacturer.getStyleClass().remove(FIELD_INVALID_CLASS);
        if(manufacturer.getText().isBlank()) {
            manufacturer.getStyleClass().add(FIELD_INVALID_CLASS);
            isValid = false;
        }

        model.getStyleClass().remove(FIELD_INVALID_CLASS);
        if(model.getText().isBlank()) {
            model.getStyleClass().add(FIELD_INVALID_CLASS);
            isValid = false;
        }


        os.getStyleClass().remove(FIELD_INVALID_CLASS);
        if(deviceType.equals("Komputer") || deviceType.equals("Smartfon"))
            if(os.getText().isBlank()) {
                os.getStyleClass().add(FIELD_INVALID_CLASS);
                isValid = false;
            }

        type.getStyleClass().remove(FIELD_INVALID_CLASS);
        if(deviceType.equals("Komputer") || deviceType.equals("Drukarka"))
            if(type.getText().isBlank()) {
                type.getStyleClass().add(FIELD_INVALID_CLASS);
                isValid = false;
            }

        problem.getStyleClass().remove(FIELD_INVALID_CLASS);
        if(problem.getText().isBlank()) {
            problem.getStyleClass().add(FIELD_INVALID_CLASS);
            isValid = false;
        }

        return isValid;
    }
}
