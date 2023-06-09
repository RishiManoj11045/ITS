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

public class FacultyClassroomListController implements Initializable {

    //Filter text fields
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

    //text fields
    @FXML
    public TextField tfType;
    @FXML
    public TextField tfCompany;
    @FXML
    public TextField tfCompanyId;
    @FXML
    public TextField tfPrice;
    @FXML
    public TextField tfSeller;
    @FXML
    public TextField tfBuyer;
    @FXML
    public TextField tfSupervisor;
    @FXML
    public TextField tfCurrentLocation;
    @FXML
    public TextField tfStatus;
    @FXML
    public TextField tfDateOfBuying;

    //table view
    @FXML
    public TableView<Object>  tvObject;
    @FXML
    public TableColumn<Object, String>  colId;
    @FXML
    public TableColumn<Object, String>  colType;
    @FXML
    public TableColumn<Object, String>  colCompany;
    @FXML
    public TableColumn<Object, String>  colCompanyID;
    @FXML
    public TableColumn<Object, String>  colPrice;
    @FXML
    public TableColumn<Object, String>  colSeller;
    @FXML
    public TableColumn<Object, String>  colBuyer;
    @FXML
    public TableColumn<Object, String>  colSupervisor;
    @FXML
    public TableColumn<Object, String>  colCurrentLocation;
    @FXML
    public TableColumn<Object, String>  colStatus;
    @FXML
    public TableColumn<Object, String>  colDateOfBuying;
    @FXML
    public Label errorFilter;


    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void handleMouseAction(MouseEvent event) {
        errorFilter.setVisible(false);
        Object object1 = tvObject.getSelectionModel().getSelectedItem() ;
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
        errorFilter.setVisible(false);
        tfBuyer.setEditable(false);
        tfCompany.setEditable(false);
        tfCompanyId.setEditable(false);
        tfCurrentLocation.setEditable(false);
        tfPrice.setEditable(false);
        tfDateOfBuying.setEditable(false);
        tfSeller.setEditable(false);
        tfStatus.setEditable(false);
        tfSupervisor.setEditable(false);
        tfType.setEditable(false);
        showRecords();
    }

    public void filter(ActionEvent event) {
        errorFilter.setVisible(false);
        if(tfFilterId.getText().trim().length()>0 || tfFilterType.getText().trim().length()>0|| tfFilterCompany.getText().trim().length()>0|| tfFilterCompanyId.getText().trim().length()>0|| tfFilterPrice.getText().trim().length()>0|| tfFilterSeller.getText().trim().length()>0|| tfFilterBuyer.getText().trim().length()>0|| tfFilterCurrentLocation.getText().trim().length()>0|| tfFilterStatus.getText().trim().length()>0|| tfFilterYearOfBuying.getText().trim().length()>0||tfFilterSupervisorId.getText().trim().length()>0){
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
            errorFilter.setVisible(true);
        }

    }

    private ObservableList<Object> getRecords() throws SQLException {
        ObservableList<Object> objects = FXCollections.observableArrayList();
        Connection con = getConnection() ;
        String query = "Select * from classroomitemlist";
        Statement st = con.prepareStatement(query);
        ResultSet rs = st.executeQuery(query);
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

    public void showRecords() {
        errorFilter.setVisible(false);
        clearTextFields();
        tfFilterId.setText("");
        tfFilterType.setText("");
        tfFilterCompany.setText("");
        tfFilterCompanyId.setText("");
        tfFilterPrice.setText("");
        tfFilterSeller.setText("");
        tfFilterBuyer.setText("");
        tfFilterSupervisorId.setText("");
        tfFilterCurrentLocation.setText("");
        tfFilterStatus.setText("");
        tfFilterYearOfBuying.setText("");
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

    public void clearTextFields() {
        errorFilter.setVisible(false);
        tfType.setText("");
        tfCompany.setText("");
        tfCompanyId.setText("");
        tfPrice.setText("");
        tfSeller.setText("");
        tfBuyer.setText("");
        tfSupervisor.setText("");
        tfCurrentLocation.setText("");
        tfStatus.setText("");
        tfDateOfBuying.setText("");
    }

    public void OnBack(ActionEvent event) throws IOException{
        root = FXMLLoader.load(requireNonNull(getClass().getResource("FacMode.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
