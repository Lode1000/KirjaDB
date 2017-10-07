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

    public static enum valikkoTila{KIRJAUTUMINEN, PAAVALIKKO};

    private JPanel pohjapaneeli;
    
    private Kirjautuminen kirjautuminen;
    private Paavalikko paavalikko;
    private Kirjavarasto kirjakanta;

    public PaaIkkuna() {
        this.pohjapaneeli = new JPanel();
        this.kirjakanta = new Kirjavarasto();
        this.kirjautuminen = new Kirjautuminen(this);
        this.paavalikko = new Paavalikko(this);
        
        this.pohjapaneeli.add(kirjautuminen.getPaneeli());
        this.add(pohjapaneeli);
        this.setTitle("Pääikkuna");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.pack();

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                suoritaLopputoimet();
            }
        });

    }
    
    public void valikonVaihtaminen(valikkoTila valikkotila) {
         if (valikkotila == valikkoTila.PAAVALIKKO) {
             this.pohjapaneeli.removeAll();
             this.paavalikko = new Paavalikko(this);
             this.pohjapaneeli.add(this.paavalikko.getPaneeli());
             this.pack();
             //this.setVisible(true);
         } else if (valikkotila == valikkoTila.KIRJAUTUMINEN){
             this.pohjapaneeli.removeAll();
             this.kirjautuminen = new Kirjautuminen(this);
             this.pohjapaneeli.add(this.kirjautuminen.getPaneeli());
             this.pack();
             //this.setVisible(true);
         }
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
