package lk.ijse.project_rio.controller;

import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.layout.AnchorPane;
import lk.ijse.project_rio.dto.Employee;
import lk.ijse.project_rio.dto.tm.EmployeeTM;
import lk.ijse.project_rio.model.EmployeeModel;
import lk.ijse.project_rio.util.AlertController;
import lk.ijse.project_rio.util.ValidateField;

public class EmployeeFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

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
    private ImageView searchIcon;

    @FXML
    private TextField txtSearch;

    @FXML
    private TextField txtempaddress;

    @FXML
    private TextField txtempcontact;

    @FXML
    private DatePicker txtempdob;

    @FXML
    private TextField txtempemail;

    @FXML
    private TextField txtempid;

    @FXML
    private TextField txtempjob;

    @FXML
    private TextField txtempname;

    @FXML
    private TextField txtempnic;

    @FXML
    private TextField txtempsalary;

    @FXML
    private TableView<EmployeeTM> tblEmployee;

    @FXML
    private AnchorPane adminChangingPane;

    @FXML
    private Label lblinvalidcontact;

    @FXML
    private Label lblinvalidemail;

    @FXML
    private Label lblinvalidemployeeid;

    @FXML
    private Label lblinvalidnic;

    @FXML
    private Label lblinvalidsalary;


    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtempid.getText();

        boolean result = AlertController.okconfirmmessage("Are you sure you want to remove this employee?");
        if(result==true) {
            try {
                boolean isDeleted = EmployeeModel.delete(id);
                if (isDeleted) {
                    AlertController.confirmmessage("Employee Deleted Successfully");
                    txtempid.setText("");
                    txtempname.setText("");
                    txtempdob.setValue(null);
                    txtempaddress.setText("");
                    txtempcontact.setText("");
                    txtempemail.setText("");
                    txtempjob.setText("");
                    txtempnic.setText("");
                    txtempsalary.setText("");
                    getAll();
                }
            } catch (SQLException e) {
                System.out.println(e);
                AlertController.errormessage("something went wrong!");
            }
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtempid.getText();
        String name = txtempname.getText();
        String nic = txtempnic.getText();
        String dob = String.valueOf(txtempdob.getValue());
        String job = txtempjob.getText();
        String contact = txtempcontact.getText();
        String address = txtempaddress.getText();
        String email = txtempemail.getText();

//        Employee employee= new Employee(id,name,nic,dob,address,email,job,contact,salary);
//        try {
//            boolean isSaved = EmployeeModel.save(employee);
//            if (isSaved) {
//                new Alert(Alert.AlertType.CONFIRMATION, "Item saved!").show();
//                txtempid.setText("");
//                txtempname.setText("");
//                txtempdob.setValue(null);
//                txtempaddress.setText("");
//                txtempcontact.setText("");
//                txtempemail.setText("");
//                txtempjob.setText("");
//                txtempnic.setText("");
//                txtempsalary.setText("");
//                getAll();
//            }
//        } catch (SQLException e) {
//            System.out.println(e);
//            new Alert(Alert.AlertType.ERROR, "something went wrong!").show();

            if(id.isEmpty() || name.isEmpty() || nic.isEmpty() || dob.isEmpty() || job.isEmpty() || contact.isEmpty() || address.isEmpty()){
                AlertController.errormessage("Employee not saved successfully.\nPlease make sure to fill out all the required fields.");
            }else{
                if(ValidateField.employeeIdCheck(id)) {
                    if (ValidateField.nicCheck(nic) || ValidateField.emailCheck(email) || ValidateField.contactCheck(contact)) {
                        if (ValidateField.nicCheck(nic) || ValidateField.emailCheck(email)) {
                            if (ValidateField.nicCheck(nic) || ValidateField.contactCheck(contact)) {
                                if (ValidateField.emailCheck(email) || ValidateField.contactCheck(contact)) {
                                    if (ValidateField.contactCheck(contact)) {
                                        if (ValidateField.emailCheck(email)) {
                                            if (ValidateField.nicCheck(nic)) {
                                                if (ValidateField.priceCheck(txtempsalary.getText())) {
                                                    Double salary = Double.valueOf(txtempsalary.getText());

                                                    Employee employee = new Employee(id,name,nic,dob,address,email,job,contact,salary);

                                                    try {
                                                        boolean isSaved = EmployeeModel.save(employee);
                                                        if (isSaved) {
                                                            AlertController.confirmmessage("New employee added successfully");
                                                            txtempid.setText(null);
                                                            txtempname.setText(null);
                                                            txtempdob.setValue(null);
                                                            txtempaddress.setText(null);
                                                            txtempcontact.setText(null);
                                                            txtempemail.setText(null);
                                                            txtempjob.setText(null);
                                                            txtempnic.setText(null);
                                                            getAll();
                                                        }
                                                    }catch(SQLIntegrityConstraintViolationException e){
                                                        AlertController.errormessage("This Employee ID already exists.");
                                                    } catch (SQLException e) {
                                                        System.out.println(e);
                                                        new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
                                                    }
                                                } else {
                                                    lblinvalidsalary.setVisible(true);
                                                }
                                            } else {
                                                lblinvalidnic.setVisible(true);
                                            }
                                        } else {
                                            lblinvalidemail.setVisible(true);
                                        }
                                    } else {
                                        lblinvalidcontact.setVisible(true);
                                    }
                                } else {
                                    lblinvalidemail.setVisible(true);
                                    lblinvalidcontact.setVisible(true);
                                }
                            } else {
                                lblinvalidnic.setVisible(true);
                                lblinvalidcontact.setVisible(true);
                            }
                        } else {
                            lblinvalidemail.setVisible(true);
                            lblinvalidnic.setVisible(true);
                        }
                    } else {
                        lblinvalidemail.setVisible(true);
                        lblinvalidcontact.setVisible(true);
                        lblinvalidnic.setVisible(true);
                    }
                }else{
                    lblinvalidemployeeid.setVisible(true);
                    lblinvalidemployeeid.setStyle("-fx-text-fill: red");
                }
            }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtempid.getText();
        String name = txtempname.getText();
        String nic = txtempnic.getText();
        String dob = String.valueOf(txtempdob.getValue());
        String job = txtempjob.getText();
        String contact = txtempcontact.getText();
        String address = txtempaddress.getText();
        String email = txtempemail.getText();

        boolean result = AlertController.okconfirmmessage("Are you sure you want to update this employees' details?");
        if (result == true) {
            if (id.isEmpty() || name.isEmpty() || nic.isEmpty() || dob.isEmpty() || job.isEmpty() || contact.isEmpty() || address.isEmpty()) {
                AlertController.errormessage("Employee not saved successfully.\nPlease make sure to fill out all the required fields.");
            } else {
                if (ValidateField.employeeIdCheck(id)) {
                    if (ValidateField.nicCheck(nic) || ValidateField.emailCheck(email) || ValidateField.contactCheck(contact)) {
                        if (ValidateField.nicCheck(nic) || ValidateField.emailCheck(email)) {
                            if (ValidateField.nicCheck(nic) || ValidateField.contactCheck(contact)) {
                                if (ValidateField.emailCheck(email) || ValidateField.contactCheck(contact)) {
                                    if (ValidateField.contactCheck(contact)) {
                                        if (ValidateField.emailCheck(email)) {
                                            if (ValidateField.nicCheck(nic)) {
                                                if (ValidateField.priceCheck(txtempsalary.getText())) {
                                                    Double salary = Double.valueOf(txtempsalary.getText());

                                                    Employee employee = new Employee(id, name, nic, dob, address, email, job, contact, salary);

                                                    try {
                                                        boolean isSaved = EmployeeModel.save(employee);
                                                        if (isSaved) {
                                                            AlertController.confirmmessage("New employee added successfully");
                                                            txtempid.setText(null);
                                                            txtempname.setText(null);
                                                            txtempdob.setValue(null);
                                                            txtempaddress.setText(null);
                                                            txtempcontact.setText(null);
                                                            txtempemail.setText(null);
                                                            txtempjob.setText(null);
                                                            txtempnic.setText(null);
                                                            getAll();
                                                        }

                                                    } catch (SQLIntegrityConstraintViolationException e) {
                                                        AlertController.errormessage("This Employee ID already exists.");
                                                    } catch (SQLException e) {
                                                        System.out.println(e);
                                                        new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
                                                    }
                                                } else {
                                                    lblinvalidnic.setVisible(true);
                                                }
                                            } else {
                                                lblinvalidemail.setVisible(true);
                                            }
                                        } else {
                                            lblinvalidcontact.setVisible(true);
                                        }
                                    } else {
                                        lblinvalidemail.setVisible(true);
                                        lblinvalidcontact.setVisible(true);
                                    }
                                } else {
                                    lblinvalidnic.setVisible(true);
                                    lblinvalidcontact.setVisible(true);
                                }
                            } else {
                                lblinvalidemail.setVisible(true);
                                lblinvalidnic.setVisible(true);
                            }
                        } else {
                            lblinvalidemail.setVisible(true);
                            lblinvalidcontact.setVisible(true);
                            lblinvalidnic.setVisible(true);
                        }
                    } else {
                        lblinvalidemployeeid.setVisible(true);
                        lblinvalidemployeeid.setStyle("-fx-text-fill: red");
                    }
                }
            }
        }
    }

    @FXML
    void searchIconOnMousePressed(MouseEvent event) {
        txtSearch.requestFocus();
        txtSearch.selectAll();
        txtSearch.fireEvent(new ActionEvent());

    }

    @FXML
    void txtSearchKeyTyped(KeyEvent event) throws SQLException {
        String searchValue = txtSearch.getText().trim();
        ObservableList<EmployeeTM> obList = EmployeeModel.getAll();

        if (!searchValue.isEmpty()) {
            ObservableList<EmployeeTM> filteredData = obList.filtered(new Predicate<EmployeeTM>(){
                @Override
                public boolean test(EmployeeTM employeetm) {
                    return String.valueOf(employeetm.getId()).toLowerCase().contains(searchValue.toLowerCase());
                }
            });
            tblEmployee.setItems(filteredData);
        } else {
            tblEmployee.setItems(obList);
        }
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String id = txtSearch.getText();

        txtempid.setText("");
        txtempname.setText("");
        txtempdob.setValue(null);
        txtempaddress.setText("");
        txtempcontact.setText("");
        txtempemail.setText("");
        txtempjob.setText("");
        txtempnic.setText("");
        txtempsalary.setText("");


        try {
            Employee employee = EmployeeModel.findById(id);
            if(employee!=null) {
                txtempid.setText(employee.getId());
                txtempname.setText(employee.getName());
                txtempnic.setText(employee.getNic());

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(employee.getDob(), formatter);
                txtempdob.setValue(date);

                txtempjob.setText(employee.getJob());
                txtempcontact.setText(employee.getContact());
                txtempaddress.setText(employee.getAddress());
                txtempemail.setText(employee.getEmail());
                txtempsalary.setText(String.valueOf(employee.getSalary()));

                txtSearch.setText("");

            }else{
                AlertController.errormessage("Employee ID Not Found");
            }
        } catch (SQLException e) {
            System.out.println(e);
            AlertController.errormessage("Something Went Wrong");
        }
    }

    private void setCellValueFactory() {
        empcolid.setCellValueFactory(new PropertyValueFactory<>("id"));
        empcolname.setCellValueFactory(new PropertyValueFactory<>("name"));
        empcoldob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        empcoladdress.setCellValueFactory(new PropertyValueFactory<>("address"));
        empcolcontact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        empcolemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        empcoljob.setCellValueFactory(new PropertyValueFactory<>("job"));
        empcolnic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        empcolsalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
    }

    private void getAll(){
        ObservableList<EmployeeTM> obList = null;
        try {
            obList = EmployeeModel.getAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        tblEmployee.setItems(obList);
    }

    @FXML
    void initialize() {
        setCellValueFactory();
        getAll();
        lblinvalidemail.setVisible(false);
        lblinvalidcontact.setVisible(false);
        lblinvalidnic.setVisible(false);
        lblinvalidemployeeid.setVisible(false);
        lblinvalidsalary.setVisible(false);
        assert btnDelete != null : "fx:id=\"btnDelete\" was not injected: check your FXML file 'employee_form.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'employee_form.fxml'.";
        assert btnUpdate != null : "fx:id=\"btnUpdate\" was not injected: check your FXML file 'employee_form.fxml'.";
        assert empcoladdress != null : "fx:id=\"empcoladdress\" was not injected: check your FXML file 'employee_form.fxml'.";
        assert empcolcontact != null : "fx:id=\"empcolcontact\" was not injected: check your FXML file 'employee_form.fxml'.";
        assert empcoldob != null : "fx:id=\"empcoldob\" was not injected: check your FXML file 'employee_form.fxml'.";
        assert empcolemail != null : "fx:id=\"empcolemail\" was not injected: check your FXML file 'employee_form.fxml'.";
        assert empcolid != null : "fx:id=\"empcolid\" was not injected: check your FXML file 'employee_form.fxml'.";
        assert empcoljob != null : "fx:id=\"empcoljob\" was not injected: check your FXML file 'employee_form.fxml'.";
        assert empcolname != null : "fx:id=\"empcolname\" was not injected: check your FXML file 'employee_form.fxml'.";
        assert empcolnic != null : "fx:id=\"empcolnic\" was not injected: check your FXML file 'employee_form.fxml'.";
        assert empcolsalary != null : "fx:id=\"empcolsalary\" was not injected: check your FXML file 'employee_form.fxml'.";
        assert searchIcon != null : "fx:id=\"searchIcon\" was not injected: check your FXML file 'employee_form.fxml'.";
        assert txtSearch != null : "fx:id=\"txtSearch\" was not injected: check your FXML file 'employee_form.fxml'.";
        assert txtempaddress != null : "fx:id=\"txtempaddress\" was not injected: check your FXML file 'employee_form.fxml'.";
        assert txtempcontact != null : "fx:id=\"txtempcontact\" was not injected: check your FXML file 'employee_form.fxml'.";
        assert txtempdob != null : "fx:id=\"txtempdob\" was not injected: check your FXML file 'employee_form.fxml'.";
        assert txtempemail != null : "fx:id=\"txtempemail\" was not injected: check your FXML file 'employee_form.fxml'.";
        assert txtempid != null : "fx:id=\"txtempid\" was not injected: check your FXML file 'employee_form.fxml'.";
        assert txtempjob != null : "fx:id=\"txtempjob\" was not injected: check your FXML file 'employee_form.fxml'.";
        assert txtempname != null : "fx:id=\"txtempname\" was not injected: check your FXML file 'employee_form.fxml'.";
        assert txtempnic != null : "fx:id=\"txtempnic\" was not injected: check your FXML file 'employee_form.fxml'.";
        assert txtempsalary != null : "fx:id=\"txtempsalary\" was not injected: check your FXML file 'employee_form.fxml'.";

    }

    public void txtempidOnMousePressed(MouseEvent mouseEvent) {
        lblinvalidemployeeid.setVisible(false);
    }

    public void txtempnicOnMousePressed(MouseEvent mouseEvent) {
        lblinvalidnic.setVisible(false);
    }

    public void txtempcontactOnMousePressed(MouseEvent mouseEvent) {
        lblinvalidcontact.setVisible(false);
    }

    public void txtempemailOnMousePressed(MouseEvent mouseEvent) {
        lblinvalidemail.setVisible(false);
    }

    public void txtempsalaryOnMouseClicked(MouseEvent mouseEvent) {
        lblinvalidsalary.setVisible(false);
    }
}
