package com.example.projekt1.Views;

import com.example.projekt1.ComputerServiceApplication;
import com.example.projekt1.Controllers.AddOrderController;
import com.example.projekt1.Controllers.HelpController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class HelpView extends Stage {

    private final FXMLLoader fxmlLoader;
    private final HelpController controller;

    public HelpView() throws IOException {
        fxmlLoader = new FXMLLoader(ComputerServiceApplication.class.getResource("Views/help-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        controller = fxmlLoader.getController();
        controller.setStage(this);
        setResizable(false);
        sizeToScene();
        setTitle("Pomoc");
        initModality(Modality.APPLICATION_MODAL);
        setScene(scene);
    }
}
