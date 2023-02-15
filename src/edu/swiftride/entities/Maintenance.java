/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.entities;

import java.util.Date;

/**
 *
 * @author dhibi
 */
public class Maintenance {
    
    
    private int id;
    private Date date_maintenance;
    private String type ;

    public Maintenance(int id, Date date_maintenance, String type) {
        this.id = id;
        this.date_maintenance = date_maintenance;
        this.type = type;
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

    @Override
    public String toString() {
        return "Maintenance{" + "id=" + id + ", date_maintenance=" + date_maintenance + ", type=" + type + '}';
    }
    
    
    
}
