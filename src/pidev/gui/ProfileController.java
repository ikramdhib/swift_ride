/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.text.pdf.codec.PngImage;
import com.jfoenix.controls.JFXButton;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import pidev.entities.User;
import pidev.services.UserCRUD;
import static pidev.utils.SendQrCodeViaEmail.sendQrCode;
import pidev.utils.UserSession;

/**
 * FXML Controller class
 *
 * @author skann
 */
public class ProfileController implements Initializable {

    @FXML
    private Circle ivphoto_personnel;
    @FXML
    private ImageView ivphoto_permis;
    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private Label email;
    @FXML
    private Label num_tel;
    @FXML
    private Label num_permis;
    @FXML
    private Label Ville;
    @FXML
    private Label cin;
    @FXML
    private Button btnmodifierprofile;
    @FXML
    private Button btnlogout;
    @FXML
    private Button btnconsulterliste;
    UserCRUD uc = new UserCRUD();
    @FXML
    private Label tfage;
    @FXML
    private Label tfphotopermis;
    @FXML
    private Label tfphotoperssonel;
  
    QRCodeWriter qrCodeWriter = new QRCodeWriter();
    @FXML
    private ImageView QRcodeView;
    @FXML
    private Label num_tellabel;
    @FXML
    private Label num_permislabel;
    @FXML
    private Label villelabel;
    @FXML
    private Label cinlabel;
    @FXML
    private Label agelabel;
    @FXML
    private JFXButton bngenerate;
    @FXML
    private Label notifqrcode;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        notifqrcode.setVisible(false);
        QRcodeView.setVisible(false);
         User user = uc.getUserByEmail(UserSession.getEmail());
        
        if ((uc.getUserByEmail(UserSession.getEmail()).getIdrole() == 2)) {
            btnconsulterliste.setVisible(false);
        }
          if ((uc.getUserByEmail(UserSession.getEmail()).getIdrole() == 1)) {
            ivphoto_personnel.setVisible(false);
             ivphoto_permis.setVisible(false);
             tfphotoperssonel.setVisible(false);
             tfphotopermis.setVisible(false);
             num_tellabel.setVisible(false);
             num_permislabel.setVisible(false);
             cinlabel.setVisible(false);
             agelabel.setVisible(false);
             tfage.setVisible(false);
             cin.setVisible(false);
             num_permis.setVisible(false);
             num_tel.setVisible(false);
             QRcodeView.setVisible(false);
             bngenerate.setVisible(false);

        }

        InputStream stream = null;
        InputStream stream1 = null;
        try {
           
            nom.setText(user.getNom());
            prenom.setText(user.getPrenom());
            email.setText(user.getEmail());
            num_tel.setText(user.getNum_tel());
            num_permis.setText(user.getNum_permis());
            Ville.setText(user.getVille());
            cin.setText(user.getCin());
            tfage.setText(user.getAge());
            stream = new FileInputStream(user.getPhoto_personel());
            stream1 = new FileInputStream(user.getPhoto_permis());
            Image photopermis = new Image(stream1);
            Image photopersonnel = new Image(stream);
           // ivphoto_personnel.setImage(photopersonnel);
           ivphoto_personnel.setFill(new ImagePattern(photopersonnel));
            ivphoto_permis.setImage(photopermis);
        } catch (FileNotFoundException ex) {

            System.out.println(ex.getMessage());
        }

    }

    public Stage profileWindow() {
        Stage stage = new Stage();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Profile.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("Profile");
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
    private void updateScreen(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateUser.fxml"));
            Parent root = loader.load();
            UpdateUserController uuc = loader.getController();
            Stage stage = (Stage) btnmodifierprofile.getScene().getWindow();
            stage.close();
            uuc.getUpdateWindowStage();
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
    @FXML
    private void generateQRcode(){
        User user = uc.getUserByEmail(UserSession.getEmail());
      //  QRcodeView.setVisible(true);
               try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            String Information = "nom : "+user.getNom()+"\n"+"prenom : "+user.getPrenom()+"\n"+"cin : "+user.getCin()+"\n"+"email : "+user.getEmail()+"\n"
                    +"num_tel : "+user.getNum_tel()+"\n"+"num_permis : "+user.getNum_permis()+"\n"+"Ville : "+user.getVille()+"\n";
            int width = 300;
            int height = 300;
            
            BufferedImage bufferedImage = null; 
            BitMatrix byteMatrix = qrCodeWriter.encode(Information, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
         //   bufferedImage.createGraphics();
            
         /*   Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);
            
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            */
           for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                bufferedImage.setRGB(x, y, byteMatrix.get(x, y) ? 0x000000 : 0xFFFFFF);
            }
        }
            System.out.println("Success...");
            
            
            

            
            QRcodeView.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
             
                try {
            sendQrCode(uc.getUserByEmail(UserSession.getEmail()).getNom()+""+uc.getUserByEmail(UserSession.getEmail()).getPrenom(),UserSession.getEmail(),bufferedImage);
            notifqrcode.setVisible(true);
        } catch (MessagingException ex) {
            System.out.println(ex.getMessage());
        }
 

            // TODO
        } catch (WriterException ex) {
                   System.out.println(ex.getMessage());        } 
    }

}
