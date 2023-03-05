/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.lowagie.text.Document;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import edu.swiftride.entities.Accident;
import edu.swiftride.entities.Voiture;

import edu.swiftride.gui.StatistiqueController;
import edu.swiftride.services.AccidentCRUD;
import edu.swiftride.services.VoitureCRUD;
import edu.swiftride.utils.MyConnection;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author user
 */
public class GestionVoitureInterfaceController implements Initializable {

    @FXML
    private JFXButton home_btn;
    @FXML
    private JFXButton profile_btn;
    @FXML
    private JFXButton availableCars_btn;
    @FXML
    private JFXButton transport_btn;
    @FXML
    private JFXButton organisation_btn;
    @FXML
    private JFXButton reservation_btn;
    @FXML
    private JFXButton maintenance_btn;
    @FXML
    private JFXButton client_btn;
    @FXML
    private JFXButton accident_btn;
    @FXML
    private Button minimize;
    @FXML
    private Button close;
    @FXML
    private AnchorPane availableCars_form;
    @FXML
    private TextField availableCars_carId;
    @FXML
    private ComboBox<String> availableCars_status;
    @FXML
    private Button availableCars_updateBtn;
    @FXML
    private Button availableCars_clearBtn;
    @FXML
    private Button availableCars_deleteBtn;
    @FXML
    private Button availableCars_insertBtn;
    @FXML
    private TextField availableCars_price;
    @FXML
    private ComboBox<String> availableCars_model;
    @FXML
    private TextField matricule;
    @FXML
    private TextField coueur;
    @FXML
    private JFXDatePicker date;
    @FXML
    private JFXComboBox<Integer> utilsateur;
    @FXML
    private JFXComboBox<Integer> entreprise;
    @FXML
    private ComboBox<String> technique;
    @FXML
    private JFXTextField availableCars_brand;
    @FXML
    private Button speaker;
    @FXML
    private Button import_btn;
    @FXML
    private ImageView imageview;
    @FXML
    private JFXTextField kilometra;
    @FXML
    private TableView<Voiture> availableCars_tableView;
    @FXML
    private TableColumn availableCars_col_carId;
    @FXML
    private TableColumn availableCars_col_brand;
    @FXML
    private TableColumn availableCars_col_model;
    @FXML
    private TableColumn availableCars_col_price;
    @FXML
    private TableColumn availableCars_col_Etattechnique;
    @FXML
    private TableColumn availableCars_col_status;
    @FXML
    private TableColumn availableCars_col_matricule;
    @FXML
    private TableColumn availableCars_col_date;
    @FXML
    private TableColumn availableCars_col_identreprise;
    @FXML
    private TableColumn availableCars_col_idutilsateur;
    @FXML
    private TableColumn availableCars_col_coleur;
    @FXML
    private TableColumn availableCars_col_id;
    @FXML
    private TableColumn availableCars_co_kilo;
    @FXML
    private TableColumn availableCars_col_image;
    @FXML
    private TextField availableCars_search;
    @FXML
    private JFXButton stats_btn;
    @FXML
    private Button refresh;
    @FXML
    private JFXButton pdf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        availableCarShowListData();
       availableCarmarqueList();
       setCellValueFromTableToText();
       availableCarStatusList();
       
       availableCarSearch();
       refreshcars();
       exportToPdf();
       
       utilId();
       entrepriseId();
       etattechnique();
       autocomple();
       statistique();
    }    

   ObservableList<String> items = FXCollections.observableArrayList(" Suzuki Alto", "Suzuki Baleno", "Suzuki Baleno kombi", "Suzuki Grand Vitara", "Suzuki Grand Vitara XL-7", "Suzuki Ignis", " Suzuki Jimny", 
     "Suzuki Kizashi", " Suzuki Liana", "Suzuki Samurai",  "Suzuki Splash", "Suzuki Swift", "Suzuki SX4", "Suzuki SX4 Sedan", "Suzuki Vitara", "Suzuki Wagon R+"," Seat Alhambra", "Seat Altea", "Seat Altea XL", "Seat Arosa", "Seat Cordoba", "Seat Cordoba Vario", "Seat Exeo", "Seat Ibiza", "Seat Ibiza ST", "Seat Exeo ST", "Seat Leon", "Seat Leon ST", "Seat Inca", "Seat Mii", "Seat Toledo",
"Renault Captur", "Renault Clio", "Renault Clio Grandtour", "Renault Espace", "Renault Express", "Renault Fluence", "Renault Grand Espace", "Renault Grand Modus", "Renault Grand Scenic", "Renault Kadjar", "Renault Kangoo", "Renault Kangoo Express", "Renault Koleos", "Renault Laguna", "Renault Laguna Grandtour", "Renault Latitude", "Renault Mascott", "Renault Mégane", "Renault Mégane CC", "Renault Mégane Combi", "Renault Mégane Grandtour", "Renault Mégane Coupé", "Renault Mégane Scénic", "Renault Scénic", "Renault Talisman", "Renault Talisman Grandtour", "Renault Thalia", "Renault Twingo", "Renault Wind", "Renault Zoé",
"Peugeot 1007", "Peugeot 107", "Peugeot 106", "Peugeot 108", "Peugeot 2008", "Peugeot 205", "Peugeot 205 Cabrio", "Peugeot 206", "Peugeot 206 CC", "Peugeot 206 SW", "Peugeot 207", "Peugeot 207 CC", "Peugeot 207 SW", "Peugeot 306", "Peugeot 307", "Peugeot 307 CC", "Peugeot 307 SW", "Peugeot 308", "Peugeot 308 CC", "Peugeot 308 SW", "Peugeot 309", "Peugeot 4007", "Peugeot 4008", "Peugeot 405", "Peugeot 406", "Peugeot 407", "Peugeot 407 SW", "Peugeot 5008", "Peugeot 508", "Peugeot 508 SW", "Peugeot 605", "Peugeot 806", "Peugeot 607", "Peugeot 807", "Peugeot Bipper", "Peugeot RCZ",
"Dacia Dokker", "Dacia Duster", "Dacia Lodgy", "Dacia Logan", "Dacia Logan MCV", "Dacia Logan Van", "Dacia Sandero", "Dacia Solenza","Citroën Berlingo", "Citroën C-Crosser", "Citroën C-Elissée", "Citroën C-Zero", "Citroën C1", "Citroën C2", "Citroën C3", "Citroën C3 Picasso", "Citroën C4", "Citroën C4 Aircross", "Citroën C4 Cactus", "Citroën C4 Coupé", "Citroën C4 Grand Picasso", "Citroën C4 Sedan", "Citroën C5", "Citroën C5 Break", "Citroën C5 Tourer", "Citroën C6", "Citroën C8", "Citroën DS3", "Citroën DS4", "Citroën DS5", "Citroën Evasion", "Citroën Jumper", "Citroën Jumpy", "Citroën Saxo", "Citroën Nemo", "Citroën Xantia", "Citroën Xsara",
"Opel Agila", "Opel Ampera", "Opel Antara", "Opel Astra", "Opel Astra cabrio", "Opel Astra caravan", "Opel Astra coupé", "Opel Calibra", "Opel Campo", "Opel Cascada", "Opel Corsa", "Opel Frontera", "Opel Insignia", "Opel Insignia kombi", "Opel Kadett", "Opel Meriva", "Opel Mokka", "Opel Movano", "Opel Omega", "Opel Signum", "Opel Vectra", "Opel Vectra Caravan", "Opel Vivaro", "Opel Vivaro Kombi", "Opel Zafira",
"Alfa Romeo 145", "Alfa Romeo 146", "Alfa Romeo 147", "Alfa Romeo 155", "Alfa Romeo 156", "Alfa Romeo 156 Sportwagon", "Alfa Romeo 159", "Alfa Romeo 159 Sportwagon", "Alfa Romeo 164", "Alfa Romeo 166", "Alfa Romeo 4C", "Alfa Romeo Brera", "Alfa Romeo GTV", "Alfa Romeo MiTo", "Alfa Romeo Crosswagon", "Alfa Romeo Spider", "Alfa Romeo GT", "Alfa Romeo Giulietta", "Alfa Romeo Giulia",
"Škoda Favorit", "Škoda Felicia", "Škoda Citigo", "Škoda Fabia", "Škoda Fabia Combi", "Škoda Fabia Sedan", "Škoda Felicia Combi", "Škoda Octavia", "Škoda Octavia Combi", "Škoda Roomster", "Škoda Yeti", "Škoda Rapid", "Škoda Rapid Spaceback", "Škoda Superb", "Škoda Superb Combi",
"Chevrolet Alero", "Chevrolet Aveo", "Chevrolet Camaro", "Chevrolet Captiva", "Chevrolet Corvette", "Chevrolet Cruze", "Chevrolet Cruze SW", "Chevrolet Epica", "Chevrolet Equinox", "Chevrolet Evanda", "Chevrolet HHR", "Chevrolet Kalos", "Chevrolet Lacetti", "Chevrolet Lacetti SW", "Chevrolet Lumina", "Chevrolet Malibu", "Chevrolet Matiz", "Chevrolet Monte Carlo", "Chevrolet Nubira", "Chevrolet Orlando", "Chevrolet Spark", "Chevrolet Suburban", "Chevrolet Tacuma", "Chevrolet Tahoe", "Chevrolet Trax",
"Porsche 911 Carrera", "Porsche 911 Carrera Cabrio", "Porsche 911 Targa", "Porsche 911 Turbo", "Porsche 924", "Porsche 944", "Porsche 997", "Porsche Boxster", "Porsche Cayenne", "Porsche Cayman", "Porsche Macan", "Porsche Panamera",
"Honda Accord", "Honda Accord Coupé", "Honda Accord Tourer", "Honda City", "Honda Civic", "Honda Civic Aerodeck", "Honda Civic Coupé", "Honda Civic Tourer", "Honda Civic Type R", "Honda CR-V", "Honda CR-X", "Honda CR-Z", "Honda FR-V", "Honda HR-V", "Honda Insight", "Honda Integra", "Honda Jazz", "Honda Legend", "Honda Prelude",
"Subaru BRZ", "Subaru Forester", "Subaru Impreza", "Subaru Impreza Wagon", "Subaru Justy", "Subaru Legacy", "Subaru Legacy Wagon", "Subaru Legacy Outback", "Subaru Levorg", "Subaru Outback", "Subaru SVX", "Subaru Tribeca", "Subaru Tribeca B9", "Subaru XV",
"Mazda 121", "Mazda 2", "Mazda 3", "Mazda 323", "Mazda 323 Combi", "Mazda 323 Coupé", "Mazda 323 F", "Mazda 5", "Mazda 6", "Mazda 6 Combi", "Mazda 626", "Mazda 626 Combi", "Mazda B-Fighter", "Mazda B2500", "Mazda BT", "Mazda CX-3", "Mazda CX-5", "Mazda CX-7", "Mazda CX-9", "Mazda Demio", "Mazda MPV", "Mazda MX-3", "Mazda MX-5", "Mazda MX-6", "Mazda Premacy", "Mazda RX-7", "Mazda RX-8", "Mazda Xedox 6",
"Mitsubishi 3000 GT", "Mitsubishi ASX", "Mitsubishi Carisma", "Mitsubishi Colt", "Mitsubishi Colt CC", "Mitsubishi Eclipse", "Mitsubishi Fuso canter", "Mitsubishi Galant", "Mitsubishi Galant Combi", "Mitsubishi Grandis", "Mitsubishi L200", "Mitsubishi L200 Pick up", "Mitsubishi L200 Pick up Allrad", "Mitsubishi L300", "Mitsubishi Lancer", "Mitsubishi Lancer Combi", "Mitsubishi Lancer Evo", "Mitsubishi Lancer Sportback", "Mitsubishi Outlander", "Mitsubishi Pajero", "Mitsubishi Pajeto Pinin", "Mitsubishi Pajero Pinin Wagon", "Mitsubishi Pajero Sport", "Mitsubishi Pajero Wagon", "Mitsubishi Space Star",
"Lexus CT", "Lexus GS", "Lexus GS 300", "Lexus GX", "Lexus IS", "Lexus IS 200", "LexusIS 250 C", "Lexus IS-F", "Lexus LS", "Lexus LX", "Lexus NX", "Lexus RC F", "Lexus RX", "Lexus RX 300", "Lexus RX 400h", "Lexus RX 450h", "Lexus SC 430",
"Toyota 4-Runner", "Toyota Auris", "Toyota Avensis", "Toyota Avensis Combi", "Toyota Avensis Van Verso", "Toyota Aygo", "Toyota Camry", "Toyota Carina", "Toyota Celica", "Toyota Corolla", "Toyota Corolla Combi", "Toyota Corolla sedan", "Toyota Corolla Verso", "Toyota FJ Cruiser", "Toyota GT86", "Toyota Hiace", "Toyota Hiace Van", "Toyota Highlander", "Toyota Hilux", "Toyota Land Cruiser", "Toyota MR2", "Toyota Paseo", "Toyota Picnic", "Toyota Prius", "Toyota RAV4", "Toyota Sequoia", "Toyota Starlet", "Toyota Supra", "Toyota Tundra", "Toyota Urban Cruiser", "Toyota Verso", "Toyota Yaris", "Toyota Yaris Verso",
"BMW i3", "BMW i8", "BMW M3", "BMW M4", "BMW M5", "BMW M6", "BMW Rad 1", "BMW Rad 1 Cabrio", "BMW Rad 1 Coupé", "BMW Rad 2", "BMW Rad 2 Active Tourer", "BMW Rad 2 Coupé", "BMW Rad 2 Gran Tourer", "BMW Rad 3", "BMW Rad 3 Cabrio", "BMW Rad 3 Compact", "BMW Rad 3 Coupé", "BMW Rad 3 GT", "BMW Rad 3 Touring", "BMW Rad 4", "BMW Rad 4 Cabrio", "BMW Rad 4 Gran Coupé", "BMW Rad 5", "BMW Rad 5 GT", "BMW Rad 5 Touring", "BMW Rad 6", "BMW Rad 6 Cabrio", "BMW Rad 6 Coupé", "BMW Rad 6 Gran Coupé", "BMW Rad 7", "BMW Rad 8 Coupé", "BMW X1", "BMW X3", "BMW X4", "BMW X5", "BMW X6", "BMW Z3", "BMW Z3 Coupé", "BMW Z3 Roadster", "BMW Z4", "BMW Z4 Roadster",
"Volkswagen Amarok", "Volkswagen Beetle", "Volkswagen Bora", "Volkswagen Bora Variant", "Volkswagen Caddy", "Volkswagen Caddy Van", "Volkswagen Life", "Volkswagen California", "Volkswagen Caravelle", "Volkswagen CC", "Volkswagen Crafter", "Volkswagen Crafter Van", "Volkswagen Crafter Kombi", "Volkswagen CrossTouran", "Volkswagen Eos", "Volkswagen Fox", "Volkswagen Golf", "Volkswagen Golf Cabrio", "Volkswagen Golf Plus", "Volkswagen Golf Sportvan", "Volkswagen Golf Variant", "Volkswagen Jetta", "Volkswagen LT", "Volkswagen Lupo", "Volkswagen Multivan", "Volkswagen New Beetle", "Volkswagen New Beetle Cabrio", "Volkswagen Passat", "Volkswagen Passat Alltrack", "Volkswagen Passat CC", "Volkswagen Passat Variant", "Volkswagen Passat Variant Van", "Volkswagen Phaeton", "Volkswagen Polo", "Volkswagen Polo Van", "Volkswagen Polo Variant", "Volkswagen Scirocco", "Volkswagen Sharan", "Volkswagen T4", "Volkswagen T4 Caravelle", "Volkswagen T4 Multivan", "Volkswagen T5", "Volkswagen T5 Caravelle", "Volkswagen T5 Multivan", "Volkswagen T5 Transporter Shuttle", "Volkswagen Tiguan", "Volkswagen Touareg", "Volkswagen Touran",
"Mercedes-Benz 100 D", "Mercedes-Benz 115", "Mercedes-Benz 124", "Mercedes-Benz 126", "Mercedes-Benz 190", "Mercedes-Benz 190 D", "Mercedes-Benz 190 E", "Mercedes-Benz 200 - 300", "Mercedes-Benz 200 D", "Mercedes-Benz 200 E", "Mercedes-Benz 210 Van", "Mercedes-Benz 210 kombi", "Mercedes-Benz 310 Van", "Mercedes-Benz 310 kombi", "Mercedes-Benz 230 - 300 CE Coupé", "Mercedes-Benz 260 - 560 SE", "Mercedes-Benz 260 - 560 SEL", "Mercedes-Benz 500 - 600 SEC Coupé", "Mercedes-Benz Trieda A", "Mercedes-Benz A", "Mercedes-Benz A L", "Mercedes-Benz AMG GT", "Mercedes-Benz Trieda B", "Mercedes-Benz Trieda C", "Mercedes-Benz C", "Mercedes-Benz C Sportcoupé", "Mercedes-Benz C T", "Mercedes-Benz Citan", "Mercedes-Benz CL", "Mercedes-Benz CL", "Mercedes-Benz CLA", "Mercedes-Benz CLC", "Mercedes-Benz CLK Cabrio", "Mercedes-Benz CLK Coupé", "Mercedes-Benz CLS", "Mercedes-Benz Trieda E", "Mercedes-Benz E", "Mercedes-Benz E Cabrio", "Mercedes-Benz E Coupé", "Mercedes-Benz E T", "Mercedes-Benz Trieda G", "Mercedes-Benz G Cabrio", "Mercedes-Benz GL", "Mercedes-Benz GLA", "Mercedes-Benz GLC", "Mercedes-Benz GLE", "Mercedes-Benz GLK", "Mercedes-Benz Trieda M", "Mercedes-Benz MB 100", "Mercedes-Benz Trieda R", "Mercedes-Benz Trieda S", "Mercedes-Benz S", "Mercedes-Benz S Coupé", "Mercedes-Benz SL", "Mercedes-Benz SLC", "Mercedes-Benz SLK", "Mercedes-Benz SLR", "Mercedes-Benz Sprinter",
"Saab 9-3", "Saab 9-3 Cabriolet", "Saab 9-3 Coupé", "Saab 9-3 SportCombi", "Saab 9-5", "Saab 9-5 SportCombi", "Saab 900", "Saab 900 C", "Saab 900 C Turbo", "Saab 9000",
"Audi 100", "Audi 100 Avant", "Audi 80", "Audi 80 Avant", "Audi 80 Cabrio", "Audi 90", "Audi A1", "Audi A2", "Audi A3", "Audi A3 Cabriolet", "Audi A3 Limuzina", "Audi A3 Sportback", "Audi A4", "Audi A4 Allroad", "Audi A4 Avant", "Audi A4 Cabriolet", "Audi A5", "Audi A5 Cabriolet", "Audi A5 Sportback", "Audi A6", "Audi A6 Allroad", "Audi A6 Avant", "Audi A7", "Audi A8", "Audi A8 Long", "Audi Q3", "Audi Q5", "Audi Q7", "Audi R8", "Audi RS4 Cabriolet", "Audi RS4/RS4 Avant", "Audi RS5", "Audi RS6 Avant", "Audi RS7", "Audi S3/S3 Sportback", "Audi S4 Cabriolet", "Audi S4/S4 Avant", "Audi S5/S5 Cabriolet", "Audi S6/RS6", "Audi S7", "Audi S8", "Audi SQ5", "Audi TT Coupé", "Audi TT Roadster", "Audi TTS",
"Kia Avella", "Kia Besta", "Kia Carens", "Kia Carnival", "Kia Cee`d", "Kia Cee`d SW", "Kia Cerato", "Kia K 2500", "Kia Magentis", "Kia Opirus", "Kia Optima", "Kia Picanto", "Kia Pregio", "Kia Pride", "Kia Pro Cee`d", "Kia Rio", "Kia Rio Combi", "Kia Rio sedan", "Kia Sephia", "Kia Shuma", "Kia Sorento", "Kia Soul", "Kia Sportage", "Kia Venga",
"Land Rover 109", "Land Rover Defender", "Land Rover Discovery", "Land Rover Discovery Sport", "Land Rover Freelander", "Land Rover Range Rover", "Land Rover Range Rover Evoque", "Land Rover Range Rover Sport",
"Dodge Avenger", "Dodge Caliber", "Dodge Challenger", "Dodge Charger", "Dodge Grand Caravan", "Dodge Journey", "Dodge Magnum", "Dodge Nitro", "Dodge RAM", "Dodge Stealth", "Dodge Viper",
"Chrysler 300 C", "Chrysler 300 C Touring", "Chrysler 300 M", "Chrysler Crossfire", "Chrysler Grand Voyager", "Chrysler LHS", "Chrysler Neon", "Chrysler Pacifica", "Chrysler Plymouth", "Chrysler PT Cruiser", "Chrysler Sebring", "Chrysler Sebring Convertible", "Chrysler Stratus", "Chrysler Stratus Cabrio", "Chrysler Town & Country", "Chrysler Voyager",
"Ford Aerostar", "Ford B-Max", "Ford C-Max", "Ford Cortina", "Ford Cougar", "Ford Edge", "Ford Escort", "Ford Escort Cabrio", "Ford Escort kombi", "Ford Explorer", "Ford F-150", "Ford F-250", "Ford Fiesta", "Ford Focus", "Ford Focus C-Max", "Ford Focus CC", "Ford Focus kombi", "Ford Fusion", "Ford Galaxy", "Ford Grand C-Max", "Ford Ka", "Ford Kuga", "Ford Maverick", "Ford Mondeo", "Ford Mondeo Combi", "Ford Mustang", "Ford Orion", "Ford Puma", "Ford Ranger", "Ford S-Max", "Ford Sierra", "Ford Street Ka", "Ford Tourneo Ford Connect", "Ford Tourneo Custom", "Ford Transit", "Ford Transit", "Ford Transit Bus", "Ford Transit Connect LWB", "Ford Transit Courier", "Ford Transit Custom", "Ford Transit kombi", "Ford Transit Tourneo", "Ford Transit Valnik", "Ford Transit Van", "Ford Transit Van 350", "Ford Windstar",
"Hummer H2", "Hummer H3","Hyundai Accent", "Hyundai Atos", "Hyundai Atos Prime", "Hyundai Coupé", "Hyundai Elantra", "Hyundai Galloper", "Hyundai Genesis", "Hyundai Getz", "Hyundai Grandeur", "Hyundai H 350", "Hyundai H1", "Hyundai H1 Bus", "Hyundai H1 Van", "Hyundai H200", "Hyundai i10", "Hyundai i20", "Hyundai i30", "Hyundai i30 CW", "Hyundai i40", "Hyundai i40 CW", "Hyundai ix20", "Hyundai ix35", "Hyundai ix55", "Hyundai Lantra", "Hyundai Matrix", "Hyundai Santa Fe", "Hyundai Sonata", "Hyundai Terracan", "Hyundai Trajet", "Hyundai Tucson", "Hyundai Veloster","Infiniti EX", "Infiniti FX", "Infiniti G", "Infiniti G Coupé", "Infiniti M", "Infiniti Q", "Infiniti QX",
"Jaguar Daimler", "Jaguar F-Pace", "Jaguar F-Type", "Jaguar S-Type", "Jaguar Sovereign", "Jaguar X-Type", "Jaguar X-type Estate", "Jaguar XE", "Jaguar XF", "Jaguar XJ", "Jaguar XJ12", "Jaguar XJ6", "Jaguar XJ8", "Jaguar XJ8", "Jaguar XJR", "Jaguar XK", "Jaguar XK8 Convertible", "Jaguar XKR", "Jaguar XKR Convertible",
"Jeep Cherokee", "Jeep Commander", "Jeep Compass", "Jeep Grand Cherokee", "Jeep Patriot", "Jeep Renegade", "Jeep Wrangler",
"Nissan 100 NX", "Nissan 200 SX", "Nissan 350 Z", "Nissan 350 Z Roadster", "Nissan 370 Z", "Nissan Almera", "Nissan Almera Tino", "Nissan Cabstar E - T", "Nissan Cabstar TL2 Valnik", "Nissan e-NV200", "Nissan GT-R", "Nissan Insterstar", "Nissan Juke", "Nissan King Cab", "Nissan Leaf", "Nissan Maxima", "Nissan Maxima QX", "Nissan Micra", "Nissan Murano", "Nissan Navara", "Nissan Note", "Nissan NP300 Pickup", "Nissan NV200", "Nissan NV400", "Nissan Pathfinder", "Nissan Patrol", "Nissan Patrol GR", "Nissan Pickup", "Nissan Pixo", "Nissan Primastar", "Nissan Primastar Combi", "Nissan Primera", "Nissan Primera Combi", "Nissan Pulsar", "Nissan Qashqai", "Nissan Serena", "Nissan Sunny", "Nissan Terrano", "Nissan Tiida", "Nissan Trade", "Nissan Vanette Cargo", "Nissan X-Trail",
"Volvo 240", "Volvo 340", "Volvo 360", "Volvo 460", "Volvo 850", "Volvo 850 kombi", "Volvo C30", "Volvo C70", "Volvo C70 Cabrio", "Volvo C70 Coupé", "Volvo S40", "Volvo S60", "Volvo S70", "Volvo S80", "Volvo S90", "Volvo V40", "Volvo V50", "Volvo V60", "Volvo V70", "Volvo V90", "Volvo XC60", "Volvo XC70", "Volvo XC90",
"Daewoo Espero", "Daewoo Kalos", "Daewoo Lacetti", "Daewoo Lanos", "Daewoo Leganza", "Daewoo Lublin", "Daewoo Matiz", "Daewoo Nexia", "Daewoo Nubira", "Daewoo Nubira kombi", "Daewoo Racer", "Daewoo Tacuma", "Daewoo Tico",
"Fiat 1100", "Fiat 126", "Fiat 500", "Fiat 500L", "Fiat 500X", "Fiat 850", "Fiat Barchetta", "Fiat Brava", "Fiat Cinquecento", "Fiat Coupé", "Fiat Croma", "Fiat Doblo", "Fiat Doblo Cargo", "Fiat Doblo Cargo Combi", "Fiat Ducato", "Fiat Ducato Van", "Fiat Ducato Kombi", "Fiat Ducato Podvozok", "Fiat Florino", "Fiat Florino Combi", "Fiat Freemont", "Fiat Grande Punto", "Fiat Idea", "Fiat Linea", "Fiat Marea", "Fiat Marea Weekend", "Fiat Multipla", "Fiat Palio Weekend", "Fiat Panda", "Fiat Panda Van", "Fiat Punto", "Fiat Punto Cabriolet", "Fiat Punto Evo", "Fiat Punto Van", "Fiat Qubo", "Fiat Scudo", "Fiat Scudo Van", "Fiat Scudo Kombi", "Fiat Sedici", "Fiat Seicento", "Fiat Stilo", "Fiat Stilo Multiwagon", "Fiat Strada", "Fiat Talento", "Fiat Tipo", "Fiat Ulysse", "Fiat Uno", "Fiat X1/9",
"MINI Cooper", "MINI Cooper Cabrio", "MINI Cooper Clubman", "MINI Cooper D", "MINI Cooper D Clubman", "MINI Cooper S", "MINI Cooper S Cabrio", "MINI Cooper S Clubman", "MINI Countryman", "MINI Mini One", "MINI One D" ,
"Rover 200", "Rover 214", "Rover 218", "Rover 25", "Rover 400", "Rover 414", "Rover 416", "Rover 620", "Rover 75",
"Smart Cabrio", "Smart City-Coupé", "Smart Compact Pulse", "Smart Forfour", "Smart Fortwo cabrio", "Smart Fortwo coupé", "Smart Roadster");
 
FilteredList<String> filteredItems = new FilteredList<>(items, p -> true);
    private ContextMenu contextMenu = new ContextMenu();


    @FXML
    public void autocomple(){

    availableCars_brand.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredItems.setPredicate(item -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();
            return item.toLowerCase().startsWith(lowerCaseFilter);
        });
    });

    availableCars_brand.setContextMenu(contextMenu);

    for (String item : items) {
        MenuItem menuItem = new MenuItem(item);
        menuItem.setOnAction(event -> {
            availableCars_brand.setText(item);
            availableCars_brand.positionCaret(item.length());
            contextMenu.hide();
        });
        contextMenu.getItems().add(menuItem);
    }

    availableCars_brand.setOnMouseClicked(event -> {
        contextMenu.hide();
    });

    availableCars_brand.setOnKeyReleased(event -> {
        if (event.getCode() == KeyCode.DOWN) {
            contextMenu.getItems().get(0).fire();
        } else if (event.getCode() == KeyCode.ENTER) {
            contextMenu.hide();
        } else {
            List<String> filteredList = filteredItems.stream().collect(Collectors.toList());
            contextMenu.getItems().clear();
            for (String item : filteredList) {
                MenuItem menuItem = new MenuItem(item);
                menuItem.setOnAction(e -> {
                    availableCars_brand.setText(item);
                    availableCars_brand.positionCaret(item.length());
                    contextMenu.hide();
                });
                contextMenu.getItems().add(menuItem);
            }
            contextMenu.show(availableCars_brand, Side.BOTTOM, 0, 0);
        }
    });
}

    


  private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;
     Voiture voiture = null ; 
     VoitureCRUD  pcm =new VoitureCRUD();




    private Image image;
    public ObservableList<Voiture> availableCarListData() {

        ObservableList<Voiture> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM voiture";

        connect = MyConnection.getInstance().getCnx();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            Voiture carD;

            while (result.next()) {
                        carD = new Voiture(
                         result.getInt("id"),      
                         result.getString("carteGrise"),
                         result.getString("marque"),
                         result.getString("model"),
                         result.getString("etat"),
                         result.getString("couleur"),
                         result.getString("etat_technique"),
                        result.getString("matricule"),
                        result.getDate("date_circulation"),
                        result.getDouble("prix"),
                         result.getInt("Kilometrage"),       
                        result.getInt("id_entreprise_partenaire"),
                        result.getInt("id_utilisateur"),
                         result.getString("image"));
                        

               listData.add(carD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }
    
private ObservableList<Voiture> availableCarList;

    public void availableCarShowListData() {
        availableCarList = availableCarListData();
        
        availableCars_co_kilo.setCellValueFactory(new PropertyValueFactory<>("Kilometrage"));
        availableCars_col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
       availableCars_col_carId.setCellValueFactory(new PropertyValueFactory<>("CarteGrise"));
        availableCars_col_brand.setCellValueFactory(new PropertyValueFactory<>("marque"));
        availableCars_col_model.setCellValueFactory(new PropertyValueFactory<>("model"));
        availableCars_col_price.setCellValueFactory(new PropertyValueFactory<>("prix"));
        availableCars_col_status.setCellValueFactory(new PropertyValueFactory<>("etat"));
        availableCars_col_coleur.setCellValueFactory(new PropertyValueFactory<>("couleur"));
         availableCars_col_identreprise.setCellValueFactory(new PropertyValueFactory<>("id_entreprise_partenaire"));
         availableCars_col_idutilsateur.setCellValueFactory(new PropertyValueFactory<>("id_utilsateur"));
         availableCars_col_matricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
         availableCars_col_Etattechnique.setCellValueFactory(new PropertyValueFactory<>("etat_technique"));
         availableCars_col_date.setCellValueFactory(new PropertyValueFactory<>("date_circulation"));
         availableCars_col_image.setCellValueFactory(new PropertyValueFactory<>("image"));
        availableCars_tableView.setItems(availableCarList);
    }
    
    @FXML
    public void refreshcars() {
        availableCarList = availableCarListData();
 availableCars_co_kilo.setCellValueFactory(new PropertyValueFactory<>("Kilometrage"));
        availableCars_col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
       availableCars_col_carId.setCellValueFactory(new PropertyValueFactory<>("CarteGrise"));
        availableCars_col_brand.setCellValueFactory(new PropertyValueFactory<>("marque"));
        availableCars_col_model.setCellValueFactory(new PropertyValueFactory<>("model"));
        availableCars_col_price.setCellValueFactory(new PropertyValueFactory<>("prix"));
        availableCars_col_status.setCellValueFactory(new PropertyValueFactory<>("etat"));
        availableCars_col_coleur.setCellValueFactory(new PropertyValueFactory<>("couleur"));
         availableCars_col_identreprise.setCellValueFactory(new PropertyValueFactory<>("id_entreprise_partenaire"));
         availableCars_col_idutilsateur.setCellValueFactory(new PropertyValueFactory<>("id_utilsateur"));
         availableCars_col_matricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
         availableCars_col_Etattechnique.setCellValueFactory(new PropertyValueFactory<>("etat_technique"));
         availableCars_col_date.setCellValueFactory(new PropertyValueFactory<>("date_circulation"));
          availableCars_col_image.setCellValueFactory(new PropertyValueFactory<>("image"));
         
        availableCars_tableView.setItems(availableCarList);
    }
    
    @FXML
    public void switchForm(ActionEvent event) {

         if (event.getSource() == availableCars_btn ) {
         
            availableCars_form.setVisible(true);
            
          
            availableCarShowListData();
          
               
        }else if(event.getSource()  !=availableCars_btn) {
                availableCars_form.setVisible(false);
               
                
        }
         
    }
    @FXML
    public void supprimercar(){
        VoitureCRUD  car =new VoitureCRUD();
   //       commandeplat t = tvcommande.getSelectionModel().getSelectedItem();
        Voiture card = (Voiture) availableCars_tableView.getSelectionModel().getSelectedItem();
      //  Plat p = new Plat(c.getreference());
        car.supprimercar(card);
        availableCarShowListData();
        Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("wow");
                alert.setHeaderText(null);
                alert.setContentText("car supprimé");
                alert.showAndWait(); 
    }
    @FXML
    public void close() {
        System.exit(0);
    }
//This is a Java method that minimizes the window of a JavaFX application.

     //The minimize() method gets a reference to the Stage object representing the main window of the application using the getScene() 
        //method on a Node called main_form and casting 
        //its window to a Stage object. The setIconified(true) method is then called on the Stage object to minimize the window.
    @FXML
    public void minimize() {
        Stage stage = (Stage) availableCars_form.getScene().getWindow();
        stage.setIconified(true);
    }
      
    
  
    
    

    /*
    public void availableCarImportImage() {

        FileChooser open = new FileChooser();
        open.setTitle("Open Image File");
        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File", "*jpg", "*png"));

        File file = open.showOpenDialog(availableCars_form.getScene().getWindow());

        if (file != null) {

            getData.path = file.getAbsolutePath();

            image = new Image(file.toURI().toString(), 116, 120, false, true);
            availableCars_imageView.setImage(image);

        }

    }
*/
 
    @FXML
 public void statistique() {
          FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("statistique.fxml"));
                            try {
                                loader.load();
                            } catch (Exception ex) {
                               System.err.println(ex.getMessage());
                            }
                            
                            StatistiqueController mcc = loader.getController();
                           // mrc.setUpdate(true);
                           
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                            
                             
    }

   
    
  
    private String[] listmarque = {"Abarth",
  "Alfa Romeo",
  "Aston Martin",
  "Audi",
  "Bentley",
  "BMW",
  "Bugatti",
  "Cadillac",
  "Chevrolet",
  "Chrysler",
  "Citroën",
  "Dacia",
  "Daewoo",
  "Daihatsu",
  "Dodge",
  "Donkervoort",
  "DS",
  "Ferrari",
  "Fiat",
  "Fisker",
  "Ford",
  "Honda",
  "Hummer",
  "Hyundai",
  "Infiniti",
  "Iveco",
  "Jaguar",
  "Jeep",
  "Kia",
  "KTM",
  "Lada",
  "Lamborghini",
  "Lancia",
  "Land Rover",
  "Landwind",
  "Lexus",
  "Lotus",
  "Maserati",
  "Maybach",
  "Mazda",
  "McLaren",
  "Mercedes-Benz",
  "MG",
  "Mini",
  "Mitsubishi",
  "Morgan",
  "Nissan",
  "Opel",
  "Peugeot",
  "Porsche",
  "Renault",
  "Rolls-Royce",
  "Rover",
  "Saab",
  "Seat",
  "Skoda",
  "Smart",
  "SsangYong",
  "Subaru",
  "Suzuki",
  "Tesla",
  "Toyota",
  "Volkswagen",
  "Volvo"};
    @FXML
    public void availableCarmarqueList() {

        List<String> listM = new ArrayList<>();

        for (String data : listmarque) {
            listM.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listM);
        availableCars_model.setItems(listData);
    }
    //First, it creates an empty list called listS.

//Then, it iterates over the array listStatus, which contains the two strings "Available" and "Not Available".
//For each string in the array, it adds the string to listS.

//Next, it creates an ObservableList called listData, which is used to
    //store the status options that will be displayed in a drop-down menu in the form. It sets listData 
    //equal to the listS list, which contains the status options.
     private String[] marqueSuzuki ={"Alto", "Baleno", "Baleno kombi", "Grand Vitara", "Grand Vitara XL-7", "Ignis", "Jimny", 
     "Kizashi", "Liana", "Samurai", 
    "Splash", "Swift", "SX4", "SX4 Sedan", "Vitara", "Wagon R+"};
     

/*
     public void availableCarmodelList() {
        
        List<String> listmod = new ArrayList<>();
       
       
            for (String info : marqueSuzuki) {
            
            
            listmod.add(info);
        
        }
            
        ObservableList listMD = FXCollections.observableArrayList(listmod);
        availableCars_brand.setItems(listMD);
    }
     */
    private String[] listStatus = {"Available", "Not Available"};

    @FXML
    public void availableCarStatusList() {

        List<String> listS = new ArrayList<>();

        for (String data : listStatus) {
            listS.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listS);
        availableCars_status.setItems(listData);
    }
   
     private String[] listtech = {"En fonction", "En Panne"};

    @FXML
    public void etattechnique() {

        List<String> listS = new ArrayList<>();

        for (String data : listtech) {
            listS.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listS);
        technique.setItems(listData);
    }
    
    int id =0;
public void setCellValueFromTableToText() {
    availableCars_tableView.setOnMouseClicked((MouseEvent event) -> {
        ObservableList<Voiture> items = availableCars_tableView.getItems();
        int index = availableCars_tableView.getSelectionModel().getSelectedIndex();
        
        if (index >= 0 && index < items.size()) {
            Voiture v = items.get(index);
            availableCars_carId.setText(v.getCarteGrise());
            availableCars_brand.setText(v.getModel());
            availableCars_model.setValue(v.getMarque());
            date.setValue(v.getDate_circulation().toLocalDate());
            entreprise.setValue(+v.getId_entreprise_partenaire());
            utilsateur.setValue(v.getId_utilsateur());
            availableCars_price.setText(""+v.getPrix());
            availableCars_status.setValue(v.getEtat());
            matricule.setText(v.getMatricule());
            coueur.setText(v.getCouleur());
            kilometra.setText(""+v.getKilometrage());
            technique.setValue(v.getEtat_technique());
            id = v.getId();
            System.out.println(id);
        }
    });
}


 Integer ident;
 Integer idult;
    
 public void modifiercar() throws SQLException {
        Voiture car = new Voiture ();
       

       
         String cartg= availableCars_carId.getText();
         marque =  availableCars_brand.getText();
         model = (String) availableCars_model.getSelectionModel().getSelectedItem();
         statu =  (String) availableCars_status.getSelectionModel().getSelectedItem();
        String mat = matricule.getText();
        String ettec = technique.getSelectionModel().getSelectedItem().toString();
        String col = coueur.getText();
        
        LocalDate dt = date.getValue();
        String inputPrice = availableCars_price.getText().trim();
        Double price = Double.parseDouble(inputPrice);
        
         
          //String prix = Double.toString(Voiture.getPrix());
          ident =  utilsateur.getSelectionModel().getSelectedItem();
          idult =  entreprise.getSelectionModel().getSelectedItem();

       
       
           car.setCarteGrise(cartg);
           car.setMarque(marque);
           car.setModel(model);
           car.setEtat(statu);
           car.setMatricule(marque);
           car.setCouleur(col);
           car.setEtat_technique(ettec);
           car.setPrix(price);
           car.setId_entreprise_partenaire(ident);
           car.setId_utilsateur(idult);
           car.setDate_circulation(Date.valueOf(dt));
           car.setId(id);

        // Effectuer la modification
        
    pcm.modifier(car);
    availableCars_tableView.setItems(availableCarListData());

    // Afficher une alerte de confirmation si la modification a réussi
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Modification réussie");
    alert.setHeaderText("La modification a été effectuée avec succès.");
    alert.showAndWait();
        }
    

     

                

        
    String marque ;
        String model ;
        String statu ;
        Integer idut;
      
    @FXML
        public void insertRecord() {
  
     Voiture m=new Voiture();
       Alert alert;
             
        String  cartegrise = availableCars_carId.getText();
        
         marque =   availableCars_brand.getText();
         model = availableCars_model.getSelectionModel().getSelectedItem();
         statu =  availableCars_status.getSelectionModel().getSelectedItem();
        
        String mat = matricule.getText();
        
        String ettec = technique.getSelectionModel().getSelectedItem().toString();
        String col = coueur.getText();
        LocalDate dt =date.getValue();
        Double pricce = Double.parseDouble(availableCars_price.getText());
        Integer kilo = Integer.parseInt(kilometra.getText());
       ident =entreprise.getSelectionModel().getSelectedItem();
       idut =utilsateur.getSelectionModel().getSelectedItem();

        
        if (cartegrise.isEmpty() || model.isEmpty() || statu.isEmpty() || mat.isEmpty() || ettec.isEmpty() || col.isEmpty()  ) {
             alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
        }else{
                
         // Check that required fields are not empty
         


           m.setImage(imageUrl);
            m.setKilometrage(kilo);
            m.setCarteGrise(cartegrise);
            m.setMarque(marque);
            m.setModel(model);
            m.setEtat(statu);
            m.setMatricule(mat);
            m.setCouleur(col);
            m.setPrix(pricce);
            m.setEtat_technique(ettec);
            m.setId_entreprise_partenaire(ident);
            m.setId_utilsateur(idut);
            m.setDate_circulation(Date.valueOf(dt));
            
            pcm.ajoutercar(m);
            
            
        }
        
        }
       
      
    @FXML
    public void availableCarClear() {
        availableCars_carId.setText("");
        availableCars_brand.setText("");
        availableCars_model.getSelectionModel().clearSelection();
        availableCars_status.getSelectionModel().clearSelection();
        availableCars_price.setText("");
        technique.getSelectionModel().clearSelection();
        utilsateur.getSelectionModel().clearSelection();
        date.setValue(LocalDate.MAX);
        entreprise.getSelectionModel().clearSelection();
        

    }
    @FXML
    public void availableCarSearch() {

        FilteredList<Voiture> filter = new FilteredList<>(availableCarList, e -> true);

        availableCars_search.textProperty().addListener((Observable, oldValue, newValue) -> {

            filter.setPredicate(predicatevoiture -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (predicatevoiture.getCarteGrise().contains(searchKey)) {
                    return true;
                } else if (predicatevoiture.getMarque().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicatevoiture.getModel().toLowerCase().contains(searchKey)) {
                    return true;
                
                } else if (predicatevoiture.getEtat().toLowerCase().contains(searchKey)) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<Voiture> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(availableCars_tableView.comparatorProperty());
        availableCars_tableView.setItems(sortList);

    }
    /////accident

   
   
    
    
    @FXML
     public void utilId() {

        String sql = "SELECT * FROM  utilisateur ";

        connect = MyConnection.getInstance().getCnx();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            ObservableList listData = FXCollections.observableArrayList();

            while (result.next()) {
                listData.add(result.getInt("id"));
            }

            utilsateur.setItems(listData);

            

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @FXML
     public void entrepriseId() {

        String sql = "SELECT * FROM  entreprise_partenaire ";

        connect = MyConnection.getInstance().getCnx();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            ObservableList listData = FXCollections.observableArrayList();

            while (result.next()) {
                listData.add(result.getInt("id"));
            }

            entreprise.setItems(listData);

            

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
     
     
  
            







    @FXML
    public void exportToPdf() {
    try {
        // Create a new PDF document
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("cars.pdf"));

        // Open the document
        document.open();

        // Create a new PDF table with the same number of columns as your TableView
        PdfPTable pdfTable = new PdfPTable(availableCars_tableView.getColumns().size());

        // Add table headers
        for (TableColumn<?, ?> column : availableCars_tableView.getColumns()) {
            pdfTable.addCell(new PdfPCell(new Phrase(column.getText())));
        }

        // Add table rows
        for (Voiture car : availableCarList) {
            pdfTable.addCell(new PdfPCell(new Phrase(Integer.toString(car.getId()))));
            pdfTable.addCell(new PdfPCell(new Phrase(car.getCarteGrise())));
            pdfTable.addCell(new PdfPCell(new Phrase(car.getMarque())));
            pdfTable.addCell(new PdfPCell(new Phrase(car.getModel())));
            pdfTable.addCell(new PdfPCell(new Phrase(Double.toString(car.getPrix()))));
            pdfTable.addCell(new PdfPCell(new Phrase(car.getEtat())));
            pdfTable.addCell(new PdfPCell(new Phrase(car.getCouleur())));
            pdfTable.addCell(new PdfPCell(new Phrase(car.getId_entreprise_partenaire())));
            pdfTable.addCell(new PdfPCell(new Phrase(car.getId_utilsateur())));
            pdfTable.addCell(new PdfPCell(new Phrase(car.getMatricule())));
            pdfTable.addCell(new PdfPCell(new Phrase(car.getEtat_technique())));
            pdfTable.addCell(new PdfPCell(new Phrase(""+car.getDate_circulation())));
             pdfTable.addCell(new PdfPCell(new Phrase(car.getKilometrage())));
        }

        // Add the PDF table to the document
        document.add(pdfTable);

        // Close the document
        document.close();

        System.out.println("PDF created successfully");
    } catch (Exception e) {
        e.printStackTrace();
    }
}


   
    private Voice voice;

    @FXML
     public void speak() {
        // Synthesize speech from the text

        speaker.setOnAction(event -> {
            // Synthesize speech from the text
            voice.speak("Hello, world!");
        });
System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
System.setProperty("mbrola.base", "");
System.setProperty("freetts.audio.playback", "true");
System.setProperty("freetts.audio.playback.default", "com.sun.speech.freetts.audio.JavaClipAudioPlayer");
System.setProperty("freetts.audio.dump", "false");
System.setProperty("freetts.audio.debug", "false");
System.setProperty("freetts.lexicon.cachesize", "50000");
System.setProperty("freetts.runnable", "com.sun.speech.freetts.PooledFreeTTSEngineCentral");

System.setProperty("com.sun.speech.freetts.voice.defaultAudioPlayer", "com.sun.speech.freetts.audio.JavaClipAudioPlayer");
System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_rms.RmsVoiceDirectory");

        // Load the FreeTTS voice
System.setProperty("freetts.voice.path", "C:\\Users\\user\\Downloads\\freetts-1.2.2-bin\\freetts-1.2\\docs\\audio");
        VoiceManager voiceManager = VoiceManager.getInstance();
 voice = voiceManager.getVoice("cmu-rms-hsmm");
if (voice == null) {
    System.err.println("Requested voice not available");
    return;
}
voice.allocate();
voice.speak("Hello, world");
voice.deallocate();

     }

private String imageUrl;

    @FXML
    public void importImage(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Import Image");
    fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
    );
    File selectedFile = fileChooser.showOpenDialog(import_btn.getScene().getWindow());
    if (selectedFile != null) {
        Path directoryPath = Paths.get("C:\\Users\\user\\Desktop\\ImportedImages");
        Path outputPath = directoryPath.resolve(selectedFile.getName());
        try {
            Files.copy(selectedFile.toPath(), outputPath, StandardCopyOption.REPLACE_EXISTING);
            imageUrl = outputPath.toUri().toString();
            Image importedImage = new Image(imageUrl);
            imageview.setImage(importedImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

    @FXML
    private void availableCarSelect(MouseEvent event) {
    }
    
}