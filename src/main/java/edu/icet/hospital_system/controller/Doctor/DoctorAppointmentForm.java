package edu.icet.hospital_system.controller.Doctor;

import edu.icet.hospital_system.dto.Appointment;
import edu.icet.hospital_system.service.ServiceFactory;
import edu.icet.hospital_system.service.custom.AppointmentService;
import edu.icet.hospital_system.util.AppointmentUtil;
import edu.icet.hospital_system.util.DoctorUtil;
import edu.icet.hospital_system.util.ServiceType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DoctorAppointmentForm implements Initializable {

    public Label LblID;
    @FXML
    private TableColumn<?, ?> ColDate;

    @FXML
    private AnchorPane DashboardLoadAnchorpane;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colTime;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblDate1;

    @FXML
    private TableView<Appointment> tlbAppointment;

    AppointmentService appointmentService = ServiceFactory.getInstance().getServiceType(ServiceType.APPOINTMENT);

    @FXML
    void DashboardLoadAnchorpaneAction(MouseEvent event) {

    }

    private void getAppointmentData() {
        List<Appointment> appointmentList = appointmentService.getID(DoctorUtil.getCurrentDoctor().getDoctor_id());
        if (appointmentList == null) return;
        ObservableList<Appointment> observableListApp = FXCollections.observableArrayList(appointmentList);

        tlbAppointment.setItems(observableListApp);

        colID.setCellValueFactory(new PropertyValueFactory<>("patient_id"));
        ColDate.setCellValueFactory(new PropertyValueFactory<>("appointment_date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getAppointmentData();
        getDoctorID();
    }

    private void getDoctorID() {
        LblID.setText(DoctorUtil.getCurrentDoctor().getDoctor_id().toString());
    }
}
