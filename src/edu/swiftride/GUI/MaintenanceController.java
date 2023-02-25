/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.GUI;

import com.jfoenix.controls.JFXButton;
import edu.swiftride.Services.GarageCRUD;
import edu.swiftride.Services.MaintenanceCRUD;
import edu.swiftride.Services.VoitureCRUD;
import edu.swiftride.entities.Garage;
import edu.swiftride.entities.Maintenance;
import edu.swiftride.entities.Voiture;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dhibi
 */
public class MaintenanceController implements Initializable {
    
   @FXML
   private ComboBox<Garage> cb_garage;
      @FXML
   private ComboBox<Voiture> cb_voiture;
     
     
     int id_g=0;
     int id_v=0;
    @FXML
    private TableView tb_maintenaces;
    @FXML
    private TableColumn tb_id;
    @FXML
    private TableColumn tb_date;
    @FXML
    private TableColumn tb_type;
    @FXML
    private JFXButton bt_save;
    @FXML
    private JFXButton bt_modify;
    @FXML
    private JFXButton bt_delete;
    @FXML
    private TextField tf_recherche;
    @FXML
    private DatePicker pk_date;
    @FXML
    private RadioButton rb_reparation;
    @FXML
    private RadioButton rb_entretien;
    @FXML
    private ToggleGroup type;
    @FXML
    private Label lb_date;
    @FXML
    private Label lb_rd;
    @FXML
    private Label lb_garage;
    @FXML
    private Label lb_voiture;
    @FXML
    private Label lb_base;
   
    
    MaintenanceCRUD mc = new MaintenanceCRUD();
    
    public static ObservableList<Maintenance> listM = null;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        pk_date.setValue(LocalDate.now());
        
        pk_date.setDayCellFactory(pcker-> new DateCell(){
         public void updateItem(LocalDate date, boolean empty) {
            super.updateItem(date, empty);
            LocalDate today = LocalDate.now();

            setDisable(empty || date.compareTo(today) < 0 );
        }
        });
        
        displayMaintenance();
        getVoitureItems();
        setCellValueFromTableToText();
        
         FilteredList<Maintenance> filtreData = new FilteredList<>(listM , p->true);
       
        
         tf_recherche.textProperty().addListener((observable, oldValue, newValue) ->{
            filtreData.setPredicate( maintenance ->{
                
             if (newValue == null || newValue.isEmpty()) {
            return true;
             }
               
             String lowerCaseFilter = newValue.toLowerCase();
             if(maintenance.getType().toLowerCase().contains(lowerCaseFilter)){
                 
                 return true;
             }
             else if(maintenance.getDate_maintenance().toString().toLowerCase().contains(lowerCaseFilter))
             {  return true ; }
             else
                 return false;
            });
       });
       
       SortedList<Maintenance> sortedData = new SortedList<>(filtreData);
       sortedData.comparatorProperty().bind(tb_maintenaces.comparatorProperty());
       tb_maintenaces.setItems(sortedData);
       
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
     
      public void getVoitureItems(){
        VoitureCRUD vc= new VoitureCRUD();
        List<String> str = new ArrayList();
         List<Voiture> lg = vc.diplayVoitures();
         
         cb_voiture.getItems().addAll(lg);
         
          cb_voiture.setOnAction(ae ->{
            id_v = cb_voiture.getSelectionModel().getSelectedItem().getId();
        });
    }
      
      String maintenance_type=null;

    @FXML
    private void getMaintenance(ActionEvent event) {
        
        if(rb_reparation.isSelected()){
            maintenance_type=rb_reparation.getText();
        }
        
        else if (rb_entretien.isSelected()){
            
            maintenance_type=rb_entretien.getText();
        }
        System.out.println(maintenance_type);
        
    }

    @FXML
    private void saveMaintenance(ActionEvent event) {
        boolean isExiste=false;
        List<Maintenance> l = mc.listDesEntites();
        LocalDate d =  pk_date.getValue();
        
        Date date = Date.valueOf(d);
        System.out.println(date);
        if(!pk_date.isEditable() && !rb_entretien.isPressed()|| !rb_reparation.isPressed() && id_v!=0){
        
        Maintenance m = new Maintenance();
        m.setDate_maintenance(date);
        m.setId_garage(id_g);
        m.setId_voiture(id_v);
        m.setType(maintenance_type);
        
         for( Maintenance m1 : l){
            if(m.equals(m1)){
                lb_base.setText("Ce Maintenace déja existe");
                lb_base.setTextFill(Color.RED);
                isExiste=true;
            }
        }
        if(!isExiste){
            try {
                mc.ajouterEntitie(m);
                pk_date.setValue(LocalDate.now());
                maintenance_type=null;
                
                FXMLLoader loader= new FXMLLoader(getClass().getResource("ajouterGarage.fxml"));
                Stage stage = new Stage();
                AjouterGarageController dc = new AjouterGarageController();
                loader.setController(dc);
                List<Maintenance> lm = mc.getMaintenace(m);
                Maintenance ms = new Maintenance();
                for(Maintenance mm : lm){
                    ms=mm;
                }
                dc.setM(ms);
                Parent roote = loader.load();
                Scene sc = new Scene(roote);
                 stage.setScene(sc);  
                   stage.show();
                
                displayMaintenance();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        }
        else{
            
            if(date==Date.valueOf(LocalDate.now())){
             lb_date.setText("* Ce champ est OBLIGATOIRE !");
             lb_date.setTextFill(Color.RED);
            }
            if(!rb_entretien.isPressed()|| !rb_reparation.isPressed()){
             lb_rd.setText("* Ce champ est OBLIGATOIRE !");
             lb_rd.setTextFill(Color.RED);   
            }
            if(id_v==0){
             lb_voiture.setText("* Ce champ est OBLIGATOIRE !");
             lb_voiture.setTextFill(Color.RED);   
            }
        }
        
    }
    
    
    public void displayMaintenance(){
        
         tb_id.setCellValueFactory(new PropertyValueFactory("id"));
        tb_date.setCellValueFactory(new PropertyValueFactory("date_maintenance"));
        tb_type.setCellValueFactory(new PropertyValueFactory("type"));
        
        List l = mc.listDesEntites();
        
        listM =FXCollections.observableArrayList(l);
        
        tb_maintenaces.setItems(listM);
        
    }

    @FXML
    private void deleteMaintenance(ActionEvent event) {
         Maintenance m =(Maintenance) tb_maintenaces.getSelectionModel().getSelectedItem();
         
         if(m.getId()!=0 && m.getId_voiture()!=0){
         
          Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setContentText("Voulez-vous vraiment supprimer le Maintenance N°"+m.getId());
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
         else{
              Alert b = new Alert(Alert.AlertType.ERROR);
                                b.setContentText("Vous devez sélectionner le Maintenance !");
                                b.setTitle("ERREUR");
                                Optional<ButtonType> res=b.showAndWait();
                                if(res.get() ==ButtonType.OK){
                                    System.out.println("alert closed");
                                }

         }
        
    }
     int id =0;
     private void setCellValueFromTableToText(){
       
        tb_maintenaces.setOnMouseClicked((MouseEvent event) -> {
            Maintenance m= (Maintenance) tb_maintenaces.getItems().get(tb_maintenaces.getSelectionModel()
                    .getSelectedIndex());
            
            pk_date.setValue(m.getDate_maintenance().toLocalDate());
            id = m.getId();
            if(m.getType().equals(rb_entretien.getText())){
            rb_entretien.setSelected(true);
            }
            else if (m.getType().equals(rb_reparation.getText())){
                rb_reparation.setSelected(true);
            }
        });
        
    }

    @FXML
    private void modifyMaintenance(ActionEvent event) {
        
        LocalDate d =  pk_date.getValue();
        
         LocalDate localDate = LocalDate.of(2023, 01, 01);
        
        
        
        Date date = Date.valueOf(d);
        
        Maintenance m = new Maintenance();
        
        m.setDate_maintenance(date);
        m.setId_garage(id_g);
        m.setId_voiture(id_v);
        m.setType(maintenance_type);
        m.setId(id);
        if(m.getDate_maintenance()!=null && m.getId()!=0 && m.getId_voiture()!=0){
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setContentText("Voulez-vous vraiment modifier le maintenance N°"+m.getId());
                a.setTitle("CONFIRMER");
                 Optional<ButtonType> res=a.showAndWait();
                if(res.get() ==ButtonType.OK){
        
                    if(mc.modifierEntite(m)){
                         pk_date.setValue(localDate);
                          maintenance_type=null;
                    displayMaintenance();
                    }
        
                }
                 else if (res.get()==ButtonType.CANCEL){
                    System.out.println("alert closed");
                }
    }
    
                else{
                Alert b = new Alert(Alert.AlertType.ERROR);
                b.setContentText("Vous devez sélectionner le Maintenance !");
                b.setTitle("ERREUR");
                Optional<ButtonType> res=b.showAndWait();
                if(res.get() ==ButtonType.OK){
                System.out.println("alert closed");
    }
}
    }
    
    
    
    
}
