
package edu.icet.hospital_system.controller.Login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import edu.icet.hospital_system.dto.Admin;
import edu.icet.hospital_system.dto.Doctor;
import edu.icet.hospital_system.dto.Patient;
import edu.icet.hospital_system.service.ServiceFactory;
import edu.icet.hospital_system.service.custom.AdminService;
import edu.icet.hospital_system.service.custom.DoctorService;
import edu.icet.hospital_system.service.custom.PatientService;
import edu.icet.hospital_system.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {

    public AnchorPane loadAnchorePaneLogin;
    @FXML
    private JFXButton btnLogin;

    @FXML
    private JFXButton btnRegister;

    @FXML
    private JFXComboBox<String> loginChoiseBox;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    void btnLoginAction(ActionEvent event) throws IOException {
        String selectedRole = loginChoiseBox.getValue();

        if (txtEmail.getText().isEmpty() && txtPassword.getText().isEmpty() && selectedRole == null) {
            new Alert(Alert.AlertType.ERROR, " Requre Fill All text Field ").show();
        } else {
            switch (selectedRole) {
                case "Doctor":

                    DoctorService doctorService = ServiceFactory.getInstance().getServiceType(ServiceType.DOCTOR);
                    Doctor doctor = doctorService.doctorValidatioin(txtEmail.getText().trim());

                    if (doctor == null) {
                        new Alert(Alert.AlertType.ERROR, "Invalide UserName Or Password").show();
                    } else {
                        String password = Password.getInstance().decryptPassword(doctor.getPassword());
                        DoctorUtil.setCurrentDoctor(doctor);
                        if (password.equals(txtPassword.getText())) {
                            loadAnchorePaneLogin.getChildren().clear();
                            Object load = FXMLLoader.load(getClass().getResource("/View/Doctor_Dashboard.fxml"));
                            loadAnchorePaneLogin.getChildren().add((Node) load);
                        } else {
                            new Alert(Alert.AlertType.ERROR, "Invalide Password").show();
                        }
                    }
                    break;

                case "Patient":
                    PatientService patientService = ServiceFactory.getInstance().getServiceType(ServiceType.PATIENT);
                    Patient patient = patientService.getEmail(txtEmail.getText().trim());

                    if (patient == null) {
                        new Alert(Alert.AlertType.ERROR, "Invalide UserName Or Password").show();
                    } else {
                        String password = Password.getInstance().decryptPassword(patient.getPassword());
                        PatientUtil.setCurrentDoctor(patient);
                        if (password.equals(txtPassword.getText())) {

                            loadAnchorePaneLogin.getChildren().clear();
                            Object load1 = FXMLLoader.load(getClass().getResource("/View/Patient_Dashboard.fxml"));
                            loadAnchorePaneLogin.getChildren().add((Node) load1);
                        } else {
                            new Alert(Alert.AlertType.ERROR, "Invalid Password").show();
                        }
                    }
                    break;

                case "Admin":
                    AdminService adminService =ServiceFactory.getInstance().getServiceType(ServiceType.ADMIN);
                    Admin admin = adminService.getEmail(txtEmail.getText().trim());

                    if (admin == null) {
                        new Alert(Alert.AlertType.ERROR, "Invalid UserName Or Password").show();
                    } else {
                        String password = Password.getInstance().decryptPassword(admin.getPassword());
                        AdminUtil.setCurrent(admin);
                        if (password.equals(txtPassword.getText())) {

                            loadAnchorePaneLogin.getChildren().clear();
                            Object load2 = FXMLLoader.load(getClass().getResource("/View/Admin_Dashboard.fxml"));
                            loadAnchorePaneLogin.getChildren().add((Node) load2);
                        } else {
                            new Alert(Alert.AlertType.ERROR, "Invalid Password").show();
                        }
                    }
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + selectedRole);
            }
        }
    }

    @FXML
    void btnRegisterAction(ActionEvent event) throws IOException {
        loadAnchorePaneLogin.getChildren().clear();
        Object load2 = FXMLLoader.load(getClass().getResource("/View/Register_selection.fxml"));
        loadAnchorePaneLogin.getChildren().add((Node) load2);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> role = FXCollections.observableArrayList(
                "Doctor", "Patient", "Admin"
        );
        loginChoiseBox.setItems(role);
    }
}
