
package edu.icet.hospital_system.controller.Patient;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import edu.icet.hospital_system.dto.Appointment;
import edu.icet.hospital_system.dto.Doctor;
import edu.icet.hospital_system.service.ServiceFactory;
import edu.icet.hospital_system.service.custom.AppointmentService;
import edu.icet.hospital_system.service.custom.DoctorService;
import edu.icet.hospital_system.util.PatientUtil;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class PatientAppointment implements Initializable {

    public Label lblPatientID;
    public TableColumn colPrice;
    @FXML
    private AnchorPane DashboardLoadAnchorpane;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private TableColumn<?, ?> colAvailability;

    @FXML
    private TableColumn<?, ?> colDoctorName;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colSpecialty;

    @FXML
    private Label lblDate;

    @FXML
    private TableView<Doctor> tlbDctor;

    @FXML
    private JFXTextField txtDoctorID;

    @FXML
    private JFXTextField txtTime;

    @FXML
    private DatePicker txtdatePicker;

    @FXML
    void DashboardLoadAnchorpaneAction(MouseEvent event) {

    }

    AppointmentService service = ServiceFactory.getInstance().getServiceType(ServiceType.APPOINTMENT);
    Integer id = PatientUtil.get().getPatient_id();

    @FXML
    void btnAddAction(ActionEvent event) {
        Appointment appointment = new Appointment(
                0,
                id,
                Integer.parseInt(txtDoctorID.getText()),
                txtdatePicker.getValue(),
                txtTime.getText()
        );

        if (service.add(appointment)) {

            new Alert(Alert.AlertType.CONFIRMATION, "Appointment Added").show();
            txtDoctorID.clear();
            txtTime.clear();
        } else {
            new Alert(Alert.AlertType.ERROR, "Appointment Added Fail").show();
        }
    }

    private void loadDateAndTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));
    }

    private void loadDoctorTable() {
        DoctorService doctorService = ServiceFactory.getInstance().getServiceType(ServiceType.DOCTOR);
        ObservableList<Doctor> doctorsOBList = FXCollections.observableArrayList(doctorService.getAll());

        tlbDctor.setItems(doctorsOBList);

        colId.setCellValueFactory(new PropertyValueFactory<>("doctor_id"));
        colDoctorName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSpecialty.setCellValueFactory(new PropertyValueFactory<>("specialty"));
        colAvailability.setCellValueFactory(new PropertyValueFactory<>("availability"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDateAndTime();
        lblPatientID.setText(PatientUtil.get().getPatient_id().toString());
        loadDoctorTable();

        tlbDctor.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                setTextToValues(newValue);
            }
        });
    }
    private void setTextToValues(Doctor doctor) {
        if (doctor != null) {
            txtDoctorID.setText(String.valueOf(doctor.getDoctor_id()));
        }
    }

}
