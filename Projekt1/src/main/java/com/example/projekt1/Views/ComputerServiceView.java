package com.example.projekt1.Views;

import com.example.projekt1.ComputerServiceApplication;
import com.example.projekt1.Controllers.ComputerServiceController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ComputerServiceView extends Stage {

    private final FXMLLoader fxmlLoader;
    private final ComputerServiceController controller;

    public ComputerServiceView() throws IOException {
        fxmlLoader = new FXMLLoader(ComputerServiceApplication.class.getResource("Views/computer-service-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        controller = fxmlLoader.getController();
        controller.setStage(this);
        setMinWidth(800);
        setMinHeight(600);
        setTitle("Serwis komputerowy");
        setScene(scene);
    }

    public void updateTable() {
        controller.updateTable();
    }
}
