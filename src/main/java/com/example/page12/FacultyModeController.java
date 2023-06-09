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

public class FacultyModeController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    private static String iithId ;

    public static String getIithId() {
        return iithId;
    }

    public void setIithId(String iithId) {
        this.iithId = iithId;
    }

    public void myLogout(ActionEvent event) throws IOException{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You're about to logout");
        alert.setContentText("Do you really want to exit?");
        if(alert.showAndWait().get() == ButtonType.OK){
            root = FXMLLoader.load(requireNonNull(getClass().getResource("LoginPage.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void FacOnLab(ActionEvent event)throws IOException{
        root = FXMLLoader.load(requireNonNull(getClass().getResource("FacultyLabTable.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void FacOnLibrary(ActionEvent event)throws IOException{
        root = FXMLLoader.load(requireNonNull(getClass().getResource("FacultyLibraryTable.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void FacOnClassroom(ActionEvent event)throws IOException{
        root = FXMLLoader.load(requireNonNull(getClass().getResource("FacultyClassroomTable.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void changePwd(ActionEvent event)throws IOException{
        root = FXMLLoader.load(requireNonNull(getClass().getResource("FacultyChangePassword.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        FacultyChangePasswordPageController facultyChangePasswordPageController=new FacultyChangePasswordPageController();
        facultyChangePasswordPageController.setIithId(iithId);
        stage.setScene(scene);
        stage.show();
    }
}
