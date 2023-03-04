/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.gui;

import data.Service;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import org.jdesktop.swingx.JXMapViewer;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class TestController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    @FXML
private Button cmdAdd;

@FXML
private Button cmdClear;

@FXML
private ComboBox<String> comboMapType;

@FXML
private Button jButton1;

@FXML
private PopupMenu jPopupMenu1;

@FXML
private Service jXMapViewer;

@FXML
private MenuItem menuEnd;

@FXML
private MenuItem menuStart;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
