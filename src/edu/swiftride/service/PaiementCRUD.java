/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.service;

import edu.swiftride.entities.paiement;
import edu.connexion3A17.interfaces.InterfaceCRUD;
import edu.connexion3A17.utils.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
/**
 *
 * @author admin
 */
public class PaiementCRUD implements InterfaceCRUD<paiement>{
    
    
     @Override
 public void ajouterEntitee(paiement paiement) {
        try {
            // Requête d'insertion avec paramètres
            String requete = "INSERT INTO paiement (id, num_carte, date, code, id_reservation) VALUES (?, ?, ?, ?, ?)";
               PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);

            // Affectation des valeurs aux paramètres
            pst.setInt(1, paiement.getId());
            pst.setInt(2, paiement.getNum_carte());
            pst.setDate(3, java.sql.Date.valueOf(paiement.getDate()));
            pst.setInt(4, paiement.getCode());
            pst.setInt(5, paiement.getId_reservation());

            // Exécution de la requête
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   
  
  public int getMaxPaiementId() {
    int maxId = 0;
    try {
        String query = "SELECT MAX(id) AS maxId FROM paiement";
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

    @Override
    public List<paiement> listeDesEntites() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifierReservation(paiement t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}


