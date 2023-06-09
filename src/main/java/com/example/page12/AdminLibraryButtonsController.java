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

public class AdminLibraryButtonsController {
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
            alert.setContentText("Do you really want to exit?");
            if(alert.showAndWait().get() == ButtonType.OK){
                root = FXMLLoader.load(requireNonNull(getClass().getResource("LoginPage.fxml")));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }

        public void OnBack(ActionEvent event) throws IOException {
            root = FXMLLoader.load(requireNonNull(getClass().getResource("AdminMode.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

        public void ObjListView(ActionEvent event) throws IOException {
            root = FXMLLoader.load(requireNonNull(getClass().getResource("AdminLibraryObjectsTable.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

        public void SVTableView(ActionEvent event) throws IOException {
            root = FXMLLoader.load(requireNonNull(getClass().getResource("AdminLibrarySupervisorMode.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }


        public void OMSTableView(ActionEvent event) throws IOException {
            root = FXMLLoader.load(requireNonNull(getClass().getResource("AdminLibraryOperationsManagerMode.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            AdminLibraryOperationsManagerController adminLibraryOperationsManagerController = new AdminLibraryOperationsManagerController();
            adminLibraryOperationsManagerController.setIithId(iithId);
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

