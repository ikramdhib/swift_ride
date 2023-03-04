/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.services;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author sami
 */
public class Email {

    private static String mot_de_passe;
    private static String email;
    private static String nom_admin;
    private static String prenom_admin;
    private static String nb_voiture;
    private static int totalCars;

    public static void sendEmail() throws AddressException, MessagingException {

        // send verification code via email
        String to = email;
        String subject = "Bienvenue à Swift Ride !";
        String messageContent = "<html><body>Bonjour " + nom_admin + " " + prenom_admin + ",<br><br>"
                + "Merci de votre partenariat de location de voitures! Nous sommes ravis de travailler avec vous,<br><br>"
                + "Grâce à vos <b>" + nb_voiture + "</b> voitures, nous avons maintenant un impressionnant total de <b>"
                + totalCars + "</b> voitures disponibles pour nos clients.<br><br>"
                + "Nous sommes impatients de continuer à renforcer notre partenariat et d'offrir des services de location de voitures exceptionnels à nos clients.<br><br>"
                + "Merci encore!</body></html>";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String from = "swiftride2023@gmail.com";
        String password = "mkokjmqjrihgumfy";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(from));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        msg.setSubject(subject);
        msg.setContent(messageContent, "text/html; charset=utf-8");
        Transport.send(msg);

    }

    public static void setMot_de_passe(String m) {
        mot_de_passe = m;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String a) {
        email = a;
    }

    public static void setNom_admin(String nom_admin) {
        Email.nom_admin = nom_admin;
    }

    public static void setPrenom_admin(String prenom_admin) {
        Email.prenom_admin = prenom_admin;
    }

    public static int getTotalCars() {
        return totalCars;
    }

    public static void setTotalCars(int totalCars) {
        Email.totalCars = totalCars;
    }

    public static void setNb_voiture(String nb_voiture) {
        Email.nb_voiture = nb_voiture;
    }

}
