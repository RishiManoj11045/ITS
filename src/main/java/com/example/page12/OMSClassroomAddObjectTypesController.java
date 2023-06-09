package com.example.page12;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static java.util.Objects.requireNonNull;

public class OMSClassroomAddObjectTypesController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    public Label errorLabel;
    @FXML
    public TextField tfObjectType;
    @FXML
    public Label errorTypeexist;
    @FXML
    public Label errorFailedToDelete;
    @FXML
    public TableView tvObjectTypeList;
    @FXML
    public TableColumn<ObjectType, String> colObjectType;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errorFailedToDelete.setVisible(false);
        errorTypeexist.setVisible(false);
        errorLabel.setVisible(false);
        try {
            showRecords();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
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

    private ObservableList<ObjectType> getRecords() throws SQLException {
        ObservableList<ObjectType> list = FXCollections.observableArrayList() ;
            Connection con = getConnection() ;
            String query ="select objecttype from classroomobjectclasses" ;
            try{
            Statement st = con.createStatement() ;
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                ObjectType objectType = new ObjectType();
                objectType.setType(rs.getString(1));
                list.add(objectType);
            }
            return list ;
        }catch(SQLException e){
                System.out.println(e.getMessage());
                return null ;
            }
    }

    public void showRecords() throws SQLException {
        errorFailedToDelete.setVisible(false);
        errorTypeexist.setVisible(false);
        errorLabel.setVisible(false);
        try{
            ObservableList<ObjectType> list = getRecords() ;
            colObjectType.setCellValueFactory(new PropertyValueFactory<ObjectType , String>("type"));
            tvObjectTypeList.setItems(list);
            tfObjectType.setText("");
        }catch (SQLException e){
            System.out.println("Exception in Crud2Controller --> showBooks() method "+e.getMessage()); //EXCEPTION HANDLING
        }
    }


    public void addObjectType(ActionEvent event) throws SQLException {
        errorFailedToDelete.setVisible(false);
        errorTypeexist.setVisible(false);
        errorLabel.setVisible(false);
        if(tfObjectType.getText().trim().length()>0){
            try{
                errorLabel.setVisible(false);
                Connection con = getConnection() ;
                String query = "create table classroom"+tfObjectType.getText().trim().toLowerCase()+"(" +
                        "id int primary key auto_increment ," +
                        "iith_id varchar(30) ," +
                        "type varchar(30) ," +
                        "company varchar(40) ," +
                        "company_id varchar(40)," +
                        "price varchar(10)," +
                        "seller varchar(20)," +
                        "buyer varchar(20)," +
                        "supervisor varchar(20)," +
                        "currentlocation varchar(20)," +
                        "status varchar(15)," +
                        "dateofbuying varchar(15)," +
                        "fieldofobject varchar(20)" +
                        ")" ;
                Statement st = con.createStatement();
                st.executeUpdate(query) ;
                String query2 = "Insert into classroomobjectclasses(objecttype) values(?) " ;
                PreparedStatement stu = con.prepareStatement(query2);
                stu.setString(1,tfObjectType.getText().trim().toLowerCase());
                stu.executeUpdate();
                stu.close();
                con.close();
                showRecords();
            }catch (SQLException e){
                showRecords();
                errorTypeexist.setVisible(true);
                System.out.println(e.getMessage());
            }
        }else {
            errorLabel.setVisible(true);
        }
    }

    public void deleteObjectType(ActionEvent event) throws SQLException {
        errorFailedToDelete.setVisible(false);
        errorTypeexist.setVisible(false);
        errorLabel.setVisible(false);
        if(tfObjectType.getText().trim().length()>0){
           try{
               errorFailedToDelete.setVisible(false);
               errorLabel.setVisible(false);
               Connection con = getConnection() ;
               String query = "drop table classroom"+tfObjectType.getText().trim().toLowerCase() ;
              Statement st = con.createStatement() ;
              st.executeUpdate(query);
              String querySV = "update classsupervisortable set objectmaintained='null' where objectmaintained=? " ;
              PreparedStatement ps= con.prepareStatement(querySV) ;
              ps.setString(1,tfObjectType.getText().trim().toLowerCase());
              int roo = ps.executeUpdate() ;
              if(roo == 0) System.out.println("Nothing to Delete");
              String query3 = "Delete from classroomobjectclasses where objecttype=?" ;
              PreparedStatement ps2 = con.prepareStatement(query3);
              ps2.setString(1,tfObjectType.getText().trim().toLowerCase());
              ps2.executeUpdate() ;
              ps2.close();
              tfObjectType.setText("");
              ps.close();
              st.close();
              con.close();
              showRecords();
           }catch (SQLException e){
               errorFailedToDelete.setVisible(true);
               System.out.println(e.getMessage());
               showRecords();
           }
        }else{
            errorLabel.setVisible(true);
        }
    }


    @FXML
    void handleMouseAction(MouseEvent event) {
        errorFailedToDelete.setVisible(false);
        errorTypeexist.setVisible(false);
        errorLabel.setVisible(false);
        ObjectType objectType = (ObjectType) tvObjectTypeList.getSelectionModel().getSelectedItem();
        tfObjectType.setText(objectType.getType());
    }

    public void back(ActionEvent event) throws IOException {
        root = FXMLLoader.load(requireNonNull(getClass().getResource("OMSClassroomMode.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void clear(ActionEvent event) {
        errorFailedToDelete.setVisible(false);
        errorTypeexist.setVisible(false);
        errorLabel.setVisible(false);
        tfObjectType.setText("");
    }
}
