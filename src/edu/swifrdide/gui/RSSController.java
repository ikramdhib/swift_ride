/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swifrdide.gui;

import edu.swiftride.services.Rss;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author sami
 */
public class RSSController implements Initializable {

    @FXML
    private Label txtRSS;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
                List<String> rssFeeds = new ArrayList<>();        
         rssFeeds.add("https://news.gnet.tn/transports-en-commun-tunisie/");
                 rssFeeds.add("https://www.entreprises-magazine.com/bakomotors-tunisie-fournira-6-tricycles-electriques-a-energie-solaire/");
         rssFeeds.add("https://www.agenceecofin.com/transports/2102-105750-tunisie-la-dette-de-la-compagnie-publique-de-transport-en-commun-transtu-evaluee-a-pres-de-639-millions");
         rssFeeds.add("https://rss.app/feeds/tkeBYjQGRR1hpjEx.xml");
         rssFeeds.add("https://www.agenceecofin.com/investissement/0802-63859-le-tunisien-ahmed-mhiri-cede-sa-startup-travelcar-au-constructeur-automobile-francais-psa");
         rssFeeds.add("https://www.agenceecofin.com/transports/1702-105651-l-estonien-bolt-annonce-533-millions-d-investissements-en-afrique-durant-les-deux-prochaines-annees");
         rssFeeds.add("https://www.agenceecofin.com/transports/2305-97912-tunisie-lancement-annonce-des-prochaines-phases-du-reseau-ferroviaire-des-banlieues-de-tunis");
         rssFeeds.add("https://www.agenceecofin.com/transports/0507-89785-la-tunisie-mature-un-projet-de-bus-rapid-transit-pour-sa-capitale");
         rssFeeds.add("https://www.agenceecofin.com/entreprendre/2402-74155-tunisie-bassem-bougerra-facilite-le-transport-urbain-avec-des-scooters-a-toit");
         rssFeeds.add("https://www.agenceecofin.com/finance/1302-73810-tunisie-intigo-leve-des-fonds-et-etend-ses-services-a-la-livraison-de-repas");
 StringBuilder builder = new StringBuilder();
        for (String feed : rssFeeds) {
            builder.append(Rss.readRSS(feed)).append("\n\n");
        }
        txtRSS.setText(builder.toString());
    }
}