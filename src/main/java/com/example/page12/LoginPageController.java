package com.example.page12;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static java.util.Objects.requireNonNull;


public class LoginPageController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField tfUsername;
    @FXML
    private TextField pfPassword;
    @FXML
    private CheckBox showPassword;
    @FXML
    private Label hiddenPassword;
    @FXML
    private Label IncorrectMessage;




    private String iithId ; //This is used for sending login info to other pages
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        hiddenPassword.setVisible(false);
        IncorrectMessage.setVisible(false);
    }


    private Connection getConnection() throws SQLException{
        String jdbcURL = "jdbc:mysql://localhost:3306/project_its";
        String username = "root";
        String password = "krmrhzb12";
        try{
            Connection con = DriverManager.getConnection(jdbcURL,username,password) ;
            System.out.println("Connected successfully returning the connection object");
            return con ;
        }catch(SQLException e){
            System.out.println("Unable to generate connection with the database : "+e.getMessage());
            return null ;
        }
    }


    public void Login(ActionEvent event) throws IOException {
        IncorrectMessage.setVisible(false);
        String username = tfUsername.getText().trim().toUpperCase();
        String password = pfPassword.getText().trim();
        iithId = username ; //Now this is sent to other pages
        try{
            Connection con = getConnection() ;
            if(username.contains("AD")){
                String query = "SELECT * from admintable2 Where iith_id=? and password=? " ;
                PreparedStatement st=con.prepareStatement(query);
                System.out.println("In admin condition");
                st.setString(1,username.toUpperCase());
                st.setString(2,password);
                   ResultSet rs =  st.executeQuery();
                    if(rs.next()){
                        System.out.println("The user exists forwarding to next page");
                        root = FXMLLoader.load(requireNonNull(getClass().getResource("AdminMode.fxml")));
                        AdminModeController adminModeController = new AdminModeController();
                        adminModeController.setIithId(iithId);
                        st.close();
                        con.close();
                    }
                    else {
                        IncorrectMessage.setVisible(true);
                        showPassword.setSelected(false);
                        hiddenPassword.setText("");
                        System.out.println("User doesn't exist Please check the entered credentials");
                        st.close();
                        con.close();
                        return ;
                    }
            } else if (username.contains("OM")){
                String rootScene = "" ;
                String query = "" ;
                if(username.contains("OMLR")){
                   rootScene = "OMSLibraryMode.fxml" ;
                   query = "SELECT * from libraryoperationsmanagertable Where iith_id=? and password=? " ;
                    OMSLibraryInfoController omsLibraryInfoController = new OMSLibraryInfoController();
                    omsLibraryInfoController.setIithId(iithId);
                }else if(username.contains("OMCL")){
                    rootScene = "OMSClassroomMode.fxml" ;
                    query = "SELECT * from classoperationsmanagertable Where iith_id=? and password=? " ;
                    OMSClassroomInfo omsClassroomInfo = new OMSClassroomInfo();
                    omsClassroomInfo.setIithId(iithId);
                }else if(username.contains("OMOF")){
                    rootScene = "OMSOfficeMode.fxml" ;
                    query = "SELECT * from officeoperationsmanagertable Where iith_id=? and password=? " ;
                    OMSOfficeInfoController omsOfficeInfoController = new OMSOfficeInfoController();
                    omsOfficeInfoController.setIithId(iithId);
                }else{
                    rootScene = "OMSLabMode.fxml" ;
                    query = "SELECT * from laboperationsmanagertable Where iith_id=? and password=? " ;
                    OMSLabInfoController omsLabInfoController = new OMSLabInfoController();
                    omsLabInfoController.setIithId(iithId);
                }
                PreparedStatement st=con.prepareStatement(query);
                st.setString(1,username.toUpperCase());
                st.setString(2,password);
                ResultSet rs =  st.executeQuery();
                if(rs.next()){
                    System.out.println("The user exists forwarding to next page");
                    root = FXMLLoader.load(requireNonNull(getClass().getResource(rootScene)));
                    st.close();
                    con.close();

                }
                else {
                    IncorrectMessage.setVisible(true);
                    showPassword.setSelected(false);
                    hiddenPassword.setText("");
                    System.out.println("User doesn't exist Please check the entered credentials");
                    st.close();
                    con.close();
                    return ;
                }
            } else if (username.contains("SV")){
                String query = null  ;
                String rootScene= null  ;
                if(username.contains("SVLR")){
                    rootScene = "SVLibraryMode.fxml" ;
                    query = "SELECT * from librarysupervisortable Where iith_id=? and password=? " ;
                    SVLibraryController svLibraryController =  new SVLibraryController();
                    svLibraryController.setIithId(iithId);

                }else if(username.contains("SVCL")){
                    rootScene = "SVClassroomMode.fxml" ;
                    query = "SELECT * from classsupervisortable Where iith_id=? and password=? " ;
                    SVClassController svClassController =  new SVClassController() ;
                    svClassController.setIithId(iithId);
                }else if(username.contains("SVOF")){
                    rootScene = "SVOfficeMode.fxml" ;
                    query = "SELECT * from officesupervisortable Where iith_id=? and password=? " ;
                    SVOfficeController svOfficeController = new SVOfficeController();
                    svOfficeController.setIithId(iithId);
                }else{
                    rootScene = "SVLabMode.fxml" ;
                    query = "SELECT * from labsupervisortable Where iith_id=? and password=? " ;
                    SVLabController svLabController = new SVLabController() ;
                    svLabController.setIithId(iithId);
                }
                PreparedStatement st=con.prepareStatement(query);
                st.setString(1,username);
                st.setString(2,password);
                ResultSet rs =  st.executeQuery();
                if(rs.next()){
                    System.out.println("The user exists forwarding to next page");
                    root = FXMLLoader.load(requireNonNull(getClass().getResource(rootScene)));
                    st.close();
                    con.close();
                }
                else {
                    IncorrectMessage.setVisible(true);
                    showPassword.setSelected(false);
                    hiddenPassword.setText("");
                    System.out.println("User doesn't exist Please check the entered credentials");
                    st.close();
                    con.close();
                    return ;
                }
            } else if (username.contains("CS")){
                String query = "SELECT * from faculty Where iith_id=? and password=? " ;
                PreparedStatement st=con.prepareStatement(query);
                st.setString(1,username);
                st.setString(2,password);
                ResultSet rs =  st.executeQuery();
                if(rs.next()){
                    System.out.println("The user exists forwarding to next page");
                    root = FXMLLoader.load(requireNonNull(getClass().getResource("FacMode.fxml")));
                    FacultyModeController facultyModeController = new FacultyModeController();
                    facultyModeController.setIithId(iithId);
                    st.close();
                    con.close();
                }
                else {
                    IncorrectMessage.setVisible(true);
                    showPassword.setSelected(false);
                    hiddenPassword.setText("");
                    System.out.println("User doesn't exist Please check the entered credentials");
                    st.close();
                    con.close();
                    return ;
                }
            } else {
                IncorrectMessage.setVisible(true);
                hiddenPassword.setText("");
                showPassword.setSelected(false);
                System.out.println("User doesn't exist Please check the entered credentials");
                con.close();
                return ;
            }
            stage = ((Stage)((Node)event.getSource()).getScene().getWindow());
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void changeVisibility(ActionEvent event){
        String password = pfPassword.getText();
        if(showPassword.isSelected()){
            hiddenPassword.setVisible(true);
            hiddenPassword.setText(password);
            return ;
        }
        hiddenPassword.setVisible(false);
    }

    public void changeVis(KeyEvent event){
        String pwd = pfPassword.getText();
        if(event.getCode() == KeyCode.ENTER) {
            hiddenPassword.setText(pwd);
        }
    }

}
