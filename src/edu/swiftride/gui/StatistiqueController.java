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
public class StatistiqueController implements Initializable {

    @FXML
    private AnchorPane home_form;
    @FXML
    private Label home_availableCars;
    @FXML
    private Label home_notavailableCars;
    @FXML
    private Label home_totalCustomers;
    @FXML
    private BarChart<?, ?> home_incomeChart;
    @FXML
    private LineChart<?, ?> home_customerChart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        homeAvailableCars();
        homenotAvailableCars();
        homeAusers();
        homeIncomeChart();
        kilometrageparcirculation();
    }    
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;
     Voiture voiture = null ; 
     VoitureCRUD  pcm =new VoitureCRUD();
     AccidentCRUD acd = new AccidentCRUD();
    
    public void homeAvailableCars(){
        
        String sql = "SELECT COUNT(id) FROM voiture WHERE etat = 'Available'";
        
        connect = MyConnection.getInstance().getCnx();
        int countAC = 0;
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            while(result.next()){
                countAC = result.getInt("COUNT(id)");
            }
            
            home_availableCars.setText(String.valueOf(countAC));
            
        }catch(Exception e){e.printStackTrace();}
        
    }
     public void homenotAvailableCars(){
        
        String sql = "SELECT COUNT(id) FROM voiture WHERE etat = 'Not Available'";
        
        connect = MyConnection.getInstance().getCnx();
        int countNAC = 0;
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            while(result.next()){
                countNAC = result.getInt("COUNT(id)");
            }
            
            home_notavailableCars.setText(String.valueOf(countNAC));
            
        }catch(Exception e){e.printStackTrace();}
        
    }
     public void homeAusers(){
        
        String sql = "SELECT COUNT(id) FROM utilisateur WHERE role = 'client'";
        
        connect = MyConnection.getInstance().getCnx();
        int countU = 0;
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            while(result.next()){
                countU = result.getInt("COUNT(id)");
            }
            
            home_totalCustomers.setText(String.valueOf(countU));
            
        }catch(Exception e){e.printStackTrace();}
        
    }
     public void homeIncomeChart(){
        
        home_incomeChart.getData().clear();
        
        String sql = "SELECT date_circulation , SUM(prix) FROM voiture GROUP BY date_circulation ORDER BY TIMESTAMP(date_circulation) ASC LIMIT 6";
        
        connect = MyConnection.getInstance().getCnx();
        
        try{
            XYChart.Series chart = new XYChart.Series();
            
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            while(result.next()){
                chart.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));
            }
            
            home_incomeChart.getData().add(chart);
            
        }catch(Exception e){e.printStackTrace();}
    }
      public void kilometrageparcirculation(){
        
        home_customerChart.getData().clear();
        
        String sql = "SELECT date_circulation , SUM(Kilometrage) FROM voiture GROUP BY date_circulation ORDER BY TIMESTAMP(date_circulation) ASC LIMIT 6";
        
        connect = MyConnection.getInstance().getCnx();
        
        try{
            XYChart.Series chart = new XYChart.Series();
            
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            while(result.next()){
                chart.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));
            }
            
            home_customerChart.getData().add(chart);
            
        }catch(Exception e){e.printStackTrace();}
    }
    
}
