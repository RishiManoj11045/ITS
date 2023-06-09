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

public class SVClassController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public static String iithId ;
    public void setIithId(String iithId) {
        SVClassController.iithId = iithId;
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

    public void ObjListView(ActionEvent event) throws IOException {
        System.out.println(iithId);
        root = FXMLLoader.load(requireNonNull(getClass().getResource("SVClassObjectList.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        SVClassObjectListController svClassObjectListController= new SVClassObjectListController();
        svClassObjectListController.setIithId(iithId);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void passwordChange(ActionEvent event) throws IOException {
        root = FXMLLoader.load(requireNonNull(getClass().getResource("SVChangePassword.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        SVChangePasswordPageController svChangePasswordPageController = new SVChangePasswordPageController();
        svChangePasswordPageController.setIithId(iithId);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
