package tietovarasto;

/**
 *
 * @author s1400591
 */
public class Kirja {
    private final int id;
    private final String nimi;
    private final String tekijanEtunimi;
    private final String tekijanSukunimi;

        public Kirja(int id, String nimi, String etunimi, String sukunimi) {
        this.id = id;
        this.nimi = nimi;
        this.tekijanEtunimi = etunimi;
        this.tekijanSukunimi = sukunimi;
    }

    public int getId() {
        return id;
    }

    public String getNimi() {
        return nimi;
    }

    public String getTekijanEtunimi() {
        return tekijanEtunimi;
    }

    public String getTekijanSukunimi() {
        return tekijanSukunimi;
    }

    @Override
    public String toString() {
        return "ID: " +id + " KIRJA: " + nimi + " NIMI: " + tekijanEtunimi + " " + tekijanSukunimi;
    }
     
        
    
}
