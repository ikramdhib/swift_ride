/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.gui;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Ines
 */
public class MeteoController implements Initializable {
 @FXML
    private Label txtlabel;
    
    @FXML
    private TextField countryField;
    
    @FXML
    private TextField cityField;
    
    @FXML
    private Button getWeatherButton;

@Override
    public void initialize(URL url, ResourceBundle rb) {
        getWeatherButton.setOnAction(event -> {
            String country = countryField.getText();
            String city = cityField.getText();
            String API_KEY = "33a8209bb9af4f5b30f9b56a83a3fb10";
            String urlString = "https://api.openweathermap.org/data/2.5/weather?q=" + country + "," + city + "&appid=" + API_KEY + "&units=metric";
            try {
                StringBuilder result = new StringBuilder();
                URL urlObj = new URL(urlString);
                URLConnection conn = urlObj.openConnection();
                BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }
                rd.close();
                Map<String, Object> respMap = jsonToMap(result.toString());
                Map<String, Object> mainMap = jsonToMap(respMap.get("main").toString());
                Map<String, Object> windMap = jsonToMap(respMap.get("wind").toString());
                txtlabel.setText("Current Temperature: " + mainMap.get("temp") + "°C\nCurrent Humidity: " + mainMap.get("humidity") + "%\nWind Speeds: " + windMap.get("speed") + "m/s\nWind Angle: " + windMap.get("deg") + "°");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        });
    }
    
    public static Map<String, Object> jsonToMap(String str) {
        Map<String, Object> map = new Gson().fromJson(str, new TypeToken<HashMap<String, Object>>() {}.getType());
        return map;
    }
} 