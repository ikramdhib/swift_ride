/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.entities;

import java.util.Objects;
import javafx.scene.control.CheckBox;

/**
 *
 * @author dhibi
 */
public class Materiel {
    
    
    private int id ;
    private String nom ;
    private int id_garage;
    private boolean chk;

    public void setChk(boolean chk) {
        this.chk = chk;
    }

    public boolean getChk() {
        return chk;
    }

    public Materiel() {
        
    }

    public Materiel(int id, String nom, int id_garage, boolean chk) {
        this.id = id;
        this.nom = nom;
        this.id_garage = id_garage;
        this.chk = chk;
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

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Materiel other = (Materiel) obj;
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        return true;
    }
   
    
    
}
