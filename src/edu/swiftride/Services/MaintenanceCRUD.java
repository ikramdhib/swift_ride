package edu.swiftride.Services;


import edu.swiftride.entities.Maintenance;
import edu.swiftride.interfaces.InterfaceCRUD;
import edu.swiftride.utils.Connect;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dhibi
 */
public class MaintenanceCRUD implements InterfaceCRUD<Maintenance>{

    @Override
    public boolean ajouterEntitie(Maintenance t) {
       int rslt = 0;
        
        try {
            String requet = "INSERT INTO maintenance(date_maintenance, type, fin_maintenance, id_voiture ) VALUES(?,?,?,?)";
            
            PreparedStatement pst = Connect.getInstance().getCnx().prepareStatement(requet);
            
            pst.setTimestamp(1, t.getDate_maintenance());
            
            pst.setString(2, t.getType());
            
            pst.setTimestamp(3, t.getFin_maintenance());
            
            pst.setInt(4, t.getId_voiture());
            
            rslt=pst.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       return rslt==1;
    }

    @Override
    public List<Maintenance> listDesEntites() {
         List<Maintenance> maintenances = new ArrayList();
        
        try {
            
            String request="SELECT * FROM maintenance" ;
            
            PreparedStatement pst = Connect.getInstance().getCnx().prepareStatement(request);
            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                
               Maintenance m = new Maintenance();
               
                m.setId(rs.getInt(1));
                m.setDate_maintenance(rs.getTimestamp(2));
                m.setType(rs.getString(3));
                m.setFin_maintenance(rs.getTimestamp(4));
                m.setId_voiture(rs.getInt(5));
                m.setId_garage(rs.getInt(6));
                
                maintenances.add(m);
                
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       return maintenances;
    }
    
    public List<Maintenance> getwithCarId(int id){
        
        List<Maintenance> maintenances = new ArrayList();
        try {
            String request="SELECT date_maintenance, type,fin_maintenance ,id_garage ,id FROM maintenance WHERE id_voiture='"+id+"'" ;
            
            PreparedStatement pst = Connect.getInstance().getCnx().prepareStatement(request);
            
            ResultSet rs = pst.executeQuery();
            
             while(rs.next()){
                
               Maintenance m = new Maintenance();
                
                m.setDate_maintenance(rs.getTimestamp(1));
                m.setType(rs.getString(2)); 
                m.setFin_maintenance(rs.getTimestamp(3));
                m.setId_garage(rs.getInt(4));
                m.setId(rs.getInt(5));
                maintenances.add(m);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return maintenances;
    }

    @Override
    public boolean modifierEntite(Maintenance t) {
  
        int rslt = 0;
        try {
            
            String request= "UPDATE maintenance  SET type='"+t.getType()+"' ,"
                    + " date_maintenance='"+t.getDate_maintenance()+"',fin_maintenance='"+t.getFin_maintenance()+"' ,"
            + " id_voiture='"+t.getId_voiture()+"'"
                    + " WHERE id='"+t.getId()+"'" ;
            
            PreparedStatement pst = Connect.getInstance().getCnx().prepareStatement(request);
            
            rslt=pst.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rslt==1;
    }

    @Override
    public boolean supprimerEntite(int id) {
        
           int rslt = 0 ;
            
        try {
            
            String request="DELETE FROM maintenance  WHERE id ='"+id+"'";
            
            PreparedStatement pst = Connect.getInstance().getCnx().prepareStatement(request);
            
            rslt=pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       return rslt==1;
    }
   
    public List getMaintenace(Maintenance t){
        List<Maintenance> maintenances = new ArrayList();
        
        try {
            String request ="SELECT * FROM maintenance WHERE id_voiture='"+t.getId_voiture()+"'"
                    + " AND date_maintenance='"+t.getDate_maintenance()+"' ";
            
            PreparedStatement pst = Connect.getInstance().getCnx().prepareStatement(request);
            
            ResultSet rs = pst.executeQuery();
         while(rs.next()){
                
               Maintenance m = new Maintenance();
                
                m.setId(rs.getInt(1));
                m.setDate_maintenance(rs.getTimestamp(2));
                m.setType(rs.getString(3));
                m.setFin_maintenance(rs.getTimestamp(4));
                m.setId_voiture(5);
                m.setId_garage(rs.getInt(6));
                
                maintenances.add(m);
                
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       return maintenances;
        
    }
    
       public List getMaintenaceWithDateAndGarageId(int id , Date d){
        List<Maintenance> maintenances = new ArrayList();
        
        try {
            String request ="SELECT * FROM maintenance WHERE id_garage='"+id+"'"
                    + " AND DATE(date_maintenance)='"+d+"' ";
            
            PreparedStatement pst = Connect.getInstance().getCnx().prepareStatement(request);
            
            ResultSet rs = pst.executeQuery();
         while(rs.next()){
                
               Maintenance m = new Maintenance();
                
                m.setId(rs.getInt(1));
                m.setDate_maintenance(rs.getTimestamp(2));
                m.setType(rs.getString(3));
                m.setFin_maintenance(rs.getTimestamp(4));
                m.setId_voiture(5);
                m.setId_garage(rs.getInt(6));
                
                maintenances.add(m);
                
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       return maintenances;
        
    }
       
         
    public boolean modifierEntiteGARAGE(Maintenance t) {
  
        int rslt = 0;
        try {
            
            String request= "UPDATE maintenance  SET id_garage='"+t.getId_garage()+"'"
                    + " WHERE id='"+t.getId()+"'" ;
            
            PreparedStatement pst = Connect.getInstance().getCnx().prepareStatement(request);
            
            rslt=pst.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rslt==1;
    }
    
}
