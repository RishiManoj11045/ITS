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
import java.text.ParseException;
import java.util.ResourceBundle;

import static java.util.Objects.requireNonNull;

public class SVClassObjectListController implements Initializable {
    //Labels
    @FXML
    public Label filterError;
    @FXML
    public Label errorInfoFields;
    @FXML
    public Label objectTypeError;
    @FXML
    public Label errorSupervisorObject;
    @FXML
    public Label errorUpdateLabel;

    //table view
    @FXML
    public TableView<Object> tvObject;
    @FXML
    public TableColumn<Object, String> colType;

    @FXML
    public TableColumn<Object, String> colCompany;

    @FXML
    public TableColumn<Object, String> colPrice;

    @FXML
    public TableColumn<Object, String> colCompanyID;

    @FXML
    public TableColumn<Object, String> colSeller;

    @FXML
    public TableColumn<Object, String> colBuyer;

    @FXML
    public TableColumn<Object, String> colSupervisor;

    @FXML
    public TableColumn<Object, String> colCurrentLocation;

    @FXML
    public TableColumn<Object, String> colStatus;

    @FXML
    public TableColumn<Object, String> colDateOfBuying;

    @FXML
    public TableColumn<Object, String> colId;

    //text fields
    @FXML
    public TextField tfCurrentLocation;
    @FXML
    public TextField tfCompany;
    @FXML
    public TextField tfCompanyId;

    @FXML
    public TextField tfSeller;

    @FXML
    public TextField tfBuyer;

    @FXML
    public TextField tfSupervisor;

    @FXML
    public TextField tfStatus;

    @FXML
    public TextField tfDateOfBuying;

    @FXML
    public TextField tfPrice;

    @FXML
    public TextField tfType;

    //Filter textFields
    @FXML
    public TextField tfFilterId;
    @FXML
    public TextField tfFilterType;
    @FXML
    public TextField tfFilterCompany;
    @FXML
    public TextField tfFilterCompanyId;
    @FXML
    public TextField tfFilterPrice;
    @FXML
    public TextField tfFilterSeller;
    @FXML
    public TextField tfFilterBuyer;
    @FXML
    public TextField tfFilterSupervisorId;
    @FXML
    public TextField tfFilterCurrentLocation;
    @FXML
    public TextField tfFilterStatus;
    @FXML
    public TextField tfFilterYearOfBuying;
    @FXML
    public Label labelAlert;

    //Duplicate for update function
    Object object = new Object() ;
    public static String iithId ;
    public void setIithId(String iithId) {
        System.out.println("in");
        SVClassObjectListController.iithId =iithId ;
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

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        labelAlert.setVisible(true);
        tfSupervisor.setDisable(true);
        tfFilterSupervisorId.setDisable(true);
        errorInfoFields.setVisible(false);
        errorUpdateLabel.setVisible(false);
        errorSupervisorObject.setVisible(false);
        filterError.setVisible(false);
        objectTypeError.setVisible(false);
    }

    @FXML
    public void handleMouseAction(MouseEvent event) {
        errorInfoFields.setVisible(false);
        errorSupervisorObject.setVisible(false);
        filterError.setVisible(false);
        errorUpdateLabel.setVisible(false);
        objectTypeError.setVisible(false);
        Object object1 = tvObject.getSelectionModel().getSelectedItem() ;

        object.setIithId(object1.getIithId());
        object.setType(object1.getType());
        object.setCompany(object1.getCompany());
        object.setCompanyId(object1.getCompanyId());
        object.setPrice(object1.getPrice());
        object.setSeller(object1.getSeller());
        object.setBuyer(object1.getBuyer());
        object.setSupervisor(object1.getSupervisor());
        object.setCurrentLocation(object1.getCurrentLocation());
        object.setStatus(object1.getStatus());
        object.setDateOfBuying(object1.getDateOfBuying());
        object.setFieldOfTheObject(object1.getFieldOfTheObject());

        tfType.setText(object1.getType());
        tfCompany.setText(object1.getCompany());
        tfCompanyId.setText(object1.getCompanyId());
        tfPrice.setText(object1.getPrice());
        tfSeller.setText(object1.getSeller());
        tfBuyer.setText(object1.getBuyer());
        tfSupervisor.setText(object1.getSupervisor());
        tfCurrentLocation.setText(object1.getCurrentLocation());
        tfStatus.setText(object1.getStatus());
        tfDateOfBuying.setText(object1.getDateOfBuying());

    }

    @FXML
    public void insertRecord(ActionEvent event) throws SQLException, ParseException {
        errorInfoFields.setVisible(false);
        errorSupervisorObject.setVisible(false);
        filterError.setVisible(false);
        errorUpdateLabel.setVisible(false);
        objectTypeError.setVisible(false);

        if(tfCurrentLocation.getText().trim().length()>0&&tfCompany.getText().trim().length()>0&&tfCompanyId.getText().trim().length()>0&&tfSeller.getText().trim().length()>0&&tfBuyer.getText().trim().length()>0&&tfSupervisor.getText().trim().length()>0&&tfStatus.getText().trim().length()>0&&tfDateOfBuying.getText().trim().length()>0&&tfPrice.getText().trim().length()>0&&tfType.getText().trim().length()>0){
            Connection con = getConnection();
            String query = "Select * from classsupervisortable where objectmaintained=? and iith_id=? ";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1,tfType.getText().trim().toLowerCase());
            st.setString(2,tfSupervisor.getText().trim().toLowerCase());
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                String query2 = "Insert into classroom"+tfType.getText().trim().toLowerCase()+"(type) values('"+tfType.getText().trim().toLowerCase()+"')" ;
                Statement sta = con.createStatement();
                int roo= sta.executeUpdate(query2);
                if(roo> 0){
                    System.out.println("Addition successfull");
                }else{
                    System.out.println("Addition unsuccessfull");
                }
                String queryForId = "SELECT id from classroom"+tfType.getText().trim().toLowerCase()+" ORDER BY id DESC LIMIT 1" ;
                ResultSet resultSet = st.executeQuery(queryForId) ;
                String iith_id = null ;
                int id = 0;
                while(resultSet.next()){
                    id=resultSet.getInt(1) ;
                    iith_id = tfType.getText().trim().toLowerCase().substring(0,3).toUpperCase() +"CL"+ id;
                }
                System.out.println(id+iith_id);
                String queryAdd = "Insert into classroomitemlist(iith_id,type,company,company_id,price,seller,buyer,supervisor,currentlocation,status,dateofbuying,fieldofobject) values(?,?,?,?,?,?,?,?,?,?,?,?) ";
                PreparedStatement staa= con.prepareStatement(queryAdd);
                staa.setString(1,iith_id);
                staa.setString(2,tfType.getText().trim().toLowerCase());
                staa.setString(3,tfCompany.getText().trim());
                staa.setString(4,tfCompanyId.getText().trim());
                staa.setString(5,tfPrice.getText().trim());
                staa.setString(6,tfSeller.getText().trim());
                staa.setString(7,tfBuyer.getText().trim());
                staa.setString(8,tfSupervisor.getText().trim());
                staa.setString(9,tfCurrentLocation.getText().trim());
                staa.setString(10,tfStatus.getText().trim());
                staa.setString(11,tfDateOfBuying.getText().trim());
                staa.setString(12,"classroom");
                staa.executeUpdate();
                showRecords();
            }else{
                errorSupervisorObject.setVisible(true);
            }
            st.close();
        }else{
            errorInfoFields.setVisible(true);
        }
    }

    @FXML
    public void updateRecord(ActionEvent event) throws SQLException {
        errorInfoFields.setVisible(false);
        errorSupervisorObject.setVisible(false);
        errorUpdateLabel.setVisible(false);
        filterError.setVisible(false);
        objectTypeError.setVisible(false);
        if(tfType.getText().trim().length()!=0 && tfCompany.getText().trim().length()!=0 &&tfCompanyId.getText().trim().length()!=0 &&tfPrice.getText().trim().length()!=0 &&tfSeller.getText().trim().length()!=0 &&tfBuyer.getText().trim().length()!=0 &&tfSupervisor.getText().trim().length()!=0 &&tfCurrentLocation.getText().trim().length()!=0 &&tfStatus.getText().trim().length()!=0 &&tfDateOfBuying.getText().trim().length()!=0){
            if(tfType.getText().trim().equals(object.getType())&&tfCompany.getText().trim().equals(object.getCompany())&&tfCompanyId.getText().trim().equals(object.getCompanyId())&&tfPrice.getText().trim().equals(object.getPrice())&&tfSeller.getText().trim().equals(object.getSeller())&&tfDateOfBuying.getText().trim().equals(object.getDateOfBuying())&&tfBuyer.getText().trim().equals(object.getBuyer())     ){
                Connection con = getConnection();
                String query = "update classroomitemlist set type=? , company = ?,company_id=? ,price=?,seller=?,buyer=?,supervisor=?,currentlocation=?,status=?,dateofbuying=? where iith_id =?";
                PreparedStatement st = con.prepareStatement(query);
                st.setString(1,tfType.getText().trim().toLowerCase());
                st.setString(2,tfCompany.getText().trim());
                st.setString(3,tfCompanyId.getText().trim());
                st.setString(4,tfPrice.getText().trim());
                st.setString(5,tfSeller.getText().trim());
                st.setString(6,tfBuyer.getText().trim());
                st.setString(7,tfSupervisor.getText().trim());
                st.setString(8,tfCurrentLocation.getText().trim());
                st.setString(9,tfStatus.getText().trim());
                st.setString(10,tfDateOfBuying.getText().trim());
                st.setString(11,object.getIithId());
                st.executeUpdate();
                st.close();
                con.close();
                showRecords();
                clearRecord();
            }else{
                errorUpdateLabel.setVisible(true);
            }
        }else{
            filterError.setVisible(false);
        }
    }

    private ObservableList<Object> getRecords() throws SQLException {
        errorInfoFields.setVisible(false);
        errorSupervisorObject.setVisible(false);
        errorUpdateLabel.setVisible(false);
        filterError.setVisible(false);
        objectTypeError.setVisible(false);
        ObservableList<Object> objects = FXCollections.observableArrayList();
        Connection con = getConnection() ;
        String query = "Select * from classroomitemlist where supervisor=?";
        PreparedStatement st = con.prepareStatement(query);
        st.setString(1,iithId);
        ResultSet rs = st.executeQuery();
        while(rs.next()){
            Object object = new Object();
            object.setIithId(rs.getString(2));
            object.setType(rs.getString(3));
            object.setCompany(rs.getString(4));
            object.setCompanyId(rs.getString(5));
            object.setPrice(rs.getString(6));
            object.setSeller(rs.getString(7));
            object.setBuyer(rs.getString(8));
            object.setSupervisor(rs.getString(9));
            object.setCurrentLocation(rs.getString(10));
            object.setStatus(rs.getString(11));
            object.setDateOfBuying(rs.getString(12));
            object.setFieldOfTheObject(rs.getString(13));
            objects.add(object);
        }
        st.close();
        con.close();
        System.out.println("returned");
        return objects ;
    }


    @FXML
    public void showRecords() {
        labelAlert.setVisible(false);
        System.out.println(iithId);
        tfSupervisor.setText(iithId);
        tfFilterSupervisorId.setText(iithId);
        errorInfoFields.setVisible(false);
        errorSupervisorObject.setVisible(false);
        errorUpdateLabel.setVisible(false);
        filterError.setVisible(false);
        objectTypeError.setVisible(false);
        try{
            ObservableList<Object> list = getRecords();
            colId.setCellValueFactory(new PropertyValueFactory<Object,String>("iithId"));
            colType.setCellValueFactory(new PropertyValueFactory<Object,String>("type"));
            colCompany.setCellValueFactory(new PropertyValueFactory<Object,String>("company"));
            colCompanyID.setCellValueFactory(new PropertyValueFactory<Object , String>("companyId"));
            colPrice.setCellValueFactory(new PropertyValueFactory<Object,String>("price"));
            colSeller.setCellValueFactory(new PropertyValueFactory<Object,String>("seller"));
            colBuyer.setCellValueFactory(new PropertyValueFactory<Object,String>("buyer"));
            colSupervisor.setCellValueFactory(new PropertyValueFactory<Object,String>("supervisor"));
            colCurrentLocation.setCellValueFactory(new PropertyValueFactory<Object,String>("currentLocation"));
            colStatus.setCellValueFactory(new PropertyValueFactory<Object,String>("status"));
            colDateOfBuying.setCellValueFactory(new PropertyValueFactory<Object,String>("dateOfBuying"));
            tvObject.setItems(list);
            errorInfoFields.setVisible(false);
            errorSupervisorObject.setVisible(false);
            filterError.setVisible(false);
            objectTypeError.setVisible(false);
            tfFilterId.setText("");
            tfFilterType.setText("");
            tfFilterCompany.setText("");
            tfFilterCompanyId.setText("");
            tfFilterPrice.setText("");
            tfFilterSeller.setText("");
            tfFilterBuyer.setText("");
            tfFilterCurrentLocation.setText("");
            tfFilterStatus.setText("");
            tfFilterYearOfBuying.setText("");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void filter(ActionEvent event) {
        errorInfoFields.setVisible(false);
        errorSupervisorObject.setVisible(false);
        errorUpdateLabel.setVisible(false);
        filterError.setVisible(false);
        objectTypeError.setVisible(false);
        if(tfFilterId.getText().trim().length()>0 || tfFilterType.getText().trim().length()>0|| tfFilterCompany.getText().trim().length()>0|| tfFilterCompanyId.getText().trim().length()>0|| tfFilterPrice.getText().trim().length()>0|| tfFilterSeller.getText().trim().length()>0|| tfFilterBuyer.getText().trim().length()>0|| tfFilterCurrentLocation.getText().trim().length()>0|| tfFilterStatus.getText().trim().length()>0|| tfFilterYearOfBuying.getText().trim().length()>0){
            String iithIds = tfFilterId.getText().trim().toLowerCase();
            String type = tfFilterType.getText().trim().toLowerCase() ;
            String company = tfFilterCompany.getText().trim();
            String companyId  = tfCompanyId.getText().trim() ;
            String price = tfFilterPrice.getText().trim() ;
            String seller = tfFilterSeller.getText().trim();
            String buyer = tfFilterBuyer.getText().trim() ;
            String supervisorId = tfFilterSupervisorId.getText().trim().toUpperCase();
            String currentLocation = tfFilterCurrentLocation.getText().trim();
            String status = tfFilterStatus.getText().trim();
            String yearOfBuying = tfFilterYearOfBuying.getText().trim();
            try{
                if(iithIds.length()==0) iithIds="%%" ;
                if(type.length()==0) type ="%%";
                if(company.length()==0) company = "%%";
                if(companyId.length()==0) companyId="%%";
                if(price.length()==0) price ="%%";
                if(seller.length()==0) seller="%%";
                if(buyer.length()==0) buyer = "%%";
                if(supervisorId.length()==0) supervisorId="%%";
                if(currentLocation.length()==0) currentLocation="%%" ;
                if(status.length()==0) status="%%";
                if(yearOfBuying.length()==0) yearOfBuying="%%";

                Connection con = getConnection();
                ObservableList<Object> objects = FXCollections.observableArrayList();
                String query="SELECT * FROM classroomitemlist where iith_id LIKE ? and type LIKE ? and company like ? and company_id like ? and price like ? and seller like ? and buyer like ? and supervisor like ? and currentlocation like ? and status like ? and dateofbuying like ? order by iith_id" ;
                PreparedStatement st = con.prepareStatement(query);
                st.setString(1,iithIds);
                st.setString(2,type);
                st.setString(3,company);
                st.setString(4,companyId);
                st.setString(5,price);
                st.setString(6,seller);
                st.setString(7,buyer);
                st.setString(8,supervisorId);
                st.setString(9,currentLocation);
                st.setString(10,status);
                st.setString(11,yearOfBuying+"%" );
                ResultSet rs = st.executeQuery();
                System.out.println("k");
                while(rs.next()){
                    System.out.println("A");
                    Object object = new Object();
                    object.setIithId(rs.getString(2));
                    object.setType(rs.getString(3));
                    object.setCompany(rs.getString(4));
                    object.setCompanyId(rs.getString(5));
                    object.setPrice(rs.getString(6));
                    object.setSeller(rs.getString(7));
                    object.setBuyer(rs.getString(8));
                    object.setSupervisor(rs.getString(9));
                    object.setCurrentLocation(rs.getString(10));
                    object.setStatus(rs.getString(11));
                    object.setDateOfBuying(rs.getString(12));
                    object.setFieldOfTheObject(rs.getString(13));
                    objects.add(object);
                }
                colId.setCellValueFactory(new PropertyValueFactory<Object,String>("iithId"));
                colType.setCellValueFactory(new PropertyValueFactory<Object,String>("type"));
                colCompany.setCellValueFactory(new PropertyValueFactory<Object,String>("company"));
                colCompanyID.setCellValueFactory(new PropertyValueFactory<Object , String>("companyId"));
                colPrice.setCellValueFactory(new PropertyValueFactory<Object,String>("price"));
                colSeller.setCellValueFactory(new PropertyValueFactory<Object,String>("seller"));
                colBuyer.setCellValueFactory(new PropertyValueFactory<Object,String>("buyer"));
                colSupervisor.setCellValueFactory(new PropertyValueFactory<Object,String>("supervisor"));
                colCurrentLocation.setCellValueFactory(new PropertyValueFactory<Object,String>("currentLocation"));
                colStatus.setCellValueFactory(new PropertyValueFactory<Object,String>("status"));
                colDateOfBuying.setCellValueFactory(new PropertyValueFactory<Object,String>("dateOfBuying"));
                tvObject.setItems(objects);
                st.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            filterError.setVisible(true);
        }
    }


    @FXML
    void clearRecord(){
        System.out.println(iithId);
        errorInfoFields.setVisible(false);
        errorSupervisorObject.setVisible(false);
        errorUpdateLabel.setVisible(false);
        filterError.setVisible(false);
        objectTypeError.setVisible(false);
        tfType.setText("");
        tfCompany.setText("");
        tfCompanyId.setText("");
        tfBuyer.setText("");
        tfSeller.setText("");
        tfPrice.setText("");
        tfStatus.setText("");
        tfCurrentLocation.setText("");
        tfDateOfBuying.setText("");
    }

    public void OnBack(ActionEvent event) throws IOException {
        errorInfoFields.setVisible(false);
        errorSupervisorObject.setVisible(false);
        errorUpdateLabel.setVisible(false);
        filterError.setVisible(false);
        objectTypeError.setVisible(false);
        Parent root = FXMLLoader.load(requireNonNull(getClass().getResource("SVClassroomMode.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}




