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

public class AdminModeSellerInfoController implements Initializable {

    Seller globalSeller = null;

    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private TableView<Seller> tvSeller = new TableView();
    @FXML
    private TableColumn<Seller, String> colCompany;

    @FXML
    private TableColumn<Seller, String> colContactNo;

    @FXML
    private TableColumn<Seller, Integer> colId;

    @FXML
    private TableColumn<Seller, String> colItem;

    @FXML
    private TableColumn<Seller, String> colName;

    @FXML
    private TableColumn<Seller, String> colMail;

    @FXML
    private TextField tfCompany;

    @FXML
    private TextField tfItem;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfMail;

    @FXML
    private TextField tfFilterCompanyName ;

    @FXML
    private TextField tfItemType ;

    @FXML
    private TextField tfContactNo;
    @FXML
    private Label textfieldError;

    @FXML
    private Label filterError;
    @FXML
    private TextField tfFilterName;

    @FXML
    private TextField tfFilterMail ;

    @FXML
    private TextField tfFilterContactNumber ;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textfieldError.setVisible(false);
        filterError.setVisible(false);
        try {
            showSellerInfo();
        } catch (SQLException e) {
            System.out.println("Error in the initialize() method in AdminModeSellerInfoController.java : "+e.getMessage());
        }
    }

    //Function for getting Connection with the database
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



    @FXML
    void handleMouseAction(MouseEvent event) {
        textfieldError.setVisible(false);
        filterError.setVisible(false);
        globalSeller = new Seller();
        Seller seller = tvSeller.getSelectionModel().getSelectedItem() ;

        globalSeller.setName(seller.getName());
        globalSeller.setCompany(seller.getCompany());
        globalSeller.setMail_id(seller.getMail_id());
        globalSeller.setContact_number(seller.getContact_number());
        globalSeller.setItem(seller.getItem());

        tfName.setText(seller.getName());
        tfCompany.setText(seller.getCompany());
        tfMail.setText(seller.getMail_id());
        tfContactNo.setText(seller.getContact_number());
        tfItem.setText(seller.getItem());
    }


    //Function Used for inserting a record in the database
    //Works Fine
    @FXML
    void insertRecord(ActionEvent event) {
        filterError.setVisible(false);
        String query = "INSERT INTO seller(name,company,mail_id,contact_number,item) VALUES(?,?,?,?,?) ";
        try{
            textfieldError.setVisible(false);
            Connection con = getConnection();
            if(tfName.getText().trim().length()!=0 && tfCompany.getText().trim().length()!=0 && tfMail.getText().trim().length()!=0 &&tfContactNo.getText().trim().length()!=0 &&tfItem.getText().trim().length()!=0  ){
                PreparedStatement st = con.prepareStatement(query);
                st.setString(1,tfName.getText().trim());
                st.setString(2,tfCompany.getText().trim());
                st.setString(3,tfMail.getText().trim());
                st.setString(4,tfContactNo.getText().trim());
                st.setString(5,tfItem.getText().trim());

                int row =  st.executeUpdate() ;
                if(row>0) System.out.println("Seller added successfully");
                else System.out.println("Seller adding failed");
                clear_fields();
                st.close();
                showSellerInfo();
            }
            else{
                textfieldError.setVisible(true);
                showSellerInfo();
            }
            con.close();


        }catch(SQLException e){
            System.out.println("Unable to retrieve the connection (in AdminModeSellerInfoController.java insertRecord() method) : "+e.getMessage());
        }
    }

    @FXML
    void deleteRecord(ActionEvent event) {
        textfieldError.setVisible(false);
        filterError.setVisible(false);
        String query = "Delete from seller Where name=? and company=? and mail_id=? and contact_number=? and item=? ";
        try{
            textfieldError.setVisible(false);
            Connection con = getConnection();
            if(tfName.getText().trim().length()!=0 && tfCompany.getText().trim().length()!=0 && tfMail.getText().trim().length()!=0 &&tfContactNo.getText().trim().length()!=0 &&tfItem.getText().trim().length()!=0  ){
                PreparedStatement st = con.prepareStatement(query);
                st.setString(1,tfName.getText().trim());
                st.setString(2,tfCompany.getText().trim());
                st.setString(3,tfMail.getText().trim());
                st.setString(4,tfContactNo.getText().trim());
                st.setString(5,tfItem.getText().trim());
                int row =  st.executeUpdate() ;
                if(row>0) System.out.println("Seller Deleted successfully");
                else System.out.println("Seller Deletion failed");
                clear_fields();
                showSellerInfo();
                st.close();
            }
            else{
                textfieldError.setVisible(true);
                showSellerInfo();
            }
            con.close();
        }catch(SQLException e){
            System.out.println("Unable to retrieve the connection (in AdminModeSellerInfoController.java deleteRecord() method) : "+e.getMessage());
        }
    }

    //Function for updating a Object value in the record
    @FXML
    void updateRecord(ActionEvent event) {
        textfieldError.setVisible(false);
        filterError.setVisible(false);
        String query = "UPDATE seller set name=?,company=?,mail_id=?,contact_number=?,item=? Where name=? and company=? and mail_id=? and contact_number=? and item=? ";
        try{
            textfieldError.setVisible(false);
            Connection con = getConnection();
            if(tfName.getText().trim().length()!=0 && tfCompany.getText().trim().length()!=0 && tfMail.getText().trim().length()!=0 &&tfContactNo.getText().trim().length()!=0 &&tfItem.getText().trim().length()!=0  ){
                PreparedStatement st = con.prepareStatement(query);
                st.setString(1,tfName.getText().trim());
                st.setString(2,tfCompany.getText().trim());
                st.setString(3,tfMail.getText().trim());
                st.setString(4,tfContactNo.getText().trim());
                st.setString(5,tfItem.getText().trim());
                st.setString(6,globalSeller.getName());
                st.setString(7,globalSeller.getCompany());
                st.setString(8,globalSeller.getMail_id());
                st.setString(9,globalSeller.getContact_number());
                st.setString(10,globalSeller.getItem());

                int row =  st.executeUpdate() ;
                if(row>0) System.out.println("Seller info updated successfully");
                else System.out.println("Seller info updation failed");
                showSellerInfo();
                clear_fields();
                st.close();
            }
            else{
                textfieldError.setVisible(true);
                showSellerInfo();
            }
            con.close();
        }catch(SQLException e){
            System.out.println("Unable to retrieve the connection (in AdminModeSellerInfoController.java updateRecord() method) : "+e.getMessage());
        }
    }

    private ObservableList<Seller> getSellerInfo() throws SQLException {
        try{
           int i = 1 ;
           Connection con = getConnection() ;
           Statement st = con.createStatement();
           ObservableList<Seller> seller = FXCollections.observableArrayList();
           String query = "SELECT * FROM seller " ;

           ResultSet rs = st.executeQuery(query) ;
           while(rs.next()){
               Seller temp = new Seller() ;
               temp.setId(i);
               temp.setName(rs.getString(2));
               temp.setCompany(rs.getString(3));
               temp.setMail_id(rs.getString(4));
               temp.setContact_number(rs.getString(5));
               temp.setItem(rs.getString(6));
               i++ ;
               seller.add(temp);
           }
           System.out.println("Returned the Observable list of Sellers");
           return seller ;
       }catch (SQLException e){
           System.out.println("Unable to retrieve the connection (in AdminModeSellerInfoController.java getSellerInfo() method) : "+e.getMessage());
           return null ;
       }
    }
    @FXML
    private void showSellerInfo() throws SQLException {
        try{
            System.out.println("In showSellerInfo() ");
            ObservableList<Seller> seller = getSellerInfo() ;
            tvSeller.setItems(seller);
            colId.setCellValueFactory(new PropertyValueFactory<Seller , Integer>("id"));
            colName.setCellValueFactory(new PropertyValueFactory<Seller,String>("name"));
            colCompany.setCellValueFactory(new PropertyValueFactory<Seller,String>("company"));
            colMail.setCellValueFactory(new PropertyValueFactory<Seller,String>("mail_id"));
            colContactNo.setCellValueFactory(new PropertyValueFactory<Seller,String>("contact_number"));
            colItem.setCellValueFactory(new PropertyValueFactory<Seller,String>("item"));
        }catch(SQLException e){
            System.out.println("Unable to retrieve Seller info from getSellerInfo() method error in showSellerInfo() method in AdminModeSellerInfoController.java :"+e.getMessage());
        }

    }
    @FXML
    private void showSellerInfoForAllRecordsButton() throws SQLException {
        try{
            System.out.println("In showSellerInfo() ");
            ObservableList<Seller> seller = getSellerInfo() ;
            tvSeller.setItems(seller);
            colId.setCellValueFactory(new PropertyValueFactory<Seller , Integer>("id"));
            colName.setCellValueFactory(new PropertyValueFactory<Seller,String>("name"));
            colCompany.setCellValueFactory(new PropertyValueFactory<Seller,String>("company"));
            colMail.setCellValueFactory(new PropertyValueFactory<Seller,String>("mail_id"));
            colContactNo.setCellValueFactory(new PropertyValueFactory<Seller,String>("contact_number"));
            colItem.setCellValueFactory(new PropertyValueFactory<Seller,String>("item"));
            clear_fields_for_filters();
        }catch(SQLException e){
            System.out.println("Unable to retrieve Seller info from getSellerInfo() method error in showSellerInfo() method in AdminModeSellerInfoController.java :"+e.getMessage());
        }

    }

    @FXML
    public void filterList(ActionEvent e){
        textfieldError.setVisible(false);
        filterError.setVisible(false);
        String company = tfFilterCompanyName.getText().trim() ;
        String item = tfItemType.getText().trim() ;
        String name = tfFilterName.getText().trim() ;
        String mail = tfFilterMail.getText().trim();
        String contactNumber = tfFilterContactNumber.getText().trim() ;
        if(company.length() > 0 ||item.length() > 0 ||name.length() > 0||mail.length() > 0||contactNumber.length() > 0  ) {
            try{
                if(company.length() == 0 ) company="%%" ;
                if(item.length() == 0 ) item="%%" ;
                if(name.length() == 0 ) name="%%" ;
                if(mail.length() == 0) mail="%%" ;
                if(contactNumber.length()==0) contactNumber="%%" ;
                textfieldError.setVisible(false);
                int i = 1 ;
                Connection con = getConnection() ;
                ObservableList<Seller> seller = FXCollections.observableArrayList();
                String query = "SELECT * FROM seller where company LIKE ? and item LIKE ? and name LIKE ? and mail_id LIKE ? AND contact_number LIKE ? order by name,item,mail_id,contact_number ";
                PreparedStatement st = con.prepareStatement(query);
                st.setString(1,company);
                st.setString(2,item);
                st.setString(3,name);
                st.setString(4,mail);
                st.setString(5,contactNumber);
                ResultSet rs = st.executeQuery() ;
                while(rs.next()){
                    Seller temp = new Seller() ;
                    temp.setId(i);
                    temp.setName(rs.getString(2));
                    temp.setCompany(rs.getString(3));
                    temp.setMail_id(rs.getString(4));
                    temp.setContact_number(rs.getString(5));
                    temp.setItem(rs.getString(6));
                    i++ ;
                    seller.add(temp);
                }
                tvSeller.setItems(seller);
                colId.setCellValueFactory(new PropertyValueFactory<Seller , Integer>("id"));
                colName.setCellValueFactory(new PropertyValueFactory<Seller,String>("name"));
                colCompany.setCellValueFactory(new PropertyValueFactory<Seller,String>("company"));
                colMail.setCellValueFactory(new PropertyValueFactory<Seller,String>("mail_id"));
                colContactNo.setCellValueFactory(new PropertyValueFactory<Seller,String>("contact_number"));
                colItem.setCellValueFactory(new PropertyValueFactory<Seller,String>("item"));
            }catch (SQLException exe){
                System.out.println("Unable to retrieve the connection (in AdminModeSellerInfoController.java filterList() method) : "+exe.getMessage());
            }
        }else if(company.length() == 0 && item.length() == 0 && name.length() == 0){
            filterError.setVisible(true);
        }
    }

    //function to clear the textfields, use it wherever you press the update,insert..etc buttons
   public void clear_fields(){
        tfCompany.setText("");
        tfItem.setText("");
        tfMail.setText("");
        tfName.setText("");
        tfContactNo.setText("");
    }

    //Function for clearing the filter options
    public void clear_fields_for_filters(){
        tfItemType.setText("");
        tfFilterCompanyName.setText("");
    }
    public void OnBack(ActionEvent event) throws IOException{
        root = FXMLLoader.load(requireNonNull(getClass().getResource("AdminMode.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
