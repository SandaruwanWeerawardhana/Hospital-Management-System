
package edu.icet.hospital_system.controller.Register;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import edu.icet.hospital_system.dto.Doctor;
import edu.icet.hospital_system.service.ServiceFactory;
import edu.icet.hospital_system.service.custom.DoctorService;
import edu.icet.hospital_system.util.ServiceType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class DoctorFormController {

    public JFXTextField txtContact;
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
        Integer id = 0;
        Doctor doctor = new Doctor(
                id,
                txtName.getText(),
                txtSpecialty.getText(),
                txtAvalibility.getText(),
                txtQulification.getText(),
                txtContact.getText(),
                txtEmail.getText(),
                txtPassword.getText()
                );
        service.addDoctor(doctor);
    }

}
