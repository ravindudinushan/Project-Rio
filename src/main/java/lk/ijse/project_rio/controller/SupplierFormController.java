package lk.ijse.project_rio.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.ijse.project_rio.dto.Supplier;
import lk.ijse.project_rio.dto.tm.SupplierTM;
import lk.ijse.project_rio.model.EmployeeModel;
import lk.ijse.project_rio.model.SupplierModel;
import lk.ijse.project_rio.util.AlertController;

public class SupplierFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button deleteBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private ImageView searchIcon;

    @FXML
    private TextField supAddress;

    @FXML
    private TableColumn<?, ?> supColAddress;

    @FXML
    private TableColumn<?, ?> supColContact;

    @FXML
    private TableColumn<?, ?> supColEmail;

    @FXML
    private TableColumn<?, ?> supColId;

    @FXML
    private TableColumn<?, ?> supColName;

    @FXML
    private TextField supContact;

    @FXML
    private TextField supEmail;

    @FXML
    private TextField supId;

    @FXML
    private TextField supName;

    @FXML
    private TextField supSearch;

    @FXML
    private TableView<SupplierTM> tblSupplier;

    @FXML
    private Button updateBtn;

    @FXML
    void clickOnActionDelete(ActionEvent event) {
        String id = supId.getText();

        boolean result = AlertController.okconfirmmessage("Are you sure you want to remove this Supplier?");
        if(result==true) {
            try {
                boolean isDeleted = SupplierModel.delete(id);
                if (isDeleted) {
                    AlertController.confirmmessage("Supplier Deleted Successfully");
                    supId.setText("");
                    supName.setText("");
                    supAddress.setText("");
                    supEmail.setText("");
                    supContact.setText("");

                    getAll();
                }
            } catch (SQLException e) {
                System.out.println(e);
                AlertController.errormessage("something went wrong!");
            }
        }
    }

    @FXML
    void clickOnActionSave(ActionEvent event) {
        String id = supId.getText();
        String name = supName.getText();
        String address = supAddress.getText();
        String email = supEmail.getText();
        String contact = supContact.getText();

        Supplier supplier = new Supplier(id, name, address, email, contact);

        try {
            boolean isSaved = SupplierModel.save(supplier);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item saved!").show();
                supId.setText("");
                supName.setText("");
                supAddress.setText("");
                supEmail.setText("");
                supContact.setText("");
                getAll();
            }
        } catch (SQLException e) {
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
        }
    }

    @FXML
    void clickOnActionUpdate(ActionEvent event) {
        String id = supId.getText();
        String name = supName.getText();
        String address = supAddress.getText();
        String email = supEmail.getText();
        String contact = supContact.getText();

        boolean result = AlertController.okconfirmmessage("Are you sure you want to update this Suppliers' details?");
        if(result==true) {

            Supplier supplier = new Supplier(id, name, address, email, contact);
            try {
                boolean isUpdated = SupplierModel.update(supplier);
                if (isUpdated) {
                    AlertController.confirmmessage("Supplier Details Updated");
                    supId.setText("");
                    supName.setText("");
                    supAddress.setText("");
                    supEmail.setText("");
                    supContact.setText("");

                    getAll();
                }
            } catch (SQLException e) {
                System.out.println(e);
                AlertController.errormessage("something went wrong!");
            }
        }
    }

    private void getAll(){
        ObservableList<SupplierTM> obList = null;
        try {
            obList = SupplierModel.getAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        tblSupplier.setItems(obList);
    }

    public void supSearchOnAction(ActionEvent actionEvent) throws SQLException {
        String id = supSearch.getText();

        supId.setText("");
        supName.setText("");
        supAddress.setText("");
        supEmail.setText("");
        supContact.setText("");


        Supplier supplier = SupplierModel.findById(id);
        if(supplier!=null) {
            supId.setText(supplier.getId());
            supName.setText(supplier.getName());
            supAddress.setText(supplier.getAddress());
            supEmail.setText(supplier.getEmail());
            supContact.setText(supplier.getContact());

            supSearch.setText("");

        }else{
            AlertController.errormessage("Supplier ID Not Found");
        }
    }

    public void searchIconOnMousePressed(MouseEvent mouseEvent) {
        supSearch.requestFocus();
        supSearch.selectAll();
        supSearch.fireEvent(new ActionEvent());
    }

    @FXML
    void txtSearchKeyTyped(KeyEvent event) throws SQLException {
        String searchValue = supSearch.getText().trim();
        ObservableList<SupplierTM> obList = SupplierModel.getAll();

        if (!searchValue.isEmpty()) {
            ObservableList<SupplierTM> filteredData = obList.filtered(new Predicate<SupplierTM>(){
                @Override
                public boolean test(SupplierTM suppliertm) {
                    return String.valueOf(suppliertm.getId()).toLowerCase().contains(searchValue.toLowerCase());
                }
            });
            tblSupplier.setItems(filteredData);
        } else {
            tblSupplier.setItems(obList);
        }
    }

    private void setCellValueFactory() {
        supColId.setCellValueFactory(new PropertyValueFactory<>("id"));
        supColName.setCellValueFactory(new PropertyValueFactory<>("name"));
        supColAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        supColEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        supColContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
    }

//    public static ObservableList<SupplierTM> getAll() throws SQLException {
//        String sql = "SELECT * FROM Supplier";
//        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
//
//            ObservableList<SupplierTM> obList = FXCollections.observableArrayList();
//
//            ResultSet resultSet = pstm.executeQuery(sql);
//            while (resultSet.next()) {
//                obList.add(new SupplierTM(
//                        resultSet.getString(1),
//                        resultSet.getString(2),
//                        resultSet.getString(3),
//                        resultSet.getString(4),
//                        resultSet.getString(5)
//                ));
//            }
//            return obList;
//        }
//    }

    @FXML
    void initialize() {
        setCellValueFactory();
        getAll();
        assert deleteBtn != null : "fx:id=\"deleteBtn\" was not injected: check your FXML file 'supplier_form.fxml'.";
        assert saveBtn != null : "fx:id=\"saveBtn\" was not injected: check your FXML file 'supplier_form.fxml'.";
        assert searchIcon != null : "fx:id=\"searchIcon\" was not injected: check your FXML file 'supplier_form.fxml'.";
        assert supAddress != null : "fx:id=\"supAddress\" was not injected: check your FXML file 'supplier_form.fxml'.";
        assert supColAddress != null : "fx:id=\"supColAddress\" was not injected: check your FXML file 'supplier_form.fxml'.";
        assert supColContact != null : "fx:id=\"supColContact\" was not injected: check your FXML file 'supplier_form.fxml'.";
        assert supColEmail != null : "fx:id=\"supColEmail\" was not injected: check your FXML file 'supplier_form.fxml'.";
        assert supColId != null : "fx:id=\"supColId\" was not injected: check your FXML file 'supplier_form.fxml'.";
        assert supColName != null : "fx:id=\"supColName\" was not injected: check your FXML file 'supplier_form.fxml'.";
        assert supContact != null : "fx:id=\"supContact\" was not injected: check your FXML file 'supplier_form.fxml'.";
        assert supEmail != null : "fx:id=\"supEmail\" was not injected: check your FXML file 'supplier_form.fxml'.";
        assert supId != null : "fx:id=\"supId\" was not injected: check your FXML file 'supplier_form.fxml'.";
        assert supName != null : "fx:id=\"supName\" was not injected: check your FXML file 'supplier_form.fxml'.";
        assert supSearch != null : "fx:id=\"supSearch\" was not injected: check your FXML file 'supplier_form.fxml'.";
        assert tblSupplier != null : "fx:id=\"tblSupplier\" was not injected: check your FXML file 'supplier_form.fxml'.";
        assert updateBtn != null : "fx:id=\"updateBtn\" was not injected: check your FXML file 'supplier_form.fxml'.";

    }
}
