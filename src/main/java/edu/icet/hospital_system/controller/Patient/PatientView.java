package edu.icet.hospital_system.controller.Patient;

import edu.icet.hospital_system.DB.DBConnection;
import edu.icet.hospital_system.dto.Appointment;
import edu.icet.hospital_system.dto.Prescription;
import edu.icet.hospital_system.service.ServiceFactory;
import edu.icet.hospital_system.service.custom.AppointmentService;
import edu.icet.hospital_system.service.custom.PrescriptionService;
import edu.icet.hospital_system.util.DoctorUtil;
import edu.icet.hospital_system.util.PatientUtil;
import edu.icet.hospital_system.util.ServiceType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class PatientView implements Initializable {

    public AnchorPane ViewAnchorpane;
    public Label lblID;
    @FXML
    private TableColumn<?, ?> colAvailability;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colDoctorName;

    @FXML
    private TableColumn<?, ?> colDosage;

    @FXML
    private TableColumn<?, ?> colDuration;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colMedicine;

    @FXML
    private TableColumn<?, ?> colSpecialty;

    @FXML
    private TableColumn<?, ?> colTime;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblDate1;

    @FXML
    private Label lblDate11;

    @FXML
    private TableView<Appointment> tlbAppointment;

    @FXML
    private TableView<Prescription> tlbPrecription;

    private void loadPrecription() {
        PrescriptionService prescriptionService = ServiceFactory.getInstance().getServiceType(ServiceType.PRESCRIPTION);
        List<Prescription> list = prescriptionService.getAllByPatientID(PatientUtil.get().getPatient_id());
        ObservableList<Prescription> prescriptionObList = FXCollections.observableArrayList(list);

        tlbPrecription.setItems(prescriptionObList);

        colID.setCellValueFactory(new PropertyValueFactory<>("prescription_id"));
        colMedicine.setCellValueFactory(new PropertyValueFactory<>("medicine"));
        colDosage.setCellValueFactory(new PropertyValueFactory<>("dosage"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
    }
    private void loadAppointment() {
        AppointmentService appointmentService = ServiceFactory.getInstance().getServiceType(ServiceType.APPOINTMENT);
        List<Appointment> appointmentList = appointmentService.getPatientByID(PatientUtil.get().getPatient_id());
        if (appointmentList == null) return;
        ObservableList<Appointment> observableListApp = FXCollections.observableArrayList(appointmentList);

        tlbAppointment.setItems(observableListApp);

        colDate.setCellValueFactory(new PropertyValueFactory<>("appointment_date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
//        colID.setCellValueFactory(new PropertyValueFactory<>(""));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblID.setText(PatientUtil.get().getPatient_id().toString());
        loadPrecription();
        loadAppointment();
    }

    public void btnActionReport(ActionEvent actionEvent) {
        try {

            JasperDesign design = JRXmlLoader.load("src/main/resources/Report/prescription.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(design);


            Map<String, Object> parameters = new HashMap<>();
            parameters.put("patient_id", PatientUtil.get().getPatient_id());

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, DBConnection.getInstance().getConnection());
            JasperExportManager.exportReportToPdfFile(jasperPrint, "Prescription.pdf");
            JasperViewer.viewReport(jasperPrint, false);

        } catch (SQLException | JRException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}

