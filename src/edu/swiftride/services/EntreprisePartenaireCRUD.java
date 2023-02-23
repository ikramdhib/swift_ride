/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.services;


import edu.swiftride.entities.EntreprisePartenaire;
import edu.swiftride.interfaces.InterfaceCRUD;
import edu.swiftride.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sami
 */
public class EntreprisePartenaireCRUD implements InterfaceCRUD{
   
       private Connection connection;
       

  public EntreprisePartenaireCRUD() {
    this.connection = MyConnection.getInstance().getConnexion();
  }

public void ajouter(EntreprisePartenaire m) {
       
    try {
        System.out.println("message");
      PreparedStatement preparedStatement;
        preparedStatement = MyConnection.getInstance().getConnexion().prepareStatement("insert into entreprise_partenaire(nom_entreprise,nom_admin,prenom_admin, nb_voiture,tel,matricule,login,mdp,id_admin) values (?,?,?,?,?,?,?,?,11212)");
           
      preparedStatement.setString(1, m.getNom_entreprise());
      preparedStatement.setString(2, m.getNom_admin());
      preparedStatement.setString(3, m.getPrenom_admin());
      preparedStatement.setInt(4, m.getNb_voiture());
      preparedStatement.setInt(5, m.getTel());
      preparedStatement.setString(6, m.getMatricule());
      preparedStatement.setString(7, m.getLogin());
      preparedStatement.setString(8, m.getMdp());
  
      
      
      
      
      
    preparedStatement.executeUpdate();
    } catch (SQLException e) {
    }
   
    }

    public void supprimer(EntreprisePartenaire m) {
       
    try {
      Statement statement = MyConnection.getInstance().getConnexion().createStatement();
    String  requete="delete from entreprise_partenaire where id='"+m.getId()+"'";
   
statement.executeUpdate(requete);

     
    } catch (SQLException e) {
    }
   
    }

    public boolean modifier(EntreprisePartenaire m) {
    boolean status = false;
    try {
    String query = "UPDATE entreprise_partenaire SET  nom_entreprise = '" + m.getNom_entreprise() + "', nom_admin = '" + m.getNom_admin() + "', prenom_admin = '" + m.getPrenom_admin() + "', nb_voiture = " + m.getNb_voiture()+", tel = " + m.getTel()+", matricule = '" + m.getMatricule()+"', login = '" + m.getLogin()+"', Mdp = '" + m.getMdp()+ "' WHERE id = " + m.getId() + " ";

      Statement Statement = MyConnection.getInstance().getConnexion().createStatement();
     
      Statement.executeUpdate(query);
      status = Statement.executeUpdate(query) > 0;
    } catch (SQLException e) {
    }
    return status;    
    }

    public List<EntreprisePartenaire> afficher() {

         List<EntreprisePartenaire> list = new ArrayList<EntreprisePartenaire>();
    try {
       
      PreparedStatement preparedStatement = MyConnection.getInstance().getConnexion().prepareStatement("select * from entreprise_partenaire");
      ResultSet rs = preparedStatement.executeQuery();
      while (rs.next()) {
       EntreprisePartenaire m = new EntreprisePartenaire();
        m.setId(rs.getInt("id"));
        m.setNom_entreprise(rs.getString("nom_entreprise"));
        m.setNom_admin(rs.getString("nom_admin"));
        m.setPrenom_admin(rs.getString("prenom_admin"));
        m.setNb_voiture(rs.getInt("nb_voiture"));
        m.setTel(rs.getInt("tel"));
        m.setMatricule(rs.getString("matricule"));
        m.setLogin(rs.getString("login"));
        m.setMdp(rs.getString("mdp"));
        
        list.add(m);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
    }

    @Override
    public void ajouterEntreprise(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean modifierEntreprise(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimerEntreprise(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List afficherEntreprise() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajouterAvis(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean modifierAvis(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimerAvis(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
    public List afficherAvis() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Iterable<EntreprisePartenaire> getListEntreprise() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Iterable<EntreprisePartenaire> getListeEntreprise() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

 

}






























