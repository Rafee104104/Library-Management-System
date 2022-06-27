/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signup;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Period;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import scenechanger.switchScene;
import signup.Signupmodel;


/**
 * FXML Controller class
 *
 * @author Antoo5949
 */
public class SignupController implements Initializable {

    /**
     * Initializes the controller class.
     */
    ///int v=10;
    switchScene a= new switchScene();

    
    @FXML
    private Button signinbtn;
    @FXML
    private TextField usernamebox;
    @FXML
    private Text incrrecttxt;
    @FXML
    private PasswordField passwordbox;
    @FXML
    private Text signuptxt;
    @FXML
    private TextField emailbox;

    
   ///String Address= address.getText();
    ///String Contactno= contactno.getText();
    ///String Emailid= emailid.getText();
    ///String Password= password.getText();
    //String Nidno= nidnumber.getText();
    ///String Userid= userid.getText();
    
    
   
    
    
    
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    } 
    
////    private boolean validateEmail(String Emailid){
////        Pattern p= Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
////        Matcher m= p.matcher(Emailid);
////        if(m.find()&& m.group().equals(Emailid))
////            return true;
////        else{
////              
////                Alert alert= new Alert(Alert.AlertType.WARNING);
////                alert.setTitle("Validate Email");
////                alert.setHeaderText(null);
////                alert.setContentText("Please Enter a valid Email");
////                alert.showAndWait();
////                
////                
////                return false;
////        
////        
////        
////        }
////    
////    
//    
//    }
    
    @FXML
    void back_to_login_page(ActionEvent event) throws IOException { 
           /// Parent root=FXMLLoader.load(getClass().getResource("/signin/Signin.fxml"));
            ///Scene scene1= new Scene(root);
            ///Stage window= (Stage) ((Node)event.getSource()).getScene().getWindow();
            //window.setScene(scene1);
            ///window.show();
            System.out.println("login_page");
            
            a.chngScene(event, "signin", "signin");
            
         
        }
    
    
    @FXML
    void signup(ActionEvent event) throws IOException, SQLException {
        
      //  Period diff= Period.between(dob.getValue(),LocalDate.now());
      //  System.out.println(diff.getYears()+"years");
        if(emailbox.getText().isEmpty() & passwordbox.getText().isEmpty() & usernamebox.getText().isEmpty()){
             Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Signup!!!");
            alert.setHeaderText(null);
            alert.setContentText("Please fill up the form");
            alert.showAndWait();

        
        }

        else if(Signupmodel.validateEmail(emailbox.getText()) & Signupmodel.Passwordvalidation(passwordbox.getText())){
            Signupmodel.insert(emailbox.getText(), usernamebox.getText(), passwordbox.getText());
            
           // Parent root=FXMLLoader.load(getClass().getResource("/signin/Signin.fxml"));
          //  Scene scene1= new Scene(root);
          //  Stage window= (Stage) ((Node)event.getSource()).getScene().getWindow();
           // window.setScene(scene1);
            //window.show();
            a.chngScene(event, "signup", "signup");
    }  
        
    
}
}
