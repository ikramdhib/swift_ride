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
public class Materiel {
    
    
    private int id ;
    private String nom ;
    private int id_garage;

    public Materiel() {
    }

    public Materiel(int id, String nom, int id_garage) {
        this.id = id;
        this.nom = nom;
        this.id_garage = id_garage;
    }


    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getId_garage() {
        return id_garage;
    }

    public void setId_garage(int id_garage) {
        this.id_garage = id_garage;
    }

    @Override
    public String toString() {
        return "Materiel{" + "id=" + id + ", nom=" + nom + ", id_garage=" + id_garage + '}';
    }
   
    
    
}
