
package edu.icet.hospital_system.controller.Admin;

import edu.icet.hospital_system.dto.Payment;
import edu.icet.hospital_system.service.ServiceFactory;
import edu.icet.hospital_system.service.custom.PaymentService;
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

public class AdminPayment implements Initializable {

    @FXML
    private AnchorPane DashboardLoadAnchorpane;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colPatientID;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableView<Payment> tlbPayment;

    @FXML
    void btnReportAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTable();
    }

    public void loadTable() {
        PaymentService service = ServiceFactory.getInstance().getServiceType(ServiceType.PAYMENT);
        ObservableList<Payment> ObList = FXCollections.observableArrayList(service.getAllData());

        tlbPayment.setItems(ObList);
        colID.setCellValueFactory(new PropertyValueFactory<>("bill_id"));
        colPatientID.setCellValueFactory(new PropertyValueFactory<>("patient_id"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("total_amount"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("payment_status"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("generated_date"));

    }
}
