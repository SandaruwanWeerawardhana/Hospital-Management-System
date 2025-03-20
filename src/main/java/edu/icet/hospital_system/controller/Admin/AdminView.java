package edu.icet.hospital_system.controller.Admin;

import edu.icet.hospital_system.dto.Admin;
import edu.icet.hospital_system.service.ServiceFactory;
import edu.icet.hospital_system.service.custom.AdminService;
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
import java.util.List;
import java.util.ResourceBundle;

public class AdminView implements Initializable {

    @FXML
    private AnchorPane DashboardLoadAnchorpane;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableView<Admin> tlbAdmin;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AdminService adminService = ServiceFactory.getInstance().getServiceType(ServiceType.ADMIN);
        List<Admin> adminList = adminService.getAll();
        ObservableList<Admin> observableList = FXCollections.observableArrayList(adminList);

        tlbAdmin.setItems(observableList);

        colID.setCellValueFactory(new PropertyValueFactory<>("admin_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact_details"));
    }


    public void btnSingUpAction(ActionEvent actionEvent) {
    }

    public void BackAction(ActionEvent actionEvent) {
    }
}
