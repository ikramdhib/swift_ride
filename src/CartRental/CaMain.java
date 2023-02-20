/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CartRental;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author user
 */
public class CaMain extends Application {
    private  double x = 0 ;
    private  double y = 0 ;
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = null;
        
            try {
                root = FXMLLoader.load(getClass().getResource("dashboardAdmin.fxml"));
            
        
           Scene scene = new Scene(root);
            primaryStage.setTitle("Hello World!");
            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            primaryStage.show();
    
    } catch (IOException ex) {
                Logger.getLogger(CaMain.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
