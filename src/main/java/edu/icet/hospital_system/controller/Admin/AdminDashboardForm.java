package edu.icet.hospital_system.controller.Admin;

import com.jfoenix.controls.JFXButton;
import edu.icet.hospital_system.service.ServiceFactory;
import edu.icet.hospital_system.service.custom.DoctorService;
import edu.icet.hospital_system.service.custom.PatientService;
import edu.icet.hospital_system.service.custom.PaymentService;
import edu.icet.hospital_system.util.AdminUtil;
import edu.icet.hospital_system.util.ServiceType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminDashboardForm implements Initializable {

    public AnchorPane AnchorpaneMainAdmin;
    @FXML
    private JFXButton Btnback;

    @FXML
    private AnchorPane DashboardLoadAnchorpaneAdmin;

    @FXML
    private Label lblId;

    @FXML
    private Label lblName;

    @FXML
    private Text lblTotIncome;


    @FXML
    private Text lblTotDctor;

    @FXML
    private Text lblTotPatient;




    @FXML
    void btnAappointmentAction(ActionEvent event) throws IOException {
       load();
    }

    @FXML
    void btnDoctorAction(ActionEvent event) throws IOException {
        DashboardLoadAnchorpaneAdmin.getChildren().clear();
        Object load = FXMLLoader.load(getClass().getResource("/View/Admin_Doctor.fxml"));
        DashboardLoadAnchorpaneAdmin.getChildren().add((Node) load);
    }

    @FXML
    void btnPatientAction(ActionEvent event) throws IOException {
        DashboardLoadAnchorpaneAdmin.getChildren().clear();
        Object load = FXMLLoader.load(getClass().getResource("/View/Admin_Patient.fxml"));
        DashboardLoadAnchorpaneAdmin.getChildren().add((Node) load);
    }

    @FXML
    void btnPaymentAction(ActionEvent event) throws IOException {
        DashboardLoadAnchorpaneAdmin.getChildren().clear();
        Object load = FXMLLoader.load(getClass().getResource("/View/Admin_Payment.fxml"));
        DashboardLoadAnchorpaneAdmin.getChildren().add((Node) load);
    }

    @FXML
    void btnProfileAction(ActionEvent event) throws IOException {
        DashboardLoadAnchorpaneAdmin.getChildren().clear();
        Object load = FXMLLoader.load(getClass().getResource("/View/Admin_Profile.fxml"));
        DashboardLoadAnchorpaneAdmin.getChildren().add((Node) load);
    }

    public void btnBackAction(ActionEvent actionEvent) throws IOException {
        AnchorpaneMainAdmin.getChildren().clear();
        Object load = FXMLLoader.load(getClass().getResource("/View/Login.fxml"));
        AnchorpaneMainAdmin.getChildren().add((Node) load);
    }

    public void btnViewAction(ActionEvent actionEvent) {
    }

    private void load() {

        try {
            DashboardLoadAnchorpaneAdmin.getChildren().clear();
            Object load = FXMLLoader.load(getClass().getResource("/View/Admin_Appointment.fxml"));
            DashboardLoadAnchorpaneAdmin.getChildren().add((Node) load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        load();
        lblId.setText(AdminUtil.getCurrent().getAdmin_id().toString());
        lblName.setText(AdminUtil.getCurrent().getName().toString());

        DoctorService doctorService = ServiceFactory.getInstance().getServiceType(ServiceType.DOCTOR);
        Integer doctorCount = doctorService.getDoctorIDCount();
        lblTotDctor.setText(String.valueOf(doctorCount));

        PatientService patientService = ServiceFactory.getInstance().getServiceType(ServiceType.PATIENT);
        Integer pCount = patientService.getCount();
        lblTotPatient.setText(String.valueOf(pCount));

        PaymentService paymentService = ServiceFactory.getInstance().getServiceType(ServiceType.PAYMENT);
        Double paymentCount = paymentService.getTotIncome();
        lblTotIncome.setText(String.valueOf(paymentCount));

    }

    public void btnAdminRegister(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/Admin_Register.fxml"))));
        stage.show();

    }
}
