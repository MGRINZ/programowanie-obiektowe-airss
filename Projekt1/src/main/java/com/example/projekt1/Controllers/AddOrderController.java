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

import java.net.URL;
import java.util.ResourceBundle;

public class AddOrderController extends AddEditOrderController {

    @FXML
    private ComboBox<String> deviceComboBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        deviceType = deviceComboBox.getValue();
    }

    public void onDeviceChange(ActionEvent actionEvent) {
        deviceType = deviceComboBox.getValue();

        os.setDisable(false);
        formatAllowed.setDisable(false);
        type.setDisable(false);

        if(!deviceType.equals("Komputer"))
            formatAllowed.setDisable(true);

        if(deviceType.equals("Smartfon"))
            type.setDisable(true);

        if(deviceType.equals("Drukarka"))
            os.setDisable(true);

    }

    public void onSaveClick(ActionEvent actionEvent) {

        if(!validate())
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
