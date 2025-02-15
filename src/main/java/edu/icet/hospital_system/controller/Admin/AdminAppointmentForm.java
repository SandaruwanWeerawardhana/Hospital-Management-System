package edu.icet.hospital_system.controller.Admin;

import com.jfoenix.controls.JFXButton;
import edu.icet.hospital_system.dto.Appointment;
import edu.icet.hospital_system.service.ServiceFactory;
import edu.icet.hospital_system.service.custom.AppointmentService;
import edu.icet.hospital_system.util.ServiceType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminAppointmentForm implements Initializable {

    @FXML
    private AnchorPane DashboardLoadAnchorpane;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private TableColumn<?, ?> colAppointmentDate;

    @FXML
    private TableColumn<?, ?> colAppointmentID;

    @FXML
    private TableColumn<?, ?> colPatientID;

    @FXML
    private TableColumn<?, ?> colTime;

    @FXML
    private TableView<Appointment> tlbAppointment;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTlb();
    }

    public void btnReportAction(ActionEvent actionEvent) {

    }

    private void loadTlb() {
        AppointmentService appointmentService = ServiceFactory.getInstance().getServiceType(ServiceType.APPOINTMENT);
        List<Appointment> list = appointmentService.getAll();
        ObservableList<Appointment> observableList = FXCollections.observableArrayList(list);

        tlbAppointment.setItems(observableList);

        colAppointmentID.setCellValueFactory(new PropertyValueFactory<>("appointment_id"));
        colPatientID.setCellValueFactory(new PropertyValueFactory<>("patient_id"));
        colAppointmentDate.setCellValueFactory(new PropertyValueFactory<>("appointment_date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
    }
}
