/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CartRental;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;

/**
 *
 * @author user
 */
public class database {
    
    public static Connection connectDb(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            // NOTE!! MAKE YOUR OWN DATABASE
            Connection connect = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/rentcar", "root", ""); // root is the default username while "" or empty is for the password
            return connect;
        }catch(Exception e){e.printStackTrace();}
        return null;
    }
}
