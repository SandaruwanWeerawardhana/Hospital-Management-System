
package edu.icet.hospital_system.controller.Register;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterSelectionFormController {

    public AnchorPane RegisterSelectionAnchorePane;
    @FXML
    private JFXButton btnAdmin;

    @FXML
    private JFXButton btnDoctor;

    @FXML
    private JFXButton btnReceptionist;

    @FXML
    void btnAdminAction(ActionEvent event) {
        try {
            RegisterSelectionAnchorePane.getChildren().clear();
            Object load = FXMLLoader.load(getClass().getResource("/View/Admin_Register.fxml"));
            RegisterSelectionAnchorePane.getChildren().add((Node) load);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load view: " + e.getMessage()).show();
        }

    }

    @FXML
    void btnDoctorAction(ActionEvent event) {

        try {
            RegisterSelectionAnchorePane.getChildren().clear();
            Object load = FXMLLoader.load(getClass().getResource("/View/Doctor_Register.fxml"));
            RegisterSelectionAnchorePane.getChildren().add((Node) load);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load view: " + e.getMessage()).show();
        }

    }

    @FXML
    void btnReceptionistAction(ActionEvent event) {

        try {
            RegisterSelectionAnchorePane.getChildren().clear();
            Object load = FXMLLoader.load(getClass().getResource("/View/Patient_Register.fxml"));
            RegisterSelectionAnchorePane.getChildren().add((Node) load);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load view: " + e.getMessage()).show();
        }

    }

    public void BackAction(ActionEvent actionEvent) {
        try {
            RegisterSelectionAnchorePane.getChildren().clear();
            Object load = FXMLLoader.load(getClass().getResource("/View/Login.fxml"));
            RegisterSelectionAnchorePane.getChildren().add((Node) load);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load view: " + e.getMessage()).show();
        }
    }
}
