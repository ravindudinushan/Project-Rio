package lk.ijse.project_rio.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.project_rio.db.DBConnection;
import lk.ijse.project_rio.dto.CartDTO;
import lk.ijse.project_rio.dto.Inventory;
import lk.ijse.project_rio.dto.tm.CartTM;
import lk.ijse.project_rio.model.CustomerModel;
import lk.ijse.project_rio.model.InventoryModel;
import lk.ijse.project_rio.model.OrderModel;
import lk.ijse.project_rio.util.AlertController;
import lk.ijse.project_rio.util.TimeAndDateController;
import lk.ijse.project_rio.util.ValidateField;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class OrderFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
    private Label lblCustName;

    @FXML
    private Label lblItemCategory;

    @FXML
    private Label lblItemName11;

    @FXML
    private Label lblItemQtyOnHand;

    @FXML
    private Label lblItemUnitPrice;

    @FXML
    private Label lblOrderDate;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblOrderTime;

    @FXML
    private Button placeOrderBt;

    @FXML
    private TableColumn<?, ?> qtyCol;

    @FXML
    private RadioButton radioBtn;

    @FXML
    private TableView<CartTM> tblOrder;

    @FXML
    private TableColumn<?, ?> totleCol;

    @FXML
    private ComboBox<String> txtCustId;

    @FXML
    private ComboBox<String> txtItemId;

    @FXML
    private TextField txtNetTotle;

    @FXML
    private TextField txtQty;

    @FXML
    private TableColumn<?, ?> unitPriceCol;

    @FXML
    private Label lblNetTotle;

    @FXML
    private AnchorPane adminChangingPane;

    @FXML
    private Button OrderReportBtn;

    @FXML
    private Label balancelbl;

    @FXML
    private Label lblmoremoneyneeded;

    @FXML
    private TextField txtpaidamount;

    @FXML
    private Label txtmoremoney;

    @FXML
    private Label lblmoreneeded;

    private ObservableList<CartTM> obList = FXCollections.observableArrayList();

    @FXML
    void addToCartOnAction(ActionEvent event) {
        String code = txtItemId.getValue();
        String name = lblItemName11.getText();
        String category = lblItemCategory.getText();
        int qty = Integer.parseInt(txtQty.getText());
        double unitPrice = Double.parseDouble(lblItemUnitPrice.getText());
        double total = qty * unitPrice;
        Button btnRemove = new Button("Remove");
        btnRemove.setCursor(Cursor.HAND);

        setRemoveBtnOnAction(btnRemove); /* set action to the btnRemove */

        if (ValidateField.numberCheck(String.valueOf(qty))) {
            if (qty> Integer.parseInt(lblItemQtyOnHand.getText())) {
                AlertController.errormessage("Item Out of stock or Insufficient amount");
            } else {
                if (!obList.isEmpty()) {
                    int newval= Integer.parseInt(lblItemQtyOnHand.getText())-Integer.parseInt(txtQty.getText());
                    lblItemQtyOnHand.setText(String.valueOf(newval));
                    for (int i = 0; i < tblOrder.getItems().size(); i++) {
                        if (itemIdCol.getCellData(i).equals(code)) {
                            qty += (int) qtyCol.getCellData(i);
                            total = qty * unitPrice;

                            obList.get(i).setQuantity(qty);
                            obList.get(i).setTotal(total);

                            tblOrder.refresh();
                            calculateNetTotal();
                            return;
                        }
                    }
                } else {
                    int newval= Integer.parseInt(lblItemQtyOnHand.getText())-Integer.parseInt(txtQty.getText());
                    lblItemQtyOnHand.setText(String.valueOf(newval));
                }
                CartTM tm = new CartTM(code, name, category, qty, unitPrice, total, btnRemove);

                obList.add(tm);
                tblOrder.setItems(obList);
                calculateNetTotal();

                txtQty.setText("");
            }
        }else{
            AlertController.errormessage("Wrong format for Quantity field");
        }

    }

    @FXML
    void clickOnActionPlaceOrder(ActionEvent event) {
        String oId = lblOrderId.getText();
        String cusId = txtCustId.getValue();
        Boolean delivery = radioBtn.isSelected();
        String totle = lblNetTotle.getText();

        List<CartDTO> cartDTOList = new ArrayList<>();

        for (int i = 0; i < tblOrder.getItems().size(); i++) {
            CartTM cartTM = obList.get(i);

            CartDTO dto = new CartDTO(
                    cartTM.getItemId(),
                    cartTM.getQuantity()
            );
            cartDTOList.add(dto);
        }

        boolean isPlaced = false;
        try {
            isPlaced = OrderModel.placeOrder(oId, cusId, delivery, totle, cartDTOList);
            if(isPlaced) {
                AlertController.confirmmessage("Order Placed");
                generateNextOrderId();

                String balance = balancelbl.getText();
                String printcash = txtpaidamount.getText();
                txtCustId.setValue(null);
                txtItemId.setValue(null);
                lblCustName.setText("");
                lblItemName11.setText("");
                lblItemUnitPrice.setText("");
                lblItemCategory.setText("");
                lblItemQtyOnHand.setText("");
                lblNetTotle.setText("0/=");
                radioBtn.setSelected(false);
                tblOrder.getItems().clear();
                txtpaidamount.setText("");
                balancelbl.setVisible(false);
                lblNetTotle.setVisible(false);
                //txtmoremoney.setText("");
                boolean result = AlertController.okconfirmmessage("Do you want the bill ?");

                if (result) {

                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("param1", printcash);
                    parameters.put("param2", balance);

                    InputStream resource = this.getClass().getResourceAsStream("/lk.ijse.project_rio.reports/orderPlacement_A4.jrxml");
                    try {
                        JasperReport jasperReport = JasperCompileManager.compileReport(resource);
                        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, DBConnection.getInstance().getConnection());
                        JasperViewer.viewReport(jasperPrint, false);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }}
            } else {
                new Alert(Alert.AlertType.ERROR, "Order Not Placed").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error").show();
        }
    }

    @FXML
    void clickOnActionRadioBtn(ActionEvent event) throws IOException {
        if(radioBtn.isSelected()){
            Stage stage = new Stage();
            stage.resizableProperty().setValue(false);
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk.ijse.project_rio.view/add_new_delivery_form.fxml"))));
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.centerOnScreen();
            stage.getIcons().add(new Image("lk.ijse.project_rio.assets/logo.png"));
            stage.show();
        }
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
        lblNetTotle.setText(String.valueOf(netTotal));
    }

    private void setRemoveBtnOnAction(Button btn) {
        btn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (result.orElse(no) == yes) {
                int index = tblOrder.getSelectionModel().getSelectedIndex();

                TablePosition pos = tblOrder.getSelectionModel().getSelectedCells().get(0);
                int row = pos.getRow();
                ObservableList<TableColumn<CartTM, ?>> columns = tblOrder.getColumns();

                String itemName = String.valueOf(columns.get(1).getCellData(row));
                int newval = Integer.parseInt(lblItemQtyOnHand.getText()) + Integer.parseInt(columns.get(3).getCellData(row).toString());
                if(itemName.equals(lblItemName11.getText())) {
                    lblItemQtyOnHand.setText(String.valueOf(newval));
                }

                obList.remove(index);
                tblOrder.refresh();
                calculateNetTotal();
            }

        });
    }

    @FXML
    void cobCustOnAction(ActionEvent event) {
        String custId = txtCustId.getValue();

        try {
            String name = CustomerModel.getCustName(custId);
            lblCustName.setText(name);
        } catch (Exception e) {
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, "Exception!").show();
        }
    }

    @FXML
    void cobItemOnAction(ActionEvent event) {
        String itemcode= txtItemId.getValue();

        try {
            Inventory item = InventoryModel.findById(itemcode);
            lblItemName11.setText(item.getName());
            lblItemUnitPrice.setText(String.valueOf(item.getUnitPrice()));
            lblItemCategory.setText(item.getCategory());
            lblItemQtyOnHand.setText(String.valueOf(item.getQtyOnHand()));
        } catch (Exception e) {
        }
    }

    private void generateNextOrderId() {
        try {
            String id = OrderModel.getNextOrderId();
            lblOrderId.setText(id);
        } catch (Exception e) {
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, "Exception!").show();
        }
    }

    void setCellValueFactory() {
        itemIdCol.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        itemNameCol.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        categortCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        unitPriceCol.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        totleCol.setCellValueFactory(new PropertyValueFactory<>("total"));
        actionCol.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    @FXML
    void initialize() {
        setCellValueFactory();
        loadCustomerIds();
        loadItemIds();
        generateNextOrderId();
        TimeAndDateController.timenow(lblOrderTime,lblOrderDate);
        assert actionCol != null : "fx:id=\"actionCol\" was not injected: check your FXML file 'order_form.fxml'.";
        assert addToCartBtn != null : "fx:id=\"addToCartBtn\" was not injected: check your FXML file 'order_form.fxml'.";
        assert categortCol != null : "fx:id=\"categortCol\" was not injected: check your FXML file 'order_form.fxml'.";
        assert deliveryCol != null : "fx:id=\"deliveryCol\" was not injected: check your FXML file 'order_form.fxml'.";
        assert itemIdCol != null : "fx:id=\"itemIdCol\" was not injected: check your FXML file 'order_form.fxml'.";
        assert itemNameCol != null : "fx:id=\"itemNameCol\" was not injected: check your FXML file 'order_form.fxml'.";
        assert lblCustName != null : "fx:id=\"lblCustName\" was not injected: check your FXML file 'order_form.fxml'.";
        assert lblItemCategory != null : "fx:id=\"lblItemCategory\" was not injected: check your FXML file 'order_form.fxml'.";
        assert lblItemName11 != null : "fx:id=\"lblItemName11\" was not injected: check your FXML file 'order_form.fxml'.";
        assert lblItemQtyOnHand != null : "fx:id=\"lblItemQtyOnHand\" was not injected: check your FXML file 'order_form.fxml'.";
        assert lblItemUnitPrice != null : "fx:id=\"lblItemUnitPrice\" was not injected: check your FXML file 'order_form.fxml'.";
        assert lblNetTotle != null : "fx:id=\"lblNetTotle\" was not injected: check your FXML file 'order_form.fxml'.";
        assert lblOrderDate != null : "fx:id=\"lblOrderDate\" was not injected: check your FXML file 'order_form.fxml'.";
        assert lblOrderId != null : "fx:id=\"lblOrderId\" was not injected: check your FXML file 'order_form.fxml'.";
        assert lblOrderTime != null : "fx:id=\"lblOrderTime\" was not injected: check your FXML file 'order_form.fxml'.";
        assert placeOrderBt != null : "fx:id=\"placeOrderBt\" was not injected: check your FXML file 'order_form.fxml'.";
        assert qtyCol != null : "fx:id=\"qtyCol\" was not injected: check your FXML file 'order_form.fxml'.";
        assert radioBtn != null : "fx:id=\"radioBtn\" was not injected: check your FXML file 'order_form.fxml'.";
        assert tblOrder != null : "fx:id=\"tblOrder\" was not injected: check your FXML file 'order_form.fxml'.";
        assert totleCol != null : "fx:id=\"totleCol\" was not injected: check your FXML file 'order_form.fxml'.";
        assert txtCustId != null : "fx:id=\"txtCustId\" was not injected: check your FXML file 'order_form.fxml'.";
        assert txtItemId != null : "fx:id=\"txtItemId\" was not injected: check your FXML file 'order_form.fxml'.";
        assert txtQty != null : "fx:id=\"txtQty\" was not injected: check your FXML file 'order_form.fxml'.";
        assert unitPriceCol != null : "fx:id=\"unitPriceCol\" was not injected: check your FXML file 'order_form.fxml'.";
        placeOrderBt.setDisable(true);

        lblmoreneeded.setVisible(false);
    }

    public void orderReportOnAction(ActionEvent actionEvent) {

    }

    public void txtpaidamountKeyTyped(KeyEvent keyEvent) {
        if (!txtpaidamount.getText().isEmpty()) {
            if (ValidateField.priceCheck(txtpaidamount.getText())) {
                double balance = Double.parseDouble(txtpaidamount.getText()) - Double.parseDouble(lblNetTotle.getText());
                if (balance >= 0) {
                    txtpaidamount.setStyle("-fx-text-fill: black");
                    balancelbl.setText(String.valueOf(balance));
                    balancelbl.setVisible(true);
                    lblmoreneeded.setVisible(false);
                    txtmoremoney.setText("");
                    placeOrderBt.setDisable(false);
                } else if (balance < 0) {
                    txtpaidamount.setStyle("-fx-text-fill: black");
                    placeOrderBt.setDisable(true);
                    balancelbl.setVisible(false);
                    double positbalance = Math.abs(balance);
                    lblmoreneeded.setVisible(true);
                    txtmoremoney.setText(positbalance + "/=");
                }
            } else {
                placeOrderBt.setDisable(true);
                txtpaidamount.setStyle("-fx-text-fill: red");
                balancelbl.setVisible(false);

            }
        }
    }
}