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
public class reservation {
    
      private int id;
        private String point_depart ;
        private String destination ;
        private int id_client ;
        private int id_vehicule ;
         private String temps_depart ;
         private float distance ;
         private String type_transport ;
         private float prix ;

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public String getType_transport() {
        return type_transport;
    }

    public void setType_transport(String type_transport) {
        this.type_transport = type_transport;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }
         

    public reservation(int id, String point_depart, String destination , int id_client , int id_vehicule , String temps_depart , float distance , String type_transport , float prix) {
        this.id = id;
        this.point_depart = point_depart;
        this.destination = destination;
        this.id_client=id_client ; 
        this.id_vehicule=id_vehicule ; 
        this.temps_depart=temps_depart ;
         this.distance=distance ;
          this.type_transport=type_transport ;
           this.prix=prix ;
    }

    public reservation() {
    }

    public int getId() {
        return id;
    }

    public String getPoint_depart() {
        return point_depart;
    }
    

    public String getDestination() {
        return destination;
    }

    public int getId_client() {
        return id_client;
    }

    public int getId_vehicule() {
        return id_vehicule;
    }

    public String getTemps_depart() {
        return temps_depart;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPoint_depart(String point_depart) {
        this.point_depart = point_depart;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public void setId_vehicule(int id_vehicule) {
        this.id_vehicule = id_vehicule;
    }

    public void setTemps_depart(String temps_depart) {
        this.temps_depart = temps_depart;
    }

   
        @Override
    public String toString() {
        return "reservation{" + "point_depart=" + point_depart + ", destination=" + destination + ", id_client=" + id_client +", id_vehicule=" + id_vehicule +", temps_depart=" + temps_depart +", distance=" + distance+", type_transport=" + type_transport+", prix=" + prix + '}';
    }

    public void setid_client(String nString) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setid_vehicule(String nString) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
