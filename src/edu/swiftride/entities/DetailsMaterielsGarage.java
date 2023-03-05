/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.entities;

/**
 *
 * @author dhibi
 */
public class DetailsMaterielsGarage {
    
    
   private  int id ; 
    private String nom ;
    private String garage;

    public DetailsMaterielsGarage(int id, String nom, String garage) {
        this.id = id;
        this.nom = nom;
        this.garage = garage;
    }

    public DetailsMaterielsGarage() {
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getGarage() {
        return garage;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setGarage(String garage) {
        this.garage = garage;
    }

    @Override
    public String toString() {
        return "DetailsMaterielsGarage{" + "id=" + id + ", nom=" + nom + ", garage=" + garage + '}';
    }
    
    
    
}
