/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
package edu.swiftride.gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;


 public class GraphController implements Initializable {
    
    @FXML
    private AnchorPane main_form;

    @FXML
    private BarChart<?, ?> Chart;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    
public Connection getConnection() {
        Connection cnn;
        try {
            cnn = DriverManager.getConnection("jdbc:mysql://localhost:3306/swiftride", "root", "");
            return cnn;
        } catch (Exception ex) {
            System.out.println("Error" + ex.getMessage());
            return null;
        }
    }
    public void chart(){
     String chartSql = "SELECT type, numero_trans FROM moyen_transport GROUP BY type ORDER BY (type) ASC LIMIT 8";   
    
    connect = getConnection();
    
    try{
        XYChart.Series chartData = new XYChart.Series();
        prepare = connect.prepareStatement(chartSql);
        result = prepare.executeQuery();
        
        while(result.next()){ //1 is the Data while 2 is the total
            chartData.getData().add(new XYChart.Data(result.getString(1),result.getInt(2)));
            
        }
        Chart.getData().add(chartData);
        
    }catch(Exception e){e.printStackTrace();}
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
*/