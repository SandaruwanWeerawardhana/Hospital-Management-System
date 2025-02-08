
package edu.icet.hospital_system.controller.Register;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import edu.icet.hospital_system.dto.Patient;
import edu.icet.hospital_system.service.ServiceFactory;
import edu.icet.hospital_system.service.custom.PatientService;
import edu.icet.hospital_system.util.ServiceType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.net.URL;
import java.util.ResourceBundle;

public class PatientFormController implements Initializable {

    @FXML
    private JFXComboBox<String> ChoiseBoxGender;

    @FXML
    private JFXButton btnSingUp;

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

    PatientService service = ServiceFactory.getInstance().getServiceType(ServiceType.PATIENT);

    @FXML
    void btnSingUpAction(ActionEvent event) {
        Integer id = 0;
        if (!(txtName.getText().isEmpty() && txtEmergencyContact.getText().isEmpty() && !txtEmail.getText().isEmpty() &&
                txtName.getText().isEmpty() && ChoiseBoxGender.getValue().isEmpty() && txtAge.getText().isEmpty())) {
            if (isInteger(txtAge.getText())) {
                Patient patient = new Patient(
                        id,
                        txtName.getText(),
                        Integer.parseInt(txtAge.getText()),
                        ChoiseBoxGender.getValue(),
                        txtMedicalHistory.getText(),
                        txtEmergencyContact.getText(),
                        txtEmail.getText(),
                        txtPassword.getText()
                );
                service.addPatient(patient);
            } else {
                new Alert(Alert.AlertType.ERROR, "Input Correct Age").show();
            }
            new Alert(Alert.AlertType.ERROR, "Fill All Detail").show();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> role = FXCollections.observableArrayList(
                "Male", "Female", "Other"
        );
        ChoiseBoxGender.setItems(role);
    }

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
