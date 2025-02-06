
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
    private AnchorPane loadAnchorePane;

    @FXML
    void btnLoginAction(ActionEvent event) throws IOException {
        String selectedRole = loginChoiseBox.getValue();
        if (txtEmail.getText().isEmpty() && txtPassword.getText().isEmpty() && loginChoiseBox.getValue().isEmpty()) {
            new Alert(Alert.AlertType.ERROR," Requre Fill All text Field ").show();
        }else {
            switch (selectedRole) {
                case "Doctor":
                    loadAnchorePane.getChildren().clear();
                    Object load = FXMLLoader.load(getClass().getResource("/View/Doctor_Dashboard.fxml"));
                    loadAnchorePane.getChildren().add((Node) load);
                    break;

                case "Patient":
                    loadAnchorePane.getChildren().clear();
                    Object load1 = FXMLLoader.load(getClass().getResource("/View/Patient_Dashboard.fxml"));
                    loadAnchorePane.getChildren().add((Node) load1);
                    break;
                case "Admin":
                    loadAnchorePane.getChildren().clear();
                    Object load2 = FXMLLoader.load(getClass().getResource("/View/Admin_Dashboard.fxml"));
                    loadAnchorePane.getChildren().add((Node) load2);
                    break;
            }
        }
    }

    @FXML
    void btnRegisterAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/Register_selection.fxml"))));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> role = FXCollections.observableArrayList(
                "Doctor", "Patient", "Admin"
        );
        loginChoiseBox.setItems(role);
    }
}
