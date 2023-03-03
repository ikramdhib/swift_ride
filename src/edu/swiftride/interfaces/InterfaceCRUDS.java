/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.interfaces;

import edu.swiftride.entities.Station;
import java.util.List;

/**
 *
 * @author Ines
 */
public interface InterfaceCRUDS<S> {
     void ajouterStation(Station s);
    void supprimerStation(Station s);
    boolean modifierStation(Station s);
    List<Station> afficherStation();
}
