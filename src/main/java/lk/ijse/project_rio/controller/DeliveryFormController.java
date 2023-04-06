package lk.ijse.project_rio.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class DeliveryFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane adminChangingPane;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> empcoladdress;

    @FXML
    private TableColumn<?, ?> empcolcontact;

    @FXML
    private TableColumn<?, ?> empcoldob;

    @FXML
    private TableColumn<?, ?> empcolemail;

    @FXML
    private TableColumn<?, ?> empcolid;

    @FXML
    private TableColumn<?, ?> empcoljob;

    @FXML
    private TableColumn<?, ?> empcolname;

    @FXML
    private TableColumn<?, ?> empcolnic;

    @FXML
    private TableColumn<?, ?> empcolsalary;

    @FXML
    private TableView<?> tblEmployee;

    @FXML
    private TextField txtSearchDeliveryId;

    @FXML
    private TextField txtSearchDueDate;

    @FXML
    private TextField txtSearchOrderId;

    @FXML
    private TextField txtSearchStatus;

    @FXML
    private DatePicker txtempdob;

    @FXML
    private TextField txtempemail;

    @FXML
    private TextField txtempjob;

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void txtSearchDeliveryIdOnAction(ActionEvent event) {

    }

    @FXML
    void txtSearchDueDateOnAction(ActionEvent event) {

    }

    @FXML
    void txtSearchKeyTypedDeliveryId(KeyEvent event) {

    }

    @FXML
    void txtSearchKeyTypedDueDate(KeyEvent event) {

    }

    @FXML
    void txtSearchKeyTypedOrderId(KeyEvent event) {

    }

    @FXML
    void txtSearchKeyTypedStatus(KeyEvent event) {

    }

    @FXML
    void txtSearchOrderIdOnAction(ActionEvent event) {

    }

    @FXML
    void txtSearchStatusOnAction(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert adminChangingPane != null : "fx:id=\"adminChangingPane\" was not injected: check your FXML file 'delivery_form.fxml'.";
        assert btnDelete != null : "fx:id=\"btnDelete\" was not injected: check your FXML file 'delivery_form.fxml'.";
        assert btnUpdate != null : "fx:id=\"btnUpdate\" was not injected: check your FXML file 'delivery_form.fxml'.";
        assert empcoladdress != null : "fx:id=\"empcoladdress\" was not injected: check your FXML file 'delivery_form.fxml'.";
        assert empcolcontact != null : "fx:id=\"empcolcontact\" was not injected: check your FXML file 'delivery_form.fxml'.";
        assert empcoldob != null : "fx:id=\"empcoldob\" was not injected: check your FXML file 'delivery_form.fxml'.";
        assert empcolemail != null : "fx:id=\"empcolemail\" was not injected: check your FXML file 'delivery_form.fxml'.";
        assert empcolid != null : "fx:id=\"empcolid\" was not injected: check your FXML file 'delivery_form.fxml'.";
        assert empcoljob != null : "fx:id=\"empcoljob\" was not injected: check your FXML file 'delivery_form.fxml'.";
        assert empcolname != null : "fx:id=\"empcolname\" was not injected: check your FXML file 'delivery_form.fxml'.";
        assert empcolnic != null : "fx:id=\"empcolnic\" was not injected: check your FXML file 'delivery_form.fxml'.";
        assert empcolsalary != null : "fx:id=\"empcolsalary\" was not injected: check your FXML file 'delivery_form.fxml'.";
        assert tblEmployee != null : "fx:id=\"tblEmployee\" was not injected: check your FXML file 'delivery_form.fxml'.";
        assert txtSearchDeliveryId != null : "fx:id=\"txtSearchDeliveryId\" was not injected: check your FXML file 'delivery_form.fxml'.";
        assert txtSearchDueDate != null : "fx:id=\"txtSearchDueDate\" was not injected: check your FXML file 'delivery_form.fxml'.";
        assert txtSearchOrderId != null : "fx:id=\"txtSearchOrderId\" was not injected: check your FXML file 'delivery_form.fxml'.";
        assert txtSearchStatus != null : "fx:id=\"txtSearchStatus\" was not injected: check your FXML file 'delivery_form.fxml'.";
        assert txtempdob != null : "fx:id=\"txtempdob\" was not injected: check your FXML file 'delivery_form.fxml'.";
        assert txtempemail != null : "fx:id=\"txtempemail\" was not injected: check your FXML file 'delivery_form.fxml'.";
        assert txtempjob != null : "fx:id=\"txtempjob\" was not injected: check your FXML file 'delivery_form.fxml'.";

    }

}
