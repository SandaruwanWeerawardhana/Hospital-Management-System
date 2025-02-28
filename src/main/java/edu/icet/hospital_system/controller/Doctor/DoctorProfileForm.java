package edu.icet.hospital_system.controller.Doctor;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import edu.icet.hospital_system.dto.Doctor;
import edu.icet.hospital_system.service.ServiceFactory;
import edu.icet.hospital_system.service.custom.DoctorService;
import edu.icet.hospital_system.util.DoctorUtil;
import edu.icet.hospital_system.util.Password;
import edu.icet.hospital_system.util.ServiceType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class DoctorProfileForm implements Initializable {

    public Label lblId;
    public JFXTextField txtEmail;
    public JFXTextField txtPassword;
    public JFXTextField txtName;
    public JFXTextField txtContact;
    public JFXTextField txtPrice;
    @FXML
    private AnchorPane DashboardLoadAnchorpane;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnEdit;

    @FXML
    private Label lblDate;

    @FXML
    private JFXTextField txtAvalibility;

    @FXML
    private JFXTextField txtQulification;

    @FXML
    private JFXTextField txtSpecialty;

    @FXML
    void DashboardLoadAnchorpaneAction(MouseEvent event) {

    }

    @FXML
    void btnDeleteAction(ActionEvent event) {
        if (doctorService.deleteDoctor(DoctorUtil.getCurrentDoctor().getDoctor_id())) {
            txtName.clear();
            txtSpecialty.clear();
            txtAvalibility.clear();
            txtQulification.clear();
            txtContact.clear();
            txtPrice.clear();
            txtEmail.clear();
            txtPassword.clear();
            new Alert(Alert.AlertType.INFORMATION, "Delete Success").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Delete Fail").show();
        }
    }

    DoctorService doctorService = ServiceFactory.getInstance().getServiceType(ServiceType.DOCTOR);

    @FXML
    void btnEditAction(ActionEvent event) {
        Doctor doctor = new Doctor(
                DoctorUtil.getCurrentDoctor().getDoctor_id(),
                txtName.getText(),
                txtSpecialty.getText(),
                txtAvalibility.getText(),
                txtQulification.getText(),
                txtContact.getText(),
                Double.parseDouble(txtPrice.getText()),
                txtEmail.getText(),
                Password.getInstance().encryptPassword(txtPassword.getText())
        );

        if (doctorService.updateDoctor(doctor)) {
            DoctorUtil.setCurrentDoctor(doctor);
            new Alert(Alert.AlertType.CONFIRMATION, "Update Success").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Update Fail").show();
        }
    }

    private void loadTextField() {
        Doctor doctor = DoctorUtil.getCurrentDoctor();
        if (doctor != null) {

            txtName.setText(doctor.getName());
            txtSpecialty.setText(doctor.getSpecialty());
            txtAvalibility.setText(doctor.getAvailability());
            txtQulification.setText(doctor.getQualifications());
            txtContact.setText(doctor.getContact_details());
            txtPrice.setText(doctor.getPrice().toString());
            txtEmail.setText(doctor.getEmail());
            txtPassword.setText(Password.getInstance().decryptPassword(doctor.getPassword()));
        } else {
            new Alert(Alert.AlertType.WARNING, "No found with ID").show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblId.setText(DoctorUtil.getCurrentDoctor().getDoctor_id().toString());
        loadTextField();
    }


}
