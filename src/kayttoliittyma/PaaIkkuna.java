package kayttoliittyma;

import javax.swing.*;
import java.awt.event.*;
import tietovarasto.Kirja;
import tietovarasto.Kirjavarasto;

/**
 *
 * @author s1400591
 */
public class PaaIkkuna extends JFrame {

    private final JButton lisaa = new JButton("Lisää");
    private final JButton poista = new JButton("Poista");
    private final JButton muuta = new JButton("Muuta");
    private final JButton hae = new JButton("Hae");
    private final JButton haeKaikki = new JButton("Hae Kaikki");

    private final JPanel pohjapaneeli = new JPanel();
    private Kirjavarasto kirjakanta = new Kirjavarasto();

    public PaaIkkuna() {
        GroupLayout asettelu = new GroupLayout(pohjapaneeli);
        pohjapaneeli.setLayout(asettelu);

        asettelu.setAutoCreateGaps(true);
        asettelu.setAutoCreateContainerGaps(true);

        //X-suunta
        GroupLayout.ParallelGroup pohjaX = asettelu.createParallelGroup();
        
        pohjaX.addComponent(lisaa, 160, GroupLayout.DEFAULT_SIZE, 1000);
        pohjaX.addComponent(poista, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, 1000);
        pohjaX.addComponent(muuta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, 1000);
        pohjaX.addComponent(hae, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, 1000);
        pohjaX.addComponent(haeKaikki, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, 1000);

        //asettelu.linkSize(lisaa, poista, muuta, hae, haeKaikki);
        asettelu.setHorizontalGroup(pohjaX);

        //Y-suunta
        GroupLayout.SequentialGroup pohjaY = asettelu.createSequentialGroup();
        pohjaY.addComponent(lisaa);
        pohjaY.addComponent(poista);
        pohjaY.addComponent(muuta);
        pohjaY.addComponent(hae);
        pohjaY.addComponent(haeKaikki);

        asettelu.setVerticalGroup(pohjaY);
        this.add(pohjapaneeli);
        this.setTitle("Pääikkuna");
        this.setLocationRelativeTo(null);
        this.pack();

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                suoritaLopputoimet();
            }
        });

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
    
    private void haeKaikkiKirjat() {
        JTextArea hakualue=new JTextArea(20,20);
        JScrollPane scroll=new JScrollPane(hakualue);
        for(Kirja kirja:kirjakanta.haeKaikkiKirjat()) {
            hakualue.append(kirja + "\n");
        }
        JOptionPane.showMessageDialog(this, scroll, "Hae Kaikki", 
                                        JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void suoritaLopputoimet() {
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PaaIkkuna().setVisible(true);
            }
        });
    }
}
