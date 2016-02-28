/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cowsandbulls;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Karthik Perumal
 */
public class Connector_Class {
   public static Connection con;
public Connector_Class()
{
try
     {
     Class.forName("com.mysql.jdbc.Driver");
     String url="jdbc:mysql://localhost:3306/";
     String dbname="cowsnbulls_db";
     con=DriverManager.getConnection(url+dbname,"root","india");
      } 
            
    
catch (SQLException ex) {
                Logger.getLogger(Connector_Class.class.getName()).log(Level.SEVERE, null, ex);}
     catch (ClassNotFoundException ex) {
            Logger.getLogger(Connector_Class.class.getName()).log(Level.SEVERE, null, ex);
        }
}
}