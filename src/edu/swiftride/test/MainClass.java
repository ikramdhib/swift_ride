/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.test;

import edu.swiftride.Services.MaintenanceCRUD;
import edu.swiftride.Services.GarageCRUD;
import edu.swiftride.Services.MaterielCRUD;
import edu.swiftride.Services.VoitureCRUD;
import edu.swiftride.entities.Garage;
import edu.swiftride.entities.Maintenance;
import edu.swiftride.entities.Materiel;
import java.io.PrintStream;
import java.sql.Date;

/**
 *
 * @author dhibi
 */
public class MainClass {
    
    public static void main(String[] args) {
        
        /****************TEST GARAGE CRUD************/
        Garage g = new Garage(2, "959");
        
        GarageCRUD gc = new GarageCRUD();
        
        
       // System.out.println(gc.ajouterEntitie(g));
        
        //System.out.println(gc.listDesEntites());
        
       // System.out.println(gc.modifierEntite(g));
        
       // System.out.println(gc.supprimerEntite(1));
       
       
       
       
       /***************TEST MATERIEL CRUD******************/
       
//        Materiel m = new Materiel(5, "koleb" ,2);
//        
          MaterielCRUD mc = new MaterielCRUD();
//        
//        System.out.println(mc.ajouterEntitie(m));
//        
//       System.out.println(mc.listDesEntites());
         // System.out.println(mc.modifierEntite(1, "coll"));
          //  System.out.println(mc.supprimerEntite(1));
         System.out.println(  mc.getDatas());
        /**************TEST MAINTENACE CRUUD***********/
//          Maintenance m = new Maintenance(4, Date.valueOf("1999-01-01") , "entretient", 1, 2);
//          Maintenance m2 = new Maintenance(3, Date.valueOf("1999-01-01") , "reparation", 1, 2);
//          MaintenanceCRUD mc = new MaintenanceCRUD();
//
        //System.out.println(mc.ajouterEntitie(m));
        // System.out.println(mc.modifierEntite(m2));
        // System.out.println(mc.listDesEntites());
        // System.out.println(mc.supprimerEntite(3));
        
        
        /******************TEST VOITURE*******************/
        VoitureCRUD vc = new VoitureCRUD();
        
        System.out.println(vc.diplayVoitures());
       
    }
    
}
