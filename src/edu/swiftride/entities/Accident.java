/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.entities;
    import java.sql.Date;

/**
 *
 * @author user
 */
public class Accident {

/**
 *
 * @author user
 */

   
    private int id;
    private String type;
    private Date dateac;
    private String description ;
    private String lieu ;
    private String id_voiture;

    public Accident() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDateac() {
        return dateac;
    }

    public void setDateac(Date dateac) {
        this.dateac = dateac;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getId_voiture() {
        return id_voiture;
    }

    public void setId_voiture(String id_voiture) {
        this.id_voiture = id_voiture;
    }

    public Accident(int id, String type, Date dateac, String description, String lieu, String id_voiture) {
        this.id = id;
        this.type = type;
        this.dateac = dateac;
        this.description = description;
        this.lieu = lieu;
        this.id_voiture = id_voiture;
    }
}
