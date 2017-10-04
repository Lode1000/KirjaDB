
package kayttoliittyma;

import tietovarasto.Kirjavarasto;

/**
 *
 * @author s1400591
 */
public class KirjanHaku extends AbstraktiLisaysMuutosPoisto {

    public KirjanHaku(Kirjavarasto kirjakanta) {
        super(kirjakanta, "Talleta", "lista");
        this.setTitle("tehtava12");
    }

    @Override
    protected void kasitteleNapinPainallus() {

        try {           
            //Non
        } catch (NumberFormatException e) {

        }
    }

    @Override
    protected void tietoIkkunanAktiivisuus() {
        tietoikkuna.aktiivisetKentat(false);
    }

}
