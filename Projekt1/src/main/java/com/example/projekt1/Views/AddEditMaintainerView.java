package com.example.projekt1.Views;

import com.example.projekt1.ComputerServiceApplication;
import com.example.projekt1.Controllers.AddEditMaintainerController;
import com.example.projekt1.Controllers.ManageMaintainersController;
import com.example.projekt1.Model.Maintainer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AddEditMaintainerView extends Stage {

    private final FXMLLoader fxmlLoader;
    private final AddEditMaintainerController controller;

    public AddEditMaintainerView() throws IOException {
        fxmlLoader = new FXMLLoader(ComputerServiceApplication.class.getResource("Views/add-edit-maintainer-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        controller = fxmlLoader.getController();
        controller.setStage(this);
        sizeToScene();
        setMinWidth(getWidth());
        setMinHeight(getHeight());
        setResizable(false);
        setTitle("Dodaj serwisanta");
        setScene(scene);
    }

    public AddEditMaintainerView(Maintainer maintainer) throws IOException {
        this();
        setTitle("Edycja serwisanta");
        controller.setMaintainer(maintainer);
    }
}
