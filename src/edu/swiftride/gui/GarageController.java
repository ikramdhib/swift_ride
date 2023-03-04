/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.GUI;

import com.jfoenix.controls.JFXButton;
import edu.swiftride.Services.GarageCRUD;
import edu.swiftride.entities.DetailsMaterielsGarage;
import edu.swiftride.entities.Garage;
import edu.swiftride.entities.Materiel;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


/**
 * FXML Controller class
 *
 * @author dhibi
 */
public class GarageController implements Initializable {

    @FXML
    private TableView table_garage;
    @FXML
    private TableColumn tb_id;
    @FXML
    private TableColumn tb_matricule;
     @FXML
    private TextField tf_matricule;
     @FXML
    private Button bt_save;
     @FXML
    private Button bt_delete;
     @FXML
    private Button bt_modify;
     
     
      
      
     GarageCRUD gc = new GarageCRUD();
    
    
    public static ObservableList<Garage> listG = null;
    
    int id =0;
    @FXML
    private AnchorPane logo;
    @FXML
    private JFXButton buttonM;
    @FXML
    private Pane pane;
    @FXML
    private TextField tf_recherche;
    @FXML
    private Label lb_mat;
   
   
    @FXML
    private Label lb_base;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        displayGarage();
        setCellValueFromTableToText();
        
        FilteredList<Garage> filtreData = new FilteredList<>(listG, p->true);
         tf_recherche.textProperty().addListener((observable, oldValue, newValue) ->{
            filtreData.setPredicate( maintenance ->{
                
             if (newValue == null || newValue.isEmpty()) {
            return true;
             }
               
             String lowerCaseFilter = newValue.toLowerCase();
             
             if(maintenance.getMatricule_garage().toLowerCase().contains(lowerCaseFilter)){
                 
                 return true;
               
             }
             else
                 return false;
            });
       });
       
       SortedList<Garage> sortedData = new SortedList<>(filtreData);
       sortedData.comparatorProperty().bind(table_garage.comparatorProperty());
       table_garage.setItems(sortedData);
       
    } 
    
   
    
    public void displayGarage(){
        
        
        
        tb_id.setCellValueFactory(new PropertyValueFactory("id"));
        tb_matricule.setCellValueFactory(new PropertyValueFactory("matricule_garage"));
         List<Garage> l = gc.listDesEntites();
        
        
        listG =FXCollections.observableArrayList(l);
        
        table_garage.setItems(listG);
        
    }
    
    @FXML
    public void addGarage(ActionEvent event){
        lb_mat.setText("");
        boolean isExiste=false;
        String matricule = tf_matricule.getText();
         List<Garage> l = gc.listDesEntites();
        if(!matricule.isEmpty()){
        
        Garage g = new Garage();
        g.setMatricule_garage(matricule);
        
        for( Garage g1 : l){
            if(g.equals(g1)){
                lb_base.setText("Ce garage déja existe");
                lb_base.setTextFill(Color.RED);
                isExiste=true;
            }
        }
        if(!isExiste){
            
        gc.ajouterEntitie(g);
        tf_matricule.setText("");
        lb_base.setText(" ");
        
        displayGarage();
        }
        }
        else{
            lb_mat.setText("* Ce champ est OBLIGATOIRE !");
            lb_mat.setTextFill(Color.RED);
        }
        
    }
    
    @FXML
    public void deleteGarage(ActionEvent ae){
        
        if(table_garage.getSelectionModel().getSelectedItems().isEmpty()){
              Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("Vous devez sélectionner le garage !");
                a.setTitle("ERREUER");
                 Optional<ButtonType> res=a.showAndWait();
                if(res.get() ==ButtonType.OK){
                a.close();
        }
        }
        Garage g =(Garage) table_garage.getSelectionModel().getSelectedItem();
             Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setContentText("Voulez-vous vraiment supprimer le Garage N°"+g.getId());
                a.setTitle("CONFIRMER");
                 Optional<ButtonType> res=a.showAndWait();
                if(res.get() ==ButtonType.OK){
                    if(gc.supprimerEntite(g.getId())){
                     listG.remove(g);
        } 
                }
                else if (res.get()==ButtonType.CANCEL){
                    System.out.println("alert closed");
                }
        
       
              
    }
    
    private void setCellValueFromTableToText(){
        
        table_garage.setOnMouseClicked((MouseEvent event) -> {
            Garage g = (Garage) table_garage.getItems().get(table_garage.getSelectionModel()
                    .getSelectedIndex());
            tf_matricule.setText(g.getMatricule_garage());
            id = g.getId();
        });
        
    }
    
    
    @FXML
   public void modifyG(ActionEvent event){
        
        String matricule = tf_matricule.getText();
        
        if(matricule!=null){
        
        Garage g = new Garage();
        
        g.setMatricule_garage(matricule);
        g.setId(id);
        
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setContentText("Voulez-vous vraiment supprimer le Garage N°"+g.getId());
                a.setTitle("CONFIRMER");
                 Optional<ButtonType> res=a.showAndWait();
                if(res.get() ==ButtonType.OK){
        
                    if(gc.modifierEntite(g)){
                        tf_matricule.setText("");
                    displayGarage();
                    }
        
                }
                 else if (res.get()==ButtonType.CANCEL){
                    System.out.println("alert closed");
                }
                
        }
        else{
                  Alert b = new Alert(Alert.AlertType.ERROR);
                b.setContentText("Vous devez sélectionner le garage !");
                b.setTitle("ERREUR");
                Optional<ButtonType> res=b.showAndWait();
                if(res.get() ==ButtonType.OK){
                    System.out.println("alert closed");
                }
        }
        
    }

    @FXML
    private void refresh(ActionEvent event) {
        
        displayGarage();
        
    }
    
    
    
}
