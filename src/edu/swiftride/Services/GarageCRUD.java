/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.Services;

import edu.swiftride.entities.Garage;
import edu.swiftride.interfaces.InterfaceCRUD;
import edu.swiftride.utils.Connect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dhibi
 */
public class GarageCRUD implements InterfaceCRUD<Garage>{

    @Override
    public boolean ajouterEntitie(Garage t) {
        
        int reslt =0;
        try {
            String request = "INSERT INTO garage(matricule_garage) VALUES(?) ";
            
            PreparedStatement pst = Connect.getInstance().getCnx().prepareStatement(request);
            
            pst.setString(1, t.getMatricule_garage());
            
           reslt= pst.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
         return reslt==1;
        
    }

    @Override
    public List<Garage> listDesEntites() {
        
        List<Garage> garages = new ArrayList();
        
        try {
            String request = "SELECT * FROM garage";
            
            PreparedStatement pst = Connect.getInstance().getCnx().prepareStatement(request);
            
            ResultSet rs=  pst.executeQuery(request);
            
            while(rs.next()){
                
                Garage garage = new Garage();
                
                garage.setId(rs.getInt(1));
                garage.setMatricule_garage(rs.getString(2));
                
                garages.add(garage);
                
            }
            
        } catch (SQLException ex) {
            
            System.out.println(ex.getMessage());
            
        }
       return garages;
    }
    
    public List<Garage> displayGarages(){
        
        List<Garage> garages = new ArrayList();
        
        try {
            String request = "SELECT id, matricule_garage FROM garage";
            
            PreparedStatement pst = Connect.getInstance().getCnx().prepareStatement(request);
            
            ResultSet rs=  pst.executeQuery(request);
            
            while(rs.next()){
                
                Garage garage = new Garage();
                garage.setMatricule_garage(rs.getString(2));
                garage.setId(rs.getInt(1));
                garages.add(garage);
                
            }
            
        } catch (SQLException ex) {
            
            System.out.println(ex.getMessage());
            
        }
       return garages;
    }
    
        public List<Garage> getGaragewithMaint(int id){
        
        List<Garage> garages = new ArrayList();
        
        try {
            String request = "SELECT * FROM garage WHERE id='"+id+"'";
            
            PreparedStatement pst = Connect.getInstance().getCnx().prepareStatement(request);
            
            ResultSet rs=  pst.executeQuery(request);
            
            while(rs.next()){
                
                Garage garage = new Garage();
                garage.setId(rs.getInt(1));
                garage.setMatricule_garage(rs.getString(2));
                
                garages.add(garage);
                
            }
            
        } catch (SQLException ex) {
            
            System.out.println(ex.getMessage());
            
        }
       return garages;
    }

    @Override
    public boolean modifierEntite(Garage t) {
        
        int rslt = 0;
        
        try {
            
            String request = "UPDATE garage SET matricule_garage='"+t.getMatricule_garage()+"' WHERE id='"+t.getId()+"' ";
            
            PreparedStatement pst = Connect.getInstance().getCnx().prepareStatement(request);
            
            rslt=pst.executeUpdate();
            
        } catch (SQLException ex) {
            
            System.out.println(ex.getMessage());
        }
       return rslt==1;
    }

    @Override
    public boolean supprimerEntite(int id) {
        
        int rslt =0;
        
        try {
            String request = "DELETE FROM garage WHERE id='"+id+"'";
            
            PreparedStatement pst = Connect.getInstance().getCnx().prepareStatement(request);
            
            rslt=pst.executeUpdate();
            
        } catch (SQLException ex) {
            
        }
        
        return rslt==1;
        
    }
    
    public Garage getGaragewithID(int id){
        
         Garage garage = new  Garage();
        
        try {
            String request = "SELECT * FROM garage WHERE id='"+id+"'";
            
            PreparedStatement pst = Connect.getInstance().getCnx().prepareStatement(request);
            
            ResultSet rs=  pst.executeQuery(request);
            
            while(rs.next()){
                
                garage.setId(rs.getInt(1));
                garage.setMatricule_garage(rs.getString(2));
                
               
                
            }
            
        } catch (SQLException ex) {
            
            System.out.println(ex.getMessage());
            
        }
       return garage;
    }
    
   

   
    
    
    
    
}
