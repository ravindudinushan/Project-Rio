package lk.ijse.project_rio.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.project_rio.dto.User;
import lk.ijse.project_rio.model.UserModel;

import javax.mail.MessagingException;

public class ForgotPasswordFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnsendotp;

    @FXML
    private PasswordField confirmpasstxt;

    @FXML
    private TextField emailtxt;

    @FXML
    private Group enteremailgroup;

    @FXML
    private AnchorPane forgotPane;

    @FXML
    private Hyperlink lblLogin;

    @FXML
    private Group newpasswordgroup;

    @FXML
    private PasswordField newpasswordtxt;

    @FXML
    private Button otpBtn;

    @FXML
    private TextField otpCode;

    @FXML
    private Group otptypegroup;

    @FXML
    private TextField usernametxt;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void changepasswordOnAction(ActionEvent event) {
        if(newpasswordtxt.getText().equals(confirmpasstxt.getText())) {
            String username = usernametxt.getText();
            String newpassword = newpasswordtxt.getText();

            try {
                boolean isUpdated = UserModel.update(username, newpassword);
                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION,"Password Changed").show();
                }
            } catch (SQLException e) {
                System.out.println(e);
                new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
            }
        }else{
            new Alert(Alert.AlertType.ERROR, "Passwords doesn't match").show();
        }
    }

    @FXML
    void clickOnActionOtpBtn(ActionEvent event) {
        if(otpCode.getText().equals(Integer. toString(randomnum))) {
            otptypegroup.setVisible(false);
            newpasswordgroup.setVisible(true);
        }else{
            new Alert(Alert.AlertType.ERROR,"Wrong OTP").show();
        }
    }

    Random rand = new Random();
    int randomnum;
    @FXML
    void clickOnActionSendOTP(ActionEvent event) throws SQLException {
        User user = UserModel.findbyusername(usernametxt.getText());

        System.out.println(usernametxt.getText());
        System.out.println(user.getName());
        System.out.println(user.getEmail());
        if (emailtxt.getText().equals(user.getEmail())) {

            String email = emailtxt.getText();
            enteremailgroup.setVisible(false);
            otptypegroup.setVisible(true);

            randomnum = rand.nextInt(9000);
            randomnum += 1000;

            try {
                EmailController.sendEmail(email, "Test Email", randomnum + "");
                System.out.println("Email sent successfully.");
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }else{
            new Alert(Alert.AlertType.ERROR,"Invalid Email Address").show();
        }
    }

    @FXML
    void onActionLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/lk.ijse.project_rio.view/login_page.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

    }

    @FXML
    void initialize() {
        assert btnsendotp != null : "fx:id=\"btnsendotp\" was not injected: check your FXML file 'forgot_password_form.fxml'.";
        assert confirmpasstxt != null : "fx:id=\"confirmpasstxt\" was not injected: check your FXML file 'forgot_password_form.fxml'.";
        assert emailtxt != null : "fx:id=\"emailtxt\" was not injected: check your FXML file 'forgot_password_form.fxml'.";
        assert enteremailgroup != null : "fx:id=\"enteremailgroup\" was not injected: check your FXML file 'forgot_password_form.fxml'.";
        assert forgotPane != null : "fx:id=\"forgotPane\" was not injected: check your FXML file 'forgot_password_form.fxml'.";
        assert lblLogin != null : "fx:id=\"lblLogin\" was not injected: check your FXML file 'forgot_password_form.fxml'.";
        assert newpasswordgroup != null : "fx:id=\"newpasswordgroup\" was not injected: check your FXML file 'forgot_password_form.fxml'.";
        assert newpasswordtxt != null : "fx:id=\"newpasswordtxt\" was not injected: check your FXML file 'forgot_password_form.fxml'.";
        assert otpBtn != null : "fx:id=\"otpBtn\" was not injected: check your FXML file 'forgot_password_form.fxml'.";
        assert otpCode != null : "fx:id=\"otpCode\" was not injected: check your FXML file 'forgot_password_form.fxml'.";
        assert otptypegroup != null : "fx:id=\"otptypegroup\" was not injected: check your FXML file 'forgot_password_form.fxml'.";
        assert usernametxt != null : "fx:id=\"usernametxt\" was not injected: check your FXML file 'forgot_password_form.fxml'.";

    }

}
