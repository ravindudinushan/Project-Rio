package lk.ijse.project_rio.controller;

import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import lk.ijse.project_rio.db.DBConnection;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class ReportFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane adminChangingPane;

    @FXML
    private Button orderPlacementBtn;

    @FXML
    private Button supplyLoadDetailBtn;

    @FXML
    void onActionorderPlacementBtn(ActionEvent event) {

    }

    @FXML
    void onActionsupplyLoadDetailBtn(ActionEvent event) {
        InputStream resource = this.getClass().getResourceAsStream("/lk.ijse.project_rio.reports/supplyLoad.jrxml");
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        assert adminChangingPane != null : "fx:id=\"adminChangingPane\" was not injected: check your FXML file 'report_form.fxml'.";
        assert orderPlacementBtn != null : "fx:id=\"orderPlacementBtn\" was not injected: check your FXML file 'report_form.fxml'.";
        assert supplyLoadDetailBtn != null : "fx:id=\"supplyLoadDetailBtn\" was not injected: check your FXML file 'report_form.fxml'.";

    }

    public void onActionemployeeBtn(ActionEvent actionEvent) {
        InputStream resource = this.getClass().getResourceAsStream("/lk.ijse.project_rio.reports/employee.jrxml");
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
