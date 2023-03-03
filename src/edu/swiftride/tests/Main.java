/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.tests;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import edu.swiftride.entities.MoyenTransport;
import edu.swiftride.entities.PDF;
import edu.swiftride.entities.Station;
import edu.swiftride.services.MoyenTransportCRUD;
import edu.swiftride.services.StationCRUD;
import edu.swiftride.utils.MyConnection;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author karra
 */
public class Main{
    public static void main(String[] args) throws DocumentException, BadElementException, IOException, FileNotFoundException {
        //MyConnection mc = new MyConnection();
         MoyenTransport m= new  MoyenTransport(3,"bus",86);
        MoyenTransportCRUD mcd = new  MoyenTransportCRUD();
        mcd.ajouterMoyenTransport(m);
        System.out.println(mcd.afficherMoyenTransport());
       
        
          Station s= new  Station(2,"ben arous","tunis");
        StationCRUD scd = new StationCRUD();
        scd.ajouterStation(s);
        System.out.println(scd.afficherStation());
        
           PDF pd=new PDF();
              try{
            pd.GeneratePdf(" Moyen de Transport",m,m.getId());
        } catch (Exception ex) {
            System.out.println(ex);
       }
        
    }
}
