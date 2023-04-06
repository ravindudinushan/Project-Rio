package lk.ijse.project_rio.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import lk.ijse.project_rio.dto.CartDTO;
import lk.ijse.project_rio.dto.Inventory;
import lk.ijse.project_rio.dto.Supplier;
import lk.ijse.project_rio.dto.tm.CartTM;
import lk.ijse.project_rio.model.InventoryModel;
import lk.ijse.project_rio.model.NewSupplyLoadModel;
import lk.ijse.project_rio.model.OrderModel;
import lk.ijse.project_rio.model.SupplierModel;
import lk.ijse.project_rio.util.TimeAndDateController;

public class NewSupplyLoadFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<?, ?> ItemIdCol;

    @FXML
    private TableColumn<?, ?> ItemNameCol;

    @FXML
    private TableColumn<?, ?> actionCol;

    @FXML
    private Button addSupplyLoadBtn;

    @FXML
    private Button addToLoadBtn;

    @FXML
    private AnchorPane adminChangingPane;

    @FXML
    private TableColumn<?, ?> categoryCol;

    @FXML
    private ComboBox<String> comItemId;

    @FXML
    private ComboBox<String> comSupId;

    @FXML
    private Label lblCategory;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblItemName;

    @FXML
    private Label lblQty;

    @FXML
    private Label lblSupId;

    @FXML
    private Label lblSupName;

    @FXML
    private Label lblTime;

    @FXML
    private TableColumn<?, ?> quantityCol;

    @FXML
    private ImageView searchIcon;

    @FXML
    private TableView<?> tblNewSupplyLoad;

    @FXML
    private TextField txtEventName;

    @FXML
    private TextField txtSearch;

    @FXML
    private TextField txtSupLoad;

    @FXML
    void btnAddSupplyLoadOnAction(ActionEvent event) {
//        String oId = comItemId.getText();
//        String cusId = txtCustId.getValue();
//        Boolean delivery = radioBtn.isSelected();
//        String totle = lblNetTotle.getText();
//
//        List<CartDTO> cartDTOList = new ArrayList<>();
//
//        for (int i = 0; i < tblNewSupplyLoad.getItems().size(); i++) {
//            CartTM cartTM = obList.get(i);
//
//            CartDTO dto = new CartDTO(
//                    cartTM.getItemId(),
//                    cartTM.getQuantity()
//            );
//            cartDTOList.add(dto);
//        }
//
//        boolean isPlaced = false;
//        try {
//            isPlaced = SupplierModel.placeOrder(oId, cusId, delivery, totle, cartDTOList);
//            if(isPlaced) {
//                new Alert(Alert.AlertType.CONFIRMATION, "Order Placed").show();
//            } else {
//                new Alert(Alert.AlertType.ERROR, "Order Not Placed").show();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            new Alert(Alert.AlertType.ERROR, "SQL Error").show();
//        }
    }

    @FXML
    void btnAddToLoadOnAction(ActionEvent event) {
//        String code = comItemId.getValue();
//        String name = lblItemName.getText();
//        String category = lblCategory.getText();
//        int qty = Integer.parseInt(lblQty.getText());
//        double unitPrice = Double.parseDouble(lblItemUnitPrice.getText());
//        double total = qty * unitPrice;
//        Button btnRemove = new Button("Remove");
//        btnRemove.setCursor(Cursor.HAND);
//
//        setRemoveBtnOnAction(btnRemove); /* set action to the btnRemove */
//
//        if (!obList.isEmpty()) {
//            for (int i = 0; i < tblNewSupplyLoad.getItems().size(); i++) {
//                if (itemIdCol.getCellData(i).equals(code)) {
//                    qty += (int) qtyCol.getCellData(i);
//                    total = qty * unitPrice;
//
//                    obList.get(i).setQuantity(qty);
//                    obList.get(i).setTotal(total);
//
//                    tblNewSupplyLoad.refresh();
//                    calculateNetTotal();
//                    return;
//                }
//            }
//        }
//        CartTM tm = new CartTM(code, name, category, qty, unitPrice, total, btnRemove);
//
//        obList.add(tm);
//        tblOrder.setItems(obList);
//        calculateNetTotal();
//
//        txtQty.setText("");
    }

    private void setRemoveBtnOnAction(Button btn) {
//        btn.setOnAction((e) -> {
//            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
//            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
//
//            Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();
//
//            if (result.orElse(no) == yes) {
//
//                int index = tblNewSupplyLoad.getSelectionModel().getSelectedIndex();
//                obList.remove(index);
//
//                tblNewSupplyLoad.refresh();
//                calculateNetTotal();
//            }
//
//        });
    }


    private void loadItemIds() {
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<String> codes = InventoryModel.loadItemIds();

            for (String code : codes) {
                obList.add(code);
            }
            comItemId.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    private void loadSupplierIds() {
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<String> codes = SupplierModel.loadSupIds();

            for (String code : codes) {
                obList.add(code);
            }
            comSupId.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    public void comSupIdOnAction(ActionEvent actionEvent) {
        String supId= comSupId.getValue();

        try {
            Supplier supplier = SupplierModel.findById(supId);
            lblSupName.setText(supplier.getName());
        } catch (Exception e) {

        }
    }

    public void comItemIdOnAction(ActionEvent actionEvent) {
        String itemcode= comItemId.getValue();

        try {
            Inventory item = InventoryModel.findById(itemcode);
            lblItemName.setText(item.getName());
            lblCategory.setText(item.getCategory());
            lblQty.setText(String.valueOf(item.getQtyOnHand()));
        } catch (Exception e) {

        }
    }

    private void generateNextSupId() {
        try {
            String id = SupplierModel.getNextSupId();
            lblSupId.setText(id);
        } catch (Exception e) {
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, "Exception!").show();
        }
    }

    @FXML
    void initialize() {
        loadItemIds();
        loadSupplierIds();
        generateNextSupId();
        TimeAndDateController.timenow(lblTime,lblDate);
        assert ItemIdCol != null : "fx:id=\"ItemIdCol\" was not injected: check your FXML file 'new_supply_load_form.fxml'.";
        assert ItemNameCol != null : "fx:id=\"ItemNameCol\" was not injected: check your FXML file 'new_supply_load_form.fxml'.";
        assert actionCol != null : "fx:id=\"actionCol\" was not injected: check your FXML file 'new_supply_load_form.fxml'.";
        assert addSupplyLoadBtn != null : "fx:id=\"addSupplyLoadBtn\" was not injected: check your FXML file 'new_supply_load_form.fxml'.";
        assert addToLoadBtn != null : "fx:id=\"addToCartBtn\" was not injected: check your FXML file 'new_supply_load_form.fxml'.";
        assert adminChangingPane != null : "fx:id=\"adminChangingPane\" was not injected: check your FXML file 'new_supply_load_form.fxml'.";
        assert categoryCol != null : "fx:id=\"categoryCol\" was not injected: check your FXML file 'new_supply_load_form.fxml'.";
        assert comItemId != null : "fx:id=\"comItemId\" was not injected: check your FXML file 'new_supply_load_form.fxml'.";
        assert comSupId != null : "fx:id=\"comSupId\" was not injected: check your FXML file 'new_supply_load_form.fxml'.";
        assert lblCategory != null : "fx:id=\"lblCategory\" was not injected: check your FXML file 'new_supply_load_form.fxml'.";
        assert lblDate != null : "fx:id=\"lblDate\" was not injected: check your FXML file 'new_supply_load_form.fxml'.";
        assert lblItemName != null : "fx:id=\"lblItemName\" was not injected: check your FXML file 'new_supply_load_form.fxml'.";
        assert lblQty != null : "fx:id=\"lblQty\" was not injected: check your FXML file 'new_supply_load_form.fxml'.";
        assert lblSupId != null : "fx:id=\"lblSupId\" was not injected: check your FXML file 'new_supply_load_form.fxml'.";
        assert lblSupName != null : "fx:id=\"lblSupName\" was not injected: check your FXML file 'new_supply_load_form.fxml'.";
        assert lblTime != null : "fx:id=\"lblTime\" was not injected: check your FXML file 'new_supply_load_form.fxml'.";
        assert quantityCol != null : "fx:id=\"quantityCol\" was not injected: check your FXML file 'new_supply_load_form.fxml'.";
        assert searchIcon != null : "fx:id=\"searchIcon\" was not injected: check your FXML file 'new_supply_load_form.fxml'.";
        assert tblNewSupplyLoad != null : "fx:id=\"tblNewSupplyLoad\" was not injected: check your FXML file 'new_supply_load_form.fxml'.";
        assert txtEventName != null : "fx:id=\"txtEventName\" was not injected: check your FXML file 'new_supply_load_form.fxml'.";
        assert txtSearch != null : "fx:id=\"txtSearch\" was not injected: check your FXML file 'new_supply_load_form.fxml'.";
        assert txtSupLoad != null : "fx:id=\"txtSupLoad\" was not injected: check your FXML file 'new_supply_load_form.fxml'.";

    }
}
