package visitor;

import composite.LeafBody;
import composite.LeafSubunit;
import composite.LeafUnit;

 /**
 * "Visitor" dichiara un metodo di visita per ogni classe concreta della struttura.
 * Il nome e la firma del metodo identificano la classe che invia la richiesta di visita al visitor in modo che esso possa identificare la classe dell’elemento che sta per visitare.
 */

public interface Visitor {

    void visitLeafUnit(LeafUnit unit);

    void visitLeafSubunit(LeafSubunit subunit);

    void visitLeafBody(LeafBody body);

}//Visitor
