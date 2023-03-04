/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.interfaces;

import edu.swiftride.entities.Avis;
import edu.swiftride.entities.EntreprisePartenaire;
import java.util.List;


public interface InterfaceCRUD<E> {
    void ajouterEntreprise(E t);
    boolean  modifierEntreprise(E t);
    void supprimerEntreprise(E t);
    List<EntreprisePartenaire> afficherEntreprise();   
   
    void ajouterAvis(E m);
    boolean modifierAvis(E m);
    void supprimerAvis(E m);
    List<Avis> afficherAvis();   
}
