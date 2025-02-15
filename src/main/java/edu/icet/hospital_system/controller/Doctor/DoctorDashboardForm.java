package edu.icet.hospital_system.controller.Doctor;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DoctorDashboardForm implements Initializable {

    public AnchorPane AnchorePaneDoctorMain;
    @FXML
    private JFXButton Btnback;

    @FXML
    private AnchorPane DashboardLoadAnchorpane;

    @FXML
    private JFXButton btnAappointment;

    @FXML
    private JFXButton btnPayment;

    @FXML
    private JFXButton btnPrescription;

    @FXML
    private JFXButton btnProfile;

    @FXML
    private AnchorPane loadAnchorePane;

    @FXML
    void btnAappointmentAction(ActionEvent event) throws IOException {
      loadDashboard();
    }

    @FXML
    void btnPrescriptionAction(ActionEvent event) throws IOException {
        DashboardLoadAnchorpane.getChildren().clear();
        Object load = FXMLLoader.load(getClass().getResource("/View/Doctor_Precription.fxml"));
        DashboardLoadAnchorpane.getChildren().add((Node) load);
    }

    @FXML
    void btnProfileAction(ActionEvent event) throws IOException {
        DashboardLoadAnchorpane.getChildren().clear();
        Object load = FXMLLoader.load(getClass().getResource("/View/Doctor_Profile.fxml"));
        DashboardLoadAnchorpane.getChildren().add((Node) load);
    }

    public void btnActionLogOut(ActionEvent actionEvent) throws IOException {
        AnchorePaneDoctorMain.getChildren().clear();
        Object load = FXMLLoader.load(getClass().getResource("/View/Login.fxml"));
        AnchorePaneDoctorMain.getChildren().add((Node) load);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadDashboard();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadDashboard() throws IOException {
        DashboardLoadAnchorpane.getChildren().clear();
        Object load = FXMLLoader.load(getClass().getResource("/View/Doctor_Appointment.fxml"));
        DashboardLoadAnchorpane.getChildren().add((Node) load);
    }
}
