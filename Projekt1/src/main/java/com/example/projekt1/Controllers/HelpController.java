package com.example.projekt1.Controllers;

import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class HelpController {

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void onClose(ActionEvent actionEvent) {
        stage.close();
    }
}
