package edu.swiftride.gui;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class QR extends Application {
    
     private String valeur1;
      private String valeur2;
       private String valeur3;

    public QR(String valeur1 , String valeur2 , String valeur3) {
        this.valeur1 = valeur1;
        this.valeur2 = valeur2;
        this.valeur3 = valeur3;
    } 
    
    @Override
    public void start(Stage primaryStage) {
        
        
        
         QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String myWeb = "Paiement SwiftRide : \n Reference secret :  " +  valeur1 +" Numero de carte bancaire :  " +  valeur2+" Date d'expiration  :  " +  valeur3 ;  
        int width = 600;
        int height = 600;
        String fileType = "png";
        
        BufferedImage bufferedImage = null;
        try {
            BitMatrix byteMatrix = qrCodeWriter.encode(myWeb, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();
            
            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);
            
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            
            System.out.println("Success...");
            
        } catch (WriterException ex) {
            //Logger.getLogger(JavaFX_QRCodeWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ImageView qrView = new ImageView();
        qrView.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
        
        StackPane root = new StackPane();
        root.getChildren().add(qrView);
        
        
            Scene scene = new Scene(root);
            
            primaryStage.setTitle("QR Code de votre paiement!");
            primaryStage.setScene(scene);
            primaryStage.show();
            
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        launch(args);
    }
    
}
