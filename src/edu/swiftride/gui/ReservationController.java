/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.gui;

import edu.swiftride.entities.reservation;
import edu.connexion3A17.services.ReservationCRUD;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class ReservationController implements Initializable {

    
      private ReservationCRUD reservationCrud;
       @FXML
    private TableColumn<reservation, Integer> t_id;
    @FXML
    private TableColumn<reservation, String> t_point_depart;
    @FXML
    private TableColumn<reservation, String> t_destination;
    @FXML
    private TableColumn<reservation, Integer> t_id_client;
    @FXML
    private TableColumn<reservation, Integer> t_id_vehicule;
    @FXML
    private TableColumn<reservation, String> t_temps_depart;
      @FXML
    private TableColumn<reservation, Integer> t_distance;
    @FXML
    private TableColumn<reservation, Integer> t_transport;
    @FXML
    private TableColumn<reservation, String> t_prix;
      
    @FXML
    private TextField distance ;
    @FXML
    private TextField depart;
    @FXML
    private TextField dest ;
    @FXML
    private TextField v_id;
    @FXML
    private TextField t_depart;
    @FXML
    private TextField c_id;
    @FXML
    private TextArea bill;
    @FXML
    private Label ltotal;
    @FXML
    private Button valider ;
     @FXML
    private Button gerer;
     @FXML
private Button delete ;
       @FXML
private Button modifier ;
        @FXML
private DatePicker date ; 
        @FXML
    private TextField type;
        
        
        
     /*@FXML
private AnchorPane myAnchorPane;*/

@FXML
private TableView<reservation> myTableView;
    @FXML
    private Pane myAnchorPane;
    @FXML
   private ComboBox<String> combo;



  public void set_depart(String m_depart) {
        this.depart.setText(m_depart);
    }
  
  public void set_destination(String m_dest) {
        this.dest.setText(m_dest);
    }
 public void set_distance(double totalDistance) {
    this.distance.setText(String.valueOf(totalDistance));
}



    /**
     * Initializes the controller class.
     */
   
 @Override
    public void initialize(URL url, ResourceBundle rb) {
         
        Afficher_Table() ;
    bill.setStyle("-fx-alignment: center; -fx-font-weight: bold; -fx-font-size: 20; -fx-text-fill: red; -fx-background-color: transparent; -fx-padding: 10;");
      combo.getItems().addAll("Transport publique", "Voiture SwiftRide");
      
   
        
        // TODO
    }    
   
 public void Buy(){
     
    }


    private void Afficher_Table() {
        reservationCrud = new ReservationCRUD();
        List<reservation> reservations = reservationCrud.listeDesEntites();
        ObservableList<reservation> observableList = FXCollections.observableArrayList(reservations);
        myTableView.setItems(observableList);
          t_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        t_point_depart.setCellValueFactory(new PropertyValueFactory<>("point_depart"));
        t_destination.setCellValueFactory(new PropertyValueFactory<>("destination"));
        t_id_client.setCellValueFactory(new PropertyValueFactory<>("id_client"));
        t_id_vehicule.setCellValueFactory(new PropertyValueFactory<>("id_vehicule"));
        t_temps_depart.setCellValueFactory(new PropertyValueFactory<>("temps_depart"));
         t_distance.setCellValueFactory(new PropertyValueFactory<>("distance"));
        t_transport.setCellValueFactory(new PropertyValueFactory<>("type_transport"));
        t_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
     
         delete.setOnAction(event -> deleteReservation());
         ltotal.setText(String.valueOf(myTableView.getItems().size()));
    }

   @FXML
    private void Print(ActionEvent event) {
        
        String regex = "\\d{2}:\\d{2}:\\d{2}"; // Expression régulière pour le format "yyyy-MM-dd HH:mm:ss"
Pattern pattern = Pattern.compile(regex);


        
                LocalDate date_get = date.getValue();
        
              ReservationCRUD pcd_id =new ReservationCRUD() ;
           String id=String.valueOf(pcd_id.getMaxReservationId()+1) ;
          float get_distance =  Float.parseFloat(distance.getText()) ;
            String point_depart =depart.getText() ;
             String destination = dest.getText() ;
            String id_vehicule = v_id.getText() ;
            String temps_depart = t_depart.getText();
            String D_temps_depart = date_get.toString()+" "+t_depart.getText();
             String id_client = c_id.getText() ;
              String get_type = combo.getSelectionModel().getSelectedItem() ;
             float prix = get_distance*1/300 ; 
         
             
            String dist= distance.getText() ;
            String str_prix= String.valueOf(prix);
         
             
             if (!pattern.matcher(temps_depart).matches()) {
    // Affichage d'un message d'erreur si la saisie ne correspond pas au format attendu
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Erreur de saisie");
    alert.setHeaderText(null);
    alert.setContentText("Le temps de départ doit être au format HH:mm:ss.");
    alert.showAndWait();
}
             else {   
                  reservation r = new reservation (Integer.parseInt(id) , point_depart , destination , Integer.parseInt(id_client), Integer.parseInt(id_vehicule) , D_temps_depart , get_distance ,get_type , prix ) ;
       
                  try {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("paiement.fxml"));
    Parent root = loader.load();
    PaiementController controller = loader.getController();
    controller.setR_id(String.valueOf(r.getId()));
    controller.setPrix(String.valueOf(r.getPrix()));

    Stage stage = new Stage();
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.showAndWait();

    boolean paymentSuccessful = controller.isPaymentSuccessful();
    if (paymentSuccessful) {
        // Le paiement a été effectué, continuer le reste du code
        distance.setText("");
        depart.setText("");
        dest.setText("");
        v_id.setText("");
        c_id.setText("");
        t_depart.setText("");

        ReservationCRUD pcd = new ReservationCRUD();
        pcd.ajouterEntitee(r);
        Afficher_Table();

        // cacher la text area et afficher le tableau
        myTableView.setVisible(true);
        bill.setVisible(false);
    } else {
        // Le paiement n'a pas été effectué, afficher un message d'erreur
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur de paiement");
        alert.setHeaderText(null);
        alert.setContentText("Le paiement n'a pas été effectué.");
        alert.showAndWait();
    }
} catch (IOException ex) {
    Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
}
                  

   

           
             }
    }
    
    
    
    private void deleteReservation() {
    // Récupérer la réservation sélectionnée dans le TableView
    reservation selectedReservation = myTableView.getSelectionModel().getSelectedItem();

    if (selectedReservation != null) {
        // Supprimer la réservation de la base de données
        ReservationCRUD crud = new ReservationCRUD();
        crud.supprimerReservation(selectedReservation.getId());

        // Retirer la réservation du TableView
        myTableView.getItems().remove(selectedReservation);
        ltotal.setText(String.valueOf(myTableView.getItems().size()));
    }
}
    
        
         @FXML
    private void modifier(ActionEvent event) {
          try {
             
              
               reservation selectedReservation = myTableView.getSelectionModel().getSelectedItem();

    // Vérifier si une réservation est sélectionnée
    if (selectedReservation == null) {
        // Afficher un message d'erreur
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur de sélection");
        alert.setHeaderText("Aucune réservation sélectionnée");
        alert.setContentText("Veuillez sélectionner une réservation à modifier.");
        alert.showAndWait();
    } else {
         FXMLLoader Loader = new FXMLLoader (getClass().getResource("details.fxml")) ;
              Parent root = Loader.load() ;
              DetailsController dc = Loader.getController() ;
           
        // Remplir les champs de saisie avec les valeurs de la réservation sélectionnée
        
        // Créer un objet DateTimeFormatter pour analyser la chaîne de caractères

        
        
        dc.setM_depart(selectedReservation.getPoint_depart());
        dc.setM_dest(selectedReservation.getDestination());
         dc.setM_t_depart(selectedReservation.getTemps_depart());
          dc.setM_c_id(String.valueOf(selectedReservation.getId_client()));
            dc.setM_v_id(String.valueOf(selectedReservation.getId_vehicule()));
             dc.setR_id(selectedReservation.getId());
            dc.setM_date(selectedReservation.getTemps_depart()); 
              dc.setM_distance(String.valueOf(selectedReservation.getDistance())); 
                dc.setM_type(selectedReservation.getType_transport()); 
             //dc.setM_date(selectedReservation.getTemps_depart()) ;
              
              
              dest.getScene().setRoot(root) ;
              
    }
          } catch (IOException ex) {
             System.out.println(ex.getMessage());
          }
    }
    
     @FXML
    private void gerer(ActionEvent event) {
        
        
         String regex = "\\d{2}:\\d{2}:\\d{2}"; // Expression régulière pour le format "yyyy-MM-dd HH:mm:ss"
Pattern pattern = Pattern.compile(regex);
        
LocalDate date_get = date.getValue();
        
           //String id=String.valueOf(myTableView.getItems().size()+1);
            ReservationCRUD pcd =new ReservationCRUD() ;
           String id=String.valueOf(pcd.getMaxReservationId()+1) ;
           
          float get_distance =  Float.parseFloat(distance.getText()) ;
            String point_depart =depart.getText() ;
             String destination = dest.getText() ;
            String id_vehicule = v_id.getText() ;
            String temps_depart = t_depart.getText();
            String D_temps_depart = date_get.toString()+" "+t_depart.getText();
             String id_client = c_id.getText() ;
              String get_type = combo.getSelectionModel().getSelectedItem() ;
            float prix = get_distance*1/300 ; 
         
             
            String dist= distance.getText() ;
            String str_prix= String.valueOf(prix);
             
             if (!pattern.matcher(temps_depart).matches()) {
                 
    // Affichage d'un message d'erreur si la saisie ne correspond pas au format attendu
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Erreur de saisie");
    alert.setHeaderText(null);
    alert.setContentText("Le temps de départ doit être au format HH:mm:ss.");
    alert.showAndWait();
}
             else {  
                  myTableView.setVisible(false);
        bill.setVisible(true);
                   String reservationDetails = "Détails de votre réservation : \n\n" +
                                     "ID Reservation : " + id + "\n\n" +
                                     "Point de départ : " + point_depart + "\n\n" +
                                     "Destination : " + destination + "\n\n" +
                                     "Distance : " + dist + "\n\n" +
                                     "Date et temps de départ : " + D_temps_depart + "\n\n" +
                                     "Type de transport : " + get_type + "\n\n" +
                                     "ID Véhicule : " + id_vehicule + "\n\n" +
                                     "ID Client : " + id_client + "\n\n" +
                                     "Prix  : " + str_prix + "  DT \n"  ;

        bill.setText(reservationDetails);
   
             }
    }
    
    
  
    

}
    

 
