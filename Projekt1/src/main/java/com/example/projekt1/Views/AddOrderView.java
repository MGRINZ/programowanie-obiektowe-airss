package com.example.projekt1.Views;

import com.example.projekt1.ComputerServiceApplication;
import com.example.projekt1.Controllers.AddOrderController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class AddOrderView extends Stage {

    private final FXMLLoader fxmlLoader;
    private final AddOrderController controller;

    public AddOrderView() throws IOException {
        fxmlLoader = new FXMLLoader(ComputerServiceApplication.class.getResource("Views/add-order-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        controller = fxmlLoader.getController();
        controller.setStage(this);
        setMinWidth(640);
        setMinHeight(480);
        setTitle("Dodawanie zlecenia");
        initModality(Modality.APPLICATION_MODAL);
        setScene(scene);
    }

}
