/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.entities;

import javafx.scene.control.Label;

/**
 *
 * @author sami
 */
public class Avis {
    private int id;
    private String commentaire;
    private int etoile;
    private int id_voiture;
    private int id_client;
   
    
    public Avis() {
    }

    public Avis(int id,String commentaire, int etoile,int id_voiture,int id_client) {
        this.id = id;
        this.commentaire = commentaire;
        this.etoile = etoile;
        this.id_voiture = id_voiture;
        this.id_client = id_client;
 }

    public void setId(int id) {
        this.id = id;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public void setEtoile(int etoile) {
        this.etoile = etoile;
    }

    public void setId_voiture(int id_voiture) {
        this.id_voiture = id_voiture;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getId() {
        return id;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public int getEtoile() {
        return etoile;
    }

    public int getId_voiture() {
        return id_voiture;
    }

    public int getId_client() {
        return id_client;
    }
   
    
    @Override
    public String toString() {
        return "Avis: id : " +id+"commentaire : " +commentaire+"etoile : "+etoile+"id_voiture : "+id_voiture+"id_client : "+id_client;
    } 


   

   

  }
