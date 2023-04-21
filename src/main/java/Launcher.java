import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Launcher extends Application {
    public static void main(String[] args) { launch(); }
    @Override
    public void start(Stage stage) throws Exception {
        URL resource = Launcher.class.getResource("lk.ijse.project_rio.view/dashboard_cashier_form.fxml");
        Parent load = FXMLLoader.load(resource);

        stage.setScene(new Scene(load));
        stage.setTitle("Rio Ice Cream");
        stage.centerOnScreen();
        stage.show();
    }
}
