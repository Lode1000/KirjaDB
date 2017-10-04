package kayttoliittyma;

import javax.swing.GroupLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import tietovarasto.Kirja;

/**
 *
 * @author s1400591
 */
public class Tietoikkuna extends JPanel {

    private final JLabel kirjaIdSelite = new JLabel("kirjaid");
    private final JLabel nimiSelite = new JLabel("nimi");
    private final JLabel etunimiSelite = new JLabel("tekijanEtunimi");
    private final JLabel sukunimiSelite = new JLabel("tekijanSukunimi");
    private final JTextField kirjaId = new JTextField(7);
    private final JTextField nimi = new JTextField(40);
    private final JTextField etunimi = new JTextField(15);
    private final JTextField sukunimi = new JTextField(30);

    public Tietoikkuna() {
        asetteleKomponentit();
    }

    private void asetteleKomponentit() {
        GroupLayout asettelu = new GroupLayout(this);
        this.setLayout(asettelu);

        asettelu.setAutoCreateGaps(true);
        asettelu.setAutoCreateContainerGaps(true);

        //X
        GroupLayout.ParallelGroup seliteX = asettelu.createParallelGroup();
        seliteX.addComponent(kirjaIdSelite);
        seliteX.addComponent(nimiSelite);
        seliteX.addComponent(etunimiSelite);
        seliteX.addComponent(sukunimiSelite);

        GroupLayout.ParallelGroup kentatX = asettelu.createParallelGroup();
        kentatX.addComponent(kirjaId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
        kentatX.addComponent(nimi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
        kentatX.addComponent(etunimi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
        kentatX.addComponent(sukunimi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);

        GroupLayout.SequentialGroup tiedot = asettelu.createSequentialGroup();
        tiedot.addGroup(seliteX);
        tiedot.addGroup(kentatX);

        GroupLayout.ParallelGroup pohjaX = asettelu.createParallelGroup();
        pohjaX.addGroup(tiedot);
        
        asettelu.setHorizontalGroup(pohjaX);

        //Y
        GroupLayout.ParallelGroup IdRiviY = asettelu.createParallelGroup();
        IdRiviY.addComponent(kirjaIdSelite);
        IdRiviY.addComponent(kirjaId);

        GroupLayout.ParallelGroup nimiRiviY = asettelu.createParallelGroup();
        nimiRiviY.addComponent(nimiSelite);
        nimiRiviY.addComponent(nimi);

        GroupLayout.ParallelGroup etunimiRiviY = asettelu.createParallelGroup();
        etunimiRiviY.addComponent(etunimiSelite);
        etunimiRiviY.addComponent(etunimi);

        GroupLayout.ParallelGroup sukunimiRiviY = asettelu.createParallelGroup();
        sukunimiRiviY.addComponent(sukunimiSelite);
        sukunimiRiviY.addComponent(sukunimi);

        GroupLayout.SequentialGroup pohjaY = asettelu.createSequentialGroup();
        pohjaY.addGroup(IdRiviY);
        pohjaY.addGroup(nimiRiviY);
        pohjaY.addGroup(etunimiRiviY);
        pohjaY.addGroup(sukunimiRiviY);

        asettelu.setVerticalGroup(pohjaY);
    }
    
    public Kirja getKirjanTiedot(){
        Kirja kirja = new Kirja (Integer.parseInt(kirjaId.getText()), nimi.getText()
                , etunimi.getText(), sukunimi.getText());
        return  kirja;
    }
    
    public void tyhjennaKentat() {
        kirjaId.setText("");
        nimi.setText("");
        etunimi.setText("");
        sukunimi.setText("");
        kirjaId.requestFocus();
    }
    
    public void taytaKentat(Kirja kirja) {
        if(kirja==null) return;
        kirjaId.setText(Integer.toString(kirja.getId()));
        nimi.setText(kirja.getNimi());
        etunimi.setText(kirja.getTekijanEtunimi());
        sukunimi.setText(kirja.getTekijanSukunimi());
    }    
    
    public void aktiivisetKentat(boolean k) {
        if(!k){
            kirjaId.setEditable(false);
            nimi.setEditable(false);
            etunimi.setEditable(false);
            sukunimi.setEditable(false);
        } else {
            kirjaId.setEditable(false);
            nimi.setEditable(true);
            etunimi.setEditable(true);
            sukunimi.setEditable(true);
        }
    }

}
