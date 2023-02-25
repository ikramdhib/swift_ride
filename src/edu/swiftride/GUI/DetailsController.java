/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.GUI;

import static edu.swiftride.GUI.MaintenanceController.listM;
import edu.swiftride.Services.GarageCRUD;
import edu.swiftride.Services.MaintenanceCRUD;
import edu.swiftride.Services.MaterielCRUD;
import edu.swiftride.Services.VoitureCRUD;
import edu.swiftride.entities.Details;
import edu.swiftride.entities.Garage;
import edu.swiftride.entities.Maintenance;
import edu.swiftride.entities.Voiture;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.logging.log4j.LogManager;
import static org.apache.poi.hssf.usermodel.HeaderFooter.file;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

/**
 * FXML Controller class
 *
 * @author dhibi
 */
public class DetailsController implements Initializable {

    @FXML
    private TableView <Details>table_detail;
    @FXML
    private TableColumn<?, ?> tb_date_mat;
    @FXML
    private TableColumn<?, ?> tb_type_mat;
    @FXML
    private TableColumn<?, ?> tb_v_matri;
    @FXML
    private TableColumn<?, ?> tb_v_model;
    @FXML
    private TableColumn<?, ?> tb_v_mrq;
    @FXML
    private TableColumn<?, ?> tb_g;
    @FXML
    private TableColumn<?, ?> tb_materiels;
    @FXML
    private TableColumn<?, ?> tb_datf;
    @FXML
    private TextField tf_recherche;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        diplayDetail();
        
        
        
         FilteredList<Details> filtreData = new FilteredList<>(listD , p->true);
       
        
         tf_recherche.textProperty().addListener((observable, oldValue, newValue) ->{
            filtreData.setPredicate( details ->{
                
             if (newValue == null || newValue.isEmpty()) {
            return true;
             }
               
             String lowerCaseFilter = newValue.toLowerCase();
             if(details.getType().toLowerCase().contains(lowerCaseFilter)){
                 
                 return true;
             }
             else if(details.getDate_maintenance().toString().toLowerCase().contains(lowerCaseFilter))
             {  return true ; }
              else if(details.getFin_maintenance().toString().toLowerCase().contains(lowerCaseFilter))
             {  return true ; }
                else if(details.getMarque().toLowerCase().contains(lowerCaseFilter))
             {  return true ; }
                else if(details.getMatricule().toLowerCase().contains(lowerCaseFilter))
             {  return true ; }
               else if(details.getIntitule().toLowerCase().contains(lowerCaseFilter))
             {  return true ; }
                else if(details.getMateriels().toLowerCase().contains(lowerCaseFilter))
             {  return true ; }
                 else if(details.getModel().toLowerCase().contains(lowerCaseFilter))
             {  return true ; }
               else if(details.getType().toLowerCase().contains(lowerCaseFilter))
             {  return true ; }
                else
                  return false;
            });
       });
       
       SortedList<Details> sortedData = new SortedList<>(filtreData);
       sortedData.comparatorProperty().bind(table_detail.comparatorProperty());
       table_detail.setItems(sortedData);
       
        
        
    }    
    
    
    public static ObservableList<Details> listD = null;
    
    public void diplayDetail(){
        
       
  List <Details> dtl=new ArrayList();
  
        GarageCRUD gc =new GarageCRUD();
        MaintenanceCRUD mcc= new MaintenanceCRUD();
        VoitureCRUD vc = new VoitureCRUD();
        MaterielCRUD mc = new MaterielCRUD();
        
        List<Voiture> v = vc.diplayDetailVoitures();
  
 
  
  for(Voiture vi :v ){
      
     
       List <Maintenance> m = mcc.getwithCarId(vi.getId());
     
       for(Maintenance mm : m){
           
            Details dd = new Details();
            List <Garage> g = gc.getGaragewithMaint(mm.getId_garage());
            System.out.println(mm.getId_garage());
            
            for(Garage gg : g ){
                
               String mt ;
               
                dd.setIntitule(gg.getMatricule_garage());
                
                 mt=mc.getMaterielsWithGId(gg.getId());
                dd.setMateriels(mt);
                
            }
           dd.setMarque(vi.getMarque());
           dd.setMatricule(vi.getMatricule());
           dd.setModel(vi.getModel());
           dd.setDate_maintenance(mm.getDate_maintenance());
           dd.setFin_maintenance(mm.getFin_maintenance());
           dd.setType(mm.getType());
           
            dtl.add(dd);
       }
       
  }
  
        System.out.println(dtl);
         System.out.println(dtl.size());
         
         
         tb_date_mat.setCellValueFactory( new PropertyValueFactory("date_maintenance")); 
         tb_datf.setCellValueFactory( new PropertyValueFactory("fin_maintenance")); 
         tb_type_mat.setCellValueFactory(new PropertyValueFactory("type"));
         tb_v_matri.setCellValueFactory(new PropertyValueFactory("matricule"));
         tb_v_model.setCellValueFactory(new PropertyValueFactory("model"));
         tb_v_mrq.setCellValueFactory(new PropertyValueFactory("marque"));
         tb_g.setCellValueFactory(new PropertyValueFactory("intitule"));
         tb_materiels.setCellValueFactory(new PropertyValueFactory("materiels"));
           listD =FXCollections.observableArrayList(dtl);
        
        table_detail.setItems(listD);
         
         
        
    }
    
    
    
    public void exporterExcel(){
        
        try {
            // Create a new workbook and sheet
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Sheet1");
            XSSFRow headerRow = sheet.createRow(0);
            ObservableList<TableColumn<Details, ?>> columns = table_detail.getColumns();
            for (int i = 0; i < columns.size(); i++) {
                TableColumn column = columns.get(i);
                String columnName = column.getText();
                headerRow.createCell(i).setCellValue(columnName);
            }
            ObservableList<Details> rows = table_detail.getItems();
            //lezem enahi el for 
            int i =0;
            int r = rows.size();
            while(r!=0){
                Details row = rows.get(i);
                
                XSSFRow sheetRow = sheet.createRow(i + 1);
                    if (row != null) {
                        sheetRow.createCell(0).setCellValue(""+row.getDate_maintenance());
                        sheetRow.createCell(1).setCellValue(""+row.getFin_maintenance());
                        sheetRow.createCell(2).setCellValue(""+row.getType());
                        sheetRow.createCell(3).setCellValue(""+row.getMarque());
                        sheetRow.createCell(4).setCellValue(""+row.getModel());
                        sheetRow.createCell(5).setCellValue(""+row.getMarque());
                        sheetRow.createCell(6).setCellValue(""+row.getIntitule());
                        sheetRow.createCell(7).setCellValue(""+row.getMateriels());
                        i++;
                        r--;
                        
                    }
                }
            
            File outputFile = new File("details.xlsx");
            
            FileOutputStream fileOut = new FileOutputStream(outputFile);
            workbook.write(fileOut);
            fileOut.close();
            
             Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("Le chemin du fichier EXCEL:"+outputFile.getAbsolutePath());
                a.setTitle("INFORMATION");
                 Optional<ButtonType> res=a.showAndWait();
                if(res.get() ==ButtonType.OK){
                    a.close();
                }
            System.out.println("Table data exported to " + outputFile.getAbsolutePath());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
}
