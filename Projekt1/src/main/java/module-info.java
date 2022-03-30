module com.example.projekt1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.projekt1 to javafx.fxml;
    exports com.example.projekt1;
    exports com.example.projekt1.Views;
    opens com.example.projekt1.Views to javafx.fxml;
}