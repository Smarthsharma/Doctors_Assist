/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor.s.assist;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author MOHIT FARMAHA
 */
  

public class patientcontroller {
    @FXML
    private Button close,submit;
    @FXML
    private TextField name,contact,email;
    @FXML
    private TextArea add;
    @FXML
    DatePicker dob;
     @FXML
    private ToggleGroup gender;
    @FXML
    private RadioButton male,female,other;
    @FXML
    private AnchorPane main_pane;
   
    public void initialize() {
        male.setUserData("M");
        female.setUserData("F");
        other.setUserData("O");
       
    }
    
     public void closeAction() {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
       }
    
     public void onRegister(ActionEvent actionEvent){
       
        String pname = name.getText();
        String padd=add.getText();
        String pcontact=contact.getText();
        
        String pemail=email.getText();
        String pdob;//.format(DateTimeFormatter.ofPattern("yyyy-mm-dd"));
        String s= gender.getSelectedToggle().getUserData().toString();
        String sqlQuery; 
        int increment = 0;
               
          Node source = (Node) actionEvent.getSource();
        Stage currentStage = (Stage) source.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("prescriptionfxml.fxml"));

     submit.setOnAction(e ->{
         if(validate()&& ValidateName()&&ValidateEmail()){
       try{
                   Connection dbConnection = JDBC.databaseConnect();
                             
              PreparedStatement sqlStatement = dbConnection.prepareStatement("INSERT INTO  patient VALUES (?,?,?,?,?,?)");
               sqlStatement.setString(1, pname);
               sqlStatement.setString(2, (dob.getValue()).toString());   
               sqlStatement.setString(3,s ); 
               sqlStatement.setString(4, padd);

               sqlStatement.setString(5, pemail);
                sqlStatement.setInt(6, Integer.parseInt(pcontact));
               sqlStatement.execute();
                
               
                 FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("prescriptionfxml.fxml"));
            Parent root2 = (Parent) fxmlLoader2.load();
            Stage stage2 = new Stage();
            stage2.setScene(new Scene(root2));
            stage2.setResizable(false);
            stage2.initModality(Modality.WINDOW_MODAL);
            stage2.initStyle(StageStyle.UNDECORATED);
            ((Stage) main_pane.getScene().getWindow()).close();
            stage2.showAndWait();
                }catch (Exception ex) {
                   System.out.print(ex);
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
        Alert alert=new Alert(Alert.AlertType.WARNING);
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
        Alert alert=new Alert(Alert.AlertType.WARNING);
        alert.setTitle("validate email");
        alert.setHeaderText(null);
        alert.setContentText("enter valid email  ");
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
        Alert alert=new Alert(Alert.AlertType.WARNING);
        alert.setTitle("validate contact");
        alert.setHeaderText(null);
        alert.setContentText("enter valid contact  ");
        alert.showAndWait();
        return false;
    }
    }*/

private boolean validate(){
if(name.getText().equals("")||add.getText().equals("")||contact.getText().equals("")||email.getText().equals("")||dob.getValue()==null)
{Alert alert= new Alert(Alert.AlertType.NONE,"Fill the Blank Spaces",ButtonType.OK);
            alert.setTitle("Invalid");
            alert.show();
            return false;
}
else
    
        return true;
}
}

