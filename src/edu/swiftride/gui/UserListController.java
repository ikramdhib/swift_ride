/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.stage.Window;

import edu.swiftride.entity.User;
import static edu.swiftride.gui.SignupController.showAlert;
import static edu.swiftride.gui.UpdateUserController.showVerification;
import edu.swiftride.services.UserCRUD;
import edu.swiftride.utils.UserSession;
import java.time.format.DateTimeFormatter;

/**
 * FXML Controller class
 *
 * @author skann
 */
public class UserListController implements Initializable {

    @FXML
    private TableView<User> tvliste;
    @FXML
    private TableColumn<User, Integer> ID;
    @FXML
    private TableColumn<User, String> Nom;
    @FXML
    private TableColumn<User, String> Prenom;
    @FXML
    private TableColumn<User, String> Email;
    @FXML
    private TableColumn<User, String> Cin;
    @FXML
    private TableColumn<User, String> date_naiss;
     @FXML
    private TableColumn<User, String> age;
    @FXML
    private TableColumn<User, String> num_permis;
    @FXML
    private TableColumn<User, String> num_tel;
    @FXML
    private TableColumn<User, String> ville;
    @FXML
    private Button btnprofile;
    @FXML
    private Button bndelete;
    @FXML
    private Button btnlogout;
    private User currentSelectedUser;
    private Button bnconsulter;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userList();
       // bnconsulter.setVisible(false);
        /*  if ((tvliste.getSelectionModel().getSelectedItems().isEmpty())) 
            bndelete.setDisable(true);
         */
    }

    private void userList() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        UserCRUD uc = new UserCRUD();
        ObservableList<User> users = FXCollections.observableArrayList(uc.consulterListe());
        tvliste.setItems(users);
        ID.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        Nom.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()));
        Prenom.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPrenom()));
        Email.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        Cin.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCin()));
        date_naiss.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDate_naiss().toString()));
        age.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAge()));
        num_permis.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNum_permis()));
        num_tel.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNum_tel()));
        ville.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVille()));

    }

    public Stage userListWindow() {
        Stage stage = new Stage();
        try {

            Parent root = FXMLLoader.load(getClass().getResource("UserList.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("Liste des utilisateurs");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return stage;
    }

    @FXML
    private void profileWindow(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Profile.fxml"));

            Parent root = loader.load();
            ProfileController pc = loader.getController();
            pc.profileWindow();
            Stage stage = (Stage) btnprofile.getScene().getWindow();
            stage.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void deleteRow(ActionEvent event) {
        Window owner = bndelete.getScene().getWindow();

        if ((tvliste.getSelectionModel().getSelectedItems().isEmpty())) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Echec!", "Séléctionne un utilisateur pour le supprimer!");
        } else if ((showVerification("Supprimer", "Supprimer cet utilisateur ?"))) {
            UserCRUD uc = new UserCRUD();
            uc.supprimerUtilisateur(tvliste.getSelectionModel().getSelectedItem());
            tvliste.getItems().removeAll(tvliste.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    private void Déconnecter(ActionEvent event) {
        try {
            UserSession.cleanUserSession();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Signin.fxml"));
            Parent root = loader.load();
            SigninController sc = loader.getController();
            Stage stage = (Stage) btnlogout.getScene().getWindow();
            stage.close();
            sc.getConnectStage();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    private void consulterClient(ActionEvent event) {
        Window owner = bnconsulter.getScene().getWindow();

        if ((tvliste.getSelectionModel().getSelectedItems().isEmpty())) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Echec!", "Séléctionne un utilisateur pour le consulter!");
        } else {

            try {
                System.out.println(tvliste.getSelectionModel().getSelectedItem().getEmail());
            
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ConsulterClient.fxml"));
                Parent root = loader.load();
                ConsulterClientController c = loader.getController();
                c.setCurrentSelectedUser(tvliste.getSelectionModel().getSelectedItem());
               // c.setNom("zeadaz");
                c.loadPage();
                c.clientProfile();
                Stage stage = (Stage) bnconsulter.getScene().getWindow();
                stage.close();

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

}
