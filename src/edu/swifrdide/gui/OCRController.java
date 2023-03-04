/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swifrdide.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.io.File;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

/**
 * FXML Controller class
 *
 * @author sami
 */
public class OCRController implements Initializable {
@FXML
    private Label txtLabel;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
// Appeler la méthode pour récupérer le texte à partir de l'image
        String text = getTextFromImage(new File("C:\\Users\\samib\\OneDrive\\Documents\\NetBeansProjects\\swift_ride\\src\\edu\\swiftride\\image/English2.png"));
        // Mettre à jour le texte du label
        txtLabel.setText(text);
    }
    
    // Méthode pour récupérer le texte à partir de l'image en utilisant la bibliothèque Tess4J
    private String getTextFromImage(File imageFile) {
        Tesseract tesseract = new Tesseract();
        try {
            String text = tesseract.doOCR(imageFile);
            return text;
        } catch (TesseractException e) {
            e.printStackTrace();
            return null;
        }
    }
}