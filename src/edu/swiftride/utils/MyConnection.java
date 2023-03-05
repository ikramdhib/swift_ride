/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author user
 */
public class MyConnection {
    String url="jdbc:mysql://localhost:3306/swiftride";
    String login="root";
    String pwd="";
    private Connection cnx ;
    private static MyConnection instance;
   

    public MyConnection() {
        try {
           cnx= DriverManager.getConnection(url, login ,pwd);
            System.out.println("connexion étabile");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    public static  MyConnection getInstance(){
    if (instance ==null){
    instance = new MyConnection() ;
    }
    
        return instance;
        }
    
    
     public Connection getCnx() {
        return cnx;
    }
    
    
}
