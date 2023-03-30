package lk.ijse.project_rio.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import lk.ijse.project_rio.dto.User;
import lk.ijse.project_rio.model.UserModel;
import lk.ijse.project_rio.util.AlertController;

public class SignupPageFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button createAnAccBtn;

    @FXML
    private Hyperlink loginLbl;

    @FXML
    private Rectangle loginRec;

    @FXML
    private TextField signTxt1;

    @FXML
    private TextField signTxt2;

    @FXML
    private TextField signTxt3;

    @FXML
    private TextField signTxt4;

    @FXML
    private TextField signTxt5;

    @FXML
    void clickOnActionLogin(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert createAnAccBtn != null : "fx:id=\"createAnAccBtn\" was not injected: check your FXML file 'signup_page_form.fxml'.";
        assert loginLbl != null : "fx:id=\"loginLbl\" was not injected: check your FXML file 'signup_page_form.fxml'.";
        assert loginRec != null : "fx:id=\"loginRec\" was not injected: check your FXML file 'signup_page_form.fxml'.";
        assert signTxt1 != null : "fx:id=\"signTxt1\" was not injected: check your FXML file 'signup_page_form.fxml'.";
        assert signTxt2 != null : "fx:id=\"signTxt2\" was not injected: check your FXML file 'signup_page_form.fxml'.";
        assert signTxt3 != null : "fx:id=\"signTxt3\" was not injected: check your FXML file 'signup_page_form.fxml'.";
        assert signTxt4 != null : "fx:id=\"signTxt4\" was not injected: check your FXML file 'signup_page_form.fxml'.";

    }

    public void clickOnActionCreateAccount(ActionEvent actionEvent) throws SQLException {
       if(signTxt2.getText().equals(signTxt3.getText())){
           String name = signTxt1.getText();
           String password = signTxt2.getText();
           String email = signTxt4.getText();
           String jobTitle = signTxt5.getText();

           User user = new User(name,password,email,jobTitle);
           boolean isSaved = UserModel.save(user);
           if (isSaved) {
               AlertController.confirmmessage("Account Created");
           }else{
               AlertController.errormessage("something went wrong");
           }
       }else{
           AlertController.errormessage("passwords doesn't match");
       }
    }
}