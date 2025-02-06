package edu.icet.hospital_system.controller.Patient;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class PatientDashboard {

    public AnchorPane DashboardLoadAnchorpanePatient;
    public AnchorPane AnchorpaneMainPatient;

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
    void btnViewAction(ActionEvent event) {

    }

    @FXML
    public void btnBackAction(ActionEvent actionEvent) throws IOException {
        AnchorpaneMainPatient.getChildren().clear();
        Object load = FXMLLoader.load(getClass().getResource("/View/Login.fxml"));
        AnchorpaneMainPatient.getChildren().add((Node) load);
    }
}
