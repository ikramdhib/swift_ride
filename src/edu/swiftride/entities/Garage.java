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
        return "Garage{" + "id=" + id + ", matricule_garage=" + matricule_garage + '}';
    }
    
    
    
}
