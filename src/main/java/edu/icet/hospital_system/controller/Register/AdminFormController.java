
package edu.icet.hospital_system.controller.Register;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import edu.icet.hospital_system.dto.Admin;
import edu.icet.hospital_system.service.ServiceFactory;
import edu.icet.hospital_system.service.custom.AdminService;
import edu.icet.hospital_system.util.ServiceType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class AdminFormController {

    public AnchorPane DashboardLoadAnchorpane;

    public JFXButton btnPrescription;
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
        Integer id = 0;
        Admin admin = new Admin(
                id,
                txtName.getText(),
                txtNIC.getText(),
                txtAddress.getText(),
                txtContactNumber.getText(),
                txtEmail.getText(),
                txtPassword.getText()
        );
        service.addAdmin(admin);
    }

    public void btnbackAction(ActionEvent actionEvent) {
    }

}
