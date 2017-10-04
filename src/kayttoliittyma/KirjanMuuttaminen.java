
package kayttoliittyma;

import tietovarasto.Kirjavarasto;

/**
 *
 * @author s1400591
 */
public class KirjanMuuttaminen extends AbstraktiLisaysMuutosPoisto {

    public KirjanMuuttaminen(Kirjavarasto kirjakanta) {
        super(kirjakanta, "Tallenna muutokset", "molemmat");
        this.setTitle("Tietojen muuttaminen");
    }

    @Override
    protected void kasitteleNapinPainallus() {

        try {
            kirjavarasto.muutakirjanTietoja(tietoikkuna.getKirjanTiedot());
            tyhjennaKentat();
            this.kirjaLista.setListData(listanSisalto(kirjavarasto.haeKaikkiKirjat()));
             //Aiheuttaa virheen mutta päivittää listan            
        } catch (NumberFormatException e) {

        }
    }

    @Override
    protected void tietoIkkunanAktiivisuus() {
        tietoikkuna.aktiivisetKentat(false);
    }

}