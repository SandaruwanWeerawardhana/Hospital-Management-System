package edu.icet.hospital_system.controller.Patient;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PatientDashboard implements Initializable {

    public AnchorPane DashboardLoadAnchorpanePatient;
    public AnchorPane AnchorpaneMainPatient;
    public Label lblPatientName;

    @FXML
    private JFXButton Btnback;

    @FXML
    private JFXButton btnAappointment;

    @FXML
    void btnAappointmentAction(ActionEvent event) throws IOException {
        DashboardLoadAnchorpanePatient.getChildren().clear();
        Object load = FXMLLoader.load(getClass().getResource("/View/Patient_Appointment.fxml"));
        DashboardLoadAnchorpanePatient.getChildren().add((Node) load);
    }

    @FXML
    void btnPaymentAction(ActionEvent event) throws IOException {
        DashboardLoadAnchorpanePatient.getChildren().clear();
        Object load = FXMLLoader.load(getClass().getResource("/View/Patient_Payment.fxml"));
        DashboardLoadAnchorpanePatient.getChildren().add((Node) load);
    }

    @FXML
    void btnProfileAction(ActionEvent event) throws IOException {
        DashboardLoadAnchorpanePatient.getChildren().clear();
        Object load = FXMLLoader.load(getClass().getResource("/View/Patient_Profile.fxml"));
        DashboardLoadAnchorpanePatient.getChildren().add((Node) load);
    }

    @FXML
    void btnViewAction(ActionEvent event) throws IOException {
        loadDashboard();
    }

    @FXML
    public void btnBackAction(ActionEvent actionEvent) throws IOException {
        AnchorpaneMainPatient.getChildren().clear();
        Object load = FXMLLoader.load(getClass().getResource("/View/Login.fxml"));
        AnchorpaneMainPatient.getChildren().add((Node) load);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadDashboard();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void loadDashboard() throws IOException {
        DashboardLoadAnchorpanePatient.getChildren().clear();
        Object load = FXMLLoader.load(getClass().getResource("/View/Patient_View.fxml"));
        DashboardLoadAnchorpanePatient.getChildren().add((Node) load);
    }
}
