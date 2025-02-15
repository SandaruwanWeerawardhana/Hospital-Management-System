package edu.icet.hospital_system.controller.Admin;

import com.jfoenix.controls.JFXTextField;
import edu.icet.hospital_system.dto.Admin;
import edu.icet.hospital_system.service.ServiceFactory;
import edu.icet.hospital_system.service.custom.AdminService;
import edu.icet.hospital_system.util.AdminUtil;
import edu.icet.hospital_system.util.Password;
import edu.icet.hospital_system.util.ServiceType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminProfile implements Initializable {

    @FXML
    private AnchorPane DashboardLoadAnchorpane;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtContact;

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
    void btnDeleteAction(ActionEvent event) {
        if (service.deleteAdmin(AdminUtil.getCurrent().getAdmin_id())) {
            txtName.clear();
            txtNIC.clear();
            txtAddress.clear();
            txtContact.clear();
            txtEmail.clear();
            txtPassword.clear();
            new Alert(Alert.AlertType.INFORMATION, "Delete Success").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Delete Fail").show();
        }
    }

    @FXML
    void btnEditAction(ActionEvent event) {
        Admin admin = new Admin(
                AdminUtil.getCurrent().getAdmin_id(),
                txtName.getText(),
                txtNIC.getText(),
                txtAddress.getText(),
                txtContact.getText(),
                txtEmail.getText(),
                Password.getInstance().encryptPassword(txtPassword.getText())
        );

        if (service.updateAdmin(admin)) {
            AdminUtil.setCurrent(admin);
            new Alert(Alert.AlertType.CONFIRMATION, "Update Success").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Update Fail").show();
        }
    }

    private void loadTextField() {
        Admin admin = AdminUtil.getCurrent();
        if (admin != null) {

            txtName.setText(admin.getName());
            txtNIC.setText(admin.getNic());
            txtAddress.setText(admin.getAddress());
            txtContact.setText(admin.getContact_details());
            txtEmail.setText(admin.getEmail());
            txtPassword.setText(Password.getInstance().decryptPassword(admin.getPassword()));
        } else {
            new Alert(Alert.AlertType.WARNING, "No found with ID").show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTextField();
    }
}
