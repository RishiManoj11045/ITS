package com.example.page12;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;

import static java.util.Objects.requireNonNull;


public class AdminModeController  {


    private Stage stage;
    private Scene scene;
    private Parent root;

    public static String iithId ;
    public void setIithId(String str){
        this.iithId = str ;
    }


    public void myLogout(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You're about to logout");
        alert.setContentText("Do you want really want to exit");
        if(alert.showAndWait().get() == ButtonType.OK){
            root = FXMLLoader.load(requireNonNull(getClass().getResource("LoginPage.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void OnbtnLab(ActionEvent event) throws IOException {
        root = FXMLLoader.load(requireNonNull(getClass().getResource("AdminLabInfo.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        AdminLabButtonsController adminLabButtonsController = new AdminLabButtonsController();
        adminLabButtonsController.setIithId(iithId);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void OnbtnLibrary(ActionEvent event) throws IOException {
        root = FXMLLoader.load(requireNonNull(getClass().getResource("AdminLibraryInfo.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        AdminLibraryButtonsController adminLibraryButtonsController = new AdminLibraryButtonsController();
        adminLibraryButtonsController.setIithId(iithId);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void OnbtnClassroom(ActionEvent event) throws IOException {
        root = FXMLLoader.load(requireNonNull(getClass().getResource("AdminClassroomInfo.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        AdminClassroomButtonsController adminClassroomButtonsController = new AdminClassroomButtonsController();
        adminClassroomButtonsController.setIithId(iithId);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void OnbtnOffice(ActionEvent event) throws IOException {
        root = FXMLLoader.load(requireNonNull(getClass().getResource("AdminOfficeInfo.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        AdminOfficeButtonsController adminOfficeButtonsController = new AdminOfficeButtonsController();
        adminOfficeButtonsController.setIithId(iithId);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void adminsellerinfoview(ActionEvent event) throws IOException{
        root = FXMLLoader.load(requireNonNull(getClass().getResource("AdminModeSellerInfo.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void changePassword(ActionEvent event) throws IOException {
        root = FXMLLoader.load(requireNonNull(getClass().getResource("AdminChangePassword.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        AdminChangePasswordPageController adminChangePasswordPageController=new AdminChangePasswordPageController();
        adminChangePasswordPageController.setIithId(iithId);
        scene = new Scene(root);
        System.out.println(iithId);
        stage.setScene(scene);
        stage.show();
    }
}
