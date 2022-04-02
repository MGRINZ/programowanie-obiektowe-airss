package com.example.projekt1.Controllers;

import com.example.projekt1.Model.ComputerService;
import com.example.projekt1.Model.Maintainer;
import com.example.projekt1.Model.Order;
import com.example.projekt1.Views.AddOrderView;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class ComputerServiceController implements Initializable {

    @FXML
    private TableView<Order> orderTable;

    @FXML
    private TableColumn<Order, Integer> number;

    @FXML
    private TableColumn<Order, String> client;

    @FXML
    private TableColumn<Order, String> device;

    @FXML
    private TableColumn<Order, String> problem;

    @FXML
    private TableColumn<Order, String> maintainer;

    @FXML
    private TableColumn<Order, Date> dateIn;

    @FXML
    private TableColumn<Order, Date> dateOut;

    @FXML
    private TableColumn<Order, String> status;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        number.setCellValueFactory(new PropertyValueFactory<Order, Integer>("number"));
        client.setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(p.getValue().getClient().getName()));
        device.setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(p.getValue().getDevice().getDeviceType()));
        problem.setCellValueFactory(new PropertyValueFactory<Order, String>("problem"));
        maintainer.setCellValueFactory(p -> {
            Maintainer maintainer = p.getValue().getMaintainer();
            if(maintainer != null)
                return new ReadOnlyObjectWrapper<>(maintainer.getName());
            else
                return new ReadOnlyObjectWrapper<>("-");
        });
        dateIn.setCellValueFactory(new PropertyValueFactory<Order, Date>("dateIn"));
        dateOut.setCellValueFactory(new PropertyValueFactory<Order, Date>("dateOut"));
        status.setCellValueFactory(p -> {
            return new ReadOnlyObjectWrapper<>(switch (p.getValue().getStatus()) {
                case IN -> "Przyjęto";
                case IN_SERVICE -> "W realizacji";
                case READY -> "Do odbioru";
                case OUT -> "Zakończone";
            });
        });

        updateTable();
    }

    public void updateTable() {
        ComputerService computerService = ComputerService.getInstance();
        orderTable.getItems().setAll(computerService.getOrders());
    }

    public void onAddClick(ActionEvent actionEvent) throws IOException {
        AddOrderView addOrderView = new AddOrderView();
        addOrderView.initOwner(((Button)actionEvent.getSource()).getScene().getWindow());
        addOrderView.show();
    }

    public void onEditClick(ActionEvent actionEvent) {
    }

    @FXML
    public void onDeleteClick(ActionEvent actionEvent) {
        Order order = orderTable.getSelectionModel().getSelectedItem();

        if(order == null)
            return;

        ComputerService computerService = ComputerService.getInstance();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Potwierdź usunięcie zgłoszenia");
        alert.setTitle("Usunięcie zgłoszenia");
        alert.setContentText("Czy na pewno usunąć zgłoszenie?");
        alert.initOwner(((Button)actionEvent.getSource()).getScene().getWindow());

        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            computerService.removeOrder(order);
            orderTable.getItems().remove(order);
        }
    }
}
