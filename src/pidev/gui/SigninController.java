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
import com.jfoenix.controls.JFXCheckBox;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.Window;
import pidev.entities.User;
import static pidev.gui.SignupController.calculerAge;
import static pidev.gui.SignupController.showAlert;
import pidev.services.UserCRUD;
import pidev.utils.EncryptPassword;
import pidev.utils.UserSession;

/**
 * FXML Controller class
 *
 * @author skann
 */
public class SigninController implements Initializable {

    @FXML
    private TextField tfemailtoconnect;
    @FXML
    private PasswordField pfpasswordtoconnect;
    @FXML
    private Button btnsignin;
    User user = new User();
    UpdateUserController updateusercontroller = new UpdateUserController();
    @FXML
    private Button btndejacompte;
    Stage stage = new Stage();
    @FXML
    private Label lblServer;
    @FXML
    private Hyperlink linkforgetpassword;
    @FXML
    private JFXCheckBox cbshowpass;
    @FXML
    private TextField tfshowedpass;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            String myWeb = "http://java-buddy.blogspot.com/";
            int width = 300;
            int height = 300;
            
            BufferedImage bufferedImage = null; 
            BitMatrix byteMatrix = qrCodeWriter.encode(myWeb, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();
            
            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
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
            
            System.out.println("Success...");
            
            
            
            ImageView qrView = new ImageView();
            
            qrView.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
            // TODO
        } catch (WriterException ex) {
            Logger.getLogger(SigninController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
@FXML
    private void forgetPasswordPage(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ForgetPassword.fxml"));
           Parent root = loader.load();
            ForgetPasswordController f = loader.getController();
           
             f.forgetPasswordWindow();
              f.disablebutton();
            Stage stage = (Stage) linkforgetpassword.getScene().getWindow();
            stage.close();
           

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void Signin(ActionEvent event) {
        try {
            Window owner = btnsignin.getScene().getWindow();

            String encryptedpassword = EncryptPassword.toHexString(EncryptPassword.getSHA(pfpasswordtoconnect.getText()));

            User user = new User();
            user.setEmail(tfemailtoconnect.getText());
            user.setPassword(encryptedpassword);
            UserCRUD uc = new UserCRUD();
            if (tfemailtoconnect.getText().isEmpty() || pfpasswordtoconnect.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "il reste des champs vides");
                return;
            } else if (!(validateEmail(tfemailtoconnect))) {
                showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Vérifier l'email");
                return;
            } else if (uc.authentifier(user)) {
                try {
                    user.setAge(calculerAge(uc.getUserByEmail(user.getEmail()).getDate_naiss()));
                    uc.updateAge(uc.getUserByEmail(user.getEmail()).getId(),user.getAge());
                    System.out.println(pfpasswordtoconnect.getText());
                    UserSession.getInstace(user.getEmail(), pfpasswordtoconnect.getText());
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Profile.fxml"));
                    Parent root = loader.load();
                    ProfileController pc = loader.getController();
                    pc.profileWindow();

                    Stage stage = (Stage) btnsignin.getScene().getWindow();
                    stage.close();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            } else {
                showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Compte introuvable");
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SigninController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean validateEmail(TextField email) {
        Pattern pattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        Matcher matcher = pattern.matcher(email.getText());
        return matcher.matches();
    }

    public Stage getConnectStage() {

        try {

            Parent root = FXMLLoader.load(getClass().getResource("Signin.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("Connecter");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return stage;
    }

    @FXML
    private void dejacompte() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Signup.fxml"));
            Parent root = loader.load();
            SignupController sc = loader.getController();
            sc.signUpWindow();
            Stage stage = (Stage) btndejacompte.getScene().getWindow();
            stage.close();
            sc.signUpWindow();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @FXML   
    private void showPassword(){
        if (cbshowpass.isSelected()){
            tfshowedpass.setText(pfpasswordtoconnect.getText());
            tfshowedpass.setVisible(true);
            pfpasswordtoconnect.setVisible(false);
            return;
        }
          pfpasswordtoconnect.setText(tfshowedpass.getText());
            pfpasswordtoconnect.setVisible(true);
            tfshowedpass.setVisible(false);
    }

}
