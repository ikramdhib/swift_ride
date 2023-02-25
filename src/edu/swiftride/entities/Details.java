/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.entities;

import java.sql.Date;

/**
 *
 * @author dhibi
 */
public class Details {
   private String marque ;
   private String model;
   private String matricule ;
   private Date date_maintenance;
   private String type ;
   private String intitule;
   private String materiels;

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public Details() {
    }

    public String getMateriels() {
        return materiels;
    }

    public void setMateriels(String materiels) {
        this.materiels = materiels;
    }

    public Details(String marque, String model, String etat_technique, String couleur, String matricule, Date date_circulation, Date date_maintenance, String type, String intitule, String materiels) {
        this.marque = marque;
        this.model = model;
        this.matricule = matricule;
        this.date_maintenance = date_maintenance;
        this.type = type;
        this.intitule = intitule;
        this.materiels = materiels;
    }

  


    public String getMarque() {
        return marque;
    }

    public String getModel() {
        return model;
    }


    public String getMatricule() {
        return matricule;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public void setModel(String model) {
        this.model = model;
    }

    

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }


    public void setDate_maintenance(Date date_maintenance) {
        this.date_maintenance = date_maintenance;
    }

    public void setType(String type) {
        this.type = type;
    }


    public Date getDate_maintenance() {
        return date_maintenance;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Details{" + "marque=" + marque + ", model=" + model +  ", matricule=" + matricule +  ", date_maintenance=" + date_maintenance + ", type=" + type + ", intitule=" + intitule + ", materiels=" + materiels + '}';
    }

  

    

    
   
    

   
    
    
    
}
