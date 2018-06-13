/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor.s.assist;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static sun.security.jgss.GSSUtil.login;
import sun.security.util.Password;

/**
 * FXML Controller class
 *
 * @author MOHIT FARMAHA
 */
public class LoginfxmlController implements Initializable {
    //private final static String MY_PASS ="admin";
    //private final static BooleanProperty GRANTED_ACCESS=new SimpleBooleanProperty(false);
   // private final static int MAX_ATTEMPTS=5;
   // private final IntegerProperty ATTEMPTS=new SimpleIntegerProperty(0);
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    @FXML
    private Button close_button;
    
    @FXML
    private AnchorPane main_pane;
    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
             con=JDBC.databaseConnect();

    }    
   
    public void Signup(ActionEvent event ) throws IOException{
        FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("doctorregisterFXML.fxml"));
            Parent root2 = (Parent) fxmlLoader2.load();
            Stage stage2 = new Stage();
            stage2.setScene(new Scene(root2));
            stage2.setResizable(false);
            stage2.initModality(Modality.WINDOW_MODAL);
            stage2.initStyle(StageStyle.UNDECORATED);
            ((Stage) main_pane.getScene().getWindow()).close();
            stage2.showAndWait();        
    }
    public void Signin(ActionEvent event ) throws IOException{
        if(username.getText().equals("admin")&& password.getText().equals("doctor@2018"))
        {
           try{ 
            FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("Admin.fxml"));
            Parent root2 = (Parent) fxmlLoader2.load();
            Stage stage2 = new Stage();
            stage2.setScene(new Scene(root2));
            stage2.setResizable(false);
            stage2.initModality(Modality.WINDOW_MODAL);
             ((Stage) main_pane.getScene().getWindow()).close();
            stage2.showAndWait();}catch(Exception e){}  
        }
        else
        {          
        String loginUsername, loginPassword;
        loginUsername = username.getText();
        loginPassword = password.getText();
        if (loginUsername.equals("") || loginPassword.equals("")) {
            username.setText("");
            password.setText("");
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
                PreparedStatement pstmt=dbConnection.prepareStatement("SELECT * FROM doctor WHERE user_id= ? and password=? ");
                pstmt.setString(1,loginUsername);
                pstmt.setString(2,loginPassword);
                ResultSet userAccessResultSet = pstmt.executeQuery();
                if(userAccessResultSet.next()) {
                   System.out.println("hello");
                   Example.id=userAccessResultSet.getInt("doctor_id");
                    Example.name=userAccessResultSet.getString("name");
                    Example.contact=userAccessResultSet.getInt("contact");
                    Example.specialization=userAccessResultSet.getString("specialization");
                    Example.emailid=userAccessResultSet.getString("email_id");
                     System.out.println("hello");
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
                    Alert alert= new Alert(Alert.AlertType.NONE,"Invalid Username or Password",ButtonType.OK);
            alert.setTitle("Invalid");
            alert.showAndWait();
                }
                }
           
            catch(Exception e){}
       /* 
            
            */
      
        }}}
    
  
    public void closeAction() {
        Stage stage = (Stage) close_button.getScene().getWindow();
        stage.close();
    }    
}
