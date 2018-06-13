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
import java.util.Calendar;
import java.util.Iterator;
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
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author MOHIT FARMAHA
 */
public class PrescriptionController {

    ObservableList<String> dose1list=FXCollections.observableArrayList("OD","BD","TD","QD");
   @FXML
   private TextArea symptom,test1;

   private ObservableList<Test> ddata=FXCollections.observableArrayList();
   @FXML
   private TextField day1,day2,day3;
   @FXML
   private ChoiceBox dose1,dose2,dose3;
   @FXML
    private ComboBox med1,med2,med3;
  @FXML
   private Label doc,specialization,contact,patid,name,sex,age,date;
    @FXML
    private AnchorPane main_pane2;
    @FXML
    private AnchorPane main_pane3;
   @FXML
    private TextField patientid;
    @FXML
    private TextField searchField;
    @FXML
    private TextField name1;
    @FXML
    private TableColumn<Test,String> tname;
    @FXML
    private TableView<Test> ttt;
     @FXML
    private Button close_button;
    private ObservableList<Patient>data;
    private Connection conn =null;
    private PreparedStatement pst=null;
    private ResultSet rs=null;
    String dat;
   int r=0,r1=0;
     private Connection con =null;
    public void initialize()
    {
         Calendar dateToday = Calendar.getInstance();
            int year, day, month;
            year = dateToday.get(Calendar.YEAR);
            month = dateToday.get(Calendar.MONTH) + 1;
            day = dateToday.get(Calendar.DAY_OF_MONTH);
            dat = year + "-" + month + "-" + day;
            date.setText(dat);
            
        try{
        doc.setText(Example.name);
        specialization.setText(Example.specialization);
        contact.setText(Integer.toString(Example.contact));
        
        //pid.setText(pp.id);
        //name.setText(pp.name);
        //sex.setText(pp.gender);
        fillComboBox();    
        dose1.setItems(dose1list);
        dose2.setItems(dose1list);
        dose3.setItems(dose1list);
        dose1.setValue("OD"); 
        dose2.setValue("OD"); 
        dose3.setValue("OD");
        setCellTable();
        loadData();
        rowClicked();
        searchTable();
         System.out.println("hello");}catch(Exception e){e.printStackTrace();}
    }
    public void searchTable(){
          FilteredList<Test> filteredData= new FilteredList<Test>(ddata, e->true);
    searchField.setOnKeyReleased(e -> {
            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super Test>) Test -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                }
                    String lowerCaseFilter=newValue.toLowerCase();
               
                if(Test.getName().toLowerCase().contains(lowerCaseFilter)){
                return true;
            }
                return false;
            });
            });
        SortedList<Test> sortedData=new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(ttt.comparatorProperty());
        ttt.setItems(sortedData);   
    });
    }
   public void fillComboBox()
   {
        try {
             conn=JDBC.databaseConnect();
            
            String query ="Select mname,composition from medicine";
            pst =conn.prepareStatement(query);
            rs=pst.executeQuery();
             while(rs.next()){ 
             med1.getItems().add(rs.getString("mname")+"("+rs.getString("composition")+")");              
             med2.getItems().add(rs.getString("mname")+"("+rs.getString("composition")+")");               
             med3.getItems().add(rs.getString("mname")+"("+rs.getString("composition")+")");               
             }
            // med1.getSelectionModel().selectFirst();
            // med2.getSelectionModel().selectFirst();
            // med3.getSelectionModel().selectFirst();
  pst.close();
  rs.close();
   }
           
         catch (SQLException ex) {
            Logger.getLogger(PrescriptionController.class.getName()).log(Level.SEVERE, null, ex);
        }
   
   }
  
    
    public void Addnewmed(ActionEvent event ) throws IOException{
        FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("Addmedicine.fxml"));
            Parent root2 = (Parent) fxmlLoader2.load();
            Stage stage2 = new Stage();
            stage2.setScene(new Scene(root2));
            stage2.setResizable(false);
            stage2.initModality(Modality.WINDOW_MODAL);
            stage2.initStyle(StageStyle.UNDECORATED);
            
            stage2.showAndWait();
        
    }
    public void Existing(ActionEvent event ) throws IOException{
        FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("ExistingPatients.fxml"));
            Parent root2 = (Parent) fxmlLoader2.load();
            Stage stage2 = new Stage();
            stage2.setScene(new Scene(root2));
            stage2.setResizable(false);
            stage2.initModality(Modality.WINDOW_MODAL);
            stage2.initStyle(StageStyle.UNDECORATED);
            
            stage2.showAndWait();
            
        
    }
    public void Addnew(ActionEvent event ) throws IOException{
        FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("patientFXML.fxml"));
            Parent root2 = (Parent) fxmlLoader2.load();
            Stage stage2 = new Stage();
            stage2.setScene(new Scene(root2));
            stage2.setResizable(false);
            stage2.initModality(Modality.WINDOW_MODAL);
            stage2.initStyle(StageStyle.UNDECORATED);
            
            stage2.showAndWait();
        
    }
    
    public void prescribetest(ActionEvent event ) throws IOException{
        FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("prescribeTest.fxml"));
            Parent root2 = (Parent) fxmlLoader2.load();
            Stage stage2 = new Stage();
            stage2.setScene(new Scene(root2));
            stage2.setResizable(false);
            stage2.initModality(Modality.WINDOW_MODAL);
            stage2.initStyle(StageStyle.UNDECORATED);
          
            stage2.showAndWait();
        
    }
   public void add(ActionEvent event ) throws IOException{
       
        
        String username;
        int pid;
        username = name1.getText();
        pid = Integer.parseInt(patientid.getText());
        if (username.equals("") || Integer.toString(pid).equals("")) {
            name1.setText("");
            patientid.setText("");
             Alert alert= new Alert(Alert.AlertType.NONE,"Fill the Blank Spaces",ButtonType.OK);
            alert.setTitle("Invalid");
            alert.show();
             
        }
           else {
           
            try {
                
                Connection dbConnection = JDBC.databaseConnect();
                PreparedStatement pstmt=dbConnection.prepareStatement("SELECT * FROM patient WHERE contact=? and name=?");
                pstmt.setInt(1,pid);
                pstmt.setString(2,username);
                ResultSet userAccessResultSet = pstmt.executeQuery();
                if(userAccessResultSet.next()){
                    
                    pp.name=userAccessResultSet.getString("name");
                    pp.id=userAccessResultSet.getString("patient_id");
                    pp.dob=userAccessResultSet.getString("dob");
                    pp.gender=userAccessResultSet.getString("gender");
                    pp.contact=userAccessResultSet.getString("contact");
                        name.setText(pp.name);
                        patid.setText(pp.id);
                        age.setText(pp.dob);
                        sex.setText(pp.gender);
                       // contact.setText(pp.contact);
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
      
      
        } }  
    
public void save(ActionEvent actionEvent){
    String docname=doc.getText();
    String docid= Integer.toString(Example.id);
    String patientname=name.getText();
    String patientid=patid.getText();
    String psymptoms=symptom.getText();
    String med01= med1.getValue().toString();
    String day01=day1.getText();
    String dose01=dose1.getValue().toString();
    String med02= med2.getValue().toString();
    String day02=day2.getText();
    String dose02=dose2.getValue().toString();
    String med03= med3.getValue().toString();
    String day03=day3.getText();
    String dose03=dose3.getValue().toString();
    String tests=test1.getText();


   try{
                   Connection dbConnection = JDBC.databaseConnect();
                             
              PreparedStatement sqlStatement = dbConnection.prepareStatement("INSERT INTO  prescription VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
               sqlStatement.setInt(1, Integer.parseInt(docid));
               sqlStatement.setInt(2,Integer.parseInt(patientid));
               sqlStatement.setString(3,psymptoms);
               sqlStatement.setString(4,tests);
               sqlStatement.setString(5,med01);
               sqlStatement.setInt(6,Integer.parseInt(day01));
               sqlStatement.setString(7,dose01);
               sqlStatement.setString(8,med02);
               sqlStatement.setInt(9,Integer.parseInt(day02));
               sqlStatement.setString(10,dose02);
               sqlStatement.setString(11,med03);
               sqlStatement.setInt(12,Integer.parseInt(day03));
               sqlStatement.setString(13,dose03);
               sqlStatement.setString(14,patientname);
               sqlStatement.setString(15,docname);
               sqlStatement.setString(16,dat);

               sqlStatement.execute();
                

}       catch (SQLException ex) {
            Logger.getLogger(PrescriptionController.class.getName()).log(Level.SEVERE, null, ex);
        }

}
private void setCellTable() 
    {
        tname.setCellValueFactory(new PropertyValueFactory<Test,String>("name"));
        
    }
    private void loadData()
    {
        try {

            con=JDBC.databaseConnect();
            Statement st=con.createStatement();
            rs =st.executeQuery("select name from test");
            while(rs.next())
            {           
               ddata.add(new Test(rs.getString("name")));
                        }
        } catch (Exception e) {
           e.printStackTrace();
        }
        ttt.setItems(ddata);
    
    }
    void rowClicked()
    {
        ttt.setRowFactory( tv -> {
            TableRow<Test> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    int k=1;
                    int rowNumber= row.getIndex();
                    Iterator<Test> iterate=ddata.iterator();
                    while(iterate.hasNext()) {
                        Test ord = iterate.next();
                        if(k==(rowNumber+1))
                        {
                            r++;
                          if(test1.getText().contains("Prescribed") && (r1==0))
                          {
                              test1.setText(test1.getText()+"\n"+ord.getName());
                              r1=1;
                          }
                          else if(test1.getText().contains(ord.getName()))
                          {
                              r--;
                          }
                          else
                            test1.setText(test1.getText()+","+ord.getName());
                          if(r>2)
                          {
                              test1.setText(test1.getText()+"\n");
                              r=0;
                          }
                          break;
                        }
                        k++;
                    }


                }
            });
            return row ;
        });

    }
  public void viewPrescription(ActionEvent event ) throws IOException{
        FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("prescribeadmin.fxml"));
            Parent root2 = (Parent) fxmlLoader2.load();
            Stage stage2 = new Stage();
            stage2.setScene(new Scene(root2));
            stage2.setResizable(false);
            stage2.initModality(Modality.WINDOW_MODAL);
            stage2.initStyle(StageStyle.UNDECORATED);
            
            stage2.showAndWait();
        
    }
}

 