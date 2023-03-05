/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.entities;

import java.sql.Timestamp;

/**
 *
 * @author dhibi
 */
public class DetailMaintenanceVoitures {
    
    private int id ;
    private String type;
    private String voiture;
    private Timestamp date_maintenance;
    private Timestamp fin_maintenance;

    public DetailMaintenanceVoitures(int id, String type, String voiture, Timestamp date_maintenance, Timestamp fin_maintenance) {
        this.id = id;
        this.type = type;
        this.voiture = voiture;
        this.date_maintenance = date_maintenance;
        this.fin_maintenance = fin_maintenance;
    }

    public DetailMaintenanceVoitures() {
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getVoiture() {
        return voiture;
    }

    public Timestamp getDate_maintenance() {
        return date_maintenance;
    }

    public Timestamp getFin_maintenance() {
        return fin_maintenance;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setVoiture(String voiture) {
        this.voiture = voiture;
    }

    public void setDate_maintenance(Timestamp date_maintenance) {
        this.date_maintenance = date_maintenance;
    }

    public void setFin_maintenance(Timestamp fin_maintenance) {
        this.fin_maintenance = fin_maintenance;
    }

    @Override
    public String toString() {
        return "DetailMaintenanceVoitures{" + "id=" + id + ", type=" + type + ", voiture=" + voiture + ", date_maintenance=" + date_maintenance + ", fin_maintenance=" + fin_maintenance + '}';
    }
    
    
    
    
    
    
}
