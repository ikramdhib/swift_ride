/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.interfaces;

import java.io.IOException;
import java.util.List;
import edu.swiftride.entity.EntreprisePartenaire;
import edu.swiftride.entity.User;

/**
 *
 * @author skann
 */
public interface InterfaceCRUD<T,E> {
     public void ajouterUtilisateur(T t);
     public void supprimerUtilisateur(T t);
     public boolean modifierUtilisateur(T t);
     public boolean emaildejaUtilise(String email);
     public boolean cindejaUtilise(String  cin);
     public boolean num_permidejaUtilise(String num);
     public void uploadPhotoPersonnel(T t) throws IOException;
     public void uploadPhotoPermis(T t) throws IOException;
     public boolean authentifier(T t);
    public List<T> consulterListe();
    public User getUserByEmail(String s);
    public void updateAge(int a,String b);
    public void updatePassword(String a);
    public int getIdrole(String a);
    public EntreprisePartenaire getEntrepriseByEmail(String t);
    public boolean authentifierEntreprise(E e);
    public boolean emailEntreprisedejautilisé(String a);
    public int histReserv(int id);
   
}
