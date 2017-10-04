package kayttoliittyma;

import tietovarasto.Kirja;
import tietovarasto.Kirjavarasto;

/**
 *
 * @author s1400591
 */
public class KirjanLisays extends AbstraktiLisaysMuutosPoisto {

    public KirjanLisays(Kirjavarasto kirjakanta) {
        super(kirjakanta, "Talleta", "napit");
        this.setTitle("tehtava");
    }

    @Override
    protected void kasitteleNapinPainallus() {

        try {
            Kirja kirja = tietoikkuna.getKirjanTiedot();
            kirjavarasto.lisaaKirja(kirja);
            tietoikkuna.tyhjennaKentat();
        } catch (NumberFormatException e) {

        }
    }

    @Override
    protected void tietoIkkunanAktiivisuus() {
        //non
    }

}
