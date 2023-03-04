/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.gui;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.io.IOException;

import java.net.URL;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import java.util.ResourceBundle;
import java.util.Random;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import edu.swiftride.entity.User;
import edu.swiftride.services.UserCRUD;
import edu.swiftride.utils.EncryptPassword;

/**
 * FXML Controller class
 *
 * @author skann
 */
public class SignupController implements Initializable {

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfemail;
    @FXML
    private PasswordField pfpassword;
    @FXML
    private DatePicker date_naissance;
    @FXML
    private TextField tfcin;
    @FXML
    private TextField tfnum_permi;
    @FXML
    private TextField tfnum_tel;
    @FXML
    private Button btnphoto_personnel;
    @FXML
    private Button btnphoto_permi;
    @FXML
    private Button btnsignup;
    User user = new User();

    Stage stage = new Stage();
    UserCRUD uc = new UserCRUD();
    @FXML
    private Button btndeja_compte;
    @FXML
    private Button bngenerate;
    @FXML
    private PasswordField tfgenpass;
    @FXML
    private TextField tfage;
    List<String> items = Arrays.asList("Ariana", "Beja ", "Ben Arous ", "Bizerte", "Gabes", "Gafsa ", "Jendouba", "Kairouan", "Kasserine", "Kebili", "Kef", "Mahdia", "Manouba", "Medenine", "Monastir", "Nabeul", "Sfax", "Sidi Bou Zid", "Siliana", "Sousse", "Tataouine ", "Tozeur", "Tunis", "Zaghouan");
    @FXML
    private ComboBox<String> cbville;
    @FXML
    private TextField tfshowedpass;
    @FXML
    private TextField passtextfield;
    private ComboBox<String> cbrole;
    List<String> roles = Arrays.asList("CLIENT", "ENTREPRISE");
    public static final String ACCOUNT_SID = "ACce15af1244abdc2ffe799d047b731092";
    public static final String AUTH_TOKEN = "8a8968a11e6993f6b9b94dab865f3931";
    public static final String TWILIO_NUMBER = "+12764966446";
   @FXML
    private Button btnhide;
    @FXML
    private Button btnhide1;
    @FXML
    private Button btnshow1;
    @FXML
    private Button btnshow;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // tfgenpass.setEditable(false);
       // tfshowedpass.setEditable(false);
       // cbrole.setVisible(false);
        //listeRole();
        listeVille();
        //cbrole.getSelectionModel().selectFirst();
        cbville.getSelectionModel().selectFirst();
        tfage.setEditable(false);
        // TODO
        date_naissance.setValue(LocalDate.now().minusYears(18));
        date_naissance.setDayCellFactory(picker -> {
            final LocalDate today = LocalDate.now();
            return new DateCell() {
                @Override
                public void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item.getYear() > today.minusYears(18).getYear() || (item.getYear() == today.minusYears(18).getYear() && item.getDayOfYear() > today.getDayOfYear())) {
                        setDisable(true);
                        setStyle("-fx-background-color: #ffc0cb;");
                    }
                }
            };
        });
        tfage.setText(calculerAge(date_naissance.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))));

        date_naissance.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue != newValue) {
                tfage.setText(calculerAge(newValue.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
            }

        });

    }

    public void listeVille() {

        ObservableList listville = FXCollections.observableArrayList(items);
        cbville.setItems(listville);
    }

   /* public void listeRole() {

        ObservableList listerole = FXCollections.observableArrayList(roles);
        cbrole.setItems(listerole);
    }*/

    @FXML
    private void PhotoPersonnel(ActionEvent event) {

        try {
            uc.uploadPhotoPersonnel(user);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());

        }
    }

    @FXML
    private void PhotoPermis(ActionEvent event) {

        try {
            uc.uploadPhotoPermis(user);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());

        }

    }

    public Stage signUpWindow() {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("Signup.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("Crée un compte");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return stage;
    }

    @FXML
    private void signup(ActionEvent event) {
        Window owner = btnsignup.getScene().getWindow();
        if (tfcin.getText().isEmpty() || tfnom.getText().isEmpty()
                || tfprenom.getText().isEmpty() || tfemail.getText().isEmpty()
                || date_naissance.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")).isEmpty() || tfnum_permi.getText().isEmpty()
                || tfnum_tel.getText().isEmpty()
                || tfemail.getText().isEmpty() || pfpassword.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Echec!", "il reste un ou des champs vides!");
        } else if (!(validateEmail(tfemail))) {
            showAlert(Alert.AlertType.ERROR, owner, "Echec!", "La format de l'email est incorrect!");
        } else if (pfpassword.getText().length() < 6) {
            showAlert(Alert.AlertType.ERROR, owner, "Echec!",
                    "Le mot de passe doit etre composé de six chiffre au minimum");
        } else if (!(tfcin.getText().chars().allMatch(Character::isDigit)) || tfcin.getText().length() != 8) {
            showAlert(Alert.AlertType.ERROR, owner, "Echec!",
                    "Cin doit etre composé seulement de 8 chiffres !");
        } else if (!(tfnum_permi.getText().chars().allMatch(Character::isDigit)) || tfnum_permi.getText().length() != 8) {
            showAlert(Alert.AlertType.ERROR, owner, "Echec!",
                    "Le numéro de permis doit etre composé seulement de 8 chiffres !");
        } else if (!(tfnum_permi.getText().chars().allMatch(Character::isDigit)) || tfnum_permi.getText().length() != 8) {
            showAlert(Alert.AlertType.ERROR, owner, "Echec!",
                    "Le numéro de permis doit etre composé seulement de 8 chiffres !");
        } else if (!(tfnum_tel.getText().chars().allMatch(Character::isDigit)) || tfnum_tel.getText().length() != 8) {
            showAlert(Alert.AlertType.ERROR, owner, "Echec!",
                    "Le numéro de téléphone doit etre composé seulement de 8 chiffres !");

        } else if (!(validateEmail(tfemail))) {
            showAlert(Alert.AlertType.ERROR, owner, "Echec!", "La format d'email est incorrect!");
        } else if (uc.emaildejaUtilise(tfemail.getText()) || (uc.emailEntreprisedejautilisé(tfemail.getText()))) {
            showAlert(Alert.AlertType.ERROR, owner, "Echec!", "Email deja utilisé");
        } else if (uc.cindejaUtilise(tfcin.getText())) {
            showAlert(Alert.AlertType.ERROR, owner, "Echec!", "Cin déja utilisé");
        } else if (uc.num_permidejaUtilise(tfnum_permi.getText())) {
            showAlert(Alert.AlertType.ERROR, owner, "Echec!", "numéro de permis déja utilisé");
        } else if (user.getPhoto_personel().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Echec!", "vous devez enregistrez une photo personnel!");
        } else if (user.getPhoto_permis().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Echec!", "vous devez enregistrez une photo de votre permis!");
        } else if (user.getPhoto_personel().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Echec!", "vous devez enregistrez une photo personnelle!");
        } else {
            try {
                user.setNom(tfnom.getText());
                user.setPrenom(tfprenom.getText());
                user.setEmail(tfemail.getText());
                user.setCin(tfcin.getText());
                user.setDate_naiss(date_naissance.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                user.setAge(calculerAge(date_naissance.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
                user.setNum_permis(tfnum_permi.getText());
                user.setVille(cbville.getSelectionModel().getSelectedItem());
                user.setNum_tel(tfnum_tel.getText());
                user.setIdrole(2);
                user.setEmail(tfemail.getText());
                user.setPassword(EncryptPassword.toHexString(EncryptPassword.getSHA(pfpassword.getText())));
                uc.ajouterUtilisateur(user);
                //sendSMS(tfnom.getText(),tfprenom.getText(),tfnum_tel.getText());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Signin.fxml"));
                Parent root = loader.load();
                SigninController sc = loader.getController();
                Stage stage = (Stage) btndeja_compte.getScene().getWindow();
                stage.close();
                sc.getConnectStage();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                System.out.println("test");
            } catch (NoSuchAlgorithmException ex) {
                System.out.println(ex.getMessage());
            }

        }

    }

    public static boolean validateEmail(TextField email) {
        Pattern pattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        Matcher matcher = pattern.matcher(email.getText());
        return matcher.matches();
    }

    @FXML
    private void dejacompte(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Signin.fxml"));
            Parent root = loader.load();
            SigninController sc = loader.getController();
            Stage stage = (Stage) btndeja_compte.getScene().getWindow();
            stage.close();
            sc.getConnectStage();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    /*public void showAlert1(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }*/
    public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    public static String calculerAge(String date) {
        DateTimeFormatter format
                = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate newdate = LocalDate.parse(date, format);
        int age = LocalDate.now().getYear() - newdate.getYear();
        if (LocalDate.now().getDayOfYear() >= newdate.getDayOfYear()) {
            return Integer.toString(age);
        } else {
            return Integer.toString(age - 1);
        }

    }

    @FXML
    private void generatePassword(ActionEvent event) {
        Window owner = bngenerate.getScene().getWindow();
        Random random = new Random();
        int length = 5;
        if (tfemail.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Echec", "Saisit votre email");
        } else if (uc.emaildejaUtilise(tfemail.getText())) {
            showAlert(Alert.AlertType.ERROR, owner, "Echec", "Email dèja utilisé");
        } else if (!validateEmail(tfemail)) {
            showAlert(Alert.AlertType.ERROR, owner, "Echec", "Email non valide");
        } else {
            int index = tfemail.getText().indexOf("@");
            String substractedemail = tfemail.getText().substring(0, index);
            int min = (int) Math.pow(10, length - 1);
            int max = (int) Math.pow(10, length) - 1;
            int randomNumber = random.nextInt(max - min + 1) + min;
            tfgenpass.setText(substractedemail + randomNumber);
        }
    }

    /*@FXML
    private void showPassword() {
        if (cbshowpass.isSelected()) {
            tfshowedpass.setText(tfgenpass.getText());
            tfshowedpass.setVisible(true);
            tfgenpass.setVisible(false);
            return;
        }
        tfgenpass.setText(tfshowedpass.getText());
        tfgenpass.setVisible(true);
        tfshowedpass.setVisible(false);
    }

    @FXML
    private void showPassword1() {
        if (cbshowpass1.isSelected()) {
            passtextfield.setText(pfpassword.getText());
            passtextfield.setVisible(true);
            pfpassword.setVisible(false);
            return;
        }
        pfpassword.setText(passtextfield.getText());
        pfpassword.setVisible(true);
        passtextfield.setVisible(false);
    }  */
    @FXML
    private void showPassword1() {
       
            passtextfield.setText(pfpassword.getText());
            passtextfield.setVisible(true);
            pfpassword.setVisible(false);
            btnshow1.setVisible(false);
            btnhide1.setVisible(true);
       
    }  
    
      @FXML
        private void hidePassword1(){
         pfpassword.setText(passtextfield.getText());
        pfpassword.setVisible(true);
        passtextfield.setVisible(false);
        btnhide1.setVisible(false);
        btnshow1.setVisible(true);
    }
       
    @FXML
    private void showPassword() {
       
            tfshowedpass.setText(tfgenpass.getText());
            tfshowedpass.setVisible(true);
            tfgenpass.setVisible(false);
            btnshow.setVisible(false);
            btnhide.setVisible(true);
       
    }  
    
      @FXML
        private void hidePassword(){
         tfgenpass.setText(tfshowedpass.getText());
        tfgenpass.setVisible(true);
        tfshowedpass.setVisible(false);
        btnhide.setVisible(false);
        btnshow.setVisible(true);
    }
        
        private void sendSMS(String nom,String prenom,String phonenumber) {

    if (phonenumber == null || phonenumber.trim().isEmpty()) {
        System.out.println("Entrer un numéro");
        return;
    }

    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    LocalDate currentDate = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String messageText = "Le compte de "+nom+" "+prenom+" "+formatter.format(currentDate);
    Message message = Message.creator(new PhoneNumber(phonenumber),
            new PhoneNumber(TWILIO_NUMBER),
            messageText).create();

    if (message.getSid() != null) {
        System.out.println("SMS sent successfully to " + phonenumber + "!");
    } else {
        System.out.println("Error sending SMS to " + phonenumber + ".");
    }
}
}
