package com.example.projekt1.Controllers;

import com.example.projekt1.Model.*;
import com.example.projekt1.Model.Devices.Computer;
import com.example.projekt1.Model.Devices.Printer;
import com.example.projekt1.Model.Devices.Smartphone;
import com.example.projekt1.Views.ComputerServiceView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.*;

public class AddOrderController implements Initializable {

    @FXML
    public TextField clientFirstName;

    @FXML
    public TextField clientLastName;

    @FXML
    public TextField phoneNumber;

    @FXML
    private ComboBox<String> deviceComboBox;

    @FXML
    public TextField manufacturer;

    @FXML
    public TextField model;

    @FXML
    public RowConstraints osRow;

    @FXML
    public Label osLabel;

    @FXML
    public TextField os;

    @FXML
    public CheckBox formatAllowed;

    @FXML
    public TextField type;

    @FXML
    public TextField problem;

    @FXML
    public ComboBox<Maintainer> maintainer;

    private ComputerService computerService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        maintainer.setConverter(new StringConverter<Maintainer>() {
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
        maintainer.getItems().addAll(maintainers);
        maintainer.setValue(maintainers.get(0));
    }

    public void onDeviceChange(ActionEvent actionEvent) {
        String device = deviceComboBox.getValue();

        os.setDisable(false);
        formatAllowed.setDisable(false);
        type.setDisable(false);

        if(!device.equals("Komputer"))
            formatAllowed.setDisable(true);

        if(device.equals("Smartfon"))
            type.setDisable(true);

        if(device.equals("Drukarka"))
            os.setDisable(true);

    }

    public void onCancelClick(ActionEvent actionEvent) {
        ((Stage)((Button)actionEvent.getSource()).getScene().getWindow()).close();
    }

    public void onSaveClick(ActionEvent actionEvent) {

        boolean isValid = true;

        clientFirstName.getStyleClass().remove("field-invalid");
        if(clientFirstName.getText().isBlank()) {
            clientFirstName.getStyleClass().add("field-invalid");
            isValid = false;
        }

        clientLastName.getStyleClass().remove("field-invalid");
        if(clientLastName.getText().isBlank()) {
            clientLastName.getStyleClass().add("field-invalid");
            isValid = false;
        }

        phoneNumber.getStyleClass().remove("field-invalid");
        if(!phoneNumber.getText().matches("^(\\+\\d\\d)?\\d{9}$")) {
            phoneNumber.getStyleClass().add("field-invalid");
            isValid = false;
        }

        manufacturer.getStyleClass().remove("field-invalid");
        if(manufacturer.getText().isBlank()) {
            manufacturer.getStyleClass().add("field-invalid");
            isValid = false;
        }

        model.getStyleClass().remove("field-invalid");
        if(model.getText().isBlank()) {
            model.getStyleClass().add("field-invalid");
            isValid = false;
        }


        os.getStyleClass().remove("field-invalid");
        if(deviceComboBox.getValue().equals("Komputer") || deviceComboBox.getValue().equals("Smartfon"))
            if(os.getText().isBlank()) {
                os.getStyleClass().add("field-invalid");
                isValid = false;
            }

        type.getStyleClass().remove("field-invalid");
        if(deviceComboBox.getValue().equals("Komputer") || deviceComboBox.getValue().equals("Drukarka"))
            if(type.getText().isBlank()) {
                type.getStyleClass().add("field-invalid");
                isValid = false;
            }

        problem.getStyleClass().remove("field-invalid");
        if(problem.getText().isBlank()) {
            problem.getStyleClass().add("field-invalid");
            isValid = false;
        }

        if(!isValid)
            return;

        Device device = switch(deviceComboBox.getValue()) {
            case "Komputer" -> new Computer(
                    manufacturer.getText().trim(),
                    model.getText().trim(),
                    os.getText().trim(),
                    formatAllowed.isSelected(),
                    type.getText().trim());
            case "Smartfon" -> new Smartphone(
                    manufacturer.getText().trim(),
                    model.getText().trim(),
                    os.getText().trim());
            case "Drukarka" -> new Printer(
                    manufacturer.getText().trim(),
                    model.getText().trim(),
                    type.getText().trim());
            default -> null;
        };

        if(device == null)
            return;

        Order order = new Order(
                new Client(
                        clientFirstName.getText().trim(),
                        clientLastName.getText().trim(),
                        phoneNumber.getText()),
                device,
                problem.getText().trim());

        int maintainerIndex = maintainer.getSelectionModel().getSelectedIndex();
        if(maintainerIndex != 0)
            order.setMaintainer(maintainer.getItems().get(maintainerIndex));

        computerService.addOrder(order);

        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        ((ComputerServiceView)stage.getOwner()).updateTable();
        stage.close();
    }

}
