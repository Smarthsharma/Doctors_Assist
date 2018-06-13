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
public class TestadminController implements Initializable {
    private ObservableList<Test>data;
   private TextField searchField;
  
    @FXML
    private TableView<Test>testtable;
    @FXML
    private TableColumn<Test, String> test_id;
    @FXML
    private TableColumn<Test, String> test_name;
    @FXML
    private TableColumn<Test, String> price;
    @FXML
    private Button  close,back;
    @FXML
    private AnchorPane main_pane;
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
        test_id.setCellValueFactory(new PropertyValueFactory<Test,String>("id"));
        test_name.setCellValueFactory(new PropertyValueFactory<Test,String>("name"));
        price.setCellValueFactory(new PropertyValueFactory<Test,String>("price"));
    }
    private void loadData()
    {
        try {

            con=JDBC.databaseConnect();
            Statement st=con.createStatement();
            rs =st.executeQuery("select * from test");
            while(rs.next())
            {           
               data.add(new Test(rs.getString(1),rs.getString(2),rs.getString(3)));
                        }
        } catch (SQLException ex) {
            Logger.getLogger(TestadminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        testtable.setItems(data);
    
    }
    
           
    public void searchTable(){
        FilteredList<Test> filteredData= new FilteredList<Test>(data, e->true);
    searchField.setOnKeyReleased(e -> {
            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super Test>) Test -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                }
                if(Test.getName().contains(newValue)){
                    return true;
                }
                else if(Test.getPrice().contains(newValue)){
                return true;
            }
                return false;
            });
            });
        SortedList<Test> sortedData=new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(testtable.comparatorProperty());
        testtable.setItems(sortedData);   
    });
    }
    @FXML
    public void closeAction() {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }    
    @FXML
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
