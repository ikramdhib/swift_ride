package edu.swiftride.services;

import java.io.*;
import java.net.*;

public class Rss {
    public static void main(String[] args) {
        System.out.println(readRSS("https://news.gnet.tn/transports-en-commun-tunisie/"));
        System.out.println(readRSS("https://www.entreprises-magazine.com/bakomotors-tunisie-fournira-6-tricycles-electriques-a-energie-solaire/"));
        System.out.println(readRSS("https://www.agenceecofin.com/transports/2102-105750-tunisie-la-dette-de-la-compagnie-publique-de-transport-en-commun-transtu-evaluee-a-pres-de-639-millions"));
        System.out.println(readRSS("https://rss.app/feeds/tkeBYjQGRR1hpjEx.xml"));
        System.out.println(readRSS("https://www.agenceecofin.com/investissement/0802-63859-le-tunisien-ahmed-mhiri-cede-sa-startup-travelcar-au-constructeur-automobile-francais-psa"));
        System.out.println(readRSS("https://www.agenceecofin.com/transports/1702-105651-l-estonien-bolt-annonce-533-millions-d-investissements-en-afrique-durant-les-deux-prochaines-annees"));
        System.out.println(readRSS("https://www.agenceecofin.com/transports/2305-97912-tunisie-lancement-annonce-des-prochaines-phases-du-reseau-ferroviaire-des-banlieues-de-tunis"));
        System.out.println(readRSS("https://www.agenceecofin.com/transports/0507-89785-la-tunisie-mature-un-projet-de-bus-rapid-transit-pour-sa-capitale"));
        System.out.println(readRSS("https://www.agenceecofin.com/entreprendre/2402-74155-tunisie-bassem-bougerra-facilite-le-transport-urbain-avec-des-scooters-a-toit"));        
        System.out.println(readRSS("https://www.agenceecofin.com/finance/1302-73810-tunisie-intigo-leve-des-fonds-et-etend-ses-services-a-la-livraison-de-repas"));
    }

    public static String readRSS(String urlAddress) {
        try{ 
            URL rssUrl = new URL(urlAddress);
            BufferedReader in = new BufferedReader(new InputStreamReader(rssUrl.openStream()));
            String sourceCode = "";
            String line;
            while((line = in.readLine())!=null){
                if(line.contains("<title>")){
                    int firstPos = line.indexOf("<title>");
                    String temp = line.substring(firstPos);
                    temp = temp.replace("<title>","");
                    int lastPos = temp.indexOf("</title>");
                    temp = temp.substring(0,lastPos);
                    temp = removeCDATA(temp); // Remove CDATA tags and surrounding characters
                    sourceCode += temp+"\n";
                }
            }
            in.close();
            return sourceCode;
        } catch (MalformedURLException ue){
            System.out.println("Malformed URL");
        } catch (IOException ioe) {
            System.out.println("Something went wrong reading the contents");
        }
        return null;
    }

    public static String removeCDATA(String input) {
        String output = input.replaceAll("<!\\[CDATA\\[|\\]\\]>", "");
        return output;
    }
}
