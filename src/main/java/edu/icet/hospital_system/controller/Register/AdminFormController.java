
package edu.icet.hospital_system.controller.Register;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import edu.icet.hospital_system.dto.Admin;
import edu.icet.hospital_system.service.ServiceFactory;
import edu.icet.hospital_system.service.custom.AdminService;
import edu.icet.hospital_system.util.OTPManager;
import edu.icet.hospital_system.util.Password;
import edu.icet.hospital_system.util.ServiceType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminFormController {

    public AnchorPane DashboardLoadAnchorpane;
    public AnchorPane AdminAnchorePane;
    public JFXTextField txtOTP;
    @FXML
    private JFXButton btnSingUpAdmin;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtContactNumber;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtNIC;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtPassword;
    AdminService service = ServiceFactory.getInstance().getServiceType(ServiceType.ADMIN);

    @FXML
    void btnSingUpAdminAction(ActionEvent event) {
        String enteredOtp = txtOTP.getText();
        String generatedOtp = OTPManager.getOTP(txtEmail.getText());

        if (generatedOtp == null) {
            new Alert(Alert.AlertType.ERROR, "No OTP found for this email. Please request a new one.").show();
            return;
        }

        if (enteredOtp.equals(generatedOtp)) {

            if ((txtName.getText().isEmpty() && txtNIC.getText().isEmpty() && txtEmail.getText().isEmpty() &&
                    txtAddress.getText().isEmpty() && txtContactNumber.getText().isEmpty() && txtPassword.getText().isEmpty())) {

                new Alert(Alert.AlertType.ERROR, "Fill All Detail").show();
            } else {
                if (validatePhoneNumber(txtContactNumber.getText()) && validateEmail(txtEmail.getText())) {
                    Admin admin = new Admin(
                            0,
                            txtName.getText(),
                            txtNIC.getText(),
                            txtAddress.getText(),
                            txtContactNumber.getText(),
                            txtEmail.getText(),
                            Password.getInstance().encryptPassword(txtPassword.getText())
                    );
                    service.addAdmin(admin);

                    new Alert(Alert.AlertType.CONFIRMATION, "Added Success !").show();

                    txtName.clear();
                    txtNIC.clear();
                    txtAddress.clear();
                    txtContactNumber.clear();
                    txtEmail.clear();
                    txtOTP.clear();
                    txtPassword.clear();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Add Valid Detail").show();
                }
            }

        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid OTP. Try Again.").show();
        }
    }

    public void btnbackAction(ActionEvent actionEvent) {
        try {
            AdminAnchorePane.getChildren().clear();
            Object load = FXMLLoader.load(getClass().getResource("/View/Login.fxml"));
            AdminAnchorePane.getChildren().add((Node) load);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load view: " + e.getMessage()).show();
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

    public void btnOTPAction(ActionEvent actionEvent) {
        String email = txtEmail.getText();
        if (email.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Enter your email!").show();
        }

        String otp = OTPManager.generateOTP();
        OTPManager.storeOTP(email, otp);
        OTPManager.sendOTP(email, otp);


        new Alert(Alert.AlertType.INFORMATION, "OTP sent to your email.").show();
    }



}
