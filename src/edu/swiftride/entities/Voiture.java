/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.entities;

import java.sql.Date;

/**
 *
 * @author user
 */
public class Voiture {
private   int id;
private String carteGrise;
private   String marque;
private   String  model;	
private    String  etat;	
private    String couleur ;	
private    String etat_technique	;
private    String matricule;
private    Date date_circulation	;		
private    double prix	;	
private int Kilometrage;
private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Voiture(int id, String carteGrise, String marque, String model, String etat, String couleur, String etat_technique, String matricule, Date date_circulation, double prix, int Kilometrage, int id_entreprise_partenaire, int id_utilsateur, String image) {
        this.id = id;
        this.carteGrise = carteGrise;
        this.marque = marque;
        this.model = model;
        this.etat = etat;
        this.couleur = couleur;
        this.etat_technique = etat_technique;
        this.matricule = matricule;
        this.date_circulation = date_circulation;
        this.prix = prix;
        this.Kilometrage = Kilometrage;
        this.id_entreprise_partenaire = id_entreprise_partenaire;
        this.id_utilsateur = id_utilsateur;
         this.image = image;

    }

    public int getKilometrage() {
        return Kilometrage;
    }

    public void setKilometrage(int Kilometrage) {
        this.Kilometrage = Kilometrage;
    }

   


private  int id_entreprise_partenaire ;
private int  id_utilsateur;

   

    

   


    public String getCarteGrise() {
        return carteGrise;
    }

    public void setCarteGrise(String carteGrise) {
        this.carteGrise = carteGrise;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getEtat_technique() {
        return etat_technique;
    }

    public void setEtat_technique(String etat_technique) {
        this.etat_technique = etat_technique;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public Date getDate_circulation() {
        return date_circulation;
    }

    public void setDate_circulation(Date date_circulation) {
        this.date_circulation = date_circulation;
    }

    

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getId_entreprise_partenaire() {
        return id_entreprise_partenaire;
    }

    public void setId_entreprise_partenaire(int id_entreprise_partenaire) {
        this.id_entreprise_partenaire = id_entreprise_partenaire;
    }

    public int getId_utilsateur() {
        return id_utilsateur;
    }

    public void setId_utilsateur(int id_utilsateur) {
        this.id_utilsateur = id_utilsateur;
    }


    public Voiture() {
    }

    @Override
    public String toString() {
        return "voiture{" + "id=" + id + ", marque=" + marque + ", model=" + model + ", etat=" + etat + ", couleur=" + couleur + ", etat_technique=" + etat_technique + ", matricule=" + matricule + ", date_circulation=" + date_circulation + ", prix=" + prix +  ", id_entreprise_partenaire=" + id_entreprise_partenaire + ", id_utilsateur=" + id_utilsateur + '}';
    }

    

    

    


}