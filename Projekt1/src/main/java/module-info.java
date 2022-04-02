module com.example.projekt1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.projekt1.Controllers to javafx.fxml;
    opens com.example.projekt1.Views to javafx.fxml;
    exports com.example.projekt1.Views;
    exports com.example.projekt1.Model;
    exports com.example.projekt1;
    opens com.example.projekt1 to javafx.fxml;
}