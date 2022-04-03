package com.example.projekt1.Views;

import com.example.projekt1.ComputerServiceApplication;
import com.example.projekt1.Controllers.EditOrderController;
import com.example.projekt1.Model.Order;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class EditOrderView extends Stage {

    private final FXMLLoader fxmlLoader;
    private final EditOrderController controller;

    public EditOrderView(Order order) throws IOException {
        fxmlLoader = new FXMLLoader(ComputerServiceApplication.class.getResource("Views/edit-order-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        controller = fxmlLoader.getController();
        controller.setStage(this);
        ((EditOrderController)fxmlLoader.getController()).setOrder(order);
        setMinWidth(640);
        setMinHeight(480);
        setTitle("Edycja zgłoszenia");
        initModality(Modality.APPLICATION_MODAL);
        setScene(scene);
    }

}
