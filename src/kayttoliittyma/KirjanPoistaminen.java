package kayttoliittyma;

import tietovarasto.Kirjavarasto;

/**
 *
 * @author s1400591
 */
public class KirjanPoistaminen extends AbstraktiLisaysMuutosPoisto {

    public KirjanPoistaminen(Kirjavarasto kirjakanta) {
        super(kirjakanta, "Poista", "molemmat");
        this.setTitle("Poista kirja");
    }

    @Override
    protected void kasitteleNapinPainallus() {

        try {
            kirjavarasto.poistakirja(tietoikkuna.getKirjanTiedot().getId());
            tyhjennaKentat();
            this.kirjaLista.setListData(listanSisalto(kirjavarasto.haeKaikkiKirjat())); //Päivitys aiheuttaa error   
            
        } catch (NumberFormatException e) {

        }
    }

    @Override
    protected void tietoIkkunanAktiivisuus() {
        tietoikkuna.aktiivisetKentat(false);
    }

}
