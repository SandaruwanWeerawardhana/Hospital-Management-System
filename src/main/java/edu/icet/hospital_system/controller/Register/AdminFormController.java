
package edu.icet.hospital_system.controller.Register;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import edu.icet.hospital_system.dto.Admin;
import edu.icet.hospital_system.service.ServiceFactory;
import edu.icet.hospital_system.service.custom.AdminService;
import edu.icet.hospital_system.util.ServiceType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AdminFormController {

    public AnchorPane DashboardLoadAnchorpane;

    public JFXButton btnPrescription;
    public AnchorPane AdminAnchorePane;
    @FXML
    private JFXButton btnSingUpAdmin;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtContactNumber;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtNIC;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtPassword;
    AdminService service = ServiceFactory.getInstance().getServiceType(ServiceType.ADMIN);

    @FXML
    void btnSingUpAdminAction(ActionEvent event) {
        if ((txtName.getText().isEmpty() && txtNIC.getText().isEmpty() && txtEmail.getText().isEmpty() &&
                txtAddress.getText().isEmpty() && txtContactNumber.getText().isEmpty() && txtPassword.getText().isEmpty())) {

            new Alert(Alert.AlertType.ERROR, "Fill All Detail").show();
        } else {
            Admin admin = new Admin(
                    0,
                    txtName.getText(),
                    txtNIC.getText(),
                    txtAddress.getText(),
                    txtContactNumber.getText(),
                    txtEmail.getText(),
                    txtPassword.getText()
            );
            service.addAdmin(admin);

            new Alert(Alert.AlertType.ERROR, "Added Success !").show();

            txtName.clear();
            txtNIC.clear();
            txtAddress.clear();
            txtContactNumber.clear();
            txtEmail.clear();
            txtPassword.clear();

        }
    }

    public void btnbackAction(ActionEvent actionEvent) {
        try {
            AdminAnchorePane.getChildren().clear();
            Object load = FXMLLoader.load(getClass().getResource("/View/Login.fxml"));
            AdminAnchorePane.getChildren().add((Node) load);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load view: " + e.getMessage()).show();
        }
    }

}
