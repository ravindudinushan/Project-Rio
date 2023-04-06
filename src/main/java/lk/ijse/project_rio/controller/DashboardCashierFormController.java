package lk.ijse.project_rio.controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.project_rio.util.BtnColorController;
import lk.ijse.project_rio.util.LogOutController;
import lk.ijse.project_rio.util.TimeAndDateController;

public class DashboardCashierFormController {

    @FXML
    private AnchorPane cashierChangingPane;

    @FXML
    private Button customerbtn;

    @FXML
    private Button deliverybtn;

    @FXML
    private Button eventbtn;

    @FXML
    private Button homebtn;

    @FXML
    private Button inventorybtn;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private Button orderbtn;

    @FXML
    private AnchorPane cashDashPane;

    @FXML
    private Button supplyLoadbtn;

    @FXML
    void cashierCustomerOnAction(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/lk.ijse.project_rio.view/customer_form.fxml"));
        cashierChangingPane.getChildren().clear();
        cashierChangingPane.getChildren().add(load);
        BtnColorController.btncolor(customerbtn,cashierChangingPane);
    }

    @FXML
    void initialize() {
        assert lblDate != null : "fx:id=\"lblDate\" was not injected: check your FXML file 'dashboard_cashier_form.fxml'.";
        assert lblTime != null : "fx:id=\"lblTime\" was not injected: check your FXML file 'dashboard_cashier_form.fxml'.";
        TimeAndDateController timeAndDate = new TimeAndDateController();
        timeAndDate.timenow(lblTime,lblDate);
    }

    public void clickOnActionInventory(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/lk.ijse.project_rio.view/inventory_form.fxml"));
        cashierChangingPane.getChildren().clear();
        cashierChangingPane.getChildren().add(load);
        BtnColorController.btncolor(inventorybtn,cashierChangingPane);
    }

    public void cashierOrderOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/lk.ijse.project_rio.view/order_form.fxml"));
        cashierChangingPane.getChildren().clear();
        cashierChangingPane.getChildren().add(load);
        BtnColorController.btncolor(orderbtn,cashierChangingPane);
    }

    public void cashierEventOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/lk.ijse.project_rio.view/event_form.fxml"));
        cashierChangingPane.getChildren().clear();
        cashierChangingPane.getChildren().add(load);
        BtnColorController.btncolor(eventbtn,cashierChangingPane);
    }

    public void logoutbtnOnMousePressed(MouseEvent mouseEvent) throws IOException {
        LogOutController.logout(cashDashPane);
    }

    public void cashierSupplyLoadOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/lk.ijse.project_rio.view/new_supply_load_form.fxml"));
        cashierChangingPane.getChildren().clear();
        cashierChangingPane.getChildren().add(load);
        BtnColorController.btncolor(supplyLoadbtn,cashierChangingPane);
    }

    public void clickOnActionDelivery(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/lk.ijse.project_rio.view/delivery_form.fxml"));
        cashierChangingPane.getChildren().clear();
        cashierChangingPane.getChildren().add(load);
        BtnColorController.btncolor(deliverybtn,cashierChangingPane);
    }
}
