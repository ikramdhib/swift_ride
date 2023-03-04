/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.GUI;

import com.jfoenix.controls.JFXButton;
import edu.swiftride.Services.GarageCRUD;
import edu.swiftride.Services.MaterielCRUD;
import edu.swiftride.entities.DetailsMaterielsGarage;
import edu.swiftride.entities.Garage;
import edu.swiftride.entities.Materiel;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

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
    private TableView<DetailsMaterielsGarage> tb_materiels;
    @FXML
    private TableColumn tb_id;
    @FXML
    private TableColumn tb_nom;
    @FXML
    private TableColumn tb_garage;
    
    public static ObservableList<DetailsMaterielsGarage> listM = null;
    
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
    
    GarageCRUD gc = new GarageCRUD();
    
    private AutoCompletionBinding<String> auto;
    private String [] materiletab={"Cric hydraulique","Clé à cliquet","Jeu de douilles","Clé dynamométrique","Tournevis","Marteau","Pince multiprise","Cisaille","Scie à métaux","Lampe d'atelier","Gants de mécanique","Pont élévateur","Compresseur d'air","Nettoyeur haute pression","Machine de diagnostic","Banc de géométrie","Equilibreuse de roues","Démonte-pneus","Chariot de visite","Etabli","Poste à souder","Aspirateur d'atelier","Ensemble de lavage des freins"};
    
    private Set<String> possibleSugg = new HashSet<>(Arrays.asList(materiletab));
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      displayMateriel();
      getGarageItems();
     setCellValueFromTableToText();
     
     auto =TextFields.bindAutoCompletion(tf_materiel, possibleSugg);
     tf_materiel.setOnKeyPressed(new EventHandler<KeyEvent>(){
          @Override
          public void handle(KeyEvent event) {
              switch(event.getCode()){
                  
                  case ENTER :
                      autoCompletLearn(tf_materiel.getText().trim());
                      break;
                  default :
                    break;  
              }
          }
         
     });
      
       FilteredList<DetailsMaterielsGarage> filtreData = new FilteredList<>(listM, p->true);
         tf_recherche.textProperty().addListener((observable, oldValue, newValue) ->{
            filtreData.setPredicate( maintenance ->{
                
             if (newValue == null || newValue.isEmpty()) {
            return true;
             }
               
             String lowerCaseFilter = newValue.toLowerCase();
             
             if(maintenance.getNom().toLowerCase().contains(lowerCaseFilter)){
                 
                 return true;
               
             }
             else if(maintenance.getGarage().toLowerCase().contains(lowerCaseFilter)){
                 
                 return true;
               
             }
             else
                 return false;
            });
       });
       
       SortedList<DetailsMaterielsGarage> sortedData = new SortedList<>(filtreData);
       sortedData.comparatorProperty().bind(tb_materiels.comparatorProperty());
       tb_materiels.setItems(sortedData);
      
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
    
    private void autoCompletLearn(String s){
        possibleSugg.add(s);
        if(auto!=null){
            auto.dispose();
        }
        auto=TextFields.bindAutoCompletion(tf_materiel,possibleSugg );
    }
    
    public boolean isEnought(String s){
        
        return s.length()>15;
        
    }
    
    public void testLength(KeyEvent ev){
       String s = ev.getCharacter();
       if(isEnought(s)){
           ev.consume();
       }
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
         
        
        
        List<Garage> listg = gc.displayGarages();
        
        
        List<DetailsMaterielsGarage> lmg = new ArrayList<>();
        
        for(Garage g : listg){
            
            List<Materiel> lm= mc.getMaterielsWithGarageId(g.getId());
            
            for(Materiel m :lm){
                
                DetailsMaterielsGarage dm = new DetailsMaterielsGarage();
                
                dm.setId(m.getId());
                dm.setNom(m.getNom());
                dm.setGarage(g.getMatricule_garage().concat("-").concat(""+g.getId()));
                
                lmg.add(dm);
            }
            
        }
        System.out.println(lmg);
        
        tb_id.setCellValueFactory(new PropertyValueFactory("id"));
        tb_nom.setCellValueFactory(new PropertyValueFactory("nom"));
        tb_garage.setCellValueFactory(new PropertyValueFactory("garage"));
        
        
        listM=FXCollections.observableArrayList(lmg);
        
        tb_materiels.setItems(listM);
    }

    @FXML
    private void deletMateriel(ActionEvent event) {
        
        if(tb_materiels.getSelectionModel().getSelectedItems().isEmpty()){
               Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("Vous devez sélectionner le Materiel !");
                a.setTitle("ERREUER");
                 Optional<ButtonType> res=a.showAndWait();
                if(res.get() ==ButtonType.OK){
                a.close();
        }
        }
        else{
         DetailsMaterielsGarage m =(DetailsMaterielsGarage) tb_materiels.getSelectionModel().getSelectedItem();
         
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
    }
    
    private void setCellValueFromTableToText(){
        
        tb_materiels.setOnMouseClicked((MouseEvent event) -> {
            DetailsMaterielsGarage m= (DetailsMaterielsGarage) tb_materiels.getItems().get(tb_materiels.getSelectionModel()
                    .getSelectedIndex());
            tf_materiel.setText(m.getNom());
            String[] str =m.getGarage().split("-");
            int idd=Integer.parseInt(str[1]);
            Garage g = gc.getGaragewithID(idd);
            cb_garage.setValue(g);
            
            id = m.getId();
        });
        
    }

    @FXML
    private void modifyMateriel(ActionEvent event) {
        
        String materiel = tf_materiel.getText();
        
        Materiel m =new Materiel();
        
        m.setNom(materiel);
        m.setId(id);
        
        m.setId_garage(id_g);
        System.out.println("ig"+id_g);
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
                                   b.close();
                                }
                }
    }
    
  
    
    
}
