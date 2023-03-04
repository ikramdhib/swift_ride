/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import javafx.util.Duration;
import static edu.swiftride.gui.SignupController.showAlert;
import edu.swiftride.services.UserCRUD;
import javax.mail.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import edu.swiftride.utils.ForgetPassword;

/**
 * FXML Controller class
 *
 * @author skann
 */
public class ForgetPasswordController implements Initializable {

    @FXML
    private TextField tfemail;
    @FXML
    private TextField tfcode;
    @FXML
    private PasswordField pfpassword;
    @FXML
    private Button bnsendcode;
    @FXML
    private Button bnvalider;
    UserCRUD uc = new UserCRUD();
    private String verificationCode;
    @FXML
    private Button bnconnecter;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
public void disablebutton(){
     bnvalider.setDisable(true);
}
private void enablebutton(){
    bnvalider.setDisable(false);
}
    @FXML
    private void sendCode() {
        Window owner = bnsendcode.getScene().getWindow();
        if (tfemail.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Echec!", "Champs email est vide !");
        } else if (!(uc.emaildejaUtilise(tfemail.getText()))&&!(uc.emailEntreprisedejautilisé(tfemail.getText()))) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Echec!", "Email introuvable !");
        }else {
            try {
                enablebutton();
                ForgetPassword.setEmail(tfemail.getText());
                ForgetPassword.sendVerificationCode();
                
                bnsendcode.setDisable(true);
                PauseTransition pause = new PauseTransition(Duration.seconds(60));               
                pause.play();                     
                pause.setOnFinished(e -> {
                    bnsendcode.setDisable(false);
                });
                
            } catch (MessagingException ex) {
                System.out.println(ex.getMessage());
            }

        }    
    }

    @FXML
    private void valider() {
        Window owner = bnvalider.getScene().getWindow();
        
        if (!(tfcode.getText().equals(ForgetPassword.getVerificationCode()))) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Echec!", "Code incorrect !");
        } else if (pfpassword.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Echec!", "Champs mot de passe est vide !");
        } else if ((pfpassword.getText().length() < 6)) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Echec!", "Le mot de passe est composé de 6 chiffres au minimum !");
        } else {
            uc.updatePassword(pfpassword.getText());
            returnToLoginPage();
        }

    }
    @FXML private void loginPage(){
        returnToLoginPage();
    }

    /* @FXML
    private void valider(ActionEvent event){
        if(tfcode.)
    }*/
   

    private void returnToLoginPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Signin.fxml"));
            Parent root = loader.load();
            SigninController sc = loader.getController();
            Stage stage = (Stage) bnvalider.getScene().getWindow();
            stage.close();
            sc.getConnectStage();
        } catch (IOException ex) {
            Logger.getLogger(ForgetPasswordController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Stage forgetPasswordWindow(){
                Stage stage = new Stage();

          try {
            Parent root = FXMLLoader.load(getClass().getResource("ForgetPassword.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("Mot de passe oublié");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return stage;
    }
}
