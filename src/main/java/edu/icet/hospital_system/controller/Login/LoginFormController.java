
package edu.icet.hospital_system.controller.Login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {

    public AnchorPane loadAnchorePaneLogin;
    @FXML
    private JFXButton btnLogin;

    @FXML
    private JFXButton btnRegister;

    @FXML
    private JFXComboBox<String> loginChoiseBox;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    void btnLoginAction(ActionEvent event) throws IOException {
        String selectedRole = loginChoiseBox.getValue();
        if (txtEmail.getText().isEmpty() && txtPassword.getText().isEmpty() && loginChoiseBox.getValue().isEmpty()) {
            new Alert(Alert.AlertType.ERROR," Requre Fill All text Field ").show();
        }else {
            switch (selectedRole) {
                case "Doctor":
                    loadAnchorePaneLogin.getChildren().clear();
                    Object load = FXMLLoader.load(getClass().getResource("/View/Doctor_Dashboard.fxml"));
                    loadAnchorePaneLogin.getChildren().add((Node) load);
                    break;

                case "Patient":
                    loadAnchorePaneLogin.getChildren().clear();
                    Object load1 = FXMLLoader.load(getClass().getResource("/View/Patient_Dashboard.fxml"));
                    loadAnchorePaneLogin.getChildren().add((Node) load1);
                    break;
                case "Admin":
                    loadAnchorePaneLogin.getChildren().clear();
                    Object load2 = FXMLLoader.load(getClass().getResource("/View/Admin_Dashboard.fxml"));
                    loadAnchorePaneLogin.getChildren().add((Node) load2);
                    break;
            }
        }
    }

    @FXML
    void btnRegisterAction(ActionEvent event) throws IOException {
        loadAnchorePaneLogin.getChildren().clear();
        Object load2 = FXMLLoader.load(getClass().getResource("/View/Register_selection.fxml"));
        loadAnchorePaneLogin.getChildren().add((Node) load2);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> role = FXCollections.observableArrayList(
                "Doctor", "Patient", "Admin"
        );
        loginChoiseBox.setItems(role);
    }
}
