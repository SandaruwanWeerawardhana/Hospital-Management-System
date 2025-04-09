
package edu.icet.hospital_system.controller.Register;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import edu.icet.hospital_system.dto.Doctor;
import edu.icet.hospital_system.service.ServiceFactory;
import edu.icet.hospital_system.service.custom.DoctorService;
import edu.icet.hospital_system.util.OTPManager;
import edu.icet.hospital_system.util.Password;
import edu.icet.hospital_system.util.ServiceType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DoctorFormController {

    public JFXTextField txtContact;
    public AnchorPane DoctorAnchorePane;
    public JFXTextField txtotp;
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

    private String sentEmailForOTP;

    @FXML
    void btnSingUpAction(ActionEvent event) {

        String email = txtEmail.getText();
        String enteredOtp = txtotp.getText();
        String generatedOtp = OTPManager.getOTP(email);

        if (generatedOtp == null) {
            new Alert(Alert.AlertType.ERROR, "No OTP found for this email. Please request a new one.").show();
            return;
        }

        if (enteredOtp.equals(generatedOtp)) {

            if ((txtName.getText().isEmpty() && txtSpecialty.getText().isEmpty() && txtEmail.getText().isEmpty() &&
                    txtQulification.getText().isEmpty() && txtAvalibility.getText().isEmpty() && txtContact.getText().isEmpty() && txtPassword.getText().isEmpty())) {

                new Alert(Alert.AlertType.ERROR, "Fill All Detail").show();
            } else {
                if (validatePhoneNumber(txtContact.getText()) && validateEmail(txtEmail.getText())) {
                    Doctor doctor = new Doctor(
                            0,
                            txtName.getText(),
                            null,
                            null,
                            null,
                            txtContact.getText(),
                            0.0,
                            txtEmail.getText(),
                            Password.getInstance().encryptPassword(txtPassword.getText())
                    );
                    service.addDoctor(doctor);

                    new Alert(Alert.AlertType.CONFIRMATION, "Added Success !").show();

                    txtName.clear();
                    txtotp.clear();
                    txtContact.clear();
                    txtEmail.clear();
                    txtotp.clear();
                    txtPassword.clear();
                    OTPManager.clearOTP(email);
                } else {
                    new Alert(Alert.AlertType.ERROR, "Added Fail").show();
                }
            }

        }else {
            new Alert(Alert.AlertType.ERROR, "Invalid OTP. Try Again.").show();
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

    public void btnOTPSend(ActionEvent actionEvent) {
        String email = txtEmail.getText();
        if (email.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Enter your email!").show();
        }

        String otp = OTPManager.generateOTP();
        OTPManager.storeOTP(email, otp);
        OTPManager.sendOTP(email, otp);
        sentEmailForOTP = email;

        new Alert(Alert.AlertType.INFORMATION, "OTP sent to your email.").show();

    }
}
