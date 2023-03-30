package lk.ijse.project_rio.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import javax.mail.MessagingException;

public class ForgotPasswordFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button sendBtn;

    @FXML
    private TextField emailTxt;

    @FXML
    private TextField otpCode;

    @FXML
    private Button otpBtn;

    @FXML
    private AnchorPane forgotPane;

    Random rand = new Random();
    int randNum;
    String email;

    @FXML
    void clickOnAction(ActionEvent event) {
        randNum=rand.nextInt(9000);
        randNum+=1000;
        email=emailTxt.getText();
        try {
            EmailController.sendEmail(email, "Test Email", randNum+"");
            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    String otp;
    @FXML
    void clickOnActionOtpBtn(ActionEvent event) throws IOException {
    otp=otpCode.getText();
        if(otp.equals(String.valueOf(randNum))){
            Parent load = FXMLLoader.load(getClass().getResource("/lk.ijse.project_rio.view/change_password_form.fxml"));
            forgotPane.getChildren().clear();
            forgotPane.getChildren().add(load);
        }
    }

    @FXML
    void initialize() {
        assert sendBtn != null : "fx:id=\"sendBtn\" was not injected: check your FXML file 'forgot_password_form.fxml'.";

    }
}
