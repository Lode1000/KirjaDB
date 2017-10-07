
package tietovarasto;

public class Kayttaja {

    private final String tunnus;
    private final String salasana;
    private final int kId;
    
    public Kayttaja(int kId, String tunnus, String salasana){
        this.kId = kId;
        this.tunnus = tunnus;
        this.salasana = salasana;
    }

    public String getTunnus() {
        return tunnus;
    }

    public String getSalasana() {
        return salasana;
    }  

    public int getkId() {
        return kId;
    }

    @Override
    public String toString() {
        return "tunnus: " + tunnus + ", salasana: " + salasana + ", kId=" + kId + '}';
    }
      
}