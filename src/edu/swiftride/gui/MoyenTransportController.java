/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.gui;

import edu.swiftride.entities.MoyenTransport;
import edu.swiftride.services.MoyenTransportCRUD;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import static jdk.nashorn.internal.objects.NativeJava.type;

/**
 * FXML Controller class
 *
 * @author Ines
 */
public class MoyenTransportController implements Initializable {

    
    @FXML
    private TextField txtid;
    @FXML
    private TextField txttype;
    @FXML
    private TextField txtnumero_trans;
    @FXML
    private TextField txtnb_station;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnSupprimer;
    @FXML
    private TableView<MoyenTransport> tableMoyenTransport;
    @FXML
    private TableColumn<MoyenTransport, Integer> colid;
    @FXML
    private TableColumn<MoyenTransport, String> coltype;
    @FXML
    private TableColumn<MoyenTransport, Integer>  colnumero_transport;
    @FXML
    private TableColumn<MoyenTransport, Integer> colnb_station;
    @FXML
    private Label label;
     
        MoyenTransportCRUD pcm=new MoyenTransportCRUD();
  @FXML
  private void handleButtonAction(ActionEvent event){
      if(event.getSource() == btnAjouter){
          insertRecord();
      }
      else if(event.getSource() == btnModifier){
          updateRecord();
      }
      else if(event.getSource() == btnSupprimer){
          deleteButton();
      }
  }
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showMoyenTransport();
    }    
    public Connection getConnection(){
        Connection cnn;
        try{
            cnn=DriverManager.getConnection("jdbc:mysql://localhost:3306/swiftride","root", "");
            return cnn;
        }catch(Exception ex){
            System.out.println("Error" +ex.getMessage());
            return null;
        }
     }
      public void showMoyenTransport(){
        ObservableList<MoyenTransport> list=getMoyenTransport();

         colid.setCellValueFactory(new PropertyValueFactory<MoyenTransport, Integer>("id"));
       coltype.setCellValueFactory(new PropertyValueFactory<MoyenTransport, String>("type"));
       colnumero_transport.setCellValueFactory(new PropertyValueFactory<MoyenTransport, Integer>("numero_trans"));
       colnb_station.setCellValueFactory(new PropertyValueFactory<MoyenTransport, Integer>("nb_station"));
       tableMoyenTransport.setItems(list);
       
    }
       private ObservableList<MoyenTransport> getMoyenTransport() {
        ObservableList<MoyenTransport> listM =FXCollections.observableArrayList();
        Connection cnn= getConnection();
       String query= "SELECT * FROM moyen_transport";
       Statement st;
       ResultSet rs;
       try{
           st=cnn.createStatement();
           rs=st.executeQuery(query);
           MoyenTransport m;
           while(rs.next()){
               m=new MoyenTransport(rs.getInt("id"),rs.getString("type"),rs.getInt("numero_trans"),rs.getInt("nb_station"),rs.getInt("id_admin"));
               listM.add(m);
           }
       }catch(Exception ex){
           ex.printStackTrace();
           
          }
       return listM;
    }
       
      
 
       public void insertRecord() {
           
    /*String query = "INSERT INTO moyen_transport (id, type, numero_trans, nb_station,id_admin) VALUES (?, ?, ?, ?,1)";
    try (Connection conn = getConnection();
         PreparedStatement pstmt = conn.prepareStatement(query)) {
    
        pstmt.setInt(1, Integer.parseInt(txtid.getText()));
        pstmt.setString(2, txttype.getText());
        pstmt.setInt(3, Integer.parseInt(txtnumero_trans.getText()));
        pstmt.setInt(4, Integer.parseInt(txtnb_station.getText()));
        pstmt.executeUpdate();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    showMoyenTransport();
}*/
     MoyenTransport m=new MoyenTransport();
       
       // m.setId(Integer.parseInt(txtid.getText()));
    
       
         
         String type = txttype.getText();
        String numero_trans = txtnumero_trans.getText();
        String nb_station = txtnb_station.getText();
         
         // Check that required fields are not empty
        if (type.isEmpty() || numero_trans.isEmpty() || nb_station.isEmpty() ){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Missing fields");
            alert.setHeaderText("All fields must be filled");
            alert.showAndWait();
            return;
        }
        
        
        
            m.setType(txttype.getText());
        m.setNumero_trans(Integer.parseInt(txtnumero_trans.getText()));
         m.setNb_station(Integer.parseInt(txtnb_station.getText()));

        pcm.ajouterMoyenTransport(m);
          tableMoyenTransport.setItems(getMoyenTransport());

    }
 
   
       private void updateRecord(){
                MoyenTransport m=new MoyenTransport();
         /*String query = "UPDATE moyen_transport SET id = " + txtid.getText() + " , type = '" + txttype.getText() + "', numero_trans = " + txtnumero_trans.getText() + ", nb_station = " + txtnb_station.getText() + " WHERE id = " + txtid.getText() + " ";
         executeQuery(query);
         showMoyenTransport();*/
         m.setId(tableMoyenTransport.getSelectionModel().getSelectedItem().getId());
        m.setType(txttype.getText());
        m.setNumero_trans(Integer.parseInt(txtnumero_trans.getText()));
         m.setNb_station(Integer.parseInt(txtnb_station.getText()));

         pcm.modifierMoyenTransport(m);
         tableMoyenTransport.setItems( getMoyenTransport());

       } 
      private void deleteButton(){
               MoyenTransport m=new MoyenTransport();
        pcm.supprimerMoyenTransport(tableMoyenTransport.getSelectionModel().getSelectedItem());
        //removeAll utilisée pour supprimer tous les éléments d’une liste contenus dans une collection spécifiée.
 
        tableMoyenTransport.getItems().removeAll(tableMoyenTransport.getSelectionModel().getSelectedItem());
    /* String query ="DELETE FROM moyen_transport WHERE id =" +txtid.getText() + "";
     executeQuery(query);
     showMoyenTransport();*/
    
}

  /*  private void executeQuery(String query) {
       Connection conn = getConnection();
       Statement st;
       try{
           st = conn.createStatement();
           st.executeUpdate(query);
           
       }catch(Exception ex){
           ex.printStackTrace();
       }
    }*/
    
   

   }   



