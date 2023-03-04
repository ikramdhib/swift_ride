/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.services;
import net.sourceforge.tess4j.Tesseract;
import java.io.File;
import net.sourceforge.tess4j.TesseractException;
/**
 *
 * @author sami
 */
public class Ocr {
   
    public static void main(String[] args) throws TesseractException {
    String inputFilePath = "C:\\Users\\samib\\OneDrive\\Documents\\NetBeansProjects\\swift_ride\\src\\edu\\swiftride\\image/English2.png";

    Tesseract tesseract = new Tesseract();
    tesseract.setDatapath("C:\\Users\\samib\\OneDrive\\Documents\\NetBeansProjects\\swift_ride");
    
    String fullText = tesseract.doOCR(new File(inputFilePath));
    
        System.out.println(fullText);
    }
}
