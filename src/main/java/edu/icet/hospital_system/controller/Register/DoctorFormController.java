
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

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DoctorFormController {

    public JFXTextField txtContact;
    public AnchorPane DoctorAnchorePane;
    public JFXTextField txtotp;
    private String generatedOTP;
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

        if (sentEmailForOTP == null || !email.equals(sentEmailForOTP)) {
            new Alert(Alert.AlertType.ERROR, "The email does not match the OTP recipient.").show();
            return;
        }

        // Verify OTP
//        if (!OTPManager.verifyOTP(email, enteredOtp)) {
//            new Alert(Alert.AlertType.ERROR, "Invalid OTP. Try Again.").show();
//            return;
//        }

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
                    txtPassword.clear();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Added Fail").show();
                }
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
        String otp = generateOTP();
        OTPManager.storeOTP(email, otp);
        sendOTP(email, otp);
        sentEmailForOTP = email;

        new Alert(Alert.AlertType.INFORMATION, "OTP sent to your email.").show();

    }

    public void sendOTP(String recipientEmail, String otp) {
        final String username = "bitzlk01@gmail.com";
        final String password = "lvgo bxrq eypr bbdc";
        final String host = "smtp.gmail.com";
        final int port = 587;

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        session.setDebug(true);
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Your OTP Code");
            message.setText("Your OTP code is: " + otp);

            Transport.send(message);
            System.out.println("OTP sent successfully to " + recipientEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public String generateOTP() {
        Random random = new Random();
        int otp = 1000 + random.nextInt(9000);
        return String.valueOf(otp);
    }


}
