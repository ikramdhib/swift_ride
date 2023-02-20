/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.entities;

/**
 *
 * @author Ines
 */
public class MoyenTransport {
    
private int id; 
private String type;
private int numero_trans;
private int nb_station;
private int id_admin;


public MoyenTransport(){

}
public MoyenTransport(int id, String type, int numero_trans,int nb_station, int id_admin) {
        this.id = id;
        this.type= type;
        this.numero_trans= numero_trans;
        this.nb_station=nb_station;
        this.id_admin=id_admin;
}

   
    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public int getNumero_trans() {
        return numero_trans;
    }

    public int getNb_station() {
        return nb_station;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setNumero_trans(int numero_trans) {
        this.numero_trans = numero_trans;
    }

    public void setNb_station(int nb_station) {
        this.nb_station = nb_station;
    }

    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    @Override
    public String toString() {
        return "MoyenTransport{" + "id=" + id + ", type=" + type + ", numero_trans=" + numero_trans + ", nb_station=" + nb_station + ", id_admin=" + id_admin + '}';
    }
    
    



}


