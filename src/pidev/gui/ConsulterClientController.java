/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import pidev.entities.User;
import pidev.services.UserCRUD;
import pidev.utils.UserSession;

/**
 * FXML Controller class
 *
 * @author skann
 */
public class ConsulterClientController implements Initializable {

    
    UserCRUD uc = new UserCRUD();
    @FXML
    private static Circle ivphoto_personnel;
    @FXML
    private static ImageView ivphoto_permis;
    @FXML
    private Button btnconsulterliste;
    @FXML
    private Button btnlogout;
    @FXML
    public  Label tfnom;
private User currentSelectedUser=new User();
   
    @FXML
    private   Label tfprenom;
    @FXML
    private  Label tfemail;
    @FXML
    private  Label tfnum_tel;
    @FXML
    private  Label tfnum_permis;
    @FXML
    private  Label tfville;
    @FXML
    private  Label tfcin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }
    public void loadPage(){
         InputStream stream = null;
        InputStream stream1 = null; 
        System.out.println(getCurrentSelectedUser().getEmail());  
        try {
            User u = uc.getUserByEmail(getCurrentSelectedUser().getEmail());
            System.out.println(u.getNom());  
            tfnom.setText(u.getNom());
            tfprenom.setText(u.getPrenom());
            tfemail.setText(u.getEmail());
            tfnum_tel.setText(u.getNum_tel());
            tfnum_permis.setText(u.getNum_permis());
            tfville.setText(u.getVille());
            tfcin.setText(u.getCin());
            stream = new FileInputStream(u.getPhoto_personel());
            stream1 = new FileInputStream(u.getPhoto_permis());
            Image photopersonnel = new Image(stream);
            Image photopermis = new Image(stream1);
              ivphoto_personnel.setFill(new ImagePattern(photopersonnel));
            ivphoto_permis.setImage(photopermis);
        } catch (FileNotFoundException ex) {

            System.out.println(ex.getMessage());
        }

    }
    /*public void setNom(String text){
        this.tfnom.setText(text);
    }*/



    public Stage clientProfile() {
        Stage stage = new Stage();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ConsulterClient.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("Consulter utilisateur");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return stage;
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

    @FXML
    private void consulterliste(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UserList.fxml"));
            Parent root = loader.load();
            UserListController ulc = loader.getController();
            Stage stage = (Stage) btnconsulterliste.getScene().getWindow();
            stage.close();
            ulc.userListWindow();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
public User getCurrentSelectedUser(){
    return currentSelectedUser;
}
    public void setCurrentSelectedUser(User user) {
        this.currentSelectedUser = user;
    }

}
