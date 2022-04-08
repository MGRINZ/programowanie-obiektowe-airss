package com.example.projekt1.Controllers;

import com.example.projekt1.Converters.OrderStatusStringConverter;
import com.example.projekt1.Model.*;
import com.example.projekt1.Model.Devices.Computer;
import com.example.projekt1.Model.Devices.Printer;
import com.example.projekt1.Model.Devices.Smartphone;
import com.example.projekt1.Views.AddOrderView;
import com.example.projekt1.Views.EditOrderView;
import com.example.projekt1.Views.HelpView;
import com.example.projekt1.Views.ManageMaintainersView;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

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

    @FXML
    private GridPane detailsGrid;

    @FXML
    private Label detailsNumber;

    @FXML
    private Label detailsClient;

    @FXML
    private Label detailsPhone;

    @FXML
    private Label detailsMaintainer;

    @FXML
    private Label detailsDateIn;

    @FXML
    private Label detailsDateOut;

    @FXML
    private Label detailsStatus;

    @FXML
    private Label detailsTypeLabel;

    @FXML
    private Label detailsDevice;

    @FXML
    private Label detailsType;

    @FXML
    private Label detailsManufacturerLabel;

    @FXML
    private Label detailsManufacturer;

    @FXML
    private Label detailsModelLabel;

    @FXML
    private Label detailsModel;

    @FXML
    private Label detailsOSLabel;

    @FXML
    private Label detailsOS;

    @FXML
    private Label detailsFormatAllowedLabel;

    @FXML
    private Label detailsFormatAllowed;

    @FXML
    private Label detailsProblemLabel;

    @FXML
    private Label detailsProblem;

    private Stage stage;

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
            OrderStatusStringConverter converter = new OrderStatusStringConverter();
            return new ReadOnlyObjectWrapper<>(converter.toString(p.getValue().getStatus()));
        });

        orderTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Order>() {
            @Override
            public void changed(ObservableValue<? extends Order> observableValue, Order oldOrder, Order newOrder) {
                if(newOrder == null)
                    return;

                Client client = newOrder.getClient();

                detailsNumber.setText(String.valueOf(newOrder.getNumber()));
                detailsClient.setText(client.getName());
                detailsPhone.setText(client.getPhoneNumber());
                detailsDateIn.setText(newOrder.getDateIn().toString());

                if(newOrder.getDateOut() != null)
                    detailsDateOut.setText(newOrder.getDateOut().toString());
                else
                    detailsDateOut.setText("-");

                Maintainer maintainer = newOrder.getMaintainer();
                if(maintainer != null)
                    detailsMaintainer.setText(newOrder.getMaintainer().getName());
                else
                    detailsMaintainer.setText("-");

                OrderStatusStringConverter converter = new OrderStatusStringConverter();
                detailsStatus.setText(converter.toString(newOrder.getStatus()));

                Device device = newOrder.getDevice();
                detailsDevice.setText(device.getDeviceType());
                detailsManufacturer.setText(device.getManufacturer());
                detailsModel.setText(device.getModel());

                if(device instanceof Computer computer) {
                    showComputerFields();
                    detailsType.setText(computer.getType());
                    detailsOS.setText(computer.getOs());
                    detailsFormatAllowed.setText(computer.isFormatAllowed() ? "Tak" : "Nie");
                } else if(device instanceof Smartphone smartphone) {
                    showSmartphoneFields();
                    detailsOS.setText(smartphone.getOs());
                } else if(device instanceof Printer printer) {
                    showPrinterFields();
                    detailsType.setText(printer.getType());
                }

                detailsProblem.setText(newOrder.getProblem());
            }
        });

        updateTable();
    }

    private void showComputerFields() {
        GridPane.setRowIndex(detailsTypeLabel, 2);
        GridPane.setRowIndex(detailsType, 2);
        GridPane.setRowIndex(detailsManufacturerLabel, 3);
        GridPane.setRowIndex(detailsManufacturer, 3);
        GridPane.setRowIndex(detailsModelLabel, 4);
        GridPane.setRowIndex(detailsModel, 4);
        GridPane.setRowIndex(detailsOSLabel, 5);
        GridPane.setRowIndex(detailsOS, 5);
        GridPane.setRowIndex(detailsFormatAllowedLabel, 6);
        GridPane.setRowIndex(detailsFormatAllowed, 6);
        GridPane.setRowIndex(detailsProblemLabel, 7);
        GridPane.setRowIndex(detailsProblem, 7);

        detailsGrid.getStyleClass().setAll("computer-details");
    }

    private void showSmartphoneFields() {
        GridPane.setRowIndex(detailsManufacturerLabel, 2);
        GridPane.setRowIndex(detailsManufacturer, 2);
        GridPane.setRowIndex(detailsModelLabel, 3);
        GridPane.setRowIndex(detailsModel, 3);
        GridPane.setRowIndex(detailsOSLabel, 4);
        GridPane.setRowIndex(detailsOS, 4);
        GridPane.setRowIndex(detailsProblemLabel, 5);
        GridPane.setRowIndex(detailsProblem, 5);

        detailsGrid.getStyleClass().setAll("smartphone-details");
    }

    private void showPrinterFields() {
        GridPane.setRowIndex(detailsTypeLabel, 2);
        GridPane.setRowIndex(detailsType, 2);
        GridPane.setRowIndex(detailsManufacturerLabel, 3);
        GridPane.setRowIndex(detailsManufacturer, 3);
        GridPane.setRowIndex(detailsModelLabel, 4);
        GridPane.setRowIndex(detailsModel, 4);
        GridPane.setRowIndex(detailsProblemLabel, 5);
        GridPane.setRowIndex(detailsProblem, 5);

        detailsGrid.getStyleClass().setAll("printer-details");
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void updateTable() {
        ComputerService computerService = ComputerService.getInstance();
        orderTable.getItems().setAll(computerService.getOrders());
    }

    @FXML
    private void onAddClick(ActionEvent actionEvent) throws IOException {
        AddOrderView addOrderView = new AddOrderView();
        addOrderView.initOwner(stage);
        addOrderView.show();
    }

    @FXML
    private void onEditClick(ActionEvent actionEvent) throws IOException {
        Order order = orderTable.getSelectionModel().getSelectedItem();

        if(order == null)
            return;

        if(order.getStatus() == OrderStatus.OUT) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Nie można edytować zgłoszenia");
            alert.setTitle("Edycja zgłoszenia");
            alert.setContentText("Zgłoszenia o statusie \"Zakończone\" nie mogą być edytowane.");
            alert.initOwner(stage);
            alert.showAndWait();
            return;
        }

        EditOrderView editOrderView = new EditOrderView(order);
        editOrderView.initOwner(stage);
        editOrderView.show();
    }

    @FXML
    private void onDeleteClick(ActionEvent actionEvent) {
        Order order = orderTable.getSelectionModel().getSelectedItem();

        if(order == null)
            return;

        ComputerService computerService = ComputerService.getInstance();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Potwierdź usunięcie zgłoszenia");
        alert.setTitle("Usunięcie zgłoszenia");
        alert.setContentText("Czy na pewno usunąć zgłoszenie?");
        alert.initOwner(stage);

        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            computerService.removeOrder(order);
            orderTable.getItems().remove(order);
        }
    }

    @FXML
    private void onHelpClick(ActionEvent actionEvent) throws IOException {
        HelpView helpView = new HelpView();
        helpView.initOwner(stage);
        helpView.show();
    }

    @FXML
    private void onCloseClick(ActionEvent actionEvent) {
        stage.close();
    }

    @FXML
    private void onManageMaintainersClick(ActionEvent actionEvent) throws IOException {
        ManageMaintainersView manageMaintainersView = new ManageMaintainersView();
        manageMaintainersView.initOwner(stage);
        manageMaintainersView.show();
    }
}
