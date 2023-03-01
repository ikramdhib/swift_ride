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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

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
        String ville = txtville.getText();
        if (!possibleSuggestions.contains(ville)) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText("Ville introuvable");
            alert.setContentText("Le champs ville doit être l'un des mots proposés!");
            alert.showAndWait();
            return;
        }
        List<String> stations = stationsByCity.get(ville);
        if (stations == null || !stations.contains(txtnom_station.getText())) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText("Station introuvable");
            alert.setContentText("Le champs station doit être l'une des stations proposées pour la ville " + ville);
            alert.showAndWait();
            return;
        }
        insert();

        } else if (event.getSource() == btnM) {
            if (!possibleSuggestions.contains(txtville.getText())) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Attention");
                alert.setHeaderText("Ville introuvable");
                alert.setContentText("Le champs ville doit être l'un des mots proposés!");
                alert.showAndWait();
                return;
            }
            update();
        } else if (event.getSource() == btnS) {
            delete();
        } else if (event.getSource() == btnR) {
            retourner(event);
        }
    }
    private String[] _possibleSuggestions = {"Ariana ", "Beja ", "Ben Arous", "Bizerte", "Gabes", "Gafsa", "Jendouba", "Kairouan", "Kasserine", "Kebili", "Kef", "Mahdia", "Manouba", "Medenine", "Monastir", "Nabeul", "Sfax", "Sidi Bou Zid", "Siliana", "Sousse", "Tataouine", "Tozeur", "Tunis", "Zaghouan"};

    private Set<String> possibleSuggestions = new HashSet<>(Arrays.asList(_possibleSuggestions));

    /**
     * Initializes the controller class.
     */
    private Map<String, List<String>> stationsByCity = new HashMap<>();

   
    
   @Override
public void initialize(URL url, ResourceBundle rb) {
    // Populate the stationsByCity map with sample data
    stationsByCity.put("Tunis", Arrays.asList("Station A", "Station B", "Station C"));
    stationsByCity.put("Sousse", Arrays.asList("Station D", "Station E"));

    // Set up columns in the table
    colids.setCellValueFactory(new PropertyValueFactory<Station, Integer>("ids"));
    colville.setCellValueFactory(new PropertyValueFactory<Station, String>("ville"));
    colnom_station.setCellValueFactory(new PropertyValueFactory<Station, String>("nom_station"));

    // Populate table with data
    tableStation.setItems(retrieveStations());

    // Set up listener for table selection changes
    tableStation.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> showSelectedStation(newValue));

    // Set up suggestions for stations text field
    TextFields.bindAutoCompletion(txtnom_station, retrieveStations());

    // Set up suggestions for cities text field
    TextFields.bindAutoCompletion(txtville, stationsByCity.keySet());
    txtville.setOnAction(event -> {
        String ville = txtville.getText();
        if (stationsByCity.containsKey(ville)) {
            TextFields.bindAutoCompletion(txtnom_station, stationsByCity.get(ville));
        } else {
            txtnom_station.clear();
        }
    });
}

 private ObservableList<Station> retrieveStations() {
    // Create a list to hold the station data
    ObservableList<Station> stationList = FXCollections.observableArrayList();

    // Add sample data to the list
    stationList.add(new Station(1, "Tunis", "Station A"));
    stationList.add(new Station(2, "Sousse", "Station B"));
    stationList.add(new Station(3, "Tunis", "Station C"));
    stationList.add(new Station(4, "Sfax", "Station D"));
    stationList.add(new Station(5, "Tunis", "Station E"));

    return stationList;
}
   

    

    private void showStationNames(String ville) {
        List<String> stations = stationsByCity.get(ville);
        if (stations == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Stations not found");
            alert.setContentText("No stations found for the selected city.");
            alert.showAndWait();
            return;
        }
        ChoiceDialog<String> dialog = new ChoiceDialog<>(stations.get(0), stations);
        dialog.setTitle("Choose a station");
        dialog.setHeaderText("Select a station for the selected city:");
        dialog.setContentText("Station:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(station -> txtnom_station.setText(station));
    }
    
    
    private void showStationsDialog(String ville) {
        List<String> stations = getStationsByVille(ville);
        ChoiceDialog<String> dialog = new ChoiceDialog<>(stations.get(0), stations);
        dialog.setTitle("Choisir une station");
        dialog.setHeaderText("Choisir une station à partir de la ville " + ville);
        dialog.setContentText("Station:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            txtnom_station.setText(result.get());
        }
    }

    private List<String> getStationsByVille(String ville) {
        // TODO: implement code to get stations by ville
        // For example, you could retrieve a list of stations from a database
        // that match the given ville and return it here.
        List<String> stations = new ArrayList<>();
        if (ville.equals("Tunis")) {
            stations.add("Gare Tunis");
            stations.add("Hached");
            stations.add("Barcelone");
        } else if (ville.equals("Sousse")) {
            stations.add("Gare Sousse");
            stations.add("Kantaoui");
            stations.add("Enfidha");
        } else if (ville.equals("Sfax")) {
            stations.add("Gare Sfax");
            stations.add("Mahrès");
            stations.add("Skhira");
        }
        return stations;
    }
private void showSelectedStation(Station station) {
    if (station != null) {
        txtville.setText(station.getVille());
        txtnom_station.setText(station.getNom_station());
    }
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
        ObservableList<Station> listS = retrieveStations();

        colids.setCellValueFactory(new PropertyValueFactory<Station, Integer>("ids"));
        colville.setCellValueFactory(new PropertyValueFactory<Station, String>("ville"));
        colnom_station.setCellValueFactory(new PropertyValueFactory<Station, String>("nom_station"));

        tableStation.setItems(listS);

    }

    private ObservableList<Station> retrieveStations() {
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
        tableStation.setItems(retrieveStations());

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
        tableStation.setItems(retrieveStations());
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
