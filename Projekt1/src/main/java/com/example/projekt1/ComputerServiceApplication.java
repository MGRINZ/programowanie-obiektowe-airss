package com.example.projekt1;

import com.example.projekt1.Views.ComputerServiceView;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class ComputerServiceApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        ComputerServiceView computerServiceView = new ComputerServiceView();
        computerServiceView.show();
    }

    public static void main(String[] args) {
        launch();
    }
}