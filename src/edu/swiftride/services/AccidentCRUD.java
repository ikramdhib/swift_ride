/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.services;

import edu.swiftride.entities.Accident;
import edu.swiftride.interfaces.InAccident;

import edu.swiftride.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author user
 */
public class AccidentCRUD implements InAccident{
    
    Connection cnx2;
    
       
    
    public void ajouteraccident(Accident acc)  {
       
    try {
        System.out.println("message");
      PreparedStatement preparedStatement = MyConnection.getInstance().getCnx().prepareStatement("insert into accident( type,date,description,lieu,id_voiture) values (?,?,?,?,?)");
           
      preparedStatement.setString(1, acc.getType());
      preparedStatement.setDate(2, acc.getDateac());
      preparedStatement.setString(3, acc.getDescription());
      preparedStatement.setString(4, acc.getLieu());
      preparedStatement.setString(5, acc.getId_voiture());
      
       
    preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    }
    public void supprimeraccident(Accident a){
   try {
            String requete="delete from accident where id=?";
             cnx2 = MyConnection.getInstance().getCnx();
            PreparedStatement pst = cnx2.prepareStatement(requete);
            pst.setInt(1,a.getId());
            pst.executeUpdate();
           
            System.out.println("car est supprimée");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }}


    public void modifieracc(Accident acc) {
    
    try {
    
String query = "UPDATE accident SET type = '" + acc.getType() + "', date = '" + acc.getDateac()
+ "', description = '" + acc.getDescription() + "', lieu = '" + acc.getLieu() + "', id_voiture = '"+acc.getId_voiture()+ "' "
+ "WHERE id = " + acc.getId() + "";

      Statement Statement = MyConnection.getInstance().getCnx().createStatement();
     
      Statement.executeUpdate(query);
      
    } catch (SQLException e) {
    }
     
    }

    

}