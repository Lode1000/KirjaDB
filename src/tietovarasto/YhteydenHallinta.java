
package tietovarasto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author s1400591
 */
public class YhteydenHallinta {
    
    public static Connection avaaYhteys(String ajuri, String url, 
                                        String kayttaja, String salasana) {
        try{
            Class.forName(ajuri).newInstance();
            return DriverManager.getConnection(url,kayttaja, salasana); 
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    } 
    
    public static void suljeYhteys(Connection yhteys) {
        if(yhteys!=null) {
            try{
                yhteys.close();
            }
            catch(Exception e) {
                //tässä ei mitään tehtävissä
            }
        }
    }
    public static void suljeLause(Statement lause) {
        if(lause!=null) {
            try{
                lause.close();
            }
            catch(Exception e) {
            }
        }
    }
    
    public static void suljeTulosjoukko(ResultSet tulosjoukko){
        if(tulosjoukko!=null) {
            try{
                tulosjoukko.close();
            }
            catch(Exception e) {
            }
        }
    }  
}
