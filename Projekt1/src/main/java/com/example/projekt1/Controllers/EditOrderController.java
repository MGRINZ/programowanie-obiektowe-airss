package com.example.projekt1.Controllers;

import com.example.projekt1.Model.Client;
import com.example.projekt1.Model.Device;
import com.example.projekt1.Model.Devices.Computer;
import com.example.projekt1.Model.Devices.Printer;
import com.example.projekt1.Model.Devices.Smartphone;
import com.example.projekt1.Model.Maintainer;
import com.example.projekt1.Model.Order;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class EditOrderController extends AddEditOrderController {

    @FXML
    private Label deviceLabel;

    @FXML
    public Label osLabel;

    @FXML
    public Label formatAllowedLabel;

    @FXML
    public Label typeLabel;

    private Order order;

    public void setOrder(Order order) {
        this.order = order;

        Client client = order.getClient();
        clientFirstName.setText(client.getFirstName());
        clientLastName.setText(client.getLastName());
        phoneNumber.setText(client.getPhoneNumber());

        Device device = order.getDevice();
        deviceLabel.setText(device.getDeviceType());
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
    }

    public void onSaveClick(ActionEvent actionEvent) {
    }
}
