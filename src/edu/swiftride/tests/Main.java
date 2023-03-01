package edu.swiftride.tests;

import edu.swiftride.entities.Avis;
import edu.swiftride.entities.EntreprisePartenaire;
import edu.swiftride.services.AvisCRUD;
import edu.swiftride.services.EntreprisePartenaireCRUD;
import edu.swiftride.utils.MyConnection;

public class Main {

    public static void main(String[] args) {
        // Load the JDBC driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("JDBC driver loaded");
        } catch (ClassNotFoundException ex) {
            System.err.println("Failed to load JDBC driver");
            return;
        }

        // Establish a database connection
        MyConnection myConnection = MyConnection.getInstance();
        if (myConnection == null) {
            System.err.println("Failed to establish a database connection");
            return;
        }

        EntreprisePartenaire e = new EntreprisePartenaire();
        e.setNom_entreprise("My Company");
        e.setNom_admin("John Doe");
        e.setPrenom_admin("Jane Smith");
        e.setNb_voiture(5);
        e.setTel(123456789);
        e.setMatricule("ABC123");
        e.setLogin("myusername");
        e.setMdp("mypassword");
        e.setId_admin(11212);
        EntreprisePartenaireCRUD ecd = new EntreprisePartenaireCRUD();
        ecd.ajouterEntreprise(e);
        System.out.println(ecd.afficherEntreprise());

        Avis a = new Avis();
        a.setCommentaire("Bla Bla");
        a.setEtoile(5);
        a.setId_voiture(61212);
        a.setId_client(02324);
        AvisCRUD ecdd = new AvisCRUD();
        ecdd.ajouterAvis(a);
        System.out.println(ecdd.afficherAvis());
    }
}
