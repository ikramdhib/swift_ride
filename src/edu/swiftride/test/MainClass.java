/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.test;

import edu.swiftride.Services.GarageCRUD;
import edu.swiftride.entities.Garage;

/**
 *
 * @author dhibi
 */
public class MainClass {
    
    public static void main(String[] args) {
        Garage g = new Garage(1, "garagelok12");
        
        GarageCRUD gc = new GarageCRUD();
        
        //System.out.println(gc.ajouterEntitie(g));
        
        //System.out.println(gc.listDesEntites());
        
        //System.out.println(gc.modifierEntite(1, "Garage Ali##12"));
        
        System.out.println(gc.supprimerEntite(1));
    }
    
}
