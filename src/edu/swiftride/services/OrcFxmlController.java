/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.services;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;


/**
 * FXML Controller class
 *
 * @author sami
 */


public class OrcFxmlController implements Initializable {
    @FXML
    private Label showOCR;
    /**
     * Initializes the controller class.
     */
   @Override
public void initialize(URL url, ResourceBundle rb) {
    String inputFilePath = "C:\\Users\\samib\\OneDrive\\Documents\\NetBeansProjects\\swift_ride\\src\\edu\\swiftride\\image/English.png";
    Tesseract tesseract = new Tesseract();
    tesseract.setDatapath("C:\\Users\\samib\\OneDrive\\Documents\\NetBeansProjects\\swift_ride");

    String fullText = null;
        try {
            fullText = tesseract.doOCR(new File(inputFilePath));
        } catch (TesseractException ex) {
            Logger.getLogger(OrcFxmlController.class.getName()).log(Level.SEVERE, null, ex);
        }
    showOCR.setText(fullText);
    
}
}