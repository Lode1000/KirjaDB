package kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import tietovarasto.Kirja;
import tietovarasto.Kirjavarasto;

/**
 *
 * @author s1400591
 */
public class Paavalikko {
    
    private final JButton lisaa;
    private final JButton poista;
    private final JButton muuta;
    private final JButton hae;
    private final JButton haeKaikki;
    private final JButton ulosKirjautuminen;
    
    private final JPanel paneeli;
    private final Kirjavarasto kirjakanta;
    private final PaaIkkuna paaIkkuna;
    
    
    public Paavalikko(PaaIkkuna paaIkkuna) {
        this.paneeli = new JPanel();
        this.haeKaikki = new JButton("Hae kaikki");
        this.hae = new JButton("Hae");
        this.muuta = new JButton("Muuta");
        this.poista = new JButton("Poista");
        this.lisaa = new JButton("Lisää");
        this.ulosKirjautuminen = new JButton("Kirjaudu ulos");
        
        this.paaIkkuna = paaIkkuna;
        this.kirjakanta = new Kirjavarasto();
        
        GroupLayout asettelu = new GroupLayout(paneeli);
        paneeli.setLayout(asettelu);

        asettelu.setAutoCreateGaps(true);
        asettelu.setAutoCreateContainerGaps(true);

        //X-suunta
        GroupLayout.ParallelGroup pohjaX = asettelu.createParallelGroup();
        
        pohjaX.addComponent(lisaa, 160, GroupLayout.DEFAULT_SIZE, 1000);
        pohjaX.addComponent(poista, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, 1000);
        pohjaX.addComponent(muuta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, 1000);
        pohjaX.addComponent(hae, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, 1000);
        pohjaX.addComponent(haeKaikki, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, 1000);
        pohjaX.addComponent(ulosKirjautuminen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, 1000);
        
        //asettelu.linkSize(lisaa, poista, muuta, hae, haeKaikki);
        asettelu.setHorizontalGroup(pohjaX);

        //Y-suunta
        GroupLayout.SequentialGroup pohjaY = asettelu.createSequentialGroup();
        pohjaY.addComponent(lisaa);
        pohjaY.addComponent(poista);
        pohjaY.addComponent(muuta);
        pohjaY.addComponent(hae);
        pohjaY.addComponent(haeKaikki);
        pohjaY.addComponent(ulosKirjautuminen);
        asettelu.setVerticalGroup(pohjaY);

        lisaa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lisaaKirja();
            }
        });
        
        poista.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                poistaKirja();
            }
        });
        
        muuta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                muutaKirja();
            }
        });

        hae.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                haeKirja();
            }
        });

        haeKaikki.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                haeKaikkiKirjat();
            }
        });
        
        ulosKirjautuminen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                kirjauduUlos();
            }
        });
    }
    
        //Nappuloiden toiminnot
    private void lisaaKirja() {
        KirjanLisays ikkuna = new KirjanLisays(kirjakanta);
        ikkuna.setLocationRelativeTo(lisaa);
        ikkuna.setVisible(true);
    }
    
    private void poistaKirja() {
        KirjanPoistaminen ikkuna = new KirjanPoistaminen(kirjakanta);
        ikkuna.setLocationRelativeTo(poista);
        ikkuna.setVisible(true);
    }
    
    private void haeKirja() {
        KirjanHaku ikkuna = new KirjanHaku(kirjakanta);
        ikkuna.setLocationRelativeTo(hae);
        ikkuna.setVisible(true);
    }

    private void muutaKirja() {
        KirjanMuuttaminen ikkuna = new KirjanMuuttaminen(kirjakanta);
        ikkuna.setLocationRelativeTo(muuta);
        ikkuna.setVisible(true);
    }
    
    private void kirjauduUlos() {
        this.paaIkkuna.valikonVaihtaminen(PaaIkkuna.valikkoTila.KIRJAUTUMINEN);
    }
    
    private void haeKaikkiKirjat() {
        JTextArea hakualue=new JTextArea(20,20);
        JScrollPane scroll=new JScrollPane(hakualue);
        for(Kirja kirja:kirjakanta.haeKaikkiKirjat()) {
            hakualue.append(kirja + "\n");
        }
        JOptionPane.showMessageDialog(this.paneeli, scroll, "Hae Kaikki", 
                                        JOptionPane.INFORMATION_MESSAGE);
    }

    public JPanel getPaneeli() {
        return paneeli;
    }
    
}
