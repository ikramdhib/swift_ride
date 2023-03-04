/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.services;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import edu.swiftride.entity.EntreprisePartenaire;
import edu.swiftride.entity.User;

import edu.swiftride.interfaces.InterfaceCRUD;
import edu.swiftride.utils.Connexion;
import edu.swiftride.utils.EncryptPassword;

/**
 *
 * @author skann
 */
public class UserCRUD implements InterfaceCRUD<User,EntreprisePartenaire> {

    @Override
    public void ajouterUtilisateur(User u) {

        try {

            String requete = "INSERT INTO utilisateur(nom,prenom,cin,date_naiss,age,num_permis,ville,num_tel,login,mdp,photo_personel,photo_permis,idrole)"
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement pst = Connexion.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, u.getNom());
            pst.setString(2, u.getPrenom());
            pst.setString(3, u.getCin());
            pst.setString(4, u.getDate_naiss());
            pst.setString(5, u.getAge());
            pst.setString(6, u.getNum_permis());
            pst.setString(7, u.getVille());
            pst.setString(8, u.getNum_tel());
            pst.setString(9, u.getEmail());
            pst.setString(10, u.getPassword());
            pst.setString(11, u.getPhoto_personel());
            pst.setString(12, u.getPhoto_permis());
            pst.setInt(13, u.getIdrole());
            pst.executeUpdate();
            System.out.println("Donnée insérés!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerUtilisateur(User t) {
        try {

            String requete = "DELETE from utilisateur where id = " + "'" + t.getId() + "'";
            Statement pst = Connexion.getInstance().getCnx().createStatement();
            pst.executeUpdate(requete);
            System.out.println("Done!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public boolean modifierUtilisateur(User t) {
        int test = 0;
        try {
            String requete = "UPDATE utilisateur SET login ='" + t.getEmail() + "'"
                    + ", mdp ='" + t.getPassword() + "' , num_tel = '" + t.getNum_tel() + "', nom = '" + t.getNom() + "',"
                    + " prenom = '" + t.getPrenom() + "', ville = '" + t.getVille() + "' WHERE id = '" + t.getId() + "'";
            PreparedStatement pst = Connexion.getInstance().getCnx().prepareStatement(requete);
            /*pst.setString(1, t.getEmail());
            pst.setString(2, t.getPassword());
            pst.setString(3, t.getNum_tel());
            pst.setString(4, t.getNom());
            pst.setString(5, t.getPrenom());
            pst.setString(6, t.getVille());
            pst.setInt(7, t.getId());*/
            test = pst.executeUpdate(requete);

            //System.out.println("Done!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return test == 1;
    }

    @Override
    public List<User> consulterListe() {
        List<User> myList = new ArrayList<>();
        String requete = "SELECT id,nom,prenom,cin,date_naiss,num_permis,ville,num_tel,login,age FROM utilisateur where idrole = "
                + "'" + 2 + "'";
        try {
            Statement st = Connexion.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            System.out.println("Done!");
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt(1));
                u.setNom(rs.getNString(2));
                u.setPrenom(rs.getNString(3));
                u.setCin(rs.getNString(4));
                u.setDate_naiss(rs.getNString(5));
                u.setNum_permis(rs.getNString(6));
                u.setVille(rs.getNString(7));
                u.setNum_tel(rs.getNString(8));
                u.setEmail(rs.getNString(9));
                u.setAge(rs.getNString(10));
                myList.add(u);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
    @Override
    public int histReserv(int id) {
        int nbr=0;
        String requete = "SELECT count(*) from reservation where id_client = "
                + "'" + id + "'";
        try {
            Statement st = Connexion.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            System.out.println("Done!");
            
                 while(rs.next())
        nbr=rs.getInt(1);
            

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nbr;
    }

    @Override
    public boolean emaildejaUtilise(String t) {
        boolean test = false;
        try {
            String requete = "SELECT * from utilisateur where login = " + "'" + t + "'";

            Statement st = Connexion.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            
               test = rs.next();
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(test);
        return test;
    }
    @Override
    public boolean emailEntreprisedejautilisé(String t) {
                boolean test = false;

        try {
            String requete = "SELECT * from entreprise_partenaire where login = " + "'" + t + "'";
            Statement st = Connexion.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
                           test = rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(UserCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return test;

    }

    @Override
    public boolean cindejaUtilise(String t) {
        boolean test = false;
        try {
            String requete = "SELECT * from utilisateur where cin = " + "'" + t + "'";
            Statement st = Connexion.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            test = rs.next();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return test;
    }

    @Override
    public boolean num_permidejaUtilise(String t) {
        boolean test = false;
        try {
            String requete = "SELECT * from utilisateur where num_permis = " + "'" + t + "'";
            Statement st = Connexion.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            test = rs.next();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return test;
    }

    @Override
    public boolean authentifier(User t) {
        boolean test = false;
        try {

            String requete = "SELECT * from utilisateur where login = " + "'" + t.getEmail() + "' and mdp = " + "'" + t.getPassword() + "'";
            Statement st = Connexion.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
             test = rs.next();
            System.out.println("Utilisateur trouve "+test);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return test;
    }
      @Override
    public boolean authentifierEntreprise(EntreprisePartenaire t) {
        boolean test = false;
        try {
            String requete = "SELECT * from entreprise_partenaire where login = " + "'" + t.getLogin() + "' and mdp = " + "'" + t.getMdp() + "'";
            Statement st = Connexion.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
           test = rs.next();
            System.out.println("Entreprise trouve "+test);
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return test;
    }

    @Override
    public User getUserByEmail(String email) {
        User u = new User();
        try {
            String requete = "SELECT id,nom,prenom,cin,num_permis,ville,num_tel,login,mdp,idrole,photo_personel,photo_permis,date_naiss,age FROM utilisateur where login = " + "'" + email + "'";
            Statement st = Connexion.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                u.setId(rs.getInt(1));
                u.setNom(rs.getNString(2));
                u.setPrenom(rs.getNString(3));
                u.setCin(rs.getNString(4));
                u.setNum_permis(rs.getNString(5));
                u.setVille(rs.getNString(6));
                u.setNum_tel(rs.getNString(7));
                u.setEmail(rs.getNString(8));
                u.setPassword(rs.getNString(9));
                u.setIdrole(rs.getInt(10));
                u.setPhoto_personel(rs.getNString(11));
                u.setPhoto_permis(rs.getNString(12));
                u.setDate_naiss(rs.getString(13));
                u.setAge(rs.getString(14));
                System.out.println("Get user by email Done!");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return u;
    }
    
    @Override
    public EntreprisePartenaire getEntrepriseByEmail(String  t) {
 EntreprisePartenaire e = new EntreprisePartenaire();
        try {
                        String requete = "SELECT id,nom_entreprise,nom_admin,prenom_admin,nb_voiture,tel,num_tel,matricule,login,mdp,id_admin,idrole FROM utilisateur where login = " + "'" + t + "'";
            Statement st = Connexion.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            if(rs.next())
            while (rs.next()) {
                e.setId(rs.getInt(1));
                e.setNom_entreprise(rs.getNString(2));
                e.setNom_admin(rs.getNString(3));
                e.setPrenom_admin(rs.getNString(4));
                e.setNb_voiture(rs.getInt(5));
                e.setTel(rs.getInt(6));
                e.setMatricule(rs.getNString(7));
                e.setLogin(rs.getNString(8));
                e.setMdp(rs.getNString(9));
                e.setId_admin(rs.getInt(10));
                e.setIdrole(rs.getInt(11));

                System.out.println("Get Entreprise by email Done!");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return e;
    }

    @Override
    public void uploadPhotoPersonnel(User t) throws IOException {
        File dossierDest = new File("C:/wamp6/www/img/PhotoPersonnel");
        if (!dossierDest.exists()) {
            dossierDest.mkdirs();
        }
        JFileChooser image_upload = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpeg", "jpg", "png");
        image_upload.setFileFilter(filter);
        image_upload.setAcceptAllFileFilterUsed(false);
        int res = image_upload.showSaveDialog(null);
        if (res == JFileChooser.APPROVE_OPTION) {
            InputStream input = null;
            OutputStream output = null;

            input = new DataInputStream(new FileInputStream(image_upload.getSelectedFile()));
            File imagedesination = new File(dossierDest, new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()) + image_upload.getSelectedFile().getName());
            output = Files.newOutputStream(imagedesination.toPath());
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
            t.setPhoto_personel(imagedesination.toPath().toString());

            input.close();
            output.close();

        }

    }

    @Override
    public void uploadPhotoPermis(User t) throws IOException {
        File dossierDest = new File("C:/wamp6/www/img/PhotoPermis");
        if (!dossierDest.exists()) {
            dossierDest.mkdirs();
        }
        JFileChooser image_upload = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpeg", "jpg", "png");
        image_upload.setFileFilter(filter);
        image_upload.setAcceptAllFileFilterUsed(false);
        int res = image_upload.showSaveDialog(null);
        if (res == JFileChooser.APPROVE_OPTION) {
            InputStream input = null;
            OutputStream output = null;

            input = new DataInputStream(new FileInputStream(image_upload.getSelectedFile()));
            File imagedesination = new File(dossierDest, new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()) + image_upload.getSelectedFile().getName());
            output = Files.newOutputStream(imagedesination.toPath());
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
            t.setPhoto_permis(imagedesination.toPath().toString());

            input.close();
            output.close();

        }

    }

    @Override
    public void updateAge(int id, String age) {
        try {
            String requete = "UPDATE utilisateur SET age = '" + age + "'"
                    + "WHERE id = '" + id + "'";

            PreparedStatement pst = Connexion.getInstance().getCnx().prepareStatement(requete);
            pst.executeUpdate(requete);

            System.out.println("update Age Done!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void updatePassword(String a) {
        try {
            String requete = "UPDATE utilisateur SET mdp = '" + EncryptPassword.toHexString(EncryptPassword.getSHA(a)) + "'";

            PreparedStatement pst = Connexion.getInstance().getCnx().prepareStatement(requete);
            pst.executeUpdate(requete);

            System.out.println("update password Done!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (NoSuchAlgorithmException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public int getIdrole(String a) {
        int idrole = 0;
        try {
            String requete = "SELECT idrole from utilisateur where login = " + "'" + a + "'";
            String requete1 = "SELECT idrole from entreprise_partenaire where login = " + "'" + a + "'";
            Statement st = Connexion.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            ResultSet rs1 = st.executeQuery(requete1);
            if (rs.next()) {
                return idrole = rs.getInt(1);
            } else {
                return idrole = rs1.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return idrole;

    }

    


    
    

}
