package lk.ijse.project_rio.util;

import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class BtnColorController {
    public static void btncolor(Button btn, AnchorPane anchorPane){
        btn.setStyle("-fx-background-color: #fa9393;");
        anchorPane.getChildren().addListener((ListChangeListener<Node>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    for (Node node : change.getAddedSubList()) {
                        if (node instanceof AnchorPane) {
                            // Check if the new node is an AnchorPane
                            AnchorPane newAnchorPane = (AnchorPane) node;
                            if (newAnchorPane.getId().equals("adminChangingPane")) {
                                btn.setStyle("-fx-background-color: brown;");
                            } else {
                                btn.setStyle("-fx-background-color: #fa9393;");
                            }
                        }
                    }
                }
            }
        });

        /////////
        /////////////
    }

    public static void cashbtncolor(Button btn,AnchorPane anchorPane){
        btn.setStyle("-fx-background-color: #fa9393;");
        anchorPane.getChildren().addListener((ListChangeListener<Node>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    for (Node node : change.getAddedSubList()) {
                        if (node instanceof AnchorPane) {
                            // Check if the new node is an AnchorPane
                            AnchorPane newAnchorPane = (AnchorPane) node;
                            if (newAnchorPane.getId().equals("cashierChangingPane")) {
                                btn.setStyle("-fx-background-color: brown;");
                            } else {
                                btn.setStyle("-fx-background-color: #fa9393;");
                            }
                        }
                    }
                }
            }
        });
    }
}
