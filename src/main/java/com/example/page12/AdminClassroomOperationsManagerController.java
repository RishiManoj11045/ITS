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

public class AdminClassroomOperationsManagerController implements Initializable {
    @FXML
    public TextField tfFilterIithId;
    @FXML
    public TextField tfFilterName;
    @FXML
    public TextField tfFilterContactNumber;
    @FXML
    public TextField tfContactNumber;
    @FXML
    public TextField tfFilterAppointedBy;
    @FXML
    private TextField tfName;
    @FXML
    public Label filterErrorLabel;
    @FXML
    public Label errorLabel;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public static String iithId ;
    public void setIithId(String str){
        this.iithId = str ;
    }

    OperationsManager op = new OperationsManager();

    @FXML
    private TableView<OperationsManager> tvOMSInfo = new TableView();
    @FXML
    private TableColumn<OperationsManager, String> colId;
    @FXML
    private TableColumn<OperationsManager, String> colName;
    @FXML
    private TableColumn<OperationsManager, String> colMail;
    @FXML
    private TableColumn<OperationsManager, String> colContactNumber;
    @FXML
    private TableColumn<OperationsManager, String> colAppointedBy ;


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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            showRecords();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        filterErrorLabel.setVisible(false);
        errorLabel.setVisible(false);
    }

    @FXML
    void deleteRecord(ActionEvent event) throws SQLException {
        filterErrorLabel.setVisible(false);
        errorLabel.setVisible(false);
        if(tfName.getText().trim().length() !=0 && tfContactNumber.getText().trim().length()!=0) {
            errorLabel.setVisible(false);
            String query="delete from classoperationsmanagertable where name=? and contactnumber=? " ;
            Connection con = getConnection();
            PreparedStatement st=con.prepareStatement(query) ;
            st.setString(1,tfName.getText());
            st.setString(2,tfContactNumber.getText());
            int roo = st.executeUpdate() ;
            if(roo > 0) System.out.println("deletion successfull");
            else System.out.println("deletion unsucessfull");
            st.close();
            con.close();
            showRecords();
            clearTextFields();
        }else{
            errorLabel.setVisible(true);
        }

    }

    @FXML
    void insertRecord(ActionEvent event) throws SQLException {
        filterErrorLabel.setVisible(false);
        errorLabel.setVisible(false);
        if(tfName.getText().trim().length()>0&&tfContactNumber.getText().trim().length()>0){
            errorLabel.setVisible(false);
            Connection con = getConnection() ;
            String addQuery = "INSERT INTO classoperationsmanagertable(name,fieldofoperations,appointedby,contactnumber,password) values(?,?,?,?,?)" ;
            PreparedStatement st = con.prepareStatement(addQuery);
            st.setString(1,tfName.getText());
            st.setString(2,"classroom");
            st.setString(3,iithId);
            st.setString(4,tfContactNumber.getText());
            st.setString(5,"1234");
            st.executeUpdate();
            String queryForId = "SELECT id from classoperationsmanagertable ORDER BY id DESC LIMIT 1" ;
            ResultSet resultSet = st.executeQuery(queryForId) ;
            String rollnum = null ;
            String mail = null ;
            int id = 0;
            while(resultSet.next()){
                id=resultSet.getInt(1) ;
                rollnum = "OMCL"+id;
                mail=rollnum.toLowerCase() +"@iith.ac.in" ;
                System.out.println(rollnum+mail);
            }
            String queryUpdate = "Update classoperationsmanagertable SET iith_id=?,iith_mailid=? Where id =? ";
            st=con.prepareStatement(queryUpdate) ;
            st.setString(1,rollnum);
            st.setString(2,mail);
            st.setInt(3,id);
            st.executeUpdate();
            st.close();
            con.close();
            showRecords();
            clearTextFields();
        }else{
            errorLabel.setVisible(true);
        }

    }

    @FXML
    void updateRecord(ActionEvent event) throws SQLException {
        filterErrorLabel.setVisible(false);
        errorLabel.setVisible(false);
        if(tfName.getText().trim().length() !=0 && tfContactNumber.getText().trim().length()!=0) {
            errorLabel.setVisible(false);
            String query="update classoperationsmanagertable set name=? , contactnumber=? where name=? and contactnumber=?" ;
            Connection con = getConnection();
            PreparedStatement st=con.prepareStatement(query) ;
            st.setString(1,tfName.getText());
            st.setString(2,tfContactNumber.getText());
            st.setString(3,op.getName());
            st.setString(4,op.getContactNumber());
            int roo = st.executeUpdate() ;
            if(roo > 0) System.out.println("Update successfull");
            else System.out.println("Update unsucessfull");
            st.close();
            con.close();
            showRecords();
            clearTextFields();
        }else{
            errorLabel.setVisible(true);
        }
    }

    public void filter(ActionEvent event) {
        filterErrorLabel.setVisible(false);
        errorLabel.setVisible(false);
        if(tfFilterAppointedBy.getText().trim().length()>0 || tfFilterName.getText().trim().length()>0||tfFilterContactNumber.getText().trim().length()>0||tfFilterIithId.getText().trim().length()>0){
            filterErrorLabel.setVisible(false);
            String appointedBy = tfFilterAppointedBy.getText().trim() ;
            String name = tfFilterName.getText().trim() ;
            String contactNumber = tfFilterContactNumber.getText().trim() ;
            String iithId = tfFilterIithId.getText().trim() ;
            try{
                if(appointedBy.length() == 0 ) appointedBy="%%" ;
                if(iithId.length() == 0 ) iithId="%%" ;
                if(name.length() == 0 ) name="%%" ;
                if(contactNumber.length()==0) contactNumber="%%" ;
                Connection con = getConnection() ;
                ObservableList<OperationsManager> operationsManagers = FXCollections.observableArrayList();
                String query = "SELECT * FROM classoperationsmanagertable where iith_id LIKE ? and name LIKE ? and contactnumber LIKE ? and appointedby LIKE ? order by iith_id";
                PreparedStatement st = con.prepareStatement(query);
                st.setString(1,iithId);
                st.setString(2,name);
                st.setString(3,contactNumber);
                st.setString(4,appointedBy);
                ResultSet rs = st.executeQuery() ;
                while(rs.next()){
                    OperationsManager operationsManager = new OperationsManager();
                    operationsManager.setId(rs.getInt(1)) ;
                    operationsManager.setAppointedBy(rs.getString(5));
                    operationsManager.setContactNumber(rs.getString(8));
                    operationsManager.setFieldOfSupervision(rs.getString(4));
                    operationsManager.setName(rs.getString(3));
                    operationsManager.setIithId(rs.getString(2));
                    operationsManager.setIithMailId(rs.getString(6));
                    operationsManagers.add(operationsManager) ;
                }
                colId.setCellValueFactory(new PropertyValueFactory<OperationsManager , String>("iithId"));
                colName.setCellValueFactory(new PropertyValueFactory<OperationsManager , String>("name"));
                colMail.setCellValueFactory(new PropertyValueFactory<OperationsManager , String>("iithMailId"));
                colContactNumber.setCellValueFactory(new PropertyValueFactory<OperationsManager , String>("contactNumber"));
                colAppointedBy.setCellValueFactory(new PropertyValueFactory<OperationsManager , String>("appointedBy"));
                tvOMSInfo.setItems(operationsManagers);
                st.close();
                con.close();
            }catch (SQLException exe){
                System.out.println("Unable to retrieve the connection (in AdminModeSellerInfoController.java filterList() method) : "+exe.getMessage());
            }
        }else{
            filterErrorLabel.setVisible(true);
        }
    }

    private ObservableList<OperationsManager> getOMList() throws SQLException {
        ObservableList<OperationsManager> operationsManagers = FXCollections.observableArrayList();
        Connection con = getConnection() ;
        String query = "SELECT * FROM classoperationsmanagertable" ;
        try{
            Statement st = con.createStatement();
            ResultSet set = st.executeQuery(query) ;
            while(set.next()){
                OperationsManager operationsManager = new OperationsManager();
                operationsManager.setId(set.getInt(1)) ;
                operationsManager.setAppointedBy(set.getString(5));
                operationsManager.setContactNumber(set.getString(8));
                operationsManager.setFieldOfSupervision(set.getString(4));
                operationsManager.setName(set.getString(3));
                operationsManager.setIithId(set.getString(2));
                operationsManager.setIithMailId(set.getString(6));
                operationsManagers.add(operationsManager) ;
            }
            System.out.println("List returned successfully");
            st.close();
            con.close();
            return operationsManagers ;
        }catch(SQLException e){
            System.out.println("Unable to create Statement object (Exception in Crud2Controller --> getBooksList() method) "+e.getMessage());
            con.close();
            return null ;
        }

    }


    @FXML
    public void showRecords() throws SQLException {
        try{
            ObservableList<OperationsManager> list = getOMList() ;
            colId.setCellValueFactory(new PropertyValueFactory<OperationsManager , String>("iithId"));
            colName.setCellValueFactory(new PropertyValueFactory<OperationsManager , String>("name"));
            colMail.setCellValueFactory(new PropertyValueFactory<OperationsManager , String>("iithMailId"));
            colContactNumber.setCellValueFactory(new PropertyValueFactory<OperationsManager , String>("contactNumber"));
            colAppointedBy.setCellValueFactory(new PropertyValueFactory<OperationsManager , String>("appointedBy"));
            tvOMSInfo.setItems(list);
            tfFilterContactNumber.setText("");
            tfFilterIithId.setText("");
            tfFilterAppointedBy.setText("");
            tfFilterName.setText("");
        }catch (SQLException e){
            System.out.println("Exception in Crud2Controller --> showBooks() method "+e.getMessage()); //EXCEPTION HANDLING
        }
    }

    @FXML
    public void handleMouseAction(MouseEvent mouseEvent) {
        OperationsManager operationsManager = tvOMSInfo.getSelectionModel().getSelectedItem() ;
        op.setName(operationsManager.getName());
        op.setContactNumber(operationsManager.getContactNumber());
        tfContactNumber.setText(operationsManager.getContactNumber());
        tfName.setText(operationsManager.getName());
    }

    @FXML
    public void clearTextFields() {
        tfName.setText("");
        tfContactNumber.setText("");
    }

    public void OnBack(ActionEvent event) throws IOException{
        root = FXMLLoader.load(requireNonNull(getClass().getResource("AdminClassroomInfo.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
