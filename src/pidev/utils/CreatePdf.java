/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.utils;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.text.Element;
import java.io.File;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import javafx.scene.image.WritableImage;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;

import javafx.scene.image.ImageView;

import java.io.FileOutputStream;

import com.itextpdf.text.Paragraph;

import java.awt.Color;

/**
 *
 * @author skann
 */
public class CreatePdf {

    public static File createPDF(String filename, BufferedImage bufferedImage) throws Exception {
        int width = 300;
        int height = 300;
        File dossierDest = new File("C:/Users/skann/OneDrive/Desktop/pdf");
        if (!dossierDest.exists()) {
            dossierDest.mkdirs();
        }
        File pdfdestination = new File(dossierDest, new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()) + filename + ".pdf");

        // FileOutputStream fileOut = new FileOutputStream(pdfdestination);
//WritableImage writableImage = new WritableImage((int) imageView.getFitWidth(), (int) imageView.getFitHeight());
        //imageView.snapshot(null, writableImage);
        //  BufferedImage bufferedImage = SwingFXUtils.fromFXImage(imageView.getImage(), null);
        PdfWriter writer = new PdfWriter(pdfdestination.getAbsolutePath());
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        ImageData img = ImageDataFactory.create(bufferedImageToBytes(bufferedImage));
        Image img1 = new Image(img);
        Paragraph paragraph = new Paragraph("ce QR code est confidentiel ne le montre a personne!");


        document.add(img1);

        document.close();

        return pdfdestination;

    }

    private static byte[] bufferedImageToBytes(BufferedImage bufferedImage) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", out);
        out.flush();
        return out.toByteArray();
    }

}
