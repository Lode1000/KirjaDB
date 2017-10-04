package kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import tietovarasto.Kirja;
import tietovarasto.Kirjavarasto;

/**
 *
 * @author s1400591
 */
public abstract class AbstraktiLisaysMuutosPoisto extends JFrame {

    private JPanel pohjapaneeli = new JPanel(new BorderLayout());
    /* nappulat. Voisi olla flowlayoutkin, mutta pixelinviilauksen vuoksi 
    kuvaa vastaamaan GroupLayout */
    private final JPanel nappiPaneeli = new JPanel(); //
    protected JButton nappula = new JButton();
    protected JButton peruuta = new JButton();

    //tietosisältö
    private JPanel tietopaneeli = new JPanel();
    protected Tietoikkuna tietoikkuna = new Tietoikkuna();
    protected JList kirjaLista;
    //db
    protected Kirjavarasto kirjavarasto;
    private final String paneeliTyyppi;

    public AbstraktiLisaysMuutosPoisto(Kirjavarasto kirjakanta, String nappulanNimi, String paneeliTyyppi) {
        this.kirjavarasto = kirjakanta;
        this.paneeliTyyppi = paneeliTyyppi;

        this.nappula.setText(nappulanNimi);
        this.peruuta.setText("Peruuta");
        tietoIkkunanAktiivisuus();

        if (paneeliTyyppi.equals("napit")) {
            asetteleNappulat();
            pohjapaneeli.add(tietoikkuna, BorderLayout.CENTER);
        }
        if (paneeliTyyppi.equals("lista")) {
            asetaListajaTietoikkuna();
        }
        if (paneeliTyyppi.equals("molemmat")) {
            asetteleNappulat();
            asetaListajaTietoikkuna();
        }

        this.add(pohjapaneeli);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();

        nappula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kasitteleNapinPainallus();
            }
        });
        peruuta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tyhjennaKentat();
                kirjaLista.clearSelection(); 
                //Aiheuttaa virheen mutta poistaa valinnan, esim. jos on vain 1 valittava kirja tai valitsee saman uudestaan
            }
        });

    }

    //-------------------Asettelumetodit---------------------------
    protected void tyhjennaKentat() {
        tietoikkuna.tyhjennaKentat();
    }

    private void asetteleNappulat() {
        GroupLayout nappienAsettelu = new GroupLayout(nappiPaneeli);
        nappiPaneeli.setLayout(nappienAsettelu);
        nappienAsettelu.setAutoCreateGaps(true);
        nappienAsettelu.setAutoCreateContainerGaps(true);

        nappienAsettelu.setHorizontalGroup(nappienAsettelu.createSequentialGroup()
                .addComponent(nappula).addComponent(peruuta));

        nappienAsettelu.setVerticalGroup(nappienAsettelu.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(nappula).addComponent(peruuta));

        pohjapaneeli.add(nappiPaneeli, BorderLayout.PAGE_START);
    }
    
    //-----Listan asettelua-----
    protected Kirja[] listanSisalto(List<Kirja> kirjat) {
        JList kirjaLista = new JList();
        Kirja[] kirjaTaulukko = new Kirja[kirjavarasto.haeKaikkiKirjat().size()];
        int i = 0;
        for (Kirja kirja : kirjat) {
            kirjaTaulukko[i] = kirja;
            i++;
        }
        kirjaLista.setListData(kirjaTaulukko);
        return kirjaTaulukko;
    }
    
    protected void asetaListajaTietoikkuna() {
        this.kirjaLista = new JList(listanSisalto(kirjavarasto.haeKaikkiKirjat()));
        this.kirjaLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        GroupLayout asetaTiedotJaLista = new GroupLayout(tietopaneeli);
        tietopaneeli.setLayout(asetaTiedotJaLista);

        asetaTiedotJaLista.setAutoCreateGaps(true);
        asetaTiedotJaLista.setAutoCreateContainerGaps(true);

        JScrollPane scrollLista = new JScrollPane(kirjaLista);
        scrollLista.setPreferredSize(new Dimension(255, 130));

        asetaTiedotJaLista.setHorizontalGroup(asetaTiedotJaLista.createSequentialGroup()
                .addComponent(tietoikkuna).addComponent(scrollLista));
        asetaTiedotJaLista.setVerticalGroup(asetaTiedotJaLista.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(tietoikkuna).addComponent(scrollLista));

        kirjaLista.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                //jos valinta on juuri vaihtumassa palataan kuuntelemaan
                if (e.getValueIsAdjusting()) {
                    return;
                }
                //kun valinta on vaihtunut tehdään halutut tehtävät
                taytaKentat();  
            }
        });

        pohjapaneeli.add(tietopaneeli, BorderLayout.LINE_START);
    }

    private void taytaKentat() {
        if (this.paneeliTyyppi.equals("molemmat")) {
            tietoikkuna.aktiivisetKentat(true);
        }
        
        tietoikkuna.taytaKentat((Kirja) kirjaLista.getSelectedValue());
    }

    //-------------------abstraktit-------------------
    protected abstract void kasitteleNapinPainallus(); //Poistaa, lisää tai muokkaa

    protected abstract void tietoIkkunanAktiivisuus(); //Onko textfieldit muokattavissa
}
