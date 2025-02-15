package edu.icet.hospital_system.controller.Patient;

import com.jfoenix.controls.JFXTextField;
import edu.icet.hospital_system.dto.Payment;
import edu.icet.hospital_system.service.ServiceFactory;
import edu.icet.hospital_system.service.custom.PaymentService;
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

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class PatientPayment implements Initializable {

    public Label lblPatientID;
    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colPaymentStatus;

    @FXML
    private Label lblDate;

    @FXML
    private TableView<Payment> tlbPayment;

    @FXML
    private JFXTextField txtAmount;

    PaymentService service = ServiceFactory.getInstance().getServiceType(ServiceType.PAYMENT);

    @FXML
    void btnPayAction(ActionEvent event) {

        Payment payment = new Payment(
                0,
                PatientUtil.get().getPatient_id(),
                Double.parseDouble(txtAmount.getText()),
                "Paid",
                null
        );
        service.add(payment);
    }


    private void loadTable() {
      List<Payment> paymentList = service.getAll(PatientUtil.get().getPatient_id());
        ObservableList<Payment> observableList = FXCollections.observableArrayList(paymentList);

        tlbPayment.setItems(observableList);

        colAmount.setCellValueFactory(new PropertyValueFactory<>("total_amount"));
        colPaymentStatus.setCellValueFactory(new PropertyValueFactory<>("payment_status"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("generated_date"));
    }
    private void loadDateAndTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblPatientID.setText(PatientUtil.get().getPatient_id().toString());
        loadDateAndTime();
        loadTable();
    }
}
