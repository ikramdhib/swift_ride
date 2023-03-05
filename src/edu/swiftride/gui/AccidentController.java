/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.lowagie.text.Document;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import edu.swiftride.entities.Accident;
import edu.swiftride.entities.Voiture;
import edu.swiftride.services.AccidentCRUD;
import edu.swiftride.utils.MyConnection;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AccidentController implements Initializable {
   @FXML
    private AnchorPane accident_form;
@FXML
    private TextField type;

    @FXML
    private JFXDatePicker dateacc;

    @FXML
    private JFXButton accident_add;

    @FXML
    private TableView<Accident> accident_tableView;

    @FXML
    private TableColumn<?, ?> accident_tableView_id;

    @FXML
    private TableColumn<?, ?> accident_tableView_col_CarID;

    @FXML
    private TableColumn<?, ?> accident_tableView_col_date;

    @FXML
    private TableColumn<?, ?> accident_tableView_col_condition;

    @FXML
    private TableColumn<?, ?> accident_tableView_col_lieu;

    @FXML
    private TableColumn<?, ?> accident_tableView_type;

    @FXML
    private ComboBox<?> idacc;

    @FXML
    private ComboBox<String> lieu;


    @FXML
    private TextField condition;

    @FXML
    private JFXButton accident_remove;

    @FXML
    private JFXButton modif_accBtn;

    @FXML
    private JFXButton refresh_btn;

    @FXML
    private JFXButton clear_btn;

    @FXML
    private JFXButton Accc_stat;

    @FXML
    private JFXButton accident_pdf;

    @FXML
    private JFXButton home_btn;

    @FXML
    private JFXButton profile_btn;

    @FXML
    private JFXButton availableCars_btn;

    @FXML
    private JFXButton transport_btn;

    @FXML
    private JFXButton organisation_btn;

    @FXML
    private JFXButton reservation_btn;

    @FXML
    private JFXButton maintenance_btn;

    @FXML
    private JFXButton client_btn;

    @FXML
    private JFXButton accident_btn;

    @FXML
    private Button minimize;

    @FXML
    private Button close;
    /**
     * Initializes the controller class.
     */
    
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;
   Accident accident;
    
     AccidentCRUD acd = new AccidentCRUD();
     
     @FXML
    public void close() {
        System.exit(0);
    }
//This is a Java method that minimizes the window of a JavaFX application.

     //The minimize() method gets a reference to the Stage object representing the main window of the application using the getScene() 
        //method on a Node called main_form and casting 
        //its window to a Stage object. The setIconified(true) method is then called on the Stage object to minimize the window.
    @FXML
    public void minimize() {
        Stage stage = (Stage) accident_form.getScene().getWindow();
        stage.setIconified(true);
    }
     
            public ObservableList<Accident> accidentlist() {

        ObservableList<Accident> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM accident";

        connect = MyConnection.getInstance().getCnx();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

           Accident accd ;

            while (result.next()) {
                        accd = new Accident(
                         result.getInt("id"),
                         result.getString("type"),
                         result.getDate("date"),
                         result.getString("description"),
                         result.getString("lieu"),
                         result.getString("id_voiture"));

                 listData.add(accd);
            }
              
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }
    
private ObservableList<Accident> accidentshowlist;

    public void aciidentShowListData() {
        accidentshowlist = accidentlist();
        
        accident_tableView_id.setCellValueFactory(new PropertyValueFactory("id"));
        accident_tableView_col_CarID.setCellValueFactory(new PropertyValueFactory("id_voiture"));
        accident_tableView_type.setCellValueFactory(new PropertyValueFactory("type"));
      accident_tableView_col_date.setCellValueFactory(new PropertyValueFactory("dateac"));
        accident_tableView_col_condition.setCellValueFactory(new PropertyValueFactory("description"));
        accident_tableView_col_lieu.setCellValueFactory(new PropertyValueFactory("lieu"));
        
       accident_tableView.setItems(accidentshowlist);
    }
    
    @FXML
    public void refresh(){
         
        accidentshowlist = accidentlist();
        
        accident_tableView_id.setCellValueFactory(new PropertyValueFactory("id"));
        accident_tableView_col_CarID.setCellValueFactory(new PropertyValueFactory("id_voiture"));
        accident_tableView_type.setCellValueFactory(new PropertyValueFactory("type"));
      accident_tableView_col_date.setCellValueFactory(new PropertyValueFactory("dateac"));
        accident_tableView_col_condition.setCellValueFactory(new PropertyValueFactory("description"));
        accident_tableView_col_lieu.setCellValueFactory(new PropertyValueFactory("lieu"));
        
       accident_tableView.setItems(accidentshowlist);
    
    }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        aciidentShowListData();
        CarId();
        refresh();
        setCellValueFromTableToText();
        lieuxlist();
    }    

        

    @FXML
    public void insertRecordaccident() {
  
     Accident ac= new Accident();
       
             
        
        
        String idac =   idacc.getSelectionModel().getSelectedItem().toString();
       String ty = type.getText();
        String con = condition.getText();
        String lie = lieu.getSelectionModel().getSelectedItem().toString();
         LocalDate dt = dateacc.getValue();

        
        
                
         // Check that required fields are not empty
        
    
 
            ac.setDateac(Date.valueOf(dt));
            ac.setDescription(con);
            ac.setId_voiture(idac);
            ac.setLieu(lie);
            ac.setType(ty);
            
            
            acd.ajouteraccident(ac);
            
            
            
        
        }
        
        private String[] listl = {"Ariana (Aryanah)", "Beja (Bajah)", "Ben Arous (Bin 'Arus)", "Bizerte (Banzart)", "Gabes (Qabis)", "Gafsa (Qafsah)", "Jendouba (Jundubah)", "Kairouan (Al Qayrawan)", "Kasserine (Al Qasrayn)", "Kebili (Qibili)", "Kef (Al Kaf)", "Mahdia (Al Mahdiyah)", "Manouba (Manubah)", "Medenine (Madanin)", "Monastir (Al Munastir)", "Nabeul (Nabul)", "Sfax (Safaqis)", "Sidi Bou Zid (Sidi Bu Zayd)", "Siliana (Silyanah)", "Sousse (Susah)", "Tataouine (Tatawin)", "Tozeur (Tawzar)", "Tunis", "Zaghouan (Zaghwan)"};
    @FXML
    public void lieuxlist() {

        List<String> listM = new ArrayList<>();

        for (String data : listl) {
            listM.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listM);
        lieu.setItems(listData);
    }   
 @FXML     
 public void supprimeraccident(){
       Accident acc =accident_tableView.getSelectionModel().getSelectedItem();
      //  Plat p = new Plat(c.getreference());
        acd.supprimeraccident(acc);
          aciidentShowListData();
        Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("wow");
                alert.setHeaderText(null);
                alert.setContentText("car supprimé");
                alert.showAndWait(); 
    }
 
    @FXML
    public void CarId() {
        
        String sql = "SELECT * FROM voiture WHERE etat = 'Available'";

        connect = MyConnection.getInstance().getCnx();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            ObservableList listData = FXCollections.observableArrayList();

            while (result.next()) {
                listData.add(result.getInt("id"));
            }

            idacc.setItems(listData);

            

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
     int idd =0;
    private void setCellValueFromTableToText(){
        
        accident_tableView.setOnMouseClicked((MouseEvent event) -> {
            Accident a= (Accident) accident_tableView.getItems().get(accident_tableView.getSelectionModel()
                    .getSelectedIndex());
            type.setText(a.getType());
            lieu.setValue(a.getLieu());
            
            dateacc.setValue(a.getDateac().toLocalDate());
           condition.setText(a.getDescription());
            
           idd = a.getId();
            System.out.println(idd);
        });
}
String lieuxx;
     public void modifieracci() {
    Accident acci = new Accident();
    
    // Get the data from the text fields
    String ty = type.getText();
    String idcar = idacc.getSelectionModel().getSelectedItem().toString();
     lieuxx = lieu.getSelectionModel().getSelectedItem().toString();
    String cond = condition.getText();
    
         LocalDate dt = dateacc.getValue();

    // Set the data to the Accident object
    acci.setDateac(Date.valueOf(dt));
    acci.setDescription(cond);
    acci.setId_voiture(idcar);
    acci.setLieu(lieuxx);
    acci.setType(ty);
    acci.setId(idd);
    
    // Update the data in the database
    acd.modifieracc(acci);

    // Refresh the TableView data
    accident_tableView.setItems(accidentlist());
Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Modification réussie");
    alert.setHeaderText("La modification a été effectuée avec succès.");
    alert.showAndWait();
        }
public void accidentClear() {
       condition.setText("");
      
       type.setText("");
       dateacc.setVisible(false);
       idacc.getSelectionModel().clearSelection();
        
        
}
public void Accidentstatistique() {
          FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("accidentstatistique.fxml"));
                            try {
                                loader.load();
                            } catch (Exception ex) {
                               System.err.println(ex.getMessage());
                            }
                            
                           AccidentstatistiqueController  mcc = loader.getController();
                           // mrc.setUpdate(true);
                           
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
 }
                             
public void exportaccidentToPdf() {
    try {
        // Create a new PDF document
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("Accident2.pdf"));

        // Open the document
        document.open();

        // Create a new PDF table with the same number of columns as your TableView
        PdfPTable pdfTable = new PdfPTable(accident_tableView.getColumns().size());

        // Add table headers
        for (TableColumn<?, ?> column : accident_tableView.getColumns()) {
            pdfTable.addCell(new PdfPCell(new Phrase(column.getText())));
        }

        // Add table rows
        for (Accident acc : accidentshowlist) {
           pdfTable.addCell(new PdfPCell(new Phrase(Integer.toString(acc.getId()))));
            pdfTable.addCell(new PdfPCell(new Phrase(acc.getId_voiture())));
           pdfTable.addCell(new PdfPCell(new Phrase(""+acc.getDateac())));
            pdfTable.addCell(new PdfPCell(new Phrase(acc.getDescription())));
             pdfTable.addCell(new PdfPCell(new Phrase(acc.getLieu())));
            pdfTable.addCell(new PdfPCell(new Phrase(acc.getType())));
            
            
        }

        // Add the PDF table to the document
        document.add(pdfTable);

        // Close the document
        document.close();

        System.out.println("PDF created successfully");
    } catch (Exception e) {
        e.printStackTrace();
    }

    }
    @FXML
    private void minimize(ActionEvent event) {
    }

    @FXML
    private void close(ActionEvent event) {
    }
    
}
