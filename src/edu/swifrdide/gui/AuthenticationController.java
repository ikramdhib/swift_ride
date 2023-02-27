    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */
    package edu.swifrdide.gui;


    import edu.swiftride.entities.EntreprisePartenaire;
import edu.swiftride.services.Email;
    import edu.swiftride.services.EntreprisePartenaireCRUD;
import edu.swiftride.utils.Cryptage;
    import java.net.URL;
import java.security.NoSuchAlgorithmException;
    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.ResultSet;
    import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
    import java.util.ResourceBundle;
    import javafx.collections.FXCollections;
    import javafx.collections.ObservableList;
    import javafx.event.ActionEvent;
    import javafx.fxml.FXML;
    import javafx.fxml.Initializable;
    import javafx.scene.control.Button;
    import javafx.scene.control.TableColumn;
    import javafx.scene.control.TableView;
    import javafx.scene.control.TextField;
    import javafx.scene.control.cell.PropertyValueFactory;
    import javafx.scene.control.Alert;
    import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
    import javafx.scene.control.PasswordField;
import javafx.scene.control.TextFormatter;
    import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javax.mail.MessagingException;


    /**
     * FXML Controller class
     *
     * @author Sami
     */
    public class AuthenticationController implements Initializable {

        @FXML
        private TextField txtNomentreprise;

        @FXML
        private TextField txtNomadmin;

        @FXML
        private TextField txtPrenomadmin;

        @FXML
        private TextField txtNbvoiture;

        @FXML
        private TextField txtTel;

        @FXML
        private TextField txtMatricule;

        @FXML
        private TextField txtLogin;

        @FXML
        private TextField txtMdps;

        @FXML
        private TableView<EntreprisePartenaire> table;

        @FXML
        private TableColumn<EntreprisePartenaire, Integer> idCol;

        @FXML
        private TableColumn<EntreprisePartenaire, String> entrepriseCol;

        @FXML
        private TableColumn<EntreprisePartenaire, String> nomadminCol;

        @FXML
        private TableColumn<EntreprisePartenaire, String> PrenomCol;

        @FXML
        private TableColumn<EntreprisePartenaire, Integer> nbvoitureCol;

        @FXML
        private TableColumn<EntreprisePartenaire, Integer> telCol;

        @FXML
        private TableColumn<EntreprisePartenaire, String> matriculeCol;

        @FXML
        private TableColumn<EntreprisePartenaire, String> loginCol;

        @FXML
        private TableColumn<EntreprisePartenaire, String> mdpsCol;

        @FXML
        private Button btnAjouter;

        @FXML
        private Button btnModifier;

        @FXML
        private Button btnSupprimer;
        
        @FXML
        private Button btnRechercher;

        @FXML
        private TextField txtRechercher;
        
        @FXML
        private ImageView myImage;


        EntreprisePartenaireCRUD pcm=new EntreprisePartenaireCRUD();
      @FXML
      private void handleButtonAction(ActionEvent event){
          if(event.getSource() == btnAjouter){
              ajouterEntreprise();
          }
          else if(event.getSource() == btnModifier){
              modifierEntreprise();
          }
          else if(event.getSource() == btnSupprimer){
              supprimerEntreprise();
          } 
      }

        /**
         * Initializes the controller class.
         */
        @Override
        public void initialize(URL url, ResourceBundle rb) {
            showEntreprisePartenaire();
            //Animation
           /* FadeTransition fade = new FadeTransition();
            fade.setNode(myImage);
            fade.setDuration(Duration.millis(10000));
            fade.setCycleCount(TranslateTransition.INDEFINITE);
            fade.setInterpolator(Interpolator.LINEAR);
            fade.setFromValue(0);
            fade.setToValue(1);
            fade.play(); */
        }    
        public Connection getConnection(){
            Connection cnn;
            try{
                cnn=DriverManager.getConnection("jdbc:mysql://localhost:3306/swiftride","root", "");
                return cnn;
            }catch(Exception ex){
                System.out.println("Error" +ex.getMessage());
                return null;
            }
         }
          public void showEntreprisePartenaire(){
            ObservableList<EntreprisePartenaire> list=getEntreprisePartenaire();
    ;
           idCol.setCellValueFactory(new PropertyValueFactory<EntreprisePartenaire, Integer>("id"));
           entrepriseCol.setCellValueFactory(new PropertyValueFactory<EntreprisePartenaire, String>("nom_entreprise"));
           nomadminCol.setCellValueFactory(new PropertyValueFactory<EntreprisePartenaire, String>("nom_admin"));
           PrenomCol.setCellValueFactory(new PropertyValueFactory<EntreprisePartenaire, String>("prenom_admin"));
           nbvoitureCol.setCellValueFactory(new PropertyValueFactory<EntreprisePartenaire, Integer>("nb_voiture"));
           telCol.setCellValueFactory(new PropertyValueFactory<EntreprisePartenaire, Integer>("tel"));
           matriculeCol.setCellValueFactory(new PropertyValueFactory<EntreprisePartenaire, String>("matricule"));
           loginCol.setCellValueFactory(new PropertyValueFactory<EntreprisePartenaire, String>("login"));
           mdpsCol.setCellValueFactory(new PropertyValueFactory<EntreprisePartenaire, String>("mdp"));

           table.setItems(list);

        }
           private ObservableList<EntreprisePartenaire> getEntreprisePartenaire() {
            ObservableList<EntreprisePartenaire> listM =FXCollections.observableArrayList();
            Connection cnn= getConnection();
           String query= "SELECT * FROM entreprise_partenaire";
           Statement st;
           ResultSet rs;
           try{
               st=cnn.createStatement();
               rs=st.executeQuery(query);
               EntreprisePartenaire m;
               while(rs.next()){
                    m=new EntreprisePartenaire();
                    m.setId(rs.getInt(1));
                    m.setNom_entreprise(rs.getNString(2));
                    m.setNom_admin(rs.getNString(3));
                    m.setPrenom_admin(rs.getNString(4));
                    m.setNb_voiture(rs.getInt(5));
                    m.setTel(rs.getInt(6));
                    m.setMatricule(rs.getNString(7));
                    m.setLogin(rs.getNString(8));
                    m.setMdp(rs.getNString(9));
                    listM.add(m);
               }
           }catch(Exception ex){
               ex.printStackTrace();

              }
           return listM;
        }



    public void ajouterEntreprise() {
        EntreprisePartenaire m = new EntreprisePartenaire();
        String nom_entreprise = txtNomentreprise.getText();
        String nom_admin = txtNomadmin.getText();
        String prenom_admin = txtPrenomadmin.getText();
        String matricule = txtMatricule.getText();
        String login = txtLogin.getText();
        String mdp = txtMdps.getText();
        String nb_voiture_str = txtNbvoiture.getText();
        String tel_str = txtTel.getText();
        
        
        
        // Check that required fields are not empty
        if (nom_entreprise.isEmpty() || nom_admin.isEmpty() || prenom_admin.isEmpty() || matricule.isEmpty() || login.isEmpty() || mdp.isEmpty() || nb_voiture_str.isEmpty() || tel_str.isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Missing fields");
            alert.setHeaderText("All fields must be filled");
            alert.showAndWait();
            return;
        }

        // Check that numeric fields contain valid numbers
        int nb_voiture, tel;
        try {
            nb_voiture = Integer.parseInt(nb_voiture_str);
            tel = Integer.parseInt(tel_str);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Invalid fields");
            alert.setHeaderText("Number of cars and telephone number must contain ONLY numbers");
            alert.showAndWait();
            return;
        }
        if (nb_voiture < 1) {
    Alert alert = new Alert(AlertType.WARNING);
    alert.setTitle("Invalid number of cars");
    alert.setHeaderText("The number of cars must be greater than 0");
    alert.showAndWait();
    return;
}

        // Check that the login field is a valid email address
        if (!login.matches("^\\S+@\\S+\\.\\S+$")) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Invalid email");
            alert.setHeaderText("The login field must contain a valid email address");
            alert.showAndWait();
            return;
        }
        
        // Check that the name and surname fields only contain alphabetic characters
    if (!nom_admin.matches("^[a-zA-Z]+$") || !prenom_admin.matches("^[a-zA-Z]+$")) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Invalid fields");
        alert.setHeaderText("Name and surname must contain ONLY alphabetic characters");
        alert.showAndWait();
        return;
    }
    
    // Check that the tel field has exactly 8 characters
    if (tel_str.length() != 8) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Invalid fields");
        alert.setHeaderText("Telephone number must be exactly 8 characters long");
        alert.showAndWait();
        return;
    }
    
    // Check that the password meets the requirements
if (!mdp.matches("^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*(),.?\":{}|<>]).{8,}$")) {
    Alert alert = new Alert(AlertType.WARNING);
    alert.setTitle("Invalid password");
    alert.setHeaderText("The password must meet the following requirements:\n" +
            "- Must start with a capital letter\n" +
            "- Must be at least 8 characters long\n" +
            "- Must contain at least one number\n" +
            "- Must contain at least one special character (!@#$%^&*(),.?\":{}|<>)");
    alert.showAndWait();
    return;
}
         // Vérifier si un autre administrateur avec les mêmes informations existe déjà
    ObservableList<EntreprisePartenaire> adminList = table.getItems();
    boolean adminExists = false;
    for (EntreprisePartenaire admin : adminList) {
        if (admin.getNom_admin().equals(nom_admin) && admin.getPrenom_admin().equals(prenom_admin)
            && admin.getNb_voiture() == nb_voiture && admin.getTel() == tel 
            && admin.getMatricule().equals(matricule) && admin.getLogin().equals(login) 
            && admin.getMdp().equals(mdp)) {
            adminExists = true;
            break;
        }
    }
    if (adminExists) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Un autre administrateur avec les mêmes informations existe déjà !");
        alert.showAndWait();
    } else {
            try {
                String cryptedmdp= Cryptage.toHexString(Cryptage.getSHA(mdp));
                // Set values for the enterprise object
                m.setNom_entreprise(nom_entreprise);
                m.setNom_admin(nom_admin);
                m.setPrenom_admin(prenom_admin);
                m.setMatricule(matricule);
                m.setLogin(login);
                m.setMdp(cryptedmdp);
                m.setNb_voiture(nb_voiture);
                m.setTel(tel);
                
                
                
                // Add the enterprise object to the list and update the table view
                pcm.ajouter(m);
                table.setItems(getEntreprisePartenaire());
                Email.setEmail(m.getLogin());
                Email.setNom_admin(txtNomadmin.getText());
                Email.setPrenom_admin(txtPrenomadmin.getText());
                Email.setNb_voiture(txtNbvoiture.getText());
                Email.setTotalCars(getTotalCars());
                
                Email.sendEmail();
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Ajout réussie");
                alert.setHeaderText("L'ajout a été effectuée avec succès.");
                alert.showAndWait();
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(AuthenticationController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MessagingException ex) {
                Logger.getLogger(AuthenticationController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
    }
    }


        private void modifierEntreprise() {
        EntreprisePartenaire m = new EntreprisePartenaire();
        EntreprisePartenaire entreprise = table.getSelectionModel().getSelectedItem();
        
        
        
                 if(entreprise == null){
    // Créer une alerte de type WARNING pour demander à l'utilisateur de choisir une entreprise à modifier
    Alert warning = new Alert(Alert.AlertType.WARNING);
    warning.setTitle("Attention");
    warning.setHeaderText("Vous devez sélectionner une entreprise à modifier.");
    warning.showAndWait();
    return;
}
                 
                 m.setId(table.getSelectionModel().getSelectedItem().getId());
        
        String nom_entreprise = txtNomentreprise.getText();
        String nom_admin = txtNomadmin.getText();
        String prenom_admin = txtPrenomadmin.getText();
        String matricule = txtMatricule.getText();
        String login = txtLogin.getText();
        String mdp = txtMdps.getText();
        String nb_voiture_str = txtNbvoiture.getText();
        String tel_str = txtTel.getText();

   
        
        // Vérifier que les champs obligatoires ne sont pas vides
        if (nom_entreprise.isEmpty() || nom_admin.isEmpty() || prenom_admin.isEmpty() || matricule.isEmpty() || login.isEmpty() || mdp.isEmpty() || nb_voiture_str.isEmpty() || tel_str.isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Champs manquants");
            alert.setHeaderText("Tous les champs doivent être remplis");
            alert.showAndWait();
            return;
        }

        // Vérifier que les champs nb_voiture et tel contiennent des nombres valides
        int nb_voiture, tel;
        try {
            nb_voiture = Integer.parseInt(nb_voiture_str);
            tel = Integer.parseInt(tel_str);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Champs invalides");
            alert.setHeaderText("Nombre de voiture et numero tel doivent être Contenir SEULEMENT des nombres!!!!");
            alert.showAndWait();
            return;
        }

        // Vérifier que le champ login est une adresse email valide
        if (!login.matches("^\\S+@\\S+\\.\\S+$")) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Champ invalide");
            alert.setHeaderText("Le champ Login doit être une adresse email valide");
            alert.showAndWait();
            return;
        }
        
          // Check that the name and surname fields only contain alphabetic characters
    if (!nom_admin.matches("^[a-zA-Z]+$") || !prenom_admin.matches("^[a-zA-Z]+$")) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Invalid fields");
        alert.setHeaderText("Name and surname must contain ONLY alphabetic characters");
        alert.showAndWait();
        return;
    }
    
    // Check that the tel field has exactly 8 characters
    if (tel_str.length() != 8) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Invalid fields");
        alert.setHeaderText("Telephone number must be exactly 8 characters long");
        alert.showAndWait();
        return;
    }
    
    // Check that the password meets the requirements
if (!mdp.matches("^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*(),.?\":{}|<>]).{8,}$")) {
    Alert alert = new Alert(AlertType.WARNING);
    alert.setTitle("Invalid password");
    alert.setHeaderText("The password must meet the following requirements:\n" +
            "- Must start with a capital letter\n" +
            "- Must be at least 8 characters long\n" +
            "- Must contain at least one number\n" +
            "- Must contain at least one special character (!@#$%^&*(),.?\":{}|<>)");
    alert.showAndWait();
    return;
}

  // Vérifier si un autre administrateur avec les mêmes informations existe déjà
    ObservableList<EntreprisePartenaire> adminList = table.getItems();
    boolean adminExists = false;
    for (EntreprisePartenaire admin : adminList) {
        if (admin.getNom_admin().equals(nom_admin) && admin.getPrenom_admin().equals(prenom_admin)
            && admin.getNb_voiture() == nb_voiture && admin.getTel() == tel 
            && admin.getMatricule().equals(matricule) && admin.getLogin().equals(login) 
            && admin.getMdp().equals(mdp)) {
            adminExists = true;
            break;
        }
    }
    
    
    if (adminExists) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Un autre administrateur avec les mêmes informations existe déjà !");
        alert.showAndWait();
    } else {

        m.setNom_entreprise(nom_entreprise);
        m.setNom_admin(nom_admin);
        m.setPrenom_admin(prenom_admin);
        m.setNb_voiture(nb_voiture);
        m.setTel(tel);
        m.setMatricule(matricule);
        m.setLogin(login);
        m.setMdp(mdp);

        // Effectuer la modification
    pcm.modifier(m);
    table.setItems(getEntreprisePartenaire());

    // Afficher une alerte de confirmation si la modification a réussi
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Modification réussie");
    alert.setHeaderText("La modification a été effectuée avec succès.");
    alert.showAndWait();
        }
        }

        private void supprimerEntreprise(){
                EntreprisePartenaire m = new EntreprisePartenaire();
                EntreprisePartenaire entreprise = table.getSelectionModel().getSelectedItem();
if(entreprise == null){
    // Créer une alerte de type WARNING pour demander à l'utilisateur de choisir une entreprise à supprimer
    Alert warning = new Alert(Alert.AlertType.WARNING);
    warning.setTitle("Attention");
    warning.setHeaderText("Vous devez sélectionner une entreprise à supprimer.");
    warning.showAndWait();
    return;
}

// Créer une alerte de type CONFIRMATION pour demander à l'utilisateur s'il veut vraiment supprimer l'entreprise
Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
confirmation.setTitle("Confirmation de suppression");
confirmation.setHeaderText("Êtes-vous sûr de vouloir supprimer l'entreprise " + entreprise.getId() + " ?");
Optional<ButtonType> result = confirmation.showAndWait();

if(result.isPresent() && result.get() == ButtonType.OK){
    pcm.supprimer(entreprise);
    table.getItems().removeAll(entreprise);
    
    // Créer une alerte de type INFORMATION pour informer l'utilisateur que la suppression a réussi
    Alert success = new Alert(Alert.AlertType.INFORMATION);
    success.setTitle("Suppression réussie");
    success.setHeaderText("L'entreprise a été supprimée avec succès.");
    success.showAndWait();
}
    }   
        
     public int getTotalCars() {
        ObservableList<EntreprisePartenaire> entreprises = getEntreprisePartenaire();
        int totalCars = 0;
        for (EntreprisePartenaire entreprise : entreprises) {
            totalCars += entreprise.getNb_voiture();
        }
        return totalCars;
    }    
     
     
    }