package com.example.projekt1.Controllers;

import com.example.projekt1.Converters.OrderStatusStringConverter;
import com.example.projekt1.Model.*;
import com.example.projekt1.Model.Devices.Computer;
import com.example.projekt1.Model.Devices.Printer;
import com.example.projekt1.Model.Devices.Smartphone;
import com.example.projekt1.Views.ComputerServiceView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class EditOrderController extends AddEditOrderController {

    @FXML
    private Label deviceLabel;

    @FXML
    public Label osLabel;

    @FXML
    public Label formatAllowedLabel;

    @FXML
    public Label typeLabel;

    @FXML
    public ComboBox<OrderStatus> status;

    private Order order;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);

        status.setConverter(new OrderStatusStringConverter());
        status.getItems().setAll(OrderStatus.IN, OrderStatus.IN_SERVICE, OrderStatus.READY, OrderStatus.OUT);
    }

    public void setOrder(Order order) {
        this.order = order;

        Client client = order.getClient();
        clientFirstName.setText(client.getFirstName());
        clientLastName.setText(client.getLastName());
        phoneNumber.setText(client.getPhoneNumber());

        Device device = order.getDevice();
        deviceLabel.setText(device.getDeviceType());
        deviceType = device.getDeviceType();
        manufacturer.setText(device.getManufacturer());
        model.setText(device.getModel());

        if(device instanceof Computer computer) {
            os.setText(computer.getOs());
            formatAllowed.setSelected(computer.isFormatAllowed());
            type.setText(computer.getType());
        } else {
            orderGrid.getChildren().remove(formatAllowedLabel);
            orderGrid.getChildren().remove(formatAllowed);
        }

        if(device instanceof Smartphone smartphone) {
            os.setText(smartphone.getOs());

            GridPane.setRowSpan(osLabel, 3);
            GridPane.setRowSpan(os, 3);

            orderGrid.getChildren().remove(typeLabel);
            orderGrid.getChildren().remove(type);
        }

        if(device instanceof Printer printer) {
            type.setText(printer.getType());

            GridPane.setRowIndex(typeLabel, 6);
            GridPane.setRowIndex(type, 6);
            GridPane.setRowSpan(typeLabel, 3);
            GridPane.setRowSpan(type, 3);

            orderGrid.getChildren().remove(osLabel);
            orderGrid.getChildren().remove(os);
        }

        problem.setText(order.getProblem());

        Maintainer maintainer = order.getMaintainer();
        if(maintainer != null)
            maintainerComboBox.setValue(maintainer);

        status.setValue(order.getStatus());
    }

    public void onSaveClick(ActionEvent actionEvent) {

        if(!validate())
            return;

        Client client = order.getClient();
        client.setFirstName(clientFirstName.getText().trim());
        client.setLastName(clientLastName.getText().trim());
        client.setPhoneNumber(phoneNumber.getText());

        Device device = order.getDevice();
        device.setManufacturer(manufacturer.getText().trim());
        device.setModel(model.getText().trim());

        if(device instanceof Computer computer) {
            computer.setOs(os.getText().trim());
            computer.setFormatAllowed(formatAllowed.isSelected());
            computer.setType(type.getText().trim());
        } else if(device instanceof Smartphone smartphone) {
            smartphone.setOs(os.getText().trim());
        } else if(device instanceof Printer printer) {
            printer.setType(type.getText().trim());
        }

        order.setProblem(problem.getText().trim());

        int maintainerIndex = maintainerComboBox.getSelectionModel().getSelectedIndex();
        if(maintainerIndex > 0)
            order.setMaintainer(maintainerComboBox.getItems().get(maintainerIndex));

        order.setStatus(status.getValue());

        ((ComputerServiceView)stage.getOwner()).updateTable();
        stage.close();
    }

}
