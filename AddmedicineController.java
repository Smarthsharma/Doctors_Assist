/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor.s.assist;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
public class AddmedicineController{
    
    //private ObservableList<med>data;
    ObservableList<med> data=FXCollections.observableArrayList();

    @FXML
    private TextField searchField;
    @FXML
    private TableView<med>medicine;
    @FXML
    private TableColumn<med,String> id;
    @FXML
    private TableColumn<med,String> brand;
    @FXML
    private TableColumn<med,String> name;
    @FXML
    private TableColumn<med,String> composition;
    @FXML
    private TableColumn<med,String> type;
    @FXML
    private Button close,back;
    @FXML
    private AnchorPane mane_pane;
    
    /**
     * Initializes the controller class.
     * @param url
     */
    public void initialize(){
        setCellTable();
        data=FXCollections.observableArrayList();
        searchTable();
        loadData();
    }    
    private Connection con =null;
    private PreparedStatement ps=null;
    private ResultSet rs=null;
  
    
    private void setCellTable()
    {
        id.setCellValueFactory(new PropertyValueFactory<med,String>("id"));
        brand.setCellValueFactory(new PropertyValueFactory<med,String>("brand"));
        name.setCellValueFactory(new PropertyValueFactory<med,String>("name"));
        composition.setCellValueFactory(new PropertyValueFactory<med,String>("composition"));
        type.setCellValueFactory(new PropertyValueFactory<med,String>("type"));
        
    }
    private void loadData()
    {
        try {
            con=JDBC.databaseConnect();
            Statement st=con.createStatement();
            rs =st.executeQuery("select *from medicine  ");
            while(rs.next())
            {
                System.out.println(rs.getString("id"));
               data.add(new med(rs.getString("id"),rs.getString("manufacturer"),rs.getString("mname"),rs.getString("composition"),rs.getString("type")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddmedicineController.class.getName()).log(Level.SEVERE, null, ex);
        }
        medicine.setItems(data);
    
    }
  
           
    public void searchTable(){
          FilteredList<med> filteredData= new FilteredList<med>(data, e->true);
    searchField.setOnKeyReleased(e -> {
            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super med>) med -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                }
                    String lowerCaseFilter=newValue.toLowerCase();
                if(med.getComposition().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }
                else if(med.getBrand().toLowerCase().contains(lowerCaseFilter)){
                return true;
            }
                return false;
            });
            });
        SortedList<med> sortedData=new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(medicine.comparatorProperty());
        medicine.setItems(sortedData);   
    });
    }
    public void closeAction() {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }    
    public void back() throws IOException
    {
    FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("prescriptionfxml.fxml"));
            Parent root2 = (Parent) fxmlLoader1.load();
            Stage stage2 = new Stage();
            stage2.setScene(new Scene(root2));
            stage2.setResizable(false);
            stage2.initModality(Modality.WINDOW_MODAL);
             ((Stage) mane_pane.getScene().getWindow()).close();
            stage2.showAndWait();
    }
}
