
package edu.icet.hospital_system.controller.Patient;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import edu.icet.hospital_system.dto.Patient;
import edu.icet.hospital_system.service.ServiceFactory;
import edu.icet.hospital_system.service.custom.PatientService;
import edu.icet.hospital_system.util.Password;
import edu.icet.hospital_system.util.PatientUtil;
import edu.icet.hospital_system.util.ServiceType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class PatientProfile implements Initializable {

    @FXML
    private JFXComboBox<String> ChoiseBoxGender;

    @FXML
    private AnchorPane DashboardLoadAnchorpane;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnEdit;

    @FXML
    private Label lblDate;

    @FXML
    private JFXTextField txtAge;

    @FXML
    private JFXTextField txtContactDetail;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtEmergencyContact;

    @FXML
    private JFXTextField txtMedicalHistory;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    void DashboardLoadAnchorpaneAction(MouseEvent event) {

    }

    PatientService patientService = ServiceFactory.getInstance().getServiceType(ServiceType.PATIENT);

    @FXML
    void btnDeleteAction(ActionEvent event) {
        if (patientService.deletePatient(PatientUtil.get().getPatient_id())) {
            txtName.clear();
            txtAge.clear();
            ChoiseBoxGender.getValue();
            txtMedicalHistory.clear();
            txtEmergencyContact.clear();
            txtEmail.clear();
            txtPassword.clear();
            new Alert(Alert.AlertType.INFORMATION, "Delete Success").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Delete Fail").show();
        }
    }

    @FXML
    void btnEditAction(ActionEvent event) {
        if (txtName.getText().isEmpty() ||
                txtAge.getText().isEmpty() ||
                ChoiseBoxGender.getValue() == null ||
                txtMedicalHistory.getText().isEmpty() ||
                txtEmergencyContact.getText().isEmpty() ||
                txtEmail.getText().isEmpty() ||
                txtPassword.getText().isEmpty()) {

            new Alert(Alert.AlertType.ERROR, "Fill all text fields").show();
            return;
        }

        try {
            Patient patient = new Patient(
                    PatientUtil.get().getPatient_id(),
                    txtName.getText(),
                    Integer.parseInt(txtAge.getText()),
                    ChoiseBoxGender.getValue(),
                    txtMedicalHistory.getText(),
                    txtEmergencyContact.getText(),
                    txtEmail.getText(),
                    Password.getInstance().encryptPassword(txtPassword.getText())
            );

            if (patientService.updatePatient(patient)) {
                PatientUtil.set(patient);
                new Alert(Alert.AlertType.CONFIRMATION, "Update Success").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Update Fail").show();
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid age. Please enter a number.").show();
        }
    }

    @FXML
    void loginChoiseBoxAction(ActionEvent event) {

    }

    private void loadTextField() {
        Patient patient = PatientUtil.get();

        if (patient != null) {
            txtName.setText(patient.getName());
            txtAge.setText(patient.getAge().toString());
            ChoiseBoxGender.getSelectionModel();
            txtEmergencyContact.setText(patient.getEmergency_contact());
            txtMedicalHistory.setText(patient.getMedical_history());
            txtEmail.setText(patient.getEmail());
            txtPassword.setText(Password.getInstance().decryptPassword(patient.getPassword()));

        } else {
            new Alert(Alert.AlertType.WARNING, "No found with ID").show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTextField();
        ObservableList<String> role = FXCollections.observableArrayList(
                "Male", "Female", "Other"
        );
        ChoiseBoxGender.setItems(role);
    }
}
