/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.Services;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author dhibi
 */
public class MailMaintenance {
    
    
    private static String email;
    private static Timestamp date;
     private static Timestamp datef;
      private static String voiture;
     private  static String marque;
     private  static String model;
      private static String matricule;
      private static String nomEn;
     public  static void sendVerificationCode()  {
      try{
        
            String to = email;
           
            String subject = "Entretient de Voiture";
            String message = "bonjour  " ;
            
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
            msg.setSentDate(new Date());
            msg.setText(message);
            msg.setContent("<html>\n" +
"    <body style=\"background-color: #e1d2b8;\">\n" +
"  <div style=\"text-align: center\">"+
"        <table>\n" +
"        <tr><td></td>\n" +
"        <td><h4 style=\"color:#2e006c\">Bonjour, </h4></td></tr>\n" +
"        </table>\n" +
"        <p style=\"color:#2e006c\"> Cher "+nomEn+",</p>\n" +
"        <p style=\"color:#2e006c\">Nous sommes ravis de vous informer que la date et l'heure de l'entretien pour votre véhicule de matricule "
                    +matricule+" , de marque : "+marque+"/"+model+" ont été confirmées. \n"
        + "la date de l'entretien: </p>"
        +"<p style=\"font-weight: bold\",\"color:#2e006c\">"+date+"</p>\n"
                + "<p style=\"color:#2e006c\">Et vous pouver recuperer votre voiture le :</p>"
                + " <p style=\"font-weight: bold\">"+datef+"</p>\n"
                        + "<p> Nous vous recommandons d'arriver quelques minutes en avance pour que notre équipe puisse effectuer les formalités nécessaires et vous remettre les clés de votre véhicule sans délai. </p>\n"
                        + "<p> N'oubliez pas de vous munir de votre permis de conduire en cours de validité ainsi que de tout autre document requis pour la location de véhicule.</p>\n"
                        + "<p>Si vous avez des questions ou des préoccupations, n'hésitez pas à nous contacter au [numéro de téléphone] ou par email à : </p>"
                        + " <p style=\"font-weight: bold\">swiftride2023@gmail.com</p>. Nous serons heureux de vous aider"
                    +"</p>\n" +
"        <p style=\"color:#2e006c\">Nous vous remercions de votre confiance et sommes impatients de vous servir.</p>\n" +
                    "    <p style=\"color:#2e006c\">Cordialement,</p>\n" +
          "</div>"+
"    </body>\n" +
"</html>","text/html");
            Transport.send(msg);
        } catch (MessagingException ex) {
                System.out.println(ex.getMessage());
        }

    }


    public static String getEmail() {
        return email;
    }

    public static void setEmail(String a) {
        email = a;
    }

    public static Timestamp getDate() {
        return date;
    }

    public static Timestamp getDatef() {
        return datef;
    }


    public static String getVoiture() {
        return voiture;
    }

    public static String getMarque() {
        return marque;
    }

    public static String getMatricule() {
        return matricule;
    }

    public static String getNomEn() {
        return nomEn;
    }

    public static void setDate(Timestamp date) {
        MailMaintenance.date = date;
    }

    public static void setDatef(Timestamp datef) {
        MailMaintenance.datef = datef;
    }

    

    public static void setVoiture(String voiture) {
        MailMaintenance.voiture = voiture;
    }

    public static void setMarque(String marque) {
        MailMaintenance.marque = marque;
    }

    public static void setMatricule(String matricule) {
        MailMaintenance.matricule = matricule;
    }

    public static void setNomEn(String nomEn) {
        MailMaintenance.nomEn = nomEn;
    }


    
    
    
}
