/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.services;


import edu.swiftride.entities.Voiture;
import edu.swiftride.interfaces.Intvoiture;
import edu.swiftride.utils.MyConnection;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author user
 */
public class VoitureCRUD implements Intvoiture {
  
    Connection cnx2;
    public VoitureCRUD(){
        cnx2 = MyConnection.getInstance().getCnx();
    }
   public void supprimercar(Voiture voiture) {
        try {
            String requete="delete from voiture where id=?";
            PreparedStatement pst = cnx2.prepareStatement(requete);
            pst.setInt(1,voiture.getId());
            pst.executeUpdate();
           
            System.out.println("car est supprimée");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }}
  
   

 

  public void modifier(Voiture car) {
  
    try {
    String query = "UPDATE voiture SET CarteGrise = '" + car.getCarteGrise() + "', marque = '" + car.getMarque() + "', model = '" + car.getModel() + "', etat = '" + car.getEtat() + "', " + "couleur = '" + car.getCouleur() + "', etat_technique = '" + car.getEtat_technique() + "', matricule = '" + car.getMatricule() + "', date_circulation = '" + car.getDate_circulation() + "', prix = '" + car.getPrix() + "', id_entreprise_partenaire = '" + car.getId_entreprise_partenaire() + "', Kilometrage = '" + car.getKilometrage() + "'  WHERE  id = '" + car.getId() + "'";

 Statement Statement =MyConnection.getInstance().getCnx().createStatement();
     
      Statement.executeUpdate(query);
      
    } catch (SQLException e) {
    }
     
    }
    
    
    public void ajoutercar(Voiture v) {
       
    try {
        System.out.println("message");
      PreparedStatement preparedStatement = MyConnection.getInstance().getCnx().prepareStatement("insert into voiture( CarteGrise,marque,model,etat,couleur,etat_technique,matricule,date_circulation,prix,id_entreprise_partenaire,id_utilisateur,Kilometrage,image) values (?,?,?,?,?,?,?,?,?,?,?,?,?)");
           
      preparedStatement.setString(1, v.getCarteGrise());
      preparedStatement.setString(2, v.getMarque());
      preparedStatement.setString(3, v.getModel());
      preparedStatement.setString(4, v.getEtat());
      preparedStatement.setString(5, v.getCouleur());
      preparedStatement.setString(6, v.getEtat_technique());
      preparedStatement.setString(7, v.getMatricule());
      preparedStatement.setDate(8, v.getDate_circulation());
      preparedStatement.setDouble(9,v.getPrix());
      preparedStatement.setInt(10, v.getId_entreprise_partenaire());
      preparedStatement.setInt(11, v.getId_utilsateur());
      preparedStatement.setInt(12, v.getKilometrage());
       preparedStatement.setString(13, v.getImage());
    preparedStatement.executeUpdate();
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
   
    }
      
      
      public List<Voiture> diplayVoitures(){
        
           List<Voiture> materiels = new ArrayList();
           String a ="pane";
        
        try {
            
            String request="SELECT id, marque FROM voiture WHERE etat_technique='"+a+"'" ;
            
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(request);
            
            ResultSet rs = pst.executeQuery();
//            
//            MaintenanceCRUD mc = new MaintenanceCRUD();
//            List<Maintenance> mm ;
            
            while(rs.next()){
                
                Voiture m = new Voiture();
                
                m.setId(rs.getInt(1));
                m.setMarque(rs.getString(3));
                
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
            
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(request);
            
            ResultSet rs = pst.executeQuery();
            
//            MaintenanceCRUD mc = new MaintenanceCRUD();
//            List<Maintenance> mm ;
            
            while(rs.next()){
                
                Voiture m = new Voiture();
                
                m.setId(rs.getInt(1));
                m.setMarque(rs.getString(3));
                m.setModel(rs.getString(4));
                m.setCouleur(rs.getString(6));
                m.setEtat_technique(rs.getString(7));
                m.setMatricule(rs.getString(8));
                m.setDate_circulation(rs.getDate(9));
                m.setKilometrage(rs.getInt(13));
                m.setId_entreprise_partenaire(rs.getInt(11));
                
                materiels.add(m);
                
                
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       return materiels;
        
    }
      
      
      public Voiture getVoitureWithMatriculAndMarque(String m , String m2){
          Voiture mm = new Voiture();
        try {
            String request="SELECT id, marque FROM voiture WHERE marque='"+m+"' "
                    + "AND matricule='"+m2+"'" ;
            
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(request);
            
            ResultSet rs = pst.executeQuery();
             
             while(rs.next()){
                mm.setId(rs.getInt(1));
                mm.setMarque(rs.getString(3));
                
                
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         return mm; 
      }
}