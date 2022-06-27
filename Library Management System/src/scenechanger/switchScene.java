/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scenechanger;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;




/**
 *
 * @author Antoo5949
 */
public class switchScene {
    
    public switchScene(){
    
    
    
    }
    
   
    public   void chngScene(ActionEvent event,String packagename,String fxmls) throws IOException{
        Parent root=FXMLLoader.load(getClass().getResource("/"+packagename+"/"+fxmls+".fxml"));
        Scene scene1= new Scene(root);
        Stage window= (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene1);
        window.show();
               
     }
}
