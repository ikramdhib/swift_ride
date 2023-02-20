/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.tests;

import edu.swiftride.entities.MoyenTransport;
import edu.swiftride.services.MoyenTransportCRUD;
import edu.swiftride.utils.MyConnection;

/**
 *
 * @author karra
 */
public class Main{
    public static void main(String[] args) {
        //MyConnection mc = new MyConnection();
         MoyenTransport m= new  MoyenTransport(3,"bus",86,7,1);
        MoyenTransportCRUD mcd = new  MoyenTransportCRUD();
        mcd.ajouterMoyenTransport(m);
        System.out.println(mcd.afficherMoyenTransport());
    }
}
