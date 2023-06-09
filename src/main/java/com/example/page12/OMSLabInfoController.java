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

public class OMSLabInfoController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public static String iithId ;

    public void setIithId(String iithId) {
        OMSLabInfoController.iithId = iithId;
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
        root = FXMLLoader.load(requireNonNull(getClass().getResource("OMSLabObjectsList.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void SVTableView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(requireNonNull(getClass().getResource("OMSLabSVTable.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        OMSLabSVTableController omsLabSVTableController = new OMSLabSVTableController();
        omsLabSVTableController.setIithId(iithId);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void passwordChange(ActionEvent event) throws IOException {
        root = FXMLLoader.load(requireNonNull(getClass().getResource("OMChangePassword.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        OMChangePasswordPageController omChangePasswordPageController = new OMChangePasswordPageController();
        omChangePasswordPageController.setIithId(iithId);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void add(ActionEvent event) throws IOException {
        root = FXMLLoader.load(requireNonNull(getClass().getResource("OMSLabAddObjectType.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        OMChangePasswordPageController omChangePasswordPageController = new OMChangePasswordPageController();
        omChangePasswordPageController.setIithId(iithId);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
