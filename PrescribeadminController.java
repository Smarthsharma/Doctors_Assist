/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor.s.assist;

import doctor.s.assist.JDBC;
import doctor.s.assist.xxx;
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
public class PrescribeadminController implements Initializable {

   private ObservableList<xxx>data;
    @FXML
    private TableView<xxx>prescribe;
    @FXML
    private TableColumn<xxx, String> c1;
    @FXML
    private TableColumn<xxx, String> c2;
    @FXML
    private TableColumn<xxx, String> c3;
    @FXML
    private TableColumn<xxx, String> c4;
    @FXML
    private TableColumn<xxx, String> c5;
    @FXML
    private TableColumn<xxx, String> c6;
    @FXML
    private TableColumn<xxx, String> c7;
    @FXML
    private TableColumn<xxx, String> c8;
    @FXML
    private TableColumn<xxx, String> c9;
    @FXML
    private TableColumn<xxx, String> c10;
    @FXML
    private TableColumn<xxx, String> c11;
    @FXML
    private TableColumn<xxx, String> c12;
    @FXML
    private TableColumn<xxx, String> c13;
    @FXML
    private TableColumn<xxx, String> c14;
    @FXML
    private TableColumn<xxx, String> c15;
    @FXML
    private TableColumn<xxx, String> c16;
    @FXML
    private TableColumn<xxx, String> c17;
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
        c1.setCellValueFactory(new PropertyValueFactory<xxx,String>("pid"));
        c2.setCellValueFactory(new PropertyValueFactory<xxx,String>("doctor_id"));
        c3.setCellValueFactory(new PropertyValueFactory<xxx,String>("docname"));
        c4.setCellValueFactory(new PropertyValueFactory<xxx,String>("patient_id"));
        
        c5.setCellValueFactory(new PropertyValueFactory<xxx,String>("patientname"));
        c6.setCellValueFactory(new PropertyValueFactory<xxx,String>("symtoms"));
        c7.setCellValueFactory(new PropertyValueFactory<xxx,String>("test"));
        c8.setCellValueFactory(new PropertyValueFactory<xxx,String>("med1"));
        c9.setCellValueFactory(new PropertyValueFactory<xxx,String>("day1"));
        c10.setCellValueFactory(new PropertyValueFactory<xxx,String>("dose1"));
        c11.setCellValueFactory(new PropertyValueFactory<xxx,String>("med2"));
        c12.setCellValueFactory(new PropertyValueFactory<xxx,String>("day2"));
        c13.setCellValueFactory(new PropertyValueFactory<xxx,String>("dose2"));
        c14.setCellValueFactory(new PropertyValueFactory<xxx,String>("med3"));
        c15.setCellValueFactory(new PropertyValueFactory<xxx,String>("day3"));
        c16.setCellValueFactory(new PropertyValueFactory<xxx,String>("dose3"));
        c17.setCellValueFactory(new PropertyValueFactory<xxx,String>("date"));
        
        
    }
    private void loadData()
    {
        try {
           
            con=JDBC.databaseConnect();
            Statement st=con.createStatement();
            rs =st.executeQuery("select *from prescribe");
            while(rs.next())
            {
           data.add(new xxx(rs.getString("pid"),rs.getString("did"),rs.getString("dname"),rs.getString("paid"),rs.getString("pname"),rs.getString("symptom"),rs.getString("test"),rs.getString("med1"),rs.getString("day1"),rs.getString("dose1"),rs.getString("med2"),rs.getString("day2"),rs.getString("dose2"),rs.getString("med3"),rs.getString("day3"),rs.getString("dose3"),rs.getString("date")));
                        }
        } catch (SQLException ex) {
            Logger.getLogger(PrescribeadminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        prescribe.setItems(data);
    
    }
     public void searchTable(){
          FilteredList<xxx> filteredData= new FilteredList<xxx>(data, e->true);
           searchField.setOnKeyReleased(e -> {
            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super xxx>) xxx -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                }
                    String lowerCaseFilter=newValue.toLowerCase();
                if(xxx.getPatient_id() .toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }
                else if(xxx.getPatientname().toLowerCase().contains(lowerCaseFilter)){
                return true;
            }
                 else if(xxx.getDoctor_id().toLowerCase().contains(lowerCaseFilter)){
                return true;
            }
                 else if(xxx.getDocname().toLowerCase().contains(lowerCaseFilter)){
                return true;
            }
                return false;
            });
            });
        SortedList<xxx> sortedData=new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(prescribe.comparatorProperty());
        prescribe.setItems(sortedData);   
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

    

