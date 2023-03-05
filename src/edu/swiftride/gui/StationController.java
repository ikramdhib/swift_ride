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
import java.util.Arrays;
import java.util.HashSet;
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

    //Auto complete
    private AutoCompletionBinding<String> villeAutoCompletionBinding;
    private AutoCompletionBinding<String> stationAutoCompletionBinding;
    private String[] _possibleSuggestions = {"Ariana", "Beja", "Ben Arous", "Bizerte", "Gabes", "Gafsa", "Jendouba", "Kairouan", "Kasserine", "Kebili", "Kef", "Mahdia", "Manouba", "Medenine", "Monastir", "Nabeul", "Sfax", "Sidi Bou Zid", "Siliana", "Sousse", "Tataouine", "Tozeur", "Tunis", "Zaghouan"};
    private Set<String> possibleSuggestions = new HashSet<>(Arrays.asList(_possibleSuggestions));
    private String[] ArienaPossibleSuggestions = {"Ariana Ville", "Ariana Soghra", "Raoued", "Sidi Thabet", "Cité Ennasr", "Cité Ghazela", "Cité El Khadra", "Cité El Menzah", "Borj Louzir", "Chotrana", "Kalaat El Andalous", "Mnihla"};
    private String[] BejaPossibleSuggestions = {"Béja Ville", "Amdoun", "Bou Arada", "Téboursouk", "Testour", "Thibar", "Medjez El Bab", "Nefza", "El Maâgoula", "Tazarka"};
    private String[] Ben_ArousPossibleSuggestions = {"El Mourouj", "Hammam Lif", "BenArous Ville", "Bou Mhel El Bassatine", "Radès", "Mornag", "Mhamdia", "Fouchana", "Ezzahra", "Khalidia"};
    private String[] BizertePossibleSuggestions = {"Bizerte Ville", "Menzel Bourguiba", "Mateur", "Ras Jebel", "Sejnane", "Tinja", "Joumine", "Metline", "Utique"};
    private String[] GabesPossibleSuggestions = {"Gabès Ville", "El Hamma", "Matmata", "Mareth", "Métouia", "Oudhref", "Ghannouch", "Menzel El Habib", "Nouvelle Matmata", "Zarat"};
    private String[] GafsaPossibleSuggestions = {"Gafsa Ville", "El Ksar", "Métlaoui", "Redeyef", "Mdhilla", "Sned", "Om Larayes", "Moulares", "El Guettar", "Sidi Aïch"};
    private String[] JendoubaPossibleSuggestions = {"Jendouba Ville", "Tabarka", "Ain Draham", "Balta-Bou Aouane", "Beni M'Tir", "Fernana", "Ghardimaou", "Oued Mliz", "Tabarka Plage"};
    private String[] KairouanPossibleSuggestions = {"Kairouan Ville", "Oueslatia", "Hajeb El Ayoun", "Chebika", "Echrarda", "Nasrallah", "Sbikha", "Haffouz", "Bou Hajla", "Alâaya"};
    private String[] KasserinePossibleSuggestions = {"Kasserine Ville", "Foussana", "Jedelienne", "Feriana", "Thala", "Sbeitla", "Haidra", "El Ayoun", "Ezzouhour"};
    private String[] KebiliPossibleSuggestions = {"Kébili Ville", "Douz", "Souk Lahad", "Faouar", "Douiret", "Tozeur", "Matmata", "Médenine", "Tataouine", "Gabes"};
    private String[] KefPossibleSuggestions = {"Le Kef", "Tajerouine", "Kalâat Senan", "Nebeur", "Sakiet Sidi Youssef", "Kalaat Khasba", "Kalaat es Senam", "Jérissa", "El Ksour"};
    private String[] MahdiaPossibleSuggestions = {"Mahdia", "El Jem", "Ksour Essef", "Chebba", "Rejiche", "Boumerdes", "Essouassi", "Hkaimiya", "Kerker"};
    private String[] ManoubaPossibleSuggestions = {"Manouba", "Douar Hicher", "Mornaguia", "Oued Ellil", "Borj El Amri", "Den Den", "El Batan", "Djedeida", "Mnihla"};
    private String[] MedeninePossibleSuggestions = {"Medenine", "Ben Gardane", "Zarzis", "Djerba Ajim", "Djerba Midoun", "Djerba Houmt Souk", "Guellala", "Houmt El Souk", "El Jorf"};
    private String[] MonastirPossibleSuggestions = {"Monastir", "Sahline", "Moknine", "Ksar Hellal", "Ksibet El Mediouni", "Bembla", "Zeramdine", "Teboulba", "Sayada"};
    private String[] NabeulPossibleSuggestions = {"Nabeul", "Hammamet", "Kelibia", "Menzel Temime", "Korba", "Dar Chaabane", "Beni Khiar", "Soliman", "Takelsa"};
    private String[] SfaxPossibleSuggestions = {"Sfax", "Sakiet Eddaier", "Mahares", "Kerkennah", "El Hencha", "Gremda", "Skhira", "Agareb", "Menzel Chaker"};
    private String[] Sidi_Bou_ZidPossibleSuggestions = {"Sidi Bouzid", "Meknassy", "Jelma", "Regueb", "Ouled Haffouz", "Sidi Ali Ben Aoun", "Souk Jedid", "Menzel Bouzaiane", "Mazzouna"};
    private String[] SilianaPossibleSuggestions = {"Siliana", "Bargou", "El Krib", "Maktar", "Kesra", "Bou Arada", "Gaâfour", "Rouhia", "Sidi Bou Rouis"};
    private String[] SoussePossibleSuggestions = {"Sousse", "Hammam Sousse", "Kalaa Kebira", "Msaken", "Enfidha", "Akouda", "Kondar", "Kalaa Seghira", "Bouficha"};
    private String[] TataouinePossibleSuggestions = {"Tataouine", "Ghomrassen", "Remada", "Dehiba", "Beni Khedache", "Ksar Ouled Debbab", "Smar", "Tamerza", "Mareth"};
    private String[] TozeurPossibleSuggestions = {"Tozeur", "Nefta", "Degache", "Hazoua", "Tamaghza", "Tamerza", "Redeyef", "Metlaoui"};
    private String[] TunisPossibleSuggestions = {"Tunis ville", "La Marsa", "Sidi Bou Said", "Tunis Carthage"};
    private String[] ZaghouanPossibleSuggestions = {"Zaghouan", "El Fahs", "Bir Mcherga", "Nadhour", "Saouaf", "Zriba", "Djebel Oust", "Zaghouan Eaux", "Zriba Ouest"};

    private Set<String> ArienaSuggestions = new HashSet<>(Arrays.asList(ArienaPossibleSuggestions));
    private Set<String> BejaSuggestions = new HashSet<>(Arrays.asList(BejaPossibleSuggestions));
    private Set<String> Ben_ArousSuggestions = new HashSet<>(Arrays.asList(Ben_ArousPossibleSuggestions));
    private Set<String> BizerteSuggestions = new HashSet<>(Arrays.asList(BizertePossibleSuggestions));
    private Set<String> GabesSuggestions = new HashSet<>(Arrays.asList(GabesPossibleSuggestions));
    private Set<String> GafsaSuggestions = new HashSet<>(Arrays.asList(GafsaPossibleSuggestions));
    private Set<String> JendoubaSuggestions = new HashSet<>(Arrays.asList(JendoubaPossibleSuggestions));
    private Set<String> KairouanSuggestions = new HashSet<>(Arrays.asList(KairouanPossibleSuggestions));
    private Set<String> KasserineSuggestions = new HashSet<>(Arrays.asList(KasserinePossibleSuggestions));
    private Set<String> KebiliSuggestions = new HashSet<>(Arrays.asList(KebiliPossibleSuggestions));
    private Set<String> KefSuggestions = new HashSet<>(Arrays.asList(KefPossibleSuggestions));
    private Set<String> MahdiaSuggestions = new HashSet<>(Arrays.asList(MahdiaPossibleSuggestions));
    private Set<String> ManoubaSuggestions = new HashSet<>(Arrays.asList(ManoubaPossibleSuggestions));
    private Set<String> MedenineSuggestions = new HashSet<>(Arrays.asList(MedeninePossibleSuggestions));
    private Set<String> MonastirSuggestions = new HashSet<>(Arrays.asList(MonastirPossibleSuggestions));
    private Set<String> NabeulSuggestions = new HashSet<>(Arrays.asList(NabeulPossibleSuggestions));
    private Set<String> SfaxSuggestions = new HashSet<>(Arrays.asList(SfaxPossibleSuggestions));
    private Set<String> Sidi_Bou_ZidSuggestions = new HashSet<>(Arrays.asList(Sidi_Bou_ZidPossibleSuggestions));
    private Set<String> SilianaSuggestions = new HashSet<>(Arrays.asList(SilianaPossibleSuggestions));
    private Set<String> SousseSuggestions = new HashSet<>(Arrays.asList(SoussePossibleSuggestions));
    private Set<String> TataouineSuggestions = new HashSet<>(Arrays.asList(TataouinePossibleSuggestions));
    private Set<String> TozeurSuggestions = new HashSet<>(Arrays.asList(TozeurPossibleSuggestions));
    private Set<String> TunisSuggestions = new HashSet<>(Arrays.asList(TunisPossibleSuggestions));
    private Set<String> ZaghouanSuggestions = new HashSet<>(Arrays.asList(ZaghouanPossibleSuggestions));

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnA) {

            String ville = txtville.getText();
            String nom_station = txtnom_station.getText();

// Check if ville and station are valid suggestions
            boolean villeValid = possibleSuggestions.contains(ville);
            boolean nom_stationValid = false;
            if (ville.equals("Ariena")) {
                nom_stationValid = ArienaSuggestions.contains(nom_station);
            } else if (ville.equals("Beja")) {
                nom_stationValid = BejaSuggestions.contains(nom_station);
            } else if (ville.equals("Ben Arous")) {
                nom_stationValid = Ben_ArousSuggestions.contains(nom_station);
            } else if (ville.equals("Bizerte")) {
                nom_stationValid = BizerteSuggestions.contains(nom_station);
            } else if (ville.equals("Gabes")) {
                nom_stationValid = GabesSuggestions.contains(nom_station);
            } else if (ville.equals("Gafsa")) {
                nom_stationValid = GafsaSuggestions.contains(nom_station);
            } else if (ville.equals("Jendouba")) {
                nom_stationValid = JendoubaSuggestions.contains(nom_station);
            } else if (ville.equals("Kairouan")) {
                nom_stationValid = KairouanSuggestions.contains(nom_station);
            } else if (ville.equals("kasserine")) {
                nom_stationValid = KasserineSuggestions.contains(nom_station);
            } else if (ville.equals("kebili")) {
                nom_stationValid = KebiliSuggestions.contains(nom_station);
            } else if (ville.equals("kef")) {
                nom_stationValid = KefSuggestions.contains(nom_station);
            } else if (ville.equals("Mahdia")) {
                nom_stationValid = MahdiaSuggestions.contains(nom_station);
            } else if (ville.equals("Manouba")) {
                nom_stationValid = ManoubaSuggestions.contains(nom_station);
            } else if (ville.equals("Medenine")) {
                nom_stationValid = MedenineSuggestions.contains(nom_station);
            } else if (ville.equals("Monastir")) {
                nom_stationValid = MonastirSuggestions.contains(nom_station);
            } else if (ville.equals("Nabeul")) {
                nom_stationValid = NabeulSuggestions.contains(nom_station);
            } else if (ville.equals("Sfax")) {
                nom_stationValid = SfaxSuggestions.contains(nom_station);
            } else if (ville.equals("Sidi Bou Zid")) {
                nom_stationValid = Sidi_Bou_ZidSuggestions.contains(nom_station);
            } else if (ville.equals("Siliana")) {
                nom_stationValid = SilianaSuggestions.contains(nom_station);
            } else if (ville.equals("Sousse")) {
                nom_stationValid = SousseSuggestions.contains(nom_station);
            } else if (ville.equals("Tataouine")) {
                nom_stationValid = TataouineSuggestions.contains(nom_station);
            } else if (ville.equals("Tozeur")) {
                nom_stationValid = TozeurSuggestions.contains(nom_station);
            } else if (ville.equals("Tunis")) {
                nom_stationValid = TunisSuggestions.contains(nom_station);
            } else if (ville.equals("Zaghouan")) {
                nom_stationValid = ZaghouanSuggestions.contains(nom_station);
            }
            // Show an alert if either nom or prenom is invalid
            if (!villeValid || !nom_stationValid) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a suggestion from the list for both ville and nom de station .");
                alert.showAndWait();
                return; // Don't continue with adding the enterprise if the input is invalid
            }

            insert();

        } else if (event.getSource() == btnM) {

            String ville = txtville.getText();
            String nom_station = txtnom_station.getText();

// Check if ville and station are valid suggestions
            boolean villeValid = possibleSuggestions.contains(ville);
            boolean nom_stationValid = false;
            if (ville.equals("Ariena")) {
                nom_stationValid = ArienaSuggestions.contains(nom_station);
            } else if (ville.equals("Beja")) {
                nom_stationValid = BejaSuggestions.contains(nom_station);
            } else if (ville.equals("Ben Arous")) {
                nom_stationValid = Ben_ArousSuggestions.contains(nom_station);
            } else if (ville.equals("Bizerte")) {
                nom_stationValid = BizerteSuggestions.contains(nom_station);
            } else if (ville.equals("Gabes")) {
                nom_stationValid = GabesSuggestions.contains(nom_station);
            } else if (ville.equals("Gafsa")) {
                nom_stationValid = GafsaSuggestions.contains(nom_station);
            } else if (ville.equals("Jendouba")) {
                nom_stationValid = JendoubaSuggestions.contains(nom_station);
            } else if (ville.equals("Kairouan")) {
                nom_stationValid = KairouanSuggestions.contains(nom_station);
            } else if (ville.equals("kasserine")) {
                nom_stationValid = KasserineSuggestions.contains(nom_station);
            } else if (ville.equals("kebili")) {
                nom_stationValid = KebiliSuggestions.contains(nom_station);
            } else if (ville.equals("kef")) {
                nom_stationValid = KefSuggestions.contains(nom_station);
            } else if (ville.equals("Mahdia")) {
                nom_stationValid = MahdiaSuggestions.contains(nom_station);
            } else if (ville.equals("Manouba")) {
                nom_stationValid = ManoubaSuggestions.contains(nom_station);
            } else if (ville.equals("Medenine")) {
                nom_stationValid = MedenineSuggestions.contains(nom_station);
            } else if (ville.equals("Monastir")) {
                nom_stationValid = MonastirSuggestions.contains(nom_station);
            } else if (ville.equals("Nabeul")) {
                nom_stationValid = NabeulSuggestions.contains(nom_station);
            } else if (ville.equals("Sfax")) {
                nom_stationValid = SfaxSuggestions.contains(nom_station);
            } else if (ville.equals("Sidi Bou Zid")) {
                nom_stationValid = Sidi_Bou_ZidSuggestions.contains(nom_station);
            } else if (ville.equals("Siliana")) {
                nom_stationValid = SilianaSuggestions.contains(nom_station);
            } else if (ville.equals("Sousse")) {
                nom_stationValid = SousseSuggestions.contains(nom_station);
            } else if (ville.equals("Tataouine")) {
                nom_stationValid = TataouineSuggestions.contains(nom_station);
            } else if (ville.equals("Tozeur")) {
                nom_stationValid = TozeurSuggestions.contains(nom_station);
            } else if (ville.equals("Tunis")) {
                nom_stationValid = TunisSuggestions.contains(nom_station);
            } else if (ville.equals("Zaghouan")) {
                nom_stationValid = ZaghouanSuggestions.contains(nom_station);
            }

            // Show an alert if either nom or prenom is invalid
            if (!villeValid || !nom_stationValid) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a suggestion from the list for both ville and nom de station .");
                alert.showAndWait();
                return; // Don't continue with adding the enterprise if the input is invalid
            }

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
        AutoComplete();

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

        // Check that type only contain alphabetic characters and spaces
        if (!ville.matches("^[a-zA-Z ]+$")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid fields");
            alert.setHeaderText("TYPE must contain ONLY alphabetic characters and spaces");
            alert.showAndWait();
            return;
        }
         if (!nom_station.matches("^[a-zA-Z ]+$")) {
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

         // Check that type only contain alphabetic characters and spaces
        if (!ville.matches("^[a-zA-Z ]+$")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid fields");
            alert.setHeaderText("TYPE must contain ONLY alphabetic characters and spaces");
            alert.showAndWait();
            return;
        }
        if (!nom_station.matches("^[a-zA-Z ]+$")) {
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

    public void AutoComplete() {
        // Bind auto-completion for txtNomadmin
        villeAutoCompletionBinding = TextFields.bindAutoCompletion(txtville, "Ariana", "Beja", "Ben Arous", "Bizerte", "Gabes", "Gafsa", "Jendouba", "Kairouan", "Kasserine", "Kebili", "Kef", "Mahdia", "Manouba", "Medenine", "Monastir", "Nabeul", "Sfax", "Sidi Bou Zid", "Siliana", "Sousse", "Tataouine", "Tozeur", "Tunis", "Zaghouan");

        // Bind auto-completion for txtPrenomadmin
        txtville.textProperty().addListener((observable, oldValue, newValue) -> {
            // Clear previous suggestions
            if (stationAutoCompletionBinding != null) {
                stationAutoCompletionBinding.dispose();
            }

            // Determine which set of suggestions to use based on the selected nom
            Set<String> suggestions;
            if (newValue.equals("Ariana")) {
                suggestions = ArienaSuggestions;
            } else if (newValue.equals("Beja")) {
                suggestions = BejaSuggestions;
            } else if (newValue.equals("Ben Arous")) {
                suggestions = Ben_ArousSuggestions;
            } else if (newValue.equals("Bizerte")) {
                suggestions = BizerteSuggestions;
            } else if (newValue.equals("Gabes")) {
                suggestions = GabesSuggestions;
            } else if (newValue.equals("Gafsa")) {
                suggestions = GafsaSuggestions;
            } else if (newValue.equals("Jendouba")) {
                suggestions = JendoubaSuggestions;
            } else if (newValue.equals("Kairouan")) {
                suggestions = KairouanSuggestions;
            } else if (newValue.equals("Kasserine")) {
                suggestions = KasserineSuggestions;
            } else if (newValue.equals("Kebili")) {
                suggestions = KebiliSuggestions;
            } else if (newValue.equals("Kef")) {
                suggestions = KefSuggestions;
            } else if (newValue.equals("Mahdia")) {
                suggestions = MahdiaSuggestions;
            } else if (newValue.equals("Manouba")) {
                suggestions = ManoubaSuggestions;
            } else if (newValue.equals("Medenine")) {
                suggestions = MedenineSuggestions;
            } else if (newValue.equals("Monastir")) {
                suggestions = MonastirSuggestions;
            } else if (newValue.equals("Nabeul")) {
                suggestions = NabeulSuggestions;
            } else if (newValue.equals("Sfax")) {
                suggestions = SfaxSuggestions;
            } else if (newValue.equals("Sidi Bou Zid")) {
                suggestions = Sidi_Bou_ZidSuggestions;
            } else if (newValue.equals("Siliana")) {
                suggestions = SilianaSuggestions;
            } else if (newValue.equals("Sousse")) {
                suggestions = SousseSuggestions;
            } else if (newValue.equals("Tataouine")) {
                suggestions = TataouineSuggestions;
            } else if (newValue.equals("Tozeur")) {
                suggestions = TozeurSuggestions;
            } else if (newValue.equals("Tunis")) {
                suggestions = TunisSuggestions;
            } else if (newValue.equals("Zaghouan")) {
                suggestions = ZaghouanSuggestions;
            } else {
                // No suggestions for the selected nom
                suggestions = new HashSet<>();
            }

            // Bind auto-completion for txtPrenomadmin using the appropriate set of suggestions
            if (txtnom_station != null) {
                stationAutoCompletionBinding = TextFields.bindAutoCompletion(txtnom_station, suggestions.toArray(new String[0]));

                // Show an alert if the user doesn't select a suggestion
                stationAutoCompletionBinding.setOnAutoCompleted(event -> {
                    if (!suggestions.contains(event.getCompletion())) {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a suggestion from the list.");
                        alert.showAndWait();
                    }
                });
            }
        });
    }

}
