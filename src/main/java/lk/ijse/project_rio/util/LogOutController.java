package lk.ijse.project_rio.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LogOutController {
    public static void logout(AnchorPane anc) throws IOException {
        anc.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader(LogOutController.class.getResource("/lk.ijse.project_rio.view/login_page.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();

        stage.setTitle("Login Page");
        stage.setScene(new Scene(root1));
        stage.getIcons().add(new Image("lk.ijse.project_rio.assets/logo.png"));
        stage.show();
    }
}
