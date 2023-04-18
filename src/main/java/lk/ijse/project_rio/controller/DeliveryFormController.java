package lk.ijse.project_rio.controller;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.project_rio.dto.Delivery;
import lk.ijse.project_rio.dto.tm.DeliveryTM;
import lk.ijse.project_rio.model.DeliveryModel;
import lk.ijse.project_rio.model.EmployeeModel;
import lk.ijse.project_rio.model.OrderModel;
import lk.ijse.project_rio.util.AlertController;
import lk.ijse.project_rio.util.ValidateField;

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
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colDeliveryId;

    @FXML
    private TableColumn<?, ?> colDeliveryStatus;

    @FXML
    private TableColumn<?, ?> colEmpId;

    @FXML
    private TableColumn<?, ?> colLocation;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private ComboBox<String> comDelStatus;

    @FXML
    private ComboBox<String> comEmpId;

    @FXML
    private TableView<?> tblEmployee;

    @FXML
    private DatePicker txtDate;

    @FXML
    private TextField txtDueDate;

    @FXML
    private TextField txtLocation;

    @FXML
    private TextField txtSearchDeliveryId;

    @FXML
    private TextField txtSearchDueDate;

    @FXML
    private TextField txtSearchOrderId;

    @FXML
    private TextField txtSearchStatus;

    @FXML
    private Label lblDeliveryId;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblinvalidwrongduedate;

    @FXML
    private TableView<DeliveryTM> tblDelivery;

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String delid = lblDeliveryId.getText();
        String ordid = lblOrderId.getText();

        boolean result = AlertController.okconfirmmessage("Are you sure you want to cancel this delivery?");
        if(result==true) {
            try {
                boolean isDeleted = DeliveryModel.delete(delid);
                if (isDeleted) {
//                    boolean isUpdated = OrderModel.updatedelivery(ordid);
                    AlertController.confirmmessage("Delivery Cancelled Successfully");
                    lblOrderId.setText("");
                    lblDeliveryId.setText("");
                    comDelStatus.setValue(null);
                    comEmpId.setValue(null);
                    txtLocation.setText("");
                    txtDate.setValue(null);
                    txtDueDate.setText("");
                    getAll();
                }
            } catch (Exception e) {
                System.out.println(e);
                AlertController.errormessage("something went wrong!");
            }
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String delid = lblDeliveryId.getText();
        String delsts = comDelStatus.getValue();
        String loc = txtLocation.getText();
        String deldate = String.valueOf(txtDate.getValue());
        String duedate = txtDueDate.getText();
        String orderid = lblOrderId.getText();
        String empid = comEmpId.getValue();

        if(ValidateField.dateCheck(deldate) || deldate.equals("Pending")) {
            if(ValidateField.dateCheck(duedate) || duedate.isEmpty()) {
                Delivery delivery = new Delivery(delid, delsts, loc, deldate, duedate, orderid, empid);
                try {
                    boolean isUpdated = DeliveryModel.update(delivery);
                    if (isUpdated) {
                        AlertController.confirmmessage("Delivery Details Updated");
                        lblOrderId.setText("");
                        lblDeliveryId.setText("");
                        comDelStatus.setValue(null);
                        comEmpId.setValue(null);
                        txtLocation.setText("");
                        txtDate.setValue(null);
                        txtDueDate.setText("");
                        getAll();
                    }
                } catch (SQLException e) {
                    System.out.println(e);
                    AlertController.errormessage("something went wrong!");
                }
            }else{
                lblinvalidwrongduedate.setVisible(true);
            }
        }
    }

    @FXML
    void txtSearchDeliveryIdOnAction(ActionEvent event) {

    }

    @FXML
    void txtSearchDueDateOnAction(ActionEvent event) throws SQLException {
        String duedate = txtSearchDueDate.getText();
        ObservableList<DeliveryTM> obList = DeliveryModel.getByDueDate(duedate);

        tblDelivery.setItems(obList);
    }

    @FXML
    void txtSearchKeyTypedDeliveryId(KeyEvent event) {
        String delid = txtSearchOrderId.getText();
        try {
            Delivery delivery = DeliveryModel.findBydeliveryId(delid);
            if(delivery!=null) {
                comDelStatus.setDisable(false);
                txtLocation.setDisable(false);
                txtDate.setDisable(false);
                txtDueDate.setDisable(false);
                comEmpId.setDisable(false);

                lblDeliveryId.setText(delivery.getDelid());
                comDelStatus.setValue(delivery.getDelsts());
                txtLocation.setText(delivery.getLoc());
                txtDate.setValue(LocalDate.parse(delivery.getDeldate()));
                txtDueDate.setText(delivery.getDuedate());
                lblOrderId.setText(delivery.getOrdid());
                comEmpId.setValue(delivery.getEmpid());

                txtSearchOrderId.setText("");
            }else{
                AlertController.errormessage("Delivery ID Not Found");
            }
        } catch (Exception e) {
            System.out.println(e);
            AlertController.errormessage("Something Went Wrong");
        }
    }



    @FXML
    void txtSearchKeyTypedOrderId(KeyEvent event) {
        String id = txtSearchOrderId.getText();
        try {
            Delivery delivery = DeliveryModel.findById(id);
            if(delivery!=null) {
                comDelStatus.setDisable(false);
                txtLocation.setDisable(false);
                txtDate.setDisable(false);
                txtDueDate.setDisable(false);
                comEmpId.setDisable(false);

                lblDeliveryId.setText(delivery.getDelid());
                comDelStatus.setValue(delivery.getDelsts());
                txtLocation.setText(delivery.getLoc());
                txtDate.setValue(LocalDate.parse(delivery.getDeldate()));
                txtDueDate.setText(delivery.getDuedate());
                lblOrderId.setText(delivery.getOrdid());
                comEmpId.setValue(delivery.getEmpid());

                txtSearchOrderId.setText("");
            }else{
                AlertController.errormessage("Order ID Not Found");
            }
        } catch (SQLException e) {
            System.out.println(e);
            AlertController.errormessage("Something Went Wrong");
        }
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

    private void loadEmployeeIds() {
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<String> ids = EmployeeModel.loadIds();

            for (String id : ids) {
                obList.add(id);
            }
            comEmpId.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    private void setCellValueFactory() {
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderid"));
        colDeliveryId.setCellValueFactory(new PropertyValueFactory<>("delid"));
        colDeliveryStatus.setCellValueFactory(new PropertyValueFactory<>("delstatus"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("duedate"));
        colLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        colEmpId.setCellValueFactory(new PropertyValueFactory<>("empid"));
    }

    private void getAll(){
        ObservableList<DeliveryTM> obList = null;
        try {
            obList = DeliveryModel.getAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        tblDelivery.setItems(obList);
    }

    @FXML
    void initialize() {
        loadEmployeeIds();
        getAll();
        setCellValueFactory();
        comDelStatus.setDisable(true);
        txtLocation.setDisable(true);
        txtDate.setDisable(true);
        txtDueDate.setDisable(true);
        comEmpId.setDisable(true);
        assert adminChangingPane != null : "fx:id=\"adminChangingPane\" was not injected: check your FXML file 'delivery_form.fxml'.";
        assert btnDelete != null : "fx:id=\"btnDelete\" was not injected: check your FXML file 'delivery_form.fxml'.";
        assert btnUpdate != null : "fx:id=\"btnUpdate\" was not injected: check your FXML file 'delivery_form.fxml'.";
        assert colDate != null : "fx:id=\"colDate\" was not injected: check your FXML file 'delivery_form.fxml'.";
        assert colDeliveryId != null : "fx:id=\"colDeliveryId\" was not injected: check your FXML file 'delivery_form.fxml'.";
        assert colDeliveryStatus != null : "fx:id=\"colDeliveryStatus\" was not injected: check your FXML file 'delivery_form.fxml'.";
        assert colEmpId != null : "fx:id=\"colEmpId\" was not injected: check your FXML file 'delivery_form.fxml'.";
        assert colLocation != null : "fx:id=\"colLocation\" was not injected: check your FXML file 'delivery_form.fxml'.";
        assert colOrderId != null : "fx:id=\"colOrderId\" was not injected: check your FXML file 'delivery_form.fxml'.";
        assert comDelStatus != null : "fx:id=\"comDelStatus\" was not injected: check your FXML file 'delivery_form.fxml'.";
        assert comEmpId != null : "fx:id=\"comEmpId\" was not injected: check your FXML file 'delivery_form.fxml'.";
        assert tblEmployee != null : "fx:id=\"tblEmployee\" was not injected: check your FXML file 'delivery_form.fxml'.";
        assert txtDate != null : "fx:id=\"txtDate\" was not injected: check your FXML file 'delivery_form.fxml'.";
        assert txtDueDate != null : "fx:id=\"txtDueDate\" was not injected: check your FXML file 'delivery_form.fxml'.";
        assert txtLocation != null : "fx:id=\"txtLocation\" was not injected: check your FXML file 'delivery_form.fxml'.";
        assert txtSearchDeliveryId != null : "fx:id=\"txtSearchDeliveryId\" was not injected: check your FXML file 'delivery_form.fxml'.";
        assert txtSearchDueDate != null : "fx:id=\"txtSearchDueDate\" was not injected: check your FXML file 'delivery_form.fxml'.";
        assert txtSearchOrderId != null : "fx:id=\"txtSearchOrderId\" was not injected: check your FXML file 'delivery_form.fxml'.";
        assert txtSearchStatus != null : "fx:id=\"txtSearchStatus\" was not injected: check your FXML file 'delivery_form.fxml'.";
        comDelStatus.getItems().addAll("Completed","Not Yet Completed","Pending");

        lblinvalidwrongduedate.setVisible(false);
    }

}
