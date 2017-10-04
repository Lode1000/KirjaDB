
package tietovarasto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author s1400591
 */
public class Kirjavarasto {

    private String ajuri;
    private String url;
    private String kayttaja;
    private String salasana;

    public Kirjavarasto(String ajuri, String url, String kayttaja, String salasana) {
        this.ajuri = ajuri;
        this.url = url;
        this.kayttaja = kayttaja;
        this.salasana = salasana;
    }

    public Kirjavarasto() {
        this("org.apache.derby.jdbc.EmbeddedDriver", "jdbc:derby:tietokanta",
                "Saku", "Salainen"); //Passwordit tässä demon vuoksi
    }

    public void lisaaKirja(Kirja lisattyKirja) {
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return;
        }
        PreparedStatement lisayslause = null;
        try {
            String lisaaSql = "insert into kirja (kirjaid, nimi, tekijanetunimi, tekijansukunimi) values (?,?,?,?)";
            lisayslause = yhteys.prepareStatement(lisaaSql);

            lisayslause.setInt(1, lisattyKirja.getId());
            lisayslause.setString(2, lisattyKirja.getNimi());
            lisayslause.setString(3, lisattyKirja.getTekijanEtunimi());
            lisayslause.setString(4, lisattyKirja.getTekijanSukunimi());
            lisayslause.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            YhteydenHallinta.suljeLause(lisayslause);
            YhteydenHallinta.suljeYhteys(yhteys);
        }
    }
    
    public void poistakirja(int kirjaID) {
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return;
        }
        PreparedStatement poistolause = null;
        try {
            String poistoSql = "delete from kirja where kirjaID=?";               
            poistolause = yhteys.prepareStatement(poistoSql);
            poistolause.setInt(1, kirjaID);
            
            poistolause.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            YhteydenHallinta.suljeLause(poistolause);
            YhteydenHallinta.suljeYhteys(yhteys);
        }
    }
    
    public boolean muutakirjanTietoja(Kirja uusikirja) {
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys == null) {
            return false;
        }
        PreparedStatement muutoslause = null;
        try {
            String muutaSql = "update kirja"
                    + " set nimi=?, tekijanetunimi=?, tekijansukunimi=? where kirjaid=?";
            muutoslause = yhteys.prepareStatement(muutaSql);

            
            muutoslause.setString(1, uusikirja.getNimi());
            muutoslause.setString(2, uusikirja.getTekijanEtunimi());
            muutoslause.setString(3, uusikirja.getTekijanSukunimi());
            muutoslause.setInt(4, uusikirja.getId());
            if(muutoslause.executeUpdate()>0) {
                return true;
            }
            else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            YhteydenHallinta.suljeLause(muutoslause);
            YhteydenHallinta.suljeYhteys(yhteys);
        }     
    }
    
    public Kirja haekirja(int kirjaID) {
        //palautta null, jos kirjaID:llä ei löydy henkilöä
        Connection yhteys=YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if(yhteys==null) return null;
        PreparedStatement hakulause=null;
        ResultSet tulosjoukko=null;
        try{
            String hakuSql="select * from kirja where kirjaid=?";
            hakulause=yhteys.prepareStatement(hakuSql);
            hakulause.setInt(1, kirjaID);
            tulosjoukko=hakulause.executeQuery();
            if(tulosjoukko.next()) {
                return new Kirja(tulosjoukko.getInt(1),       //ID
                                    tulosjoukko.getString(2),   //etunimi
                                    tulosjoukko.getString(3),   //sukunimi
                                    tulosjoukko.getString(4));     //syntymävuosi
            }
            else { //jos ei löytynyt eli tulosjoukko oli tyhjä
                return null;
            }
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
        finally{
            YhteydenHallinta.suljeTulosjoukko(tulosjoukko);
            YhteydenHallinta.suljeLause(hakulause);
            YhteydenHallinta.suljeYhteys(yhteys);
        }
    }
    
    public List<Kirja> haeKaikkiKirjat() {
        List<Kirja> kirjat = new ArrayList<Kirja>();
        
        Connection yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttaja, salasana);
        if (yhteys != null) {
            PreparedStatement hakulause = null;
            ResultSet tulosjoukko = null;
            try {
                String hakuSql = "select kirjaid, nimi, tekijanEtunimi, tekijanSukunimi from kirja";
                hakulause = yhteys.prepareStatement(hakuSql);
                tulosjoukko = hakulause.executeQuery();

                while (tulosjoukko.next()) {
                    kirjat.add(new Kirja(tulosjoukko.getInt(1),
                       tulosjoukko.getString(2),tulosjoukko.getString(3),tulosjoukko.getString(4)));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                YhteydenHallinta.suljeTulosjoukko(tulosjoukko);
                YhteydenHallinta.suljeLause(hakulause);
                YhteydenHallinta.suljeYhteys(yhteys);
            }
        }
        return kirjat;
    }
    
}
