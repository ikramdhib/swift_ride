/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swifrdide.gui;

import edu.swiftride.entities.Avis;
import edu.swiftride.entities.EntreprisePartenaire;
import edu.swiftride.services.AvisCRUD;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.xml.bind.DatatypeConverter;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author sami
 */
public class AvisController implements Initializable {

    @FXML
    private TextArea txtCommentiare;

    @FXML
    private Rating rating;

    @FXML
    private Rating rating2;

    @FXML
    private Label msg;

    @FXML
    private Label msg2;

    @FXML
    private TableView<Avis> table;

    @FXML
    private TableColumn<Avis, Integer> idCol;

    @FXML
    private TableColumn<Avis, String> commentaireCol;

    @FXML
    private TableColumn<Avis, Integer> etoileCol;

    @FXML
    private Button btnAjouter;

    @FXML
    private Button btnModifier;

    @FXML
    private Button btnSupprimer;

    @FXML
    private Button btnSubmit;

    AvisCRUD pcm = new AvisCRUD();

    @FXML
    void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnAjouter) {
            ajouterAvis();
        } else if (event.getSource() == btnModifier) {
            modifierAvis();
        } else if (event.getSource() == btnSupprimer) {
            supprimerAvis();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showAvis();
        ratingAvis();
    }

    public Connection getConnection() {
        Connection cnn;
        try {
            cnn = DriverManager.getConnection("jdbc:mysql://localhost:3306/swiftride", "root", "");
            return cnn;
        } catch (Exception ex) {
            System.out.println("Error" + ex.getMessage());
            return null;
        }
    }
    private int ratingValue = 0;

    public void ratingAvis() {
        rating.ratingProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number old, Number newT) {
                ratingValue = newT.intValue();
                msg.setText("Votre avis : " + newT + " etoiles");

                // Appel de la méthode pour calculer la moyenne des notes
                double moyenne = calculerMoyenne();

                msg2.setText("Moyenne d'avis , " + getAvis().size() + " utilisateurs");

                // Display the average rating in a non-editable rating control
                // Mettre à jour le rating2 avec la valeur de la moyenne
                rating2.setRating(moyenne);
            }

            public double calculerMoyenne() {
                int sum = 0;
                for (Avis avis : getAvis()) {
                    sum += avis.getEtoile();
                }
                double moyenne = (double) sum / getAvis().size();
                return moyenne;
            }
        });

    }

    public void showAvis() {
        ObservableList<Avis> list = getAvis();
        ;
        idCol.setCellValueFactory(new PropertyValueFactory<Avis, Integer>("id"));
        commentaireCol.setCellValueFactory(new PropertyValueFactory<Avis, String>("Commentaire"));
        etoileCol.setCellValueFactory(new PropertyValueFactory<Avis, Integer>("etoile"));

        table.setItems(list);

    }

    private ObservableList<Avis> getAvis() {
        ObservableList<Avis> listM = FXCollections.observableArrayList();
        Connection cnn = getConnection();
        String query = "SELECT * FROM avis";
        Statement st;
        ResultSet rs;
        try {
            st = cnn.createStatement();
            rs = st.executeQuery(query);
            Avis m;
            while (rs.next()) {
                m = new Avis();
                m.setId(rs.getInt(1));
                m.setCommentaire(rs.getNString(2));
                m.setEtoile(rs.getInt(3));
                listM.add(m);
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return listM;
    }

    public void ajouterAvis() {
        Avis m = new Avis();
        String commentaire = txtCommentiare.getText();
        int etoile = (int) rating.getRating();

        // Check that required fields are not empty
        if (commentaire.isEmpty() || etoile <= 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Missing fields");
            alert.setHeaderText("All fields must be filled");
            alert.showAndWait();
            return;
        }
        // Check for bad words
        if (commentaire.toLowerCase().contains("stupid") || commentaire.toLowerCase().contains("crazy") || commentaire.toLowerCase().contains("dumb")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("GROS MOTS !!");
            alert.setHeaderText("Hey ! Pas de gros mots s'il vous plaît !");
            alert.showAndWait();
            return;
        }
        // Set values for the Avis object
        m.setCommentaire(commentaire);
        m.setEtoile(etoile);

        // Add the enterprise object to the list and update the table view
        pcm.ajouterAvis(m);
        table.setItems(getAvis());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ajout réussie");

        // Add a funny comment based on the rating
        String message;
        switch (etoile) {
            case 1:
                message = "Avez-vous eu une mauvaise journée ?";
                break;
            case 2:
                message = "Le plafond est à cinq, vous savez ?";
                break;
            case 3:
                message = "Merci mais vous êtes sûr que c'est la bonne note ?";
                break;
            case 4:
                message = "Excellent, vous êtes presque parfait !";
                break;
            case 5:
                message = "wow 5 étoiles ! Vous êtes incroyable !";
                break;
            default:
                message = "Merci pour votre commentaire !";
                break;
        }
        alert.setHeaderText(message);
        alert.setContentText("L'ajout a été effectuée avec succès");
        alert.showAndWait();

    }

    private void modifierAvis() {
        Avis m = new Avis();
        Avis av = table.getSelectionModel().getSelectedItem();

        if (av == null) {
            // Créer une alerte de type WARNING pour demander à l'utilisateur de choisir un avis à modifier
            Alert warning = new Alert(Alert.AlertType.WARNING);
            warning.setTitle("Attention");
            warning.setHeaderText("Vous devez sélectionner un avis à modifier.");
            warning.showAndWait();
            return;
        }
        m.setId(table.getSelectionModel().getSelectedItem().getId());

        String commentaire = txtCommentiare.getText();
        int etoile = (int) rating.getRating();

        // Check that required fields are not empty
        if (commentaire.isEmpty() || etoile <= 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Missing fields");
            alert.setHeaderText("All fields must be filled");
            alert.showAndWait();
            return;
        }

        // Check for forbidden words
        if (commentaire.toLowerCase().contains("stupid") || commentaire.toLowerCase().contains("crazy")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("GROS MOTS !!");
            alert.setHeaderText("Hey ! Pas de gros mots s'il vous plaît");
            alert.showAndWait();
            return;
        }

        // Set values for the Avis object
        m.setCommentaire(commentaire);
        m.setEtoile(etoile);

        // Effectuer la modification
        pcm.modifierAvis(m);
        table.setItems(getAvis());

        // Add comment based on the rating
        String message;
        switch (etoile) {
            case 1:
                message = "Avez-vous eu une mauvaise journée ?";
                break;
            case 2:
                message = "Le plafond est à cinq, vous savez ?";
                break;
            case 3:
                message = "Merci mais vous êtes sûr que c'est la bonne note ?";
                break;
            case 4:
                message = "Excellent, vous êtes presque parfait !";
                break;
            case 5:
                message = "wow 5 étoiles ! Vous êtes incroyable !";
                break;
            default:
                message = "Merci pour votre commentaire !";
                break;
        }
        // Afficher une alerte de confirmation si la modification a réussi
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(message);
        alert.setContentText("Modification a été effectuée avec succès");
        alert.showAndWait();
    }

    private void supprimerAvis() {
        Avis m = new Avis();
        Avis av = table.getSelectionModel().getSelectedItem();

        if (av == null) {
            // Créer une alerte de type WARNING pour demander à l'utilisateur de choisir un avis à modifier
            Alert warning = new Alert(Alert.AlertType.WARNING);
            warning.setTitle("Attention");
            warning.setHeaderText("Vous devez sélectionner un avis à modifier.");
            warning.showAndWait();
            return;
        }

// Créer une alerte de type CONFIRMATION pour demander à l'utilisateur s'il veut vraiment supprimer l'entreprise
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmation de suppression");
        confirmation.setHeaderText("Êtes-vous sûr de vouloir supprimer l'avis " + av.getId() + " ?");
        Optional<ButtonType> result = confirmation.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            pcm.supprimer(av);
            table.getItems().removeAll(av);

            // Créer une alerte de type INFORMATION pour informer l'utilisateur que la suppression a réussi
            Alert success = new Alert(Alert.AlertType.INFORMATION);
            success.setTitle("Suppression réussie");
            success.setHeaderText("L'entreprise a été supprimée avec succès.");
            success.showAndWait();
        }
    }
}
