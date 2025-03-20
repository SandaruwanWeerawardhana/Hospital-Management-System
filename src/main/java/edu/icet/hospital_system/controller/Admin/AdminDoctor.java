package edu.icet.hospital_system.controller.Admin;

import com.jfoenix.controls.JFXButton;
import edu.icet.hospital_system.DB.DBConnection;
import edu.icet.hospital_system.dto.Doctor;
import edu.icet.hospital_system.service.ServiceFactory;
import edu.icet.hospital_system.service.custom.DoctorService;
import edu.icet.hospital_system.util.ServiceType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminDoctor implements Initializable {

    @FXML
    private AnchorPane DashboardLoadAnchorpane;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private TableColumn<?, ?> colAvailability;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colDoctorName;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colQualifications;

    @FXML
    private TableColumn<?, ?> colSpecialty;

    @FXML
    private TableView<Doctor> tlbDoctor;

    public void btnReportAction(ActionEvent actionEvent) {
        try {
            JasperDesign design = JRXmlLoader.load("src/main/resources/Report/doctor.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(design);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());
            JasperExportManager.exportReportToPdfFile(jasperPrint, "Doctor_Report.pdf");
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDoctorTable();
    }

    private void loadDoctorTable() {
        DoctorService doctorService = ServiceFactory.getInstance().getServiceType(ServiceType.DOCTOR);
        ObservableList<Doctor> doctorsOBList = FXCollections.observableArrayList(doctorService.getAll());

        tlbDoctor.setItems(doctorsOBList);

        colID.setCellValueFactory(new PropertyValueFactory<>("doctor_id"));
        colDoctorName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSpecialty.setCellValueFactory(new PropertyValueFactory<>("specialty"));
        colAvailability.setCellValueFactory(new PropertyValueFactory<>("availability"));
        colQualifications.setCellValueFactory(new PropertyValueFactory<>("qualifications"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact_details"));
    }

    public void btnaddAction(ActionEvent actionEvent) {

        try {
            DashboardLoadAnchorpane.getChildren().clear();
            Object load = FXMLLoader.load(getClass().getResource("/View/Doctor_Register.fxml"));
            DashboardLoadAnchorpane.getChildren().add((Node) load);

        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load view: " + e.getMessage()).show();
        }
    }
}
