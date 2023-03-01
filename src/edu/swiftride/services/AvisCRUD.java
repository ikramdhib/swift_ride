/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.services;

import edu.swiftride.entities.Avis;
import edu.swiftride.interfaces.InterfaceCRUD;
import edu.swiftride.utils.MyConnection;
import static java.awt.PageAttributes.MediaType.A;
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
public class AvisCRUD implements InterfaceCRUD {

    private Connection connection;

    public AvisCRUD() {
        this.connection = MyConnection.getInstance().getConnexion();
    }

    public void ajouterAvis(Avis m) {

        try {
            System.out.println("message");
            PreparedStatement preparedStatement;
            preparedStatement = MyConnection.getInstance().getConnexion().prepareStatement("insert into avis(commentaire,etoile,id_voiture,id_client) values (?,?,11212,11212)");

            preparedStatement.setString(1, m.getCommentaire());
            preparedStatement.setInt(2, m.getEtoile());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
        }

    }

    public List<Avis> afficherAvis() {

        List<Avis> list = new ArrayList<Avis>();
        try {

            PreparedStatement preparedStatement = MyConnection.getInstance().getConnexion().prepareStatement("select * from avis");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Avis m = new Avis();
                m.setId(rs.getInt("id"));
                m.setCommentaire(rs.getString("commentaire"));
                m.setEtoile(rs.getInt("etoile"));

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

    public boolean modifierAvis(Avis m) {
        boolean status = false;
        if (m == null) {
            return status;
        }
        try {
            String query = "UPDATE avis SET commentaire = ?, etoile = ? WHERE id = ?";
            Connection conn = MyConnection.getInstance().getConnexion();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, m.getCommentaire());
            pstmt.setInt(2, m.getEtoile());
            pstmt.setInt(3, m.getId());
            int rowCount = pstmt.executeUpdate();
            status = rowCount > 0;
        } catch (SQLException e) {
            // handle exception
            e.printStackTrace();
        }
        return status;
    }

    public void supprimer(Avis m) {

        try {
            Statement statement = MyConnection.getInstance().getConnexion().createStatement();
            String requete = "delete from avis where id='" + m.getId() + "'";

            statement.executeUpdate(requete);

        } catch (SQLException e) {
        }

    }

    @Override
    public void ajouterAvis(Object m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean modifierAvis(Object m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimerAvis(Object m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Iterable<Avis> getAvisList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
