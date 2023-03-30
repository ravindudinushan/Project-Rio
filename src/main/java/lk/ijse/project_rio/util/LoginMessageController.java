package lk.ijse.project_rio.util;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

public class LoginMessageController {
    public static void loginsuccessfulmsg(){
        Image img = new Image("lk.ijse.project_rio.assets/righticon.png", 96, 96, true, true);

        Notifications notificationBuilder = Notifications.create()
                .title("Login ")
                .text("  Login Successful")
                .graphic(new ImageView(img))
                .hideAfter(Duration.seconds(3));
        notificationBuilder.darkStyle();
        notificationBuilder.show();
    }

    public static void loginunsuccessfulmsg(){
        Image img = new Image("lk.ijse.project_rio.assets/wrongicon.png", 96, 96, true, true);

        Notifications notificationBuilder = Notifications.create()
                .title("Login ")
                .text("  Login Unsuccessful")
                .graphic(new ImageView(img))
                .hideAfter(Duration.seconds(3));
        notificationBuilder.darkStyle();
        notificationBuilder.show();
    }
}
