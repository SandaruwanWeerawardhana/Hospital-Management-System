
package edu.icet.hospital_system.controller.Admin;

import com.jfoenix.controls.JFXButton;
import edu.icet.hospital_system.dto.Patient;
import edu.icet.hospital_system.service.ServiceFactory;
import edu.icet.hospital_system.service.custom.PatientService;
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
import java.util.ResourceBundle;

public class AdminPatientForm implements Initializable {


    @FXML
    private TableColumn<?, ?> ColAge;

    @FXML
    private AnchorPane DashboardLoadAnchorpane;

    @FXML
    private JFXButton btnReportAction;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colGender;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableView<Patient> tlbPatient;

    @FXML
    void btnReportAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTable();
    }

    public void loadTable() {
        PatientService patientService = ServiceFactory.getInstance().getServiceType(ServiceType.PATIENT);
        ObservableList<Patient> prescriptionObList = FXCollections.observableArrayList(patientService.getAllData());

        tlbPatient.setItems(prescriptionObList);

        colID.setCellValueFactory(new PropertyValueFactory<>("patient_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ColAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("emergency_contact"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

    }
}
