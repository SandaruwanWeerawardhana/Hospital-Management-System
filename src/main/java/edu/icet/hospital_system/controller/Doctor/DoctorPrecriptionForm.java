package edu.icet.hospital_system.controller.Doctor;

import com.jfoenix.controls.JFXTextField;
import edu.icet.hospital_system.dto.Appointment;
import edu.icet.hospital_system.dto.Patient;
import edu.icet.hospital_system.dto.Prescription;
import edu.icet.hospital_system.service.ServiceFactory;
import edu.icet.hospital_system.service.custom.AppointmentService;
import edu.icet.hospital_system.service.custom.PatientService;
import edu.icet.hospital_system.service.custom.PrescriptionService;
import edu.icet.hospital_system.util.DoctorUtil;
import edu.icet.hospital_system.util.ServiceType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DoctorPrecriptionForm implements Initializable {

    public Label lblDoctorID;
    @FXML
    private TableColumn<?, ?> ColAge;

    @FXML
    private AnchorPane DashboardLoadAnchorpane;

    @FXML
    private TableColumn<?, ?> colContactDetails;

    @FXML
    private TableColumn<?, ?> colGender;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colMedicalHistory;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblDate1;

    @FXML
    private TableView<Patient> tlbPatientDetail;

    @FXML
    private JFXTextField txtDosage;

    @FXML
    private JFXTextField txtDuration;

    @FXML
    private JFXTextField txtMedicine;

    @FXML
    private JFXTextField txtPatientId;

    @FXML
    void DashboardLoadAnchorpaneAction(MouseEvent event) {

    }

    PrescriptionService prescriptionService = ServiceFactory.getInstance().getServiceType(ServiceType.PRESCRIPTION);

    @FXML
    void btnPrescriptionAction(ActionEvent event) {
        Prescription prescription = new Prescription(
                0,
                Integer.parseInt(txtPatientId.getText()),
                Integer.parseInt(lblDoctorID.getText()),
                txtMedicine.getText(),
                txtDosage.getText(),
                txtDuration.getText()
        );
        boolean isAdded = prescriptionService.add(prescription);
        if(isAdded) {
            boolean prescriptionAdded = new Alert(Alert.AlertType.CONFIRMATION, "Prescription Added").showAndWait().filter(r -> r == ButtonType.OK).isPresent();
            if (prescriptionAdded){
                txtPatientId.clear();
                txtMedicine.clear();
                txtDuration.clear();
                txtDosage.clear();
            }


        }else {
            new Alert(Alert.AlertType.ERROR, "Added Fail").show();
        }
    }

    public void loadTable() {
        AppointmentService appointmentService = ServiceFactory.getInstance().getServiceType(ServiceType.APPOINTMENT);
        ObservableList<Appointment> appointmentsObList = FXCollections.observableArrayList();
//        appointmentsObList.add()
        List<Appointment> id = appointmentService.getID(DoctorUtil.getCurrentDoctor().getDoctor_id());
        appointmentsObList.forEach(data->{
            System.out.println(data);
        });

        PatientService patientService = ServiceFactory.getInstance().getServiceType(ServiceType.PATIENT);
        ObservableList<Patient> prescriptionObList = FXCollections.observableArrayList(patientService.getAll(1));



        tlbPatientDetail.setItems(prescriptionObList);

        colID.setCellValueFactory(new PropertyValueFactory<>("patient_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ColAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colMedicalHistory.setCellValueFactory(new PropertyValueFactory<>("medical_history"));
        colContactDetails.setCellValueFactory(new PropertyValueFactory<>("emergency_contact"));


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTable();
        lblDoctorID.setText(DoctorUtil.getCurrentDoctor().getDoctor_id().toString());
    }
}
