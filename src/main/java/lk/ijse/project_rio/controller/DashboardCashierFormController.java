package lk.ijse.project_rio.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.project_rio.util.TimeAndDateController;

public class DashboardCashierFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane cashierChangingPane;

    @FXML
    private Button cashierDashbordBtn;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;



    @FXML
    void cashierCustomerOnAction(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/lk.ijse.project_rio.view/customer_form.fxml"));
        cashierChangingPane.getChildren().clear();
        cashierChangingPane.getChildren().add(load);
    }

    @FXML
    void initialize() {
        assert lblDate != null : "fx:id=\"lblDate\" was not injected: check your FXML file 'dashboard_cashier_form.fxml'.";
        assert lblTime != null : "fx:id=\"lblTime\" was not injected: check your FXML file 'dashboard_cashier_form.fxml'.";
        TimeAndDateController timeAndDate = new TimeAndDateController();
        timeAndDate.timenow(lblTime,lblDate);
    }

}
