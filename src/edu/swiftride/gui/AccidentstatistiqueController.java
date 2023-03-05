/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.gui;

import edu.swiftride.entities.Voiture;
import edu.swiftride.services.AccidentCRUD;
import edu.swiftride.services.VoitureCRUD;
import edu.swiftride.utils.MyConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AccidentstatistiqueController implements Initializable {

    @FXML
    private AnchorPane home_form;
    @FXML
    private Label total_accident;
    @FXML
    private Label num_accident;
    @FXML
    private BarChart<?, ?> data_accidentchart;
    @FXML
    private LineChart<?, ?> accident_chart;
     @FXML
    private BarChart<?, ?> accident_chart2;


    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
       totalaccident();
       accdientchart();
              accdientchart2();

       
    }    
     private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;
     Voiture voiture = null ; 
     VoitureCRUD  pcm =new VoitureCRUD();
     AccidentCRUD acd = new AccidentCRUD();
    
    public void totalaccident(){
        
        String sql = "SELECT COUNT(id) FROM accident ";
        
        connect = MyConnection.getInstance().getCnx();
        int countAC = 0;
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            while(result.next()){
                countAC = result.getInt("COUNT(id)");
            }
            
            total_accident.setText(String.valueOf(countAC));
            
        }catch(Exception e){e.printStackTrace();}
        
    }
     
   
     public void accdientchart(){
        
      data_accidentchart.getData().clear();
        
        String sql = "SELECT date , COUNT(id) FROM accident GROUP BY date ORDER BY TIMESTAMP(date) ASC LIMIT 6";
        
        connect = MyConnection.getInstance().getCnx();
        
        try{
            XYChart.Series chart = new XYChart.Series();
            
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            while(result.next()){
                chart.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));
            }
            
            data_accidentchart.getData().add(chart);
            
        }catch(Exception e){e.printStackTrace();}
    }
     public void accdientchart2(){
        
      accident_chart2.getData().clear();
        
        String sql = "SELECT lieu , COUNT(id) FROM accident GROUP BY id ORDER BY(id) ";
        
        connect = MyConnection.getInstance().getCnx();
        
        try{
            XYChart.Series chart = new XYChart.Series();
            
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            while(result.next()){
                chart.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));
            }
            
            accident_chart2.getData().add(chart);
            
        }catch(Exception e){e.printStackTrace();}
    }
   

}
