/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.GUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import edu.swiftride.Services.MaterielCRUD;
import edu.swiftride.entities.Garage;
import edu.swiftride.entities.Materiel;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dhibi
 */
public class AjouterMaterielsController implements Initializable {

    @FXML
    private TableView<Materiel> table;
    @FXML
    private TableColumn<?, ?> tb_id;
    @FXML
    private TableColumn<?, ?> tb_materiel;
    @FXML
    private JFXButton add;
    @FXML
    private JFXListView<String> listView;
     @FXML
      private Label lb_mat;
    
     public static ObservableList<Materiel> listM = null;
     
      MaterielCRUD mc = new MaterielCRUD();
     
      String s1 =null;
      int id_g=0;
      
      Garage g ;
    @FXML
    private ImageView clodebt;

    public void setG(Garage g) {
        this.g = g;
    }

    public Garage getG() {
        return g;
    }

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       displayMateriel();
       setCellValueFromTableToText();
    }
    
    
     public void displayMateriel(){
        
        tb_id.setCellValueFactory(new PropertyValueFactory("id"));
        tb_materiel.setCellValueFactory(new PropertyValueFactory("nom"));
        
        List l = mc.listDesEntites();
        
        listM=FXCollections.observableArrayList(l);
        
        table.setItems(listM);
    }
     private void setCellValueFromTableToText(){
        table.setOnMouseClicked((MouseEvent event) -> {
            Materiel m= (Materiel) table.getItems().get(table.getSelectionModel()
                    .getSelectedIndex());
            
            id_g=m.getId();
            
            s1=m.getNom();
        });
        
    }

    @FXML
    private void addall(ActionEvent event) {
        
        List<String> str = new ArrayList();
        Materiel m = new Materiel();
        m.setId(id_g);
        m.setId_garage(g.getId());
        m.setNom(s1);
        if(id_g!=0){
        System.out.println(m);
        
        if(mc.modifierEntite(m)){
            
            str.add(m.getNom());
            listM.remove(m);
            listView.getItems().addAll(str);
            
        }
        }
        else{
            lb_mat.setText("* GARAGE non specifier");
            lb_mat.setTextFill(Color.RED);
        }
        
    }

    @FXML
    private void close(ActionEvent event) {
           Stage stage = (Stage) clodebt.getScene().getWindow();
    stage.close();
    }

    @FXML
    private void goMateriel(ActionEvent event) {
        
        try {
            Stage st = new Stage();
            FXMLLoader load = new FXMLLoader(getClass()
                    .getResource("materielss.fxml"));
            Parent root = load.load();
            Scene sc = new Scene(root);
            
            st.setScene(sc);
            st.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

  
    
}
