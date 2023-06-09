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

public class AdminClassroomSupervisorController implements Initializable {


    @FXML
    public TextField tfName;
    @FXML
    public TextField tfContactNumber;
    @FXML
    public TextField tfObjectMaintained;
    @FXML
    public Label errorLabel;
    @FXML
    public TextField tfFilterIithId;
    @FXML
    public TextField tfFilterName;
    @FXML
    public TextField tfFilterContactNumber;
    @FXML
    public TextField tfFilterAppointedBy;
    @FXML
    public TextField tfFilterObjectMaintained;
    @FXML
    public Label errorFilterLabel;
    @FXML
    public TableView<Supervisor> tvOMSLabSVInfo = new TableView();
    @FXML
    public TableColumn<Supervisor,String> colId;
    @FXML
    public TableColumn<Supervisor,String> colName;
    @FXML
    public TableColumn<Supervisor,String> colMail;
    @FXML
    public TableColumn<Supervisor,String> colContactNumber;
    @FXML
    public TableColumn<Supervisor,String> colField;
    @FXML
    public TableColumn<Supervisor,String> colAppointedBy;
    @FXML
    public TableColumn<Supervisor,String> colObjectMaintained;
    @FXML
    public Label errorTypeError;


    Supervisor sv = new Supervisor() ;

    public static String iithId ;
    public void setIithId(String iithId) {
        OMSLibrarySVTableController.iithId = iithId;
    }

    private Stage stage;
    private Scene scene;
    private Parent root;


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
        showRecords();
        errorLabel.setVisible(false);
        errorTypeError.setVisible(false);
        errorFilterLabel.setVisible(false);
    }

    @FXML
    void updateRecord(ActionEvent event) throws SQLException {
        errorLabel.setVisible(false);
        errorTypeError.setVisible(false);
        errorFilterLabel.setVisible(false);
        if(tfName.getText().trim().length() !=0 && tfContactNumber.getText().trim().length()!=0 && tfObjectMaintained.getText().trim().toLowerCase().length()!=0 ) {
            errorLabel.setVisible(false);
            if(tfObjectMaintained.getText().trim().equals(sv.getObjectClassUnderSupervision().trim())){
                errorTypeError.setVisible(false);
                String query="update classsupervisortable set name=? , contactnumber=? where name=? and contactnumber=? and objectmaintained=?" ;
                Connection con = getConnection();
                PreparedStatement st=con.prepareStatement(query) ;
                st.setString(1,tfName.getText());
                st.setString(2,tfContactNumber.getText());
                st.setString(3,sv.getName());
                st.setString(4,sv.getContactNumber());
                st.setString(5,sv.getObjectClassUnderSupervision());
                int roo = st.executeUpdate() ;
                if(roo > 0) System.out.println("Update successfull");
                else System.out.println("Update unsucessfull");
                st.close();
                con.close();
                showRecords();
                clearTextFields();
            }else {
                errorTypeError.setVisible(true);
            }

        }else{
            errorLabel.setVisible(true);
        }
    }

    @FXML
    void handleMouseAction(MouseEvent event) {
        errorLabel.setVisible(false);
        errorTypeError.setVisible(false);
        errorFilterLabel.setVisible(false);
        Supervisor supervisor = tvOMSLabSVInfo.getSelectionModel().getSelectedItem() ;
        sv.setName(supervisor.getName());
        sv.setContactNumber(supervisor.getContactNumber());
        sv.setObjectClassUnderSupervision(supervisor.getObjectClassUnderSupervision());
        tfContactNumber.setText(supervisor.getContactNumber());
        tfName.setText(supervisor.getName());
        tfObjectMaintained.setText(supervisor.getObjectClassUnderSupervision());
    }


    private ObservableList<Supervisor> getSVList() throws SQLException {
        ObservableList<Supervisor> supervisors = FXCollections.observableArrayList();
        Connection con = getConnection() ;
        String query = "SELECT * FROM classsupervisortable" ;
        try{
            Statement st = con.createStatement();
            ResultSet set = st.executeQuery(query) ;
            while(set.next()){
                Supervisor supervisor = new Supervisor();
                supervisor.setId(set.getInt(1)) ;
                supervisor.setAppointedBy(set.getString(6));
                supervisor.setContactNumber(set.getString(9));
                supervisor.setFieldofoperations(set.getString(4));
                supervisor.setName(set.getString(3));
                supervisor.setIithId(set.getString(2));
                supervisor.setIithMailId(set.getString(7));
                supervisor.setObjectClassUnderSupervision(set.getString(5));
                supervisors.add(supervisor) ;
            }
            System.out.println("List returned successfully");
            st.close();
            con.close();
            return supervisors ;
        }catch(SQLException e){
            System.out.println("Unable to create Statement object (Exception in Crud2Controller --> getBooksList() method) "+e.getMessage());
            con.close();
            return null ;
        }

    }


    public void showRecords() {
        try{
            ObservableList<Supervisor> list = getSVList() ;
            colId.setCellValueFactory(new PropertyValueFactory<Supervisor , String>("iithId"));
            colName.setCellValueFactory(new PropertyValueFactory<Supervisor , String>("name"));
            colMail.setCellValueFactory(new PropertyValueFactory<Supervisor , String>("iithMailId"));
            colContactNumber.setCellValueFactory(new PropertyValueFactory<Supervisor , String>("contactNumber"));
            colAppointedBy.setCellValueFactory(new PropertyValueFactory<Supervisor , String>("appointedBy"));
            colField.setCellValueFactory(new PropertyValueFactory<Supervisor,String>("fieldofoperations")); ;
            colObjectMaintained.setCellValueFactory(new PropertyValueFactory<Supervisor , String>("objectClassUnderSupervision"));
            tvOMSLabSVInfo.setItems(list);
            tfFilterContactNumber.setText("");
            tfFilterIithId.setText("");
            tfFilterObjectMaintained.setText("");
            tfFilterAppointedBy.setText("");
            tfFilterName.setText("");
        }catch (SQLException e){
            System.out.println("Exception in Crud2Controller --> showBooks() method "+e.getMessage()); //EXCEPTION HANDLING
        }
    }



    public void filter(ActionEvent event) {
        errorLabel.setVisible(false);
        errorTypeError.setVisible(false);
        errorFilterLabel.setVisible(false);
        if(tfFilterAppointedBy.getText().trim().length()>0 || tfFilterName.getText().trim().length()>0||tfFilterContactNumber.getText().trim().length()>0||tfFilterIithId.getText().trim().length()>0 ||tfFilterObjectMaintained.getText().trim().length()>0){
            String appointedBy = tfFilterAppointedBy.getText().trim() ;
            String name = tfFilterName.getText().trim() ;
            String contactNumber = tfFilterContactNumber.getText().trim() ;
            String iithId = tfFilterIithId.getText().trim() ;
            String objectMaintained = tfFilterObjectMaintained.getText().trim().toLowerCase();
            try{
                if(appointedBy.length() == 0 ) appointedBy="%%" ;
                if(iithId.length() == 0 ) iithId="%%" ;
                if(name.length() == 0 ) name="%%" ;
                if(contactNumber.length()==0) contactNumber="%%" ;
                if(objectMaintained.length()==0) objectMaintained="%%" ;
                Connection con = getConnection() ;
                ObservableList<Supervisor> supervisors = FXCollections.observableArrayList();
                String query = "SELECT * FROM classsupervisortable where iith_id LIKE ? and name LIKE ? and contactnumber LIKE ? and appointedby LIKE ? and objectmaintained LIKE ? order by iith_id ";
                PreparedStatement st = con.prepareStatement(query);
                st.setString(1,iithId);
                st.setString(2,name);
                st.setString(3,contactNumber);
                st.setString(4,appointedBy);
                st.setString(5,objectMaintained);
                ResultSet rs = st.executeQuery() ;
                while(rs.next()){
                    Supervisor supervisor = new Supervisor();
                    supervisor.setId(rs.getInt(1)) ;
                    supervisor.setAppointedBy(rs.getString(6));
                    supervisor.setContactNumber(rs.getString(9));
                    supervisor.setFieldofoperations(rs.getString(4));
                    supervisor.setName(rs.getString(3));
                    supervisor.setIithId(rs.getString(2));
                    supervisor.setIithMailId(rs.getString(7));
                    supervisor.setObjectClassUnderSupervision(rs.getString(5));
                    supervisors.add(supervisor) ;
                }
                colId.setCellValueFactory(new PropertyValueFactory<Supervisor , String>("iithId"));
                colName.setCellValueFactory(new PropertyValueFactory<Supervisor , String>("name"));
                colMail.setCellValueFactory(new PropertyValueFactory<Supervisor , String>("iithMailId"));
                colContactNumber.setCellValueFactory(new PropertyValueFactory<Supervisor , String>("contactNumber"));
                colAppointedBy.setCellValueFactory(new PropertyValueFactory<Supervisor , String>("appointedBy"));
                colField.setCellValueFactory(new PropertyValueFactory<Supervisor,String>("fieldofoperations")); ;
                colObjectMaintained.setCellValueFactory(new PropertyValueFactory<Supervisor , String>("objectClassUnderSupervision"));
                tvOMSLabSVInfo.setItems(supervisors);
                st.close();
                con.close();
            }catch (SQLException exe){
                System.out.println("Unable to retrieve the connection (in AdminModeSellerInfoController.java filterList() method) : "+exe.getMessage());
            }
        }else{
            errorFilterLabel.setVisible(true);
        }
    }

    public void clearTextFields() {
        tfContactNumber.setText("");
        tfName.setText("");
        tfObjectMaintained.setText("");
    }

    public void OnBack(ActionEvent event) throws IOException{
        errorLabel.setVisible(false);
        errorTypeError.setVisible(false);
        errorFilterLabel.setVisible(false);
        root = FXMLLoader.load(requireNonNull(getClass().getResource("AdminClassroomInfo.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
