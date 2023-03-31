package lk.ijse.project_rio.controller;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.project_rio.dto.tm.CartTM;
import lk.ijse.project_rio.model.CustomerModel;
import lk.ijse.project_rio.model.InventoryModel;

public class OrderFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField TxtOrderId;

    @FXML
    private TableColumn<?, ?> actionCol;

    @FXML
    private Button addToCartBtn;

    @FXML
    private TableColumn<?, ?> categortCol;

    @FXML
    private TableColumn<?, ?> deliveryCol;

    @FXML
    private TableColumn<?, ?> itemIdCol;

    @FXML
    private TableColumn<?, ?> itemNameCol;

    @FXML
    private Button placeOrderBt;

    @FXML
    private TableColumn<?, ?> qtyCol;

    @FXML
    private RadioButton radioBtn;

    @FXML
    private TableView<?> tblOrder;

    @FXML
    private TableColumn<?, ?> totleCol;

    @FXML
    private TextField txtCategory;

    @FXML
    private ComboBox<String> txtCustId;

    @FXML
    private TextField txtCustName;

    @FXML
    private ComboBox<String> txtItemId;

    @FXML
    private TextField txtItemName;

    @FXML
    private TextField txtNetTotle;

    @FXML
    private TextField txtOrderDate;

    @FXML
    private TextField txtOrderTime;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtQtyOnHand;

    @FXML
    private TextField txtUnitPrice;

    @FXML
    private TableColumn<?, ?> unitPriceCol;

    private ObservableList<CartTM> obList = FXCollections.observableArrayList();

    @FXML
    void addToCartOnAction(ActionEvent event) {
        String code = txtItemId.getValue();
        int qty = Integer.parseInt(txtQty.getText());
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        double total = quantity * unitPrice;
        Button btnRemove = new Button("Remove");
        btnRemove.setCursor(Cursor.HAND);

        setRemoveBtnOnAction(btnRemove); /* set action to the btnRemove */

        if (!obList.isEmpty()) {
            for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
                if (colItemCode.getCellData(i).equals(code)) {
                    qty += (int) colQty.getCellData(i);
                    total = qty * unitPrice;

                    obList.get(i).setQty(qty);
                    obList.get(i).setTotal(total);

                    tblOrderCart.refresh();
                    calculateNetTotal();
                    return;
                }
            }
        }
    }

    @FXML
    void clickOnActionPlaceOrder(ActionEvent event) {

    }

    @FXML
    void clickOnActionRadioBtn(ActionEvent event) {

    }

    private void loadCustomerIds() {
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<String> ids = CustomerModel.loadIds();

            for (String id : ids) {
                obList.add(id);
            }
            txtCustId.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    private void loadItemIds() {
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<String> codes = InventoryModel.loadItemIds();

            for (String code : codes) {
                obList.add(code);
            }
            txtItemId.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    private void setOrderDate() {
        txtOrderDate.setText(String.valueOf(LocalDate.now()));
    }

    @FXML
    void txtQtyOnAction(ActionEvent event) {
        addToCartOnAction(event);
    }

    private void calculateNetTotal() {
        double netTotal = 0.0;
        for (int i = 0; i < tblOrder.getItems().size(); i++) {
            double total = (double) totleCol.getCellData(i);
            netTotal += total;
        }
        txtNetTotle.setText(String.valueOf(netTotal));
    }

    private void setRemoveBtnOnAction(Button btn) {
        btn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (result.orElse(no) == yes) {

                int index = tblOrder.getSelectionModel().getSelectedIndex();
                obList.remove(index);

                tblOrder.refresh();
                calculateNetTotal();
            }

        });
    }

    void setCellValueFactory() {
        itemIdCol.setCellValueFactory(new PropertyValueFactory<>("code"));
        itemNameCol.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        categortCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        deliveryCol.setCellValueFactory(new PropertyValueFactory<>("delivery"));
        unitPriceCol.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        totleCol.setCellValueFactory(new PropertyValueFactory<>("total"));
        actionCol.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    @FXML
    void initialize() {
        setCellValueFactory();
        loadCustomerIds();
        loadItemIds();
        setOrderDate();
        assert TxtOrderId != null : "fx:id=\"TxtOrderId\" was not injected: check your FXML file 'order_form.fxml'.";
        assert actionCol != null : "fx:id=\"actionCol\" was not injected: check your FXML file 'order_form.fxml'.";
        assert addToCartBtn != null : "fx:id=\"addToCartBtn\" was not injected: check your FXML file 'order_form.fxml'.";
        assert categortCol != null : "fx:id=\"categortCol\" was not injected: check your FXML file 'order_form.fxml'.";
        assert deliveryCol != null : "fx:id=\"deliveryCol\" was not injected: check your FXML file 'order_form.fxml'.";
        assert itemIdCol != null : "fx:id=\"itemIdCol\" was not injected: check your FXML file 'order_form.fxml'.";
        assert itemNameCol != null : "fx:id=\"itemNameCol\" was not injected: check your FXML file 'order_form.fxml'.";
        assert placeOrderBt != null : "fx:id=\"placeOrderBt\" was not injected: check your FXML file 'order_form.fxml'.";
        assert qtyCol != null : "fx:id=\"qtyCol\" was not injected: check your FXML file 'order_form.fxml'.";
        assert radioBtn != null : "fx:id=\"radioBtn\" was not injected: check your FXML file 'order_form.fxml'.";
        assert tblOrder != null : "fx:id=\"tblOrder\" was not injected: check your FXML file 'order_form.fxml'.";
        assert totleCol != null : "fx:id=\"totleCol\" was not injected: check your FXML file 'order_form.fxml'.";
        assert txtCategory != null : "fx:id=\"txtCategory\" was not injected: check your FXML file 'order_form.fxml'.";
        assert txtCustId != null : "fx:id=\"txtCustId\" was not injected: check your FXML file 'order_form.fxml'.";
        assert txtCustName != null : "fx:id=\"txtCustName\" was not injected: check your FXML file 'order_form.fxml'.";
        assert txtItemId != null : "fx:id=\"txtItemId\" was not injected: check your FXML file 'order_form.fxml'.";
        assert txtItemName != null : "fx:id=\"txtItemName\" was not injected: check your FXML file 'order_form.fxml'.";
        assert txtNetTotle != null : "fx:id=\"txtNetTotle\" was not injected: check your FXML file 'order_form.fxml'.";
        assert txtOrderDate != null : "fx:id=\"txtOrderDate\" was not injected: check your FXML file 'order_form.fxml'.";
        assert txtOrderTime != null : "fx:id=\"txtOrderTime\" was not injected: check your FXML file 'order_form.fxml'.";
        assert txtQty != null : "fx:id=\"txtQty\" was not injected: check your FXML file 'order_form.fxml'.";
        assert txtQtyOnHand != null : "fx:id=\"txtQtyOnHand\" was not injected: check your FXML file 'order_form.fxml'.";
        assert txtUnitPrice != null : "fx:id=\"txtUnitPrice\" was not injected: check your FXML file 'order_form.fxml'.";
        assert unitPriceCol != null : "fx:id=\"unitPriceCol\" was not injected: check your FXML file 'order_form.fxml'.";

    }
}
