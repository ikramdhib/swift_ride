/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.interfaces;

import java.util.List;

/**
 *
 * @author dhibi
 */
public interface InterfaceCRUD<T> {
    
    
    public boolean ajouterEntitie(T t);
    
    public List <T> listDesEntites();
    
    public boolean modifierEntite(int id , String nom);
    
    public boolean supprimerEntite(int id);
    
}
