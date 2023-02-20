

package edu.swiftride.services;
import edu.swiftride.entities.MoyenTransport;
import edu.swiftride.interfaces.InterfaceCRUD;
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
public class MoyenTransportCRUD implements InterfaceCRUD{
    
       private Connection connection;
       

  public MoyenTransportCRUD() {
    this.connection = MyConnection.getInstance().getConnexion();
  }

       @Override
    public void ajouterMoyenTransport(MoyenTransport m) {
       
    try {
        System.out.println("message");
      PreparedStatement preparedStatement = MyConnection.getInstance().getConnexion().prepareStatement("insert into moyen_transport( type,numero_trans,nb_station, id_admin) values (?,?,?,1)");
           
      preparedStatement.setString(1, m.getType());
      preparedStatement.setInt(2, m.getNumero_trans());
      preparedStatement.setInt(3, m.getNb_station());
    preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
   
    }

    @Override
    public void supprimerMoyenTransport(MoyenTransport m) {
       
    try {
      Statement statement = MyConnection.getInstance().getConnexion().createStatement();
    String  requete="delete from moyen_transport where id='"+m.getId()+"'";
    
statement.executeUpdate(requete);

      
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    }

    @Override
    public boolean modifierMoyenTransport(MoyenTransport m) {
    boolean status = false;
    try {
    String query = "UPDATE moyen_transport SET  type = '" + m.getType() + "', numero_trans = " + m.getNumero_trans() + ", nb_station = " + m.getNb_station() + " WHERE id = " + m.getId() + " ";

      Statement Statement = MyConnection.getInstance().getConnexion().createStatement();
      
      Statement.executeUpdate(query);
      status = Statement.executeUpdate(query) > 0;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return status;    
    }

    @Override
    public List<MoyenTransport> afficherMoyenTransport() {

         List<MoyenTransport> list = new ArrayList<MoyenTransport>();
    try {
        
      PreparedStatement preparedStatement = MyConnection.getInstance().getConnexion().prepareStatement("select * from moyen_transport");
      ResultSet rs = preparedStatement.executeQuery();
      while (rs.next()) {
       MoyenTransport m = new MoyenTransport();
        m.setId(rs.getInt("id"));
        m.setType(rs.getString("type"));
        m.setNumero_trans(rs.getInt("numero_trans"));
        m.setNb_station(rs.getInt("nb_station"));
        m.setId_admin(rs.getInt("id_admin"));
        list.add(m);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
    }

  
    

  
}
