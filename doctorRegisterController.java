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
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sun.security.util.Password;

public class doctorRegisterController {
    @FXML
    private AnchorPane  main_pane;
    @FXML
    private Button close,submit;
    @FXML
    private TextField name , qualification,userid,contact,email;
    @FXML
    private TextArea padd;
    @FXML
    private DatePicker dob;
    @FXML
    private TextField password;
    @FXML
    private ToggleGroup gender;
    @FXML
    private RadioButton male,female,other;
    
   
    public void initialize() {
        male.setUserData("M");
        female.setUserData("F");
        other.setUserData("O");
       
    }
  
    
                    
    /*  public void submit(ActionEvent event ) throws IOException{
        FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("loginfxml.fxml"));
            Parent root2 = (Parent) fxmlLoader2.load();
            Stage stage2 = new Stage();
            stage2.setScene(new Scene(root2));
            stage2.setResizable(false);
            stage2.initModality(Modality.WINDOW_MODAL);
            stage2.initStyle(StageStyle.UNDECORATED);
            ((Stage) main_pane.getScene().getWindow()).close();
            stage2.showAndWait();
        
 
       }*/
       public void closeAction() {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
       }
    @SuppressWarnings("UseSpecificCatch")
       public void onRegister(ActionEvent actionEvent){
       
        String dname = name.getText();
        String dqualification = qualification.getText();
        String dpadd=padd.getText();
        String dcontact=contact.getText();
        String duserid=userid.getText();
        String demail=email.getText();
        String dpassword=password.getText();
        String ddob=(dob.getValue()).toString();//.format(DateTimeFormatter.ofPattern("yyyy-mm-dd"));
        String s= gender.getSelectedToggle().getUserData().toString();
        String sqlQuery; 
        int increment = 0;
        System.out.println(dname);       
          Node source = (Node) actionEvent.getSource();
        Stage currentStage = (Stage) source.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("loginfxml.fxml"));

     submit.setOnAction(e ->{
         if(ValidateName()&ValidateEmail()&ValidatePassword()){
     
        
       try{
                   Connection dbConnection = JDBC.databaseConnect();
//                 Statement st = dbConnection.createStatement();
//                sqlQuery = "SELECT  * FROM doctor";
//                ResultSet queryResult = st.executeQuery(sqlQuery);
//                if (queryResult.next()) {
//                    System.out.println(queryResult.getInt("dotor_id"));
//
//                }                
             

                
             PreparedStatement sqlStatement = dbConnection.prepareStatement("INSERT INTO  doctor VALUES (?,?,?,?,?,?,?,?,?)");

             sqlStatement.setString(1, dname);
                sqlStatement.setString(2, dqualification);
                sqlStatement.setString(3, dpadd);
                sqlStatement.setString(4, duserid);
                sqlStatement.setString(5,s );
                sqlStatement.setString(6, ddob);
                sqlStatement.setInt(7, Integer.parseInt(dcontact));
                sqlStatement.setString(8, demail);
                sqlStatement.setString(9, dpassword);
                sqlStatement.execute();
                
                 FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("loginfxml.fxml"));
            Parent root2 = (Parent) fxmlLoader2.load();
            Stage stage2 = new Stage();
            stage2.setScene(new Scene(root2));
            stage2.setResizable(false);
            stage2.initModality(Modality.WINDOW_MODAL);
            stage2.initStyle(StageStyle.UNDECORATED);
            ((Stage) main_pane.getScene().getWindow()).close();
            stage2.showAndWait();
                }
                  catch (Exception eux) {
                   System.out.print(eux);
                }
       
            
       
         }});  
       
       }


private boolean ValidateName(){
    Pattern p=Pattern.compile("[a-zA-z]+");
    Matcher m= p.matcher(name.getText());
    if(m.find()&&m.group().equals(name.getText()))
    {
        return true;
    }
    else{
        Alert alert=new Alert(AlertType.WARNING);
        alert.setTitle("validate name");
        alert.setHeaderText(null);
        alert.setContentText("enter validate name  ");
        alert.showAndWait();
        return false;
    }
    }
private boolean ValidateEmail(){
    Pattern p=Pattern.compile("[a-zA-z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)++");
    Matcher m= p.matcher(email.getText());
    if(m.find()&&m.group().equals(email.getText()))
    {
        return true;
    }
    else{
        Alert alert=new Alert(AlertType.WARNING);
        alert.setTitle("validate email");
        alert.setHeaderText(null);
        alert.setContentText("enter valid email  ");
        alert.showAndWait();
        return false;
    }
    }
private boolean ValidatePassword(){
    Pattern p=Pattern.compile("(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%]).{5,8}");
    Matcher m= p.matcher(password.getText());
    if(m.matches())
    {
        return true;
    }
    else{
        Alert alert=new Alert(AlertType.WARNING);
        alert.setTitle("validate password");
        alert.setHeaderText(null);
        alert.setContentText("enter valid password  ");
        alert.showAndWait();
        return false;
    }
    }
/*private boolean ValidateMobile(){
    Pattern p=Pattern.compile("(0/91)?[7-9][0-9]{9}");
    Matcher m= p.matcher(contact.getText());
    if(m.find()&&m.group().equals(contact.getText()))
    {
        return true;
    }
    else{
        Alert alert=new Alert(AlertType.WARNING);
        alert.setTitle("validate contact");
        alert.setHeaderText(null);
        alert.setContentText("enter valid contact  ");
        alert.showAndWait();
        return false;
    }
    }*/

}


