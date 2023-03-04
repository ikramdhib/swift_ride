package edu.swiftride.gui;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
package pidev.gui;

import com.teamdev.jxmaps.GeocoderCallback;
import com.teamdev.jxmaps.GeocoderRequest;
import com.teamdev.jxmaps.GeocoderResult;
import com.teamdev.jxmaps.GeocoderStatus;
import com.teamdev.jxmaps.InfoWindow;
import com.teamdev.jxmaps.Map;

import com.teamdev.jxmaps.MapStatus;
import com.teamdev.jxmaps.MapViewOptions;
import com.teamdev.jxmaps.Marker;
import com.teamdev.jxmaps.javafx.*;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.Scene;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author skann
 */
/*
public class MapController implements Initializable {
     private BorderPane borderPanel;
     VBox cosa;
 @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     public void initialize() 
     {
         MapView sample = new MapView();
         sample.setOnMapReadyHandler(new MapReadyHandler() {
       @Override
       public void onMapReady(MapStatus status) {
          if (status == MapStatus.MAP_STATUS_OK) {
              final Map map = mapView.getMap();
              map.setCenter(new LatLng(35.91466, 10.312499));
              map.setZoom(2.0);
          }
       }

         });
         sample.setMaxHeight(394.0);
         sample.setPrefWidth(704.0);
         sample.setVisible(true);
         borderPanel = new BorderPane(sample);
         borderPanel.setVisible(true);
         borderPanel.setLayoutX(76.0);
         borderPanel.setLayoutY(134.0);
         borderPanel.setPrefHeight(200.0);
         borderPanel.setPrefWidth(467.0);
         cosa.getChildren().add(borderPanel);
     }
}
*/
/*
public class MapController implements Initializable {
       Stage stage;
          private static final String ACCESS_TOKEN = "<pk.eyJ1Ijoic2thbmRlcm5hc3JpIiwiYSI6ImNsZWgxdTNnbjBraDkzb2xka3hyaHhza3IifQ.gR_qNalGTJb1bXRHiwAK8g>";
   @Override
    public void initialize(URL location, ResourceBundle resources) {
              // Create a new JFXPanel to host the JxMaps browser

        // Create a new MapViewOptions object to configure the Mapbox map view
        MapViewOptions options = new MapViewOptions();
        options.accessToken(ACCESS_TOKEN);
        options.center(new com.teamdev.jxmaps.LatLng(37.7749, -122.4194)); // San Francisco
        options.zoom(12);

        // Create a new MapView object with the MapViewOptions object
        MapView mapView = new MapView(options);

        // Add a MapReadyHandler to the MapView to wait for the map to be loaded
        mapView.setOnMapReadyHandler(new MapReadyHandler() {
            @Override
            public void onMapReady(MapStatus status) {
                if (status == MapStatus.MAP_STATUS_OK) {
                    // Get the Map object from the MapView
                    Map map = mapView.getMap();

                    // Add any custom map options or markers here
                }
            }
        });

        // Add the MapView to the JFXPanel
        jfxPanel.setScene(new Scene(new StackPane(mapView)));

        // Show the JFXPanel in a JavaFX stage
        primaryStage.setScene(new Scene(jfxPanel));
        primaryStage.setTitle("Mapbox JxMaps Demo");
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);
        primaryStage.show();

        // Show the MapView
        mapView.showMap();
  Scene scene = new Scene(new BorderPane(mapView), 700, 500);
        stage.setTitle("JxMaps - Hello, World!");
        stage.setScene(scene);
        stage.show();
        }


  
    }

      
*/
 



