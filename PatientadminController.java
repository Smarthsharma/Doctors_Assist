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
import javafx.event.ActionEvent;
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
public class PatientadminController implements Initializable {
    private ObservableList<Patient>data;
    @FXML
    private TextField searchField;
    @FXML
    private TableView<Patient>patient;
    @FXML
    private TableColumn<Patient,String> patient_id;
    @FXML
    private TableColumn<Patient,String> name;
    @FXML
    private TableColumn<Patient, String> dob;
    @FXML
    private TableColumn<Patient, String> gender;
    @FXML
    private TableColumn<Patient, String> address;
    @FXML
    private TableColumn<Patient, String> contact;
    @FXML
    private TableColumn<Patient, String> email;
    @FXML
    private Button back;
    @FXML
    private Button close;
    @FXML
    private AnchorPane main_pane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         setCellTable();
        data=FXCollections.observableArrayList();
        loadData();
    }    
    private Connection con =null;
    private PreparedStatement ps=null;
    private ResultSet rs=null;
       private void setCellTable()
    {
        patient_id.setCellValueFactory(new PropertyValueFactory<Patient,String>("patient_id"));
        name.setCellValueFactory(new PropertyValueFactory<Patient,String>("name"));
        dob.setCellValueFactory(new PropertyValueFactory<Patient,String>("dob"));
        gender.setCellValueFactory(new PropertyValueFactory<Patient,String>("gender"));
        address.setCellValueFactory(new PropertyValueFactory<Patient,String>("address"));
        contact.setCellValueFactory(new PropertyValueFactory<Patient,String>("contact"));
        email.setCellValueFactory(new PropertyValueFactory<Patient,String>("email"));
    }
        private void loadData()
    {
        try {
           
            con=JDBC.databaseConnect();
            Statement st=con.createStatement();
            rs =st.executeQuery("select *from patient");
            while(rs.next())
            {
                
               data.add(new Patient(rs.getString(7),rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(6),rs.getString(5)));
                        }
        } catch (SQLException ex) {
            Logger.getLogger(MedicineadminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        patient.setItems(data);
    }
      public void searchTable(){
          FilteredList<Patient> filteredData= new FilteredList<Patient>(data, e->true);
    searchField.setOnKeyReleased(e -> {
            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super Patient>) Patient -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                }
                    String lowerCaseFilter=newValue.toLowerCase();
                if(Patient.getPatient_id().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }
                else if(Patient.getName().toLowerCase().contains(lowerCaseFilter)){
                return true;
            }
                return false;
            });
            });
        SortedList<Patient> sortedData=new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(patient.comparatorProperty());
        patient.setItems(sortedData);   
    });
    } 
    
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
