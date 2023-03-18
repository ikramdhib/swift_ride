package edu.swifrdide.gui;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class OCRController implements Initializable {

    @FXML
    private Label txtLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // initialize the text of the label to an empty string
        txtLabel.setText("");
    }

    @FXML
    private void selectImage() {
        FileChooser fileChooser = new FileChooser();
      //Select Image
        fileChooser.setTitle("Select Image File");
        fileChooser.getExtensionFilters().addAll(
            new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(txtLabel.getScene().getWindow());
        if (selectedFile != null) {
            String text = getTextFromImage(selectedFile);
            txtLabel.setText(text);
        }
    }
//test
    private String getTextFromImage(File imageFile) {
        Tesseract tesseract = new Tesseract();
        try {
            String text = tesseract.doOCR(imageFile);
            return text;
        } catch (TesseractException e) {
            e.printStackTrace();
            return "Error reading image file: " + e.getMessage();
        }
    }
}
