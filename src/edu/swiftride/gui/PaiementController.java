/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.gui;

import com.graphhopper.util.Parameters;
import edu.swiftride.entities.paiement;
import edu.swiftride.entities.reservation;
import edu.connexion3A17.services.PaiementCRUD;
import edu.connexion3A17.services.ReservationCRUD;
import java.net.URL;
import java.time.LocalDate;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class PaiementController implements Initializable {

    @FXML
    private Text prix;
    @FXML
    private TextField num;
    @FXML
    private DatePicker date;
    @FXML
    private TextField code;
    @FXML
    private Button payer;
    @FXML
    private Button retour;
    @FXML
    private Text id;
    @FXML
    private Text prix_2;
    
     int test=0 ; 
    @FXML
    private Text r_id;
    

     
     
     
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        //code.setText(point_depart);
        // TODO
    }    
     
   /* public void setPoint_depart(String point_depart) {
        this.point_depart = point_depart;
    }*/

        public void setPrix(String prix) {
        this.prix_2.setText(prix);
        this.prix.setText(prix);
    }
    
    public void setR_id(String id) {
        this.r_id.setText(id);
    }
    
    
    
    public void setCode(String code) {
        this.code.setText(code);
    }

    
     public boolean isPaymentSuccessful() {
         if (test==0)
         { return false; }
         else
         { return true ; }

    }
    
    
     @FXML
    private void payer(ActionEvent event) {
        test=1 ; 
        
        LocalDate date_get = date.getValue();
        
        Random random = new Random();
String randomNumber = String.valueOf(random.nextInt(100) + 1);


            String p_num =num.getText() ;
             String p_date = date_get.toString() ;
            String p_code = code.getText() ;
        String r_id2 = r_id.getText() ;
        
        String id = randomNumber + r_id2 ; 
 
                  paiement p = new paiement (Integer.parseInt(id) , Integer.parseInt(p_num) , p_date , Integer.parseInt(p_code) ,  Integer.parseInt(r_id2) ) ;
                  
                   PaiementCRUD pcd = new PaiementCRUD();
        pcd.ajouterEntitee(p);
             
         // Récupérer la scène associée au bouton cliqué
    Scene scene = ((Node) event.getSource()).getScene();
    // Récupérer la fenêtre parent associée à la scène
    Stage stage = (Stage) scene.getWindow();
    // Fermer la fenêtre
    stage.close();
    
    Stage stage2 = new Stage();
    QR qr = new QR(id , p_num , p_date);
  
    qr.start(stage2);
    }
  
    
    @FXML
    private void retour(ActionEvent event) {
         // Récupérer la scène associée au bouton cliqué
    Scene scene = ((Node) event.getSource()).getScene();
    // Récupérer la fenêtre parent associée à la scène
    Stage stage = (Stage) scene.getWindow();
    // Fermer la fenêtre
    stage.close();
    }
    
    }
   
    

   

