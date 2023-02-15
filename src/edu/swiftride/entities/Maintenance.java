/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.entities;

import java.sql.Date;

/**
 *
 * @author dhibi
 */
public class Maintenance {
    
    
    private int id;
    private Date date_maintenance;
    private String type ;
    private int id_voiture;
    private int id_garage;

    public Maintenance(int id, Date date_maintenance, String type, int id_voiture, int id_garage) {
        this.id = id;
        this.date_maintenance = date_maintenance;
        this.type = type;
        this.id_voiture = id_voiture;
        this.id_garage = id_garage;
    }

   

    public Maintenance() {
    }

    public int getId() {
        return id;
    }

    public Date getDate_maintenance() {
        return date_maintenance;
    }

    public String getType() {
        return type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate_maintenance(Date date_maintenance) {
        this.date_maintenance = date_maintenance;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId_voiture() {
        return id_voiture;
    }

    public int getId_garage() {
        return id_garage;
    }

    public void setId_voiture(int id_voiture) {
        this.id_voiture = id_voiture;
    }

    public void setId_garage(int id_garage) {
        this.id_garage = id_garage;
    }

    @Override
    public String toString() {
        return "Maintenance{" + "id=" + id + ", date_maintenance=" + date_maintenance + ", type=" + type + ", id_voiture=" + id_voiture + ", id_garage=" + id_garage + '}';
    }

    
    
    
    
}
