/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.services;
/**
 *
 * @author Ines
 */
import java.sql.*;
public class TransportFareCalculator {
    private Connection conn; 
    public TransportFareCalculator(String url, String user, String password) {
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public double calculateFare(String a, String b) {
        double price = 0.0;
        try {
            // Récupérer les enregistrements des stations de départ et d'arrivée
            String stationQuery = "SELECT * FROM station WHERE nom_station IN (?,?)";
            PreparedStatement stationStmt = conn.prepareStatement(stationQuery);
            stationStmt.setString(1, a);
            stationStmt.setString(2, b);
            ResultSet stationResult = stationStmt.executeQuery();
            stationResult.next();
            int startStationId = stationResult.getInt("ids");
            String startStationName = stationResult.getString("nom_station");
            stationResult.next();
            int endStationId = stationResult.getInt("ids");
            String endStationName = stationResult.getString("nom_station");
            // Récupérer tous les arrêts de transport sur le trajet entre les stations a et b
            String transportQuery = "SELECT m.type, m.numero_trans, s.ids, s.ville, s.nom_station FROM moyen_transport m JOIN station s ON m.id = s.ids WHERE s.nom_station IN (?, ?) ORDER BY m.id";
            PreparedStatement transportStmt = conn.prepareStatement(transportQuery);
            transportStmt.setString(1, a);
            transportStmt.setString(2, b);
            ResultSet transportResult = transportStmt.executeQuery();
            // Compter le nombre de stations traversées pour chaque moyen de transport
            int numStops = 0;
            String prevStationName = startStationName;
            while (transportResult.next()) {
                String currentStationName = transportResult.getString("nom_station");
                if (!currentStationName.equals(prevStationName)) {
                    numStops++;
                    prevStationName = currentStationName;
                }
            }
            // Calculer le nombre total de stations traversées sur le trajet
            int totalStops = numStops + 1;  // +1 pour le premier arrêt
            // Calculer le prix en fonction du nombre de stations traversées
            if (totalStops <= 3) {
                price = 15000.0;
            } else {
                price = 15000.0 * Math.pow(1.05, totalStops - 3);
            } 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return price;
    } 
    public static void main(String[] args){
        String url = "jdbc:mysql://localhost:3306/swiftride";
        String user = "root";
        String password = "";
        TransportFareCalculator calculator = new TransportFareCalculator(url, user, password);
        double fare = calculator.calculateFare("laouina","tunis");
        System.out.println("Fare: " + fare);
    }
}