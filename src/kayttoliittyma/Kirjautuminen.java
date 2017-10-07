package kayttoliittyma;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import tietovarasto.BCrypt;
import tietovarasto.Kayttaja;
import tietovarasto.Kirjavarasto;

/**
 *
 * @author s1400591
 *
 */
public class Kirjautuminen {

    private final JPanel paneeli;

    private final JButton kirjaudu;
    private final JButton rekisteroidy;
    
    private final JLabel tunnus;
    private final JLabel salasana;
    private final JTextField tunnusKentta;
    private final JPasswordField salasanaKentta;
    private Kirjavarasto kirjakanta;
    private final PaaIkkuna paaIkkuna;

    public Kirjautuminen(PaaIkkuna paaIkkuna) {
        this.paaIkkuna = paaIkkuna;
        this.kirjakanta = new Kirjavarasto();
        this.paneeli = new JPanel();
        
        this.tunnus = new JLabel("Tunnus: ");
        this.salasana = new JLabel("Salasana: ");
        this.salasanaKentta = new JPasswordField("Salasana", 20);
        this.tunnusKentta = new JTextField("Käyttäjätunnus", 20);
        this.rekisteroidy = new JButton("Rekisteröidy");
        this.kirjaudu = new JButton("Kirjaudu");

        asetteleKomponentit();

        kirjaudu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kirjauduSisaan(tunnusKentta.getText(), new String(salasanaKentta.getPassword()));
                
            }
        });
        
        rekisteroidy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rekisteroidy(tunnusKentta.getText(), new String(salasanaKentta.getPassword()));
            }
        });
        
        tunnusKentta.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent me) {
                super.mouseExited(me); 
                tunnusKentta.setText("");
            }        
        });
        
        salasanaKentta.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent me) {
                super.mouseExited(me); 
                salasanaKentta.setText("");
            }        
        });
    }
    
        private void kirjauduSisaan(String tunnus, String salasana){
        Kayttaja kayttaja = kirjakanta.haeTunnus(tunnus);
            
        if(kayttaja == null) {
            infoLaatikko("Annettua käyttäjätunnusta '" + tunnus + "' ei ole olemassa!", "Kirjautuminen");
            return;
        }
        if (BCrypt.checkpw(salasana, kayttaja.getSalasana())){  
           paaIkkuna.valikonVaihtaminen(PaaIkkuna.valikkoTila.PAAVALIKKO);
            //PaaIkkuna.setKayttaja(kayttaja); //Jos haluaa tehdä lokitiedoston, kuka muutti
        } else {
            paaIkkuna.valikonVaihtaminen(PaaIkkuna.valikkoTila.PAAVALIKKO);
            infoLaatikko("Väärä käyttäjätunnus tai salasana!", "Kirjautuminen");
        }
    }
    
    private void rekisteroidy(String tunnus, String salasana){
        if(tunnus.isEmpty()){ //Estää tyhjän tunnuksen luomisen
            infoLaatikko("Anna 1-20 merkkiä pitkä tunnus!", "Rekisteröityminen");
            return;
        }
        if(salasana.length() <= 3){ //Estää tyhjän tunnuksen luomisen
            infoLaatikko("Anna yli 3 merkkiä pitkä salasana", "Rekisteröityminen");
            return;
        }
        if(kirjakanta.haeTunnus(tunnus) == null){ //Tekee tunnuksen, jos tietokannassa ei ole kyseistä nimeä
            kirjakanta.rekisteroiKayttaja(tunnus, BCrypt.hashpw(salasana, BCrypt.gensalt()));
            infoLaatikko("Käyttäjätunnus rekisteröity onnistuneesti!", "Rekisteröityminen");
        } else {
            infoLaatikko("Käyttäjätunnus on jo käytetty!\nValitse toinen tunnus!", "Rekisteröityminen");
        }
    }
    
    private void infoLaatikko(String viesti, String infonOtsikko) {
            JOptionPane.showMessageDialog(this.paneeli,viesti, infonOtsikko, 
                    JOptionPane.WARNING_MESSAGE);
    }

    private void asetteleKomponentit() {
        GroupLayout asettelu = new GroupLayout(paneeli);
        paneeli.setLayout(asettelu);

        asettelu.setAutoCreateGaps(true);
        asettelu.setAutoCreateContainerGaps(true);

        //X
        GroupLayout.ParallelGroup kentatX = asettelu.createParallelGroup(); //Kirjoituskentät päällekkäin
        kentatX.addComponent(tunnusKentta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
        kentatX.addComponent(salasanaKentta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);

        GroupLayout.SequentialGroup nappulatX = asettelu.createSequentialGroup(); //Nappulat vierekkäin
        nappulatX.addComponent(kirjaudu, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
        nappulatX.addComponent(rekisteroidy, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);

        GroupLayout.ParallelGroup elementit = asettelu.createParallelGroup(); //Päällekkäin 
        elementit.addGroup(kentatX);    //ensin yllä asetetut kirjoituskentät
        elementit.addGroup(nappulatX);  //Niiden alapuolelelle nappulaelementti

        GroupLayout.ParallelGroup pohjaX = asettelu.createParallelGroup(); //Laitetaan koko mölli yhteen
        pohjaX.addGroup(elementit);

        asettelu.setHorizontalGroup(pohjaX);

        //Y
        GroupLayout.ParallelGroup tunnusRivi = asettelu.createParallelGroup();
        tunnusRivi.addComponent(tunnusKentta);  //Eka rivi

        GroupLayout.ParallelGroup salasanaRivi = asettelu.createParallelGroup();
        salasanaRivi.addComponent(salasanaKentta); //toka rivi

        GroupLayout.ParallelGroup nappulaSarake = asettelu.createParallelGroup();
        nappulaSarake.addComponent(kirjaudu);
        nappulaSarake.addComponent(rekisteroidy); //Kolmas rivi. Molemmat on samassa rivissä.
        asettelu.linkSize(kirjaudu, rekisteroidy);

        GroupLayout.SequentialGroup pohjaY = asettelu.createSequentialGroup();
        pohjaY.addGroup(tunnusRivi);  //eka rivi
        pohjaY.addGroup(salasanaRivi);  //Toka rivi
        pohjaY.addGroup(nappulaSarake); //kolmas rivi

        asettelu.setVerticalGroup(pohjaY);
    }

    public JPanel getPaneeli() {
        return paneeli;
    }

}
