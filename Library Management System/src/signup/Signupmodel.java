/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signup;

import database.SQliteConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.Alert;

/**
 *
 * @author Antoo5949
 */
public class Signupmodel {
    ///Alert.AlertType a= Error;

    public static void insert(String emailid, String userid, String password) throws SQLException {
        Connection conn = SQliteConnection.Connector();
        PreparedStatement preparedstaement = null;
        PreparedStatement userexist = null;
        ResultSet rs = null;
        String query2 = "INSERT INTO Data_Info(User_ID,User_Name,Email,Password) VALUES(?,?,?,?)";
        String query1 = "SELECT * from Data_Info WHERE Email=?";
        
        
        try {
            userexist = conn.prepareStatement(query1);
            userexist.setString(1, emailid);
            rs = userexist.executeQuery();
            if (rs.isBeforeFirst()) {
                System.out.println("User already exists!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("User already exists!");
                alert.setHeaderText(null);
                alert.setContentText("You can not use this email");
                alert.showAndWait();
            } else {
                preparedstaement = conn.prepareStatement(query2);
                preparedstaement.setString(1, emailid);
                preparedstaement.setString(2, userid);
                preparedstaement.setString(3, password);
                
                
                

                preparedstaement.executeUpdate();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thank You");
                alert.setHeaderText(null);
                alert.setContentText("Thamk you for Signing Up");
                alert.showAndWait();

            }

        } catch (SQLException e) {
            System.out.println(e);

        } finally {
            if (userexist != null) {
                try {
                    userexist.close();
                } catch (SQLException e) {

                    e.printStackTrace();
                }

            }
            if (preparedstaement != null) {

                try {
                    preparedstaement.close();
                } catch (SQLException e) {

                    e.printStackTrace();
                }

            }
        }

    }

    public static boolean validateEmail(String Emailid) {
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m = p.matcher(Emailid);
        /// System.out.println(m.group());
        if (m.find() && m.group().equals(Emailid)) {

            return true;

        } else {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Email");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter a valid Email");
            alert.showAndWait();

            return false;

        }

    }

    

    public static boolean Passwordvalidation(String password) {
        Pattern p = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\\W]).{6,20})");
        Matcher m = p.matcher(password);
        /// System.out.println(m.group());
        if (m.find() && m.group().equals(password)) {

            return true;

        } else {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Password");
            alert.setHeaderText(null);
            alert.setContentText("Your password must contain  contain 8 characters and at least one number, one letter and one unique character such as !#$%&?  ");
            alert.showAndWait();

            return false;

        }

    }

    public static boolean validateDob(LocalDate date) {
        try {
            Period diff = Period.between(date, LocalDate.now());
            if (diff.getYears() >= 18) {
                return true;
            } else {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Invalid age");
                alert.setHeaderText(null);
                alert.setContentText("You must be at least 18 years old to sign up");
                alert.showAndWait();

                return false;

            }
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Age");
            alert.setHeaderText(null);
            alert.setContentText("Please fill up The date of Birth");
            alert.showAndWait();
        }
        return false;

    }
}
