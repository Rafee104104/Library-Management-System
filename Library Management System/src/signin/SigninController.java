package signin;
import database.SQliteConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javax.swing.JOptionPane;
import scenechanger.switchScene;

public class SigninController {

    public SigninModel signmodel = new SigninModel();
    switchScene a = new switchScene();

    @FXML
    private TextField usernamebox;
    @FXML
    private TextField emailbox;
    @FXML
    private PasswordField passwordbox;
    @FXML
    private Button signinbtn;
    @FXML
    private Text signuptxt;
    @FXML
    private Text incrrecttxt;
    
    Connection conn=null;
    ResultSet res=null;
    PreparedStatement pst=null;

    @FXML
    public void initialize() {
        incrrecttxt.setDisable(true);
        if (signmodel.isDbConnected()) {
            System.out.println("Databse connected");
        } else {
            System.out.println("Database is not connected");
        }

    }
    @FXML
    private void signinbtnaction(ActionEvent event) throws IOException {

        
 
        conn=SQliteConnection.Connector();
        String sql;
        sql = "Select * from User_Information where User_Name=? and Email=? and Password=?";
	Integer ID=1;
        ID=++ID;
        
        String User_ID = ID.toString();
        //User_ID=usernamebox.getText();
        String userName=usernamebox.getText();
        String password=passwordbox.getText();
        String mailAdress=emailbox.getText();
        try{
            
           pst=conn.prepareStatement(sql);
           pst.setString(1,userName );
           pst.setString(2,mailAdress);
           pst.setString(3,password );
           
           res= pst.executeQuery();
           
           if(res.next())
           {
               
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("singin.fxml"));
                Parent root = loader.load();
                
                Scene tableViewScene = new Scene(root);
           
                //access the controller and call a method
                SigninController controller = loader.getController();
                controller.showProfileInfo(userName,mailAdress);
                
                //This line gets the Stage information
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                
                window.setScene(tableViewScene);
                window.show();            
           }else
               incrrecttxt.setVisible(true);
               //JOptionPane.showMessageDialog(null,"Invalid Username or Password");
               
           
        }catch(Exception e){
            
            JOptionPane.showMessageDialog(null,e);
            
        }
    
    }
    /// String username= username_button.getText();

    @FXML
    private void usernameboxactn(ActionEvent event) {
    }

    @FXML
    private void passwordboxact(ActionEvent event) {
    }
    @FXML
    public void disable_signinbtn() {
        String username = usernamebox.getText();
        String password = passwordbox.getText();
        boolean disablebtn = (username.isEmpty() || username.trim().isEmpty()) || (password.isEmpty() || password.trim().isEmpty());
        signinbtn.setDisable(disablebtn);
    }
    /*
    @FXML
    void signup(ActionEvent event) throws IOException {
        Parent asd = FXMLLoader.load(getClass().getResource("Signup.fxml"));
        Scene scene2 = new Scene(asd);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.show();
    }*/

    @FXML
    public void signupact(MouseEvent event) throws IOException {
         ((Node)event.getSource()).getScene().getWindow().hide();
          Stage primaryStage=new Stage();
         Parent root=FXMLLoader.load(getClass(). getResource("signup.fxml"));
         Scene src=new Scene(root);
          primaryStage.setTitle("Library Managent System");
          primaryStage.setScene(src);
           primaryStage.show();

        
    }

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private void showProfileInfo(String userName, String mailAdress) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
