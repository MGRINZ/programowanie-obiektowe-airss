package com.example.projekt1.Views;

import com.example.projekt1.ComputerServiceApplication;
import com.example.projekt1.Controllers.ComputerServiceController;
import com.example.projekt1.Controllers.ManageMaintainersController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ManageMaintainersView extends Stage {

    private final FXMLLoader fxmlLoader;
    private final ManageMaintainersController controller;

    public ManageMaintainersView() throws IOException {
        fxmlLoader = new FXMLLoader(ComputerServiceApplication.class.getResource("Views/manage-maintainers-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        controller = fxmlLoader.getController();
        controller.setStage(this);
        setMinWidth(640);
        setMinHeight(480);
        sizeToScene();

        setTitle("Zarządzanie serwisantami");
        setScene(scene);
    }

    public void updateList() {
        controller.updateList();
    }
}
