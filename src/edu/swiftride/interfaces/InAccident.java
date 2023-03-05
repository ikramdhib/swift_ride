/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.interfaces;

import edu.swiftride.entities.Accident;



/**
 *
 * @author user
 */
public interface InAccident {
     void supprimeraccident(Accident a);
     void modifieracc(Accident acc);
     void ajouteraccident(Accident acc);
}
