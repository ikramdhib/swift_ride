/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.gui;

/**
 *
 * @author Ines
 */
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 *
 * @author Ines
 */
 public class weatherAPI {
     static String stat;
     public static Map<String, Object> jsonToMap(String str) {
        Map<String, Object> map = new Gson().fromJson(str, new TypeToken<HashMap<String, Object>>() {}.getType());
        return map;
    }
     public void stat(){
         try {
             FXMLLoader loader=new FXMLLoader(getClass().getResource("meteo.fxml"));
             Parent root = loader.load();
             MeteoController mc=loader.getController();
             mc.setLabel(stat);
             
         } catch (IOException ex) {
             Logger.getLogger(weatherAPI.class.getName()).log(Level.SEVERE, null, ex);
         }
     }
     
     

    public static void main(String[] args) {
        String API_KEY = "33a8209bb9af4f5b30f9b56a83a3fb10";
        String LOCATION = "tunisie,ariana";
        String urlString = "https://api.openweathermap.org/data/2.5/weather?q=" + LOCATION + "&appid=" + API_KEY + "&units=metric";
        try {
            StringBuilder result = new StringBuilder();
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
        //    System.out.println(result);
             System.out.println(LOCATION);
            Map<String, Object> respMap = jsonToMap(result.toString());
            Map<String, Object> mainMap = jsonToMap(respMap.get("main").toString());
            Map<String, Object> windMap = jsonToMap(respMap.get("wind").toString());
            System.out.println("Current Temperature: " + mainMap.get("temp"));
            System.out.println("Current Humidity: " + mainMap.get("humidity"));
            System.out.println("Wind Speeds: " + windMap.get("speed"));
            System.out.println("Wind Angle: " + windMap.get("deg"));
   stat="Current Temperature: " + mainMap.get("temp")+"stat";
            System.out.println(stat);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}