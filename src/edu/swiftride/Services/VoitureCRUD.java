/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.Services;

import edu.swiftride.entities.Maintenance;
import edu.swiftride.entities.Voiture;
import edu.swiftride.utils.Connect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dhibi
 */
public class VoitureCRUD {
    
    public List<Voiture> diplayVoitures(){
        
           List<Voiture> materiels = new ArrayList();
        
        try {
            
            String request="SELECT id, marque FROM voiture" ;
            
            PreparedStatement pst = Connect.getInstance().getCnx().prepareStatement(request);
            
            ResultSet rs = pst.executeQuery();
            
            MaintenanceCRUD mc = new MaintenanceCRUD();
            List<Maintenance> mm ;
            
            while(rs.next()){
                
                Voiture m = new Voiture();
                
                m.setId(rs.getInt(1));
                m.setMarque(rs.getString(2));
                
                materiels.add(m);
                
                
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       return materiels;
        
    }
    
      public List<Voiture> diplayDetailVoitures(){
        
           List<Voiture> materiels = new ArrayList();
        
        try {
            
            String request="SELECT * FROM voiture" ;
            
            PreparedStatement pst = Connect.getInstance().getCnx().prepareStatement(request);
            
            ResultSet rs = pst.executeQuery();
            
            MaintenanceCRUD mc = new MaintenanceCRUD();
            List<Maintenance> mm ;
            
            while(rs.next()){
                
                Voiture m = new Voiture();
                
                m.setId(rs.getInt(1));
                m.setMarque(rs.getString(2));
                m.setModel(rs.getString(3));
                m.setCouleur(rs.getString(5));
                m.setEtat_technique(rs.getString(6));
                m.setMatricule(rs.getString(7));
                m.setDate_circulation(rs.getDate(8));
                
                materiels.add(m);
                
                
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       return materiels;
        
    }
    
}
