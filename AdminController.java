/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor.s.assist;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class AdminController {
@FXML
private Button close_button;
@FXML
private AnchorPane main_pane;
    public void closeAction() {
        Stage stage = (Stage) close_button.getScene().getWindow();
        stage.close();
    }
       public void patient (ActionEvent event ) throws IOException{
   FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("patientadmin.fxml"));
            Parent root2 = (Parent) fxmlLoader2.load();
            Stage stage2 = new Stage();
            stage2.setScene(new Scene(root2));
            stage2.setResizable(false);
            stage2.initStyle(StageStyle.UNDECORATED);
            stage2.initModality(Modality.WINDOW_MODAL);
            stage2.initStyle(StageStyle.UNDECORATED);
            ((Stage) main_pane.getScene().getWindow()).close();
            stage2.showAndWait();
        
 
       }
    public void medicine (ActionEvent event ) throws IOException{
        FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("medicineadmin.fxml"));
            Parent root2 = (Parent) fxmlLoader2.load();
            Stage stage2 = new Stage();
            stage2.setScene(new Scene(root2));
            stage2.setResizable(false);
            stage2.initStyle(StageStyle.UNDECORATED);
            stage2.initModality(Modality.WINDOW_MODAL);
            stage2.initStyle(StageStyle.UNDECORATED);
            ((Stage) main_pane.getScene().getWindow()).close();
            stage2.showAndWait();
        
 
       }
    public void doctor (ActionEvent event ) throws IOException{
        FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("doctoadmin.fxml"));
            Parent root2 = (Parent) fxmlLoader2.load();
            Stage stage2 = new Stage();
            stage2.setScene(new Scene(root2));
            stage2.setResizable(false);
            stage2.initStyle(StageStyle.UNDECORATED);
            stage2.initModality(Modality.WINDOW_MODAL);
            stage2.initStyle(StageStyle.UNDECORATED);
           ((Stage) main_pane.getScene().getWindow()).close(); 
            stage2.showAndWait();
        
 
       }
    public void test (ActionEvent event ) throws IOException{
        FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("testadmin.fxml"));
            Parent root2 = (Parent) fxmlLoader2.load();
            Stage stage2 = new Stage();
            stage2.setScene(new Scene(root2));
            stage2.setResizable(false);
            stage2.initStyle(StageStyle.UNDECORATED);
            stage2.initModality(Modality.WINDOW_MODAL);
             stage2.initStyle(StageStyle.UNDECORATED);
           ((Stage) main_pane.getScene().getWindow()).close(); 
            stage2.showAndWait();
        
 
       }
      public void medHistory (ActionEvent event ) throws IOException{
        FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("prescribeadmin.fxml"));
            Parent root2 = (Parent) fxmlLoader2.load();
            Stage stage2 = new Stage();
            stage2.setScene(new Scene(root2));
            stage2.setResizable(false);
            stage2.initStyle(StageStyle.UNDECORATED);
            stage2.initModality(Modality.WINDOW_MODAL);
             stage2.initStyle(StageStyle.UNDECORATED);
           ((Stage) main_pane.getScene().getWindow()).close(); 
            stage2.showAndWait();
        
 
       }  
    public void back() throws IOException
    {
    FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("loginfxml.fxml"));
            Parent root2 = (Parent) fxmlLoader1.load();
            Stage stage2 = new Stage();
            stage2.setScene(new Scene(root2));
            stage2.setResizable(false);
            stage2.initModality(Modality.WINDOW_MODAL);
             ((Stage) main_pane.getScene().getWindow()).close();
           
    }
    
}
