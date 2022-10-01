package composite;

import static support.Util.findUnit;

 /**
 * "CompositeTest" testa le classi che seguono il pattern Composite.
 */

public class CompositeTest {

    public static void main(String[] args) {

        //Seguendo l'esempio riportato nella traccia, dichiaramo delle unità.

        AbstractCompositeUnit consiglio = new LeafUnit("Consiglio di Amministrazione");
        AbstractCompositeUnit comitato = new LeafSubunit("Comitato Tecnico Scientifico");
        AbstractCompositeUnit ricerca = new LeafBody("Ricerca e Sviluppo");
        AbstractCompositeUnit acquisti = new LeafSubunit("Acquisti");
        AbstractCompositeUnit produzione = new LeafSubunit("Produzione");
        AbstractCompositeUnit area = new LeafSubunit("Area Vendite");
        AbstractCompositeUnit marketing = new LeafBody("Marketing");
        AbstractCompositeUnit customer = new LeafBody("Customer Care");

        //Definiamo una gerarchia

        consiglio.addChild(comitato);
        consiglio.addChild(acquisti);
        consiglio.addChild(produzione);
        consiglio.addChild(area);

        comitato.addChild(ricerca);

        area.addChild(marketing);
        area.addChild(customer);

        //Testiamo

        System.out.println(ricerca.getType());
        acquisti.setType("Area Acquisti");
        System.out.println(acquisti.getType());
        System.out.println(marketing.getParent());
        System.out.println(findUnit(consiglio,"Ricerca e Sviluppo"));
    }//main

}//CompositeTest
