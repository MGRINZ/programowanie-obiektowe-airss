package com.example.projekt1.Views;

import com.example.projekt1.ComputerServiceApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class AddOrderView extends Stage {

    public AddOrderView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ComputerServiceApplication.class.getResource("Views/order-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        setMinWidth(640);
        setMinHeight(480);
        setTitle("Dodawanie zgłoszenia");
        initModality(Modality.APPLICATION_MODAL);
        setScene(scene);
    }

}
