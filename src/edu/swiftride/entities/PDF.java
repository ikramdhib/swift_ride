/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.entities;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;


/**
 *
 * @author Ines
 */
public class PDF {
    public void GeneratePdf(String filename, MoyenTransport m, int id) throws FileNotFoundException, DocumentException, BadElementException, IOException, InterruptedException, SQLException {

        Document document = new Document() {
        };
        PdfWriter.getInstance(document, new FileOutputStream(filename + ".pdf"));
        document.open();
        //document.add();
        
        document.add(new Paragraph("                              Swift Ride                     "));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("L'identifiant de ce moyen de transport  :" + m.getId()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("Le type de moyen de transport :" + m.getType()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("Le numero de moyen de transport :" + m.getNumero_trans()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("                      "));
        document.close();
        Process pro = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + filename + ".pdf");
    }
}




