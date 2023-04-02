package lk.ijse.project_rio.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.project_rio.dto.User;
import lk.ijse.project_rio.model.UserModel;
import lk.ijse.project_rio.util.LoginMessageController;
import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;

public class LoginPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Hyperlink forgotPassword;

    @FXML
    private Button loginBtn;

    @FXML
    private TextField loginTxt1;

    @FXML
    private PasswordField loginTxt2;

    @FXML
    private ComboBox loginComboBox;

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void clickOnActionFogotPassword(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/lk.ijse.project_rio.view/forgot_password_form.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void clickOnActionLogin(ActionEvent actionEvent) throws IOException, SQLException {
//        Parent root = FXMLLoader.load(getClass().getResource("/lk.ijse.project_rio.view/dashboard_form.fxml"));
//        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.centerOnScreen();
//        stage.show();
        String username = loginTxt1.getText();
        String password = loginTxt2.getText();
        String combo = String.valueOf(loginComboBox.getValue());

        try {
            User user = UserModel.findbyusername(username);
            if(user.getPassword().equals(password) && user.getJobTitle().equalsIgnoreCase(combo) && combo.equals("Admin")) {
                LoginMessageController.loginsuccessfulmsg();

                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
                    loginBtn.getScene().getWindow().hide();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/lk.ijse.project_rio.view/dashboard_form.fxml"));
                    Parent root1 = null;
                    try {
                        root1 = fxmlLoader.load();
                    } catch (IOException e) {
                        System.out.println(e);
                        e.printStackTrace();
                    }
                    Stage stage = new Stage();
                    stage.setTitle("DashBoard");
                    stage.setScene(new Scene(root1));
                    stage.setResizable(false);
                    stage.show();
                }));
                timeline.play();
            }else if(user.getPassword().equals(password) && user.getJobTitle().equalsIgnoreCase(combo) && combo.equals("Cashier")) {
                LoginMessageController.loginsuccessfulmsg();

                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
                    loginBtn.getScene().getWindow().hide();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/lk.ijse.project_rio.view/dashboard_cashier_form.fxml"));
                    Parent root1 = null;
                    try {
                        root1 = fxmlLoader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Stage stage = new Stage();
                    stage.setTitle("DashBoard");
                    stage.setScene(new Scene(root1));
                    stage.setResizable(false);
                    stage.show();
                }));
                timeline.play();
            }else{
                LoginMessageController.loginunsuccessfulmsg();
            }
        } catch (SQLException e) {
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
        }
    }

    @FXML
    void initialize() {
        assert loginComboBox != null : "fx:id=\"loginComboBox\" was not injected: check your FXML file 'login_page.fxml'.";
        assert forgotPassword != null : "fx:id=\"forgotPassword\" was not injected: check your FXML file 'login_page.fxml'.";
        assert loginBtn != null : "fx:id=\"loginBtn\" was not injected: check your FXML file 'login_page.fxml'.";
        assert loginTxt1 != null : "fx:id=\"loginTxt1\" was not injected: check your FXML file 'login_page.fxml'.";
        assert loginTxt2 != null : "fx:id=\"loginTxt2\" was not injected: check your FXML file 'login_page.fxml'.";

        loginComboBox.getItems().addAll("Admin", "Cashier");
    }

    public void clickOnActionSignUp(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/lk.ijse.project_rio.view/signup_page_form.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}
