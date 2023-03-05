/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.gui;

import com.jfoenix.controls.JFXButton;
import edu.swiftride.entities.MoyenTransport;
import edu.swiftride.entities.PDF;
import edu.swiftride.services.MoyenTransportCRUD;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import static jdk.nashorn.internal.objects.NativeJava.type;
import static org.omg.CORBA.AnySeqHelper.type;

/**
 * FXML Controller class
 *
 * @author Ines
 */
public class MoyenTransportController implements Initializable {

    @FXML
    private TextField txtid;
    @FXML
    private ComboBox<String> txttype;
    @FXML
    private TextField txtnumero_trans;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button pass;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnSupprimer;
    @FXML
    private TableView<MoyenTransport> tableMoyenTransport;
    @FXML
    private TableColumn<MoyenTransport, Integer> colid;
    @FXML
    private TableColumn<MoyenTransport, String> coltype;
    @FXML
    private TableColumn<MoyenTransport, Integer> colnumero_transport;
    @FXML
    private Label label;
    @FXML
    private TextField filterField;

    private ObservableList<MoyenTransport> moyensTransportList;

    @FXML
    private void ToPdf(ActionEvent event) {
        MoyenTransport m = tableMoyenTransport.getSelectionModel().getSelectedItem();

        PDF pd = new PDF();
        try {
            pd.GeneratePdf("Moyen de transport", m, m.getId());
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    MoyenTransportCRUD pcm = new MoyenTransportCRUD();

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnAjouter) {
            insertRecord();
        } else if (event.getSource() == btnModifier) {
            updateRecord();
        } else if (event.getSource() == btnSupprimer) {
            deleteButton();
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txttype.setItems(FXCollections.observableArrayList("Bus", "train", "metro"));
        pass.setOnAction(event -> pass());
        moyensTransportList = getMoyenTransport();
        showMoyenTransport();
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            searchMoyenTransportList(newValue);
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

    public void showMoyenTransport() {
        ObservableList<MoyenTransport> list = getMoyenTransport();
        colid.setCellValueFactory(new PropertyValueFactory<MoyenTransport, Integer>("id"));
        coltype.setCellValueFactory(new PropertyValueFactory<MoyenTransport, String>("type"));
        colnumero_transport.setCellValueFactory(new PropertyValueFactory<MoyenTransport, Integer>("numero_trans"));
        tableMoyenTransport.setItems(list);
    }

    public void searchMoyenTransportList(String newValue) {
        ObservableList<MoyenTransport> filteredList = FXCollections.observableArrayList();
        if (newValue == null || newValue.isEmpty()) {
            tableMoyenTransport.setItems(moyensTransportList);
            return;
        }
        String lowerCaseFilter = newValue.toLowerCase();

        for (MoyenTransport moyenTransport : moyensTransportList) {
            if (moyenTransport.getType().toLowerCase().contains(lowerCaseFilter)
                    || String.valueOf(moyenTransport.getId()).contains(lowerCaseFilter)
                    || String.valueOf(moyenTransport.getNumero_trans()).contains(lowerCaseFilter)) {
                filteredList.add(moyenTransport);
            }
        }
        tableMoyenTransport.setItems(filteredList);
    }

    private ObservableList<MoyenTransport> getMoyenTransport() {
        ObservableList<MoyenTransport> listM = FXCollections.observableArrayList();
        Connection cnn = getConnection();
        String query = "SELECT * FROM moyen_transport";
        Statement st;
        ResultSet rs;
        try {
            st = cnn.createStatement();
            rs = st.executeQuery(query);
            MoyenTransport m;
            while (rs.next()) {
                m = new MoyenTransport(rs.getInt("id"), rs.getString("type"), rs.getInt("numero_trans"));
                listM.add(m);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listM;
    }

    public void insertRecord() {
        MoyenTransport m = new MoyenTransport();

        // m.setId(Integer.parseInt(txtid.getText()));
        String type = txttype.getValue() != null ? txttype.getValue().toString() : "";
        String numero_trans = txtnumero_trans.getText();

        // Check that required fields are not empty
        if (type.isEmpty() || numero_trans.isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Missing fields");
            alert.setHeaderText("All fields must be filled");
            alert.showAndWait();
            return;
        }

// Check that numero_trans contains ONLY numeric characters
        if (!numero_trans.matches("\\d+")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid fields");
            alert.setHeaderText("NUMERO_TRANS must contain ONLY numeric characters");
            alert.showAndWait();
            return;
        }

// Check that numero_trans is greater than zero
        if (Integer.parseInt(numero_trans) <= 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid fields");
            alert.setHeaderText("NUMERO_TRANS must be greater than 0");
            alert.showAndWait();
            return;
        }

        m.setType(txttype.getValue());
        m.setNumero_trans(Integer.parseInt(txtnumero_trans.getText()));

        pcm.ajouterMoyenTransport(m);
        tableMoyenTransport.setItems(getMoyenTransport());
        // Afficher une alerte de confirmation si la modification a réussi
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Ajout réussie");
        alert.setHeaderText("L'ajout a été effectuée avec succès.");
        alert.showAndWait();
    }

    private void updateRecord() {
        MoyenTransport m = new MoyenTransport();

        MoyenTransport moyen = tableMoyenTransport.getSelectionModel().getSelectedItem();
        if (moyen == null) {
            // Créer une alerte de type WARNING pour demander à l'utilisateur de choisir une entreprise à modifier
            Alert warning = new Alert(Alert.AlertType.WARNING);
            warning.setTitle("Attention");
            warning.setHeaderText("Vous devez sélectionner une entreprise à modifier.");
            warning.showAndWait();
            return;
        }
        m.setId(tableMoyenTransport.getSelectionModel().getSelectedItem().getId());
        m.setType(txttype.getValue());
        m.setNumero_trans(Integer.parseInt(txtnumero_trans.getText()));

        String type = txttype.getValue();
        String numero_trans = txtnumero_trans.getText();
        // Check that required fields are not empty
        if (type.isEmpty() || numero_trans.isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Missing fields");
            alert.setHeaderText("All fields must be filled");
            alert.showAndWait();
            return;
        }
        pcm.modifierMoyenTransport(m);
        tableMoyenTransport.setItems(getMoyenTransport());
        // Afficher une alerte de confirmation si la modification a réussi
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Modification réussie");
        alert.setHeaderText("La modification a été effectuée avec succès.");
        alert.showAndWait();

    }

    private void deleteButton() {
        MoyenTransport m = new MoyenTransport();
        MoyenTransport moyen = tableMoyenTransport.getSelectionModel().getSelectedItem();
        if (moyen == null) {
            // Créer une alerte de type WARNING pour demander à l'utilisateur de choisir une entreprise à supprimer
            Alert warning = new Alert(Alert.AlertType.WARNING);
            warning.setTitle("Attention");
            warning.setHeaderText("Vous devez sélectionner un moyen de trfansport à supprimer.");
            warning.showAndWait();
            return;
        }
        // Créer une alerte de type CONFIRMATION pour demander à l'utilisateur s'il veut vraiment supprimer l'entreprise
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmation de suppression");
        confirmation.setHeaderText("Êtes-vous sûr de vouloir supprimer le moyen de transport " + moyen.getId() + " ?");
        Optional<ButtonType> result = confirmation.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            pcm.supprimerMoyenTransport(moyen);
            tableMoyenTransport.getItems().removeAll(moyen);
            // Créer une alerte de type INFORMATION pour informer l'utilisateur que la suppression a réussi
            Alert success = new Alert(Alert.AlertType.INFORMATION);
            success.setTitle("Suppression réussie");
            success.setHeaderText("Le moyen de transport a été supprimée avec succès.");
            success.showAndWait();
        }
    }

    private void pass() {
        try {
            FXMLLoader Loader = new FXMLLoader(getClass().getResource("Details.fxml"));
            Parent root = Loader.load();
            StationController dc = Loader.getController();
            dc.showStation();
            txtnumero_trans.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
