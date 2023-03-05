package edu.swiftride.services;
import edu.swiftride.entities.Station;
import edu.swiftride.interfaces.InterfaceCRUDS;
import edu.swiftride.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author lelou
 */
public class StationCRUD implements InterfaceCRUDS{
       private Connection connection;
  public StationCRUD() {
    this.connection = MyConnection.getInstance().getConnexion();
  }

       @Override
    public void ajouterStation(Station s) {
       
    try {
        System.out.println("message");
      PreparedStatement preparedStatement = MyConnection.getInstance().getConnexion().prepareStatement("insert into station ( ville,nom_station) values (?,?)");    
    preparedStatement.setString(1, s.getVille());
    preparedStatement.setString(2, s.getNom_station());
    preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
   
    }

    @Override
    public void supprimerStation(Station s) {
       
    try {
      Statement statement = MyConnection.getInstance().getConnexion().createStatement();
     String  requete="delete from station where ids='"+s.getIds()+"'";
     statement.executeUpdate(requete);  
    } catch (SQLException e) {
      e.printStackTrace();
    }
    }

    @Override
    public boolean modifierStation(Station s) {
    boolean status = false;
    try {
    String query = "UPDATE station SET  ville = '" + s.getVille() + "', nom_station = '" + s.getNom_station() +  "' WHERE ids = " + s.getIds() + " ";

      Statement Statement = MyConnection.getInstance().getConnexion().createStatement();
      
      Statement.executeUpdate(query);
      status = Statement.executeUpdate(query) > 0;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return status;    
    }

    @Override
    public List<Station> afficherStation() {

         List<Station> list = new ArrayList<Station>();
    try {
        
      PreparedStatement preparedStatement = MyConnection.getInstance().getConnexion().prepareStatement("select * from station");
      ResultSet rs = preparedStatement.executeQuery();
      while (rs.next()) {
       Station s = new Station();
        s.setIds(rs.getInt("ids"));
        s.setVille(rs.getString("ville"));
        s.setNom_station(rs.getString("nom_station"));

        list.add(s);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
    }

  
    

  
}