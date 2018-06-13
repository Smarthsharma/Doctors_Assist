/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor.s.assist;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Smarth Sharma
 */
public class DoctoadminController implements Initializable {
    private ObservableList<doc>data;
    @FXML
    private TableView<doc>doctor;
    @FXML
    private TableColumn<doc, String> doctor_id;
    @FXML
    private TableColumn<doc, String> name;
    @FXML
    private TableColumn<doc, String> qualification;
    @FXML
    private TableColumn<doc, String> dob;
    @FXML
    private TableColumn<doc, String> gender;
    @FXML
    private TableColumn<doc, String> address;
    @FXML
    private TableColumn<doc, String> contact;
    @FXML
    private TableColumn<doc, String> email;
    @FXML
    private TableColumn<doc, String> user_id;
    @FXML
    private TableColumn<doc, String> password;
    @FXML
    private TextField searchField;
    @FXML
    private Button  close,back;
    @FXML
    private AnchorPane main_pane;
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources){
        setCellTable();
        data=FXCollections.observableArrayList();
        loadData();
    }    
    private Connection con =null;
    private PreparedStatement ps=null;
    private ResultSet rs=null;
  
    
    private void setCellTable() 
    {
        doctor_id.setCellValueFactory(new PropertyValueFactory<doc,String>("doctor_id"));
        name.setCellValueFactory(new PropertyValueFactory<doc,String>("name"));
        qualification.setCellValueFactory(new PropertyValueFactory<doc,String>("qualification"));
        dob.setCellValueFactory(new PropertyValueFactory<doc,String>("dob"));
        
        gender.setCellValueFactory(new PropertyValueFactory<doc,String>("gender"));
        address.setCellValueFactory(new PropertyValueFactory<doc,String>("address"));
        contact.setCellValueFactory(new PropertyValueFactory<doc,String>("contact"));
        email.setCellValueFactory(new PropertyValueFactory<doc,String>("email"));
        
        user_id.setCellValueFactory(new PropertyValueFactory<doc,String>("user_id"));
        password.setCellValueFactory(new PropertyValueFactory<doc,String>("password"));
        
        
    }
    private void loadData()
    {
        try {
           
            con=JDBC.databaseConnect();
            Statement st=con.createStatement();
            rs =st.executeQuery("select *from doctor");
            while(rs.next())
            {
           data.add(new doc(rs.getString(10),rs.getString(1),rs.getString(2),rs.getString(6),rs.getString(5),rs.getString(3),rs.getString(7),rs.getString(8),rs.getString(4),rs.getString(9)));
                        }
        } catch (SQLException ex) {
            Logger.getLogger(DoctoadminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        doctor.setItems(data);
    
    }
     public void searchTable(){
          FilteredList<doc> filteredData= new FilteredList<doc>(data, e->true);
    searchField.setOnKeyReleased(e -> {
            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super doc>) doc -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                }
                    String lowerCaseFilter=newValue.toLowerCase();
                if(doc.getName().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }
                else if(doc.getDoctor_id().toLowerCase().contains(lowerCaseFilter)){
                return true;
            }
                return false;
            });
            });
        SortedList<doc> sortedData=new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(doctor.comparatorProperty());
        doctor.setItems(sortedData);   
    });
    } 

   /* @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
      public void closeAction() {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }    
    public void back() throws IOException
    {
    FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("Admin.fxml"));
            Parent root2 = (Parent) fxmlLoader1.load();
            Stage stage2 = new Stage();
            stage2.setScene(new Scene(root2));
            stage2.setResizable(false);
            stage2.initModality(Modality.WINDOW_MODAL);
             ((Stage) main_pane.getScene().getWindow()).close();
            stage2.showAndWait();
    }
    
}
