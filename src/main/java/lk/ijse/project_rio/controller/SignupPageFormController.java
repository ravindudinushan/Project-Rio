package lk.ijse.project_rio.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import lk.ijse.project_rio.dto.User;
import lk.ijse.project_rio.model.UserModel;
import lk.ijse.project_rio.util.AlertController;
import lk.ijse.project_rio.util.ValidateField;

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
    private PasswordField signTxt2;

    @FXML
    private PasswordField signTxt3;

    @FXML
    private TextField signTxt4;

    @FXML
    private TextField signTxt5;

    @FXML
    private Label passlabel;

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

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

    public void OnActionLogin(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/lk.ijse.project_rio.view/login_page.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void signTxt2OnKeyTyped(KeyEvent keyEvent) {
//        String password = signTxt2.getText();
//
//        // Check if the password is valid
//        List<String> messages = ValidateField.getValidationMessages(password);
//        if (messages.isEmpty()) {
//            passlabel.setStyle("-fx-text-fill: #27cb27; -fx-background-color: black; -fx-background-radius: 10; -fx-font-family: Offside; -fx-padding: 10");
//            passlabel.setText("strong valid password");
//            createAnAccBtn.setDisable(false);
//        } else {
//            String message = String.join(", ", messages);
//            passlabel.setStyle("-fx-text-fill: red; -fx-background-color: black; -fx-background-radius: 10; -fx-font-family: Offside; -fx-padding: 10");
//            passlabel.setText(message);
//            createAnAccBtn.setDisable(true);
//        }
//        passlabel.setVisible(true);
    }
}
