package com.example.projekt1.Controllers;

import com.example.projekt1.Model.ComputerService;
import com.example.projekt1.Model.Maintainer;
import com.example.projekt1.Views.AddEditMaintainerView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ManageMaintainersController implements Initializable {

    @FXML
    private ListView<Maintainer> maintainers;

    private Stage stage;
    private ComputerService computerService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        computerService = ComputerService.getInstance();

        maintainers.setCellFactory(maintainerListView -> {
            TextFieldListCell<Maintainer> cell = new TextFieldListCell<>();
            cell.setConverter(new StringConverter<Maintainer>() {
                @Override
                public String toString(Maintainer maintainer) {
                    return maintainer.getName();
                }

                @Override
                public Maintainer fromString(String s) {
                    return null;
                }
            });
            return cell;
        });
        updateList();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void updateList() {
        maintainers.getItems().setAll(computerService.getMaintainers());
    }

    @FXML
    private void onAddClick(ActionEvent actionEvent) throws IOException {
        AddEditMaintainerView addEditMaintainerView = new AddEditMaintainerView();
        addEditMaintainerView.initOwner(stage);
        addEditMaintainerView.show();
    }

    @FXML
    private void onEditClick(ActionEvent actionEvent) throws IOException {
        int maintainerIndex = maintainers.getSelectionModel().getSelectedIndex();

        if(maintainerIndex == -1)
            return;

        Maintainer maintainer = computerService.getMaintainers().get(maintainerIndex);

        AddEditMaintainerView addEditMaintainerView = new AddEditMaintainerView(maintainer);
        addEditMaintainerView.initOwner(stage);
        addEditMaintainerView.show();
    }

    @FXML
    private void onDeleteClick(ActionEvent actionEvent) {
        int maintainerIndex = maintainers.getSelectionModel().getSelectedIndex();

        if(maintainerIndex == -1)
            return;

        Maintainer maintainer = computerService.getMaintainers().get(maintainerIndex);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Potwierdź usunięcie serwisanta");
        alert.setTitle("Usunięcie serwisanta");
        alert.setContentText("Czy na pewno usunąć serwisanta?");
        alert.initOwner(stage);

        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            computerService.getMaintainers().remove(maintainer);
            maintainers.getItems().remove(maintainer);
        }
    }

}
