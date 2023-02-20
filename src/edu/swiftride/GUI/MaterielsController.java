/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.GUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import edu.swiftride.Services.GarageCRUD;
import edu.swiftride.Services.MaterielCRUD;
import edu.swiftride.entities.Garage;
import edu.swiftride.entities.Materiel;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
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
public class MaterielsController implements Initializable {

    @FXML
    private ComboBox<Garage> cb_garage;
    @FXML
    private TextField tf_materiel;
    @FXML
    private JFXButton bt_save;
    
    int id_g =0;
    int id=0;
    
    MaterielCRUD mc = new MaterielCRUD();
    
    @FXML
    private TableView tb_materiels;
    @FXML
    private TableColumn tb_id;
    @FXML
    private TableColumn tb_nom;
    
    public static ObservableList<Materiel> listM = null;
    
    @FXML
    private AnchorPane logo;
    @FXML
    private JFXButton buttonM;
    @FXML
    private JFXButton bt_modify;
    @FXML
    private JFXButton bt_delete;
    
    @FXML
    private TextField tf_recherche;
    @FXML
    private Pane pane;
    @FXML
    private Label lb_mat;
    @FXML
    private Label lb_garage;
    @FXML
    private Label lb_base;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      displayMateriel();
      getGarageItems();
      setCellValueFromTableToText();
    }    
    
    public void getGarageItems(){
        GarageCRUD gc = new GarageCRUD();
        List<String> str = new ArrayList();
         List<Garage> lg = gc.listDesEntites();
         
         cb_garage.getItems().addAll(lg);
         
          cb_garage.setOnAction(ae ->{
            id_g = cb_garage.getSelectionModel().getSelectedItem().getId();
        });
    }
    
    @FXML
    private void saveMateriel(ActionEvent event) {
        
        boolean isExiste=false;
         List<Materiel> l = mc.listDesEntites();
        String materiel= tf_materiel.getText();
        
        if(!materiel.isEmpty()&& id_g!=0){
        
        Materiel m = new Materiel();
        
        m.setId_garage(id_g);
        m.setNom(materiel);
        
         for( Materiel m1 : l){
            if(m.equals(m1)){
                lb_base.setText("Ce garage déja existe");
                lb_base.setTextFill(Color.RED);
                isExiste=true;
            }
        }
         if(!isExiste){
        mc.ajouterEntitie(m);
        tf_materiel.setText(" ");
        cb_garage.setValue(new Garage());
        lb_base.setText("");
        lb_garage.setText(" ");
        displayMateriel();
         }
        }else{
            
            if(materiel.isEmpty()){
               lb_mat.setText("* Ce champ est OBLIGATOIRE !");
               lb_mat.setTextFill(Color.RED);
            }
            if(cb_garage.getSelectionModel().getSelectedItem()==null){
                lb_garage.setText("* Ce champ est OBLIGATOIRE !");
                lb_garage.setTextFill(Color.RED);
            }
            
        }
        
    }
    
    public void displayMateriel(){
        
        tb_id.setCellValueFactory(new PropertyValueFactory("id"));
        tb_nom.setCellValueFactory(new PropertyValueFactory("nom"));
        
        List l = mc.listDesEntites();
        
        listM=FXCollections.observableArrayList(l);
        
        tb_materiels.setItems(listM);
    }

    @FXML
    private void deletMateriel(ActionEvent event) {
         Materiel m =(Materiel) tb_materiels.getSelectionModel().getSelectedItem();
         
          Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setContentText("Voulez-vous vraiment supprimer le Materiel N°"+m.getId());
                a.setTitle("CONFIRMER");
                 Optional<ButtonType> res=a.showAndWait();
                if(res.get() ==ButtonType.OK){
                    if(mc.supprimerEntite(m.getId())){
                     listM.remove(m);
        } 
                }
                else if (res.get()==ButtonType.CANCEL){
                    System.out.println("alert closed");
                }
        
    }
    
    private void setCellValueFromTableToText(){
        
        tb_materiels.setOnMouseClicked((MouseEvent event) -> {
            Materiel m= (Materiel) tb_materiels.getItems().get(tb_materiels.getSelectionModel()
                    .getSelectedIndex());
            tf_materiel.setText(m.getNom());
            
            id = m.getId();
        });
        
    }

    @FXML
    private void modifyMateriel(ActionEvent event) {
        
        String materiel = tf_materiel.getText();
        
        Materiel m =new Materiel();
        
        m.setNom(materiel);
        m.setId(id);
        if(m!=null){
      Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setContentText("Voulez-vous vraiment modifier le Materiel N°"+m.getId());
                a.setTitle("CONFIRMER");
                 Optional<ButtonType> res=a.showAndWait();
                if(res.get() ==ButtonType.OK){
        
                    if(mc.modifierEntite(m)){
                        tf_materiel.setText("");
                    displayMateriel();
                    }
        
                }
                 else if (res.get()==ButtonType.CANCEL){
                    System.out.println("alert closed");
                }
    }
    
                else{
                       Alert b = new Alert(Alert.AlertType.ERROR);
                                b.setContentText("Vous devez sélectionner le Materiel !");
                                b.setTitle("ERREUR");
                                Optional<ButtonType> res=b.showAndWait();
                                if(res.get() ==ButtonType.OK){
                                    System.out.println("alert closed");
                                }
                }
    }
    
}
