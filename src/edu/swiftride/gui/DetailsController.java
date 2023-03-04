/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.gui;

import static com.sun.glass.ui.Cursor.setVisible;
import edu.swiftride.entities.reservation;
import edu.connexion3A17.services.ReservationCRUD;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JFrame;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class DetailsController implements Initializable {

    

    @FXML
    private TextField m_depart;
    @FXML
    private TextField m_dest ;
    @FXML
    private TextField m_v_id;
    @FXML
    private TextField m_t_depart;
    @FXML
    private TextField m_c_id;
    @FXML
    private Button appliquer ;
     @FXML
    private Button retour ;
     @FXML
private DatePicker m_date ; 
    @FXML
    private TextField m_distance;
    
    private int r_id=0 ; 

    @FXML
    private Button map;
    
    @FXML
    private ComboBox<String> combo;

    public int getR_id() {
        return r_id;
    }

    public void setR_id(int r_id) {
        this.r_id = r_id;
    }
    
    //private int r_id=0 ;

   public void setM_date(String Ch) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    LocalDate date = LocalDateTime.parse(Ch, formatter).toLocalDate();
    m_date.setValue(date);
}
    
     @FXML
    private Button gerer ;
      @FXML
    private TextArea m_bill;

    public void setM_depart(String m_depart) {
        this.m_depart.setText(m_depart);
    }
    
     public void setM_distance(String m_distance) {
        this.m_distance.setText(m_distance);
    }
      public void setM_type(String m_type) {
        this.combo.setValue(m_type);
    }

    public void setM_dest(String m_dest) {
        this.m_dest.setText(m_dest);
    }

       public void setM_v_id(String v_id) {
        this.m_v_id.setText(v_id);
    }

      public void setM_t_depart(String t_depart) {
           DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    LocalDateTime dateTime = LocalDateTime.parse(t_depart, formatter);
    LocalTime time = dateTime.toLocalTime();
        this.m_t_depart.setText(time.toString());
    }  
      
      public void setM_t_depart2(String t_depart) {
           
        this.m_t_depart.setText(t_depart);
    }  
       public void setM_date2(String Ch) {
    LocalDate date = LocalDate.parse(Ch) ;
    m_date.setValue(date);
}
      
      public void setM_c_id(String c_id) {
        this.m_c_id.setText(c_id);
    }

      
      
    @FXML
private void appliquer(ActionEvent event) {
    try {
        
         String regex = "\\d{2}:\\d{2}:\\d{2}"; // Expression régulière pour le format "yyyy-MM-dd HH:mm:ss"
Pattern pattern = Pattern.compile(regex);


        
                LocalDate date_get = m_date.getValue();
        
            // Récupérer les nouvelles valeurs entrées par l'utilisateur

          float get_distance =  Float.parseFloat(m_distance.getText()) ;
            String point_depart =m_depart.getText() ;
             String destination = m_dest.getText() ;
            String id_vehicule = m_v_id.getText() ;
            String temps_depart = m_t_depart.getText();
            String D_temps_depart = date_get.toString()+" "+m_t_depart.getText();
             String id_client = m_c_id.getText() ;
              String get_type = combo.getSelectionModel().getSelectedItem() ;
             float prix = get_distance*1/300 ; 
         
             
            String dist= m_distance.getText() ;
            String str_prix= String.valueOf(prix);
         
         
               if (!pattern.matcher(temps_depart).matches()) {
    // Affichage d'un message d'erreur si la saisie ne correspond pas au format attendu
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur de saisie");
    alert.setHeaderText(null);
    alert.setContentText("Le temps de départ doit être au format HH:mm:ss.");
    alert.showAndWait();
}
               else {
             
           reservation r = new reservation (r_id , point_depart , destination , Integer.parseInt(id_client), Integer.parseInt(id_vehicule) , D_temps_depart , get_distance ,get_type , prix ) ;
            /*reservation r = new reservation (555 , "kldf" , "gkds" , 666 , 777 , "lsd,f");*/
             ReservationCRUD pcd =new ReservationCRUD() ;
            pcd.modifierReservation(r) ;

        // Afficher un message de confirmation
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Modification réussie");
        alert.setHeaderText("La réservation a été modifiée avec succès.");
        alert.showAndWait();
        
         
         FXMLLoader Loader = new FXMLLoader (getClass().getResource("Services.fxml")) ;
              Parent root = Loader.load() ;
            
               m_dest.getScene().setRoot(root) ;



// Charger le fichier FXML de la nouvelle interface
   /* FXMLLoader loader = new FXMLLoader(getClass().getResource("Services.fxml"));
    Parent root = loader.load();

    // Afficher la nouvelle interface dans une nouvelle fenêtre
    Stage stage = new Stage();
    stage.setScene(new Scene(root));
    stage.show();

    // Fermer la fenêtre actuelle si nécessaire
    ((Node)(event.getSource())).getScene().getWindow().hide();*/
    }} catch (Exception ex) {
        // Afficher un message d'erreur en cas d'exception
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de modification");
        alert.setHeaderText("Impossible de modifier la réservation.");
        alert.setContentText(ex.getMessage());
        alert.showAndWait();
    }
}

      
      
 @FXML
    private void gerer(ActionEvent event) {
        
      
        
         String regex = "\\d{2}:\\d{2}:\\d{2}"; // Expression régulière pour le format "yyyy-MM-dd HH:mm:ss"
Pattern pattern = Pattern.compile(regex);
        
LocalDate date_get = m_date.getValue();
        
   float get_distance =  Float.parseFloat(m_distance.getText()) ;
            String point_depart =m_depart.getText() ;
             String destination = m_dest.getText() ;
            String id_vehicule = m_v_id.getText() ;
            String temps_depart = m_t_depart.getText();
            String D_temps_depart = date_get.toString()+" "+m_t_depart.getText();
             String id_client = m_c_id.getText() ;
              String get_type = combo.getSelectionModel().getSelectedItem() ;
             float prix = get_distance*1/300 ; 
         
             
            String dist= m_distance.getText() ;
            String str_prix= String.valueOf(prix);
         
             
             if (!pattern.matcher(temps_depart).matches()) {
                 
    // Affichage d'un message d'erreur si la saisie ne correspond pas au format attendu
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur de saisie");
    alert.setHeaderText(null);
    alert.setContentText("Le temps de départ doit être au format HH:mm:ss.");
    alert.showAndWait();
}
             else {  
                
                  String reservationDetails = "Détails de votre réservation : \n\n" +
                                     "ID Reservation : " + r_id + "\n\n" +
                                     "Point de départ : " + point_depart + "\n\n" +
                                     "Destination : " + destination + "\n\n" +
                                     "Distance : " + dist + "\n\n" +
                                     "Date et temps de départ : " + D_temps_depart + "\n\n" +
                                     "Type de transport : " + get_type + "\n\n" +
                                     "ID Véhicule : " + id_vehicule + "\n\n" +
                                     "ID Client : " + id_client + "\n\n" +
                                     "Prix  : " + str_prix + "  DT \n"  ;

        m_bill.setText(reservationDetails);
   
             }
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
         m_bill.setStyle("-fx-alignment: center; -fx-font-weight: bold; -fx-font-size: 20; -fx-text-fill: red; -fx-background-color: transparent; -fx-padding: 10;");
          combo.getItems().addAll("Transport publique", "Voiture SwiftRide");
     
    }
    
    
    @FXML
private void retour(ActionEvent event) {

    
    
     try {
           
            
   FXMLLoader Loader = new FXMLLoader (getClass().getResource("Services.fxml")) ;
              Parent root = Loader.load() ;
            
               m_dest.getScene().setRoot(root) ;


// Charger le fichier FXML de la nouvelle interface
           /* FXMLLoader loader = new FXMLLoader(getClass().getResource("Services.fxml"));
            Parent root = loader.load();
            
            // Afficher la nouvelle interface dans une nouvelle fenêtre
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            
            // Fermer la fenêtre actuelle si nécessaire
            ((Node)(event.getSource())).getScene().getWindow().hide();*/
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    
    
}

    @FXML
    private void map(ActionEvent event) {
        
      ((Node) event.getSource()).getScene().getWindow().hide();
        
         String[] parts2 = m_dest.getText().split(",");
String dest_lat = parts2[0].split(":")[1].trim();
String dest_long = parts2[1].split(":")[1].trim();
        
        
        String[] parts = m_depart.getText().split(",");
String depart_lat = parts[0].split(":")[1].trim();
String depart_long = parts[1].split(":")[1].trim();
        
         String str_id = String.valueOf(r_id) ; 
         LocalDate date_get = m_date.getValue();


            Main.main(new String[]{depart_lat , depart_long , dest_lat , dest_long, str_id , m_t_depart.getText() , m_v_id.getText() , m_c_id.getText() ,combo.getSelectionModel().getSelectedItem(), date_get.toString() , m_distance.getText()});
        
       
            
    
 
            
       /*  JFrame mainWindow = new Main();
    mainWindow.setVisible(true);*/

    // hide current window
   /* Stage stage = (Stage) ((Node)(event.getSource())).getScene().getWindow();
    stage.hide();*/
         
    
           /*Stage stage = (Stage) ((Node)(event.getSource())).getScene().getWindow();
    stage.hide();*/

    // Attendre la fermeture de la fenêtre
  
         /*   m_depart.setText(Main.getStartCoordinates());
            m_dest.setText(Main.getEndCoordinates());*/
        
    

        
    }

    
}
    
    
    
    
    

