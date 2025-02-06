
package edu.icet.hospital_system.controller.Register;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterSelectionFormController {

    @FXML
    private JFXButton btnAdmin;

    @FXML
    private JFXButton btnDoctor;

    @FXML
    private JFXButton btnReceptionist;

    @FXML
    void btnAdminAction(ActionEvent event) {
        Stage stage1 = new Stage();
        try {
            stage1.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/Admin_Register.fxml"))));
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load view: " + e.getMessage()).show();
        }
        stage1.show();
    }

    @FXML
    void btnDoctorAction(ActionEvent event) {
        Stage stage1 = new Stage();
        try {
            stage1.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/Doctor_Register.fxml"))));
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load view: " + e.getMessage()).show();

        }
        stage1.show();
    }

    @FXML
    void btnReceptionistAction(ActionEvent event) {
        Stage stage1 = new Stage();
        try {
            stage1.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/Patient_Register.fxml"))));
            stage1.show();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load view: " + e.getMessage()).show();

        }

    }

}
