/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.Services;

import edu.swiftride.entities.Materiel;
import edu.swiftride.interfaces.InterfaceCRUD;
import edu.swiftride.interfaces.MaterielInterfaceCRUD;
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
public class MaterielCRUD implements MaterielInterfaceCRUD<Materiel> {

    @Override
    public boolean ajouterEntite(Materiel t, int id_G) {
        
        int rslt = 0;
        
        try {
            String requet = "INSERT INTO materiel(nom, id_garage) VALUES(?,?)";
            
            PreparedStatement pst = Connect.getInstance().getCnx().prepareStatement(requet);
            
            pst.setString(1, t.getNom());
            
            pst.setInt(2, id_G);
            
            rslt=pst.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       return rslt==1;
    }

    @Override
    public List<Materiel> listDesEntites() {
        
        List<Materiel> materiels = new ArrayList<Materiel>();
        
        try {
            
            String request="SELECT * FROM materiel" ;
            
            PreparedStatement pst = Connect.getInstance().getCnx().prepareStatement(request);
            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                
                Materiel m = new Materiel();
                
                m.setId(rs.getInt(1));
                m.setNom(rs.getString(2));
                
                materiels.add(m);
                
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       return materiels;
    }

    @Override
    public boolean modifierEntite(int id, String nom) {
    
            int rslt = 0;
        try {
            
            String request= "UPDATE materiel SET nom='"+nom+"' WHERE id='"+id+"'" ;
            
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
            
            String request="DELETE FROM materiel WHERE id ='"+id+"'";
            
            PreparedStatement pst = Connect.getInstance().getCnx().prepareStatement(request);
            
            rslt=pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       return rslt==1;
    }

   
    
    
}
