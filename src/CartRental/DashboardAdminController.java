/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CartRental;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.net.URL;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author user
 */
public class DashboardAdminController implements Initializable {

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button close;

    @FXML
    private Button minimize;

    @FXML
    private Button home_btn;

    @FXML
    private Button logoutBtn;

    @FXML
    private Button rentCar_btn;

    @FXML
    private Button availableCars_btn;

    @FXML
    private Label username;
    @FXML
    private AnchorPane home_form;

    @FXML
    private Label home_availableCars;

    @FXML
    private Label home_totalIncome;

    @FXML
    private Label home_totalCustomers;

    @FXML
    private BarChart<?, ?> home_incomeChart;

    @FXML
    private LineChart<?, ?> home_customerChart;

    @FXML
    private AnchorPane availableCars_form;

    @FXML
    private TextField availableCars_carId;

    @FXML
    private TextField availableCars_brand;

    @FXML
    private TextField availableCars_model;

    @FXML
    private ComboBox<?> availableCars_status;

    @FXML
    private Button availableCars_importBtn;

    @FXML
    private ImageView availableCars_imageView;

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
    private TableView<CarData> availableCars_tableView;

    @FXML
    private TableColumn<CarData, String> availableCars_col_carId;

    @FXML
    private TableColumn<CarData, String> availableCars_col_brand;

    @FXML
    private TableColumn<CarData, String> availableCars_col_model;

    @FXML
    private TableColumn<CarData, String> availableCars_col_price;

    @FXML
    private TableColumn<CarData, String> availableCars_col_status;

    @FXML
    private TextField availableCars_search;

    @FXML
    private AnchorPane rent_form;

    @FXML
    private ComboBox<?> rent_carId;

    @FXML
    private ComboBox<?> rent_brand;

    @FXML
    private ComboBox<?> rent_model;

    @FXML
    private TextField rent_firstName;

    @FXML
    private TextField rent_lastName;

    @FXML
    private ComboBox<?> rent_gender;

    @FXML
    private DatePicker rent_dateRented;

    @FXML
    private DatePicker rent_dateReturn;

    @FXML
    private Button rentBtn;

    @FXML
    private Label rent_balance;

    @FXML
    private Label rent_total;

    @FXML
    private TextField rent_amount;

    @FXML
    private TableView<?> rent_tableView;

    @FXML
    private TableColumn<?, ?> rent_col_carId;

    @FXML
    private TableColumn<?, ?> rent_col_brand;

    @FXML
    private TableColumn<?, ?> rent_col_model;

    @FXML
    private TableColumn<?, ?> rent_col_price;

    @FXML
    private TableColumn<?, ?> rent_col_status;
    
    //    DATABASE TOOLS
    //connect is a Connection object used to connect to a database.
    //connect is a Connection object used to connect to a database.
    //If you want to execute a Statement object many times, it usually reduces execution time to use a PreparedStatement object instead.
    //result is a ResultSet object used to hold the results of a SQL query.
    
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;

    private Image image;
    
    //Overall, this method is useful for retrieving a list of cars from a database and displaying them in a UI using an ObservableList.

     public ObservableList<CarData> availableCarListData() {

        ObservableList<CarData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM car";

        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
//
            CarData carD;
//t then iterates over the result set, creates a CarData object for each row, and adds it to the ObservableList.
            while (result.next()) {
                carD = new CarData(result.getInt("car_id"),
                         result.getString("brand"),
                         result.getString("model"),
                         result.getDouble("price"),
                         result.getString("status"),
                         result.getString("image"),
                         result.getDate("date"));

                listData.add(carD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }//This is a Java method that sets up a TableView to display data from an ObservableList<CarData>.
    //This method is useful for setting up a TableView 
     //to display a list of available cars, and can be called in a UI controller class to populate the table with data.
     
 //The method starts by calling the availableCarListData() method to retrieve an ObservableList<CarData> 
     //of available cars. It then sets the availableCarList field to reference this list.    
private ObservableList<CarData> availableCarList;

    public void availableCarShowListData() {
        availableCarList = availableCarListData();

        availableCars_col_carId.setCellValueFactory(new PropertyValueFactory<>("carId"));
        availableCars_col_brand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        availableCars_col_model.setCellValueFactory(new PropertyValueFactory<>("model"));
        availableCars_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        availableCars_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        availableCars_tableView.setItems(availableCarList);
    }
//This is a Java method that calls System.exit(0) to terminate the running Java Virtual Machine (JVM) and close the application.

//The close() method is typically used as an event handler for a "close" or "exit" button in a graphical user 
//interface (GUI) application. When the user clicks the button, this method is called to shut down the application.
public void close() {
        System.exit(0);
    }
//This is a Java method that minimizes the window of a JavaFX application.

     //The minimize() method gets a reference to the Stage object representing the main window of the application using the getScene() 
        //method on a Node called main_form and casting 
        //its window to a Stage object. The setIconified(true) method is then called on the Stage object to minimize the window.
    public void minimize() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }
    //This is a JavaFX event handler method that switches the visible form in a GUI application based on which button was clicked.


    //The switchForm() method takes an ActionEvent parameter, which is passed automatically by the JavaFX framework when a button is clicked. 
       // The method checks which button was clicked by using the event.getSource() method, which returns the object that fired the event.
    //if the home_btn is clicked, it sets the visibility of the home_form to true and the visibility of the other forms to false
    //Overall, this method allows the user to switch between different views or forms in the application by clicking on buttons in the GUI.
    public void switchForm(ActionEvent event) {

        if (event.getSource() == home_btn) {
            home_form.setVisible(true);
            availableCars_form.setVisible(false);
            rent_form.setVisible(false);

            home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #686f86, #8e9296);");
            availableCars_btn.setStyle("-fx-background-color:transparent");
            rentCar_btn.setStyle("-fx-background-color:transparent");


            
            
        } else if (event.getSource() == availableCars_btn) {
            home_form.setVisible(false);
            availableCars_form.setVisible(true);
            rent_form.setVisible(false);

            availableCars_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #686f86, #8e9296);");
            home_btn.setStyle("-fx-background-color:transparent");
            rentCar_btn.setStyle("-fx-background-color:transparent");
            
           

            // TO UPDATE YOUR TABLEVIEW ONCE YOU CLICK THE AVAILABLE CAR NAV BUTTON
             
            availableCarShowListData();

        } else if (event.getSource() == rentCar_btn) {
            home_form.setVisible(false);
            availableCars_form.setVisible(false);
            rent_form.setVisible(true);

            rentCar_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #686f86, #8e9296);");
            home_btn.setStyle("-fx-background-color:transparent");
            availableCars_btn.setStyle("-fx-background-color:transparent");

            

        }

    }
    
    
    private double x = 0;
    private double y = 0;
    
//This is a method that handles the user logout functionality in a JavaFX GUI application.

//When the logout() method is called, it displays an alert dialog box asking the user to confirm whether they want to log out or not.
    //The Alert class is used to create the dialog box, and the showAndWait() method is called to display the dialog and wait for the user's response. 
    //The user can either click the "OK" or "Cancel" button in the dialog.

//If the user clicks the "OK" button, the option.get().equals(ButtonType.OK) condition will evaluate to true, and the method will proceed to hide 
    //the current dashboard form using the getWindow().hide() method. This will effectively log the user out of the application and return them 
    //to the login screen or exit the application entirely.

//If the user clicks the "Cancel" button, the dialog box will be closed, and the method will return without doing anything else.
 
    public void logout() {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");
        Optional<ButtonType> option = alert.showAndWait();
        try {
            if (option.get().equals(ButtonType.OK)) {
                // HIDE YOUR DASHBOARD FORM
                logoutBtn.getScene().getWindow().hide();

                
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //The availableCarAdd() method is responsible for adding a new car to the database. It first checks if all the required fields 
    //are filled in, and if the path of the car image is not empty. If any of these conditions is not met, it shows an error message 
    // to the user using an Alert. If all the fields are filled in, it prepares an SQL statement to insert a new row into the 
   //car table of the database, using the values entered by the user. It also converts the image path to a format 
   //that can be stored in the database. Finally, it executes the SQL statement and shows a success message 
    // to the user using another Alert. It then updates the available car
    //list displayed on the GUI using the availableCarShowListData() method, and clears the fields using the availableCarClear() method.
    public void availableCarAdd() {

        String sql = "INSERT INTO car (car_id, brand, model, price, status, image, date) "
                + "VALUES(?,?,?,?,?,?,?)";

        connect = database.connectDb();

        try {
            Alert alert;

            if (availableCars_carId.getText().isEmpty()
                    || availableCars_brand.getText().isEmpty()
                    || availableCars_model.getText().isEmpty()
                    || availableCars_status.getSelectionModel().getSelectedItem() == null
                    || availableCars_price.getText().isEmpty()
                    || getData.path == null || getData.path == "") {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, availableCars_carId.getText());
                prepare.setString(2, availableCars_brand.getText());
                prepare.setString(3, availableCars_model.getText());
                prepare.setString(4, availableCars_price.getText());
                prepare.setString(5, (String) availableCars_status.getSelectionModel().getSelectedItem());

                String uri = getData.path;
                uri = uri.replace("\\", "\\\\");

                prepare.setString(6, uri);

                  Date date = new Date(); 
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                prepare.setString(7, String.valueOf(sqlDate));

                prepare.executeUpdate();

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Added!");
                alert.showAndWait();

                availableCarShowListData();
                availableCarClear();
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    //The availableCarClear() method simply clears all the fields and the car image displayed on the GUI.
    public void availableCarClear() {
        availableCars_carId.setText("");
        availableCars_brand.setText("");
        availableCars_model.setText("");
        availableCars_status.getSelectionModel().clearSelection();
        availableCars_price.setText("");

        getData.path = "";

        availableCars_imageView.setImage(null);

    }
//The availableCarImportImage() method is used to allow the user to select an image file to import and display in the form. 
    //It uses JavaFX's FileChooser class to create a file chooser dialog, and sets the dialog's title and file extension filters to allow 
           // only image files with the .jpg and .png extensions.
//Once the user selects a file, the method gets the absolute path of the selected file and saves it to a static 
        //variable called path in a getData class. It then creates an Image object using the file's URI and sets the image view's 
//image property to the new image. The image property of the ImageView is responsible for displaying the image in the form.
 //
    //
    public void availableCarImportImage() {

        FileChooser open = new FileChooser();
        open.setTitle("Open Image File");
        open.getExtensionFilters().add(new ExtensionFilter("Image File", "*jpg", "*png"));

        File file = open.showOpenDialog(main_form.getScene().getWindow());

        if (file != null) {

            getData.path = file.getAbsolutePath();

            image = new Image(file.toURI().toString(), 116, 120, false, true);
            availableCars_imageView.setImage(image);

        }

    }
    //First, it creates an empty list called listS.

//Then, it iterates over the array listStatus, which contains the two strings "Available" and "Not Available".
//For each string in the array, it adds the string to listS.

//Next, it creates an ObservableList called listData, which is used to
    //store the status options that will be displayed in a drop-down menu in the form. It sets listData 
    //equal to the listS list, which contains the status options.
    private String[] listStatus = {"Available", "Not Available"};

    public void availableCarStatusList() {

        List<String> listS = new ArrayList<>();

        for (String data : listStatus) {
            listS.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listS);
        availableCars_status.setItems(listData);
    }
 //It retrieves the selected CarData object from the availableCars_tableView using the getSelectedItem method.
///It retrieves the index of the selected item using the getSelectedIndex method.
//It checks if the selected item index is valid. If the index is less than -1, it returns to exit the method.
//It sets the CarData object's carId, brand, model, and price to their respective fields in the form.
//It sets the getData.path to the selected CarData object's image.
//It sets the image of the availableCars_imageView to the selected CarData object's image.
   
    public void availableCarSelect() {
        CarData carD = availableCars_tableView.getSelectionModel().getSelectedItem();
        int num = availableCars_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < - 1) {
            return;
        }

        availableCars_carId.setText(String.valueOf(carD.getCarId()));
        availableCars_brand.setText(carD.getBrand());
        availableCars_model.setText(carD.getModel());
        availableCars_price.setText(String.valueOf(carD.getPrice()));

        getData.path = carD.getImage();

        String uri = "file:" + carD.getImage();

        image = new Image(uri, 116, 153, false, true);
        availableCars_imageView.setImage(image);

    }
    //The method builds an SQL UPDATE statement to modify the selected record, using the values from the input fields on the UI form. 
    //Note that the image path is also updated, since it might have been changed.
     //Before performing the update, the method displays a confirmation dialog to the user, asking for confirmation.
    //If the user confirms the update, the method executes the SQL statement and shows a success message to the user.
     //The method also refreshes the UI list of available cars and clears the input fields
    
    public void availableCarUpdate() {

        String uri = getData.path;
        uri = uri.replace("\\", "\\\\");

        String sql = "UPDATE car SET brand = '" + availableCars_brand.getText() + "', model = '"
                + availableCars_model.getText() + "', status ='"
                + availableCars_status.getSelectionModel().getSelectedItem() + "', price = '"
                + availableCars_price.getText() + "', image = '" + uri
                + "' WHERE car_id = '" + availableCars_carId.getText() + "'";

        connect = database.connectDb();

        try {
            Alert alert;

            if (availableCars_carId.getText().isEmpty()
                    || availableCars_brand.getText().isEmpty()
                    || availableCars_model.getText().isEmpty()
                    || availableCars_status.getSelectionModel().getSelectedItem() == null
                    || availableCars_price.getText().isEmpty()
                    || getData.path == null || getData.path == "") {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Car ID: " + availableCars_carId.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    availableCarShowListData();
                    availableCarClear();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void availableCarDelete() {

        String sql = "DELETE FROM car WHERE car_id = '" + availableCars_carId.getText() + "'";

        connect = database.connectDb();

        try {
            Alert alert;
            if (availableCars_carId.getText().isEmpty()
                    || availableCars_brand.getText().isEmpty()
                    || availableCars_model.getText().isEmpty()
                    || availableCars_status.getSelectionModel().getSelectedItem() == null
                    || availableCars_price.getText().isEmpty()
                    || getData.path == null || getData.path == "") {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE Car ID: " + availableCars_carId.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();

                    availableCarShowListData();
                    availableCarClear();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TO DISPLAY THE DATA ON THE TABLEVIEW
        availableCarShowListData();
        availableCarStatusList();
    }   
    
    
}
