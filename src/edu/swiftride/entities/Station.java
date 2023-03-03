/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.entities;

/**
 *
 * @author Ines
 */
public class Station {
    int ids;
    String ville;
    String nom_station;
   

    public Station(){
        
    }

    public Station(int ids, String ville, String nom_station) {
        this.ids=ids;
        this.ville=ville;
        this.nom_station=nom_station;
        
    }

    public int getIds() {
        return ids;
    }

    public String getVille() {
        return ville;
    }

    public String getNom_station() {
        return nom_station;
    }

   
    public void setIds(int ids) {
        this.ids = ids;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setNom_station(String nom_station) {
        this.nom_station = nom_station;
    }

  
    
    @Override
    public String toString() {
        return "Station{" + "ids=" + ids + ", ville=" + ville + ", nom_station=" + nom_station + '}';
    }
    
    
}
