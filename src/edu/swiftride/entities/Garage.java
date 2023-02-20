/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.entities;

import java.util.Objects;

/**
 *
 * @author dhibi
 */
public class Garage {
    
    
    private int id ;
    private String matricule_garage;

    public Garage(int id, String matricule_garage) {
        this.id = id;
        this.matricule_garage = matricule_garage;
    }

    public Garage() {
    }

    public int getId() {
        return id;
    }

    public String getMatricule_garage() {
        return matricule_garage;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMatricule_garage(String matricule_garage) {
        this.matricule_garage = matricule_garage;
    }

    @Override
    public String toString() {
        return id + " - " + matricule_garage ;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Garage other = (Garage) obj;
        
        if (!Objects.equals(this.matricule_garage, other.matricule_garage)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
