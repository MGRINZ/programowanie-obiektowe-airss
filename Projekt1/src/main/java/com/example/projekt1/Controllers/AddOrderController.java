package com.example.projekt1.Controllers;

import com.example.projekt1.Model.Client;
import com.example.projekt1.Model.Device;
import com.example.projekt1.Model.Devices.Computer;
import com.example.projekt1.Model.Devices.Printer;
import com.example.projekt1.Model.Devices.Smartphone;
import com.example.projekt1.Model.Order;
import com.example.projekt1.Views.ComputerServiceView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class AddOrderController extends AddEditOrderController {

    @FXML
    private ComboBox<String> deviceComboBox;

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

        int maintainerIndex = maintainerComboBox.getSelectionModel().getSelectedIndex();
        if(maintainerIndex != 0)
            order.setMaintainer(maintainerComboBox.getItems().get(maintainerIndex));

        computerService.addOrder(order);

        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        ((ComputerServiceView)stage.getOwner()).updateTable();
        stage.close();
    }

}
