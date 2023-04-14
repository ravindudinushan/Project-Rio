package lk.ijse.project_rio.controller;

import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import jfxtras.scene.control.LocalTimeTextField;
import lk.ijse.project_rio.dto.Event;
import lk.ijse.project_rio.dto.tm.EventTM;
import lk.ijse.project_rio.model.EmployeeModel;
import lk.ijse.project_rio.model.EventModel;
import lk.ijse.project_rio.util.AlertController;
import lk.ijse.project_rio.util.ValidateField;

public class EventFormController {

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
    private ComboBox<String> comEmpId;

    @FXML
    private TableColumn<?, ?> empIdCol;

    @FXML
    private TableColumn<?, ?> eventDateCol;

    @FXML
    private TableColumn<?, ?> eventIdCol;

    @FXML
    private TableColumn<?, ?> eventNameCol;

    @FXML
    private TableColumn<?, ?> eventTimeCol;

    @FXML
    private TableColumn<?, ?> eventTypeCol;

    @FXML
    private ImageView searchIcon;

    @FXML
    private TableView<EventTM> tblEvent;

    @FXML
    private DatePicker txtEventDate;

    @FXML
    private TextField txtEventId;

    @FXML
    private TextField txtEventName;

    @FXML
    private LocalTimeTextField txtEventTime;

    @FXML
    private TextField txtEventType;

    @FXML
    private TextField txtSearch;

    @FXML
    private AnchorPane cashierChangingPane;

    @FXML
    private AnchorPane adminChangingPane;

    @FXML
    private Label lblinvalideventid;

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtEventId.getText();

        boolean result = AlertController.okconfirmmessage("Are you sure you want to remove this Event?");
        if(result==true) {
            try {
                boolean isDeleted = EventModel.delete(id);
                if (isDeleted) {
                    AlertController.confirmmessage("Event Deleted Successfully");
                    txtEventId.setText("");
                    txtEventName.setText("");
                    txtEventDate.setValue(null);
                    txtEventTime.setLocalTime(null);
                    txtEventType.setText("");
                    comEmpId.setValue(null);
                    getAll();
                }
            } catch (SQLException e) {
                System.out.println(e);
                AlertController.errormessage("something went wrong!");
            }
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent actionEvent) {
        String id = txtEventId.getText();
        String name = txtEventName.getText();
        String date = String.valueOf(txtEventDate.getValue());
        String time = String.valueOf(txtEventTime.getLocalTime());
        String type = txtEventType.getText();
        String empId = comEmpId.getValue();

//        Event event = new Event(id,name,date,time,type,empId);
//        try {
//            boolean isSaved = EventModel.save(event);
//            if (isSaved) {
//                new Alert(Alert.AlertType.CONFIRMATION, "Event saved!").show();
//                txtEventId.setText("");
//                txtEventName.setText("");
//                txtEventDate.setValue(null);
//                txtEventTime.setLocalTime(null);
//                txtEventType.setText("");
//                comEmpId.setValue(null);
//                getAll();
//            }
//        } catch (SQLException e) {
//            System.out.println(e);
//            new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
//        }
        if(id.isEmpty() || name.isEmpty() || date.isEmpty() || time.isEmpty() || type.isEmpty() || empId.isEmpty()){
            AlertController.errormessage("Event not saved successfully.\nPlease make sure to fill out all the required fields.");
        }else{
            if(ValidateField.eventIdCheck(id)) {
                                        Event event = new Event(id,name,date,time,type,empId);

                                        try {
                                            boolean isSaved = EventModel.save(event);
                                            if (isSaved) {
                                                AlertController.confirmmessage("New Event added successfully");
                                                txtEventId.setText(null);
                                                txtEventName.setText(null);
                                                txtEventDate.setValue(null);
                                                txtEventTime.setLocalTime(null);
                                                txtEventType.setText(null);
                                                comEmpId.setValue(null);
                                                getAll();
                                            }
                                        }catch(SQLIntegrityConstraintViolationException e){
                                            AlertController.errormessage("This Event ID already exists.");
                                        } catch (SQLException e) {
                                            System.out.println(e);
                                            new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
                                        }
            }else{
                lblinvalideventid.setVisible(true);
                lblinvalideventid.setStyle("-fx-text-fill: red");
            }
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent actionEvent) {
        String id = txtEventId.getText();
        String name = txtEventName.getText();
        String date = String.valueOf(txtEventDate.getValue());
        String time = String.valueOf(txtEventTime.getLocalTime());
        String type = txtEventType.getText();
        String empId = comEmpId.getValue();

        boolean result = AlertController.okconfirmmessage("Are you sure you want to update this event details?");
        if (result == true) {

            if (id.isEmpty() || name.isEmpty() || date.isEmpty() || time.isEmpty() || type.isEmpty() || empId.isEmpty()) {
                AlertController.errormessage("Event not saved successfully.\nPlease make sure to fill out all the required fields.");
            } else {
                if (ValidateField.eventIdCheck(id)) {
                    Event event = new Event(id, name, date, time, type, empId);

                    try {
                        boolean isUpdated = EventModel.update(event);
                        if (isUpdated) {
                            AlertController.confirmmessage("New Event added successfully");
                            txtEventId.setText(null);
                            txtEventName.setText(null);
                            txtEventDate.setValue(null);
                            txtEventTime.setLocalTime(null);
                            txtEventType.setText(null);
                            comEmpId.setValue(null);
                            getAll();
                        }
                    } catch (SQLException e) {
                        System.out.println(e);
                        new Alert(Alert.AlertType.ERROR, "something went wrong!");
                    }
                } else {
                    lblinvalideventid.setVisible(true);
                    lblinvalideventid.setStyle("-fx-text-fill: red");
                }
            }
        }
    }

    private void getAll(){
        ObservableList<EventTM> obList = null;
        try {
            obList = EventModel.getAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        tblEvent.setItems(obList);
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

    @FXML
    void searchIconOnMousePressed(MouseEvent event) {
        txtSearch.requestFocus();
        txtSearch.selectAll();
        txtSearch.fireEvent(new ActionEvent());
    }

    @FXML
    void txtSearchKeyTyped(KeyEvent event) throws SQLException {
        String searchValue = txtSearch.getText().trim();
        ObservableList<EventTM> obList = EventModel.getAll();

        if (!searchValue.isEmpty()) {
            ObservableList<EventTM> filteredData = obList.filtered(new Predicate<EventTM>(){
                @Override
                public boolean test(EventTM eventtm) {
                    return String.valueOf(eventtm.getId()).toLowerCase().contains(searchValue.toLowerCase());
                }
            });
            tblEvent.setItems(filteredData);
        } else {
            tblEvent.setItems(obList);
        }
    }

    @FXML
    void txtSearchOnAction(ActionEvent actionEvent) {
        String id = txtSearch.getText();

        txtEventId.setText("");
        txtEventName.setText("");
        txtEventDate.setValue(null);
        txtEventTime.setLocalTime(null);
        txtEventType.setText("");
        comEmpId.setValue(null);

        try {
            Event event = EventModel.findById(id);
            if(event!=null) {
                txtEventId.setText(event.getId());
                txtEventName.setText(event.getName());
                txtEventDate.setValue(LocalDate.parse(event.getDate()));
                txtEventTime.setLocalTime(LocalTime.parse(event.getTime()));
                txtEventType.setText(event.getType());
                comEmpId.setValue(event.getEmpId());

                txtSearch.setText("");

            }else{
                AlertController.errormessage("Event ID Not Found");
            }
        } catch (Exception e) {
            System.out.println(e);
            AlertController.errormessage("Something Went Wrong");
        }
    }

    private void setCellValueFactory() {
        empIdCol.setCellValueFactory(new PropertyValueFactory<>("empId"));
        eventIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        eventNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        eventDateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        eventTimeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        eventTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
    }

    @FXML
    void initialize() {
        setCellValueFactory();
        getAll();
        loadEmployeeIds();
        lblinvalideventid.setVisible(false);
        assert btnDelete != null : "fx:id=\"btnDelete\" was not injected: check your FXML file 'event_form.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'event_form.fxml'.";
        assert btnUpdate != null : "fx:id=\"btnUpdate\" was not injected: check your FXML file 'event_form.fxml'.";
        assert comEmpId != null : "fx:id=\"comEmpId\" was not injected: check your FXML file 'event_form.fxml'.";
        assert empIdCol != null : "fx:id=\"empIdCol\" was not injected: check your FXML file 'event_form.fxml'.";
        assert eventDateCol != null : "fx:id=\"eventDateCol\" was not injected: check your FXML file 'event_form.fxml'.";
        assert eventIdCol != null : "fx:id=\"eventIdCol\" was not injected: check your FXML file 'event_form.fxml'.";
        assert eventNameCol != null : "fx:id=\"eventNameCol\" was not injected: check your FXML file 'event_form.fxml'.";
        assert eventTimeCol != null : "fx:id=\"eventTimeCol\" was not injected: check your FXML file 'event_form.fxml'.";
        assert eventTypeCol != null : "fx:id=\"eventTypeCol\" was not injected: check your FXML file 'event_form.fxml'.";
        assert searchIcon != null : "fx:id=\"searchIcon\" was not injected: check your FXML file 'event_form.fxml'.";
        assert tblEvent != null : "fx:id=\"tblEvent\" was not injected: check your FXML file 'event_form.fxml'.";
        assert txtEventDate != null : "fx:id=\"txtEventDate\" was not injected: check your FXML file 'event_form.fxml'.";
        assert txtEventId != null : "fx:id=\"txtEventId\" was not injected: check your FXML file 'event_form.fxml'.";
        assert txtEventName != null : "fx:id=\"txtEventName\" was not injected: check your FXML file 'event_form.fxml'.";
        assert txtEventTime != null : "fx:id=\"txtEventTime\" was not injected: check your FXML file 'event_form.fxml'.";
        assert txtEventType != null : "fx:id=\"txtEventType\" was not injected: check your FXML file 'event_form.fxml'.";
        assert txtSearch != null : "fx:id=\"txtSearch\" was not injected: check your FXML file 'event_form.fxml'.";

    }

    public void txtEventIdOnMousePressed(MouseEvent mouseEvent) {
       lblinvalideventid.setVisible(false);
    }
}
