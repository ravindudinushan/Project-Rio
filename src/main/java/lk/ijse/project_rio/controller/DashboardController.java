package lk.ijse.project_rio.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.project_rio.util.TimeAndDateController;

import java.io.IOException;

public class DashboardController {

    @FXML
    private Button homeBtn1;

    @FXML
    private AnchorPane homePane;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private AnchorPane adminChangingPane;


    @FXML
    void initialize() {
        assert lblDate != null : "fx:id=\"lblDate\" was not injected: check your FXML file 'dashboard_form.fxml'.";
        assert lblTime != null : "fx:id=\"lblTime\" was not injected: check your FXML file 'dashboard_form.fxml'.";

        TimeAndDateController timeAndDate = new TimeAndDateController();
        timeAndDate.timenow(lblTime,lblDate);
    }

    public void adminEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/lk.ijse.project_rio.view/employee_form.fxml"));
        adminChangingPane.getChildren().clear();
        adminChangingPane.getChildren().add(load);
    }

    public void clickOnActionSupplierForm(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/lk.ijse.project_rio.view/supplier_form.fxml"));
        adminChangingPane.getChildren().clear();
        adminChangingPane.getChildren().add(load);
    }

    public void adminInventoryOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/lk.ijse.project_rio.view/inventory_form.fxml"));
        adminChangingPane.getChildren().clear();
        adminChangingPane.getChildren().add(load);
    }
}
