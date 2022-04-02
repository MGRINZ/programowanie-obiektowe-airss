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
    public GridPane orderGrid;

    @FXML
    public TextField clientFirstName;

    @FXML
    public TextField clientLastName;

    @FXML
    public TextField phoneNumber;

    @FXML
    public TextField manufacturer;

    @FXML
    public TextField model;

    @FXML
    public TextField os;

    @FXML
    public CheckBox formatAllowed;

    @FXML
    public TextField type;

    @FXML
    public TextField problem;

    @FXML
    public ComboBox<Maintainer> maintainerComboBox;

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

    public void onCancelClick(ActionEvent actionEvent) {
        ((Stage)((Button)actionEvent.getSource()).getScene().getWindow()).close();
    }
}
