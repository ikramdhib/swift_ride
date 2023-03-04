/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.service;

import edu.swiftride.entities.reservation;
import edu.connexion3A17.interfaces.InterfaceCRUD;
import edu.connexion3A17.utils.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author admin
 */
public class ReservationCRUD implements InterfaceCRUD<reservation>{
    
    
     @Override
public void ajouterEntitee(reservation r) {
    String requete = "INSERT INTO reservation(id, point_depart, destination, id_client, id_vehicule, temps_depart, distance, type_transport, prix) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try {
        PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
        pst.setInt(1, r.getId());
        pst.setString(2, r.getPoint_depart());
        pst.setString(3, r.getDestination());
        pst.setInt(4, r.getId_client());
        pst.setInt(5, r.getId_vehicule());
        pst.setString(6, r.getTemps_depart());
        pst.setFloat(7, r.getDistance());
        pst.setString(8, r.getType_transport());
        pst.setFloat(9, r.getPrix());
        pst.executeUpdate();
        System.out.println("Done!");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

  
    
      @Override
    public List<reservation> listeDesEntites() {
        List<reservation> myList=new ArrayList<>();
        String requete="SELECT * FROM reservation";
        try {
            Statement st = MyConnection.getInstance().getCnx().createStatement();
          ResultSet rs=  st.executeQuery(requete);
          while(rs.next()){
              reservation r = new reservation();
              r.setId(rs.getInt(1));
              r.setPoint_depart(rs.getNString(2));
              r.setDestination(rs.getNString(3));
               r.setId_client(rs.getInt(4));
              r.setId_vehicule(rs.getInt(5));
               r.setTemps_depart(rs.getNString(6));
               r.setDistance(rs.getFloat(7));
              r.setType_transport(rs.getString(8));
               r.setPrix(rs.getFloat(9));
              myList.add(r);
          }
         
          
        }catch (SQLException ex) {     
            System.out.println(ex.getMessage());
        }
        return myList;
    }
    
    public void supprimerReservation(int idReservation) {
    String requete = "DELETE FROM reservation WHERE id = ?";
    try {
        PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
        pst.setInt(1, idReservation);
        pst.executeUpdate();
        System.out.println("La réservation a été supprimée avec succès de la base de données !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}
    
   public void modifierReservation(reservation r) {
    String requete = "UPDATE reservation SET point_depart=?, destination=?, id_client=?, id_vehicule=?, temps_depart=? , distance=? , type_transport=? , prix=? WHERE id=?";
    try {
        PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
        pst.setString(1, r.getPoint_depart());
        pst.setString(2, r.getDestination());
        pst.setInt(3, r.getId_client());
        pst.setInt(4, r.getId_vehicule());
        pst.setString(5, r.getTemps_depart());
        pst.setFloat(6, r.getDistance());
        pst.setString(7, r.getType_transport());
        pst.setFloat(8, r.getPrix());
        pst.setInt(9, r.getId());
        pst.executeUpdate();
        System.out.println("La réservation a été modifiée avec succès.");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}
  public int getMaxReservationId() {
    int maxId = 0;
    try {
        String query = "SELECT MAX(id) AS maxId FROM reservation";
        Statement stmt = MyConnection.getInstance().getCnx().createStatement();
        ResultSet rs = stmt.executeQuery(query);
        if (rs.next()) {
            maxId = rs.getInt("maxId");
        }
        rs.close();
        stmt.close();

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return maxId;
}
    
}


