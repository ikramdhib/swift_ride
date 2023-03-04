/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.GUI;

import com.jfoenix.controls.JFXButton;
import edu.swiftride.Services.GarageCRUD;
import edu.swiftride.Services.MaintenanceCRUD;
import edu.swiftride.entities.Garage;
import edu.swiftride.entities.Maintenance;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dhibi
 */
public class AjouterGarageController implements Initializable {

    @FXML
    private ComboBox<Garage> cb_garage;
    @FXML
    private JFXButton closeButton;
    @FXML
    private JFXButton bt_save;
    
    @FXML
    private Label lb_garage;

    
    
    Maintenance m ;

    public Maintenance getM() {
        return m;
    }

    public void setM(Maintenance m) {
        this.m = m;
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
        getGarageItems();
    }  
    
    int id_g=0;
    
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
    private void ajouterGarage(ActionEvent event) {
        
        MaintenanceCRUD mc = new MaintenanceCRUD();
        if(id_g!=0){
        m.setId_garage(id_g);
        
        System.out.println(m);
        
        if(mc.modifierEntiteGARAGE(m)){
            
            
            try {
                FXMLLoader loader= new FXMLLoader(getClass().getResource("ajouterMateriels.fxml"));
                Stage stage = new Stage();
                AjouterMaterielsController dc = new AjouterMaterielsController();
                loader.setController(dc);
               Garage g = new Garage();
               g.setId(id_g);
                dc.setG(g);
                Parent roote = loader.load();
                Scene sc = new Scene(roote);
                stage.setScene(sc);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AjouterGarageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        }
        else{
            lb_garage.setText("* Ce champ OBLIGATOIRE");
            lb_garage.setTextFill(Color.RED);
        }
    }

    @FXML
    private void annuler(ActionEvent event) {
       Stage stage = (Stage) closeButton.getScene().getWindow();
    stage.close();
    }
    
    public void goTogarage(){
        try {
            Stage st = new Stage();
            FXMLLoader load = new FXMLLoader(getClass()
                    .getResource("garage.fxml"));
            Parent root = load.load();
            Scene sc = new Scene(root);
            
            st.setScene(sc);
            st.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
