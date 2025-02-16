
package edu.icet.hospital_system.controller.Register;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import edu.icet.hospital_system.dto.Patient;
import edu.icet.hospital_system.service.ServiceFactory;
import edu.icet.hospital_system.service.custom.PatientService;
import edu.icet.hospital_system.util.Password;
import edu.icet.hospital_system.util.ServiceType;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatientFormController implements Initializable {

    public AnchorPane PatientAnchorePane;
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

        if ((txtName.getText().isEmpty() && txtEmergencyContact.getText().isEmpty() && txtEmail.getText().isEmpty() &&
                txtName.getText().isEmpty() && ChoiseBoxGender.getValue().isEmpty() && txtAge.getText().isEmpty())) {

            new Alert(Alert.AlertType.ERROR, "Fill All Detail").show();
        } else {
            if (validatePhoneNumber(txtEmergencyContact.getText()) && validateEmail(txtEmail.getText())) {
                if (isInteger(txtAge.getText())) {
                    Patient patient = new Patient(
                            0,
                            txtName.getText(),
                            Integer.parseInt(txtAge.getText()),
                            ChoiseBoxGender.getValue(),
                            txtMedicalHistory.getText(),
                            txtEmergencyContact.getText(),
                            txtEmail.getText(),
                            Password.getInstance().encryptPassword(txtPassword.getText())
                    );
                    service.addPatient(patient);
                    new Alert(Alert.AlertType.CONFIRMATION, "Added Success !").show();

                    txtName.clear();
                    txtAge.clear();
                    ChoiseBoxGender.setValue(null);
                    txtMedicalHistory.clear();
                    txtEmergencyContact.clear();
                    txtEmail.clear();
                    txtPassword.clear();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Input Correct Age").show();
                }
            }else {
                new Alert(Alert.AlertType.WARNING, "Add Valid Detail").show();
            }

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

    public void BackAction(ActionEvent actionEvent) {
        try {
            PatientAnchorePane.getChildren().clear();
            Object load = FXMLLoader.load(getClass().getResource("/View/Login.fxml"));
            PatientAnchorePane.getChildren().add((Node) load);
        } catch (IOException e) {
            new Alert(Alert.AlertType.CONFIRMATION, "Failed to load view: " + e.getMessage()).show();
        }
    }

    private boolean validatePhoneNumber(String phoneNumber) {

        Pattern pattern = Pattern.compile("^0\\d{9}$");
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();

    }

    private boolean validateEmail(String email) {

        Pattern pattern = Pattern.compile("^[^@]+@[^@]+$");
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
}
