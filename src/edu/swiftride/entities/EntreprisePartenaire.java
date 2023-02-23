/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.entities;

/**
 *
 * @author sami
 */
public class EntreprisePartenaire {
   private int id;
    private String nom_entreprise;
    private String nom_admin;
    private String prenom_admin;
    private int nb_voiture;
    private int tel;
    private String matricule;
    private String login;
    private String mdp;
    private int id_admin;
   
    
    public EntreprisePartenaire() {
    }

    public EntreprisePartenaire(int id,String nom_entreprise, String nom_admin, String prenom_admin, int nb_voiture,int tel,String matricule,String login,String mdp,int id_admin) {
        this.id = id;
        this.nom_entreprise = nom_entreprise;
        this.nom_admin = nom_admin;
        this.prenom_admin = prenom_admin;
        this.nb_voiture = nb_voiture;
        this.tel = tel;
        this.matricule = matricule;
        this.login = login;
        this.mdp = mdp;
        this.id_admin=id_admin;
        
    }

   

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    public int getId_admin() {
        return id_admin;
    }


    public int getId() {
        return id;
    }

    public String getNom_entreprise() {
        return nom_entreprise;
    }

    public String getNom_admin() {
        return nom_admin;
    }

    public String getPrenom_admin() {
        return prenom_admin;
    }

    public int getNb_voiture() {
        return nb_voiture;
    }

    public int getTel() {
        return tel;
    }

    public String getMatricule() {
        return matricule;
    }

    public String getLogin() {
        return login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom_entreprise(String nom_entreprise) {
        this.nom_entreprise = nom_entreprise;
    }

    public void setNom_admin(String nom_admin) {
        this.nom_admin = nom_admin;
    }

    public void setPrenom_admin(String prenom_admin) {
        this.prenom_admin = prenom_admin;
    }

    public void setNb_voiture(int nb_voiture) {
        this.nb_voiture = nb_voiture;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

   
    
    @Override
    public String toString() {
        return "EntreprisePartenaire: id : " +id+"nom_entreprise : " +nom_entreprise+"nom_admin : "+nom_admin+"prenom_admin : "+prenom_admin+"nb_voiture : "+nb_voiture+"tel : "+tel+"matricule : "+matricule+"login : "+login+"mdp : "+mdp+"id_admin : "+id_admin;
    } 

    public Object getNom_Entreprise() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Object getTelephone() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Object getNomEntreprise() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  }
        
 
