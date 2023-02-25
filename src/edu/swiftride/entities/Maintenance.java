/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.entities;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

/**
 *
 * @author dhibi
 */
public class Maintenance {
    
    
    private int id;
    private Timestamp date_maintenance;
    private String type ;
    private int id_voiture;
    private int id_garage;
    private Timestamp fin_maintenance;

   

   

    public Maintenance() {
    }

    public int getId() {
        return id;
    }


    public String getType() {
        return type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Maintenance(int id, Timestamp date_maintenance, String type, int id_voiture, int id_garage, Timestamp fin_maintenance) {
        this.id = id;
        this.date_maintenance = date_maintenance;
        this.type = type;
        this.id_voiture = id_voiture;
        this.id_garage = id_garage;
        this.fin_maintenance = fin_maintenance;
    }

    public Timestamp getDate_maintenance() {
        return date_maintenance;
    }

    public Timestamp getFin_maintenance() {
        return fin_maintenance;
    }

    public void setDate_maintenance(Timestamp date_maintenance) {
        this.date_maintenance = date_maintenance;
    }

    public void setFin_maintenance(Timestamp fin_maintenance) {
        this.fin_maintenance = fin_maintenance;
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
        return "Maintenance{" + "id=" + id + ", date_maintenance=" + date_maintenance + ", type=" + type + ", id_voiture=" + id_voiture + ", id_garage=" + id_garage + ", fin_maintenance=" + fin_maintenance + '}';
    }


    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Maintenance other = (Maintenance) obj;
        if (this.id_voiture != other.id_voiture && this.date_maintenance!=other.date_maintenance ) {
            return false;
        }
        return true;
    }

    
    
    
    
}
