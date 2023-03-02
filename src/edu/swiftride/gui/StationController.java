/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.gui;

import edu.swiftride.entities.MoyenTransport;
import edu.swiftride.entities.Station;
import edu.swiftride.services.StationCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import java.util.stream.Collectors;

/**
 * FXML Controller class
 *
 * @author Ines
 */
public class StationController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private TextField txtville;
    @FXML
    private TextField txtnom_station;
    @FXML
    private Button btnA;
    @FXML
    private Button btnM;
    @FXML
    private Button btnS;
    @FXML
    private TableView<Station> tableStation;
    @FXML
    private TableColumn<Station, Integer> colids;
    @FXML
    private TableColumn<Station, String> colville;
    @FXML
    private TableColumn<Station, String> colnom_station;
    @FXML
    private Button btnR;
    @FXML

    StationCRUD scd = new StationCRUD();

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnA) {
// Vérifier la saisie de l'utilisateur pour la ville
            String ville = txtville.getText();
            ObservableList<String> villes = FXCollections.observableArrayList("Ariana", "Beja", "Ben Arous", "Bizerte", "Gabes", "Gafsa", "Jendouba", "Kairouan", "Kasserine", "Kebili", "Kef", "Mahdia", "Manouba", "Medenine", "Monastir", "Nabeul", "Sfax", "Sidi Bou Zid", "Siliana", "Sousse", "Tataouine", "Tozeur", "Tunis", "Zaghouan");
            if (!villes.contains(ville)) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Invalid input");
                alert.setHeaderText("Veuillez saisir une ville valide.");
                alert.showAndWait();
                return;
            }
// Vérifier si la ville a des stations suggérées
            ObservableList<String> stationsSuggerees = FXCollections.observableArrayList();
            switch (ville) {
                case "Ariana":
                    stationsSuggerees.addAll("Ariana Ville", "Ariana Soghra","Raoued", "Sidi Thabet","Cité Ennasr","Cité Ghazela","Cité El Khadra","Cité El Menzah","Borj Louzir","Chotrana","Kalaat El Andalous","Mnihla");
                    break;
                case "Beja":
                    stationsSuggerees.addAll("Béja Ville","Amdoun","Bou Arada","Téboursouk","Testour", "Thibar","Medjez El Bab","Nefza","El Maâgoula","Tazarka");
                    break;
                case "Jendouba":
                    stationsSuggerees.addAll("Jendouba Ville","Tabarka","Ain Draham","Balta-Bou Aouane","Beni M'Tir", "Fernana","Ghardimaou","Oued Mliz","Tabarka Plage");
                    break;
                case "Gafsa":
                    stationsSuggerees.addAll("Gafsa Ville","El Ksar","Métlaoui","Redeyef","Mdhilla", "Sned","Om Larayes","Moulares","El Guettar","Sidi Aïch");
                    break;
                case "Ben Arous":
                    stationsSuggerees.addAll("El Mourouj","Hammam Lif","Ben Arous Ville","Bou Mhel El Bassatine","Radès", "Mornag","Mhamdia","Fouchana","Ezzahra","Khalidia");
                    break;  
                case "Bizerte":
                    stationsSuggerees.addAll("Bizerte Ville","Menzel Bourguiba","Mateur","Ras Jebel","Sejnane", "Tinja","Joumine","Metline","Utique");
                    break;   
                case "Gabes":
                    stationsSuggerees.addAll("Gabès Ville","El Hamma","Matmata","Mareth","Métouia", "Oudhref","Ghannouch","Menzel El Habib","Nouvelle Matmata","Zarat");
                    break;                             
                case "Kairouan":
                    stationsSuggerees.addAll("Kairouan Ville","Oueslatia","Hajeb El Ayoun","Chebika","Echrarda", "Nasrallah","Sbikha","Haffouz","Bou Hajla","Alâaya");
                    break;    
                case "Kasserine":
                    stationsSuggerees.addAll("Kasserine Ville","Foussana","Jedelienne","Feriana","Thala", "Sbeitla","Haidra","El Ayoun","Ezzouhour");
                    break;
                case "Kebili":
                    stationsSuggerees.addAll("Kébili Ville","Douz","Souk Lahad","Faouar","Douiret", "Tozeur","Matmata","Médenine","Tataouine","Gabes");
                    break;  
                case "Kef":
                    stationsSuggerees.addAll("Le Kef","Tajerouine","Kalâat Senan","Nebeur","Sakiet Sidi Youssef", "Kalaat Khasba","Kalaat es Senam","Jérissa","El Ksour");
                    break;   
case "Mahdia":
    stationsSuggerees.addAll("Mahdia","El Jem","Ksour Essef","Chebba","Rejiche","Boumerdes","Essouassi","Hkaimiya","Kerker");
    break;
case "Manouba":
    stationsSuggerees.addAll("Manouba","Douar Hicher","Mornaguia","Oued Ellil","Borj El Amri","Den Den","El Batan","Djedeida","Mnihla");
    break;
case "Medenine":
    stationsSuggerees.addAll("Medenine","Ben Gardane","Zarzis","Djerba Ajim","Djerba Midoun","Djerba Houmt Souk","Guellala","Houmt El Souk","El Jorf");
    break;
case "Monastir":
    stationsSuggerees.addAll("Monastir","Sahline","Moknine","Ksar Hellal","Ksibet El Mediouni","Bembla","Zeramdine","Teboulba","Sayada");
    break;
case "Nabeul":
    stationsSuggerees.addAll("Nabeul","Hammamet","Kelibia","Menzel Temime","Korba","Dar Chaabane","Beni Khiar","Soliman","Takelsa");
    break;
case "Sfax":
    stationsSuggerees.addAll("Sfax","Sakiet Eddaier","Mahares","Kerkennah","El Hencha","Gremda","Skhira","Agareb","Menzel Chaker");
    break;
case "Sidi Bou Zid":
    stationsSuggerees.addAll("Sidi Bouzid","Meknassy","Jelma","Regueb","Ouled Haffouz","Sidi Ali Ben Aoun","Souk Jedid","Menzel Bouzaiane","Mazzouna");
    break;
case "Siliana":
    stationsSuggerees.addAll("Siliana","Bargou","El Krib","Maktar","Kesra","Bou Arada","Gaâfour","Rouhia","Sidi Bou Rouis");
    break;
case "Sousse":
    stationsSuggerees.addAll("Sousse","Hammam Sousse","Kalaa Kebira","Msaken","Enfidha","Akouda","Kondar","Kalaa Seghira","Bouficha");
    break;
case "Tataouine":
    stationsSuggerees.addAll("Tataouine","Ghomrassen","Remada","Dehiba","Beni Khedache","Ksar Ouled Debbab","Smar","Tamerza","Mareth");
    break;
case "Tozeur":
    stationsSuggerees.addAll("Tozeur","Nefta","Degache","Hazoua","Tamaghza","Tamerza","Gafsa","Redeyef","Metlaoui");
    break;
case "Tunis":
    stationsSuggerees.addAll("Tunis","Ariana","Ben Arous","La Marsa","Manouba","Mornag","Radès","Sidi Bou Said","Tunis Carthage");
    break;
case "Zaghouan":
    stationsSuggerees.addAll("Zaghouan","El Fahs","Bir Mcherga","Nadhour","Saouaf","Zriba","Djebel Oust","Zaghouan Eaux","Zriba Ouest");
    break;

                    
                    
                default:
                    break;
            }
// Si la ville a des stations suggérées, les ajouter au champ de texte d'autocomplétion
            if (!stationsSuggerees.contains(stationsSuggerees)) {
                AutoCompletionBinding<String> binding = TextFields.bindAutoCompletion(txtnom_station, stationsSuggerees);
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Invalid input");
                alert.setHeaderText("Veuillez saisir une station valide.");
                alert.showAndWait();
                return;
            }
            insert();
            
   
            
            
        } else if (event.getSource() == btnM) {
            update();
        } else if (event.getSource() == btnS) {
            delete();
        } else if (event.getSource() == btnR) {
            retourner(event);
        }

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showStation();

        ObservableList<String> villes = FXCollections.observableArrayList("Ariana", "Beja", "Ben Arous", "Bizerte", "Gabes", "Gafsa", "Jendouba", "Kairouan", "Kasserine", "Kebili", "Kef", "Mahdia", "Manouba", "Medenine", "Monastir", "Nabeul", "Sfax", "Sidi Bou Zid", "Siliana", "Sousse", "Tataouine", "Tozeur", "Tunis", "Zaghouan");

        // Ajouter les suggestions de ville au champ de texte d'autocomplétion
    AutoCompletionBinding<String> binding = TextFields.bindAutoCompletion(txtville, villes);

    // Ajouter un listener pour détecter le changement de la valeur de la ville sélectionnée
    txtville.textProperty().addListener((observable, oldValue, newValue) -> {
        // Filtrer les stations pour n'afficher que celles qui correspondent à la ville sélectionnée
        ObservableList<Station> filteredListS = getStation().filtered(s -> s.getVille().equals(newValue));

        // Créer une liste des noms de station pour les suggestions
        ObservableList<String> stations = filteredListS.stream().map(s -> s.getNom_station()).collect(Collectors.toCollection(FXCollections::observableArrayList));

        // Ajouter les suggestions de station au champ de texte d'autocomplétion
        AutoCompletionBinding<String> stationBinding = TextFields.bindAutoCompletion(txtnom_station, stations);
        
        
    });
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

    public void showStation() {
        ObservableList<Station> listS = getStation();

        colids.setCellValueFactory(new PropertyValueFactory<Station, Integer>("ids"));
        colville.setCellValueFactory(new PropertyValueFactory<Station, String>("ville"));
        colnom_station.setCellValueFactory(new PropertyValueFactory<Station, String>("nom_station"));

        tableStation.setItems(listS);

    }

    private ObservableList<Station> getStation() {
        ObservableList<Station> listStation = FXCollections.observableArrayList();
        Connection cnn = getConnection();
        String query = "SELECT * FROM station";
        Statement st;
        ResultSet rs;
        try {
            st = cnn.createStatement();
            rs = st.executeQuery(query);
            Station s;
            while (rs.next()) {
                s = new Station(rs.getInt("ids"), rs.getString("ville"), rs.getString("nom_station"));
                listStation.add(s);
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return listStation;
    }

    public void insert() {

        Station s = new Station();

        // m.setId(Integer.parseInt(txtid.getText()));
        String ville = txtville.getText();
        String nom_station = txtnom_station.getText();

        // Check that required fields are not empty
        if (ville.isEmpty() || nom_station.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Missing fields");
            alert.setHeaderText("All fields must be filled");
            alert.showAndWait();
            return;
        }

        // Check that type only contain alphabetic characters
        if (!ville.matches("^[a-zA-Z]+$")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid fields");
            alert.setHeaderText("TYPE must contain ONLY alphabetic characters");
            alert.showAndWait();
            return;
        }

        s.setVille(txtville.getText());
        s.setNom_station(txtnom_station.getText());

        scd.ajouterStation(s);
        tableStation.setItems(getStation());

        // Afficher une alerte de confirmation si la modification a réussi
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ajout réussie");
        alert.setHeaderText("L'ajout a été effectuée avec succès.");
        alert.showAndWait();

        showStation();

    }

    private void update() {
        Station s = new Station();

        Station st = tableStation.getSelectionModel().getSelectedItem();
        if (st == null) {
            // Créer une alerte de type WARNING pour demander à l'utilisateur de choisir une entreprise à modifier
            Alert warning = new Alert(Alert.AlertType.WARNING);
            warning.setTitle("Attention");
            warning.setHeaderText("Vous devez sélectionner une station à modifier.");
            warning.showAndWait();
            return;
        }

        s.setIds(tableStation.getSelectionModel().getSelectedItem().getIds());
        s.setVille(txtville.getText());
        s.setNom_station(txtnom_station.getText());
        String ville = txtville.getText();
        String nom_station = txtnom_station.getText();

        // Check that required fields are not empty
        if (ville.isEmpty() || nom_station.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Missing fields");
            alert.setHeaderText("All fields must be filled");
            alert.showAndWait();
            return;
        }

        // Check that type only contain alphabetic characters
        if (!ville.matches("^[a-zA-Z]+$")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid fields");
            alert.setHeaderText("TYPE must contain ONLY alphabetic characters");
            alert.showAndWait();
            return;
        }
        if (!nom_station.matches("^[a-zA-Z]+$")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid fields");
            alert.setHeaderText("TYPE must contain ONLY alphabetic characters");
            alert.showAndWait();
            return;
        }
        scd.modifierStation(s);
        tableStation.setItems(getStation());
        // Afficher une alerte de confirmation si la modification a réussi
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Modification réussie");
        alert.setHeaderText("La modification a été effectuée avec succès.");
        alert.showAndWait();

    }

    private void delete() {
        Station s = new Station();
        Station st = tableStation.getSelectionModel().getSelectedItem();
        if (st == null) {
            // Créer une alerte de type WARNING pour demander à l'utilisateur de choisir une entreprise à supprimer
            Alert warning = new Alert(Alert.AlertType.WARNING);
            warning.setTitle("Attention");
            warning.setHeaderText("Vous devez sélectionner une station à supprimer.");
            warning.showAndWait();
            return;
        }
        // Créer une alerte de type CONFIRMATION pour demander à l'utilisateur s'il veut vraiment supprimer l'entreprise
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmation de suppression");
        confirmation.setHeaderText("Êtes-vous sûr de vouloir supprimer la station " + st.getIds() + " ?");
        Optional<ButtonType> result = confirmation.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            scd.supprimerStation(st);
            tableStation.getItems().removeAll(st);
            // Créer une alerte de type INFORMATION pour informer l'utilisateur que la suppression a réussi
            Alert success = new Alert(Alert.AlertType.INFORMATION);
            success.setTitle("Suppression réussie");
            success.setHeaderText("La station a été supprimée avec succès.");
            success.showAndWait();
        }

    }

    private void retourner(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Authentication.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
