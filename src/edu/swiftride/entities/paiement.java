/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.entities;

/**
 *
 * @author admin
 */
public class paiement {
      private int id;
    private int num_carte;
    private String date;
    private int code;
    private int id_reservation;

    public paiement(int id, int num_carte, String date, int code, int id_reservation) {
        this.id = id;
        this.num_carte = num_carte;
        this.date = date;
        this.code = code;
        this.id_reservation = id_reservation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum_carte() {
        return num_carte;
    }

    public void setNum_carte(int num_carte) {
        this.num_carte = num_carte;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }
}
