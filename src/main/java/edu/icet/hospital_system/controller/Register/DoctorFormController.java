
package edu.icet.hospital_system.controller.Register;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import edu.icet.hospital_system.dto.Doctor;
import edu.icet.hospital_system.service.ServiceFactory;
import edu.icet.hospital_system.service.custom.DoctorService;
import edu.icet.hospital_system.util.ServiceType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class DoctorFormController {

    public JFXTextField txtContact;
    public AnchorPane DoctorAnchorePane;
    @FXML
    private JFXButton btnSingUp;

    @FXML
    private JFXTextField txtAvalibility;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    private JFXTextField txtQulification;

    @FXML
    private JFXTextField txtSpecialty;
    DoctorService service = ServiceFactory.getInstance().getServiceType(ServiceType.DOCTOR);

    @FXML
    void btnSingUpAction(ActionEvent event) {
        if ((txtName.getText().isEmpty() && txtSpecialty.getText().isEmpty() && txtEmail.getText().isEmpty() &&
                txtQulification.getText().isEmpty() && txtAvalibility.getText().isEmpty()&& txtContact.getText().isEmpty() && txtPassword.getText().isEmpty())) {

            new Alert(Alert.AlertType.ERROR, "Fill All Detail").show();
        } else {
            Doctor doctor = new Doctor(
                    0,
                    txtName.getText(),
                    txtSpecialty.getText(),
                    txtAvalibility.getText(),
                    txtQulification.getText(),
                    txtContact.getText(),
                    txtEmail.getText(),
                    txtPassword.getText()
            );
            service.addDoctor(doctor);

            new Alert(Alert.AlertType.ERROR, "Added Success !").show();

            txtName.clear();
            txtSpecialty.clear();
            txtAvalibility.clear();
            txtQulification.clear();
            txtContact.clear();
            txtEmail.clear();
            txtPassword.clear();

        }

    }

    public void BackAction(ActionEvent actionEvent) {
        try {
            DoctorAnchorePane.getChildren().clear();
            Object load = FXMLLoader.load(getClass().getResource("/View/Login.fxml"));
            DoctorAnchorePane.getChildren().add((Node) load);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load view: " + e.getMessage()).show();
        }
    }
}
