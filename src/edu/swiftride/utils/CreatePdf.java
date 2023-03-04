/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.utils;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Table;
import java.io.File;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

import com.itextpdf.text.Paragraph;

/**
 *
 * @author skann
 */
public class CreatePdf {

    public static File createPDF(String filename, BufferedImage bufferedImage) throws Exception {
        int width = 300;
        int height = 300;
        File dossierDest = new File("pdf");
        if (!dossierDest.exists()) {
            dossierDest.mkdirs();
        }
        File pdfdestination = new File(dossierDest, new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()) + filename + ".pdf");
        PdfWriter writer = new PdfWriter(pdfdestination.getAbsolutePath());
                ImageData img = ImageDataFactory.create(bufferedImageToBytes(bufferedImage));
        PdfDocument pdf = new PdfDocument(writer);
          PageSize pageSize = new PageSize(img.getWidth(), img.getHeight());
          pdf.setDefaultPageSize(pageSize);
          Image image = new Image(img);
          image.setFixedPosition(
                (pdf.getDefaultPageSize().getWidth() - img.getWidth()) / 2,
                (pdf.getDefaultPageSize().getHeight() - img.getHeight()) / 2
        );
        Document document = new Document(pdf);
                Paragraph paragraph = new Paragraph("ce QR code est confidentiel ne le montre a personne!");

        document.add(image);
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
