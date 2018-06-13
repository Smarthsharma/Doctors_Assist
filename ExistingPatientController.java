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
import java.util.function.Predicate;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Smarth Sharma
 */

public class ExistingPatientController {
    @FXML
    private Button close_button;
    private ObservableList<Patient>data;
    @FXML
    private TextField searchField;
    @FXML
    private TextField patientid;
    @FXML
    private TextField name;
    @FXML
    private TableView<Patient>patient;
    @FXML
    private AnchorPane main_pane;
    public void closeAction() {
        Stage stage = (Stage) close_button.getScene().getWindow();
        stage.close();
    }
       public void add(ActionEvent event ) throws IOException{
       
        
           
        String username, pid;
        username = name.getText();
        pid = patientid.getText();
        if (username.equals("") || pid.equals("")) {
            name.setText("");
            patientid.setText("");
             Alert alert= new Alert(Alert.AlertType.NONE,"Fill the Blank Spaces",ButtonType.OK);
            alert.setTitle("Invalid");
            alert.show();
            
        
          //   Alert alert=new Alert(AlertType.WARNING);
            // alert.setTitle("fil blank");
             
             
             alert.showAndWait();
             
        }
           else {
           
            try {
                Connection dbConnection = JDBC.databaseConnect();
                PreparedStatement pstmt=dbConnection.prepareStatement("SELECT * FROM patient WHERE id= ? and name=? ");
                pstmt.setString(1,username);
                pstmt.setString(2,pid);
                ResultSet userAccessResultSet = pstmt.executeQuery();
                if(userAccessResultSet.next()) {
                    pp.name=userAccessResultSet.getString("name");
                    pp.id=userAccessResultSet.getString("id");
                    pp.dob=userAccessResultSet.getString("dob");
                    pp.gender=userAccessResultSet.getString("gender");
            FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("prescriptionfxml.fxml"));
            Parent root2 = (Parent) fxmlLoader1.load();
            Stage stage2 = new Stage(); 
            stage2.setScene(new Scene(root2));
            stage2.setResizable(false);
            stage2.initModality(Modality.WINDOW_MODAL);
             ((Stage) main_pane.getScene().getWindow()).close();
            stage2.showAndWait();
                    }
                else{
                    Alert alert= new Alert(Alert.AlertType.NONE,"Wrong Patient-id or Name or patient does not exist",ButtonType.OK);
            alert.setTitle("Invalid");
            alert.showAndWait();
                }
                }
           
            catch(Exception e){}
       /* 
            
            */
      
        }}}
    
    
